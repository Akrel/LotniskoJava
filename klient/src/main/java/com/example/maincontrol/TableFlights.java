package com.example.maincontrol;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.example.model.ListFlightResponse;
import com.example.model.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private TableView<User> tableView;
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

    public ObservableList<User> list = FXCollections.observableArrayList(
            new User("name", "surname", "email", "phone", "password"),
            new User("name1", "surname1", "email1", "phone1", "password1"),
            new User("name2", "surname2", "email2", "phone2", "password2"),
            new User("name3", "surname3", "email3", "phone3", "password3")

    );

    public TableFlights(ListFlightResponse listFlightResponse) {
        // podczas stworzenia tego beana zostanie wywolany ten konstruktor
        this.listFlightResponse = listFlightResponse;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        name.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        surname.setCellValueFactory(new PropertyValueFactory<User, String>("surname"));
        email.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        password.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
        phone.setCellValueFactory(new PropertyValueFactory<User, String>("phone"));
        tableView.setItems(list);

    }

    public void bookButton(javafx.scene.input.MouseEvent mouseEvent) {
        ObservableList<User> userObservableList = tableView.getSelectionModel().getSelectedItems();
        System.out.println(userObservableList.get(0).getName());
        System.out.println(userObservableList.get(0).getSurname());
    }
}
