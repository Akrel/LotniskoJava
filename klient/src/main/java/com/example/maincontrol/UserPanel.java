package com.example.maincontrol;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.net.URL;
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
    private TableColumn<?, ?> destinationLabel;
    @FXML
    private TableColumn<?, ?> dateDepartureLabel;
    @FXML
    private TableColumn<?, ?> sourceCityLabel;
    @FXML
    private TableColumn<?, ?> dateArrivalLabel;
    @FXML
    private TableColumn<?, ?> priceLabel;
    @FXML
    private TableView<?> tableFlight;

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

    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
