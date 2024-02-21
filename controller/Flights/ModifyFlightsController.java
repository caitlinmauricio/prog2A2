package controller.Flights;

import java.io.IOException;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Flight;
import model.Flights;
import model.Exceptions.DuplicateItemException;
import model.Exceptions.ErrorModel;
import model.Exceptions.ItemNotFoundException;

public class ModifyFlightsController extends Controller<Flights> {

    public final Flights getFlights() { return model; }

    @FXML private Button modifyBtn;
    @FXML private TextField airlineTf;
    @FXML private TextField flightNoTf;
    @FXML private TextField takeoffTf;
    @FXML private TextField landingTf;
    @FXML private TextField costTf;
    @FXML private VBox fxmlAdd;
    @FXML private VBox fxmlRemove;
    @FXML private GridPane grid;

    @FXML
    public void initialize() {
        grid.setVgap(10);
        grid.setHgap(0);
        if (fxmlAdd != null) {
            BooleanBinding isAnyFieldEmpty = Bindings.createBooleanBinding(() ->
                airlineTf.getText().isEmpty() ||
                flightNoTf.getText().isEmpty() ||
                takeoffTf.getText().isEmpty() ||
                landingTf.getText().isEmpty() ||
                costTf.getText().isEmpty(),
                airlineTf.textProperty(),
                flightNoTf.textProperty(),
                takeoffTf.textProperty(),
                landingTf.textProperty(),
                costTf.textProperty()
            );
            modifyBtn.disableProperty().bind(isAnyFieldEmpty);
        } else if (fxmlRemove != null) {
            modifyBtn.disableProperty().bind(Bindings.or(
            takeoffTf.textProperty().isEmpty(),
            landingTf.textProperty().isEmpty()
        ));
        }
    }

    private String getAirline() {
        return airlineTf.getText();
    }

    private String getFlightNo() {
        return flightNoTf.getText();
    }

    private String getTakeoff() {
        return takeoffTf.getText();
    }

    private String getLanding() {
        return landingTf.getText();
    }

    private String getCost() {
        return costTf.getText();
    }

    public void handleAdd(ActionEvent e) {
        Stage errorStage = new Stage();
        errorStage.getIcons().add(new Image("/image/error_icon.png"));
        try {
            int flightNo = Integer.parseInt(getFlightNo());
            double cost = Double.parseDouble(getCost());
            try {
                Flight flight = new Flight(getAirline(), flightNo, getTakeoff(), getLanding(), cost);
                model.addFlight(flight);
                stage.close();
            } catch (DuplicateItemException e1) {
                try {
                    String message = "Flight already exists";
                    ViewLoader.showStage(new ErrorModel(e1, message), "/view/Error/ErrorView.fxml", "Error", errorStage);
                } catch (Exception e2) {
                    // TODO: handle exception
                    e2.printStackTrace();
                }
            }
        } catch (NumberFormatException e3) {
            try {
                String message = "Flight number & cost needs to be in valid format";
                ViewLoader.showStage(new ErrorModel(e3, message), "/view/Error/ErrorView.fxml", "Error", errorStage);
            } catch (Exception e4) {
                // TODO: handle exception
                e4.printStackTrace();
            }
        }
    }

    public void handleRemove(ActionEvent e) {
        try {
            Flight flight = model.getFlight(getTakeoff(), getLanding());
            model.removeFlight(flight);
            stage.close();
        } catch (ItemNotFoundException e1) {
            Stage errorStage = new Stage();
            errorStage.getIcons().add(new Image("/image/error_icon.png")); 
            try {
                String message = "Flight does not exist";
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
