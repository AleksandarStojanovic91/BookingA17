Feature: Categories

  Scenario Outline: Categories load and present data

    Given I am on KP home page
    When I click on category "<category>"
    Then I should see results for category "<category>"
    And I should see the url for the "<category>"

    Examples:
      | category                     |
      | Alati i oruđa                |
      | Audio \| Vinili, CD i kasete |