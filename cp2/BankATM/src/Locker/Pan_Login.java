package Locker;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Pan_Login extends JPanel implements ActionListener {
    private JLabel Label_title, Label_id, Label_pw;
    private JTextField Text_id;
    private JPasswordField Text_pw;
    private JButton Button_login, Button_join,Button_back;
    Thread thread;

    Main MainFrame;
    public Pan_Login(Main parent){
        MainFrame = parent;
        init();
    }
    private void init(){
        setLayout(null);
        setBounds(0,0,450,600);
        Label_title = new JLabel("로그인");
        Label_title.setFont(new Font("맑은 고딕",Font.BOLD,30));
        Label_title.setBounds(200,50,100,30);
        add(Label_title);
        Label_id = new JLabel("ID : ");
        Label_id.setFont(new Font("맑은 고딕",Font.BOLD,20));
        Label_id.setBounds(50,150,150,30);
        add(Label_id);
        Label_pw = new JLabel("PW : ");
        Label_pw.setFont(new Font("맑은 고딕",Font.BOLD,20));
        Label_pw.setBounds(50,200,150,30);
        add(Label_pw);
        Text_id = new JTextField();
        Text_id.setBounds(200,150,200,30);
        add(Text_id);
        Text_pw = new JPasswordField();
        Text_pw.setBounds(200,200,200,30);
        add(Text_pw);
        Button_login = new JButton("로그인");
        Button_login.setBounds(50,300,150,30);
        Button_login.addActionListener(this);
        add(Button_login);
        Button_join = new JButton("회원가입");
        Button_join.setBounds(200,300,150,30);
        Button_join.addActionListener(this);
        add(Button_join);
        Button_back = new JButton("뒤로가기");
        Button_back.setBounds(200,350,150,30);
        Button_back.addActionListener(this);
        add(Button_back);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==Button_login) {
            String id = Text_id.getText();
            String pw = Text_pw.getText();
            if (id.equals("") || pw.equals("")) {
                JOptionPane.showMessageDialog(null, "아이디와 비밀번호를 입력해주세요.", "로그인 실패", JOptionPane.ERROR_MESSAGE);
            } else {
                Main.sendAccount(new Account(id, pw, "", "", 0, 0,"Login"));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                if(MainFrame.check_login(id,pw)){
                    JOptionPane.showMessageDialog(null, "로그인 성공", "로그인 성공", JOptionPane.INFORMATION_MESSAGE);
                    MainFrame.setIsLogin(true);
                    this.setVisible(false);
                    MainFrame.display("Main");
                }
                else{
                    JOptionPane.showMessageDialog(null, "아이디와 비밀번호를 확인해주세요.", "로그인 실패", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        else if(e.getSource()==Button_join){
            this.setVisible(false);
            MainFrame.display("join");
        }
        else if(e.getSource()==Button_back){
            this.setVisible(false);
            MainFrame.display("Main");
        }
    }
}
