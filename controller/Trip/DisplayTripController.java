package controller.Trip;

import java.io.IOException;
import model.Exceptions.*;
import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Destination;
import model.Destinations;
import model.Flight;
import model.Flights;
import model.Itinery;
import model.Trip;
import javafx.collections.ListChangeListener;

public class DisplayTripController extends Controller<Trip> {

    public final Trip getTrip() { return model; }

    @FXML private ListView<Itinery> tripLv;
    @FXML private Button viewBtn;

    @FXML
    private void initialize() {
        tripLv.setItems(model.getItinery());
        tripLv.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tripLv.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldSelection, newSelection) ->
            viewBtn.setDisable(newSelection == null));
            
        model.getDestinations().getDestinations().addListener(new ListChangeListener<Destination>() {
            @Override
            public void onChanged(Change<? extends Destination> c) {
                while (c.next()) {
                    if (c.wasAdded() || c.wasRemoved()) {
                        tripLv.setItems(model.getItinery());
                    }
                }
            }
        });

        model.getFlights().getFlights().addListener(new ListChangeListener<Flight>() {
            @Override
            public void onChanged(Change<? extends Flight> c) {
                while (c.next()) {
                    if (c.wasAdded() || c.wasRemoved()) {
                        tripLv.setItems(model.getItinery());
                    }
                }
            }
        });
    }

    public ObservableList<Itinery> getItinery() {
        return tripLv.getSelectionModel().getSelectedItems();
    }

    public void showDestinations(Destinations d){
        Stage displayStage = new Stage();
        displayStage.getIcons().add(new Image("/image/destinations_icon.png")); 
        try {
            ViewLoader.showStage(d, "/view/Destinations/DisplayDestinationsView.fxml", "View Destinations", displayStage);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void showFlights(Flights f){
        Stage displayStage = new Stage();
        displayStage.getIcons().add(new Image("/image/flights_icon.png")); 
        try {
            ViewLoader.showStage(f, "/view/Flights/DisplayFlightsView.fxml", "View Flights", displayStage);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void handleView(ActionEvent e) {
        Stage errorStage = new Stage();
        errorStage.getIcons().add(new Image("/image/error_icon.png"));
        String firstModelName = null;
        for (Itinery i : getItinery()) {
            String modelName = i.getClass().getSimpleName();
            if (firstModelName == null) { firstModelName = modelName; }
            else if (!modelName.equals(firstModelName)) {
            }
        }

        if (firstModelName.equals("Destination")) { 
            try {
                Destinations destinations = new Destinations(getItinery());
                showDestinations(destinations); 
            } catch (ClassCastException e1) {
                try {
                    String message = "Mismatch of destinations and flights, select one type only";
                    ViewLoader.showStage(new ErrorModel(e1, message), "/view/Error/ErrorView.fxml", "Error", errorStage);
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        } 
        else if (firstModelName.equals("Flight")) { 
            try {
                Flights flights = new Flights(getItinery());
                showFlights(flights);
            } catch (ClassCastException e1) {
                try {
                    String message = "Mismatch of destinations and flights, select one type only";
                    ViewLoader.showStage(new ErrorModel(e1, message), "/view/Error/ErrorView.fxml", "Error", errorStage);
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public void handleExit(ActionEvent e) {
        stage.close();
    }

}
