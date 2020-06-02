package com.example.maincontrol;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.springframework.stereotype.Component;

import com.example.model.Flight;
import com.example.model.ListFlightRequest;
import com.example.model.ListFlightResponse;

@Component
public class ClientControl {

    private final static String IP_ADDRESS = "127.0.0.1";
    private final static int SERVER_PORT = 6666;

    public ListFlightResponse listFlights(ListFlightRequest request) {
        ListFlightResponse listFlightResponse = (ListFlightResponse) send(request);
        if (listFlightResponse != null) {
            System.out.println("ListFlightResponse received!");
            Iterable<Flight> flightIterable = listFlightResponse.returnListFlight();
            for (Flight flight : flightIterable) {
                System.out.println(flight.getId());
            }
        }

        return listFlightResponse;
    }

    public Object send(Object request) {
        Socket clientSocket = null;
        ObjectOutputStream out = null;
        ObjectInputStream in = null;
        try {
            clientSocket = new Socket(IP_ADDRESS, SERVER_PORT);

            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());
            out.writeObject(request);
            return in.readObject();
        } catch (IOException e) {
            System.err.println("Error while communication with server - socket error");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Error while communication with server - classes incompatible");
            e.printStackTrace();
        } finally {
            closeConnection(clientSocket, out, in);
        }

        return null;
    }

    private void closeConnection(Socket clientSocket, ObjectOutputStream out, ObjectInputStream in) {
        try {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
            if (clientSocket != null) {
                clientSocket.close();
            }
        } catch (Exception e) {
            System.err.println("Error while closing connection");
        }
    }

}
