package controller.Destinations;

import java.io.IOException;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Destinations;

public class ExploreDestinationsController extends Controller<Destinations>{

    public Destinations getDestinations() { return model; }

    @FXML private Label destLbl;

    @FXML
    private void initialize() {
        String admin = getDestinations().getAgency().getLoggedInUser().getName();
        destLbl.setText("Hi "+ admin + ", welcome to the Destinations section");
    }

    public void handleDisplay(ActionEvent e) {
        Stage displayStage = new Stage();
        displayStage.getIcons().add(new Image("/image/destinations_icon.png")); 
        try {
            ViewLoader.showStage(getDestinations(), "/view/Destinations/DisplayDestinationsView.fxml", "Display Destinations", displayStage);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public void handleFiltered(ActionEvent e) {
        Stage displayStage = new Stage();
        displayStage.getIcons().add(new Image("/image/destinations_icon.png")); 
        try {
            ViewLoader.showStage(getDestinations(), "/view/Destinations/DisplayFilteredDestinationsView.fxml", "Display Destinations Filtered", displayStage);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public void handleAdd(ActionEvent e) {
        Stage displayStage = new Stage();
        displayStage.getIcons().add(new Image("/image/destinations_icon.png")); 
        try {
            ViewLoader.showStage(getDestinations(), "/view/Destinations/AddDestinationView.fxml", "Add Destination", displayStage);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public void handleRemove(ActionEvent e) {
        Stage displayStage = new Stage();
        displayStage.getIcons().add(new Image("/image/destinations_icon.png")); 
        try {
            ViewLoader.showStage(getDestinations(), "/view/Destinations/RemoveDestinationView.fxml", "Remove Destination", displayStage);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public void handleExit(ActionEvent e) {
        stage.close();
    }

}
