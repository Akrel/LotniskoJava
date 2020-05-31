package com.example;


import com.example.mainserverpackage.AirportControlServer;
import com.example.mainserverpackage.FlightRepository;
import com.example.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;


@SpringBootApplication
public class MainServer {


    @Autowired
    FlightRepository flightRepository;

    public static void main(String[] args) {
        SpringApplication.run(MainServer.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }


            Iterable<Flight> flights = flightRepository.findAll();

            for (Flight flight : flights) {
                System.out.println(flight.getId());

            }


            AirportControlServer server = new AirportControlServer();
            server.start(6666, flightRepository);


        };
    }


}



