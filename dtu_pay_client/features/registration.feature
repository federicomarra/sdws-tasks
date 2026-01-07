Feature: Registration
  Scenario: Successful Customer Registration
    Given a customer with name "Alice"
    When the customer registers with Simple DTU Pay
    Then the registration is successful

  Scenario: Successful Merchant Registration
    Given a merchant with name "Daniel"
    When the merchant registers with Simple DTU Pay
    Then the registration is successful