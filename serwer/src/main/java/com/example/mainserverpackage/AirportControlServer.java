package com.example.mainserverpackage;

import com.example.model.communication.*;
import com.example.model.database.Airport;
import com.example.model.database.Flight;
import com.example.model.database.Reservation;
import com.example.model.database.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Klasa odpowiada za kontrole nad serwerem.
 * Tworzy oraz udostępnia socket na którym klient może się komunikować
 * Obługuje żądania klienta
 */
@Component
public class AirportControlServer implements Serializable {
    private ServerSocket serverSocket;

    Logger logger = LoggerFactory.getLogger(AirportControlServer.class);
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AirportRepository airportRepository;

    /**
     * Metoda do uruchomienia  serwerem.
     * Tworzy socket na danym poracie oraz otwiera strumienie do obierania danych od klienta.
     * Metoda czeka na nadchodzące żadania(Request) i odpowida na nie (response)
     *
     * @param port port na którym serwer komunikuje się z aplikacją kliencka
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void start(int port) throws IOException, ClassNotFoundException {
        // uruchamiamy socket serwera, do którego może połączyć się klient
        serverSocket = new ServerSocket(port);
        logger.info("START SERVER");
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
            } else if (request instanceof CreateUserRequest) {
                CreateUserRequest createUserRequest = (CreateUserRequest) request;
                CreateUserResponse createUserResponse = getCreateUserResponse(createUserRequest);
                out.writeObject(createUserResponse);
            } else if (request instanceof LoginUserRequest) {
                LoginUserRequest loginUserRequest = (LoginUserRequest) request;
                LoginUserResponse loginUserResponse = getLoginUser(loginUserRequest);
                out.writeObject(loginUserResponse);
            } else if (request instanceof AirportRequest) {
                AirportRequest airportRequest = (AirportRequest) request;
                AirportResponse airportResponse = getAirportBase(airportRequest);
                out.writeObject(airportResponse);
            } else if (request instanceof FindReservationRequest) {
                FindReservationRequest findReservationRequest = (FindReservationRequest) request;
                FindReservationResponse findReservationResponse = findReservation(findReservationRequest);
                out.writeObject(findReservationResponse);
            }


            close(clientSocket, out, in);

        }
    }

    /**
     * Metoda do obsługi żądań dotyczących rezerwacji
     *
     * @param findReservationRequest żądanie od klienta
     * @return odpwoiedź do klienta
     */
    private FindReservationResponse findReservation(FindReservationRequest findReservationRequest) {
        Iterable<Reservation> reservations = reservationRepository.findByClient_Id(findReservationRequest.getUserId());
        if (reservations == null) {
            return new FindReservationResponse("NOT FOUND RESERVATION", null);
        } else {
            ArrayList<Reservation> reservationArrayList = new ArrayList<Reservation>();
            for (Reservation reservation : reservations) {
                reservationArrayList.add(reservation);
            }
            return new FindReservationResponse("FOUND RESERVATION", reservationArrayList);

        }
    }

    /**
     * Metoda do obsługi żądań dotyczących lotniska
     *
     * @param airportRequest żądanie od klienta dotyczące danych z lotniksa
     * @return  pobrane informacjie z bazy dotyczące lotniska
     */
    private AirportResponse getAirportBase(AirportRequest airportRequest) {
        Iterable<Airport> airports = airportRepository.findAll();
        if (airports == null) {
            return new AirportResponse("NOT FOUND AIRPORTS", null);
        } else {
            return new AirportResponse("FOUND AIRPORTS", airports);
        }

    }

    ;

    /**
     * Metoda do obsługi żądań dotyczącej użykowników
     *
     * @param loginUserRequest żądanie od klienta dotyczące użykowników
     * @return odpowiedź
     */
    private LoginUserResponse getLoginUser(LoginUserRequest loginUserRequest) {
        User user = userRepository.findUserByEmail(loginUserRequest.getEmail());

        if (user != null) {
            if (user.getPassword().equals(loginUserRequest.getPassword())) {
                logger.info("FOUND");
                return new LoginUserResponse("Hello: ", user);
            } else {
                return new LoginUserResponse("NOT FOUND USER", null);
            }
        } else {
            return new LoginUserResponse("NOT FOUND USER", null);
        }

    }

    /**
     * @param createUserRequest
     * @return
     */
    private CreateUserResponse getCreateUserResponse(CreateUserRequest createUserRequest) {
        User user = new User();

        user.setEmail(createUserRequest.getEmail());
        user.setName(createUserRequest.getName());
        user.setSurname(createUserRequest.getSurname());
        user.setPassword(createUserRequest.getPassword());
        user.setPhone(createUserRequest.getPhone());

        if (createUserRequest.getTypeCreate().matches("edit")) {
            user.setId(createUserRequest.getId());
            User createdUser = userRepository.save(user);
            logger.info("Edit client");
            return new CreateUserResponse("MODYFIED: " + user.getName(), user);
        } else if (createUserRequest.getTypeCreate().matches("add")) {
            User chceckUser = userRepository.findUserByEmail(user.getEmail());
            if (chceckUser != null) {
                return new CreateUserResponse("THIS EMAIL IS USED: ");
            } else {
                User createdUser = userRepository.save(user);
                logger.info("Add client");
                return new CreateUserResponse("CREATED: " + user.getName());
            }
        } else {
            return new CreateUserResponse("Error", null);
        }
    }

    private CreateReservationResponse getCreateReservationResponse(CreateReservationRequest createReservationRequest) throws IOException {

        Optional<Flight> flightOptional = flightRepository.findById(createReservationRequest.getFlightId());


        if (!flightOptional.isPresent()) {
            return new CreateReservationResponse("INCORRECT FLIGHT ID - NO SUCH FLIGHT IN DB");
        }

        Flight flight = flightOptional.get();


        int numberSeats = flight.getNumberSeats();
        if (numberSeats == 0) {
            return new CreateReservationResponse("NOT ENOUGH SEATS FOR FLIGHT");
        }


        int newNumberOfSeats = numberSeats - 1;
        flight.setNumberSeats(newNumberOfSeats);
        flightRepository.save(flight); // flight posiada id, więc repository wykona UPDATE na tym locie


        Reservation reservation = new Reservation();
        reservation.setFlight(flight);
        reservation.setPassengerName(createReservationRequest.getPassengerName());
        reservation.setPassengerSurname(createReservationRequest.getPassengerSurname());
        reservation.setClient(createReservationRequest.getUser());
        Reservation createdReservation = reservationRepository.save(reservation); // reservation nie posiada id, wiec repository wykona operacje CREATE

        logger.info("Dodano rezerwacje: " + createdReservation.getId());

        return new CreateReservationResponse("OK, RESERVATION ID: " + createdReservation.getId());

    }

    /**
     * Metoda do pobiera odpowiednie dane z bazy, określone w żądaniu i tworzy z nich odpowiedź dla klienta.
     *
     * @param listFlightRequest żądanie od klienta
     * @return odpowiedź dla klienta dotycząca lotów
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
     * Metoda zamyka połączenie z serwerem
     *
     * @param clientSocket socket serwera
     * @param out          strumien wyjściowy serwera
     * @param in           strumień wejściowy serwera
     * @throws IOException
     */
    private void close(Socket clientSocket, ObjectOutputStream out, ObjectInputStream in) throws IOException {
        out.close();
        in.close();
        clientSocket.close();
    }
}


