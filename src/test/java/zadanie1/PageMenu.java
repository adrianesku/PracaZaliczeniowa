package zadanie1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PageMenu {
    private static WebDriver driver;

    // konstruktor do sterowników
    public PageMenu(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // ustalanie elementów strony wg nazwy lub id
    @FindBy(id = "addresses-link")
    WebElement elementAddresses;

    @FindBy(id = "addresses-link")
    WebElement elementAddress;

    @FindBy(partialLinkText = "Create new address")
    WebElement elementCreateNewAcc;

    public void goToEditAddress() {
        List<WebElement> lista = driver.findElements(By.id("addresses-link"));

        // sprawdzenie czy są już dodane jakieś adressy
        if (lista.size()>0) {
            System.out.println("Konto ma już przypisane inne adresy");
            System.out.println("===============================================================");
            elementAddresses.click();
            elementCreateNewAcc.click();
        } else {

            System.out.println("Nie ma żadnych adresów - dodaj pierwszy adres");
            System.out.println("===============================================================");
            elementAddress.click();
        }
    }

}
