package com.example.maincontrol;

import com.example.model.communication.CreateUserRequest;
import com.example.model.communication.CreateUserResponse;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.regex.Pattern;

@Controller
@Component
public class UserRegisterController implements InitializingBean {
    @Autowired
    MyAppController myAppController;

    @Autowired
    SpringFxmlLoader springFxmlLoader;

    @Autowired
    ClientControl clientControl;


    @FXML
    private JFXTextField filedName;
    @FXML
    private JFXTextField fieldSurname;
    @FXML
    private JFXTextField fieldEmail;
    @FXML
    private JFXTextField fieldPhone;
    @FXML
    private JFXPasswordField fieldPassword;
    @FXML
    private JFXPasswordField fieldRePass;
    @FXML
    private CheckBox checkBox;
    @FXML
    private JFXButton buttonCreate;
    @FXML
    private JFXButton buttonCancel;
    @FXML
    private Text waringField;
    final String passRegEx = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])(?=\\S+$).{8,15}$";
    final String phoneRegEx = "(?:\\d{3}-){2}\\d{4}";

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    public void clickCreateAccount(javafx.scene.input.MouseEvent mouseEvent) {
        waringField.setText("");
        Boolean check = false;
        check = isValid(fieldEmail.toString());
        if (!filedName.getText().isEmpty()) {
            if (!fieldSurname.getText().isEmpty()) {
                if (!fieldEmail.getText().isEmpty()) {
                    if (!check) {
                        if (!fieldPhone.getText().isEmpty()) {
                            if (!fieldPhone.getText().matches(phoneRegEx)) {
                                if (!fieldPassword.getText().isEmpty() || !fieldRePass.getText().isEmpty()) {
                                    if (fieldPassword.getText().matches(passRegEx)) {
                                        if (fieldRePass.getText().equals(fieldPassword.getText())) {
                                            if (checkBox.isSelected()) {
                                                CreateUserRequest createUserRequest = new CreateUserRequest();
                                                createUserRequest.setName(filedName.getText());
                                                createUserRequest.setSurname(fieldSurname.getText());
                                                createUserRequest.setPhone(fieldPhone.getText());
                                                createUserRequest.setPassword(fieldPassword.getText());
                                                createUserRequest.setEmail(fieldEmail.getText());
                                                createUserRequest.setTypeCreate("add");
                                                CreateUserResponse createUserResponse = clientControl.registerUser(createUserRequest);
                                                waringField.setText(createUserResponse.getStatus());
                                            } else {
                                                waringField.setText("Accept accept terms and conditions");
                                            }
                                        } else {
                                            waringField.setText("Bad Password");
                                        }
                                    } else {
                                        waringField.setText("Invalid Password ");
                                    }

                                } else {
                                    waringField.setText("Empty Password");
                                }
                            } else {
                                waringField.setText("Bad format phone number, format is 000 000 000");
                            }
                            } else {
                                waringField.setText("Empty phone number");
                            }
                        } else {
                            waringField.setText("Bad email");
                        }
                    } else {
                        waringField.setText("Empty email");

                    }
                } else {
                    waringField.setText("Empty surname");
                }
            } else {
                waringField.setText("Empty name");
            }

        }

        public static boolean isValid (String email){
            String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                    "[a-zA-Z0-9_+&*-]+)*@" +
                    "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                    "A-Z]{2,7}$";

            Pattern pat = Pattern.compile(emailRegex);
            if (email == null)
                return false;
            return pat.matcher(email).matches();
        }

        public void clickCancel (javafx.scene.input.MouseEvent mouseEvent){
            if (!myAppController.getMainLoad().getChildren().isEmpty()) {
                myAppController.getMainLoad().getChildren().clear();
            }
            AnchorPane root = loadUi("/userLogin");
            AnchorPane.setLeftAnchor(root, 210d);
            myAppController.getMainLoad().getChildren().add(root);

            myAppController.hideAllSliderMenu();
        }

        private AnchorPane loadUi (String ui){
            return (AnchorPane) springFxmlLoader.load(ui + ".fxml");
        }
    }