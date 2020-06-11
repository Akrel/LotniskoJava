package com.example.mainserverpackage;

import com.example.model.communication.*;
import com.example.model.database.Flight;
import com.example.model.database.Reservation;
import com.example.model.database.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Optional;

@Component
public class AirportControlServer implements Serializable {
    private ServerSocket serverSocket;


    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private UserRepository userRepository;

    /**
     * @param port
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void start(int port) throws IOException, ClassNotFoundException {
        // uruchamiamy socket serwera, do którego może połączyć się klient
        serverSocket = new ServerSocket(port);
        System.out.println("START SERVER");
        // główna pętla procesująca zapytania
        while (true) {
            Socket clientSocket = serverSocket.accept();  // tutaj serwer blokuje się, oczekując na zapytanie od klienta
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());

            Object request = in.readObject();

            if (request instanceof ListFlightRequest) {
                ListFlightRequest listFlightRequest = (ListFlightRequest) request;
                ListFlightResponse flightResponse = getListFlightResponse(listFlightRequest);
                System.out.println("t");
                out.writeObject(flightResponse);
            } else if (request instanceof CreateReservationRequest) {
                CreateReservationRequest createReservationRequest = (CreateReservationRequest) request;
                CreateReservationResponse createReservationResponse = getCreateReservationResponse(createReservationRequest);
                out.writeObject(createReservationResponse);
            } else if (request instanceof CreateUserRequest) {
                CreateUserRequest createUserRequest = (CreateUserRequest) request;
                CreateUserResponse createUserResponse = getCreateUserResponse(createUserRequest);
                out.writeObject(createUserResponse);
            } else if (request instanceof LoginUserRequest) {
                LoginUserRequest loginUserRequest = (LoginUserRequest) request;
                LoginUserResponse loginUserResponse = getLoginUser(loginUserRequest);
                out.writeObject(loginUserResponse);
            }
            /* else if (request instanceof NewTypeOfRequest) {
                NewTypeOfRequest newRequest = (NewTypeOfRequest) request;
                NewTypeOfResponse newResponse = getNewTypeOfResponse(newRequest);
                out.writeObject(newResponse);
            }
            */

            close(clientSocket, out, in);

        }
    }

    private LoginUserResponse getLoginUser(LoginUserRequest loginUserRequest) {
        User user = userRepository.findUserByEmail(loginUserRequest.getEmail());

        if (user != null) {
            if (user.getPassword().equals(loginUserRequest.getPassword())) {
                System.out.println("ZNALEZIONO");
                return new LoginUserResponse("Hello: ", user);
            } else {
                return new LoginUserResponse("NOT FOUND USER", null);
            }
        }
        else {
            return new LoginUserResponse("NOT FOUND USER", null);
        }

    }

    private CreateUserResponse getCreateUserResponse(CreateUserRequest createUserRequest) {
        User user = new User();
        user.setEmail(createUserRequest.getEmail());
        user.setName(createUserRequest.getName());
        user.setSurname(createUserRequest.getSurname());
        user.setPassword(createUserRequest.getPassword());
        user.setPhone(createUserRequest.getPhone());
        User createdUser = userRepository.save(user);
        System.out.println("Add client");
        return new CreateUserResponse("REGISTER: " + createdUser.getName() + " " + createdUser.getSurname());
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
        reservation.setClient(createReservationRequest.getUser());
        Reservation createdReservation = reservationRepository.save(reservation); // reservation nie posiada id, wiec repository wykona operacje CREATE

        System.out.println("Dodano rezerwacje: " + createdReservation.getId());

        return new CreateReservationResponse("OK, RESERVATION ID: " + createdReservation.getId());
    }

    /**
     * @param listFlightRequest
     * @return
     */
    private ListFlightResponse getListFlightResponse(ListFlightRequest listFlightRequest) {
        Iterable<Flight> all = null;
        if (!(listFlightRequest.getOrigin().isEmpty() && listFlightRequest.getDestination().isEmpty())) {
            all = flightRepository.findByOriginCityAndDestinationCity(listFlightRequest.getOrigin(), listFlightRequest.getDestination());
        } else if (!(listFlightRequest.getArrivalDate().toString().isEmpty() && listFlightRequest.getDepartureDate().toString().isEmpty())) {
            all = flightRepository.findByDateArrivalAndAndDateDeparture(listFlightRequest.getArrivalDate().toString(), listFlightRequest.getDepartureDate().toString());
        }

        ListFlightResponse flightResponse = new ListFlightResponse();
        for (Flight flight : all) {
            flightResponse.insertToList(flight);
        }
        return flightResponse;
    }

    /**
     * @param clientSocket
     * @param out
     * @param in
     * @throws IOException
     */
    private void close(Socket clientSocket, ObjectOutputStream out, ObjectInputStream in) throws IOException {
        out.close();
        in.close();
        clientSocket.close();
    }
}


