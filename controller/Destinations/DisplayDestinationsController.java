package controller.Destinations;

import au.edu.uts.ap.javafx.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Destination;
import model.Destinations;

public class DisplayDestinationsController  extends Controller<Destinations>{

    public Destinations getDestinations() { return model; }

    @FXML private TableView<Destination> destinationsTv;
    @FXML private TextField countryTf;

    @FXML
    private void initialize() {
        destinationsTv.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        destinationsTv.setItems(model.getDestinations());
        if (countryTf != null) {
            countryTf.textProperty().addListener(
                (observable, oldCountry, newCountry) ->
                destinationsTv.setItems(model.getFilteredDestinations(newCountry)));
        }
    }
    
    public void handleExit(ActionEvent e) {
        stage.close();
    }
    
}
