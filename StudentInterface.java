package Chaoxing_Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StudentInterface extends JFrame implements ActionListener {
    StudentDao dao = new StudentDao();
    JLabel no = new JLabel("学号");
    JLabel name = new JLabel("姓名");
    JLabel sex = new JLabel("性别");
    JLabel birthday = new JLabel("出生日期");
    JLabel notice = new JLabel("注意：出生日期输入格式为YYYY/MM/DD");
    JLabel nativePlace = new JLabel("籍贯");
    JLabel department = new JLabel("系号");
    JLabel classNo = new JLabel("班级");

    JTextField no_text = new JTextField();
    JTextField name_text = new JTextField();
    JTextField birthday_text = new JTextField();
    JTextField nativePlace_text = new JTextField();
    JTextField department_text = new JTextField();
    JTextField classNo_text = new JTextField();

    JRadioButton male = new JRadioButton("男", true);
    JRadioButton female = new JRadioButton("女");
    ButtonGroup group = new ButtonGroup();

    JButton save = new JButton("存入数据库");
    JButton search = new JButton("查询");

    Container con;

    public StudentInterface() throws HeadlessException {
        setTitle("学生信息录入系统");
        setSize(500, 380);
        con = this.getContentPane();
        con.setLayout(null);
        no.setBounds(70,20,30,25);
        no_text.setBounds(150, 20,250,25);

        name.setBounds(70,50,30,25);
        name_text.setBounds(150, 50,250,25);

        sex.setBounds(70,80,30,25);
        male.setBounds(150,80,50,25);
        female.setBounds(200,80,50,25);
        group.add(male); group.add(female);

        birthday.setBounds(70,110,50,25);
        birthday_text.setBounds(150,110,250,25);

        notice.setBounds(150,140,250,25);

        nativePlace.setBounds(70,170,30,25);
        nativePlace_text.setBounds(150,170,250,25);

        department.setBounds(70,200,30,25);
        department_text.setBounds(150,200,250,25);

        classNo.setBounds(70,230,30,25);
        classNo_text.setBounds(150,230,250,25);

        search.setBounds(60,280,100,30);
        save.setBounds(210,280,200,30);


        con.add(no); con.add(no_text);
        con.add(name); con.add(name_text);
        con.add(sex); con.add(male); con.add(female);
        con.add(birthday); con.add(birthday_text);
        con.add(notice);
        con.add(nativePlace); con.add(nativePlace_text);
        con.add(department); con.add(department_text);
        con.add(classNo); con.add(classNo_text);
        con.add(save); con.add(search);

        search.addActionListener(this);
        save.addActionListener(this);

        setResizable(false);
        setVisible(true);
    }

    public void clear_text(){
        //清空
        no_text.setText("");
        name_text.setText("");
        birthday_text.setText("");
        nativePlace_text.setText("");
        department_text.setText("");
        classNo_text.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == search){
            String no = no_text.getText();
            boolean judge = false;
            ArrayList<Student> list = dao.queryAll();
            //判断待插入的学生是否存在
            for(int i = 0; i < list.size(); i++){
                if(no.equals((list.get(i).getNo()))){
                    judge = true;
                    break;
                }
            }
            if(judge){
                Student stu = new StudentDao().search_student(no);

                name_text.setText(stu.getName());
                if(stu.getSex().equals("男")){
                    male.setSelected(true);
                }
                else{
                    female.setSelected(true);
                }
                birthday_text.setText(stu.getBirthday());
                nativePlace_text.setText(stu.getNativePlace());
                department_text.setText(stu.getDepartment());
                classNo_text.setText(stu.getClassNo());
                JOptionPane.showMessageDialog(this, "查询成功！！！");
            }
            else {
                JOptionPane.showMessageDialog(this, "查无此人！！！");
            }
        }

        if(e.getSource() == save){
            //获取学生信息
            String no = no_text.getText();
            String name = name_text.getText();
            String sex;
            if(male.isSelected()){
                sex = "男";
            }
            else {
                sex = "女";
            }
            String birthday = birthday_text.getText();
            String nativePlace = nativePlace_text.getText();
            String department = department_text.getText();
            String classNo = classNo_text.getText();
            //创建学生对象
            Student s = new Student(no, name, sex, birthday, nativePlace, department, classNo);
            //调用StudentDao中查询全部学生信息的方法，获得信息
            boolean judge = false;
            ArrayList<Student> list = dao.queryAll();
            //判断待插入的学生是否存在
            for(int i = 0; i < list.size(); i++){
                if(no.equals((list.get(i).getNo()))){
                    judge = true;
                    break;
                }
            }
            if( !judge){
                dao.addStudent(s);
                JOptionPane.showMessageDialog(this, "插入成功！！！");
            }
            else {
                JOptionPane.showMessageDialog(this, "学号不能重复！！！");
            }
            clear_text();
        }
    }
}
