package org.example.flightcontrol;

import org.example.flightcontrol.service.CacheService;
import org.example.flightcontrol.service.CheckInService;
import org.example.flightcontrol.service.CouponService;
import org.example.flightcontrol.service.TicketService;
import org.example.flightcontrol.service.impl.CacheServiceImpl;
import org.example.flightcontrol.service.impl.CheckInServiceImpl;
import org.example.flightcontrol.service.impl.TicketServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FlightControlApplicationTests {

    private TicketService ticketService;
    private CheckInService checkInService;
    private CacheService cacheService;

    @BeforeEach
    void setUp() {
        ticketService = new TicketServiceImpl();
        checkInService = new CheckInServiceImpl();
        checkInService = new CheckInServiceImpl();
        cacheService = new CacheServiceImpl();
    }

    @Test
    void testTicketAvailable() {
        int availableTicketId = 1;
        boolean isAvailable = ticketService.isTicketAvailable(availableTicketId);

        assertTrue(isAvailable);
    }

    @Test
    void testCheckInSuccess() {
        int destinationId = 1;
        int checkInId = 100;
        boolean result = checkInService.checkIn(destinationId, checkInId);

        assertTrue(result);
    }

    @Test
    void testCheckInDuplicate() {
        int destinationId = 1;
        int checkInId = 100;
        boolean firstCheckIn = checkInService.checkIn(destinationId, checkInId);
        boolean secondCheckIn = checkInService.checkIn(destinationId, checkInId);

        assertTrue(firstCheckIn);
        assertFalse(secondCheckIn);
    }
    @Test
    void testAddToCache() {
        String key = "testKey";
        String value = "testValue";
        cacheService.addToCache(key, value);

        assertEquals(value, cacheService.getFromCache(key));
    }

    @Test
    void testCacheEviction() {
        for (int i = 1; i <= 10; i++) {
            cacheService.addToCache("key" + i, "value" + i);
        }
        cacheService.addToCache("key11", "value11");
        assertNull(cacheService.getFromCache("key1"));
        assertNotNull(cacheService.getFromCache("key11"));
        for (int i = 2; i <= 10; i++) {
            assertNotNull(cacheService.getFromCache("key" + i));
        }
    }




}
