package controller.Error;

import au.edu.uts.ap.javafx.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Exceptions.ErrorModel;

public class ErrorController extends Controller<ErrorModel>{
    public final ErrorModel getError() { return model; }

    @FXML private Label exceptionLbl;
    @FXML private Label msgLbl;

    @FXML 
    private void initialize(){
        exceptionLbl.setText("" + model.getException().getClass().getSimpleName());
        msgLbl.setText(model.getMessage());
    }

    public void handleExit(ActionEvent e) {
        stage.close();
    }
}
