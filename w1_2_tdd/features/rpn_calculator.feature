Feature: Reverse Polish Notation (RPN) Calculator

  This is an example feature together with step definitions and
  production code implementation. Remove the example when you
  implement your own code.

  As a calculator user,
  I want to perform calculations using Reverse Polish Notation
  So that I can evaluate mathematical expressions efficiently without parentheses.

  Scenario: Successful evaluation of a simple RPN expression
    Given the RPN Calculator is initialized
    When I input the number 5
    And I input the number 3
    And I press plus
    Then the result is 8

