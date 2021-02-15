package zadanie2;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;


public class ZadanieTest {

        WebDriver driver;

        @Given("Otwarta strona logowania do sklepu")
        public void otwarcieStronySklepu() {
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
        public void logowanieDoSklepu(String email, String haslo) {
                // stworzenie obikeut z klasy LoginPage, który ma na celu zalogowanie się do sklepu
                LoginPage loginPage = new LoginPage(driver);
                loginPage.loginAs(email, haslo);
        }

        @And("Wyszukanie i wybranie towaru {string}")
        public void wyszukanieTowaru(String searchProduct) {
                // stworzenie obikeut z klasy SearchPage, który ma znaleźć i wybrać towar oraz sprawdzić ewentualną zniżkę
                SearchPage searchPage = new SearchPage(driver);
                searchPage.searchProduct(searchProduct);
                searchPage.selectProduct(searchProduct);
                searchPage.checkDiscount();
        }

        @And("Wybranie rozmiaru {string} i ilości {string} oraz dodanie do koszyka i przejśćie do checkout")
        public void szczegolyZamowienia(String rozmiar, String ilosc) {
                // stworzenie obikeut z klasy DetailsPage, który ma na celu ustalić szczegóły zamówienia i przejść do realizacji zamówienia
                DetailsPage detailsPage = new DetailsPage(driver);
                detailsPage.setSize(rozmiar);
                detailsPage.setQuantity(ilosc);
                detailsPage.addToCart();
                detailsPage.goToCheckout();
        }

        @And("Uzupełnienie informacji o płątności i dostawie")
        public void szczegolyDostawy() {
                // stworzenie obikeut z klasy OrderPage, który ma na celu przejśćie przez szczegóły realizacji dostawy i płatności
                OrderPage orderPage = new OrderPage(driver);
                orderPage.setAddress();
                orderPage.setShippingMethod();
                orderPage.setPayment();
        }

        @Then("Potwierdzenie zamówienie w formie screenshota")
        public void potwierdzenieZamowienia() throws Exception {
                // stworzenie obikeut z klasy ScreenShot, który ma na celu zrobić screenshot z potwierdzeniem zamówienia
                ScreenShot screenshot = new ScreenShot(driver);
                screenshot.takeSnapShot();
        }

    }

