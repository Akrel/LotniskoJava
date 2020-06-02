package com.example.maincontrol;

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

    public JFXButton buttonUser;


    private AnchorPane loadUi(String ui) {
        AnchorPane root = (AnchorPane) springFxmlLoader.load(ui + ".fxml");
        return root;
    }

    public void searchButton(javafx.event.ActionEvent actionEvent) {
        if(!myAppController.getMainLoad().getChildren().isEmpty()){
            myAppController.getMainLoad().getChildren().clear();
        }
        AnchorPane root = loadUi("/tableFlights");
        AnchorPane.setRightAnchor(root, 50d);
        myAppController.getMainLoad().getChildren().add(root);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("SearchFlightController initialized");
    }
}
