package com.example.maincontrol;

import com.example.model.communication.ListFlightResponse;
import com.example.model.database.Flight;
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
import javafx.util.Callback;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


@Controller
@Component
@Scope("prototype")
public class BookFlight implements Initializable {

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

    private ListFlightResponse listFlightResponse;
    private Flight flight;

    BookFlight(ListFlightResponse listFlightResponse, Flight flight) {
        this.flight = flight;
        this.listFlightResponse = listFlightResponse;
    }


    private AnchorPane loadUi(String ui, ListFlightResponse listFlightResponse) {
        return (AnchorPane) springFxmlLoader.load(ui + ".fxml", listFlightResponse);
    }


    public void afterPropertiesSet() throws Exception {

    }

    public void backButton(MouseEvent mouseEvent) {
        if (!myAppController.getMainLoad().getChildren().isEmpty()) {
            myAppController.getMainLoad().getChildren().clear();
        }

        AnchorPane root = loadUi("/tableFlights", listFlightResponse);
        AnchorPane.setRightAnchor(root, 15d);
        myAppController.getMainLoad().getChildren().add(root);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Flight> lista =  FXCollections.observableArrayList();
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
        tableViewBook.setItems(lista);
    }

    public void payButton(MouseEvent mouseEvent) {

    }
}
