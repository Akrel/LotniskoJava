package com.example.maincontrol;

import com.example.model.communication.CreateUserRequest;
import com.example.model.communication.CreateUserResponse;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
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
    final String strRegEx = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])(?=\\S+$).{8,15}$";

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
                            if (!fieldPassword.getText().isEmpty() || !fieldRePass.getText().isEmpty()) {
                                if (fieldRePass.getText().equals(fieldPassword.getText())) {
                                    if(checkBox.isSelected())
                                    {
                                        CreateUserRequest createUserRequest = new CreateUserRequest();
                                        createUserRequest.setName(filedName.getText());
                                        createUserRequest.setSurname(fieldSurname.getText());
                                        createUserRequest.setPhone(fieldPhone.getText());
                                        createUserRequest.setPassword(fieldPassword.getText());
                                        createUserRequest.setEmail(fieldEmail.getText());
                                        CreateUserResponse createUserResponse = clientControl.registerUser(createUserRequest);
                                        waringField.setText(createUserResponse.getStatus());
                                    }
                                    else{
                                        waringField.setText("Accept accept terms and conditions");
                                    }
                                } else {
                                    waringField.setText("Bad password");
                                }
                            } else {
                                waringField.setText("Empty password");
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

    public void clickCancel(javafx.scene.input.MouseEvent mouseEvent) {
    /*    if () {

        } else {
            waringField.setText("Bad Email");
        }*/
    }
}