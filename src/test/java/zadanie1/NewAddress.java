package zadanie1;

import io.cucumber.java.Before;
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

public class NewAddress {
    WebDriver driver;

    @Before
    public void setUp() {
        // Skonfiguruj sterownik przeglądarki
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
        // Uruchom nowy egzemplarz przeglądarki Chrome
        driver = new ChromeDriver();
        // ustawienie czasu oczekiwania
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // Zmaksymalizuj okno przeglądarki
        driver.manage().window().maximize();
    }

    @Given("Otwarta strona logowania do sklepu")
    public void otwarcieStrony() {
        // Przejdź do sklepu
        driver.get("https://prod-kurs.coderslab.pl/index.php?controller=authentication&back=my-account");
    }

    @When("Wpisanie prawidłowego loginu {string} oraz hasła {string}")
    public void logowanie(String email, String haslo) {
        // użycie metody z clasy PageLogin która pozwoli na zalgowanie się na stronę
        PageLogin pageLogin = new PageLogin(driver);
        pageLogin.logowanieNaStrone(email,haslo);
    }

    @And("Przejście do edycji adresu")
    public void przejscieDoEdycji() {
        PageMenu pageMenu = new PageMenu(driver);
        pageMenu.przejscieDoEdycjiAdresu();
    }

    @And("Wypełnienie formularza danymi: {string} {string} {string} {string} {string} {string} i dodanie nowego adresu")
    public void nowyAdres(String alias, String address, String city, String zip, String country, String phone) {
        PageNewAddress pageNewAddress = new PageNewAddress(driver);
        pageNewAddress.dodawanieAdresu(alias,address,city,zip,country,phone);
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
