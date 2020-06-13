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


    public JFXButton buttonPanelClient;
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


    private User loggedInUser;


    public void hideAllSliderMenu() {

        userSlideBarHide();
    }

    public JFXButton getButtonPanelClient() {
        return buttonPanelClient;
    }

    public void setButtonPanelClient(JFXButton buttonPanelClient) {
        this.buttonPanelClient = buttonPanelClient;
    }

    /**
     * Metoda do obsługi przycisku  menu logowania
     * @param event
     * @throws IOException
     */

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

    /**
     * Metoda do obsługi przycisku wyszukiwania lotow
     * @param event
     * @throws IOException
     */
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

    /**
     * Metoda do obsługi przycisku rejestracji użytkownika
     * @param event
     */
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
    /**
     * Metoda pozwala na przeciąganie okna.
     * @param event
     */
    @FXML
    void draged(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX() - SceneX);
        stage.setY(event.getScreenY() - SceneY);
    }

    /**
     * Metoda do obsługi przycisku minimalizacji okna aplikacji.
     * @param event
     */
    @FXML
    void min(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);

    }

    /**
     * Metoda do obsługi przycisku zamknięcia okna aplikacji.
     * @param event
     */
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
        buttonPanelClient.setDisable(true);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                closeFastNav.setToX(-(vUserSlideBox.getWidth()));
                closeFastNav.play();

            }
        });
    }

    /**
     * Metoda obsługująca chowanie się menu dla użytkownika
     */

    public void userSlideBarHide() {
        buttonUser.getStyleClass().remove("side-button-active");
        buttonUser.getStyleClass().addAll("sidebar-button");
        closeNav.setToX(-(vUserSlideBox.getWidth()));
        closeNav.play();

    }

    /**
     * Metoda obsługująca wysuwania się menu dla użytkownika
     */
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


    public User getLoggedInUser() {
        return loggedInUser;
    }


    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    /**
     * Metoda do obsługi przycisku, który otwiera panel użytkownika
     * @param mouseEvent
     */
    public void clientPanelButton(MouseEvent mouseEvent) {
        if (!mainLoad.getChildren().isEmpty()) {
            mainLoad.getChildren().clear();
        }
        AnchorPane root = loadUi("/userPanel");
        mainLoad.getChildren().add(root);
        AnchorPane.setLeftAnchor(root, 110d);
        hideAllSliderMenu();

    }


    @Override
    public void afterPropertiesSet() throws Exception {

    }
}