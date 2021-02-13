Feature: Dodawanie nowego adresu


  Scenario: Użytkownik loguje się do serwisu i dodaje nowy adres

    Given Otwarta strona logowania do sklepu
    When Wpisanie prawidłowego loginu "Testowy@wp.pl" oraz hasła "Test123"
    And Przejście do edycji adresu
    And Wypełnienie formularza i dodanie nowego adresu
    Then Informacja o prawidłowym dodaniu adresu
    And Usuniecie dodanego adresu
    And Potwierdzenie usuniecia adresu