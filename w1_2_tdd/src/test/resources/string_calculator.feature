Feature: String Calculator
  I want to calculate the addition of numbers in a string

  Scenario: Add empty string
    Given the input is empty
    When I calculate the addition
    Then the result should be 0
    
  Scenario: Add two string numbers
    Given the input is "1,2"
    When I calculate the addition
    Then the result should be 3
    
  Scenario: Add multiple string numbers
    Given the input is "1,2,3,4,5"
    When I calculate the addition
    Then the result should be 15
  
  Scenario: Add string numbers with new lines
    Given the input is "1\n2,3"
    When I calculate the addition
    Then the result should be 6

  Scenario: Add string numbers with new lines
    Given the input is "1\n2,3"
    When I calculate the addition
    Then the result should be 6
    
  Scenario: multiple two string numbers
    Given the input is "10;20"
    When I calculate the product
    Then the result should be 200
    
  Scenario: multiple numbers
    Given the input is "10,2,8"
    When I calculate the product
    Then the result should be 160
    
  Scenario: multiple numbers with new lines
    Given the input is "2\n3//4"
    When I calculate the product
    Then the result should be 24

  Scenario: Handle invalid input of a letter for addition
    Given the input is "a,4"
    When I calculate the addition
    Then an error should be raised indicating invalid input
    
  Scenario: Handle invalid input of a negative number for addition
    Given the input is "-5;4"
    When I calculate the product
    Then an error should be raised indicating invalid input