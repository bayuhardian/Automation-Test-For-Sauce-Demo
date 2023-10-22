Feature: Add to Cart and Checkout in Sauce Demo

  Scenario: Add a product to the cart and complete the checkout process
    Given User is on the Sauce Demo product page
    When User is logged in
    When User selects a product and adds it to the cart
    And User proceeds to checkout
    And User enters shipping information
    And User completes the purchase
    Then Purchase is successful
