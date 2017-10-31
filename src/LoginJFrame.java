import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginJFrame extends JFrame{
    private Container cp;
    private JLabel jlbID = new JLabel("ID");
    private JLabel jlbPW = new JLabel("PW");
    private JTextField jtfID = new JTextField();
    private JTextField jtfPW = new JPasswordField();
    private JButton jbtnLogin = new JButton("Login");
    private JButton jbtnExit = new JButton("Exit");
    private int screenW=Toolkit.getDefaultToolkit().getScreenSize().width;
    private int screenH=Toolkit.getDefaultToolkit().getScreenSize().height;
    public LoginJFrame(){
        initcomp();
    }
    public void initcomp(){
        setBounds(screenW/2-125,screenH/2-75,250,150);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        cp =this.getContentPane();
        cp.setLayout(new GridLayout(3,2,3,3));
        cp.add(jlbID);
        cp.add(jtfID);
        cp.add(jlbPW);
        cp.add(jtfPW);
        cp.add(jbtnLogin);
        cp.add(jbtnExit);
        jlbID.setHorizontalAlignment(JLabel.RIGHT);
        jlbPW.setHorizontalAlignment(JLabel.RIGHT);
        jtfID.setText("h304");
        jtfPW.setText("23323456");
        jbtnExit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        jbtnLogin.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {

                if(jtfID.getText().equals("h304")&&jtfPW.getText().equals("23323456")){
                    System.out.println("登入成功");
                    MainJFrame mf = new  MainJFrame(LoginJFrame.this);
                    //jtfID.setText("");
                    //jtfPW.setText("");
                    mf.setVisible(true);
                    LoginJFrame.this.setVisible((false));
                }else{
                    JOptionPane.showMessageDialog(null, "帳密錯誤", "提示訊息", JOptionPane.INFORMATION_MESSAGE );
                }

            }
        });
    }

}