package Chaoxing_Project;
//�������ݿ⹫���������������ء����Ӵ�������Դ�ͷţ����ݿ⹤���ࣩ
import java.sql.*;

public class JDBCUtils {
    //����һ��������������������
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/student?characterEncoding=utf-8&serverTimezone=UTC";
        Connection conn = DriverManager.getConnection(url,"root","wzxdmm20021024");
        return conn;
    }

    //���������ر����ݿ����Դ
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
