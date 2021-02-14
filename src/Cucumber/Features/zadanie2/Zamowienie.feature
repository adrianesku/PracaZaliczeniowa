Feature: Składanie zamówienia


  Scenario: Użytkownik loguje się do serwisu i składa zamówienie

    Given Otwarta strona logowania do sklepu
    When Logowanie do sklepu loginem "Testowy@wp.pl" i hasłem "Test123"
    And Wyszukanie i wybranie towaru "Hummingbird Printed Sweate"
    And Wybranie rozmiaru "M" i ilości "5" oraz dodanie do koszyka
    And Uzupełnienie danych do zamówienia
    And Potwierdzenie zamówienia
    Then Potwierdzenie zamówienie w formie screenshota