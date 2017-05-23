Feature: Association resource

  Scenario: create an association
    When I create association with name "Scottish Football Association"
    Then association with name "Scottish Football Association" should exist
