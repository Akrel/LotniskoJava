package com.example.maincontrol;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import com.jfoenix.controls.JFXButton;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@Component
public class MyAppController implements Initializable, InitializingBean {

    public VBox vUserSlideBox;
    @FXML
    private AnchorPane mainScene;
    private TranslateTransition openNav;
    private TranslateTransition closeNav;
    private TranslateTransition closeFastNav;
    double SceneX, SceneY;
    public JFXButton buttonUser;
    public JFXButton buttonFlight;

    private boolean initialized = false;

    @Autowired
    SpringFxmlLoader springFxmlLoader;

    public void hideAllSliderMenu() {
        userSlideBarHide();
    }

    @FXML
    public void u1(MouseEvent event) throws IOException {
        loadUi("/userLogin");
        hideAllSliderMenu();
    }

    @FXML
    void goFlights(ActionEvent event) throws IOException {
        loadUi("/searchFlights");
        hideAllSliderMenu();
    }

    @FXML
    void goCreateAccount(MouseEvent event) {
        loadUi("/userRegister");
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
    void max(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setFullScreenExitHint(" ");
        stage.setFullScreen(!stage.isFullScreen());
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

    private void loadUi(String ui) {
        AnchorPane root = (AnchorPane) springFxmlLoader.load(ui + ".fxml");
        AnchorPane.setRightAnchor(root, 50d);
        mainScene.getChildren().add(root);
    }

    public AnchorPane getMainScene() {
        return mainScene;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("1234567890");
        initialized = true;
    }
}