package controller.Destinations;

import java.io.IOException;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Destination;
import model.Destinations;
import model.Exceptions.DuplicateItemException;
import model.Exceptions.ErrorModel;
import model.Exceptions.ItemNotFoundException;

public class ModifyDestinationsController extends Controller<Destinations>{

    public final Destinations getDestinations() { return model; }

    @FXML private Button modifyBtn;
    @FXML private TextField nameTf;
    @FXML private TextField countryTf;
    @FXML private GridPane grid;

    @FXML
    private void initialize() {
        grid.setVgap(10);
        grid.setHgap(0);
        modifyBtn.disableProperty().bind(Bindings.or(
            nameTf.textProperty().isEmpty(),
            countryTf.textProperty().isEmpty()
        ));
        System.out.println(model);
    }

    private String getName() {
        return nameTf.getText();
    }

    private String getCountry() {
        return countryTf.getText();
    }

    public void handleAdd(ActionEvent e) {
        try {
            Destination destination = new Destination(getName(), getCountry());
            model.addDestination(destination);
            stage.close();
        } catch (DuplicateItemException e1) {
            Stage errorStage = new Stage();
            errorStage.getIcons().add(new Image("/image/error_icon.png")); 
            try {
                String message = "Destination already exists";
                ViewLoader.showStage(new ErrorModel(e1, message), "/view/Error/ErrorView.fxml", "Error", errorStage);
            } catch (IOException e2) {
                // TODO Auto-generated catch block
                e2.printStackTrace();
            }
        }
    }

    public void handleRemove() {
        try {
            Destination destination = model.destination(getName(), getCountry());
            model.removeDestination(destination);
            stage.close();
        } catch (ItemNotFoundException e1) {
            Stage errorStage = new Stage();
            errorStage.getIcons().add(new Image("/image/error_icon.png")); 
            try {
                String message = "Destination does not exist";
                ViewLoader.showStage(new ErrorModel(e1, message), "/view/Error/ErrorView.fxml", "Error", errorStage);
            } catch (IOException e2) {
                // TODO Auto-generated catch block
                e2.printStackTrace();
            }
        }
    }
    
    public void handleExit(ActionEvent e) {
        stage.close();
    }
    
}
