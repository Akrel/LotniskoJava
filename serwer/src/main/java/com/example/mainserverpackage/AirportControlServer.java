package com.example.mainserverpackage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.model.communication.CreateReservationRequest;
import com.example.model.communication.CreateReservationResponse;
import com.example.model.communication.ListFlightRequest;
import com.example.model.communication.ListFlightResponse;
import com.example.model.database.Flight;
import com.example.model.database.Reservation;

@Component
public class AirportControlServer implements Serializable {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private FlightRepository flightRepository;

    private ServerSocket serverSocket;

    public void start(int port) throws IOException, ClassNotFoundException {
        // uruchamiamy socket serwera, do którego może połączyć się klient
        serverSocket = new ServerSocket(port);

        // główna pętla procesująca zapytania
        while (true) {
            Socket clientSocket = serverSocket.accept();  // tutaj serwer blokuje się, oczekując na zapytanie od klienta
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());

            Object request = in.readObject();

            if (request instanceof ListFlightRequest) {
                ListFlightRequest listFlightRequest = (ListFlightRequest) request;
                ListFlightResponse flightResponse = getListFlightResponse(listFlightRequest);
                out.writeObject(flightResponse);
            } else if (request instanceof CreateReservationRequest) {
                CreateReservationRequest createReservationRequest = (CreateReservationRequest) request;
                CreateReservationResponse createReservationResponse = getCreateReservationResponse(createReservationRequest);
                out.writeObject(createReservationResponse);
            } /* else if (request instanceof NewTypeOfRequest) {
                NewTypeOfRequest newRequest = (NewTypeOfRequest) request;
                NewTypeOfResponse newResponse = getNewTypeOfResponse(newRequest);
                out.writeObject(newResponse);
            }
            */

            close(clientSocket, out, in);

        }
    }

    private CreateReservationResponse getCreateReservationResponse(CreateReservationRequest createReservationRequest) throws IOException {
        // pobieramy flightOptional o przysłanym id z bazy danych
        // jeżeli istnieje w bazie, dostaniemy optionala z wartością
        // jeżeli nie istnieje w bazie, dostaniemy pustego optionala
        Optional<Flight> flightOptional = flightRepository.findById(createReservationRequest.getFlightId());

        // sprawdzamy czy flightOptional istnieje - jeżeli nie, to zwracamy informację o błędnym id
        if (!flightOptional.isPresent()) {
            return new CreateReservationResponse("INCORRECT FLIGHT ID - NO SUCH FLIGHT IN DB");
        }

        Flight flight = flightOptional.get();

        // walidacja wolnych miejsc
        int numberSeats = flight.getNumberSeats();
        if (numberSeats == 0) {
            return new CreateReservationResponse("NOT ENOUGH SEATS FOR FLIGHT");
        }

        // zmniejszanie liczby wolnych miejsc w locie
        int newNumberOfSeats = numberSeats - 1;
        flight.setNumberSeats(newNumberOfSeats);
        flightRepository.save(flight); // flight posiada id, więc repository wykona UPDATE na tym locie

        // tworzenie rezerwacji
        Reservation reservation = new Reservation();
        reservation.setFlight(flight);
        reservation.setPassengerName(createReservationRequest.getPassengerName());
        reservation.setPassengerSurname(createReservationRequest.getPassengerSurname());
        Reservation createdReservation = reservationRepository.save(reservation); // reservation nie posiada id, wiec repository wykona operacje CREATE

        System.out.println("Dodano rezerwacje: " + createdReservation.getId());

        return new CreateReservationResponse("OK, RESERVATION ID: " + createdReservation.getId());
    }

    private ListFlightResponse getListFlightResponse(ListFlightRequest listFlightRequest) {
        //
        Iterable<Flight> all = flightRepository.findAll();
        ListFlightResponse flightResponse = new ListFlightResponse();
        for (Flight flight : all) {
            flightResponse.insertToList(flight);

        }
        return flightResponse;
    }

    private void close(Socket clientSocket, ObjectOutputStream out, ObjectInputStream in) throws IOException {
        out.close();
        in.close();
        clientSocket.close();
    }

}


