package com.example.mainserverpackage;

import com.example.model.Flight;
import com.example.model.ListFlightRequest;
import com.example.model.ListFlightResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

public class AirportControlServer implements Serializable {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

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
                    flightResponse.insetToList(flight);

                }
                out.writeObject(flightResponse);

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


