Feature: Mail adress

  Background:
    Given Navigate to page "https://login.mailchimp.com/signup/"

  Scenario: Add simple information
    Given I have written my email "Igor@mail.com"
    Given I have written my username "Igor"
    Given I have written my password "Igor111!"
    When I click the Sign Up button
    Then my email is open

  Scenario: Add simple information
    Given I have written my email "Igor@mail.com"
    Given I have written my username "Igor01234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789"
    Given I have written my password "Igor111!"
    When I click the Sign Up button
    Then long username

  Scenario: Add simple information
    Given I have written my email ""
    Given I have written my username "Igor"
    Given I have written my password "Igor111!"
    When I click the Sign Up button
    Then missing email