Feature: Student Service

  Scenario Outline: Register and find a student
    Given I have a student with name <name> and city <city>
    When I register the student
    Then I get an ID
    When I search for the student by that ID
    Then I get the student answer with name <name> and city <city>
    Examples:
      | name       | city       |
      | "Federico" | "Florence" |
      | "Alberto"  | "Padova"   |

  Scenario Outline: Update a student city
    Given I have a student with name <name> and city <city>
    When I register the student
    And I update the student's city to <newCity>
    And I search for the student by that ID
    Then I get the student answer with name <name> and city <newCity>
    Examples:
      | name       | city       | newCity      |
      | "Federico" | "Florence" | "Copenhagen" |
      | "Alberto"  | "Padova"   | "Copenhagen" |
    
  Scenario: Delete a student
    Given I have a student with name "Federico" and city "Copenhagen"
    When I register the student
    And I delete the student
    When I search for the student by that ID
    Then I get the error "Not Found"

  Scenario: Search non-existent student
    When I search for the student by id "non-existent-id"
    Then I get the error "Not Found"