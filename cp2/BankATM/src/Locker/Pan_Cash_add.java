package Locker;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Pan_Cash_add extends JPanel implements ActionListener {
    private JLabel Label_title, Label_money, Label_money_add;
    private JTextField Text_money, Text_money_add;
    private JButton Button_add, Button_back;

    Main MainFrame;

    public Pan_Cash_add(Main parent){
        MainFrame = parent;
        init();
    }
    private void init(){
        setLayout(null);
        setBounds(0,0,450,600);
        Label_title = new JLabel("충전");
        Label_title.setFont(new Font("맑은 고딕",Font.BOLD,30));
        Label_title.setBounds(200,50,100,30);
        add(Label_title);
        Label_money = new JLabel("현재 잔액 : ");
        Label_money.setFont(new Font("맑은 고딕",Font.BOLD,20));
        Label_money.setBounds(50,150,150,30);
        add(Label_money);
        Label_money_add = new JLabel("충전 금액 : ");
        Label_money_add.setFont(new Font("맑은 고딕",Font.BOLD,20));
        Label_money_add.setBounds(50,200,150,30);
        add(Label_money_add);
        Text_money = new JTextField();
        Text_money.setBounds(200,150,200,30);
        Text_money.setEditable(false);
        add(Text_money);
        Text_money_add = new JTextField();
        Text_money_add.setBounds(200,200,200,30);
        add(Text_money_add);
        Button_add = new JButton("충전");
        Button_add.setBounds(50,250,150,30);
        Button_add.addActionListener(this);
        add(Button_add);
        Button_back = new JButton("뒤로가기");
        Button_back.setBounds(200,250,150,30);
        Button_back.addActionListener(this);
        add(Button_back);
    }
    public void refresh(){
        Text_money.setText(Integer.toString(Main.account.get_cash()));
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==Button_add){
            int cash = Integer.parseInt(Text_money_add.getText());
            Main.account.set_cash(Main.account.get_cash()+cash);
            Main.account.set_purpose("Save");
            Main.sendAccount(Main.account);
            Text_money_add.setText(null);
            refresh();
        }
        else if(e.getSource()==Button_back){
            this.setVisible(false);
            MainFrame.display("Main");
        }
    }
}
