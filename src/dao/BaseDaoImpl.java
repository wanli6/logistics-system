package dao;

import com.mysql.cj.util.Base64Decoder;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDaoImpl {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/logistics";
    private static final String NAME = "root";
    private static final String pswdBase64 = "d2wwMTA1MDYu";
    private static String PASSWORD = null;
    static {
        try {
            PASSWORD = new String((new BASE64Decoder()).decodeBuffer(pswdBase64));
            Class.forName(DRIVER);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //获得连接
    public static Connection getConn(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL,NAME,PASSWORD);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return conn;
    }
}