package controller.Flights;

import au.edu.uts.ap.javafx.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Flight;
import model.Flights;

public class DisplayFlightsController extends Controller<Flights> {

    public final Flights getFlights() { return model; }

    @FXML private TableView<Flight> flightsTv;
    @FXML private TextField countryTf;

    @FXML
    private void initialize() {
        flightsTv.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        flightsTv.setItems(model.getFlights());
        if (countryTf != null) {
            countryTf.textProperty().addListener(
                (observable, oldCountry, newCountry) ->
                flightsTv.setItems(model.getFilteredFlights(newCountry)));
        }
    }

    public void handleExit(ActionEvent e) {
        stage.close();
    }
    
}
