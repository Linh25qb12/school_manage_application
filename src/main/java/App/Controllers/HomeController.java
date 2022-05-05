package App.Controllers;

import App.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ResourceBundle;


public class HomeController implements Initializable {

    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnStudents;

    @FXML
    private Button btnTimetable;

    @FXML
    private Button btnLogout;


    @FXML
    private void handleClickEvents(ActionEvent mouseEvent){

        Main m = new Main();

        if(mouseEvent.getSource() == btnDashboard){
            m.changeScene("/View/Dashboard.fxml");
        }else
        if(mouseEvent.getSource() == btnStudents){
            m.changeScene("/View/Student.fxml");
        }else
        if(mouseEvent.getSource() == btnTimetable){
            m.changeScene("/View/Timetable.fxml");
        }else
        if(mouseEvent.getSource() == btnLogout){
            m.changeScene("/View/Login.fxml");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
