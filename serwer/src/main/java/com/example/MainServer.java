package com.example;

import com.example.mainserverpackage.AirportControlServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * Główna klasa uruchamiająca aplikację SpringBoot.
 */
@SpringBootApplication
public class MainServer {
    @Autowired
    AirportControlServer airportControlServer;
    public static void main(String[] args) {
        SpringApplication.run(MainServer.class, args);
    }

    /**
     * Definicja operacji jakie mają wykonać się po starcie aplikacji.
     * <p>
     * Technicznie, jest to metoda tworząca bean klasy CommandLineRunner,
     * który uruchamia zdefiniowany kod.
     * <p>
     * W tym wypadku, CommandLineRunner odpowiada za uruchomienie serwera,
     * stworzenie socketu serwerowego i rozpoczęcie procesu nasłuchiwania.
     */
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            airportControlServer.start(6666);
        };
    }

}



