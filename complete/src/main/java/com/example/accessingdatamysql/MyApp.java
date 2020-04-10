package com.example.accessingdatamysql;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import static javafx.application.Application.launch;

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

        primaryStage.setTitle("hello World");
        Scene scene = new Scene(rootNode,800,600);
        primaryStage.show();
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
