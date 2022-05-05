package App.Controllers;


import App.Main;

public class TimetableController {
    Main m = new Main();
    public void backHome(){
        m.changeScene("/View/Home.fxml");
    }
}
