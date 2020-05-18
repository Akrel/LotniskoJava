package com.example.maincontrol;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@Component
public class SearchFlightController implements InitializingBean {

    @Autowired
    MyAppController myAppController;

    @Autowired
    SpringFxmlLoader springFxmlLoader;

    public JFXButton buttonUser;

    private void loadUi(String ui) {
        AnchorPane root = null;

        root = (AnchorPane) springFxmlLoader.load(ui + ".fxml");


        AnchorPane.setRightAnchor(root, 50d);

        myAppController.getMainScene().getChildren().add(root);

    }

    public void searchButton(javafx.event.ActionEvent actionEvent) {
        loadUi("/tableFlights");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("SearchFlightController initialized");
    }
}
