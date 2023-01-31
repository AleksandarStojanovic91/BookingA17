Feature: Booking Car Rentals
  As a user I should be able to book a car rental

  Background: I am on booking car rentals page
    Given I navigate to booking car rentals page

    @BookingCarRentals
    Scenario: I book a car rental on booking page
      When I set pickup location to "Paris" and same return location "No" and return location is "Rome"
      And I set drivers age to "25"
      And I set from date to "April 15 2023, 11:15" and until date to "May 20 2023, 11:15"
      And I click search button on car rentals
