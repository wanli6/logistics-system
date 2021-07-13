package service;

import dao.ConsignorDaoImpl;
import dao.ContractDaoImpl;
import dao.LogisticsDaoImpl;
import model.Consignor;
import model.Contract;
import model.Logistics;
import util.ShowMessageUtil;

import java.util.List;

public class ConsignorService {
    private static ConsignorService consignorService;
    static {
        consignorService = new ConsignorService();
    }
    public static ConsignorService getInstance(){
        return consignorService;
    }
    ConsignorDaoImpl consignorDao ;
//    LogisticsDaoImpl logisticsDao ;
//    ContractDaoImpl contractDao;
    private ConsignorService(){
        consignorDao = ConsignorDaoImpl.getInstance();
        //logisticsDao = LogisticsDaoImpl.getInstance();
        //contractDao = ContractDaoImpl.getInstance();
    }
    public boolean addCon(int id,String password,String name
            ,String address, String tel){
        Consignor consignor = new Consignor(id,password,name,address,tel);
        if(consignorDao.insertCons(consignor)){
            ShowMessageUtil.Message("注册成功！");
            return true;
        }else {
            ShowMessageUtil.Message("注册失败，账号已存在！");
            return false;
        }
    }

    public boolean updCon(int id,String password,String name
            ,String address, String tel){
        Consignor consignor = new Consignor(id,password,name,address,tel);
        if(consignorDao.updateConsignor(consignor)){
            ShowMessageUtil.Message("更新成功！");
            return true;
        }else {
            ShowMessageUtil.Message("更新失败！");
            return false;
        }
    }

    public Object[][] queryConsByLid(int l_id) {
        List<Consignor> list = consignorDao.queryConsignorsByLid(l_id);
        Object[][] obj = new Object[list.size()][4];
        int i = 0;
        for (Consignor c: list) {
            obj[i][0] = c.getId();
            obj[i][1] = c.getName();
            obj[i][2] = c.getAddress();
            obj[i][3] = c.getTel();
            i++;
        }
        return obj;
    }

    public Object[][] queryAllCons() {
        List<Consignor> list = consignorDao.queryAllConsignors();
        Object[][] obj = new Object[list.size()][5];
        int i = 0;
        for (Consignor c: list) {
            obj[i][0] = c.getId();
            obj[i][1] = c.getName();
            obj[i][2] = c.getAddress();
            obj[i][3] = c.getTel();
            obj[i][4] = c.getPassword();
            i++;
        }
        return obj;
    }
}