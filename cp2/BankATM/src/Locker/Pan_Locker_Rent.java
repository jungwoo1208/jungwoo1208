package Locker;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
public class Pan_Locker_Rent extends JPanel implements ActionListener{
    private JLabel Label_title;
    private JButton Button_back;
    private JPanel panel;
    private static JButton[] Button_Locker = new JButton[36];
    private static locker []Locker;
    Main MainFrame;
    Pan_Rent pan_rent;
    public Pan_Locker_Rent(Main parent,locker []Locker,Pan_Rent pan_rent){
        MainFrame = parent;
        this.Locker = Locker;
        this.pan_rent = pan_rent;
        init();
    }
    private void init(){
        setLayout(null);
        setBounds(0,0,450,600);
        Label_title = new JLabel("락커 대여");
        Label_title.setFont(new Font("맑은 고딕",Font.BOLD,30));
        Label_title.setBounds(150,50,200,30);
        add(Label_title);
        panel = new JPanel();
        panel.setLayout(new GridLayout(6,6));
        panel.setBounds(50,120,350,350);
        for(int i=0;i<36;i++){
            Button_Locker[i] = new JButton(Integer.toString(i+1));
            Button_Locker[i].addActionListener(this);
            if(Locker[i].getIsRented()){
                Button_Locker[i].setBackground(Color.red);
            }
            else{
                Button_Locker[i].setBackground(Color.green);
            }
            panel.add(Button_Locker[i]);
        }
        add(panel);
        Button_back = new JButton("뒤로가기");
        Button_back.setBounds(200,500,150,30);
        Button_back.addActionListener(this);
        add(Button_back);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==Button_back){
            this.setVisible(false);
            MainFrame.display("Main");
        }for(int i=0;i<36;i++){
            if(e.getSource()==Button_Locker[i]){
                update();
                if(Main.account.get_locker_number()!=0){
                    JOptionPane.showMessageDialog(null,"이미 락커를 대여하셨습니다.");
                    return;
                }
                if(Locker[i].getIsRented()){
                    JOptionPane.showMessageDialog(null,"이미 대여된 락커입니다.");
                }
                else{
                    this.setVisible(false);
                    MainFrame.display("pan_rent",i);
                }
            }
        }
    }
    public static void update(){
        for(int i=0;i<36;i++){
            if(Main.Locker[i].getIsRented()){
                Button_Locker[i].setBackground(Color.red);
            }
            else{
                Button_Locker[i].setBackground(Color.green);
            }
        }
    }
}
