package controller;

import java.io.IOException;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Administrator;
import model.Agency;
import model.Exceptions.ErrorModel;
import model.Exceptions.InvalidCredentialsException;

public class LoginController extends Controller<Agency>  {

    public final Agency getAgency() { return model; }

    @FXML private TextField usernameTf;
    @FXML private PasswordField passwordTf;
    @FXML private Button loginBtn;

    @FXML
    private void initialize() {
        loginBtn.disableProperty().bind(Bindings.or(
            usernameTf.textProperty().isEmpty(),
            passwordTf.textProperty().isEmpty()
        ));
    }
    
    private String getUsername() {
        return usernameTf.getText();
    }

    private String getPassword() {
        return passwordTf.getText();
    }

    private void setUsername() {
        usernameTf.setText("");
    }

    private void setPassword() {
        passwordTf.setText("");
    }

    public void handleLogin(ActionEvent e){
        String username = getUsername();
        String password = getPassword();
        Stage agencyStage = new Stage();
        agencyStage.getIcons().add(new Image("/image/agency_icon.png")); 
        try {
            if (getAgency().getAdministrators().hasAdministrator(username, password)) {
                try {
                    Administrator admin = getAgency().getAdministrators().getAdministrator(username, password);
                    getAgency().setLoggedInUser(admin);
                    ViewLoader.showStage(getAgency(), "/view/AgencyView.fxml", "Prog 2 Travel Agency", agencyStage);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                stage.close();
            } 
        } catch (InvalidCredentialsException e1) {
            // error window
            Stage errorStage = new Stage();
            errorStage.getIcons().add(new Image("/image/error_icon.png")); 
            try {
                String message = "incorrect login credentials";
                ViewLoader.showStage(new ErrorModel(e1, message), "/view/Error/ErrorView.fxml", "Error", errorStage);
            } catch (IOException e2) {
                // TODO Auto-generated catch block
                e2.printStackTrace();
            }
            setUsername();
            setPassword();
        }
    }

    public void handleExit(ActionEvent e) {
        stage.close();
    }


}
