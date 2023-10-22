Feature: Logout Functionality in Sauce Demo

  Scenario: User Logout
    Given User is logged in to Sauce Demo
    When User logs out from the application
    Then User is redirected to the login page
