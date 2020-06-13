package com.example.maincontrol;

import com.example.model.communication.CreateReservationRequest;
import com.example.model.communication.CreateReservationResponse;
import com.example.model.communication.ListFlightResponse;
import com.example.model.database.Flight;
import com.example.model.database.User;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;


@Controller
@Component
@Scope("prototype")
public class BookFlight implements Initializable {
    Logger logger = LoggerFactory.getLogger(BookFlight.class);
    @Autowired
    MyAppController myAppController;

    @Autowired
    SpringFxmlLoader springFxmlLoader;

    @Autowired
    ClientControl clientControl;

    @FXML
    private JFXTextField nameField;

    @FXML
    private TableView<Flight> tableViewBook;
    @FXML
    private TableColumn<Flight, String> cityColumnBook;

    @FXML
    private TableColumn<Flight, String> dateDepartureColumnBook;

    @FXML
    private TableColumn<Flight, Integer> priceColumnBook;

    @FXML
    private JFXTextField surnameField;

    @FXML
    private Text infoLabel;

    private ListFlightResponse listFlightResponse;

    private Flight flight;


    BookFlight(ListFlightResponse listFlightResponse, Flight flight) {
        this.flight = flight;
        this.listFlightResponse = listFlightResponse;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Flight> lista = FXCollections.observableArrayList();
        lista.add(flight);
        dateDepartureColumnBook.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Flight, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Flight, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDateDepearture());
            }
        });
        priceColumnBook.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("price"));
        cityColumnBook.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Flight, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Flight, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDestination().getCity());
            }
        });


        User loggedInUser = myAppController.getLoggedInUser();
        if (loggedInUser != null) {
            nameField.setText(loggedInUser.getName());
            surnameField.setText(loggedInUser.getSurname());
        }

        tableViewBook.setItems(lista);
    }

    public void payButton(MouseEvent mouseEvent) {

        if (nameField.getText().isEmpty() || surnameField.getText().isEmpty()) {
            infoLabel.setText("FILL ALL FIELDS");
        } else {
            infoLabel.setText("");
            CreateReservationRequest request = new CreateReservationRequest();
            User user = myAppController.getLoggedInUser();


            request.setFlightId(flight.getId());
            request.setPassengerName(nameField.getText());
            request.setPassengerSurname(surnameField.getText());
            User loggedInUser = myAppController.getLoggedInUser();
            if (loggedInUser != null) {
                request.setUser(loggedInUser);
            }

            logger.info(request.getPassengerName());
            logger.info(request.getPassengerSurname());
            CreateReservationResponse reservationResponse = clientControl.createReservation(request);
            infoLabel.setText(reservationResponse.getStatus());
        }
    }

    public void backButton(MouseEvent mouseEvent) {
        if (!myAppController.getMainLoad().getChildren().isEmpty()) {
            myAppController.getMainLoad().getChildren().clear();
        }
        AnchorPane root = loadUi("/tableFlights", listFlightResponse);
        AnchorPane.setRightAnchor(root, 15d);
        myAppController.getMainLoad().getChildren().add(root);
    }

    private AnchorPane loadUi(String ui, ListFlightResponse listFlightResponse) {
        return (AnchorPane) springFxmlLoader.load(ui + ".fxml", listFlightResponse);
    }
}
