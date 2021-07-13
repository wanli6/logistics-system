package dao;

import model.Consignor;
import model.Contract;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContractDaoImpl {
    private static ContractDaoImpl contractDao;
    static {
        contractDao = new ContractDaoImpl();
    }

    private ContractDaoImpl(){
    }
    public static ContractDaoImpl getInstance(){
        return contractDao;
    }

    // 查看所有
    public List<Contract> queryAllContracts() {
        String sql = "select * from contract";
        Connection conn = BaseDaoImpl.getConn();
        //创建一个结果集
        ResultSet rs = null;
        List<Contract> list = new ArrayList<Contract>();
        PreparedStatement psts = null;
        try {
            psts=conn.prepareStatement(sql);
            //执行
            rs=psts.executeQuery();
            while(rs.next()){
                int l_id = rs.getInt("l_id");
                int c_id  = rs.getInt("c_id");
                String date= rs.getString("date");
                int money = rs.getInt("money");
                Contract contract=new Contract(l_id,c_id,date,money);
                list.add(contract);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            try {
                conn.close();
                psts.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    //添加
    public boolean insertContract(Contract contract){
        String sql = "insert into contract(l_id, c_id, date, money) values(?,?,?,?)";
        Connection conn = BaseDaoImpl.getConn();
        PreparedStatement psts = null;
        try {
            psts = conn.prepareStatement(sql);
            psts.setObject(1, contract.getL_id());
            psts.setObject(2, contract.getC_id());
            psts.setObject(3,contract.getDate());
            psts.setObject(4, contract.getMoney());
            int res = psts.executeUpdate();
            if(res > 0) {
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                conn.close();//关闭连接
                psts.close();//关闭预编译
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean updContractMoney(int logId,int conId, int money) {
        String sql = "update contract set money = ? where contract.l_id = ? and contract.c_id = ?";
        Connection conn = BaseDaoImpl.getConn();
        PreparedStatement psts = null;
        try{
            psts = conn.prepareStatement(sql);
            psts.setObject(1,money);
            psts.setObject(2,logId);
            psts.setObject(3,conId);
            int res = psts.executeUpdate();
            if(res>0){return true;}
        }catch (SQLException se){
            se.printStackTrace();
        }finally {
            try {
                conn.close();
                psts.close();
            }catch (SQLException se){
                se.printStackTrace();
            }
        }
        return false;
    }

    public boolean updContractStart(int logId,int conId, String start) {
        String sql = "update contract set date = ? where contract.l_id = ? and contract.c_id = ?";
        Connection conn = BaseDaoImpl.getConn();
        PreparedStatement psts = null;
        try{
            psts = conn.prepareStatement(sql);
            psts.setObject(1,start);
            psts.setObject(2,logId);
            psts.setObject(3,conId);
            int res = psts.executeUpdate();
            if(res>0){return true;}
        }catch (SQLException se){
            se.printStackTrace();
        }finally {
            try {
                conn.close();
                psts.close();
            }catch (SQLException se){
                se.printStackTrace();
            }
        }
        return false;
    }

    public Contract queryContract(int logId,int conId){
        String sql = "select * from contract where l_id = ? and c_id = ?";
        Connection conn = BaseDaoImpl.getConn();
        ResultSet rs = null;
        Contract contract = null;
        PreparedStatement psts = null;
        try{
            psts = conn.prepareStatement(sql);
            psts.setObject(1,logId);
            psts.setObject(2,conId);

            rs = psts.executeQuery();
            while(rs.next()){
                int l_id = rs.getInt("l_id");
                int c_id = rs.getInt("c_id");
                String date = rs.getString("date");
                int money = rs.getInt("money");
                contract = new Contract(l_id,c_id,date,money);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                conn.close();
                psts.close();
                rs.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return contract;
    }

    public boolean deleteContract(int logId, int conId){
        String sql = "delete from contract where l_id = "+logId+" and c_id = "+conId;
        Connection conn = BaseDaoImpl.getConn();
        PreparedStatement psts = null;
        try{
            psts = conn.prepareStatement(sql);
            int res = psts.executeUpdate();
            if(res>0){
                return true;
            }
        }catch (SQLException se){
            se.printStackTrace();
        }finally {
            try {
                conn.close();
                psts.close();
            }catch (SQLException se){
                se.printStackTrace();
            }
        }
        return false;
    }
}