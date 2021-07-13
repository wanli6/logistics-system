package dao;

import model.Consignor;
import model.Logistics;
import model.Trucks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LogisticsDaoImpl {
    private static LogisticsDaoImpl logisticsDaoImpl;
    static {
        logisticsDaoImpl = new LogisticsDaoImpl();
    }
    private LogisticsDaoImpl(){

    }

    public boolean updateLogistic(Logistics logistics) {
        String sql = "update logistics set name=?,address=?,tel=?,password=? where id=?";
        Connection conn = BaseDaoImpl.getConn();
        PreparedStatement psts=null;
        try {
            psts = conn.prepareStatement(sql);
            psts.setObject(1, logistics.getName());
            psts.setObject(2, logistics.getAddress());
            psts.setObject(3, logistics.getTel());
            psts.setObject(4, logistics.getPassword());
            psts.setObject(5, logistics.getId());
            int res = psts.executeUpdate();
            if(res > 0) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            try {
                conn.close();//关闭连接
                psts.close();//关闭预编译
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static LogisticsDaoImpl getInstance(){
        return logisticsDaoImpl;
    }

    public Logistics queryLogistics(int id,String password){
        String sql = "select * from logistics where id = ? ";
        if(password != null){
            sql += "and password = ?";
        }
        Connection conn = BaseDaoImpl.getConn();
        ResultSet rs = null;
        Logistics logistics = null;

        PreparedStatement psts = null;
        try {
            psts = conn.prepareStatement(sql);
            //简单防注入
            psts.setInt(1,id);
            if(password!=null) {
                psts.setString(2, password);
            }
            //执行
            rs = psts.executeQuery();
            while(rs.next()){
                int return_id = rs.getInt("id");
                String return_password = rs.getString("password");
                String return_name = rs.getString("name");
                String return_address = rs.getString("address");
                String return_tel = rs.getString("tel");
                logistics = new Logistics(return_id,return_password,
                        return_name,return_address,return_tel);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                conn.close();;
                psts.close();;
                rs.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return logistics;
    }

    public boolean insertlog(Logistics logistics){
        String sql = "insert into logistics(id,name,address,tel,password) values(?,?,?,?,?)";
        Connection conn = BaseDaoImpl.getConn();
        PreparedStatement psts = null;
        try {
            psts = conn.prepareStatement(sql);
            psts.setObject(1, logistics.getId());
            psts.setObject(2, logistics.getName());
            psts.setObject(3,logistics.getAddress());
            psts.setObject(4, logistics.getTel());
            psts.setObject(5, logistics.getPassword());
            int res = psts.executeUpdate();
            if(res > 0) {
                return true;
            }
        }catch (SQLException e){
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

    //删除用户
    public boolean deleteLog(int id) {
        String sql="delete from logistics where id="+id;
        Connection conn = BaseDaoImpl.getConn();
        PreparedStatement psts = null;
        try {
            psts = conn.prepareStatement(sql);
            int res = psts.executeUpdate();
            if(res > 0) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    // 查看所有用户
    public List<Logistics> queryAllLogistic() {
        String sql = "select * from logistics";
        Connection conn = BaseDaoImpl.getConn();
        //创建一个结果集
        ResultSet rs = null;
        List<Logistics> list = new ArrayList<Logistics>();
        PreparedStatement psts = null;
        try {
            psts=conn.prepareStatement(sql);
            //执行
            rs=psts.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String address= rs.getString("address");
                String tel = rs.getString("tel");
                String password = rs.getString("password");
                Logistics logistics=new Logistics(id,password,name,address,tel);
                list.add(logistics);
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

    public List<Logistics> queryLogisticByCid(int c_id){
        String sql = "select logistics.id,logistics.name,logistics.address,logistics.tel,logistics.password from logistics,contract,consignor where consignor.id = ? and consignor.id = contract.c_id and logistics.id = contract.l_id";
        Connection conn = BaseDaoImpl.getConn();
        ResultSet rs = null;
        List<Logistics> list = new ArrayList<Logistics>();
        PreparedStatement psts = null;
        try {
            psts=conn.prepareStatement(sql);
            psts.setInt(1,c_id);
            //执行
            rs=psts.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String password = rs.getString("password");
                String name = rs.getString("name");
                String address= rs.getString("address");
                String tel = rs.getString("tel");
                Logistics logistics=new Logistics(id,password,name,address,tel);
                list.add(logistics);
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
}