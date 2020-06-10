package com.example.maincontrol;

import com.example.model.communication.LoginUserRequest;
import com.example.model.communication.LoginUserResponse;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
@Component
public class UserLoginController implements InitializingBean {
    @Autowired
    MyAppController myAppController;

    @Autowired
    SpringFxmlLoader springFxmlLoader;

    @Autowired
    ClientControl clientControl;

    @FXML
    private JFXTextField logEmail;

    @FXML
    private JFXPasswordField logPassw;
    @FXML
    private Text infoLabel;

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    public void logButton(MouseEvent mouseEvent) {

        if (!logEmail.getText().isEmpty()) {
            if (!logPassw.getText().isEmpty()) {
                LoginUserRequest loginUserRequest = new LoginUserRequest();
                loginUserRequest.setEmail(logEmail.getText());
                loginUserRequest.setPassword(logPassw.getText());
                LoginUserResponse loginUserResponse = clientControl.loginUser(loginUserRequest);
               infoLabel.setText(loginUserResponse.getStatus() + loginUserResponse.getUser().getName());
            } else {
                infoLabel.setText("PASSWORD EMPTY");
            }
        } else {
            infoLabel.setText("EMAIL EMPTY");
        }
    }
}
