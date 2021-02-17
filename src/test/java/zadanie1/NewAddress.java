package zadanie1;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

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
    public void openWebsite() {
        // Przejdź do sklepu
        driver.get("https://prod-kurs.coderslab.pl/index.php?controller=authentication&back=my-account");
    }

    @When("Wpisanie prawidłowego loginu {string} oraz hasła {string}")
    public void login(String email, String password) {
        // użycie metody z clasy PageLogin która pozwoli na zalgowanie się na stronę
        PageLogin pageLogin = new PageLogin(driver);
        pageLogin.login(email,password);
    }

    @And("Przejście do edycji adresu")
    public void goToEditAddress() {
        // użycie metody z clasy PageMenu która pozwoli na przejśćie w menu do edycji adresu
        PageMenu pageMenu = new PageMenu(driver);
        pageMenu.goToEditAddress();
    }

    @And("Wypełnienie formularza danymi: {string} {string} {string} {string} {string} {string} i dodanie nowego adresu")
    public void addAddress(String alias, String address, String city, String zip, String country, String phone) {
        // użycie metody z clasy PageNewAddress która pozwoli na wypełnienie formularza i dodanie adresu
        PageNewAddress pageNewAddress = new PageNewAddress(driver);
        pageNewAddress.addAddress(alias,address,city,zip,country,phone);
    }

    @Then("Informacja o prawidłowym dodaniu adresu {string}")
    public void verficationAddAddress(String message) {
        // użycie metody z clasy PageAddressMenu, która pozwoli na sprawdzenie komunikatu
            PageAdrdessMenu pageAdrdessMenu = new PageAdrdessMenu(driver);
            Assert.assertEquals(message, pageAdrdessMenu.getUpdateInformation());
        }

    @After
    public void exitTest() {
        // znajdź elementy typu "article" na stronie i usuń ostatni element (najonowszy adres)
        List<WebElement> lista = driver.findElements(By.tagName("article"));
        WebElement element = driver.findElement(By.xpath("html/body/main/section/div/div/section/section/div[" + (lista.size() - 1) + "]/article/div[2]/a[2]/span"));
        element.click();
        // użycie metody z clasy PageAddressMenu, która pozwoli na sprawdzenie komunikatu
        PageAdrdessMenu pageAdrdessMenu = new PageAdrdessMenu(driver);
        Assert.assertEquals("Address successfully deleted!", pageAdrdessMenu.getUpdateInformation());

        // Zamknij przeglądarkę
        driver.quit();
    }
}
