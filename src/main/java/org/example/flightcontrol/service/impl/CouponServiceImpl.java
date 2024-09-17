package org.example.flightcontrol.service.impl;

import org.example.flightcontrol.service.CouponService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;

@Service
public class CouponServiceImpl implements CouponService {
    private static final Logger logger = LoggerFactory.getLogger(CouponServiceImpl.class);
    private final Map<Integer, Boolean> coupons = Map.of(
            1, true,
            2, true,
            3, false,
            4, true,
            5, false
    );

    private final Random random = new Random();
    private final double[] discounts = {0.1, 0.5, 0.6};

    @Override
    public double applyCoupon(int couponId, double ticketPrice) {
        boolean isValidCoupon = coupons.getOrDefault(couponId, false);
        if (!isValidCoupon) {
            logger.warn("Attempt to apply invalid coupon: ID {}", couponId);
            throw new IllegalArgumentException("Coupon is not applied");
        }

        double discount = discounts[random.nextInt(discounts.length)];
        double discountedPrice = ticketPrice * (1 - discount);

        logger.info("Coupon applied: ID {}. Original price: ${}, Discount: {}%, Final price: ${}",
                couponId, ticketPrice, discount * 100, discountedPrice);

        return discountedPrice;
    }
}
