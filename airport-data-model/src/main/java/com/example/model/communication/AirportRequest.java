package com.example.model.communication;

import java.io.Serializable;

/**
 * Klasa jest modelem komunikacji pomiędzy serwerem a klientem,
 * klasa jest żądaniem od klienta do serwera informacji dotyczących lotnisk.
 */
public class AirportRequest implements Serializable {
    private String status;

}
