package com.example.maincontrol;

import com.example.model.communication.ListFlightRequest;
import com.example.model.communication.ListFlightResponse;
import com.jfoenix.controls.JFXButton;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
@Component
public class SearchFlightController implements InitializingBean {

    @Autowired
    MyAppController myAppController;

    @Autowired
    SpringFxmlLoader springFxmlLoader;

    @Autowired
    ClientControl clientControl;

    public JFXButton buttonUser;

    private AnchorPane loadUi(String ui, ListFlightResponse listFlightResponse) {
        return (AnchorPane) springFxmlLoader.load(ui + ".fxml", listFlightResponse);
    }

    public void searchButton(javafx.event.ActionEvent actionEvent) {
        if (!myAppController.getMainLoad().getChildren().isEmpty()) {
            myAppController.getMainLoad().getChildren().clear();
        }

        // wyszukaj liste lotow
        ListFlightRequest request = new ListFlightRequest();
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
}
