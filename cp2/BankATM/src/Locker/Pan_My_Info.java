package Locker;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
public class Pan_My_Info extends JPanel implements ActionListener {
    private JLabel Label_title, Label_name, Label_id, Label_money;
    private JTextField Text_name, Text_id, Text_money;
    private JButton Button_back;

    Main MainFrame;
    public Pan_My_Info(Main parent){
        MainFrame = parent;
        init();
    }
    private void init(){
        setLayout(null);
        setBounds(0,0,450,600);
        Label_title = new JLabel("내 정보");
        Label_title.setFont(new Font("맑은 고딕",Font.BOLD,30));
        Label_title.setBounds(200,50,100,30);
        add(Label_title);
        Label_name = new JLabel("이름 : ");
        Label_name.setFont(new Font("맑은 고딕",Font.BOLD,20));
        Label_name.setBounds(50,150,150,30);
        add(Label_name);
        Label_id = new JLabel("ID : ");
        Label_id.setFont(new Font("맑은 고딕",Font.BOLD,20));
        Label_id.setBounds(50,200,150,30);
        add(Label_id);
        Label_money = new JLabel("잔액 : ");
        Label_money.setFont(new Font("맑은 고딕",Font.BOLD,20));
        Label_money.setBounds(50,250,150,30);
        add(Label_money);
        Text_name = new JTextField();
        Text_name.setBounds(200,150,200,30);
        Text_name.setEditable(false);
        add(Text_name);
        Text_id = new JTextField();
        Text_id.setBounds(200,200,200,30);
        Text_id.setEditable(false);
        add(Text_id);
        Text_money = new JTextField();
        Text_money.setBounds(200,250,200,30);
        Text_money.setEditable(false);
        add(Text_money);
        Button_back = new JButton("뒤로가기");
        Button_back.setBounds(200,300,150,30);
        Button_back.addActionListener(this);
        add(Button_back);
    }
    public void refresh(){
            Text_name.setText(MainFrame.account.get_name());
            Text_id.setText(MainFrame.account.get_id());
            Text_money.setText(Integer.toString(MainFrame.account.get_cash()));
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==Button_back){
            this.setVisible(false);
            MainFrame.display("Main");
        }
    }
}
