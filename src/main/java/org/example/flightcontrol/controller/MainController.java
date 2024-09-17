package org.example.flightcontrol.controller;

import lombok.RequiredArgsConstructor;
import org.example.flightcontrol.service.CacheService;
import org.example.flightcontrol.service.CheckInService;
import org.example.flightcontrol.service.CouponService;
import org.example.flightcontrol.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainController {
    @Autowired
    private TicketService ticketService;
    @Autowired
    private CheckInService checkInService;
    @Autowired
    private CouponService couponService;
    @Autowired
    private CacheService cacheService;

    @GetMapping("/ticket/{ticketId}")
    public boolean isTicketAvailable(@PathVariable int ticketId) {
        String cacheKey = "ticket=" + ticketId;
        Object cachedResult = cacheService.getFromCache(cacheKey);
        if (cachedResult != null) {
            return (boolean) cachedResult;
        }
        boolean result = ticketService.isTicketAvailable(ticketId);
        cacheService.addToCache(cacheKey, result);
        return result;
    }

    @PostMapping("/checkIn/{destinationId}/{checkInId}")
    public boolean checkInBaggage(@PathVariable int destinationId, @PathVariable int checkInId) {
        return checkInService.checkIn(destinationId, checkInId);
    }

    @PostMapping("/coupon/{couponId}/{price}")
    public double applyCoupon(@PathVariable int couponId, @PathVariable double price) {
        return couponService.applyCoupon(couponId, price);
    }
}
