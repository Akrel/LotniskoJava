package com.example.maincontrol;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

import static org.junit.Assert.assertEquals;


@SpringBootApplication
public class MyApp extends Application {
    private ConfigurableApplicationContext springContext;
    private Parent rootNode;
    private FXMLLoader fxmlLoader;

    @Override
    public void init() throws Exception{
        springContext = SpringApplication.run(MyApp.class);
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(springContext::getBean);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        fxmlLoader.setLocation((getClass().getResource("/sample.fxml")));
        rootNode = fxmlLoader.load();

       // primaryStage.setTitle("hello World");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(rootNode,800,600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void showMenuAirport() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MyApp.class.getResource("/sample.fxml"));
        AnchorPane menuAir = loader.load();
    }


    @Test
    public void givenGreetingClient_whenServerRespondsWhenStarted_thenCorrect() throws IOException {
        ClientControl client = new ClientControl();
        client.startConnection("127.0.0.1", 6666);
        String response = client.sendMessage("hello server");
        assertEquals("hello client", response);
    }

    @Override
    public void stop(){
        springContext.stop();
    }

    public static void main(String[] args)
    {
        launch(args);
    }

}