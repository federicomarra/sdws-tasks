Feature: hello service
  Scenario: hello service returns correct text answer
    When I call the hello service
    Then I get the hello answer "Hello RESTEasy"

  Scenario: hello service returns correct text answer
    When I call the hello text service
    Then I get the hello answer "Hello RESTEasy"

  Scenario: hello service returns correct html answer
    When I call the hello html service
    Then I get the hello answer "<h1>Hello RESTEasy</h1>"

  Scenario: hello service returns correct json answer
    When I call the hello json service
    Then I get the hello answer "{\"message\":\"Hello RESTEasy\"}"

  Scenario: hello service returns correct xml answer
    When I call the hello xml service
    Then I get the hello answer "<greeting>Hello RESTEasy</greeting>"