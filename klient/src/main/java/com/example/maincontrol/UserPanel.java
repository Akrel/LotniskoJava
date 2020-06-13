package com.example.maincontrol;

import com.example.model.communication.FindReservationRequest;
import com.example.model.communication.FindReservationResponse;
import com.example.model.database.Reservation;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

@Controller
@Component
public class UserPanel implements InitializingBean, Initializable {
    @Autowired
    MyAppController myAppController;

    @Autowired
    SpringFxmlLoader springFxmlLoader;

    @Autowired
    ClientControl clientControl;

    @FXML
    private Text nameLabel;
    @FXML
    private Text surnameLabel;
    @FXML
    private Text emailLabel;
    @FXML
    private Text phoneNumberLabel;
    @FXML
    private TableColumn<Reservation, String> destinationLabel;
    @FXML
    private TableColumn<Reservation, Date> dateDepartureLabel;
    @FXML
    private TableColumn<Reservation, String> sourceCityLabel;
    @FXML
    private TableColumn<Reservation, Date> dateArrivalLabel;
    @FXML
    private TableColumn<Reservation, Integer> priceLabel;
    @FXML
    private TableView<Reservation> tableFlight;

    private AnchorPane loadUi(String ui) {
        return (AnchorPane) springFxmlLoader.load(ui + ".fxml");
    }


    public void editProfileBUtton(javafx.scene.input.MouseEvent mouseEvent) {
        if (!myAppController.getMainLoad().getChildren().isEmpty()) {
            myAppController.getMainLoad().getChildren().clear();
        }
        AnchorPane root = loadUi("/editProfile");
        myAppController.getMainLoad().getChildren().add(root);
        AnchorPane.setLeftAnchor(root, 110d);
        myAppController.hideAllSliderMenu();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameLabel.setText(myAppController.getLoggedInUser().getName());
        surnameLabel.setText(myAppController.getLoggedInUser().getSurname());
        emailLabel.setText(myAppController.getLoggedInUser().getEmail());
        phoneNumberLabel.setText(myAppController.getLoggedInUser().getPhone());
        FindReservationRequest findReservationRequest = new FindReservationRequest(myAppController.getLoggedInUser().getId());
        FindReservationResponse findReservationResponse = clientControl.findReservation(findReservationRequest);
        List<Reservation> lista = null;
        lista = findReservationResponse.getListOfReservation();
        ObservableList<Reservation> list = FXCollections.observableArrayList();
        for (Reservation t : lista) {
            list.add(t);
        }

        destinationLabel.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Reservation, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getFlight().getDestination().getCity());
            }
        });
        dateDepartureLabel.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation, Date>, ObservableValue<Date>>() {
            @Override
            public ObservableValue<Date> call(TableColumn.CellDataFeatures<Reservation, Date> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getFlight().getDateDepearture());
            }
        });
        sourceCityLabel.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Reservation, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getFlight().getOrigin().getCity());
            }
        });
        dateArrivalLabel.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation, Date>, ObservableValue<Date>>() {
            @Override
            public ObservableValue<Date> call(TableColumn.CellDataFeatures<Reservation, Date> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getFlight().getDateArrival());
            }
        });
        priceLabel.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Reservation, Integer> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getFlight().getPrice());
            }
        });


        tableFlight.setItems(list);
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
