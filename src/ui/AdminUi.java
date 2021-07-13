package ui;

import dao.ConsignorDaoImpl;
import dao.LogisticsDaoImpl;
import model.*;
import service.*;
import util.MailUtil;
import util.ShowMessageUtil;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class AdminUi extends JFrame implements ActionListener, ChangeListener {
    private static final long serialVersionUID = 1L;

    public static final int CARD0 = 0;
    public static final int CARD1 = 1;
    public static final int CARD2 = 2;
    public static final int CARD3 = 3;
    public static final int CARD4 = 4;
    public static final int CARD5 = 5;



    private LogisticsDaoImpl logisticsDao;
    private ConsignorService consignorService;
    private ConsignorDaoImpl consignorDao;
    private LogisticService logisticService;

    private int logId;
    private int conId;
    private int tId;

    private Toolkit toolkit = Toolkit.getDefaultToolkit();
    private Dimension sc = toolkit.getScreenSize();
    private JLabel card0 = new JLabel();
    private JPanel card1 = new JPanel();
    private JPanel card2 = new JPanel();
    private JLabel card3 = new JLabel();
    private JLabel card4 = new JLabel();

    private String[] ss = {"物流公司","发货公司"};
    private JComboBox typeBox1 = new JComboBox(ss);
    private JLabel id = new JLabel("物流账号");
    private JTextField textId = new JTextField();
    private JButton btnSch = new JButton("查找");
    private JLabel info = new JLabel("您可以做如下操作：");
    private JButton btnDel = new JButton("删  除");

    private JLabel cid = new JLabel("发货账号");
    private JTextField ctextId = new JTextField();
    private JButton btnSchCon = new JButton("查找");
    private JButton btnDelCon = new JButton("删  除");

    private JLabel type = new JLabel("类型");
    private String[] s = {"物流公司","发货公司"};
    private JComboBox typeBox = new JComboBox(s);
    private JLabel newId = new JLabel("帐号");
    private JLabel newName = new JLabel("名称");
    private JLabel newAddress = new JLabel("地址");
    private JLabel newTel = new JLabel("电话");
    private JLabel newPassword = new JLabel("密码");
    private JLabel newPassword2 = new JLabel("确认密码");
    private JLabel mail = new JLabel("邮箱");
    private JTextField mailText = new JTextField();
    private JButton btnSend = new JButton("发送验证码");
    private JLabel code1 = new JLabel("验证码：");
    private JTextField textCode = new JTextField();
    private JLabel code2 = new JLabel();
    private JTextField newIdText = new JTextField();
    private JTextField newNameText = new JTextField();
    private JTextField newAddressText = new JTextField();
    private JTextField newTelText = new JTextField();
    private JPasswordField newPasswordText = new JPasswordField();
    private JPasswordField newPasswordText2 = new JPasswordField();
    private JButton btnAddOrUpdata = new JButton("确定");




    private String[] TableHead = {"账号", "名称", "地址", "电话","密码"};
    private String table = new String("<html><style>"
            + "table{margin-top:0px;padding-left:80px;}"
            + "td{width:170px;}</style>"
            + "<table><tr><td>本公司账号</td><td>名称</td><td>地址</td><td>电话</td><td>密码</td></tr>");

    private Font font = new Font("楷体", Font.PLAIN, 20);
    private Font font1 = new Font("楷体", Font.PLAIN, 16);

    private JTabbedPane tabbedPane;
    private JButton reFresh = new JButton("刷新");
    private JButton btnexit = new JButton("退出");//
    private JMenuBar menuBar = new JMenuBar();
    private String realCode;

    public AdminUi(int defaultCard) {
        init();
        initPane();
        tabbedPane.addTab("欢迎您！", card0);// 后面将card0设为不可用
        tabbedPane.addTab("1.所有物流公司", card1);
        tabbedPane.addTab("2.所有发货公司", card2);
        tabbedPane.addTab("3.管理公司", card3);
        tabbedPane.addTab("4.添加公司", card4);
        tabbedPane.setSelectedIndex(defaultCard);
        tabbedPane.setEnabledAt(0, false);

        addListener();
    }
    private void initCard1() {
        card1.removeAll();
        JTable LogTable = new JTable(logisticService.queryAllLogs(), TableHead);
        LogTable.setFont(font);
        LogTable.setPreferredSize(new Dimension(720, 440));
        LogTable.getTableHeader().setPreferredSize(new Dimension(0, 30));
        LogTable.getTableHeader().setFont(font1);
        LogTable.setRowHeight(30);
        LogTable.setEnabled(false); // 设置为不开操作
        JScrollPane scroll = new JScrollPane(LogTable);
        scroll.setPreferredSize(new Dimension(900, 720));
        card1.add(scroll);
        card1.setFont(font);
    }
    private void initCard2() {
        card2.removeAll();
        JTable conTable = new JTable(consignorService.queryAllCons(), TableHead);
        conTable.setFont(font);
        conTable.setPreferredSize(new Dimension(900, 720));
        conTable.getTableHeader().setPreferredSize(new Dimension(0, 30));
        conTable.getTableHeader().setFont(font1);
        conTable.setRowHeight(30);
        conTable.setEnabled(false); // 设置为不开操作
        JScrollPane scroll = new JScrollPane(conTable);
        scroll.setPreferredSize(new Dimension(900, 720));
        card2.setFont(font);
        card2.add(scroll);

    }
    private void initCard3() {
        typeBox1.setBounds(100, 80, 150, 30);
        textId.setBounds(270, 80, 150, 30);
        btnSch.setBounds(450, 80, 100, 30);

        info.setBounds(100, 150, 200, 30);

        btnDel.setBounds(430,150,120,30);
        card3.setFont(font);
        typeBox1.setFont(font);
        textId.setFont(font);
        btnSch.setFont(font);
        info.setFont(font);
        btnDel.setFont(font);
        card3.add(textId);
        card3.add(typeBox1);
        card3.add(info);
        card3.add(btnDel);
        card3.add(btnSch);

    }
    private void initCard4() {
        type.setBounds(120,50,80,30);
        typeBox.setBounds(200,50,200,30);

        newId.setBounds(120, 100, 80, 30);
        newIdText.setBounds(200, 100, 200, 30);

        newName.setBounds(120, 150, 80, 30);
        newNameText.setBounds(200, 150, 200, 30);

        newAddress.setBounds(120, 200, 80, 30);
        newAddressText.setBounds(200, 200, 200, 30);

        newTel.setBounds(120, 250, 80, 30);
        newTelText.setBounds(200, 250, 200, 30);

        newPassword.setBounds(120,300,80,30);
        newPasswordText.setBounds(200,300,200,30);
        newPasswordText.setEchoChar('*');
        newPassword2.setBounds(120,350,80,30);
        newPasswordText2.setBounds(200,350,200,30);
        newPasswordText2.setEchoChar('*');

        mail.setBounds(120,400,80,30);
        mail.setForeground(Color.black);

        mailText.setBounds(200,400,150,30);
        mailText.setBorder(null);
        mailText.setForeground(Color.black);

        btnSend.setBounds(360,400,200,25);
        btnSend.addActionListener(this);

        code1.setBounds(120, 450, 80, 25);
        code1.setForeground(Color.black);

        textCode.setBounds(200, 450, 80, 25);
        textCode.setBorder(null);

        code2.setBounds(300, 450, 70, 25);
        code2.setFont(font1);
        code2.setForeground(Color.black);

        btnAddOrUpdata.setBounds(200, 500, 90, 30);


        type.setFont(font);
        typeBox.setFont(font);
        newId.setFont(font);
        newIdText.setFont(font);
        newName.setFont(font);
        newNameText.setFont(font);
        newAddress.setFont(font);
        newAddressText.setFont(font);
        newTel.setFont(font);
        newTelText.setFont(font);
        newPassword.setFont(font);
        newPasswordText.setFont(font);
        newPassword2.setFont(font);
        newPasswordText2.setFont(font);
        mail.setFont(font);
        mailText.setFont(font);
        btnSend.setFont(font);
        code1.setFont(font);
        textCode.setFont(font);
        btnAddOrUpdata.setFont(font);
        card4.add(mail);
        card4.add(mailText);
        card4.add(btnSend);
        card4.add(code1);
        card4.add(textCode);
        card4.add(newId);
        card4.add(newIdText);
        card4.add(newNameText);
        card4.add(newName);
        card4.add(newAddress);
        card4.add(newAddressText);
        card4.add(newTel);
        card4.add(newTelText);
        card4.add(newPassword);
        card4.add(newPasswordText);
        card4.add(newPassword2);
        card4.add(newPasswordText2);
        card4.add(typeBox);
        card4.add(type);
        card4.add(btnAddOrUpdata);
    }
    private void initPane() {
        setTitle("物流信息管理系统--管理员");
        setResizable(false);
        setBounds((sc.width - 1100) / 2, (sc.height - 720) / 2, 1100, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(toolkit.getImage("resources/icon.jpg"));

        tabbedPane = new JTabbedPane(SwingConstants.LEFT);// 点击栏位置
        tabbedPane.setFont(font);// 左栏字体，字号
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        tabbedPane.addChangeListener(this);
        tabbedPane.setBackground(new Color(	193, 255, 193));

        reFresh.addActionListener(this);
        btnexit.addActionListener(this);
        this.setJMenuBar(menuBar);
        menuBar.add(reFresh);
        menuBar.add(btnexit);

        getContentPane().add(tabbedPane);
    }
    private void init() {
        conId = -1;
        tId = -1;
        consignorDao = ConsignorDaoImpl.getInstance();
        consignorService = ConsignorService.getInstance();
        logisticService = LogisticService.getInstance();
        logisticsDao = LogisticsDaoImpl.getInstance();
    }
    private void addListener() {
        btnDel.addActionListener(this);
        btnSch.addActionListener(this);
        btnSchCon.addActionListener(this);
        btnDelCon.addActionListener(this);
        btnAddOrUpdata.addActionListener(this);
    }

    public static void AdminMainUi(int defaultCard) {
        AdminUi jTabbedPaneTest = new AdminUi(defaultCard);
        jTabbedPaneTest.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnSch) {
            String id = textId.getText();
            if (id.equals("")) {
                ShowMessageUtil.Message("您还没有输入账号！");
            }
            else {
                int realId = -1;
                try {
                    realId = Integer.parseInt(id);
                } catch (NumberFormatException ee) {
                    ShowMessageUtil.Message("请输入正确的账号！");
                    return;
                }
                if(typeBox1.getSelectedIndex() == 0){
                    Logistics logistics = logisticsDao.queryLogistics(realId,null);
                    if (logistics == null)
                        ShowMessageUtil.Message("账号不存在!");
                    else {
                        String str = new String(table);
                        str += "<tr><td>" + logistics.getId() + "</td><td>"
                                + logistics.getName() + "</td><td>" + logistics.getAddress()
                                + "</td><td>" + logistics.getTel()+"</td><td>"+logistics.getPassword()
                                + "</td></tr></table></html>";

                        card3.setText(str);
                        logId = realId;
                    }
                }else{
                    Consignor consignor = consignorDao.queryConsignor(realId,null);
                    if (consignor == null || consignor.getId() <= 0){
                        ShowMessageUtil.Message("公司不存在!");
                    } else {
                        String str = new String(table);
                        str += "<tr><td>" + consignor.getId() + "</td><td>"
                                + consignor.getName() + "</td><td>" + consignor.getAddress()
                                + "</td><td>" + consignor.getTel()+"</td><td>"+consignor.getPassword()
                                + "</td></tr></table></html>";
                        card3.setText(str);
                        conId = realId;
                    }
                }

            }
        }
        else if (e.getSource() == this.btnDel) {
            if(typeBox1.getSelectedIndex() == 0){
                if (logId <= 0) {
                    ShowMessageUtil.Message("请先查找一位用户！");
                }
                else if (logisticsDao.deleteLog(logId)) {
                    ShowMessageUtil.Message("已删除该公司！");
                    logId = 0;
                } else {
                    ShowMessageUtil.Message("删除失败~");
                }
            }else{
                if (conId <= 0) {
                    ShowMessageUtil.Message("请先查找一位用户！");
                }
                else if (this.conId <= 0) {
                    ShowMessageUtil.Message("请先查找！");
                } else {
                    if (consignorDao.deleteConsignor(conId)) {
                        ShowMessageUtil.Message("成功删除该公司！");
                        conId = 0;
                    } else{
                        ShowMessageUtil.Message("删除失败~");
                    }
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
        else if (e.getSource() == this.btnAddOrUpdata) {
            Integer realId = -1;
            try {
                realId = Integer.parseInt(newIdText.getText());
            } catch (NumberFormatException ee) {
                ShowMessageUtil.Message("编号不正确!");
                return;
            }
            int type = typeBox.getSelectedIndex();
            String newName = newNameText.getText();
            String newAddress = newAddressText.getText();
            String newTel = newTelText.getText();
            String newPassword = new String(newPasswordText.getPassword());
            String newPassword2 = new String(newPasswordText2.getPassword());
            if(!textCode.getText().equals(realCode)){
                ShowMessageUtil.Message("验证码错误");
            }
            else if(!newPassword.equals(newPassword2)){
                ShowMessageUtil.Message("两次密码不一致");
            }
            else if (realId<=0||newName.equals("")||newAddress.equals("")||
                    newTel.equals("")||newTel.equals("") || newPassword.equals("")) {
                ShowMessageUtil.Message("信息不能为空！");
            }
            else{
               if(type == 0){
                   if(logisticsDao.queryLogistics(realId,null)!=null){
                       logisticService.udpLog(realId,newPassword,newName,newAddress,newTel);
                   }else{
                       logisticService.addLog(realId,newPassword,newName,newAddress,newTel);
                   }
               }else{
                   if(consignorDao.queryConsignor(realId,null) != null){
                       consignorService.updCon(realId,newPassword,newName,newAddress,newTel);
                   }else{
                       consignorService.addCon(realId,newPassword,newName,newAddress,newTel);
                   }
               }
            }

        }
        else if (e.getSource() == this.reFresh) {// 刷新
            AdminUi.AdminMainUi(0);
            this.dispose();
        }
        else if (e.getSource() == this.btnexit) {// 退出登录
            new App();
            this.dispose();
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        int selectIndex = tabbedPane.getSelectedIndex();
        if (selectIndex == AdminUi.CARD1) {
            initCard1();
        } else if (selectIndex == AdminUi.CARD2) {
            System.out.println(2);
            initCard2();
        } else if (selectIndex == AdminUi.CARD3) {
            initCard3();
        } else if (selectIndex == AdminUi.CARD4) {
            initCard4();
        } else {
            System.out.println(selectIndex);
        }
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
}