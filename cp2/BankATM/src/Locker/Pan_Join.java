package Locker;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Pan_Join extends JPanel implements ActionListener{
    private JLabel Label_title,Label_id,Label_pw,Label_pw_check,Label_name,Label_phone;
    private JTextField Text_id,Text_name,Text_phone;
    private JPasswordField Text_pw,Text_pw_check;
    private JButton Button_join,Button_back;
    private JPanel panel;
    private Main MainFrame;
    public Pan_Join(Main parent){
        MainFrame = parent;
        init();
    }
    private void init(){
        setLayout(null);
        setBounds(0,0,450,600);
        Label_title = new JLabel("회원가입");
        Label_title.setFont(new Font("맑은 고딕",Font.BOLD,30));
        Label_title.setBounds(150,50,200,30);
        add(Label_title);
        Label_id = new JLabel("아이디");
        Label_id.setFont(new Font("맑은 고딕",Font.BOLD,20));
        Label_id.setBounds(50,120,100,30);
        add(Label_id);
        Label_pw = new JLabel("비밀번호");
        Label_pw.setFont(new Font("맑은 고딕",Font.BOLD,20));
        Label_pw.setBounds(50,170,100,30);
        add(Label_pw);
        Label_pw_check = new JLabel("비밀번호 확인");
        Label_pw_check.setFont(new Font("맑은 고딕",Font.BOLD,20));
        Label_pw_check.setBounds(50,220,150,30);
        add(Label_pw_check);
        Label_name = new JLabel("이름");
        Label_name.setFont(new Font("맑은 고딕",Font.BOLD,20));
        Label_name.setBounds(50,270,100,30);
        add(Label_name);
        Label_phone = new JLabel("전화번호");
        Label_phone.setFont(new Font("맑은 고딕",Font.BOLD,20));
        Label_phone.setBounds(50,320,100,30);
        add(Label_phone);
        Text_id = new JTextField();
        Text_id.setBounds(200,120,200,30);
        add(Text_id);
        Text_pw = new JPasswordField();
        Text_pw.setBounds(200,170,200,30);
        add(Text_pw);
        Text_pw_check = new JPasswordField();
        Text_pw_check.setBounds(200,220,200,30);
        add(Text_pw_check);
        Text_name = new JTextField();
        Text_name.setBounds(200,270,200,30);
        add(Text_name);
        Text_phone = new JTextField();
        Text_phone.setBounds(200,320,200,30);
        add(Text_phone);
        Button_join = new JButton("회원가입");
        Button_join.setBounds(200,370,150,30);
        Button_join.addActionListener(this);
        add(Button_join);
        Button_back = new JButton("뒤로가기");
        Button_back.setBounds(200,500,150,30);
        Button_back.addActionListener(this);
        add(Button_back);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==Button_join){
            if(Text_id.getText().equals("")||Text_pw.getText().equals("")||Text_pw_check.getText().equals("")||Text_name.getText().equals("")||Text_phone.getText().equals("")){
                JOptionPane.showMessageDialog(null,"빈칸을 모두 채워주세요.");
            }
            else if(!Text_pw.getText().equals(Text_pw_check.getText())){
                JOptionPane.showMessageDialog(null,"비밀번호가 일치하지 않습니다.");
            }
            else{
                Main.sendAccount(new Account(Text_id.getText(),Text_pw.getText(),Text_name.getText(),Text_phone.getText(),0,0,"Join"));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                if(Main.account.get_purpose().equals("Join_success")){
                    JOptionPane.showMessageDialog(null,"회원가입이 완료되었습니다.");
                    MainFrame.display("Main");
                    this.setVisible(false);
                }
                else if(Main.account.get_purpose().equals("Join_fail")){
                    JOptionPane.showMessageDialog(null,"회원가입 실패.");
                }

            }
        }
        else if(e.getSource()==Button_back){
            this.setVisible(false);
            MainFrame.display("Main");
        }
    }
}
