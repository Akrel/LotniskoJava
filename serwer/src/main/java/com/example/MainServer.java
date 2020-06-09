package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.example.mainserverpackage.AirportControlServer;

/**
    Główna klasa uruchamiająca aplikację SpringBoot.
 */
@SpringBootApplication
public class MainServer {

    public static void main(String[] args) {
        SpringApplication.run(MainServer.class, args);
    }

    /**
     * Definicja operacji jakie mają wykonać się po starcie aplikacji.
     *
     * Technicznie, jest to metoda tworząca bean klasy CommandLineRunner,
     * który uruchamia zdefiniowany kod.
     *
     * W tym wypadku, CommandLineRunner odpowiada za uruchomienie serwera,
     * stworzenie socketu serwerowego i rozpoczęcie procesu nasłuchiwania.
     */
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            AirportControlServer server = new AirportControlServer();
            server.start(6666);
        };
    }

}



