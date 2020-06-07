package com.example.maincontrol;

import com.example.model.communication.ListFlightResponse;
import com.example.model.database.Flight;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

@Controller
@Component
@Scope("prototype") // scope prototype oznacza, ze za kazdym razem gdy bedzie potrzebny obiekt tego typu
// bedzie tworzony nowy kontroler - dzieki temu za kazdym razem mamy nowa liste lotow
public class TableFlights implements Initializable {

    @Autowired
    MyAppController myAppController;

    @Autowired
    SpringFxmlLoader springFxmlLoader;

    @Autowired
    ClientControl clientControl;

    @FXML
    private TableView<Flight> tableView;

    @FXML
    private TableColumn<Flight, String> countryColumn;

    @FXML
    private TableColumn<Flight, String> cityDestinationColumn;

    @FXML
    private TableColumn<Flight, Date> dateDepartureColumn;

    @FXML
    private TableColumn<Flight, Date> countrySourceColumn;

    @FXML
    private TableColumn<Flight, Integer> priceColumn;

    @FXML
    private TableColumn<Flight, Integer> numberSeatsColumn;

    @FXML
    private TableColumn<Flight, Date> dateArrivalColumn;

    @FXML
    private TableColumn<Flight, String> citySourceColumn;


    private ListFlightResponse listFlightResponse = null;


    public TableFlights(ListFlightResponse listFlightResponse) {
        // podczas stworzenia tego beana zostanie wywolany ten konstruktor
        this.listFlightResponse = listFlightResponse;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Flight> lista = null;
        lista = listFlightResponse.getListOfFlight();
        ObservableList<Flight> list = FXCollections.observableArrayList();
        for (Flight t : lista) {
            list.add(t);
        }
        countryColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Flight, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Flight, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDestination().getCountry());
            }
        });

        cityDestinationColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Flight, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Flight, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDestination().getCity());
            }
        });
        countrySourceColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Flight, Date>, ObservableValue<Date>>() {
            @Override
            public ObservableValue<Date> call(TableColumn.CellDataFeatures<Flight, Date> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getOrigin().getCountry());
            }
        });
        citySourceColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Flight, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Flight, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getOrigin().getCity());
            }
        });
        dateDepartureColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Flight, Date>, ObservableValue<Date>>() {
            @Override
            public ObservableValue<Date> call(TableColumn.CellDataFeatures<Flight, Date> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDateDepearture());
            }
        });
        priceColumn.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("price"));
        numberSeatsColumn.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("numberSeats"));
        dateArrivalColumn.setCellValueFactory(new PropertyValueFactory<Flight, Date>("dateArrival"));

        tableView.setItems(list);

    }

    private AnchorPane loadUi(String ui,ListFlightResponse listFlightResponse,Flight flight) {
        return (AnchorPane) springFxmlLoader.load(ui + ".fxml",listFlightResponse, flight);
    }

    public void bookButton(javafx.scene.input.MouseEvent mouseEvent) {
        Flight chooseFlight = tableView.getSelectionModel().getSelectedItem();
        if(chooseFlight != null)
        {
            AnchorPane root = loadUi("/bookFlight",listFlightResponse,chooseFlight);
            AnchorPane.setTopAnchor(root, 50d);
            AnchorPane.setLeftAnchor(root, 150d);
            myAppController.getMainLoad().getChildren().clear();
            myAppController.getMainLoad().getChildren().add(root);
        }
        else {

            }
    }
}
