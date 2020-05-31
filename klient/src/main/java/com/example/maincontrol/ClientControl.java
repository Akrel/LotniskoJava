package com.example.maincontrol;


import com.example.model.Flight;
import com.example.model.ListFlightRequest;
import com.example.model.ListFlightResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientControl {
    private Socket clientSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;


    public void startConnection(String ip, int port) throws IOException, ClassNotFoundException {
        clientSocket = new Socket(ip, port);
        out = new ObjectOutputStream(clientSocket.getOutputStream());
        in = new ObjectInputStream(clientSocket.getInputStream());
        ListFlightRequest listFlightRequest = new ListFlightRequest();
        out.writeObject(listFlightRequest);

        ListFlightResponse listFlightResponse = new ListFlightResponse();
        listFlightResponse = (ListFlightResponse) in.readObject();
        Iterable<Flight> flightIterable = listFlightResponse.returnListFlight();
        for (Flight flight : flightIterable) {
            System.out.println(flight.getId());
        }



    }


    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }
}
