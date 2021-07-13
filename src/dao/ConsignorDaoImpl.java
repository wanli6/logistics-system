package dao;

import model.Consignor;
import model.Logistics;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsignorDaoImpl {
    private static ConsignorDaoImpl consignorDaoImpl;
    static {
        consignorDaoImpl = new ConsignorDaoImpl();
    }

    private ConsignorDaoImpl(){

    }
    public static ConsignorDaoImpl getInstance(){
        return consignorDaoImpl;
    }

    public boolean insertCons(Consignor consignor){
        String sql = "insert into consignor(id,name,address,tel,password) values(?,?,?,?,?)";
        Connection conn = BaseDaoImpl.getConn();
        PreparedStatement psts = null;
        try {
            psts = conn.prepareStatement(sql);
            psts.setObject(1, consignor.getId());
            psts.setObject(2, consignor.getName());
            psts.setObject(3,consignor.getAddress());
            psts.setObject(4, consignor.getTel());
            psts.setObject(5, consignor.getPassword());
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

    public Consignor queryConsignor(int id, String password) {
        String sql = "select * from consignor where id = ?";
        if (password != null) sql += " and password = ?";
        Connection conn = BaseDaoImpl.getConn();
        //创建一个结果集
        ResultSet rs = null;
        Consignor consignor = null;
        PreparedStatement psts = null;
        try {
            psts = conn.prepareStatement(sql);
            psts.setObject(1, id);
            if (password != null) {
                psts.setObject(2, password);
            }
            //执行
            rs=psts.executeQuery();
            while(rs.next()){
                int return_id = rs.getInt("id");
                String name = rs.getString("name");
                String address= rs.getString("address");
                String tel = rs.getString("tel");
                String return_password = rs.getString("password");
                consignor = new Consignor(return_id, return_password,name, address, tel);
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
        return consignor;
    }

    public List<Consignor> queryConsignorsByLid(int l_id){
        String sql = "select consignor.id,consignor.name,consignor.address,consignor.tel,consignor.password from consignor,contract,logistics where logistics.id = ? and consignor.id = contract.c_id and logistics.id = contract.l_id";
        Connection conn = BaseDaoImpl.getConn();
        ResultSet rs = null;
        List<Consignor> list = new ArrayList<Consignor>();
        PreparedStatement psts = null;
        try {
            psts=conn.prepareStatement(sql);
            psts.setInt(1,l_id);
            //执行
            rs=psts.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String password = rs.getString("password");
                String name = rs.getString("name");
                String address= rs.getString("address");
                String tel = rs.getString("tel");
                Consignor consignor=new Consignor(id,password,name,address,tel);
                list.add(consignor);
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

    // 查看所有用户
    public List<Consignor> queryAllConsignors() {
        String sql = "select * from consignor";
        Connection conn = BaseDaoImpl.getConn();
        //创建一个结果集
        ResultSet rs = null;
        List<Consignor> list = new ArrayList<Consignor>();
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
                Consignor consignor=new Consignor(id,password,name,address,tel);
                list.add(consignor);
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
    public boolean deleteConsignor(int id) {
        String sql="delete from consignor where id="+id;
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

    public boolean updateConsignor(Consignor consignor) {
        String sql = "update consignor set name=?,address=?,tel=?,password=? where id=?";
        Connection conn = BaseDaoImpl.getConn();
        PreparedStatement psts=null;
        try {
            psts = conn.prepareStatement(sql);
            psts.setObject(1, consignor.getName());
            psts.setObject(2, consignor.getAddress());
            psts.setObject(3, consignor.getTel());
            psts.setObject(4, consignor.getPassword());
            psts.setObject(5, consignor.getId());
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

    //修改用户名
    public boolean updConsignorName(int id, String name) {
        String sql = "update consignor set name=? where id="+id;
        Connection conn = BaseDaoImpl.getConn();
        PreparedStatement psts=null;
        try {
            psts = conn.prepareStatement(sql);
            psts.setObject(1, name);
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

    //修改用户密码
//    public boolean updConsignoePassword(int id, String password,String str) {
//        String sql = "update "+str+" set password=? where id="+id;
//        Connection conn = BaseDaoImpl.getConn();
//        PreparedStatement psts=null;
//        try {
//            psts = conn.prepareStatement(sql);
//            psts.setObject(1, password);
//            int res = psts.executeUpdate();
//            if(res > 0) return true;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally{
//            try {
//                conn.close();//关闭连接
//                psts.close();//关闭预编译
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return false;
//    }

}