package controller.Flights;

import java.io.IOException;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Flights;

public class ExploreFlightsController extends Controller<Flights>{

    public final Flights getFlights() { return model; }

    @FXML private Label flightsLbl;

    @FXML
    private void initialize() {
        String admin = getFlights().getAgency().getLoggedInUser().getName();
        flightsLbl.setText("Hi "+ admin + ", welcome to the Flights section");
    }

    public void handleDisplay(ActionEvent e) {
        Stage displayStage = new Stage();
        displayStage.getIcons().add(new Image("/image/flights_icon.png")); 
        try {
            ViewLoader.showStage(getFlights(), "/view/Flights/DisplayFlightsView.fxml", "Display Flights", displayStage);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public void handleFiltered(ActionEvent e) {
        Stage displayStage = new Stage();
        displayStage.getIcons().add(new Image("/image/flights_icon.png")); 
        try {
            ViewLoader.showStage(getFlights(), "/view/Flights/DisplayFilteredFlightsView.fxml", "Display Flights Filtered", displayStage);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public void handleAdd(ActionEvent e) {
        Stage displayStage = new Stage();
        displayStage.getIcons().add(new Image("/image/flights_icon.png")); 
        try {
            ViewLoader.showStage(getFlights(), "/view/Flights/AddFlightView.fxml", "Add Flight", displayStage);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public void handleRemove(ActionEvent e) {
        Stage displayStage = new Stage();
        displayStage.getIcons().add(new Image("/image/flights_icon.png")); 
        try {
            ViewLoader.showStage(getFlights(), "/view/Flights/RemoveFlightView.fxml", "Remove Flight", displayStage);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public void handleExit(ActionEvent e) {
        stage.close();
    }

}
