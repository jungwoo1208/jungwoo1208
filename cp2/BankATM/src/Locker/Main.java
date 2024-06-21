package Locker;


import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.*;



import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Main extends JFrame implements ActionListener  {
    public static Account account=null;

    static boolean isLogin = false;

    private JLabel title;
    private JButton btn_locker_info,btn_locker_rent,btn_login,btn_cash_add,btn_my_info,btn_exit;
    Pan_Locker_Info pan_locker_info;
    Pan_Locker_Rent pan_locker_rent;
    Pan_Login pan_login;
    Pan_Cash_add pan_cash_add;
    Pan_My_Info pan_my_info;

    Pan_Join pan_join;
    Socket socket1;

    Pan_Rent pan_rent;
    static locker []Locker = new locker[36];
    Socket socket2;

    private static ObjectOutputStream oos1;
    private static ObjectOutputStream oos2;

    SimpleDateFormat sdf;
    public void setIsLogin(boolean isLogin) {
        Main.isLogin = isLogin;
    }
    public void setAccount(Account account) {
        Main.account = account;
    }


    public Main(){
        for(int i=0;i<36;i++){
            Locker[i] = new locker(i,false);
        }
        init();
        setVisible(true);
        client_start();
    }
    private void init(){
        setLayout(null);
        setTitle("Locker");
        setSize(450,600);

        title = new JLabel("사물함 대여");
        title.setFont(new Font("맑은 고딕",Font.BOLD,30));
        title.setBounds(125,50,200,30);
        add(title);

        btn_locker_info = new JButton("사물함 정보");
        btn_locker_info.setBounds(20,150,200,125);
        btn_locker_info.addActionListener(this);
        add(btn_locker_info);

        btn_locker_rent = new JButton("사물함 대여");
        btn_locker_rent.setBounds(20,275,200,125);
        btn_locker_rent.addActionListener(this);
        add(btn_locker_rent);

        btn_login = new JButton("로그인");
        btn_login.setBounds(20,400,200,125);
        btn_login.addActionListener(this);
        add(btn_login);

        btn_cash_add = new JButton("충전");
        btn_cash_add.setBounds(225,150,200,125);
        btn_cash_add.addActionListener(this);
        add(btn_cash_add);

        btn_my_info = new JButton("내 정보");
        btn_my_info.setBounds(225,275,200,125);
        btn_my_info.addActionListener(this);
        add(btn_my_info);

        btn_exit = new JButton("종료");
        btn_exit.setBounds(225,400,200,125);
        btn_exit.addActionListener(this);
        add(btn_exit);

        pan_locker_info = new Pan_Locker_Info(this);
        add(pan_locker_info);
        pan_locker_info.setVisible(false);
        pan_rent = new Pan_Rent(this, Locker);
        pan_locker_rent = new Pan_Locker_Rent(this,Locker,pan_rent);
        add(pan_locker_rent);
        pan_locker_rent.setVisible(false);

        pan_login = new Pan_Login(this);
        add(pan_login);
        pan_login.setVisible(false);

        pan_cash_add = new Pan_Cash_add(this);
        add(pan_cash_add);
        pan_cash_add.setVisible(false);

        pan_my_info = new Pan_My_Info(this);
        add(pan_my_info);
        pan_my_info.setVisible(false);

        pan_join = new Pan_Join(this);
        add(pan_join);
        pan_join.setVisible(false);

        pan_rent = new Pan_Rent(this,Locker);
        add(pan_rent);
        pan_rent.setVisible(false);

    }
    public boolean check_login(String id,String pw){
        if(account==null){
            return false;
        }
        return account.get_id().equals(id) && account.get_pw().equals(pw);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==btn_locker_info){
            if(!isLogin){
                JOptionPane.showMessageDialog(null,"로그인을 해주세요");
            }else {
                pan_locker_info.refresh();
                display("pan_locker_info");
            }
        } else if (e.getSource()==btn_locker_rent){
            if(!isLogin){
                JOptionPane.showMessageDialog(null,"로그인을 해주세요");
            }else {
                display("pan_locker_rent");
            }
        } else if (e.getSource()==btn_login){
            display("pan_login");
        } else if (e.getSource()==btn_cash_add){
            if(!isLogin){
                JOptionPane.showMessageDialog(null,"로그인을 해주세요");
            }else {
                pan_cash_add.refresh();
                display("pan_cash_add");
            }
        } else if (e.getSource()==btn_my_info){
            if(!isLogin){
                JOptionPane.showMessageDialog(null,"로그인을 해주세요");
            }else {
                pan_my_info.refresh();
                display("pan_my_info");
            }
        } else if (e.getSource()==btn_exit){
            client_stop();
            System.exit(0);
        } else if (e.getSource()==pan_join) {
            display("pan_join");
        } else if (e.getSource()==pan_rent) {
            display("pan_rent");
        }
    }
    public void display(String viewName){
        if(viewName.equals("pan_locker_info")){
            pan_locker_info.setVisible(true);
            SetFrameUi(false);
        } else if (viewName.equals("pan_locker_rent")){
            pan_locker_rent.update();
            pan_locker_rent.setVisible(true);
            SetFrameUi(false);
        } else if (viewName.equals("pan_login")){
            pan_login.setVisible(true);
            SetFrameUi(false);
        } else if (viewName.equals("pan_cash_add")){
            pan_cash_add.setVisible(true);
            SetFrameUi(false);
        } else if (viewName.equals("pan_my_info")){
            pan_my_info.setVisible(true);
            SetFrameUi(false);
        } else if (viewName.equals("Main")){
            SetFrameUi(true);
        }else if (viewName.equals("join")){
            pan_join.setVisible(true);
            SetFrameUi(false);
        }
    }
    public void display(String viewName,int locker_number){
        if(viewName.equals("pan_rent")){
            pan_rent.setLockerNumber(locker_number);
            pan_rent.setVisible(true);
            SetFrameUi(false);
        }
    }

    void SetFrameUi(boolean b){
        if(b){
            title.setVisible(true);
            btn_locker_info.setVisible(true);
            btn_locker_rent.setVisible(true);
            btn_login.setVisible(true);
            btn_cash_add.setVisible(true);
            btn_my_info.setVisible(true);
            btn_exit.setVisible(true);
        }else {
            title.setVisible(false);
            btn_locker_info.setVisible(false);
            btn_locker_rent.setVisible(false);
            btn_login.setVisible(false);
            btn_cash_add.setVisible(false);
            btn_my_info.setVisible(false);
            btn_exit.setVisible(false);
        }
    }
    public void client_start(){
        try{
            socket1 =new Socket("192.168.0.132",7777);//서버에 따라 ip 주소 변경
            socket2 = new Socket("192.168.0.132",7778);//서버에 따라 ip 주소 변경
            System.out.println("서버에 연결되었습니다.");
            oos1 = new ObjectOutputStream(socket1.getOutputStream());
            oos2 = new ObjectOutputStream(socket2.getOutputStream());
            Thread receiver1 = new Thread(new ClientReceiver1(socket1));
            Thread receiver2 = new Thread(new ClientReceiver2(socket2));
            receiver1.start();
            receiver2.start();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void client_stop(){
        try{
            sendAccount(account);
            sendLocker(Locker);
            socket1.close();
            socket2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void sendAccount(Account account) {
        try {
            oos1.reset();
            oos1.writeObject(account);
            oos1.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void sendLocker(locker[] locker) {
        try {
            oos2.reset();
            oos2.writeObject(locker);
            oos2.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    class ClientReceiver1 extends Thread{
        Socket socket;
        ObjectInputStream ois;

        ClientReceiver1(Socket socket){
            this.socket = socket;
            try{
                ois = new ObjectInputStream(socket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public void run(){
            try{
                while(ois!=null){
                    try {
                        Account receivedAccount = (Account) ois.readObject();
                        setAccount(receivedAccount);
                        ois = new ObjectInputStream(socket.getInputStream());
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    static class ClientReceiver2 extends Thread{
        Socket socket;
        ObjectInputStream ois;

        ClientReceiver2(Socket socket){
            this.socket = socket;
            try{
                ois = new ObjectInputStream(socket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public void run(){
            try{
                while(true){
                    try {
                        locker[] receivedLocker = (locker[]) ois.readObject();
                        for(int i=0;i<36;i++){
                            Locker[i] = receivedLocker[i];
                        }
                        ois = new ObjectInputStream(socket.getInputStream());
                        Pan_Locker_Rent.update();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[]args){
        new Main();
    }
}
