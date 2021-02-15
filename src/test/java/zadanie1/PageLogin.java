package zadanie1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageLogin {

    private static WebDriver driver;

    // konstruktor do sterowników
    public PageLogin(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // ustalanie elementów strony wg nazwy lub id
    @FindBy(name = "email")
    WebElement elementEmail;

    @FindBy(name = "password")
    WebElement elementPassword;

    @FindBy(id = "submit-login")
    WebElement elementSubmit;

    // metoda do logowania wg. zmiennych e-mail i password
    public void logowanieNaStrone(String email, String password) {
        // wpisanie e-maila
        elementEmail.click();
        elementEmail.clear();
        elementEmail.sendKeys(email);

        // wpisanie hasła
        elementPassword.click();
        elementPassword.clear();
        elementPassword.sendKeys(password);

        // potwierdź logowanie
        elementSubmit.click();
    }
}
