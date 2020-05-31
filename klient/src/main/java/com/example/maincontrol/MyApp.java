package com.example.maincontrol;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
@SpringBootApplication
public class MyApp extends Application {

    private ConfigurableApplicationContext springContext;
    private Parent rootNode;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws IOException {
        springContext = SpringApplication.run(MyApp.class);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(springContext::getBean);
        fxmlLoader.setLocation((getClass().getResource("/sample.fxml")));
        rootNode = fxmlLoader.load();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Scene scene = new Scene(rootNode, 1024, 768);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.show();


        ClientControl client = new ClientControl();
        client.startConnection("127.0.0.1", 6666);


    }


    @Override
    public void stop() {
        springContext.stop();
    }


}