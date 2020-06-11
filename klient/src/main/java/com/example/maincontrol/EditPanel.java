package com.example.maincontrol;

import com.example.model.communication.CreateUserRequest;
import com.example.model.communication.CreateUserResponse;
import com.example.model.communication.LoginUserRequest;
import com.example.model.communication.LoginUserResponse;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;


@Controller
@Component
public class EditPanel implements InitializingBean, Initializable {
    @Autowired
    MyAppController myAppController;

    @Autowired
    SpringFxmlLoader springFxmlLoader;

    @Autowired
    ClientControl clientControl;

    @FXML
    private JFXTextField nameLabel;

    @FXML
    private JFXTextField surnameLabel;

    @FXML
    private JFXTextField emailLabel;

    @FXML
    private JFXTextField phoneLabel;

    @FXML
    private JFXPasswordField passwordLabel;

    @FXML
    private JFXPasswordField confirmPasswordLabel;

    @FXML
    private Text infoLabel;


    public void confirmButton(MouseEvent mouseEvent) {
        infoLabel.setText("");
        Boolean check = false;
        check = isValid(emailLabel.toString());
        if (!nameLabel.getText().isEmpty()) {
            if (!surnameLabel.getText().isEmpty()) {
                if (!emailLabel.getText().isEmpty()) {
                    if (!check) {
                        if (!phoneLabel.getText().isEmpty()) {
                            CreateUserRequest createUserRequest = new CreateUserRequest();
                            createUserRequest.setName(nameLabel.getText());
                            createUserRequest.setSurname(surnameLabel.getText());
                            createUserRequest.setPhone(phoneLabel.getText());
                            createUserRequest.setEmail(emailLabel.getText());
                            createUserRequest.setPassword(myAppController.getLoggedInUser().getPassword());
                            CreateUserResponse createUserResponse = clientControl.registerUser(createUserRequest);
                            infoLabel.setText(createUserResponse.getStatus());
                            LoginUserRequest loginUserRequest = new LoginUserRequest();
                            loginUserRequest.setEmail(emailLabel.getText());
                            LoginUserResponse loginUserResponse = clientControl.loginUser(loginUserRequest);
                            myAppController.setLoggedInUser(loginUserResponse.getUser());
                            //initialize();
                        } else {
                            infoLabel.setText("Empty phone number");
                        }
                    } else {
                        infoLabel.setText("Bad email");
                    }
                } else {
                    infoLabel.setText("Empty email");
                }
            } else {
                infoLabel.setText("Empty surname");
            }
        } else {
            infoLabel.setText("Empty name");
        }
    }

    public void backButton(MouseEvent mouseEvent) {

        if (!myAppController.getMainLoad().getChildren().isEmpty()) {
            myAppController.getMainLoad().getChildren().clear();
        }
        AnchorPane root = loadUi("/userPanel");
        myAppController.getMainLoad().getChildren().add(root);
        AnchorPane.setLeftAnchor(root, 110d);
        myAppController.hideAllSliderMenu();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameLabel.setText(myAppController.getLoggedInUser().getName());
        surnameLabel.setText(myAppController.getLoggedInUser().getSurname());
        emailLabel.setText(myAppController.getLoggedInUser().getEmail());
        phoneLabel.setText(myAppController.getLoggedInUser().getPhone());

    }

    private AnchorPane loadUi(String ui) {
        return (AnchorPane) springFxmlLoader.load(ui + ".fxml");
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    public static boolean isValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
}
