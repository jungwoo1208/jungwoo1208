package Locker;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

public class Pan_Rent extends JPanel implements ActionListener{
    private JLabel Label_title,Label_price,Label_number,Label_money;
    private JButton Button_back,Button_rent;
    private JComboBox ComboBox_time;
    private JTextField Text_number;
    private Main MainFrame;
    private locker []Locker;
    private int locker_number;
    public Pan_Rent(Main parent,locker []Locker){
        MainFrame = parent;
        this.Locker = Locker;
        init();
    }
    private void init() {
        setLayout(null);
        setBounds(0, 0, 450, 600);
        Label_title = new JLabel("락커 대여");
        Label_title.setFont(new Font("맑은 고딕", Font.BOLD, 30));
        Label_title.setBounds(150, 50, 200, 30);
        add(Label_title);
        Label_price = new JLabel("가격 : 1000원/시간");
        Label_price.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        Label_price.setBounds(50, 120, 200, 30);
        add(Label_price);
        Label_number = new JLabel("락커 번호");
        Label_number.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        Label_number.setBounds(50, 170, 100, 30);
        add(Label_number);
        Label_money = new JLabel("총 금액 : 0원");
        Label_money.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        Label_money.setBounds(50, 220, 200, 30);
        add(Label_money);
        Text_number = new JTextField();
        Text_number.setEditable(false);
        Text_number.setBounds(200, 170, 200, 30);
        add(Text_number);
        ComboBox_time = new JComboBox();
        ComboBox_time.setBounds(200, 270, 200, 30);
        for (int i = 1; i <= 24; i++) {
            ComboBox_time.addItem(i + "시간");
        }
        ComboBox_time.addActionListener(this);
        add(ComboBox_time);
        Button_rent = new JButton("대여하기");
        Button_rent.setBounds(200, 320, 150, 30);
        Button_rent.addActionListener(this);
        add(Button_rent);
        Button_back = new JButton("뒤로가기");
        Button_back.setBounds(200, 370, 150, 30);
        Button_back.addActionListener(this);
        add(Button_back);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==Button_back){
            this.setVisible(false);
            MainFrame.display("Main");
        }
        else if(e.getSource()==ComboBox_time){
            int time = ComboBox_time.getSelectedIndex()+1;
            Label_money.setText("총 금액 : "+time*1000+"원");
        }
        else if(e.getSource()==Button_rent){
            Pan_Locker_Rent.update();
            if(Locker[locker_number].getIsRented()){
                JOptionPane.showMessageDialog(null,"이미 대여중인 락커입니다.");
                return;
            }
            int time = ComboBox_time.getSelectedIndex()+1;
            if(time*1000>Main.account.get_cash()){
                JOptionPane.showMessageDialog(null,"잔액이 부족합니다.");
                return;
            }
            Locker[locker_number].setIsRented(true);
            Locker[locker_number].setRenter(Main.account.get_id());
            String currentTime = Calendar.getInstance().getTime().toString();
            String date = currentTime.substring(8,10);
            String time2 = currentTime.substring(11,13);
            String minute = currentTime.substring(14,16);
            int time3 = Integer.parseInt(time2)+time;
            if(time3>=24){
                time3-=24;
                int date2 = Integer.parseInt(date) +1;
                if(date2>=31){
                    date2-=30;
                }
                date = Integer.toString(date2).trim();
                if(date2<10){
                    date = "0"+date;
                }
            }
            time2 = Integer.toString(time3).trim();
            if(time3<10){
                time2 = "0"+time2;
            }
            Locker[locker_number].setTime(date+"일"+time2+"시"+minute+"분");
            Main.account.set_locker_number(locker_number+1);
            Main.account.set_cash(Main.account.get_cash()-time*1000);
            Main.sendLocker(Main.Locker);
            Main.account.set_purpose("Save");
            System.out.println(Main.account.get_purpose());
            System.out.println(Main.account.get_locker_number());
            Main.sendAccount(Main.account);
            JOptionPane.showMessageDialog(null,"대여가 완료되었습니다.");
            this.setVisible(false);
            MainFrame.display("Main");
        }
    }
    public void setLockerNumber(int locker_number){
        this.locker_number = locker_number;
        Text_number.setText(Integer.toString(locker_number+1).trim()+ "번");
    }
}
