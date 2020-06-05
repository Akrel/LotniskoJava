package com.example.maincontrol;

import com.example.model.communication.ListFlightResponse;
import com.example.model.database.Flight;
import com.example.model.database.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.net.URL;
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

    @FXML
    private TableView<Flight> tableView;
    @FXML
    private TableColumn<User, String> name;
    @FXML
    private TableColumn<User, String> surname;
    @FXML
    private TableColumn<User, String> email;
    @FXML
    private TableColumn<User, String> phone;
    @FXML
    private TableColumn<User, String> password;

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

        tableView.setItems(list);

    }

    public void bookButton(javafx.scene.input.MouseEvent mouseEvent) {
        ObservableList<Flight> userObservableList = tableView.getSelectionModel().getSelectedItems();

    }
}
