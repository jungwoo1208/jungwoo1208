package Locker;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

public class Pan_Locker_Info extends JPanel implements ActionListener{
    private JLabel Label_title, Label_using_locker, Label_Locker_Time, Label_Locker_addTime,Label_Locker_price;
    private JTextField Text_using_locker, Text_Locker_Time,Text_Locker_cash,Test_Locker_price;
    private JComboBox ComboBox_time;

    private JButton Button_add, Button_back,Button_return;

    Main MainFrame;

    public Pan_Locker_Info(Main parent){
        MainFrame = parent;
        init();
    }
    private void init(){
        setLayout(null);
        setBounds(0,0,450,600);
        Label_title = new JLabel("사물함 정보");
        Label_title.setFont(new Font("맑은 고딕",Font.BOLD,30));
        Label_title.setBounds(200,50,100,30);
        add(Label_title);
        Label_using_locker = new JLabel("사용중인 사물함");
        Label_using_locker.setFont(new Font("맑은 고딕",Font.BOLD,20));
        Label_using_locker.setBounds(50,120,200,30);
        add(Label_using_locker);
        Text_using_locker = new JTextField();
        Text_using_locker.setEditable(false);
        Text_using_locker.setBounds(250,120,100,30);
        add(Text_using_locker);
        Label_Locker_Time = new JLabel("남은 시간");
        Label_Locker_Time.setFont(new Font("맑은 고딕",Font.BOLD,20));
        Label_Locker_Time.setBounds(50,170,200,30);
        add(Label_Locker_Time);
        Text_Locker_Time = new JTextField();
        Text_Locker_Time.setEditable(false);
        Text_Locker_Time.setBounds(250,170,100,30);
        add(Text_Locker_Time);
        Label_Locker_addTime = new JLabel("충전할 시간");
        Label_Locker_addTime.setFont(new Font("맑은 고딕",Font.BOLD,20));
        Label_Locker_addTime.setBounds(50,220,200,30);
        add(Label_Locker_addTime);
        ComboBox_time = new JComboBox();
        ComboBox_time.setBounds(250,220,100,30);
        for(int i=1;i<=24;i++){
            ComboBox_time.addItem(i+"시간");
        }
        ComboBox_time.addActionListener(this);
        add(ComboBox_time);
        Label_Locker_price = new JLabel("사물함 가격");
        Label_Locker_price.setFont(new Font("맑은 고딕",Font.BOLD,20));
        Label_Locker_price.setBounds(50,270,200,30);
        add(Label_Locker_price);
        Test_Locker_price = new JTextField();
        Test_Locker_price.setEditable(false);
        Test_Locker_price.setBounds(250,270,100,30);
        add(Test_Locker_price);
        Button_add = new JButton("충전하기");
        Button_add.setBounds(50,320,150,30);
        Button_add.addActionListener(this);
        add(Button_add);
        Button_return = new JButton("반납하기");
        Button_return.setBounds(250,320,150,30);
        Button_return.addActionListener(this);
        add(Button_return);
        Button_back = new JButton("뒤로가기");
        Button_back.setBounds(200,370,150,30);
        Button_back.addActionListener(this);
        add(Button_back);
    }
    public void refresh(){
        Text_using_locker.setText(Main.account.get_locker_number()+"번 사물함");
        if(Main.account.get_locker_number()==0){
            Text_Locker_Time.setText("대여한 사물함이 없습니다.");
        }
        else{
            Calendar cal = Calendar.getInstance();
            int day = cal.get(Calendar.DAY_OF_MONTH);
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            int minute = cal.get(Calendar.MINUTE);
            int day2 = Integer.parseInt(Main.Locker[Main.account.get_locker_number()-1].getTime().substring(0,2));
            int hour2 = Integer.parseInt(Main.Locker[Main.account.get_locker_number()-1].getTime().substring(3,5).trim());
            int minute2 = Integer.parseInt(Main.Locker[Main.account.get_locker_number()-1].getTime().substring(6,8).trim());
            int time = (day2-day)*24*60+(hour2-hour)*60+(minute2-minute);
            int time2 = time/60;
            String time3 = String.valueOf(time2);
            if(time2<10){
                time3 = "0"+time3;
            }
            Text_Locker_Time.setText(time3+"시간 "+time%60+"분");
        }
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==Button_back) {
            this.setVisible(false);
            MainFrame.display("Main");
        }else if(e.getSource()==ComboBox_time){
            int time = ComboBox_time.getSelectedIndex()+1;
            Test_Locker_price.setText(time*1000+"원");
        } else if (e.getSource() == Button_add) {
            int time = ComboBox_time.getSelectedIndex()+1;
            if(Main.account.get_locker_number()==0){
                JOptionPane.showMessageDialog(null,"대여한 사물함이 없습니다.");
                return;
            }
            if(time*1000>Main.account.get_cash()){
                JOptionPane.showMessageDialog(null,"잔액이 부족합니다.");
                return;
            }
            Calendar cal = Calendar.getInstance();
            int day = cal.get(Calendar.DAY_OF_MONTH);
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            int minute = cal.get(Calendar.MINUTE);
            int day2 = Integer.parseInt(Main.Locker[Main.account.get_locker_number()-1].getTime().substring(0,2).trim());
            int hour2 = Integer.parseInt(Main.Locker[Main.account.get_locker_number()-1].getTime().substring(3,5).trim());
            int minute2 = Integer.parseInt(Main.Locker[Main.account.get_locker_number()-1].getTime().substring(6,8).trim());
            int time2 = (day2-day)*24*60+(hour2-hour)*60+(minute2-minute);
            int time3 = time2+time*60;
            int day3 = day+time3/(24*60);
            int hour3 = hour+time3%(24*60)/60;
            int minute3 = minute+time3%(24*60)%60;
            String day4 = String.valueOf(day3);
            if(minute3>=60){
                hour3+=1;
                minute3-=60;
            }
            if(day3<10){
                day4 = "0"+day4;
            }
            String hour4 = String.valueOf(hour3);
            if(hour3<10){
                hour4 = "0"+hour4;
            }
            String minute4 = String.valueOf(minute3);
            if(minute3<10){
                minute4 = "0"+minute4;
            }
            Main.Locker[Main.account.get_locker_number()-1].setTime(day4+"일"+hour4+"시"+minute4+"분");
            Main.account.set_cash(Main.account.get_cash()-time*1000);
            Main.account.set_purpose("Save");
            Main.sendAccount(Main.account);
            Main.sendLocker(Main.Locker);
            refresh();
            JOptionPane.showMessageDialog(null,"충전되었습니다.");
        } else if (e.getSource() == Button_return) {
            if(Main.account.get_locker_number()==0){
                JOptionPane.showMessageDialog(null,"대여한 사물함이 없습니다.");
                return;
            }
            Calendar cal = Calendar.getInstance();
            int day = cal.get(Calendar.DAY_OF_MONTH);
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            int minute = cal.get(Calendar.MINUTE);
            int day2 = Integer.parseInt(Main.Locker[Main.account.get_locker_number()-1].getTime().substring(0,2).trim());
            int hour2 = Integer.parseInt(Main.Locker[Main.account.get_locker_number()-1].getTime().substring(3,5).trim());
            int minute2 = Integer.parseInt(Main.Locker[Main.account.get_locker_number()-1].getTime().substring(6,8).trim());
            int time = (day2-day)*24*60+(hour2-hour)*60+(minute2-minute);
            int time2 = time/60;
            int time5 = time2*1000+time%60*1000/60;
            Main.Locker[Main.account.get_locker_number()-1].setIsRented(false);
            Main.Locker[Main.account.get_locker_number()-1].setRenter("");
            Main.Locker[Main.account.get_locker_number()-1].setTime("0");
            Main.account.set_cash(Main.account.get_cash()+time5);
            Main.account.set_locker_number(0);
            Main.account.set_purpose("Save");
            Main.sendAccount(Main.account);
            Main.sendLocker(Main.Locker);
            refresh();
            JOptionPane.showMessageDialog(null,"반납되었습니다.");
        }
    }
}
