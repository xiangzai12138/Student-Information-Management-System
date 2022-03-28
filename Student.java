package Chaoxing_Project;
//封装类，成员变量

public class Student {
    private String no;
    private String name;
    private String sex;
    private String birthday;
    private String nativePlace;
    private String department;
    private String classNo;

    public Student(String no, String name, String sex, String birthday, String nativePlace, String department, String classNo) {
        this.no = no;
        this.name = name;
        this.sex = sex;
        this.birthday = birthday;
        this.nativePlace = nativePlace;
        this.department = department;
        this.classNo = classNo;
    }

    @Override
    public String toString() {
        return "Student{" +
                "no='" + no + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                ", nativePlace='" + nativePlace + '\'' +
                ", department='" + department + '\'' +
                ", classNo='" + classNo + '\'' +
                '}';
    }

    public Student(){
    }

    public String getNo() {
        return no;
    }
    public void setNo(String no) {
        this.no = no;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getBirthday() {
        return birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public String getNativePlace() {
        return nativePlace;
    }
    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }

    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }
}
