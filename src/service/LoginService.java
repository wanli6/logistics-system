package service;

import dao.ConsignorDaoImpl;
import dao.LogisticsDaoImpl;
import model.Consignor;
import model.Logistics;

public class LoginService {
    private static LoginService loginService;

    private LogisticsDaoImpl logisticsDao;
    private ConsignorDaoImpl consignorDao;

    static {
        loginService = new LoginService();
    }

    public static LoginService getInstance(){
        return loginService;
    }
    private LoginService(){
        logisticsDao = LogisticsDaoImpl.getInstance();
        consignorDao = ConsignorDaoImpl.getInstance();
    }

    public Logistics logisticsLogin(int id,String password){
        return logisticsDao.queryLogistics(id,password);
    }
    public Consignor consignorLogin(int id, String password){
        return consignorDao.queryConsignor(id,password);
    }
    public boolean AdminLogin(int id,String password){
        if(id == 0 && password.equals("admin")){
            return true;
        }else {
            return false;
        }
    }

}