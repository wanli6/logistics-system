package ui;

import dao.ArriveDaoImpl;
import dao.ContractDaoImpl;
import dao.GoodsDaoImpl;
import dao.TrucksDaoImpl;
import model.*;
import service.*;
import util.ShowMessageUtil;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogisticsUi extends JFrame implements ActionListener, ChangeListener {
    private static final long serialVersionUID = 1L;

    public static final int CARD0 = 0;
    public static final int CARD1 = 1;
    public static final int CARD2 = 2;
    public static final int CARD3 = 3;
    public static final int CARD4 = 4;
    public static final int CARD5 = 5;
    public static final int CARD6 = 6;
    public static final int CARD7 = 7;


    private TrucksDaoImpl trucksDao;
    private ContractDaoImpl contractDao;
    private GoodsDaoImpl goodsDao;
    private GoodService goodService;
    private ContractService contractService;
    private ConsignorService consignorService;
    private TruckService truckService;
    private CooperateService cooperateService;
    private ArriveDaoImpl arriveDao;
    private int logId;
    private int conId;
    private int tId;

    private List<Object[]> list = null;

    private Toolkit toolkit = Toolkit.getDefaultToolkit();
    private Dimension sc = toolkit.getScreenSize();
    private JLabel card0 = new JLabel();
    private JPanel card1 = new JPanel();
    private JLabel card2 = new JLabel();
    private JLabel card3 = new JLabel();
    private JPanel card4 = new JPanel();
    private JLabel card5 = new JLabel();
    private JLabel card6 = new JLabel();
    private JLabel card7 = new JLabel();

    private JLabel id = new JLabel("账号");
    private JTextField textId = new JTextField();
    private JButton btnSchCon = new JButton("查找");
    private JLabel info = new JLabel("您可以做如下操作：");
    private JButton btnDelCon = new JButton("删除客户");
    private JTextField textNewStart = new JTextField();
    private JTextField textNewMoney = new JTextField();
    private JButton btnNewStart = new JButton("修改日期");
    private JButton btnNewMoney = new JButton("修改金额");

    private JLabel addInfo = new JLabel("添加一个新的合约：");
    private JLabel newCid = new JLabel("客户账号");
    private JLabel startTime = new JLabel("开始时间");
    private JLabel money = new JLabel("金额");
    private JTextField textCid = new JTextField();
    private JTextField startText = new JTextField();
    private JTextField moneyText = new JTextField();
    private JButton btnNewCon = new JButton("确定");

    private JLabel card3Info = new JLabel("您可以选择一个货车接收货物：");
    private JLabel card3gId = new JLabel("货物编号");
    private JTextField card3gIdText = new JTextField();
    private JLabel card3tId = new JLabel("货车编号");
    private JTextField card3tIdText = new JTextField();
    private JButton btnCard3 = new JButton("确认");

    private JLabel truckId = new JLabel("编号");
    private JTextField textTruckId = new JTextField();
    private JButton btnSchTruck = new JButton("查找");

    private JLabel tidLabel = new JLabel("编号");
    private JLabel tNum = new JLabel("车牌");
    private JLabel driver1 = new JLabel("司机1");
    private JLabel driver2 = new JLabel("司机2");
    private JLabel goTime = new JLabel("发车时间");
    private JLabel weight = new JLabel("载重");
    private JLabel isFree = new JLabel("是否空闲");
    private String[] status = {"忙碌","空闲"};
    private JComboBox isFreeBox = new JComboBox(status);
    private JTextField tidText = new JTextField();
    private JTextField textnum = new JTextField();
    private JTextField driver1Text = new JTextField();
    private JTextField driver2Text = new JTextField();
    private JTextField goTimeText = new JTextField("装车时生成");
    private JTextField weightText = new JTextField("装车时生成");
    private JTextField updTruckText = new JTextField();
    private JButton btnAddOrUpdataTruck = new JButton("确定");

    //    private JButton btnUpdateTruck = new JButton("修 改");
    private JButton btnAddTruckToLog = new JButton("添加到本公司");
    private JButton btnDelTruck = new JButton("删除");

    private JLabel info7 = new JLabel("搜索车辆到达信息（通过车牌）：");
    private JTextField text7 = new JTextField();
    private JButton button7 = new JButton("模糊搜索");
    private JLabel label7 = new JLabel("文件名:");
    private JTextField nameText = new JTextField();
    private JButton storeButton = new JButton("保存");

    JScrollPane scroll;
    JTable msgTable;

    private String[] conTableHead = {"账号", "名称", "地址", "电话"};
    private String tableContract = new String("<html><style>"
            + "table{margin-top:0px;padding-left:80px;}"
            + "td{width:170px;}</style>"
            + "<table><tr><td>本公司账号</td><td>发货公司账号</td><td>日期</td><td>金额</td></tr>");

    private String[] TruckTableHead = {"编号", "车牌号","司机1","司机2","发车时间","载重","是否空闲"};
    private String tableTruck = new String("<html><style>"
            + "table{margin-top:0px;padding-left:80px;}"
            + "td{width:170px;}</style>"
            + "<table><tr><td>车辆编号</td><td>车牌号</td><td>司机1</td><td>司机2</td><td>出发时间</td><td>载重</td><td>是否空闲</td></tr>");

    private String[] goodsTableHead = {"货物编号", "货物名","货物类型","重量","收货人电话","运费","发货方","所在货车"};
    private String tableGoods = new String("<html><style>"
            + "table{margin-top:0px;padding-left:80px;}"
            + "td{width:170px;}</style>"
            + "<table><tr><td>货物编号</td><td>货物名</td><td>货物类型</td><td>重量</td><td>收货人电话</td><td>运费</td><td>发货方</td><td>所在货车</td></tr>");

    private String[] arriveTableHead = {"货车编号", "货车车牌","发车时间","到达时间","收货站点编号","收货站点名","收货点名字","地址"};

    private Font font = new Font("楷体", 0, 20);
    private Font font1 = new Font("楷体", 0, 16);

    private JTabbedPane tabbedPane;
    private JButton reFresh = new JButton("刷新");
    private JButton btnexit = new JButton("退出");//
    private JMenuBar menuBar = new JMenuBar();

    public LogisticsUi(int logId, int defaultCard) {
        this.logId = logId;
        init();
        initPane();
        tabbedPane.addTab("欢迎您！", card0);// 后面将card0设为不可用
        tabbedPane.addTab("1.本公司客户", card1);
        tabbedPane.addTab("2.客户签约管理", card2);
        tabbedPane.addTab("3.可接收货物", card3);
        tabbedPane.addTab("4.本公司车辆", card4);
        tabbedPane.addTab("5.管理车辆", card5);
        tabbedPane.addTab("6.添加车辆", card6);
        tabbedPane.addTab("7.抵达信息", card7);
        tabbedPane.setSelectedIndex(defaultCard);
        tabbedPane.setEnabledAt(0, false);

        addListener();
    }
    private void initCard1() {
        card1.removeAll();
        JTable conTable = new JTable(consignorService.queryConsByLid(logId), conTableHead);
        conTable.setFont(font);
        conTable.setPreferredSize(new Dimension(900, 600));
        conTable.getTableHeader().setPreferredSize(new Dimension(0, 30));
        conTable.getTableHeader().setFont(font1);
        conTable.setRowHeight(30);
        conTable.setEnabled(false); // 设置为不开操作
        JScrollPane scroll = new JScrollPane(conTable);
        scroll.setPreferredSize(new Dimension(900, 720));
        card1.add(scroll);
        card1.setFont(font);
    }
    private void initCard2() {
        id.setBounds(290, 20, 70, 30);
        textId.setBounds(380, 20, 150, 30);
        btnSchCon.setBounds(560, 20, 80, 30);

        info.setBounds(290, 70, 200, 30);
        textNewStart.setBounds(290, 120, 200, 30);
        textNewMoney.setBounds(290,170,200,30);

        btnDelCon.setBounds(510,70,120,30);
        btnNewStart.setBounds(510, 120, 120, 30);
        btnNewMoney.setBounds(510, 170, 120, 30);
        card2.setFont(font);
        id.setFont(font);
        textId.setFont(font);
        btnSchCon.setFont(font1);
        info.setFont(font);
        textNewStart.setFont(font);
        textNewMoney.setFont(font);
        btnDelCon.setFont(font1);
        btnNewMoney.setFont(font1);
        btnNewStart.setFont(font1);
        card2.add(textId);
        card2.add(id);
        card2.add(info);
        card2.add(textNewStart);
        card2.add(textNewMoney);
        card2.add(btnDelCon);
        card2.add(btnSchCon);
        card2.add(btnNewMoney);
        card2.add(btnNewStart);

        addInfo.setBounds(290,320,200,30);
        addInfo.setFont(font);
        newCid.setBounds(290, 360, 80, 30);
        startTime.setBounds(290, 400, 80, 30);
        money.setBounds(290, 440, 80, 30);
        textCid.setBounds(400, 360, 200, 30);
        startText.setBounds(400, 400, 200, 30);
        moneyText.setBounds(400, 440, 200, 30);
        btnNewCon.setBounds(450, 480, 90, 30);
        newCid.setFont(font);
        textCid.setFont(font);
        startTime.setFont(font);
        money.setFont(font);
        startText.setFont(font);
        moneyText.setFont(font);
        btnNewCon.setFont(font);
        card2.add(newCid);
        card2.add(startTime);
        card2.add(money);
        card2.add(textCid);
        card2.add(startText);
        card2.add(moneyText);
        card2.add(btnNewCon);
        card2.add(addInfo);
    }
    private void initCard3() {
        card3.removeAll();
        card3.setLayout(new BoxLayout(card3,BoxLayout.Y_AXIS));
        JTable truckTable = new JTable(goodService.queryGoodsNoTruck(logId), goodsTableHead);
        truckTable.setFont(font);
        truckTable.setPreferredSize(new Dimension(900, 200));
        truckTable.getTableHeader().setPreferredSize(new Dimension(0, 30));
        truckTable.getTableHeader().setFont(font1);
        truckTable.setRowHeight(30);
        truckTable.setEnabled(false); // 设置为不开操作
        JScrollPane scroll = new JScrollPane(truckTable);
        scroll.setPreferredSize(new Dimension(900, 200));

        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);
        jPanel.setSize(new Dimension(900,200));
        card3Info.setBounds(30,0,300,30);
        card3Info.setFont(font);
        card3gId.setBounds(30,50,100,30);
        card3gId.setFont(font);
        card3gIdText.setBounds(150,50,180,30);
        card3gIdText.setFont(font);
        card3tId.setBounds(30,100,100,30);
        card3tId.setFont(font);
        card3tIdText.setBounds(150,100,180,30);
        card3tIdText.setFont(font);
        btnCard3.setBounds(350,100,150,30);
        btnCard3.setFont(font);
        jPanel.add(card3Info);
        jPanel.add(card3tId);
        jPanel.add(card3tIdText);
        jPanel.add(btnCard3);
        jPanel.add(card3gId);
        jPanel.add(card3gIdText);

        card3.add(scroll);
        card3.add(jPanel);
        card3.setFont(font);
    }
    private void initCard4() {
        JTable truckTable = new JTable(truckService.queryTrucksById(logId), TruckTableHead);
        truckTable.setFont(font);
        truckTable.setPreferredSize(new Dimension(900, 600));
        truckTable.getTableHeader().setPreferredSize(new Dimension(0, 30));
        truckTable.getTableHeader().setFont(font1);
        truckTable.setRowHeight(30);
        truckTable.setEnabled(false); // 设置为不开操作
        JScrollPane scroll = new JScrollPane(truckTable);
        scroll.setPreferredSize(new Dimension(900, 720));
        card4.removeAll();
        card4.add(scroll);
    }
    private void initCard5() {

        truckId.setBounds(310, 50, 70, 30);
        textTruckId.setBounds(400, 50, 150, 30);
        btnSchTruck.setBounds(580, 50, 80, 30);
        info.setBounds(310, 100, 200, 30);
        btnDelTruck.setBounds(530, 100, 130, 30);
        btnAddTruckToLog.setBounds(460,150,200,30);

        truckId.setFont(font);
        textTruckId.setFont(font);
        btnSchTruck.setFont(font1);
        info.setFont(font);
        btnDelTruck.setFont(font1);
        btnAddTruckToLog.setFont(font1);

        card5.add(info);
        card5.add(truckId);
        card5.add(textTruckId);
        card5.add(btnSchTruck);
        card5.setFont(font);
        card5.add(btnDelTruck);
        card5.add(btnAddTruckToLog);

    }
    private void initCard6() {
        tidLabel.setBounds(310, 50, 80, 30);
        tidText.setBounds(400, 50, 200, 30);

        tNum.setBounds(310, 100, 80, 30);
        textnum.setBounds(400, 100, 200, 30);

        driver1.setBounds(310, 150, 80, 30);
        driver1Text.setBounds(400, 150, 200, 30);

        driver2.setBounds(310, 200, 80, 30);
        driver2Text.setBounds(400, 200, 200, 30);

        goTime.setBounds(310,250,80,30);
        goTimeText.setBounds(400,250,200,30);
        goTimeText.setEditable(false);

        weight.setBounds(310,300,80,30);
        weightText.setBounds(400,300,200,30);
        weightText.setEditable(false);

        isFree.setBounds(310,350,80,30);
        isFreeBox.setBounds(400,350,200,30);
        btnAddOrUpdataTruck.setBounds(430, 410, 90, 30);


        tidLabel.setFont(font);
        tidText.setFont(font);
        tNum.setFont(font);
        textnum.setFont(font);
        driver1.setFont(font);
        driver1Text.setFont(font);
        driver2.setFont(font);
        driver2Text.setFont(font);
        goTime.setFont(font);
        goTimeText.setFont(font);
        weight.setFont(font);
        weightText.setFont(font);
        isFree.setFont(font);
        isFreeBox.setFont(font);
        btnAddOrUpdataTruck.setFont(font);

        card6.add(tidLabel);
        card6.add(tidText);
        card6.add(textnum);
        card6.add(tNum);
        card6.add(driver1);
        card6.add(driver1Text);
        card6.add(driver2);
        card6.add(driver2Text);
        card6.add(goTime);
        card6.add(goTimeText);
        card6.add(weight);
        card6.add(weightText);
        card6.add(isFree);
        card6.add(isFreeBox);
        card6.add(btnAddOrUpdataTruck);
    }
    private void initCard7(){
        info7.setFont(font);
        info7.setBounds(300,10,400,30);
        text7.setFont(font);
        text7.setBounds(300,50,200,30);
        button7.setFont(font);
        button7.setBounds(510,50,200,30);
        label7.setFont(font);
        label7.setBounds(300,90,200,30);
        nameText.setFont(font1);
        nameText.setBounds(300,130,200,30);
        storeButton.setFont(font);
        storeButton.setBounds(510,130,100,30);
        card7.add(info7);
        card7.add(text7);
        card7.add(button7);
        card7.add(label7);
        card7.add(nameText);
        card7.add(storeButton);
    }
    private void initPane() {
        setTitle("物流信息管理系统--物流公司端");
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
        conId = -1;
        tId = -1;
        consignorService = ConsignorService.getInstance();
        truckService = TruckService.getInstance();
        contractService = ContractService.getInstance();
        contractDao = ContractDaoImpl.getInstance();
        trucksDao = TrucksDaoImpl.getInstance();
        goodsDao = GoodsDaoImpl.getInstance();
        cooperateService = CooperateService.getInstance();
        goodService = GoodService.getInstance();
        arriveDao = ArriveDaoImpl.getInstance();
    }
    private void addListener() {
        btnDelCon.addActionListener(this);
        btnNewCon.addActionListener(this);
        btnSchCon.addActionListener(this);
        btnNewStart.addActionListener(this);
        btnNewMoney.addActionListener(this);
        btnSchTruck.addActionListener(this);
        btnAddOrUpdataTruck.addActionListener(this);
        btnDelTruck.addActionListener(this);
        btnAddTruckToLog.addActionListener(this);
        btnCard3.addActionListener(this);
        button7.addActionListener(this);
        storeButton.addActionListener(this);
    }

    public static void LogMainUi(int id, int defaultCard) {
        LogisticsUi jTabbedPaneTest = new LogisticsUi(id, defaultCard);
        jTabbedPaneTest.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnSchCon) {
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
                Contract contract = contractDao.queryContract(logId,realId);
                if (contract == null)
                    ShowMessageUtil.Message("账号不存在!");
                else {
                    String str = new String(tableContract);
                    str += "<tr><td>" + contract.getL_id() + "</td><td>"
                            + contract.getC_id() + "</td><td>" + contract.getDate()
                            + "</td><td>" + contract.getMoney()
                            + "</td></tr></table></html>";
                    card2.setText(str);
                    conId = realId;
                }
            }
        }
        else if (e.getSource() == this.btnDelCon) {
            if (conId <= 0)
                ShowMessageUtil.Message("请先查找一个与发货公司的合约！");
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
        else if (e.getSource() == this.btnNewCon) {
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
                contractService.addContract(logId,realId,startText.getText(),Integer.parseInt(moneyText.getText()));
            }
        }
        else if (e.getSource() == this.btnCard3){
            int realTId = -1;
            int realGId = -1;
            realTId = Integer.parseInt(card3tIdText.getText());
            realGId = Integer.parseInt(card3gIdText.getText());
            Trucks trucks = truckService.queryTruckBelongLog(realTId,logId);
            Goods goods = goodService.queryGoodsNoTruckByLidGid(logId,realGId);
            if(trucks == null){
                ShowMessageUtil.Message("没有找到该车");
            }
            else if(trucks.getIsFree() <=0){
                ShowMessageUtil.Message("该车正忙");
            }else if(goods == null){
                ShowMessageUtil.Message("没有找到该货物");
            }else if(goods.getT_id()!=0){
                ShowMessageUtil.Message("该货物已经装车");
            }else{
                trucks.setIsFree(0);
                trucks.setWeight(goods.getWeight());
                goods.setT_id(trucks.getId());
                goodsDao.updateGoods(goods);
                trucksDao.updateTruck(trucks);
                ShowMessageUtil.Message("添加成功");
            }

        }
        else if (e.getSource() == this.btnSchTruck) {
            String id = textTruckId.getText();
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
                Trucks trucks = trucksDao.queryTrucksById(realId);
                if (trucks == null || trucks.getId() <= 0){
                    ShowMessageUtil.Message("车辆不存在!");
                } else {
                    String str = new String(tableTruck);
                    str+="<tr><td>"+trucks.getId()+"</td><td>"+trucks.getNum()+"</td><td>"+
                            trucks.getDriver1()+ "</td><td>"+trucks.getDriver2()+"</td><td>"+
                            trucks.getStartTime()+"</td><td>"+trucks.getWeight()+"</td><td>"+trucks.getIsFree()+"</td></tr></table></html>";
                    card5.setText(str);
                    tId = realId;
                }
            }
        }
        else if (e.getSource() == this.btnDelTruck) {
            if (this.tId <= 0) {
                ShowMessageUtil.Message("请先查找一辆车！");
            } else {
                if (trucksDao.deleteTrucks(tId)) {
                    ShowMessageUtil.Message("成功删除该车！");
                    tId = 0;
                } else{
                    ShowMessageUtil.Message("此车不在本公司！\n删除失败~");
                }
            }
        }
        else if (e.getSource() == this.btnAddOrUpdataTruck) {
            Integer realId = -1;
            try {
                realId = Integer.parseInt(tidText.getText());
            } catch (NumberFormatException ee) {
                ShowMessageUtil.Message("编号不正确!");
                return;
            }
            String num = textnum.getText();
            String driver1 = driver1Text.getText();
            String driver2 = driver2Text.getText();
            int isFree = isFreeBox.getSelectedIndex();
            if (realId<=0||num.equals("")||driver1.equals("")||
                    driver2.equals("")) {
                ShowMessageUtil.Message("信息不能为空！");
            } else {
                if(trucksDao.queryTrucksById(realId)!=null) {
                    truckService.updTruck(realId,num,driver1,driver2,null,0,isFree);
                } else{
                    truckService.addTruck(realId,num,driver1,driver2,null,0,isFree);
                }
            }

        }
        else if (e.getSource() == this.btnAddTruckToLog) {
            if (tId == 0) {
                ShowMessageUtil.Message("请先查找！");
            } else {
                Integer realId = -1;
                try {
                    realId = Integer.parseInt(textTruckId.getText());
                } catch (NumberFormatException ee) {
                    ShowMessageUtil.Message("填写不正确!");
                    return;
                }
                if(!cooperateService.addCooperate(logId,realId)){
                    return;
                }
            }
        }
        else if (e.getSource() == this.reFresh) {// 刷新
            LogisticsUi.LogMainUi(logId, 0);
            this.dispose();
        }
        else if (e.getSource() == this.btnexit) {// 退出登录
            new App();
            this.dispose();
        }
        else if (e.getSource() == this.button7){
            if(text7.getText() == null){
                ShowMessageUtil.Message("输入不能为空！");
            }else{
                String tableArrive = new String("<html><style>"
                        + "table{margin-top:0px;padding-left:80px;}"
                        + "td{width:170px;}</style>"
                        + "<table><tr><td>货车编号</td><td>货车车牌</td><td>发车时间</td><td>到达时间</td><td>站点电话</td><td>站点名称</td><td>站点地址</td></tr>");

                list = arriveDao.queryMsgByNum(text7.getText());
                for(Object[] o : list){
                    tableArrive+="<tr><td>"+o[0]+"</td><td>"+o[1]+"</td><td>"+o[2]+"</td><td>"+o[3]+"</td><td>"+o[4]+"</td><td>"+o[5]+"</td><td>"+o[6]+"</td></tr>";
                }
                tableArrive+="</table></html>";
                card7.setText(tableArrive);
                card7.setFont(font);
            }
        }
        else if(e.getSource() == storeButton){
            if(list == null){
                ShowMessageUtil.Message("请先查询");
            }else if(nameText.getText().equals("")){
                ShowMessageUtil.Message("输入文件名");
            }else{
                try(FileWriter fileWriter = new FileWriter("resources/"+nameText.getText())){
                    for(Object[] o : list ){
                        fileWriter.write(o[0].toString()+"   ");
                        fileWriter.write(o[1].toString()+"   ");
                        fileWriter.write(o[2].toString()+"   ");
                        fileWriter.write(o[3].toString()+"   ");
                        fileWriter.write(o[4].toString()+"   ");
                        fileWriter.write(o[5].toString()+"   ");
                        fileWriter.write(o[6].toString()+"   \n");
                    }
                    ShowMessageUtil.Message("写入文件成功");
                }catch (IOException ee){
                    ee.printStackTrace();
                }
            }
        }
    }
    @Override
    public void stateChanged(ChangeEvent e) {
        int selectIndex = tabbedPane.getSelectedIndex();
        if (selectIndex == LogisticsUi.CARD1) {
            initCard1();
        } else if (selectIndex == LogisticsUi.CARD2) {
            initCard2();
        } else if (selectIndex == LogisticsUi.CARD3) {
            initCard3();
        } else if (selectIndex == LogisticsUi.CARD4) {
            initCard4();
        } else if (selectIndex == LogisticsUi.CARD5) {
            initCard5();
        }
        else if(selectIndex == LogisticsUi.CARD6) {
            initCard6();
        }
        else if(selectIndex == LogisticsUi.CARD7) {
            initCard7();
        }
        else {
            System.out.println(selectIndex);
        }
    }
}