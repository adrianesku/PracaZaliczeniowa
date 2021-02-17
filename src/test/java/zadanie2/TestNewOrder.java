package zadanie2;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import zadanie1.PageLogin;

import java.util.concurrent.TimeUnit;


public class TestNewOrder {

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
        public void openWebsite() {
                // Przejdź do sklepu
                driver.get("https://prod-kurs.coderslab.pl/index.php?controller=authentication&back=my-account");
        }

        @When("Logowanie do sklepu loginem {string} i hasłem {string}")
        public void logowanieDoSklepu(String email, String password) {
                // użycie metody z clasy PageLogin która pozwoli na zalgowanie się na stronę
                PageLogin pageLogin = new PageLogin(driver);
                pageLogin.login(email,password);
        }

        @And("Wyszukanie i wybranie towaru {string} oraz sprawdzenie rabatu {string}")
        public void findProduct(String searchProduct, String disccount) {
                // użycie metod z clasy PageSearch, które znajdą i wybiorą produkt oraz sprawdzą rabat
                PageSearch pageSearch = new PageSearch(driver);
                pageSearch.searchProduct(searchProduct);
                pageSearch.selectProduct(searchProduct);
                Assert.assertEquals(disccount, pageSearch.checkDiscount());
        }

        @And("Wybranie rozmiaru {string} i ilości {string} oraz dodanie do koszyka i przejśćie do checkout")
        public void detailsOrder(String rozmiar, String ilosc) {
                // użycie metod z clasy DetailsPage, które wybiorą szczegóły zamówienia
                PageDetails pageDetails = new PageDetails(driver);
                pageDetails.setSize(rozmiar);
                pageDetails.setQuantity(ilosc);
                pageDetails.addToCart();
                pageDetails.goToCheckout();
        }

        @And("Uzupełnienie informacji o płątności i dostawie")
        public void detailsDelivery() {
                // stworzenie obikeut z klasy OrderPage, który ma na celu przejśćie przez szczegóły realizacji dostawy i płatności
                PageOrder pageOrder = new PageOrder(driver);
                pageOrder.setAddress();
                pageOrder.setShippingMethod();
                pageOrder.setPayment();
        }

        @Then("Potwierdzenie zamówienie w formie screenshota")
        public void confirmationOrder() throws Exception {
                // stworzenie obikeut z klasy ScreenShot, który ma na celu zrobić screenshot z potwierdzeniem zamówienia
                ScreenShot screenshot = new ScreenShot(driver);
                screenshot.takeSnapShot();
        }

    }

