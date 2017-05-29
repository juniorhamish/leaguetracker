Feature: Association resource

  Scenario: create an association
    When I create association with name "Scottish Football Association"
    Then association with name "Scottish Football Association" should exist

  Scenario: association name must be unique
    Given I have an association with name "Test"
    When I create association with name "Test"
    Then I should get a 409 Conflict status in the response

  Scenario: association short name need not be unique
    Given I have an association with name "Scottish Football Association" and short name "SFA"
    When I create association with name "Test" and short name "SFA"
    Then association with name "Test" should exist

  Scenario: find association by name
    Given I have an association with name "Foo"
    When I search for association by name "Foo"
    Then I should get a 200 OK status in the response
    And the response should contain json with field "name" equal to "Foo"

  Scenario: find association by name that doesn't exist
    When I search for association by name "Bar"
    Then I should get a 404 Not Found status in the response

  Scenario: list all associations when none exist
    Given there are no associations
    When I list all associations
    Then I should get a 200 OK status in the response
    And there should be 0 results

  Scenario: list all associations when one exists
    Given there are no associations
    And I create association with name "Foo"
    When I list all associations
    Then I should get a 200 OK status in the response
    And there should be 1 result
    And the response should contain json with field "_embedded.associations[0].name" equal to "Foo"