Feature: Mail adress


  Scenario Outline: Add simple information
    Given I have opened "chrome" browser
    Given Navigate to page "https://login.mailchimp.com/signup/"
    Given I have written my email "<email>"
    Given I have written my username "<username>"
    Given I have written my password "<password>"
    When I click the Sign Up button
    Then Registration result is "<result>"

    Examples:
      | email                                                 | username                                                                                                           | password    | result          |
      | generated                                             | generated                                                                                                          | Igor1111!   | ok              |
      | Igor1@mail.com                                        | Igor01234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789 | Igor1112!   | usernametoolong |
      |                                                       | IgorM                                                                                                              | Igor11123!  | emailrequired   |
      | IgorM@mail.com                                        | Igor                                                                                                               | Igor111234! | usernameexists  |

