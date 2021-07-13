package dao;

import model.Consignor;
import model.Trucks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrucksDaoImpl {
    private static TrucksDaoImpl trucksDao;
    static {
        trucksDao = new TrucksDaoImpl();
    }

    private TrucksDaoImpl(){
    }
    public static TrucksDaoImpl getInstance(){
        return trucksDao;
    }

    public Trucks queryTrucksById(int t_id){
        String sql = "select trucks.id,num,driver1,driver2,startTime,weight,isFree from trucks where trucks.id = ?";
        Connection conn = BaseDaoImpl.getConn();
        Trucks trucks = null;
        ResultSet rs = null;
        PreparedStatement psts = null;
        try {
            psts=conn.prepareStatement(sql);
            psts.setInt(1,t_id);
            //执行
            rs=psts.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String num = rs.getString("num");
                String driver1= rs.getString("driver1");
                String driver2 = rs.getString("driver2");
                String startTime = rs.getString("startTime");
                int weight = rs.getInt("weight");
                int isFree = rs.getInt("isFree");
                trucks=new Trucks(id,num,driver1,driver2,startTime,weight,isFree);
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
        return trucks;
    }

    public List<Trucks> queryTrucksByLog(int l_id){
        String sql = "select trucks.id,num,driver1,driver2,startTime,weight,isFree from trucks,cooperate,logistics where logistics.id = cooperate.l_id and trucks.id = cooperate.t_id and cooperate.l_id = ?";
        Connection conn = BaseDaoImpl.getConn();
        List<Trucks> list = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement psts = null;
        try {
            psts=conn.prepareStatement(sql);
            psts.setInt(1,l_id);
            //执行
            rs=psts.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String num = rs.getString("num");
                String driver1= rs.getString("driver1");
                String driver2 = rs.getString("driver2");
                String startTime = rs.getString("startTime");
                int weight = rs.getInt("weight");
                int isFree = rs.getInt("isFree");
                Trucks trucks=new Trucks(id,num,driver1,driver2,startTime,weight,isFree);
                list.add(trucks);
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

    //删除用户
    public boolean deleteTrucks(int id) {
        String sql="delete from trucks where id="+id;
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

    public boolean insertTruck(Trucks trucks){
        String sql = "insert into trucks(id,num,driver1,driver2,startTime,weight) values(?,?,?,?,?,?)";
        Connection conn = BaseDaoImpl.getConn();
        PreparedStatement psts = null;
        try {
            psts = conn.prepareStatement(sql);
            psts.setObject(1, trucks.getId());
            psts.setObject(2, trucks.getNum());
            psts.setObject(3,trucks.getDriver1());
            psts.setObject(4, trucks.getDriver2());
            psts.setObject(5, trucks.getStartTime());
            psts.setObject(6, trucks.getWeight());
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

    public boolean updateTruck(Trucks trucks) {
        String sql = "update trucks set num=?,driver1=?,driver2=?,startTime=?,weight=?,isFree=? where id="+trucks.getId();
        Connection conn = BaseDaoImpl.getConn();
        PreparedStatement psts=null;
        try {
            psts = conn.prepareStatement(sql);
            psts.setObject(1, trucks.getNum());
            psts.setObject(2, trucks.getDriver1());
            psts.setObject(3, trucks.getDriver2());
            psts.setObject(4, trucks.getStartTime());
            psts.setObject(5, trucks.getWeight());
            psts.setObject(6, trucks.getIsFree());
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
}