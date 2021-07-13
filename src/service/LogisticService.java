package service;

import dao.ConsignorDaoImpl;
import dao.LogisticsDaoImpl;
import model.Consignor;
import model.Logistics;
import util.ShowMessageUtil;

import java.util.List;

public class LogisticService {

    private static LogisticService logisticService;
    static {
        logisticService = new LogisticService();
    }
    public static LogisticService getInstance(){
        return logisticService;
    }

    private LogisticsDaoImpl logisticsDao;
    private ConsignorDaoImpl consignorDao;
    public LogisticService(){
        logisticsDao = LogisticsDaoImpl.getInstance();
    }

    public boolean addLog(int id,String password,String name
            ,String address, String tel){
        Logistics logistics = new Logistics(id,password,name,address,tel);
        if(logisticsDao.insertlog(logistics)){
            ShowMessageUtil.Message("注册成功！");
            return true;
        }else {
            ShowMessageUtil.Message("注册失败，账号已存在！");
            return false;
        }
    }

    public boolean udpLog(int id,String password,String name
            ,String address, String tel){
        Logistics logistics = new Logistics(id,password,name,address,tel);
        if(logisticsDao.updateLogistic(logistics)){
            ShowMessageUtil.Message("更新成功！");
            return true;
        }else {
            ShowMessageUtil.Message("更新失败！");
            return false;
        }
    }

    public Object[][] queryLogsByCid(int c_id) {
        List<Logistics> list = logisticsDao.queryLogisticByCid(c_id);
        Object[][] obj = new Object[list.size()][4];
        int i = 0;
        for (Logistics l: list) {
            obj[i][0] = l.getId();
            obj[i][1] = l.getName();
            obj[i][2] = l.getAddress();
            obj[i][3] = l.getTel();
            i++;
        }
        return obj;
    }

    public Object[][] queryAllLogs() {
        List<Logistics> list = logisticsDao.queryAllLogistic();
        Object[][] obj = new Object[list.size()][5];
        int i = 0;
        for (Logistics l: list) {
            obj[i][0] = l.getId();
            obj[i][1] = l.getName();
            obj[i][2] = l.getAddress();
            obj[i][3] = l.getTel();
            obj[i][4] = l.getPassword();
            i++;
        }
        return obj;
    }
}