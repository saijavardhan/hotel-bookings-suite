@booking
Feature: Hotel bookings

  Background: Open the hotel booking page
    Given I am on the Hotel Booking page

  @saveBooking
  Scenario: Save a booking from today for 2 days
    When I book the hotel with below details
      | firstName      | lastName      | totalAmount | isdepositpaid | checkin | checkout |
      | firstNameHotel | lastNameHotel | 100         | true          | TODAY   | TODAY+2  |
    Then I should see a hotel booked with below details
      | firstName      | lastName      | totalAmount | isdepositpaid | checkin | checkout |
      | firstNameHotel | lastNameHotel | 100         | true          | TODAY   | TODAY+2  |

  @deleteBooking
  Scenario: Delete a booking deletes the booking
    And I have an existing hotel booking
      | firstName      | lastName      | totalAmount | isdepositpaid | checkin | checkout |
      | firstNameHotel | lastNameHotel | 100         | false         | TODAY   | TODAY+3  |
    When I delete the booking
    Then the booking should get deleted

  @manual
  Scenario: checkout date should not be prior to checkin date
    And I give checkin date as TODAY+1
    And I give checkout date as TODAY
    When I try to book the hotel
    Then I should see an error message
    And the booking is not made

  @manual
  Scenario: All the fields are mandatory to make a booking
    When I try to book the hotel without specifying any one field
    Then I should see an error specific to that field
    And the booking is not made

  @manual
  Scenario: Price should accept only numeric values
    And I pass price as "abc"
    And I specify all the other fields with valid values
    When I try to book the hotel
    Then the price filed should display an error message
    And the booking is not made

