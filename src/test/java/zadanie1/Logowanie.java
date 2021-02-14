package zadanie1;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Logowanie {

    private WebDriver driver;

    @Given("Otwarta strona logowania do sklepu")
    public void otwarcieStrony() {
        // Skonfiguruj sterownik przeglądarki
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
        // Uruchom nowy egzemplarz przeglądarki Chrome
        driver = new ChromeDriver();
        // ustawienie czasu oczekiwania
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // Zmaksymalizuj okno przeglądarki
        driver.manage().window().maximize();
        // Przejdź do sklepu
        driver.get("https://prod-kurs.coderslab.pl/index.php?controller=authentication&back=my-account");
    }

    @When("Wpisanie prawidłowego loginu {string} oraz hasła {string}")
    public void logowanie(String email, String haslo) {

        //tworzenie dwóch tablic 1 z polami formularza druga z danymi do wypełnienia
        String[] tablica = {"email","password"};
        String[] tablica2 = {email,haslo};

        // wypełnienie pól formularza stringami
        for (int i = 0; i < tablica.length; i++) {
            // Znajdź element strony wg. jego nazwy
            WebElement elementLog = driver.findElement(By.name(tablica[i]));
            // Wyczyść teskst zapisany w elemencie (dla bezpieczeństwa gdyby coś już w nim było)
            elementLog.clear();
            // Uzupełnij pole logowania wg. dwóch tablic i pokaż na konsoli co gdzie wpisał
            elementLog.sendKeys(tablica2[i]);
            System.out.println(" Czy element jest dostępny: " + elementLog.isDisplayed());
            System.out.println(" Do pola " + tablica[i] + " wpisano wartość: " + elementLog.getAttribute("value"));
            System.out.println("===============================================================");
        }
        // potwierdź logowanie
        WebElement potwierdzLog = driver.findElement(By.id("submit-login"));
        potwierdzLog.click();
    }

    @And("Przejście do edycji adresu")
    public void przejscieDoEdycjiAdresu() {
        List<WebElement> lista = driver.findElements(By.id("addresses-link"));

        if (lista.size()>0) {
            WebElement element = driver.findElement(By.id("addresses-link"));
            System.out.println("Konto ma już przypisane inne adresy");
            System.out.println("===============================================================");
            element.click();
            WebElement element2 = driver.findElement(By.partialLinkText("Create new address"));
            element2.click();
        } else {
            WebElement element2 = driver.findElement(By.id("address-link"));
            System.out.println("Nie ma żadnych adresów - dodaj pierwszy adres");
            System.out.println("===============================================================");
            element2.click();
        }
    }

    @And("Wypełnienie formularza i dodanie nowego adresu")
    public void dodawanieAdresu() {
        //tworzenie dwóch tablic 1 z polami formularza druga z danymi do wypełnienia
        String[] tablica = {"alias","firstname","lastname","company","vat_number","address1", "address2", "postcode", "city", "phone" };
        String[] tablica2 = {"testAlias","Adrian","Mierzwiak","Coders Lab","123", "adres test 1", "adres test 2", "55-555", "TestoCity","666 666 666" };

        // wypełnienie pól formularza stringami
        for (int i = 0; i < tablica.length; i++) {
            // Znajdź element strony wg. jego nazwy
            WebElement elementFormularza = driver.findElement(By.name(tablica[i]));
            // Wyczyść teskst zapisany w elemencie (dla bezpieczeństwa gdyby coś już w nim było)
            elementFormularza.clear();
            // Uzupełnij formularz wg. dwóch tablic oraz pokaż na konsoli co gdzie zostało wpisane
            elementFormularza.sendKeys(tablica2[i]);
            System.out.println(" Czy element jest dostępny: " + elementFormularza.isDisplayed());
            System.out.println(" Do pola " + tablica[i] + " wpisano wartość: " + elementFormularza.getAttribute("value"));
            System.out.println("===============================================================");
        }
        // wybranie kraju z listy rozwijalnej (nowy obiekt z klasy Select)
        Select elementSelect = new Select(driver.findElement(By.name("id_country")));
        elementSelect.selectByVisibleText("United Kingdom");

        // zatwierdzenie formularza
        WebElement potwierdzAdres = driver.findElement(By.name("submitAddress"));
        potwierdzAdres.submit();
    }

    @Then("Informacja o prawidłowym dodaniu adresu")
    public void potwierdzenieDodaniaAdresu() {

        WebElement komunikat = driver.findElement(By.xpath("html/body/main/section/div/div/section/section/aside/div/article/ul/li"));
        //WebElement komunikat = driver.findElement(By.linkText("Address successfully added!"));
        List<WebElement> lista = driver.findElements(By.tagName("article"));
        System.out.println("Lista zawiera: " + lista.size() + " elementów");

        //sprawdzenie czy pojawił się komunikat potwierdzający oraz czy konto zawiera conajmniej jeden adres
        if (komunikat.isDisplayed() && lista.size()>0) {
            System.out.println("Prawidłowo dodano adres");
            System.out.println("===============================================================");
        } else {
            System.out.println("Coś poszło nie tak");
            System.out.println("===============================================================");
        }
    }

    @And("Usuniecie dodanego adresu")
    public void usuniecieAdresu() {
        // znajdź elementy typu "article" na stronie
        List<WebElement> lista = driver.findElements(By.tagName("article"));
        // wyświetl ile elmentów zostało znalezionych
        WebElement element = driver.findElement(By.xpath("html/body/main/section/div/div/section/section/div[" + (lista.size() - 1) + "]/article/div[2]/a[2]/span"));
        element.click();
    }

    @And("Potwierdzenie usuniecia adresu")
    public void potwierdzenieUsunieciaAdresu() {
        WebElement komunikat = driver.findElement(By.xpath("html/body/main/section/div/div/section/section/aside/div/article/ul/li"));
        //WebElement komunikat = driver.findElement(By.linkText("Address successfully deleted!"));

        //sprawdzenie czy pojawił się komunikat potwierdzający oraz czy konto zawiera conajmniej jeden adres
        if (komunikat.isDisplayed()) {
            System.out.println("Prawidłowo usunięto adres");
            System.out.println("===============================================================");
        } else {
            System.out.println("Coś poszło nie tak");
            System.out.println("===============================================================");
        }
    }
}
