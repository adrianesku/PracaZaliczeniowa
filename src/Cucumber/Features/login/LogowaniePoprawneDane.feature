Feature: Logowanie do sklepu


  Scenario: Użytkownik loguje się do serwisu prawidłowymi danymi

    Given Otwarta strona sklepu

    When wpisanie prawidłowego loginu oraz hasła

    Then Użytkownik został prawidłowo zalogowany do systemu