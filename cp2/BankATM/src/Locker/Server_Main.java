package Locker;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;

public class Server_Main extends JFrame implements ActionListener {

    private List<Account> AccountList= new Vector<>();
    private locker[] Locker = new locker[36];

    private HashMap clients;

    private ServerSocket serverSocket1;
    private ServerSocket serverSocket2;


    private JToggleButton Btn_StartStop;
    private JButton Btn_Reset;
    private JTextArea TextArea_Log;
    private JScrollPane sp;
    ArrayList<ServerReceiver1>serverReceiver1s=new ArrayList<>();
    ArrayList<ServerReceiver2>serverReceiver2s=new ArrayList<>();
    private JLabel title;

    ArrayList<SocketList>socketLists=new ArrayList<>();
    private volatile boolean isServerRunning = false;
    public static void main(String[] args) {
        InetAddress inetAddress = null;
        try {
            inetAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        String ipAddress = inetAddress.getHostAddress();

        System.out.println("현재 시스템의 IP 주소: " + ipAddress);
        new Server_Main();
    }
    public Server_Main() {

        clients = new HashMap();
        Collections.synchronizedMap(clients);
        Load_Data();
        load_locker();
        check_data();
        Initgui();
    }
    public void check_data(){
        String currentTime = Calendar.getInstance().getTime().toString();
        String date = currentTime.substring(8,10);
        String time2 = currentTime.substring(11,13);
        String minute = currentTime.substring(14,16);
        for(locker Locker : Locker){
            if(Locker.getIsRented()) {
                if(Integer.parseInt(Locker.getTime().substring(0,2))<Integer.parseInt(date)){
                    Locker.setIsRented(false);
                    Locker.setRenter("");
                    Locker.setTime("0");
                }
                else if(Integer.parseInt(Locker.getTime().substring(0,2))==Integer.parseInt(date)){
                    if(Integer.parseInt(Locker.getTime().substring(3,5))<Integer.parseInt(time2)){
                        Locker.setIsRented(false);
                        Locker.setRenter("");
                        Locker.setTime("0");
                    }
                    else if(Integer.parseInt(Locker.getTime().substring(3,5))==Integer.parseInt(time2)){
                        if(Integer.parseInt(Locker.getTime().substring(6,8))<Integer.parseInt(minute)){
                            Locker.setIsRented(false);
                            Locker.setRenter("");
                            Locker.setTime("0");
                        }
                    }
                }
            }
        }
        save_Data2();
    }

    public void load_locker(){
        try {
            File file = new File("C:\\Users\\gjw19\\IdeaProjects\\comp2\\LockerData.txt");
            FileReader filereader = new FileReader(file);
            BufferedReader bufReader = new BufferedReader(filereader);
            String line = "";
            while((line = bufReader.readLine()) != null){
                String[] data = line.split(" ");
                if(data.length!=2&&data.length!=4)continue;
                if(data.length==2)Locker[Integer.parseInt(data[0])-1] = new locker(Integer.parseInt(data[0]),Boolean.parseBoolean(data[1]));
                else Locker[Integer.parseInt(data[0])-1] = new locker(Integer.parseInt(data[0]),Boolean.parseBoolean(data[1]),data[2],data[3]);
            }
            bufReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("파일이 없습니다.");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void Initgui() {
        setTitle("서버 GUI");
        setSize(480, 320);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        mainPanel.add(topPanel, BorderLayout.NORTH);

        TextArea_Log = new JTextArea();
        TextArea_Log.setEditable(false);
        sp = new JScrollPane(TextArea_Log);
        mainPanel.add(sp, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        Btn_StartStop = new JToggleButton("시작");
        Btn_StartStop.addActionListener(this);
        bottomPanel.add(Btn_StartStop);

        Btn_Reset = new JButton("텍스트 창 초기화");
        Btn_Reset.addActionListener(this);
        bottomPanel.add(Btn_Reset);

        title = new JLabel("현재 접속자 수:"+clients.size());
        title.setFont(new Font("맑은 고딕",Font.BOLD,13));
        title.setBounds(185,0,200,20);
        add(title);


        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);

        setLocationRelativeTo(null);
        setVisible(true);

    }
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == Btn_StartStop)
        {
            if (Btn_StartStop.isSelected())
            {
                startServer();
            } else
            {
                stopServer();
            }
        }

        else if (e.getSource() == Btn_Reset)
        {
            TextArea_Log.setText(null);
        }
    }
    public void startServer(){
        isServerRunning = true;
        new Thread(() -> {
            try{
                serverSocket1 = new ServerSocket(7777);
                serverSocket2 = new ServerSocket(7778);
                addMsg("서버가 시작되었습니다.");
                Btn_StartStop.setText("종료");
                while (isServerRunning){
                    Socket socket1 = serverSocket1.accept();
                    Socket socket2 = serverSocket2.accept();
                    addMsg("["+ socket1.getInetAddress()+":"+ socket1.getPort()+"]"+"에서 접속하였습니다.");
                    addMsg("["+ socket2.getInetAddress()+":"+ socket2.getPort()+"]"+"에서 접속하였습니다.");
                    send(Locker,socket2);
                    clients.put(socket2.getInetAddress()+":"+ socket2.getPort(), new DataOutputStream(socket2.getOutputStream()));
                    title.setText("현재 접속자 수:"+clients.size());
                    socketLists.add(new SocketList(socket1,socket2));
                    serverReceiver1s.add(new ServerReceiver1(socket1));
                    serverReceiver2s.add(new ServerReceiver2(socket2));
                    serverReceiver1s.get(serverReceiver1s.size()-1).start();
                    serverReceiver2s.get(serverReceiver2s.size()-1).start();
                }
            } catch (IOException e) {
                if (isServerRunning) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public void save_Data1(){
        try {
            File file = new File("C:\\Users\\gjw19\\IdeaProjects\\comp2\\UserData.txt");
            FileWriter fw = new FileWriter(file);
            fw.write("");
            BufferedWriter bw = new BufferedWriter(fw);
            for(Account AccountList : AccountList){
                bw.write(AccountList.get_id()+" "+AccountList.get_pw()+" "+AccountList.get_name()+" "+AccountList.get_phone()+" "+AccountList.get_cash()+" "+AccountList.get_locker_number());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public void save_Data2(){
        try {
            File file = new File("C:\\Users\\gjw19\\IdeaProjects\\comp2\\LockerData.txt");
            FileWriter fw = new FileWriter(file);
            fw.write("");
            BufferedWriter bw = new BufferedWriter(fw);
            for(locker Locker : Locker){
                bw.write(Locker.getNumber()+" "+Locker.getIsRented()+" "+Locker.getRenter()+" "+Locker.getTime());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    class ServerReceiver1 extends Thread{
        Socket socket;
        ObjectInputStream ois;
        String clientKey;

        ServerReceiver1(Socket socket){
            this.socket = socket;
            this.clientKey = socket.getInetAddress() + ":" + socket.getPort();
            try{
                ois = new ObjectInputStream(socket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public void run(){
            try{
                while(ois!=null){
                    Account account = (Account) ois.readObject();
                    if(account.get_purpose().equals("Login")) {
                            for (Account AccountList : AccountList) {
                                if (account.get_id().equals(AccountList.get_id())) {
                                    send(AccountList,socket);
                                    break;
                                }
                            }
                    }
                    if(account.get_purpose().equals("Save")){
                            for(Account AccountList : AccountList){
                                if(account.get_id().equals(AccountList.get_id())){
                                    AccountList.set_cash(account.get_cash());
                                    AccountList.set_locker_number(account.get_locker_number());
                                    break;
                                }
                            }
                            save_Data1();
                    }
                    if(account.get_purpose().equals("Join")){
                            boolean check = false;
                            for(int i=0;i<AccountList.size();i++) {
                                if (account.get_id().equals(AccountList.get(i).get_id())) {
                                    check = true;
                                    break;
                                }
                            }
                            if(check){
                                send(new Account("","","","",0,0,"Join_fail"),socket);
                            }
                            else{
                                AccountList.add(new Account(account.get_id(),account.get_pw(),account.get_name(),account.get_phone(),0,0));
                                addMsg("["+socket.getInetAddress()+":"+socket.getPort()+"]"+"에서 회원가입하였습니다.");
                                save_Data1();
                                send(new Account("","","","",0,0,"Join_success"),socket);
                            }
                        }

                }
            } catch (IOException e) {
                // 클라이언트 연결 종료 감지
                clients.remove(clientKey); // 클라이언트 목록에서 제거
                addMsg("["+socket.getInetAddress()+":"+socket.getPort()+"]"+"에서 접속을 종료하였습니다.");
                title.setText("현재 접속자 수:"+clients.size()); // 이용자 수 업데이트
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } finally {
                title.setText("현재 접속자 수:"+clients.size());
            }
        }
    }
    class ServerReceiver2 extends Thread{
        Socket socket;
        ObjectInputStream ois;
        String clientKey;

        ServerReceiver2(Socket socket){
            this.socket = socket;
            this.clientKey = socket.getInetAddress() + ":" + socket.getPort();
            try{
                ois = new ObjectInputStream(socket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public void run(){
            try{
                while(ois!=null){
                    Locker = (locker[]) ois.readObject();
                    save_Data2();
                    send_all(Locker);
                }
            } catch (IOException e) {
                // 클라이언트 연결 종료 감지
                clients.remove(clientKey); // 클라이언트 목록에서 제거
                addMsg("["+socket.getInetAddress()+":"+socket.getPort()+"]"+"에서 접속을 종료하였습니다.");
                title.setText("현재 접속자 수:"+clients.size()); // 이용자 수 업데이트
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } finally {
                title.setText("현재 접속자 수:"+clients.size());
            }
        }
    }
    public void send(Account account,Socket socket1){
        try{
            ObjectOutputStream oos = new ObjectOutputStream(socket1.getOutputStream());
            oos.writeObject(account);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void send(locker[] locker,Socket socket2){
        try{
            ObjectOutputStream oos = new ObjectOutputStream(socket2.getOutputStream());
            oos.writeObject(locker);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void send_all(locker[] locker) {
        try {
            Iterator iterator = clients.keySet().iterator();
            while (iterator.hasNext()) {
                DataOutputStream out = (DataOutputStream) clients.get(iterator.next());
                if (out != null) {
                    ObjectOutputStream oos = new ObjectOutputStream(out);
                    oos.writeObject(locker);
                    oos.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void stopServer(){
        isServerRunning = false;
        Btn_StartStop.setText("시작");
        save_Data1();
        try{
            Iterator iterator = clients.keySet().iterator();
            addMsg("서버가 종료되었습니다.");
            while (iterator.hasNext()){
                DataOutputStream out = (DataOutputStream) clients.get(iterator.next());
                if (out != null) {
                    out.close();
                }
            }
            clients.clear();
            title.setText("현재 접속자 수:"+clients.size());
            if (serverSocket1 != null&&serverSocket2!=null) {
                serverSocket1.close();
                serverSocket2.close();
            }
            for(int i=0;i<socketLists.size();i++){
                Socket socket1 = socketLists.get(i).getSocket1();
                Socket socket2 = socketLists.get(i).getSocket2();
                if (socket1 != null&&socket2!=null) {
                    socket1.close();
                    socket2.close();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void Load_Data(){
        try {
            File file = new File("C:\\Users\\gjw19\\IdeaProjects\\comp2\\UserData.txt");
            FileReader filereader = new FileReader(file);
            BufferedReader bufReader = new BufferedReader(filereader);
            String line = "";
            while((line = bufReader.readLine()) != null){
                String[] data = line.split(" ");
                if(data.length!=6)continue;
                AccountList.add(new Account(data[0], data[1], data[2], data[3], Integer.parseInt(data[4]), Integer.parseInt(data[5])));
            }
            bufReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("파일이 없습니다.");
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public void addMsg(String data) {
        TextArea_Log.append(getTime()+data + "\n");
    }
    static String getTime(){
        SimpleDateFormat f = new SimpleDateFormat("[hh:mm:ss]");
        return f.format(new Date());
    }
}
