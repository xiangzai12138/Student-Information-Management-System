package Chaoxing_Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StudentInterface extends JFrame implements ActionListener {
    StudentDao dao = new StudentDao();
    JLabel no = new JLabel("ѧ��");
    JLabel name = new JLabel("����");
    JLabel sex = new JLabel("�Ա�");
    JLabel birthday = new JLabel("��������");
    JLabel notice = new JLabel("ע�⣺�������������ʽΪYYYY/MM/DD");
    JLabel nativePlace = new JLabel("����");
    JLabel department = new JLabel("ϵ��");
    JLabel classNo = new JLabel("�༶");

    JTextField no_text = new JTextField();
    JTextField name_text = new JTextField();
    JTextField birthday_text = new JTextField();
    JTextField nativePlace_text = new JTextField();
    JTextField department_text = new JTextField();
    JTextField classNo_text = new JTextField();

    JRadioButton male = new JRadioButton("��", true);
    JRadioButton female = new JRadioButton("Ů");
    ButtonGroup group = new ButtonGroup();

    JButton save = new JButton("�������ݿ�");
    JButton search = new JButton("��ѯ");

    Container con;

    public StudentInterface() throws HeadlessException {
        setTitle("ѧ����Ϣ¼��ϵͳ");
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
        //���
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
            //�жϴ������ѧ���Ƿ����
            for(int i = 0; i < list.size(); i++){
                if(no.equals((list.get(i).getNo()))){
                    judge = true;
                    break;
                }
            }
            if(judge){
                Student stu = new StudentDao().search_student(no);

                name_text.setText(stu.getName());
                if(stu.getSex().equals("��")){
                    male.setSelected(true);
                }
                else{
                    female.setSelected(true);
                }
                birthday_text.setText(stu.getBirthday());
                nativePlace_text.setText(stu.getNativePlace());
                department_text.setText(stu.getDepartment());
                classNo_text.setText(stu.getClassNo());
                JOptionPane.showMessageDialog(this, "��ѯ�ɹ�������");
            }
            else {
                JOptionPane.showMessageDialog(this, "���޴��ˣ�����");
            }
        }

        if(e.getSource() == save){
            //��ȡѧ����Ϣ
            String no = no_text.getText();
            String name = name_text.getText();
            String sex;
            if(male.isSelected()){
                sex = "��";
            }
            else {
                sex = "Ů";
            }
            String birthday = birthday_text.getText();
            String nativePlace = nativePlace_text.getText();
            String department = department_text.getText();
            String classNo = classNo_text.getText();
            //����ѧ������
            Student s = new Student(no, name, sex, birthday, nativePlace, department, classNo);
            //����StudentDao�в�ѯȫ��ѧ����Ϣ�ķ����������Ϣ
            boolean judge = false;
            ArrayList<Student> list = dao.queryAll();
            //�жϴ������ѧ���Ƿ����
            for(int i = 0; i < list.size(); i++){
                if(no.equals((list.get(i).getNo()))){
                    judge = true;
                    break;
                }
            }
            if( !judge){
                dao.addStudent(s);
                JOptionPane.showMessageDialog(this, "����ɹ�������");
            }
            else {
                JOptionPane.showMessageDialog(this, "ѧ�Ų����ظ�������");
            }
            clear_text();
        }
    }
}
