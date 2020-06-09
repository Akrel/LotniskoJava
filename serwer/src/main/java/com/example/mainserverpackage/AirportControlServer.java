package com.example.mainserverpackage;

import com.example.model.communication.ListFlightRequest;
import com.example.model.communication.ListFlightResponse;
import com.example.model.database.Flight;
import com.example.model.database.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

@Controller
public class AirportControlServer implements Serializable {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    @Autowired
    private ReservationRepository reservationRepository;
    public @ResponseBody
    void addNewReservation(Reservation reservation){
        reservationRepository.save(reservation);
        System.out.println("Dodano");
    };


    public void start(int port, FlightRepository flightRepository) throws IOException, ClassNotFoundException {
        serverSocket = new ServerSocket(port);

        while (true) {
            clientSocket = serverSocket.accept();
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream((clientSocket.getInputStream()));

            Object request = in.readObject();


            if (request instanceof ListFlightRequest) {
                ListFlightRequest listFlightRequest = (ListFlightRequest) request;
                Iterable<Flight> all = flightRepository.findAll();
                ListFlightResponse flightResponse = new ListFlightResponse();
                for (Flight flight : all) {
                    flightResponse.insertToList(flight);

                }
                out.writeObject(flightResponse);
//                out.close();
//                in.close();
//                clientSocket.close();
            } else if (request instanceof Reservation) {
                Reservation reservation = (Reservation) request;
                addNewReservation(reservation);
            }


        }
    }

    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }


}


