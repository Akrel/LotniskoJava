package com.example.mainserverpackage;

import java.net.*;
import java.io.*;
import java.util.Date;

public class AirportControlServer  implements Serializable {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
        ListFlightResponse object1 = new ListFlightResponse();
        Flights object2 = new Flights(1,5);
        object1.insetToList(object2);

        objectOutputStream.writeObject(object1);
        String greeting = in.readLine();
        if ("hello server".equals(greeting)) {
            out.println("hello client");
        } else {
            out.println("greeting");
        }

        System.out.println("Serwer" + greeting);
    }

    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }

    public static void main(String[] args) throws IOException {
        AirportControlServer server = new AirportControlServer();
        server.start(6666);
    }


}


