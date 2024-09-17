package org.example.flightcontrol.service.impl;

import org.example.flightcontrol.service.CheckInService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class CheckInServiceImpl implements CheckInService {
    private static final Logger logger = LoggerFactory.getLogger(CheckInServiceImpl.class);
    private final Map<Integer, Integer> checkInMap = new HashMap<>();

    @Override
    public boolean checkIn(int destinationId, int checkInId) {
        int combinedKey = generateKey(destinationId, checkInId);

        if (checkInMap.containsKey(combinedKey)) {
            logger.info("Check-in attempt failed: Ticket with ID {} already checked in for destination {}.", checkInId, destinationId);
            return false;
        }

        checkInMap.put(combinedKey, checkInId);
        logger.info("Successful check-in: Ticket with ID {} checked in for destination {}.", checkInId, destinationId);
        return true;
    }

    private int generateKey(int destinationId, int checkInId) {
        return destinationId * 1000 + checkInId;
    }

}
