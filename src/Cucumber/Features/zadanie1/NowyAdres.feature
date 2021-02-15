Feature: Dodawanie nowego adresu


  Scenario Outline: Użytkownik loguje się do serwisu i dodaje nowy adres

    Given Otwarta strona logowania do sklepu
    When Wpisanie prawidłowego loginu "Testowy@wp.pl" oraz hasła "Test123"
    And Przejście do edycji adresu
    And Wypełnienie formularza danymi: "<alias>" "<address>" "<city>" "<zip>" "<country>" "<phone>" i dodanie nowego adresu
    Then Informacja o prawidłowym dodaniu adresu
    And Usuniecie dodanego adresu
    And Potwierdzenie usuniecia adresu

    Examples:
    | alias      | address     | city        | zip    | country        | phone       |
    | testAlias  | adres test 1| TestoCity   | 59-140 | United Kingdom | 666 666 666 |
    | testAlias2 | adres test 2| TestoCity 2 | 66-540 | United Kingdom | 777 777 777 |
