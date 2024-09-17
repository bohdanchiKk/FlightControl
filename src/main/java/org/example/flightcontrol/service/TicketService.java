package org.example.flightcontrol.service;

public interface TicketService {
    boolean isTicketAvailable(int ticketId);
    void bookTicket(int ticketId);
}
