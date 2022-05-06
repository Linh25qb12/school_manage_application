package App.Controllers;

import App.Main;
import App.Model.StudentModel;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.paint.Color;
import javafx.util.converter.IntegerStringConverter;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;


public class StudentController implements Initializable {

    @FXML
    private TableView<StudentModel> tbData;

    @FXML
    public TableColumn<StudentModel, Integer> studentId;

    @FXML
    public TableColumn<StudentModel, String> studentName;

    @FXML
    public TableColumn<StudentModel, String> studentClass;

    @FXML
    public TableColumn<StudentModel, Integer> studentAge;

    @FXML
    public TableColumn<StudentModel, String> studentGender;

    @FXML
    private TextField txtAreaId;

    @FXML
    private TextField txtAreaName;

    @FXML
    private TextField txtAreaClass;

    @FXML
    private TextField txtAreaAge;

    @FXML
    private TextField txtAreaSearch;

    @FXML
    private Label wrongInput;

    @FXML
    private FontAwesomeIconView offFilter;

    @FXML
    private ChoiceBox<String> chbxGender;


    private String[] gender = {"Male", "Female"};

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        studentId.setCellValueFactory(new PropertyValueFactory<>("StudentId"));
        studentName.setCellValueFactory(new PropertyValueFactory<>("StudentName"));
        studentClass.setCellValueFactory(new PropertyValueFactory<>("StudentClass"));
        studentAge.setCellValueFactory(new PropertyValueFactory<>("StudentAge"));
        studentGender.setCellValueFactory(new PropertyValueFactory<>("StudentGender"));
        tbData.setEditable(true);
        chbxGender.getItems().addAll(gender);
        tbData.setItems(readStudentInfo());
        studentName.setCellFactory(TextFieldTableCell.forTableColumn());
        studentClass.setCellFactory(TextFieldTableCell.forTableColumn());
        studentAge.setCellFactory(TextFieldTableCell.forTableColumn((new IntegerStringConverter())));
    }

    Main m = new Main();

    ObservableList<StudentModel> studentsModels = FXCollections.observableArrayList();
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
                studentsModels.add(new StudentModel(studentId,
                                        studentName,
                                        studentClass,
                                        studentAge,
                                        studentGender));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentsModels;
    }

    public void addNewStudent(){
        try{
            StudentModel newStudent = new StudentModel(Integer.parseInt(txtAreaId.getText()),
                    txtAreaName.getText(),
                    txtAreaClass.getText(),
                    Integer.parseInt(txtAreaAge.getText()),
                    chbxGender.getValue());
            try{
                FileWriter fw = new FileWriter("data.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(newStudent.toString());
                bw.newLine();
                bw.close();
                fw.close();
            }catch (Exception e){

            }
            tbData.getItems().add(newStudent);
        }catch (IllegalArgumentException e){
            wrongInput.setText("Wrong input format ");
        }

    }

    public void backHome(){
        m.changeScene("/View/Home.fxml");
    }

    public void offFilter(){
        tbData.setItems(studentsModels);
        offFilter.setFill(Color.rgb(45,117,232));
    }

    public void changeNameCell(TableColumn.CellEditEvent editEvent){
        StudentModel studentSelected = tbData.getSelectionModel().getSelectedItem();
        studentSelected.setStudentName(editEvent.getNewValue().toString());
        writeAllStudentInfo();

    }

    public void changeClassCell(TableColumn.CellEditEvent editEvent){
        StudentModel studentSelected = tbData.getSelectionModel().getSelectedItem();
        studentSelected.setStudentClass(editEvent.getNewValue().toString());
        writeAllStudentInfo();
    }

    public void changeAgeCell(TableColumn.CellEditEvent editEvent){
        StudentModel studentSelected = tbData.getSelectionModel().getSelectedItem();
        studentSelected.setStudentAge((Integer)editEvent.getNewValue());
        writeAllStudentInfo();
    }

    public void writeAllStudentInfo(){
        try{
            FileWriter fw = new FileWriter("data.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for(StudentModel stu : studentsModels){
                bw.write(stu.toString());
                bw.newLine();
            }
            bw.close();
            fw.close();
        }catch (Exception e){

        }
    }

    public void deleteStudent() {
        StudentModel selectedRow;
        selectedRow = tbData.getSelectionModel().getSelectedItem();
        studentsModels.remove(selectedRow);
        writeAllStudentInfo();
    }

    public void searchStudentByName(){
        String search = txtAreaSearch.getText();
        List<StudentModel> searchResult = studentsModels.stream()
                .filter(stu -> stu.getStudentName().contains(search))
                .collect(Collectors.toList());

        ObservableList<StudentModel> searchResultConverted = FXCollections.observableList(searchResult);
        offFilter.setFill(Color.BLACK);
        tbData.setItems(searchResultConverted);
    }

}
