import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Random;

public class MainJFrame extends JFrame {
    private int screenW=Toolkit.getDefaultToolkit().getScreenSize().width;
    private int screenH=Toolkit.getDefaultToolkit().getScreenSize().height;
    private JDesktopPane jdp = new JDesktopPane();
    private JMenuBar jmb = new JMenuBar();
    private JInternalFrame jifL = new JInternalFrame();
    private JInternalFrame jifK = new JInternalFrame();
    private Container cpL;
    private Container cpK;
    private JPanel janL = new JPanel(new GridLayout(1,6,5,0));
    private JPanel janB = new JPanel(new GridLayout(1,2,5,5));
    private JPanel janK = new JPanel(new GridLayout(3,3,3,3));
    private JPanel janF = new JPanel(new GridLayout(2,3,5,5));
    private JMenu jmFile = new JMenu("File");
    private JMenu jmSet = new JMenu("Set");
    private JMenu jmTool = new JMenu("Tool");

    private JMenu jmAbout = new JMenu("About");
    private JMenuItem jmiExit = new JMenuItem("Exit");
    private JMenuItem jmiLotto = new JMenuItem("Lotto");
    private JMenuItem jmiKeyboard = new JMenuItem("Keyboard");
    private JMenuItem jmiSignout = new JMenuItem("Sign out");
    private JMenuItem jmiFont = new JMenuItem("Font");
    private JMenuItem jmiHelp = new JMenuItem("Help");
    private JMenuItem jmiOpen = new JMenuItem("Open...");


    private JButton jbtnR = new JButton("Generate");
    private JButton jbtnC = new JButton("Close");
    private JLabel jlbs[] = new JLabel[6];
    private JButton jbtnsK[] = new JButton[10];
    private JTextField jtfK = new JTextField();
    private   String tmp = "";
    private int data[] = new int[6];
    private String[] option={"PLAIA","BOLD","ITALIC","BOLD+ITALIC"};
    private JComboBox jcbo = new JComboBox(option);
    private Random rnd = new Random(System.currentTimeMillis());
    private JLabel jlbFamily = new JLabel("Family");
    private JLabel jlbStyle = new JLabel("Style");
    private JLabel jlbSize = new JLabel("Size");
    private JTextField jtfFamily = new JTextField();
    private JTextField jtfSize = new JTextField();

    private JInternalFrame jifFile = new JInternalFrame();
    private Container cpFile = new Container();
    private JMenuBar jmbFile = new JMenuBar();
    private JMenu jmFileData = new JMenu("Data");
    private JMenuItem jmiFDLoad = new JMenuItem("Load");
    private JMenuItem jmiFDNew = new JMenuItem("New");
    private JMenuItem jmiFDClose = new JMenuItem("Close");
    private JTextArea jtaFD = new JTextArea();
    private JScrollPane jspFD = new JScrollPane(jtaFD);
    private JFileChooser jfc = new JFileChooser();

    Boolean checkjifL = true;
    Boolean checkjifK = true;


    public LoginJFrame lgjf = new LoginJFrame();
    public MainJFrame(LoginJFrame lgjf1){
        lgjf=lgjf1;
        initComp();
    }
    public void initComp(){
        setBounds(screenW/2-250,screenH/2-225,500,450);
        jifL.setBounds(0,0,200,100);
        jifK.setBounds(0,0,250,350);
        jifFile.setBounds(0,0,300,350);

        cpL=jifL.getContentPane();
        cpK=jifK.getContentPane();
        cpFile=jifFile.getContentPane();
        cpL.setLayout(new BorderLayout(3,3));
        cpL.add(janL,BorderLayout.CENTER);
        cpL.add(janB,BorderLayout.SOUTH);
        janB.add(jbtnR);
        janB.add(jbtnC);
         janF.add(jlbFamily);
         janF.add(jlbStyle);
         janF.add(jlbSize);
         janF.add(jtfFamily);
         janF.add(jcbo);
         janF.add(jtfSize);
        jtfK.setEditable(false);
        for(int i=0;i<6;i++) {
            jlbs[i]=new JLabel();
            janL.add(jlbs[i]);
            jlbs[i].setHorizontalAlignment((int) CENTER_ALIGNMENT);
        }
        LottoGenerate();

        for(int i=0;i<9;i++){
            jbtnsK[i]=new JButton(Integer.toString(i+1));

            String x =Integer.toString(i+1);
            jbtnsK[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    tmp=tmp+x;
                    jtfK.setText(tmp);
                }
            });
        }
        Random ranK = new Random();
        int x;
        int dataK[]=new int[10];
        for(int i=0;i<9;i++){
            x=ranK.nextInt(9);
            dataK[i]=x;
            janK.add(jbtnsK[x]);
            for(int j=0;j<i;j++){
                if(dataK[j]==dataK[i]){
                    i--;
                }
            }
        }
        cpK.add(jtfK,BorderLayout.NORTH);
        cpK.add(janK,BorderLayout.CENTER);

        this.setJMenuBar(jmb);

        this.setContentPane(jdp);
        jdp.add(jifL);
        jdp.add(jifK);
        jdp.add(jifFile);
        jmb.add(jmFile);
        jmb.add(jmSet);
        jmb.add(jmTool);
        jmb.add(jmAbout);

        jmFile.add(jmiOpen);
        jmFile.add(jmiExit);
        jmFile.add(jmiSignout);


        jmTool.add(jmiLotto);
        jmTool.add(jmiKeyboard);

        jmSet.add(jmiFont);
        jmAbout.add(jmiHelp);

        jifFile.setJMenuBar(jmbFile);
        jmbFile.add(jmFileData);
        jmFileData.add(jmiFDLoad);
        jmFileData.add(jmiFDNew);
        jmFileData.add(jmiFDClose);
        jifFile.add(jtaFD);

        jmiExit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        jbtnC.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                checkjifL=true;
                jifL.setVisible(false);
            }
        });
        jbtnR.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                LottoGenerate();
            }
        });
        jmiSignout.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                lgjf.setVisible(true);
                dispose();
            }
        });
        jmiExit.setAccelerator(KeyStroke.getKeyStroke('X',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        jmiSignout.setAccelerator(KeyStroke.getKeyStroke('C',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                lgjf.setVisible(true);
            }
        });

        jmiLotto.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkjifL==true){
                    checkjifL=false;
                    jifL.setVisible(true);
                }else{
                    checkjifL=true;
                    jifL.setVisible(false);
                }
            }
        });

        jmiKeyboard.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(checkjifK==true){
                    checkjifK=false;
                    jifK.setVisible(true);
                }else{
                    jifK.setVisible(false);
                    jtfK.setText("");
                    tmp="";
                    checkjifK=true;
                }
            }
        });
        jmiFont.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(MainJFrame.this,janF,"Font Setting",JOptionPane.OK_CANCEL_OPTION);
                int fontStyle = 0;
                switch (jcbo.getSelectedIndex()){
                    case 0:
                        fontStyle=Font.PLAIN;
                        break;
                    case 1:
                        fontStyle=Font.BOLD;
                        break;
                    case 2:
                        fontStyle=Font.ITALIC;
                        break;
                    case 3:
                        fontStyle=Font.BOLD+ Font.ITALIC;
                        break;
                }
                if(result==JOptionPane.OK_OPTION){
                    UIManager.put("Menu.font",new Font(jtfFamily.getText(),fontStyle,Integer.parseInt(jtfSize.getText())));
                }
            }
        });
        jmiOpen.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            jifFile.setVisible(true);
            }
        });
        jmiFDClose.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                jifFile.setVisible(false);
            }
        });
        jmiFDLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    try {
                        File inFile = jfc.getSelectedFile();
                        BufferedReader br = new BufferedReader(new FileReader((inFile)));
                        String str = "";
                        while ((str = br.readLine()) != null) {
                            jtaFD.append(str + "\n");
                        }
                    } catch (Exception ioe) {
                        JOptionPane.showMessageDialog(null, "" + ioe.toString());
                    }
                }
            }
        });
    }
    private void LottoGenerate(){
        for(int i=0;i<6;i++){
            data[i]=rnd.nextInt(42)+1;
            jlbs[i].setText(Integer.toString(data[i]));
            for(int j=0;j<i;j++){
                if(data[j]==data[i]){
                    i--;
                }
            }
        }
    }
}