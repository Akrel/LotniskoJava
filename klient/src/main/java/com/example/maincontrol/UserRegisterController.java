package com.example.maincontrol;

import com.example.model.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
@Component
public class UserRegisterController implements InitializingBean {
    @Autowired
    MyAppController myAppController;

    @Autowired
    SpringFxmlLoader springFxmlLoader;
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


    public JFXTextField getFieldEmail() {
        return fieldEmail;
    }

    public JFXPasswordField getFieldPassword() {
        return fieldPassword;
    }

    public JFXPasswordField getFieldRePass() {
        return fieldRePass;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public JFXButton getButtonCancel() {
        return buttonCancel;
    }

    public JFXButton getButtonCreate() {
        return buttonCreate;
    }

    public JFXTextField getFieldPhone() {
        return fieldPhone;
    }

    public JFXTextField getFieldSurname() {
        return fieldSurname;
    }

    public JFXTextField getFiledName() {
        return filedName;
    }


    @Override
    public void afterPropertiesSet() throws Exception {

    }

    public void clickCreateAccount(javafx.scene.input.MouseEvent mouseEvent) {

        String name = getFiledName().toString();
        String surname = getFieldSurname().toString();
        String email = getFieldEmail().toString();
        String phone = getFieldPhone().toString();
        String password = getFieldPassword().toString();
        String rePassw = getFieldRePass().toString();

        if (name.isEmpty() || surname.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
            System.out.println("Zle uzupelniono");
        } else {
                if (!rePassw.equals(password))
                {
                    System.out.println("Zle haslo");
                }
                else {
                    User nowy = new User(name,surname,email,phone,password);
                }
        }

    }

    public void clickCancel(javafx.scene.input.MouseEvent mouseEvent) {
    }
}