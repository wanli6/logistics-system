package dao;

import model.Goods;
import model.Trucks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodsDaoImpl {
    private static GoodsDaoImpl goodsDao;
    static {
        goodsDao = new GoodsDaoImpl();
    }
    private GoodsDaoImpl(){}
    public static GoodsDaoImpl getInstance(){
        return goodsDao;
    }
    public List<Goods> queryGoodsByCon(int c_id){
        String sql = "select goods.id,goods.name,goods.type,goods.weight,goods.receivetel,goods.cost,goods.c_id,goods.t_id from goods where goods.c_id = ?";
        Connection conn = BaseDaoImpl.getConn();
        List<Goods> list = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement psts = null;
        try {
            psts=conn.prepareStatement(sql);
            psts.setInt(1,c_id);
            //执行
            rs=psts.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String type= rs.getString("type");
                int weight = rs.getInt("weight");
                String receivetel = rs.getString("receivetel");
                int cost = rs.getInt("cost");
                int cid = rs.getInt("c_id");
                int tid = rs.getInt("t_id");
                Goods goods=new Goods(id,name,type,weight,receivetel,cost,cid,tid);
                list.add(goods);
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

    public Goods queryGoodsById(int g_id){
        String sql = "select goods.id,goods.name,goods.type,goods.weight,goods.receivetel,goods.cost,goods.c_id,goods.t_id from goods where goods.id = ?";
        Connection conn = BaseDaoImpl.getConn();
        Goods  goods = null;
        ResultSet rs = null;
        PreparedStatement psts = null;
        try {
            psts=conn.prepareStatement(sql);
            psts.setInt(1, g_id);
            //执行
            rs=psts.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String type= rs.getString("type");
                int weight = rs.getInt("weight");
                String receivetel = rs.getString("receivetel");
                int cost = rs.getInt("cost");
                int cid = rs.getInt("c_id");
                int tid = rs.getInt("t_id");
                goods=new Goods(id,name,type,weight,receivetel,cost,cid,tid);
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
        return goods;
    }

    public boolean deleteGoods(int g_id) {
        String sql="delete from goods where id="+g_id;
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

    public boolean insertGoods(Goods goods){
        String sql = "insert into goods(id,name,type,weight,receivetel,cost,c_id,t_id) values(?,?,?,?,?,?,?,?)";
        Connection conn = BaseDaoImpl.getConn();
        PreparedStatement psts = null;
        try {
            psts = conn.prepareStatement(sql);
            psts.setObject(1, goods.getId());
            psts.setObject(2, goods.getName());
            psts.setObject(3, goods.getType());
            psts.setObject(4, goods.getWeight());
            psts.setObject(5, goods.getReceiveTel());
            psts.setObject(6, goods.getCost());
            psts.setObject(7, goods.getC_id());
            psts.setObject(8, goods.getT_id());
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

    public boolean updateGoods(Goods goods) {
        String sql = "update goods set name=?,type=?,weight=?,receivetel=?,cost=?,c_id=?,t_id=? where id="+goods.getId();
        Connection conn = BaseDaoImpl.getConn();
        PreparedStatement psts=null;
        try {
            psts = conn.prepareStatement(sql);
            psts.setObject(1, goods.getName());
            psts.setObject(2, goods.getType());
            psts.setObject(3, goods.getWeight());
            psts.setObject(4, goods.getReceiveTel());
            psts.setObject(5, goods.getCost());
            psts.setObject(6, goods.getC_id());
            psts.setObject(7, goods.getT_id());
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