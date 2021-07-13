package ui;

import service.ConsignorService;
import service.LogisticService;
import util.MailUtil;
import util.ShowMessageUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Register implements ActionListener {
    private LogisticService logisticService;
    private ConsignorService consignorService;

    private JFrame jf = new JFrame("物流信息管理系统");
    private Container con = jf.getContentPane();// 获得面板

    private Toolkit toolkit = Toolkit.getDefaultToolkit();
    private Dimension sc = toolkit.getScreenSize();// 获得屏幕尺寸
    private JLabel title = null;
    private JLabel title1 = new JLabel("物流公司注册");
    private JLabel title2 = new JLabel("委托公司注册");
    private JLabel id = new JLabel("账   号");
    private JLabel name = new JLabel("公司名称");
    private JLabel address = new JLabel("公司地址");
    private JLabel tel = new JLabel("联系方式");
    private JLabel pass = new JLabel("密   码");
    private JLabel pass2 = new JLabel("确认密码");
    private JLabel mail = new JLabel("邮箱");
    private JTextField mailText = new JTextField();
    private JButton btnSend = new JButton("发送验证码");
    private JLabel code1 = new JLabel("验证码：");
    private JTextField textCode = new JTextField();
    private JLabel code2 = new JLabel();
    private JTextField textId = new JTextField();
    private JTextField textName = new JTextField();
    private JTextField textAddress = new JTextField();
    private JTextField textTel = new JTextField();
    private JPasswordField textPs = new JPasswordField(); // 密码框
    private JPasswordField textPs2 = new JPasswordField();
    private String realCode;
    // 按钮
    private JButton registerBtn = new JButton("确定");
    private JButton backLoginBtn = new JButton("返回");

    // 字体，样式（粗体，斜体），大小
    private Font font = new Font("楷体", Font.BOLD, 28);
    private Font font1 = new Font("楷体", Font.PLAIN, 20);
    private Font font2 = new Font("楷体", Font.PLAIN, 18);

    public Register(int userType) {
        logisticService = LogisticService.getInstance();
        consignorService = ConsignorService.getInstance();
        con.setLayout(null);
        jf.setIconImage(toolkit.getImage("resources/icon.jpg"));
        jf.setSize(480, 700);
        jf.setLocation((sc.width - 480) / 2, (sc.height - 700) / 2);
        jf.setResizable(false);// 窗口大小不可变
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        con.setVisible(true);

        if(userType == 0){
            title = title1;
        }else if(userType == 1){
            title = title2;
        }
        title.setBounds(160, 40, 190, 30);
        title.setFont(font);
        title.setForeground(Color.black);

        id.setBounds(110, 140, 95, 30);
        id.setFont(font1);
        id.setForeground(Color.black);

        name.setBounds(110, 190, 95, 30);
        name.setFont(font1);
        name.setForeground(Color.black);

        address.setBounds(110, 240, 95, 30);
        address.setFont(font1);
        address.setForeground(Color.black);

        tel.setBounds(110, 290, 95, 30);
        tel.setFont(font1);
        tel.setForeground(Color.black);

        pass.setBounds(110, 340, 95, 30);// 密码的位置大小
        pass.setForeground(Color.black);
        pass.setFont(font1);

        pass2.setBounds(110, 390, 95, 30);
        pass2.setForeground(Color.black);
        pass2.setFont(font1);

        textId.setBounds(200, 143, 170, 25);
        textId.setFont(font1);
        textId.setBorder(null);// 边框

        textName.setBounds(200, 193, 170, 25);
        textName.setFont(font1);
        textName.setBorder(null);// 边框

        textAddress.setBounds(200, 243, 170, 25);
        textAddress.setFont(font1);
        textAddress.setBorder(null);// 边框

        textTel.setBounds(200, 293, 170, 25);
        textTel.setFont(font1);
        textTel.setBorder(null);// 边框

        textPs.setBounds(200, 343, 170, 25);
        textPs.setFont(font1);
        textPs.setBorder(null);
        // 可以将密码显示为* ；默认为· 但默认又对其设置了字体时会乱码
        textPs.setEchoChar('*');

        textPs2.setBounds(200, 393, 170, 25);
        textPs2.setFont(font1);
        textPs2.setBorder(null);
        textPs2.setEchoChar('*');

        mail.setBounds(110,443,80,30);
        mail.setFont(font1);
        mail.setForeground(Color.black);

        mailText.setBounds(200,443,80,30);
        mailText.setFont(font1);
        mailText.setBorder(null);
        mailText.setForeground(Color.black);

        btnSend.setBounds(290,443,150,25);
        btnSend.setFont(font2);
        btnSend.addActionListener(this);

        code1.setBounds(110, 493, 80, 25);
        code1.setFont(font1);
        code1.setForeground(Color.black);

        textCode.setBounds(200, 493, 80, 25);
        textCode.setBorder(null);
        textCode.setFont(font1);

        code2.setBounds(300, 493, 70, 25);
        code2.setFont(font1);
        code2.setForeground(Color.black);

        registerBtn.setBounds(140, 540, 90, 25);
        registerBtn.setFont(font2);
        registerBtn.addActionListener(this);

        backLoginBtn.setBounds(250, 540, 90, 25);
        backLoginBtn.setFont(font2);
        backLoginBtn.addActionListener(this);
        ImageIcon bgim = new ImageIcon("resources/background.jpg");// 背景图案
        JLabel bg = new JLabel(bgim);
        Container laycon = jf.getLayeredPane();
        bg.setSize(jf.getSize().width, jf.getSize().height);
        bgim.setImage(bgim.getImage().getScaledInstance(bg.getSize().width,
                bg.getSize().height, Image.SCALE_DEFAULT));
        laycon.add(bg, new Integer(Integer.MIN_VALUE));

        con.add(title);
        con.add(id);
        con.add(name);
        con.add(address);
        con.add(tel);
        con.add(pass);
        con.add(pass2);
        con.add(textId);
        con.add(textName);
        con.add(textAddress);
        con.add(textTel);
        con.add(textPs);
        con.add(textPs2);
        con.add(code1);
        con.add(textCode);
        con.add(code2);
        con.add(mail);
        con.add(mailText);
        con.add(btnSend);
        con.add(registerBtn);
        con.add(backLoginBtn);
        con.add(bg);
    }

    private String getCode() {
        StringBuilder codesb = new StringBuilder();
        // 以当前系统毫秒时间为种子，new一个随机数类Random
        Random rand = new Random(System.currentTimeMillis());

        // 随机产生四个      0-9数字
        for (int i = 0; i < 4; i++) {
            int temp = rand.nextInt();
            if (temp < 0) {
                temp = -temp;
            }
            codesb.append(temp % 10);
        }
        // 保存验证码在系统中
        realCode = codesb.toString();
        return realCode;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.registerBtn) {
            String pswd = new String(textPs.getPassword());
            String pswd2 = new String(textPs2.getPassword());
            String id = textId.getText();
            if (id.equals("") || pswd.equals("") || pswd2.equals("")) {
                ShowMessageUtil.Message("账号、密码不能为空！");
            } else {
                if(textCode.getText().equals(realCode)){
                    if (pswd.equals(pswd2)) {
                        Integer realId = -1;
                        try {
                            // String型id转int型id，不可转（非数字字符）时会抛异常
                            realId = Integer.parseInt(id);
                            boolean res = false;
                            if(title.equals(title1)){
                                res = logisticService.addLog(realId, pswd, this.textName.getText(),
                                        this.textAddress.getText(),this.textTel.getText() );
                            }else{
                                res = consignorService.addCon(realId,pswd,this.textName.getText(),
                                        this.textAddress.getText(),this.textTel.getText() );
                            }

                            if (res) {
                                // 注册成功则跳转到登录界面
                                new Login();
                                this.jf.dispose();
                            }
                        } catch (Exception ex) {
                            // 故需捕获之
                            ShowMessageUtil.Message("请输入有效的账号！");
                        }
                    } else {
                        ShowMessageUtil.Message("两次输入的密码不同！");
                    }
                }else{
                    ShowMessageUtil.Message("验证码不正确");
                }

            }
        }
        else if(e.getSource() == this.btnSend){
            if(mailText.getText() == null){
                ShowMessageUtil.Message("邮箱未填写！");
            }
            else{
                if(MailUtil.sendMailCode(mailText.getText(),getCode())){
                    ShowMessageUtil.Message("发送成功！\n请注意接收");
                }else{
                    ShowMessageUtil.Message("发送失败，请稍后重试！");
                }
            }
        }
        else if (e.getSource() == this.backLoginBtn) {
            new Login();
            this.jf.dispose();
        }
    }
}