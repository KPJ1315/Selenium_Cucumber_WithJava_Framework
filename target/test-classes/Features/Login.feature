@run
Feature: Doctor Login

  @TC1
  Scenario: Doctor login with valid credentials
    Given User opens the browser
    When User opens the URL "https://acianadocisnuiprod-staging.azurewebsites.net/user/signIn"
    Then User clicks on sign-in
    And  User enters email "karthik.jambagi@archents.com" and password "Kpj@4321" and clicks login
    Then User lands on provider homepage
    When User clicks on logout
    Then User closes browser

    @TC2
    Scenario Outline: Doctor login with invalid credentials
      Given User opens the browser
      When User opens the URL "https://acianadocisnuiprod-staging.azurewebsites.net/user/signIn"
      Then User clicks on sign-in
      And  User enters email "<username>" and password "<password>" and clicks login
      And  User is shown error message
      Then User closes browser
      Examples:
        | username          | password |
        | karthik@gmail.com | Kpj@4321 |
        | karthik                      | Kpj@4321 |
        | karthik.jambagi@archents.com | Kpj@1234 |
        |                              | Kpj@1234 |
        | karthik.jambagi@archnts.com  | Kpj@1234 |
        | karthik.jambagi@archnts.com  |          |
        |                              |          |




