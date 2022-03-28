package Chaoxing_Project;
//�ṩ���ݿ��������ɾ�Ĳ飨���ݿ�����ࣩ

import java.sql.*;
import java.util.ArrayList;

public class StudentDao {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

////////////////////��ѯ����������ѧ��
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

    //һ����ѯ
    public ArrayList<Student> queryAll(){
        ArrayList<Student> list = new ArrayList<Student>();
        try {
            //1��������ݿ�����
            conn = JDBCUtils.getConnection();
            //2������SQL���ִ�ж���
            stmt = conn.createStatement();
            //3��ִ��SQL���
            ResultSet rs = stmt.executeQuery("select * from student");
            //4��������
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
            //5���ͷ���Դ
            JDBCUtils.releas(rs, stmt, conn);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //��������
    public void addStudent(Student stu){
        try {
            //1��������ݿ�����
            conn = JDBCUtils.getConnection();
            //2������ִ�ж���
            //(1)��׼��SQL���
            String sql = "insert into student values(?,?,?,?,?,?,?)";
            //(2)������PreparedStatement����
            ps = conn.prepareStatement(sql);
            //(3)����������ֵ
            ps.setString(1, stu.getNo());
            ps.setString(2,stu.getName());
            ps.setString(3,stu.getSex());
            ps.setString(4, stu.getBirthday());
            ps.setString(5,stu.getNativePlace());
            ps.setString(6,stu.getDepartment());
            ps.setString(7,stu.getClassNo());
            //(4)��ִ��SQL�������
            int count = ps.executeUpdate();
            if(count > 0){
                System.out.println("����ɹ�������");
            }
            else {
                System.out.println("����ʧ�ܣ�����");
            }
            //3���ͷ���Դ
            JDBCUtils.releas(stmt, conn);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
