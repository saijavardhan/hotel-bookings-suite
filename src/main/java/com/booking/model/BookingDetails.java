package com.booking.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookingDetails {
    String firstName;
    String lastName;
    Double price;
    String isDepositPaid;
    String checkinDate;
    String checkoutDate;
}
