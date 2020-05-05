package com.example.maincontrol;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import com.jfoenix.controls.JFXButton;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class MyAppController implements Initializable {
    public VBox vUserSlideBox;

    private TranslateTransition openNav;
    private TranslateTransition closeNav;
    private TranslateTransition closeFastNav;
    double x, y;
    public JFXButton buttonUser;
    private TranslateTransition openNav2;


    @FXML
    void pressed(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }

    @FXML
    void draged(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
    }


    @FXML
    void max(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setFullScreenExitHint(" ");
        if (stage.isFullScreen()) {
            stage.setFullScreen(false);
        } else {
            stage.setFullScreen(true);
        }
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


    @Override
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

    public void hideAllSliderMenu() {
        userSlideBarHide();
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
}
