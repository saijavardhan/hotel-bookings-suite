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
    Scenario: Booking test
      When I tet it
      Then I should see this