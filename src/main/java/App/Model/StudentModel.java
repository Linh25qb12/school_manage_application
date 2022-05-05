package App.Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
public class StudentModel {
    private SimpleIntegerProperty studentId;
    private SimpleStringProperty studentName;
    private SimpleStringProperty studentClass;
    private SimpleIntegerProperty studentAge;
    private SimpleStringProperty studentGender;


    public StudentModel(Integer studentId, String studentName, String studentClass, Integer studentAge, String studentGender) {
        super();
        this.studentId = new SimpleIntegerProperty(studentId);
        this.studentName = new SimpleStringProperty(studentName);
        this.studentClass = new SimpleStringProperty(studentClass);
        this.studentAge = new SimpleIntegerProperty(studentAge);
        this.studentGender = new SimpleStringProperty(studentGender);
    }

    public StudentModel(){

    }

    public int getStudentId() {
        return studentId.get();
    }

    public void setStudentId(int studentId) {
        this.studentId = new SimpleIntegerProperty(studentId);
    }

    public String getStudentName() {
        return studentName.get();
    }

    public void setStudentName(String studentName) {
        this.studentName = new SimpleStringProperty(studentName);
    }

    public String getStudentClass() {
        return studentClass.get();
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = new SimpleStringProperty(studentClass);
    }

    public int getStudentAge() {
        return studentAge.get();
    }

    public void setStudentAge(int studentAge) {
        this.studentAge = new SimpleIntegerProperty(studentAge);
    }

    public String getStudentGender() {
        return studentGender.get();
    }

    public void setStudentGender(String studentGender) {
        this.studentGender = new SimpleStringProperty(studentGender);
    }

    @Override
    public String toString(){
        return getStudentId()+"/"+getStudentName()+"/"+getStudentClass()+"/"+getStudentAge()+"/"+getStudentGender();
    }
}
