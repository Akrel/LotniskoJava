package com.example.accessingdatamysql;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class myAppController implements Initializable  {

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }


    @FXML
    public void goTab(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/changepassword.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
}
