package zadanie2;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.hu.De;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;


public class ZadanieTest {

        WebDriver driver;

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

        @When("Logowanie do sklepu loginem {string} i hasłem {string}")
        public void logowanie(String email, String haslo) {
                LoginPage loginPage = new LoginPage(driver);
                loginPage.loginAs(email, haslo);
        }

        @And("Wyszukanie i wybranie towaru {string}")
        public void wyszukanieTowaru(String searchProduct) {
                SearchPage searchPage = new SearchPage(driver);
                searchPage.searchProduct(searchProduct);
                searchPage.selectProduct(searchProduct);
                searchPage.checkDiscount();
        }

        @And("Wybranie rozmiaru {string} i ilości {string} oraz dodanie do koszyka")
        public void szczegolyZamowienia(String rozmiar, String ilosc) {
                DetailsPage detailsPage = new DetailsPage(driver);
                detailsPage.setSize(rozmiar);
                detailsPage.setQuantity(ilosc);
                detailsPage.addToCart();
        }


        @And("Uzupełnienie danych do zamówienia")
        public void c() {

        }

        @And("Potwierdzenie zamówienia")
        public void d() {

        }

        @Then("Potwierdzenie zamówienie w formie screenshota")
        public void e() {

        }

    }

