Feature: Booking Stays
  As I user I should be able to book a stay

  Background: I am on booking stays page
    Given I navigate to booking stays page

  @BookingStays
  Scenario: I book a stay on booking stays page
    When I set destination to "Kopaonik"
    And I set check in date to "2023-01-25" and check out date to "2023-02-14"
    And I add "3" adults and "1" children with age "4" and "2" rooms
    And I click search button