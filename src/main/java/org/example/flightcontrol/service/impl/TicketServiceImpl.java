package org.example.flightcontrol.service.impl;

import lombok.Setter;
import org.example.flightcontrol.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public class TicketServiceImpl implements TicketService {
    private static final Logger logger = LoggerFactory.getLogger(TicketServiceImpl.class);
    private final Map<Integer, Boolean> tickets = Map.of(
            1, true,
            2, false,
            3, true,
            4, false,
            5, true
    );

    @Override
    public boolean isTicketAvailable(int ticketId) {
        boolean isAvailable = tickets.getOrDefault(ticketId, false);
        if (isAvailable) {
            logger.info("Ticket ID {} is available.", ticketId);
        } else {
            logger.info("Ticket ID {} is not available.", ticketId);
        }
        return isAvailable;
    }

    @Override
    public void bookTicket(int ticketId) {
        if (!tickets.containsKey(ticketId)) {
            logger.warn("Attempt to book a non-existent ticket: ID {}", ticketId);
            throw new IllegalArgumentException("Ticket does not exist");
        }

        if (tickets.get(ticketId)) {
            tickets.put(ticketId, false);
            logger.info("Ticket ID {} has been booked successfully.", ticketId);
        } else {
            logger.warn("Attempt to book an already booked ticket: ID {}", ticketId);
            throw new IllegalStateException("Ticket already booked");
        }
    }
}
