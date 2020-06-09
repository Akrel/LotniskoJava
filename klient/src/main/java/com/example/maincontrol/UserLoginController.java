package com.example.maincontrol;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.awt.event.MouseEvent;
@Controller
@Component
public class UserLoginController implements InitializingBean {
    @Autowired
    MyAppController myAppController;

    @Autowired
    SpringFxmlLoader springFxmlLoader;
    @FXML
    private JFXTextField logEmail;

    @FXML
    private JFXPasswordField logPassw;

    @FXML
    void logButton(MouseEvent event) {


        // myAppController.setLoggedInUserId(123);
        // UserLoginResponse
        //     User
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    public void logButton(javafx.scene.input.MouseEvent mouseEvent) {
        // myAppController.setLoggedInUserId(123);
    }
}
