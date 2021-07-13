package dao;

import model.Goods;
import model.Trucks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArriveDaoImpl {
    private static ArriveDaoImpl arriveDao;
    static {
        arriveDao = new ArriveDaoImpl();
    }

    private ArriveDaoImpl(){
    }
    private TrucksDaoImpl trucksDao;
    public static ArriveDaoImpl getInstance(){
        return arriveDao;
    }

    public List<Object[]> queryMsgByNum(String num){
        String sql = "select distinct * from arrive_msg where num like "+"'%"+num+"%'";
        Connection conn = BaseDaoImpl.getConn();
        List<Object[]> list = new ArrayList<>();

        ResultSet rs = null;
        PreparedStatement psts = null;
        try {
            psts = conn.prepareStatement(sql);
            rs = psts.executeQuery();
            while (rs.next()){
                Object[] obj = new Object[7];
                obj[0] = rs.getInt("tid");
                obj[1] = rs.getString("num");
                obj[2] = rs.getString("startTime");
                obj[3] = rs.getString("data");
                obj[4] = rs.getInt("id");
                obj[5] = rs.getString("name");
                obj[6] = rs.getString("address");
                list.add(obj);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally{
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