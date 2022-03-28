package Chaoxing_Project;
//提供数据库操作：增删改查（数据库操作类）

import java.sql.*;
import java.util.ArrayList;

public class StudentDao {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

////////////////////查询符合条件的学生
    public Student search_student(String No){
        Student s = new Student();
        try {
            conn = JDBCUtils.getConnection();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from student");
            while(rs.next()){
                if(rs.getString(1).equals(No)){
                    s = new Student();
                    s.setNo(rs.getString(1));
                    s.setName(rs.getString(2));
                    s.setSex(rs.getString(3));
                    s.setBirthday(rs.getString(4));
                    s.setNativePlace(rs.getString(5));
                    s.setDepartment(rs.getString(6));
                    s.setClassNo(rs.getString(7));
                }
            }
            JDBCUtils.releas(rs, stmt, conn);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }

    //一、查询
    public ArrayList<Student> queryAll(){
        ArrayList<Student> list = new ArrayList<Student>();
        try {
            //1、获得数据库连接
            conn = JDBCUtils.getConnection();
            //2、创建SQL语句执行对象
            stmt = conn.createStatement();
            //3、执行SQL语句
            ResultSet rs = stmt.executeQuery("select * from student");
            //4、处理结果
            Student s;
            while(rs.next()){
                s = new Student();
                s.setNo(rs.getString(1));
                s.setName(rs.getString(2));
                s.setSex(rs.getString(3));
                s.setBirthday(rs.getString(4));
                s.setNativePlace(rs.getString(5));
                s.setDepartment(rs.getString(6));
                s.setClassNo(rs.getString(7));
                list.add(s);
            }
            //5、释放资源
            JDBCUtils.releas(rs, stmt, conn);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //二、插入
    public void addStudent(Student stu){
        try {
            //1、获得数据库连接
            conn = JDBCUtils.getConnection();
            //2、创建执行对象
            //(1)、准备SQL语句
            String sql = "insert into student values(?,?,?,?,?,?,?)";
            //(2)、创建PreparedStatement对象
            ps = conn.prepareStatement(sql);
            //(3)、给参数赋值
            ps.setString(1, stu.getNo());
            ps.setString(2,stu.getName());
            ps.setString(3,stu.getSex());
            ps.setString(4, stu.getBirthday());
            ps.setString(5,stu.getNativePlace());
            ps.setString(6,stu.getDepartment());
            ps.setString(7,stu.getClassNo());
            //(4)、执行SQL命令语句
            int count = ps.executeUpdate();
            if(count > 0){
                System.out.println("插入成功！！！");
            }
            else {
                System.out.println("插入失败！！！");
            }
            //3、释放资源
            JDBCUtils.releas(stmt, conn);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
