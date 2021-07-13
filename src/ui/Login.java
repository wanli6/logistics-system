package ui;

import model.Consignor;
import model.Logistics;
import service.LoginService;
import util.MailUtil;
import util.ShowMessageUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author 32635
 */
public class Login implements ActionListener {
    private LoginService loginService;

    private JFrame jf = new JFrame("物流信息管理系统");
    private Container con = jf.getContentPane();
    private Toolkit toolkit = Toolkit.getDefaultToolkit();
    private  Dimension sc = toolkit.getScreenSize();
    private JLabel title = new JLabel("欢迎使用");
    private JLabel name1 = new JLabel("账户：");
    private JLabel pass1 = new JLabel("密码：");
    private JTextField textName = new JTextField();
    private JPasswordField textPassword = new JPasswordField();


    // 单选
//    private JRadioButton choice1 = new JRadioButton("物流公司");
//    private JRadioButton choice2 = new JRadioButton("发货公司");
    private JLabel typeLabel = new JLabel("类型：");
    private String[] s = {"物流公司","发货公司","管理员"};
    private JComboBox<String> choices = new JComboBox<>(s);

    private JLabel code1 = new JLabel("验证码：");
    private JTextField textCode = new JTextField();
    private JLabel code2 = new JLabel();

    private String realCode;

    private JButton button1 = new JButton("注册");
    private JButton button2 = new JButton("登录");

    private ButtonGroup buttonGroup = new ButtonGroup();

    private Font font = new Font("楷体", Font.BOLD, 28);
    private Font font1 = new Font("楷体", Font.PLAIN, 20);
    private Font font2 = new Font("楷体", Font.PLAIN, 18); // 字体，样式（粗体，斜体），大小

    public Login(){
        loginService = LoginService.getInstance();
        con.setLayout(null);
        jf.setIconImage(toolkit.getImage("resources/icon.jpg"));
        jf.setSize(600,600);
        jf.setLocation((sc.width - 600) / 2, (sc.height - 600) / 2);
        jf.setResizable(false);// 窗口大小不可变
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        con.setVisible(true);

        title.setBounds(230, 60, 170, 30);
        title.setFont(font);
        title.setForeground(Color.black);

        name1.setBounds(170, 180, 60, 30);// 账号的位置大小
        name1.setFont(font1);// 字体
        name1.setForeground(Color.black);// name1字的颜色

        pass1.setBounds(170, 230, 60, 30);// 密码的位置大小
        pass1.setForeground(Color.black);
        pass1.setFont(font1);

        textName.setBounds(250, 183, 160, 25);
        textName.setBorder(null);// 边框
        textName.setFont(font1);

        textPassword.setBounds(250, 233, 160, 25);
        textPassword.setBorder(null);
        textPassword.setEchoChar('*');// 可以将密码显示为* ；默认为·
        textPassword.setFont(font1);

//        choice1.setBounds(180, 340, 140, 25);
//        choice1.setSelected(true);// 默认普通用户登录
//        choice2.setBounds(320, 340, 80, 25);
        typeLabel.setBounds(170,330,60,30);
        typeLabel.setFont(font1);
        choices.setBounds(250,330,150,25);
        choices.setFont(font2);

        code1.setBounds(170, 280, 80, 25);
        code1.setFont(font1);
        code1.setForeground(Color.black);

        textCode.setBounds(250, 282, 80, 25);
        textCode.setBorder(null);
        textCode.setFont(font1);

        code2.setBounds(345, 283, 70, 25);
        code2.setFont(font1);
        code2.setText(getCode()); // 设置验证码
        code2.setForeground(Color.black);

        button1.setBounds(190, 400, 90, 25);
        button1.setFont(font2);
        button1.addActionListener(this);

        button2.setBounds(300, 400, 90, 25);
        button2.setFont(font2);
        button2.addActionListener(this);

        ImageIcon bgImg = new ImageIcon("resources/background.jpg");
        JLabel bg = new JLabel(bgImg);
        Container laycon = jf.getLayeredPane();
        bg.setSize(jf.getSize().width, jf.getSize().height);
        bgImg.setImage(bgImg.getImage().getScaledInstance(bg.getSize().width,
                bg.getSize().height, Image.SCALE_DEFAULT));
        laycon.add(bg, new Integer(Integer.MIN_VALUE));
        con.add(title);
        con.add(name1);
        con.add(pass1);
        con.add(textName);
        con.add(textPassword);
//        con.add(choice1);
//        con.add(choice2);
//        buttonGroup.add(choice1);
//        buttonGroup.add(choice2);
        con.add(typeLabel);
        con.add(choices);
        con.add(code1);
        con.add(code2);
        con.add(textCode);
        con.add(button1);
        con.add(button2);
        con.add(bg);
        jf.setVisible(true);
    }

    private String getCode(){
        StringBuilder code = new StringBuilder();
        // 以当前系统毫秒时间为种子，new一个随机数类Random
        Random rand = new Random(System.currentTimeMillis());

        // 随机产生四个      0-9数字
        for (int i = 0; i < 4; i++) {
            int temp = rand.nextInt();
            if (temp < 0) {
                temp = -temp;
            }
            code.append(temp % 10);
        }
        // 保存验证码在系统中
        realCode = code.toString();
        return realCode;
    }

    @Override
    public void actionPerformed(ActionEvent ac) {
        if(ac.getSource() == this.button2){
            // 获得输入的 账号、密码
            String id = textName.getText();
            String pswd = new String(textPassword.getPassword());

            if (id.equals("") || pswd.equals("")) {
                ShowMessageUtil.Message("账号、密码不能为空！");
            } else{
                String code = textCode.getText();
                if(realCode.equals(code)){
                    int choice = choices.getSelectedIndex();

                    Integer realId = -1;
                    try {
                        realId = Integer.parseInt(id);
                        login(choice,realId,pswd);
                    }catch (Exception e){
                        e.printStackTrace();
                        ShowMessageUtil.Message("请输入有效的账号！");
                    }
                }else{
                    ShowMessageUtil.Message("验证码不正确！");
                }
            }
        }
        else if(choices.getSelectedIndex() == 2 && ac.getSource() == this.button1){
            ShowMessageUtil.Message("管理员账号不允许注册！");
        }
        else if(ac.getSource() == this.button1){
            new Register(choices.getSelectedIndex());
            this.jf.dispose();
        }
    }

    private void login(int choice,Integer realId, String pswd) {
        boolean res = false;
        Logistics logistics = null;
        Consignor consignor = null;

        if (choice == 0) {
            logistics = loginService.logisticsLogin(realId, pswd);
            if (logistics != null) {
                res = true;
                if(res){
                    App.getLogin().jf.dispose();
                }
                LogisticsUi.LogMainUi(realId, ConsignorUi.CARD0);
            }
        } else if(choice == 1){
            consignor = loginService.consignorLogin(realId, pswd);
            if (consignor != null) {
                res = true;
                if(res){
                    App.getLogin().jf.dispose();
                }
                ConsignorUi.ConMainUi(realId, ConsignorUi.CARD0);
            }
        }else{
            if(loginService.AdminLogin(realId,pswd)){
                res = true;
                if(res){
                    App.getLogin().jf.dispose();
                }
                AdminUi.AdminMainUi(AdminUi.CARD0);
            }
        }

        if(res){
            App.getLogin().jf.dispose();
        }if(!res){
            ShowMessageUtil.Message("账号或密码错误");
        }
    }
}