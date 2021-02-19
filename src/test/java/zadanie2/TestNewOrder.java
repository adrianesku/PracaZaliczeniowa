package zadanie2;

import io.cucumber.java.After;
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
                // użycie metod z clasy PageDetails, które wybiorą szczegóły zamówienia (ilość / rozmiar)
                PageDetails pageDetails = new PageDetails(driver);
                pageDetails.setSize(rozmiar);
                pageDetails.setQuantity(ilosc);
                pageDetails.addToCart();
                pageDetails.goToCheckout();
        }

        @And("Uzupełnienie informacji o płątności i dostawie")
        public void detailsDelivery() {
                // użycie metod z clasy PageOrder, która ustali szczegóły dostawy
                PageOrder pageOrder = new PageOrder(driver);
                pageOrder.setAddress();
                pageOrder.setShippingMethod();
                pageOrder.setPayment();
        }

        @Then("Potwierdzenie zamówienie w formie screenshota")
        public void confirmationOrder() throws Exception {
                // użycie metod z clasy ScreenShot, która zrobi zdjęcie z potwierdzeniem zamówienia i zapisze w folderze z rezpozytorium
                ScreenShot screenshot = new ScreenShot(driver);
                screenshot.takeSnapShot();
        }

        @After
        public void exitTest() throws InterruptedException {
                // zapisanie numeru zamówienia
                PageHistoryOrders pageHistoryOrders = new PageHistoryOrders(driver);

                // zapisz do zmiennych cenę i numer zamówienia
                String fullPrice = pageHistoryOrders.getPriceOrder();
                String fullOrder = pageHistoryOrders.getNumberOrder();
                String nrOrder = fullOrder.substring(17, fullOrder.length());

                // wypisz dane o cenie i numerze zamówienia na konsoli
                System.out.println("===== SZUKAMY ZAMÓWIENIA =====");
                System.out.println(fullOrder);
                System.out.println(nrOrder);
                System.out.println("Cena zamówienia: " + fullPrice);
                System.out.println("==============================");

                // przejście do listy/histori zamówień
                PageTopMenu pageTopMenu = new PageTopMenu(driver);
                pageTopMenu.goToUserMenu();
                pageTopMenu.goToHistoryOrders();

                // informacje o ilości i liście zamówień
                pageHistoryOrders.infoOrders();
                pageHistoryOrders.findeOrder(nrOrder);

                // Zamknij przeglądarkę
                driver.quit();
        }
    }

