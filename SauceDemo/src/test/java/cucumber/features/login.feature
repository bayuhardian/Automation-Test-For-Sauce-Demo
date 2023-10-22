Feature: Login to Sauce Demo

  Scenario: Login with valid credentials
    Given User is on the Sauce Demo login page
    When User enters valid username and password
    Then User is logged in successfully

  Scenario: Login with invalid credentials
    Given User is on the Sauce Demo login page
    When User enters invalid username and password
    Then User sees error message indicating login failure
