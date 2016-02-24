Feature: Test Button Press
  We are just testing a button press

  Scenario: Press the button and see text change
    Given I press the "Click Me" button
        Then I should see "You clicked the button!"
