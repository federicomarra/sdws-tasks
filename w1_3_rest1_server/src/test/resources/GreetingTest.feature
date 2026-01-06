Feature: hello service
  Scenario: hello service returns correct text answer
    When I call the hello text service
    Then I get the answer "Hello RESTEasy"
    
  Scenario: hello service returns correct html answer
    When I call the hello html service
    Then I get the answer "<h1>Hello RESTEasy</h1>"
    
  Scenario: hello service returns correct json answer
    When I call the hello json service
    Then I get the answer "{\"message\":\"Hello RESTEasy\"}"
      
  Scenario: hello service returns correct xml answer
    When I call the hello xml service
    Then I get the answer "<message>Hello RESTEasy</message>"