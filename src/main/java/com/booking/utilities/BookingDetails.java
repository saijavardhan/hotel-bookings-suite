package com.booking.utilities;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookingDetails {
    String firstName;
    String lastName;
    String price;
    String isDepositPaid;
    String checkinDate;
    String checkoutDate;
}
