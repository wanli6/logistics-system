package service;

import dao.ConsignorDaoImpl;
import dao.ContractDaoImpl;
import dao.LogisticsDaoImpl;
import model.Consignor;
import model.Contract;
import util.ShowMessageUtil;

public class ContractService {
    private static ContractService contractService;
    static {
        contractService =new  ContractService();
    }
    public static ContractService getInstance(){
        return contractService;
    }
    private ContractDaoImpl contractDao;
    private ContractService(){
        contractDao = ContractDaoImpl.getInstance();
    }

    public boolean addContract(int l_id,int c_id,String date,int money){
        Contract contract = new Contract(l_id,c_id,date,money);
        if(contractDao.insertContract(contract)){
            ShowMessageUtil.Message("添加成功！");
            return true;
        }else {
            ShowMessageUtil.Message("添加失败！");
            return false;
        }
    }


}