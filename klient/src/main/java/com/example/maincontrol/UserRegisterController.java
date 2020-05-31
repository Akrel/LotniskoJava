package com.example.maincontrol;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;

import java.awt.event.MouseEvent;

public class UserRegisterController {
    @FXML
    private JFXTextField filedName;
    @FXML
    private JFXTextField fieldSuname;
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

    public JFXTextField getFieldSuname() {
        return fieldSuname;
    }

    public JFXTextField getFiledName() {
        return filedName;
    }


    @FXML
    void clickCancel(MouseEvent event) {

    }

    @FXML
    void clickCreateAccount(MouseEvent event) {
        
    }


}