package service;

import dao.GoodsDaoImpl;
import dao.LogisticsDaoImpl;
import dao.TrucksDaoImpl;
import model.Trucks;
import util.ShowMessageUtil;

import java.util.List;

public class TruckService {
    private static TruckService truckService;
    static {
        truckService = new TruckService();
    }
    public static TruckService getInstance(){return truckService;}
    private LogisticsDaoImpl logisticsDao;
    private GoodsDaoImpl goodsDao;
    private TrucksDaoImpl trucksDao;
    private TruckService(){
        logisticsDao = LogisticsDaoImpl.getInstance();
        goodsDao = GoodsDaoImpl.getInstance();
        trucksDao = TrucksDaoImpl.getInstance();
    }

    public Object[][] queryTrucksById(int l_id){
        List<Trucks> list = trucksDao.queryTrucksByLog(l_id);
        Object[][] obj = new Object[list.size()][7];
        int i = 0;
        for (Trucks c: list) {
            obj[i][0] = c.getId();
            obj[i][1] = c.getNum();
            obj[i][2] = c.getDriver1();
            obj[i][3] = c.getDriver2();
            obj[i][4] = c.getStartTime();
            obj[i][5] = c.getWeight();
            obj[i][6] = c.getIsFree();
            i++;
        }
        return obj;
    }

    public Trucks queryTruckBelongLog(int t_id,int l_id){
        List<Trucks> list = trucksDao.queryTrucksByLog(l_id);
        for(Trucks t : list){
            if(t.getId() == t_id){
                return t;
            }
        }
        return null;
    }

    public boolean addTruck(int id,String num,String driver1
            ,String driver2, String startTime,int weight,int isFree){
        Trucks trucks = new Trucks(id,num,driver1,driver2,startTime,weight,isFree);
        if(trucksDao.insertTruck(trucks)){
            ShowMessageUtil.Message("添加成功！");
            return true;
        }else {
            ShowMessageUtil.Message("添加失败！");
            return false;
        }
    }

    public boolean updTruck(int id,String num,String driver1
            ,String driver2, String startTime,int weight,int isFree){
        Trucks trucks = new Trucks(id,num,driver1,driver2,startTime,weight,isFree);
        if(trucksDao.updateTruck(trucks)){
            ShowMessageUtil.Message("更新成功！");
            return true;
        }else {
            ShowMessageUtil.Message("更新失败!");
            return false;
        }
    }

}