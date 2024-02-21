package controller;

import java.io.IOException;

import au.edu.uts.ap.javafx.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Agency;
import model.Trip;

public class AgencyController extends Controller<Agency>  {

    public final Agency getAgency() { return model; }

    @FXML private Label agencyLbl;
    
    @FXML
    private void initialize() {
        String admin = getAgency().getLoggedInUser().getName();
        agencyLbl.setText("Hi "+ admin + ", welcome to the Prog2 Travel Agency");
    }

    public void handleFlights(ActionEvent e){
        Stage flightStage = new Stage();
        flightStage.getIcons().add(new Image("/image/flights_icon.png")); 
        try {
            ViewLoader.showStage(getAgency().getFlights(), "/view/Flights/ExploreFlightsView.fxml", "Explore Flights", flightStage);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void handleDest(ActionEvent e) {
        Stage destStage = new Stage();
        destStage.getIcons().add(new Image("/image/destinations_icon.png")); 
        try {
            ViewLoader.showStage(getAgency().getDestinations(), "/view/Destinations/ExploreDestinationsView.fxml", "Explore Destinations", destStage);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public void handleTrip(ActionEvent e) {
        Stage tripStage = new Stage();
        tripStage.getIcons().add(new Image("/image/trip_icon.png")); 
        try {
            ViewLoader.showStage(new Trip(getAgency()), "/view/Trip/BookTripView.fxml", "Book a Trip", tripStage);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public void handleExit(ActionEvent e) {
        stage.close();
    }


}
