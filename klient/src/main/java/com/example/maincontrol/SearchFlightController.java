package com.example.maincontrol;

import com.example.model.communication.AirportRequest;
import com.example.model.communication.AirportResponse;
import com.example.model.communication.ListFlightRequest;
import com.example.model.communication.ListFlightResponse;
import com.example.model.database.Airport;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Klasa obsługująca wyszukiwanie lotów.
 */
@Controller
@Component
public class SearchFlightController implements InitializingBean, Initializable {
    Logger logger = LoggerFactory.getLogger(SearchFlightController.class);
    @Autowired
    MyAppController myAppController;

    @Autowired
    SpringFxmlLoader springFxmlLoader;

    @Autowired
    ClientControl clientControl;


    @FXML
    private JFXDatePicker departureDateLabel;

    @FXML
    private ChoiceBox<String> destinationLabel;

    @FXML
    private JFXDatePicker arrivalDateLabel;
    @FXML
    private ChoiceBox<String> fromLabel;
    public JFXButton buttonUser;


    /**
     * Metoda obłsługuje przycisk do wyszukiwania lotów
     * @param actionEvent
     */
    public void searchButton(javafx.event.ActionEvent actionEvent) {
        if (!myAppController.getMainLoad().getChildren().isEmpty()) {
            myAppController.getMainLoad().getChildren().clear();
        }

        ListFlightRequest request = new ListFlightRequest();


        request.setDestination(destinationLabel.getValue());
        request.setOrigin(fromLabel.getValue());
        request.setDepartureDate(Date.from(departureDateLabel.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        request.setArrivalDate(Date.from(arrivalDateLabel.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        ListFlightResponse listFlightResponse = clientControl.listFlights(request);
        AnchorPane root = loadUi("/tableFlights", listFlightResponse);
        AnchorPane.setRightAnchor(root, 15d);
        myAppController.getMainLoad().getChildren().add(root);

    }

    @Override
    public void afterPropertiesSet() throws Exception {

        logger.info("SearchFlightController initialized");
    }

    public void fromPickerLabel(MouseEvent mouseEvent) {

    }

    public void toPickLabel(MouseEvent mouseEvent) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            ObservableList<String> listCity = FXCollections.observableArrayList();
            AirportRequest request = new AirportRequest();
            AirportResponse airportResponse = clientControl.getAirports(request);
            Iterable<Airport> cityAirport = airportResponse.getListOfAirport();
            logger.info(airportResponse.getStatus());
            for (Airport t : cityAirport) {
                listCity.add(t.getCity());
            }

            fromLabel.setItems(listCity);
            destinationLabel.setItems(listCity);
        }catch (Exception e)
        {
            e.printStackTrace(System.out);
            logger.error("SERVER DOWN");
        }
    }

    private AnchorPane loadUi(String ui, ListFlightResponse listFlightResponse) {
        return (AnchorPane) springFxmlLoader.load(ui + ".fxml", listFlightResponse);
    }
}
