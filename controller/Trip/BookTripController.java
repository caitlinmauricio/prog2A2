package controller.Trip;

import java.io.IOException;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Trip;
import model.Exceptions.DuplicateItemException;
import model.Exceptions.ErrorModel;
import model.Exceptions.InsufficientDestinationsException;

public class BookTripController extends Controller<Trip> {

    public final Trip getTrip() { return model; }

    @FXML private Label tripLbl;

    @FXML
    private void initialize() {
        String admin = getTrip().getAgency().getLoggedInUser().getName();
        tripLbl.setText("Hi "+ admin + ", welcome to the Trip section");
    }

    public void handleDisplay(ActionEvent e) {
        Stage displayStage = new Stage();
        displayStage.getIcons().add(new Image("/image/trip_icon.png")); 
        try {
            ViewLoader.showStage(getTrip(), "/view/Trip/DisplayTripView.fxml", "Display Trip", displayStage);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public void handleAdd(ActionEvent e) {
        Stage displayStage = new Stage();
        displayStage.getIcons().add(new Image("/image/trip_icon.png")); 
        try {
            ViewLoader.showStage(getTrip().getDestinations(), "/view/Destinations/AddDestinationView.fxml", "Add Destination To Trip", displayStage);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public void handleRemove(ActionEvent e) {
        Stage displayStage = new Stage();
        displayStage.getIcons().add(new Image("/image/trip_icon.png")); 
        try {
            ViewLoader.showStage(getTrip().getDestinations(), "/view/Destinations/RemoveDestinationView.fxml", "Remove Destination To Trip", displayStage);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public void handleConnect(ActionEvent e) {
        Stage errorStage = new Stage();
        errorStage.getIcons().add(new Image("/image/error_icon.png"));
        try {
            model.addConnectingFlights();
            
        } catch (DuplicateItemException e1) {
            try {
                String message = "Duplicate destinations";
                ViewLoader.showStage(new ErrorModel(e1, message), "/view/Error/ErrorView.fxml", "Error", errorStage);
            } catch (IOException e2) {
                // TODO Auto-generated catch block
                e2.printStackTrace();
            }
        } catch (InsufficientDestinationsException e1) {
            try {
                String message = "Not enough destinations";
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
