package Chaoxing_Project;
//创建数据库公共操作：驱动加载、连接创建、资源释放（数据库工具类）
import java.sql.*;

public class JDBCUtils {
    //方法一、加载驱动、创建连接
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/student?characterEncoding=utf-8&serverTimezone=UTC";
        Connection conn = DriverManager.getConnection(url,"root","wzxdmm20021024");
        return conn;
    }

    //方法二、关闭数据库的资源
    public static void releas(Statement stmt, Connection conn) throws SQLException {
        if(stmt != null){
            stmt.close();
            stmt = null;
        }
        if(conn != null){
            conn.close();
            conn = null;
        }
    }

    public static void releas(ResultSet rs, Statement stmt, Connection conn) throws SQLException {
        if(rs != null){
            rs.close();
            rs = null;
        }
        releas(stmt,conn);
    }
}
