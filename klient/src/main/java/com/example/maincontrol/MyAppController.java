package com.example.maincontrol;

import com.example.model.database.User;
import com.jfoenix.controls.JFXButton;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
@Component
public class MyAppController implements Initializable, InitializingBean {

    @Autowired
    SpringFxmlLoader springFxmlLoader;

    @FXML
    private AnchorPane mainScene;
    private TranslateTransition openNav;
    private TranslateTransition closeNav;
    private TranslateTransition closeFastNav;
    double SceneX, SceneY;
    public JFXButton buttonUser;
    public JFXButton buttonFlight;
    public AnchorPane mainLoad;
    public VBox vUserSlideBox;
    private boolean initialized = false;
    private JFXButton buttonPanelClient;

    private User loggedInUser;


    public void hideAllSliderMenu() {

        userSlideBarHide();
    }

    @FXML
    public void loginUserButton(MouseEvent event) throws IOException {
        if (!mainLoad.getChildren().isEmpty()) {
            mainLoad.getChildren().clear();
        }
        AnchorPane root = loadUi("/userLogin");
        AnchorPane.setLeftAnchor(root, 210d);
        mainLoad.getChildren().add(root);

        hideAllSliderMenu();
    }

    @FXML
    void goFlights(ActionEvent event) throws IOException {

        if (!mainLoad.getChildren().isEmpty()) {
            mainLoad.getChildren().clear();
        }
        AnchorPane root = loadUi("/searchFlights");
        AnchorPane.setLeftAnchor(root, 110d);
        AnchorPane.setRightAnchor(root, 100d);
        mainLoad.getChildren().add(root);
        hideAllSliderMenu();
    }

    @FXML
    void goCreateAccount(MouseEvent event) {
        if (!mainLoad.getChildren().isEmpty()) {
            mainLoad.getChildren().clear();
        }
        AnchorPane root = loadUi("/userRegister");
        AnchorPane.setLeftAnchor(root, 220d);
        AnchorPane.setRightAnchor(root, 230d);
        AnchorPane.setTopAnchor(root, 50d);
        AnchorPane.setBottomAnchor(root, 30d);
        mainLoad.getChildren().add(root);
        hideAllSliderMenu();
    }

    @FXML
    void pressed(MouseEvent event) {
        SceneX = event.getSceneX();
        SceneY = event.getSceneY();
    }

    @FXML
    void draged(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX() - SceneX);
        stage.setY(event.getScreenY() - SceneY);
    }


    @FXML
    void min(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);

    }

    @FXML
    void close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }


    @FXML
    public void initialize(URL location, ResourceBundle resources) {

        openNav = new TranslateTransition(Duration.millis(100), vUserSlideBox);
        openNav.setToX(vUserSlideBox.getTranslateX() - vUserSlideBox.getWidth());
        closeNav = new TranslateTransition(Duration.millis(100), vUserSlideBox);
        closeFastNav = new TranslateTransition(Duration.millis(.1), vUserSlideBox);

        buttonUser.setOnAction((ActionEvent event) -> {
            buttonUserHover();
        });


        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                closeFastNav.setToX(-(vUserSlideBox.getWidth()));
                closeFastNav.play();

            }
        });
    }


    public void userSlideBarHide() {
        buttonUser.getStyleClass().remove("side-button-active");
        buttonUser.getStyleClass().addAll("sidebar-button");
        closeNav.setToX(-(vUserSlideBox.getWidth()));
        closeNav.play();

    }


    public void buttonUserHover() {
        if ((vUserSlideBox.getTranslateX()) == -(vUserSlideBox.getWidth())) {
            buttonUser.getStyleClass().remove("side-button");
            buttonUser.getStyleClass().add("sidebar-button-active");

            openNav.play();
        } else {
            userSlideBarHide();
        }

    }

    private AnchorPane loadUi(String ui) {
        AnchorPane root = (AnchorPane) springFxmlLoader.load(ui + ".fxml");
        return root;
    }

    public AnchorPane getMainScene() {
        return mainScene;
    }

    public AnchorPane getMainLoad() {
        return mainLoad;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("1234567890");
        initialized = true;
    }


    public User getLoggedInUser() {
        return loggedInUser;
    }


    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public void clientPanelButton(MouseEvent mouseEvent) {
        if (!mainLoad.getChildren().isEmpty()) {
            mainLoad.getChildren().clear();
        }
        AnchorPane root = loadUi("/userPanel");
        mainLoad.getChildren().add(root);
        AnchorPane.setLeftAnchor(root, 110d);
        hideAllSliderMenu();

    }



}