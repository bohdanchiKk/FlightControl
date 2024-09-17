Build the Application:
mvn clean install

Run the Application:
mvn spring-boot:run

Endpoints
1. Check Ticket Availability
   
    Endpoint: GET /ticket/{ticketId}
    Parameters:
        ticketId: The ID of the ticket to check.
    Response: Returns a boolean indicating the availability of the ticket.

3. Check-In Baggage
   
    Endpoint: POST /checkIn/{destinationId}/{checkInId}
    Parameters:
        destinationId: The ID of the destination.
        checkInId: The ID of the baggage to check in.
    Response: Returns a boolean indicating whether the check-in was successful.

4. Apply Coupon

    Endpoint: POST /coupon/{couponId}/{price}
    Parameters:
        couponId: The ID of the coupon.
        price: The original price of the ticket.
    Response: Returns the new price after applying the coupon.
