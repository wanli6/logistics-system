package ui;

import dao.ContractDaoImpl;
import dao.GoodsDaoImpl;
import dao.TrucksDaoImpl;
import model.*;
import service.*;
import util.ShowMessageUtil;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConsignorUi extends JFrame implements ActionListener, ChangeListener {
    private static final long serialVersionUID = 1L;

    public static final int CARD0 = 0;
    public static final int CARD1 = 1;
    public static final int CARD2 = 2;
    public static final int CARD3 = 3;
    public static final int CARD4 = 4;
    public static final int CARD5 = 5;
    public static final int CARD6 = 6;


    private TrucksDaoImpl trucksDao;
    private GoodsDaoImpl goodsDao;
    private ContractDaoImpl contractDao;
    private ContractService contractService;
    private ConsignorService consignorService;
    private LogisticService logisticService;
    private TruckService truckService;
    private GoodService goodService;
    private CooperateService cooperateService;
    private int logId;
    private int conId;
    private int gId;

    private Toolkit toolkit = Toolkit.getDefaultToolkit();
    private Dimension sc = toolkit.getScreenSize();
    private JLabel card0 = new JLabel();
    private JPanel card1 = new JPanel();
    private JLabel card2 = new JLabel();
    private JLabel card3 = new JLabel();
    private JPanel card4 = new JPanel();
    private JLabel card5 = new JLabel();
    private JLabel card6 = new JLabel();

    private JLabel id = new JLabel("物流账号");
    private JTextField textId = new JTextField();
    private JButton btnSchLog = new JButton("查找");
    private JLabel info = new JLabel("您可以做如下操作：");
    private JButton btnDelLog = new JButton("删除物流公司");
    private JTextField textNewStart = new JTextField();
    private JTextField textNewMoney = new JTextField();
    private JButton btnNewStart = new JButton("修改日期");
    private JButton btnNewMoney = new JButton("修改金额");

    private JLabel addInfo = new JLabel("添加一个新的合约：");
    private JLabel newLid = new JLabel("物流账号");
    private JLabel startTime = new JLabel("开始时间");
    private JLabel money = new JLabel("金额");
    private JTextField textCid = new JTextField();
    private JTextField startText = new JTextField();
    private JTextField moneyText = new JTextField();
    private JButton btnNewContract = new JButton("确定");

    private JLabel goodsId = new JLabel("货物编号");
    private JTextField textGoodsId = new JTextField();
    private JButton btnSchGoods = new JButton("查找");

    private JLabel goodsId2 = new JLabel("编号");
    private JLabel goodsName = new JLabel("名称");
    private JLabel goodsType = new JLabel("种类");
    private JLabel goodsWeight = new JLabel("重量");
    private JLabel goodsReceiveTel = new JLabel("收货电话");
    private JLabel goodsCost = new JLabel("运费");
    private JLabel goodsCid = new JLabel("发货方编号");
    private JLabel goodsTid = new JLabel("货车编号");
    private JTextField gIdText = new JTextField();
    private JTextField gNameText = new JTextField();
    private JTextField gTypeText = new JTextField();
    private JTextField gWeightText = new JTextField();
    private JTextField gReceiveTelText = new JTextField();
    private JTextField gCostText = new JTextField();
    private JTextField gCidText = new JTextField();
    private JTextField gTidText = new JTextField();
    private JButton btnAddOrUpdataTruck = new JButton("确定");

    //    private JButton btnUpdateTruck = new JButton("修 改");
    //private JButton btnAddGoodsToCon = new JButton("添加到本公司");
    private JButton btnDelGoods = new JButton("删除");



    private String[] logTableHead = {"账号", "名称", "地址", "电话"};
    private String tableLog = new String("<html><style>"
            + "table{margin-top:0px;padding-left:80px;}"
            + "td{width:170px;}</style>"
            + "<table><tr><td>本公司账号</td><td>物流公司账号</td><td>日期</td><td>金额</td></tr>");

    private String[] goodsTableHead = {"货物编号", "货物名","货物类型","重量","收货人电话","运费","发货方","所在货车"};
    private String tableGoods = new String("<html><style>"
            + "table{margin-top:0px;padding-left:80px;}"
            + "td{width:170px;}</style>"
            + "<table><tr><td>货物编号</td><td>货物名</td><td>货物类型</td><td>重量</td><td>收货人电话</td><td>运费</td><td>发货方</td><td>所在货车</td></tr>");

    private Font font = new Font("楷体", 0, 20);
    private Font font1 = new Font("楷体", 0, 16);

    private JTabbedPane tabbedPane;
    private JButton reFresh = new JButton("刷新");
    private JButton btnexit = new JButton("退出");//
    private JMenuBar menuBar = new JMenuBar();

    public ConsignorUi(int conId, int defaultCard) {
        this.conId = conId;
        init();
        initPane();
        tabbedPane.addTab("欢迎您！", card0);// 后面将card0设为不可用
        tabbedPane.addTab("1.本公司客户", card1);
        tabbedPane.addTab("2.签约管理", card2);
        tabbedPane.addTab("3.本公司货物", card4);
        tabbedPane.addTab("4.管理货物", card5);
        tabbedPane.addTab("5.添加货物", card6);
        tabbedPane.setSelectedIndex(defaultCard);
        tabbedPane.setEnabledAt(0, false);

        addListener();
    }
    private void initCard1() {
        card1.removeAll();
        JTable logTable = new JTable(logisticService.queryLogsByCid(conId), logTableHead);
        logTable.setFont(font);
        logTable.setPreferredSize(new Dimension(900, 600));
        logTable.getTableHeader().setPreferredSize(new Dimension(0, 30));
        logTable.getTableHeader().setFont(font1);
        logTable.setRowHeight(30);
        logTable.setEnabled(false); // 设置为不开操作
        JScrollPane scroll = new JScrollPane(logTable);
        scroll.setPreferredSize(new Dimension(900, 720));
        card1.add(scroll);
        card1.setFont(font);
    }
    private void initCard2() {
        id.setBounds(290, 20, 90, 30);
        textId.setBounds(380, 20, 150, 30);
        btnSchLog.setBounds(560, 20, 80, 30);

        info.setBounds(290, 70, 200, 30);
        textNewStart.setBounds(290, 120, 200, 30);
        textNewMoney.setBounds(290,170,200,30);

        btnDelLog.setBounds(510,70,120,30);
        btnNewStart.setBounds(510, 120, 120, 30);
        btnNewMoney.setBounds(510, 170, 120, 30);
        card2.setFont(font);
        id.setFont(font);
        textId.setFont(font);
        btnSchLog.setFont(font1);
        info.setFont(font);
        textNewStart.setFont(font);
        textNewMoney.setFont(font);
        btnDelLog.setFont(font1);
        btnNewMoney.setFont(font1);
        btnNewStart.setFont(font1);
        card2.add(textId);
        card2.add(id);
        card2.add(info);
        card2.add(textNewStart);
        card2.add(textNewMoney);
        card2.add(btnDelLog);
        card2.add(btnSchLog);
        card2.add(btnNewMoney);
        card2.add(btnNewStart);

        addInfo.setBounds(290,320,200,30);
        addInfo.setFont(font);
        newLid.setBounds(290, 360, 80, 30);
        startTime.setBounds(290, 400, 80, 30);
        money.setBounds(290, 440, 80, 30);
        textCid.setBounds(400, 360, 200, 30);
        startText.setBounds(400, 400, 200, 30);
        moneyText.setBounds(400, 440, 200, 30);
        btnNewContract.setBounds(450, 480, 90, 30);
        newLid.setFont(font);
        textCid.setFont(font);
        startTime.setFont(font);
        money.setFont(font);
        startText.setFont(font);
        moneyText.setFont(font);
        btnNewContract.setFont(font);
        card2.add(addInfo);
        card2.add(newLid);
        card2.add(startTime);
        card2.add(money);
        card2.add(textCid);
        card2.add(startText);
        card2.add(moneyText);
        card2.add(btnNewContract);
    }
    private void initCard3() {

    }
    private void initCard4() {
        JTable goodsTable = new JTable(goodService.queryGoodsById(conId), goodsTableHead);
        goodsTable.setFont(font);
        goodsTable.setPreferredSize(new Dimension(900, 600));
        goodsTable.getTableHeader().setPreferredSize(new Dimension(0, 30));
        goodsTable.getTableHeader().setFont(font1);
        goodsTable.setRowHeight(30);
        goodsTable.setEnabled(false); // 设置为不开操作
        JScrollPane scroll = new JScrollPane(goodsTable);
        scroll.setPreferredSize(new Dimension(900, 720));
        card4.removeAll();
        card4.add(scroll);
    }
    private void initCard5() {

        goodsId.setBounds(310, 50, 90, 30);
        textGoodsId.setBounds(400, 50, 150, 30);
        btnSchGoods.setBounds(580, 50, 80, 30);
        info.setBounds(310, 100, 200, 30);
        btnDelGoods.setBounds(530, 100, 130, 30);
        // btnAddGoodsToCon.setBounds(230,150,200,30);

        goodsId.setFont(font);
        textGoodsId.setFont(font);
        btnSchGoods.setFont(font1);
        info.setFont(font);
        btnDelGoods.setFont(font1);
        //btnAddGoodsToCon.setFont(font1);

        card5.add(info);
        card5.add(goodsId);
        card5.add(textGoodsId);
        card5.add(btnSchGoods);
        card5.setFont(font);
        card5.add(btnDelGoods);
        //card5.add(btnAddGoodsToCon);

    }
    private void initCard6() {
        goodsId2.setBounds(300, 50, 80, 30);
        gIdText.setBounds(410, 50, 200, 30);

        goodsName.setBounds(300, 100, 80, 30);
        gNameText.setBounds(410, 100, 200, 30);

        goodsType.setBounds(300, 150, 80, 30);
        gTypeText.setBounds(410, 150, 200, 30);

        goodsWeight.setBounds(300, 200, 80, 30);
        gWeightText.setBounds(410, 200, 200, 30);

        goodsReceiveTel.setBounds(300,250,80,30);
        gReceiveTelText.setBounds(410,250,200,30);

        goodsCost.setBounds(300,300,80,30);
        gCostText.setBounds(410,300,200,30);

        goodsCid.setBounds(300,350,110,30);
        gCidText.setBounds(410,350,200,30);
        gCidText.setText(String.valueOf(conId));
        gCidText.setEditable(false);

        goodsTid.setBounds(300,400,80,30);
        gTidText.setBounds(410,400,200,30);
        gTidText.setText("0");
        gTidText.setEditable(false);

        btnAddOrUpdataTruck.setBounds(430, 450, 90, 30);


        goodsId2.setFont(font);
        gIdText.setFont(font);
        goodsName.setFont(font);
        gNameText.setFont(font);
        goodsType.setFont(font);
        gTypeText.setFont(font);
        goodsWeight.setFont(font);
        gWeightText.setFont(font);
        goodsReceiveTel.setFont(font);
        gReceiveTelText.setFont(font);
        goodsCost.setFont(font);
        gCostText.setFont(font);
        goodsCid.setFont(font);
        gCidText.setFont(font);
        goodsTid.setFont(font);
        gTidText.setFont(font);
        btnAddOrUpdataTruck.setFont(font);

        card6.add(goodsId2);
        card6.add(gIdText);
        card6.add(gNameText);
        card6.add(goodsName);
        card6.add(goodsType);
        card6.add(gTypeText);
        card6.add(goodsWeight);
        card6.add(gWeightText);
        card6.add(goodsReceiveTel);
        card6.add(gReceiveTelText);
        card6.add(goodsCost);
        card6.add(gCostText);
        card6.add(goodsCid);
        card6.add(gCidText);
        card6.add(goodsTid);
        card6.add(gTidText);
        card6.add(btnAddOrUpdataTruck);
    }
    private void initPane() {
        setTitle("物流信息管理系统--发货公司端");
        setResizable(false);
        setBounds((sc.width - 1100) / 2, (sc.height - 600) / 2, 1100, 600);
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
        logId = -1;
        gId = -1;
        consignorService = ConsignorService.getInstance();
        logisticService = LogisticService.getInstance();
        truckService = TruckService.getInstance();
        contractService = ContractService.getInstance();
        contractDao = ContractDaoImpl.getInstance();
        trucksDao = TrucksDaoImpl.getInstance();
        cooperateService = CooperateService.getInstance();
        goodService = GoodService.getInstance();
        goodsDao = GoodsDaoImpl.getInstance();
    }
    private void addListener() {
        btnDelLog.addActionListener(this);
        btnNewContract.addActionListener(this);
        btnSchLog.addActionListener(this);
        btnNewStart.addActionListener(this);
        btnNewMoney.addActionListener(this);
        btnSchGoods.addActionListener(this);
        btnAddOrUpdataTruck.addActionListener(this);
        btnDelGoods.addActionListener(this);
        //btnAddGoodsToCon.addActionListener(this);
    }

    public static void ConMainUi(int id, int defaultCard) {
        ConsignorUi jTabbedPaneTest = new ConsignorUi(id, defaultCard);
        jTabbedPaneTest.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnSchLog) {
            String id = textId.getText();
            if (id.equals("")) {
                ShowMessageUtil.Message("您还没有输入账号！");
            } else {
                int realId = -1;
                try {
                    realId = Integer.parseInt(id);
                } catch (NumberFormatException ee) {
                    ShowMessageUtil.Message("请输入正确的账号！");
                    return;
                }
                Contract contract = contractDao.queryContract(realId,conId);
                if (contract == null)
                    ShowMessageUtil.Message("账号不存在!");
                else {
                    String str = new String(tableLog);
                    str += "<tr><td>" + contract.getL_id() + "</td><td>"
                            + contract.getC_id() + "</td><td>" + contract.getDate()
                            + "</td><td>" + contract.getMoney()
                            + "</td></tr></table></html>";
                    card2.setText(str);
                    logId = realId;
                }
            }
        }
        else if (e.getSource() == this.btnDelLog) {
            if (conId <= 0)
                ShowMessageUtil.Message("请先查找一个与物流公司的合约！");
            else {
                if (contractDao.deleteContract(logId,conId)) {
                    ShowMessageUtil.Message("已删除与该公司合约关系！");
                    conId = 0;
                } else {
                    ShowMessageUtil.Message("删除失败~");
                }
            }
        }
        else if (e.getSource() == this.btnNewMoney) {
            if (conId <= 0) {
                ShowMessageUtil.Message("请先查找一位用户！");
            } else {
                String moneyString = textNewMoney.getText();
                if (id.equals("")  || moneyString.equals("")) {
                    ShowMessageUtil.Message("信息未填写！");
                } else {
                    int money = Integer.parseInt(moneyString);
                    if (contractDao.updContractMoney(logId,conId, money)) {
                        ShowMessageUtil.Message("修改成功！");
                    } else{
                        ShowMessageUtil.Message("修改失败~");
                    }
                }
            }
        }
        else if (e.getSource() == this.btnNewStart) {
            if (conId <= 0) {
                ShowMessageUtil.Message("请先查找一位用户！");
            } else {
                String start = textNewStart.getText();
                if (id.equals("") || start.equals("") ) {
                    ShowMessageUtil.Message("信息未填写！");
                } else {
                    if (contractDao.updContractStart(logId,conId, start)) {
                        ShowMessageUtil.Message("修改成功！");
                    } else{
                        ShowMessageUtil.Message("修改失败~");
                    }
                }
            }
        }
        else if (e.getSource() == this.btnNewContract) {
            String id = textCid.getText();
            if (id.equals("")) {
                ShowMessageUtil.Message("账号不能为空！");
            } else {
                Integer realId = -1;
                try {
                    // String型id转int型id，不可转（非数字字符）时会抛异常
                    realId = Integer.parseInt(id);
                } catch (Exception ex) {
                    // 故需捕获之
                    ShowMessageUtil.Message("请输入有效的账号！");
                }
                contractService.addContract(realId,conId,startText.getText(),Integer.parseInt(moneyText.getText()));
            }
        }
        else if (e.getSource() == this.btnSchGoods) {
            String id = textGoodsId.getText();
            if (id.equals("")) {
                ShowMessageUtil.Message("您还没有输入编号！");
            } else {
                Integer realId = -1;
                try {
                    realId = Integer.parseInt(id);
                }catch (Exception ex){
                    ShowMessageUtil.Message("请输入有效的账号！");
                    return;
                }
                Goods goods = goodsDao.queryGoodsById(realId);
                if (goods == null){
                    ShowMessageUtil.Message("货物不存在!");
                } else {
                    String str = new String(tableGoods);
                    str+="<tr><td>"+goods.getId()+"</td><td>"+goods.getName()+"</td><td>"+
                            goods.getType()+ "</td><td>"+goods.getWeight()+"</td><td>"+
                            goods.getReceiveTel()+"</td><td>"+goods.getCost()+"</td><td>"+
                            goods.getC_id()+"</td><td>"+goods.getT_id()+"</td></tr></table></html>";
                    card5.setText(str);
                    gId = realId;
                }
            }
        }
        else if (e.getSource() == this.btnDelGoods) {
            if (this.gId <= 0) {
                ShowMessageUtil.Message("请先查找一个货物！");
            } else {
                if (goodsDao.deleteGoods(gId)) {
                    ShowMessageUtil.Message("成功删除该货物！");
                    gId = 0;
                } else{
                    ShowMessageUtil.Message("此货物不在本公司！\n删除失败~");
                }
            }
        }
//        else if (e.getSource() == this.btnAddGoodsToCon) {
//            if (gId == 0) {
//                ShowMessageUtil.Message("请先查找！");
//            } else {
//                Integer realId = -1;
//                try {
//                    realId = Integer.parseInt(textGoodsId.getText());
//                } catch (NumberFormatException ee) {
//                    ShowMessageUtil.Message("填写不正确!");
//                    return;
//                }
//                if(!cooperateService.addCooperate(logId,realId)){
//                    return;
//                }
//            }
//        }
        else if (e.getSource() == this.btnAddOrUpdataTruck) {
            Integer realId = -1;
            int gcost = -1;
            int gweight = -1;
            int tId = -1;
            try {
                realId = Integer.parseInt(gIdText.getText());
                gcost = Integer.parseInt(gCostText.getText());
                gweight = Integer.parseInt(gCostText.getText());
                tId = Integer.parseInt(gTidText.getText());
            } catch (NumberFormatException ee) {
                ShowMessageUtil.Message("信息不正确!");
                return;
            }
            String gname = gNameText.getText();
            String gType = gTypeText.getText();
            String gReceiveTel = gReceiveTelText.getText();
            if (realId<=0|| gname.equals("")||gType.equals("")||
                    gweight <=0||gReceiveTel.equals("") || gcost <=0 ) {
                ShowMessageUtil.Message("信息不能为空！");
            } else {
                if(goodsDao.queryGoodsById(realId)!=null) {
                    goodService.updGoods(realId, gname,gType, gweight,gReceiveTel, gweight,conId,tId);
                } else{
                    goodService.addGoods(realId, gname,gType, gweight,gReceiveTel, gweight,conId,tId);
                }
            }

        }

        else if (e.getSource() == this.reFresh) {// 刷新
            ConsignorUi.ConMainUi(conId, 0);
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
        if (selectIndex == ConsignorUi.CARD1) {
            initCard1();
        } else if (selectIndex == ConsignorUi.CARD2) {
            initCard2();
        } else if (selectIndex == ConsignorUi.CARD3) {
            initCard4();
        } else if (selectIndex == ConsignorUi.CARD4) {
            initCard5();
        } else if(selectIndex == ConsignorUi.CARD5) {
            initCard6();
        } else {
            System.out.println(selectIndex);
        }
    }
}