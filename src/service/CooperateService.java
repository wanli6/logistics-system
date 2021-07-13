package service;

import dao.CooperateDaoImpl;
import dao.LogisticsDaoImpl;
import dao.TrucksDaoImpl;
import model.Cooperate;
import util.ShowMessageUtil;

public class CooperateService {
    private static CooperateService cooperateService;
    static {
        cooperateService = new CooperateService();
    }
    public static CooperateService getInstance(){return cooperateService;}
    private LogisticsDaoImpl logisticsDao;
    private TrucksDaoImpl trucksDao;
    private CooperateDaoImpl cooperateDao;
    private CooperateService(){
        logisticsDao = LogisticsDaoImpl.getInstance();
        trucksDao = TrucksDaoImpl.getInstance();
        cooperateDao = CooperateDaoImpl.getInstance();
    }
    public boolean addCooperate(int l_id,int t_id){
        Cooperate cooperate = new Cooperate(l_id,t_id);
        if(cooperateDao.insertCooperate(cooperate)){
            ShowMessageUtil.Message("添加成功！");
            return true;
        }else{
            ShowMessageUtil.Message("添加失败！");
            return false;
        }
    }
}