package App.Controllers;

import App.Main;
import App.Model.StudentModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    @FXML
    private TableView<StudentModel> tbData;
    @FXML
    public TableColumn<StudentModel, Integer> studentId;

    @FXML
    public TableColumn<StudentModel, String> studentName;

    @FXML
    public TableColumn<StudentModel, String> studentClass;

    @FXML
    private PieChart pieChart;

    @FXML
    private Label totalStu;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        studentId.setCellValueFactory(new PropertyValueFactory<>("StudentId"));
        studentName.setCellValueFactory(new PropertyValueFactory<>("StudentName"));
        studentClass.setCellValueFactory(new PropertyValueFactory<>("StudentClass"));
        loadChart();
        tbData.setItems(readStudentInfo());
        totalStu.setText(String.valueOf(students.size()));

    }

    Main m = new Main();

    ObservableList<StudentModel> students = FXCollections.observableArrayList();

    public ObservableList<StudentModel> readStudentInfo() {

        try {
            FileReader fr = new FileReader("data.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while (true){
                line = br.readLine();
                if(line == null){
                    break;
                }
                String txt[] = line.split("/");
                int studentId = Integer.parseInt(txt[0]);
                String studentName = txt[1];
                String studentClass = txt[2];
                int studentAge = Integer.parseInt(txt[3]);
                String studentGender = txt[4];
                students.add(new StudentModel(studentId,
                        studentName,
                        studentClass,
                        studentAge,
                        studentGender));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    public void viewStudentsDetail(){
        m.changeScene("/View/Student.fxml");
    }

    private void loadChart()
    {

        PieChart.Data slice1 = new PieChart.Data("Classes", 213);
        PieChart.Data slice2 = new PieChart.Data("Attendance"  , 67);
        PieChart.Data slice3 = new PieChart.Data("Teachers" , 36);

        pieChart.getData().add(slice1);
        pieChart.getData().add(slice2);
        pieChart.getData().add(slice3);

    }

    public void backHome(){
        m.changeScene("/View/Home.fxml");
    }

}
