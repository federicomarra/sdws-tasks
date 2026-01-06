Feature: person service
  Scenario: person service returns correct person
    When I call the GET person service
    Then I get the person answer "{\"address\":\"USA\",\"name\":\"Susan\"}"
    
  Scenario: change person service to new person
    Given I have a person called "Federico" from "Denmark"
    When I call the PUT person service
    Then I get the person answer "{\"address\":\"Denmark\",\"name\":\"Federico\"}"
    
  Scenario: person service returns changed person
    When I call the GET person service
    Then I get the person answer "{\"address\":\"Denmark\",\"name\":\"Federico\"}"
    
  Scenario: change person service to invalid address
    Given I have a person called "Federico" from "-none-"
    When I call the PUT person service
    Then I get the person error "ERROR: Address cannot be \"-none-\""

  Scenario: reset person service to default person
    Given I have a person called "Susan" from "USA"
    When I call the PUT person service
    Then I get the person answer "{\"address\":\"USA\",\"name\":\"Susan\"}"