package com.example.maincontrol;

import javafx.fxml.FXMLLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class SpringFxmlLoader {

    @Autowired
    ApplicationContext applicationContext;

    /**
     * Metoda ładująca pliki fxml do klasy
     * @param url
     * @param params
     * @return
     */
    public Object load(String url, Object... params) {
        try (InputStream fxmlStream = SpringFxmlLoader.class.getResourceAsStream(url)) {
            FXMLLoader loader = new FXMLLoader();
            // applicationContext.getBean(aClass, params)
            // aClass = np. TableFlights.class
            // params = np. ListFlightResponse, potrzebny do zainicjalizowania kontrolera TableFlights
            // jezeli params == null, po prostu tworzy się bean bez parametrów
            loader.setControllerFactory(aClass -> applicationContext.getBean(aClass, params));
            return loader.load(fxmlStream);
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }

}