package dao;

import model.Contract;
import model.Cooperate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CooperateDaoImpl {
    private static CooperateDaoImpl cooperateDao;
    static {
        cooperateDao = new CooperateDaoImpl();
    }

    private CooperateDaoImpl(){
    }
    public static CooperateDaoImpl getInstance(){
        return cooperateDao;
    }

    //添加
    public boolean insertCooperate(Cooperate cooperate){
        String sql = "insert into cooperate(l_id, t_id) values(?,?)";
        Connection conn = BaseDaoImpl.getConn();
        PreparedStatement psts = null;
        try {
            psts = conn.prepareStatement(sql);
            psts.setObject(1, cooperate.getL_id());
            psts.setObject(2, cooperate.getT_id());
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
    //查询
    public Cooperate queryCooperate(int logId, int tId) {
        String sql = "select * from cooperate where l_id = ? and t_id = ?";
        Connection conn = BaseDaoImpl.getConn();
        ResultSet rs = null;
        Cooperate cooperate = null;
        PreparedStatement psts = null;
        try{
            psts = conn.prepareStatement(sql);
            psts.setObject(1,logId);
            psts.setObject(2,tId);

            rs = psts.executeQuery();
            while(rs.next()){
                int l_id = rs.getInt("l_id");
                int t_id = rs.getInt("t_id");
                cooperate = new Cooperate(l_id,t_id);
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
        return cooperate;
    }
    //删除
    public boolean deleteCooperate(int logId, int tId){
        String sql = "delete from contract where l_id = "+logId+" and c_id = "+tId;
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