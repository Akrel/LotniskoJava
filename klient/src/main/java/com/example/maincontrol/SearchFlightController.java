package com.example.maincontrol;

import com.example.model.communication.ListFlightRequest;
import com.example.model.communication.ListFlightResponse;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
@Component
public class SearchFlightController implements InitializingBean, Initializable {

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

    static ObservableList<String> list = FXCollections.observableArrayList("Krakow", "Gdansk", "Katowice", "Radom");


    private AnchorPane loadUi(String ui, ListFlightResponse listFlightResponse) {
        return (AnchorPane) springFxmlLoader.load(ui + ".fxml", listFlightResponse);
    }

    public void searchButton(javafx.event.ActionEvent actionEvent) {
        if (!myAppController.getMainLoad().getChildren().isEmpty()) {
            myAppController.getMainLoad().getChildren().clear();
        }

        // wyszukaj liste lotow
        ListFlightRequest request = new ListFlightRequest();


        request.setDestination(destinationLabel.getValue());
        request.setOrigin(fromLabel.getValue());


        ListFlightResponse listFlightResponse = clientControl.listFlights(request);

        // stworz nowy widok, przekazujac mu wyszukana liste lotow
        AnchorPane root = loadUi("/tableFlights", listFlightResponse);
        AnchorPane.setRightAnchor(root, 15d);
        myAppController.getMainLoad().getChildren().add(root);

    }

    @Override
    public void afterPropertiesSet() throws Exception {

        System.out.println("SearchFlightController initialized");
    }

    public void fromPickerLabel(MouseEvent mouseEvent) {

    }

    public void toPickLabel(MouseEvent mouseEvent) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fromLabel.setItems(list);
        destinationLabel.setItems(list);
    }
}
