package zadanie1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class PageNewAddress {
    private static WebDriver driver;

    // konstruktor do sterowników
    public PageNewAddress(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // ustalanie elementów strony wg nazwy lub id
    @FindBy(name = "alias")
    WebElement elementAlias;

    @FindBy(name = "address1")
    WebElement elementAddress;

    @FindBy(name = "city")
    WebElement elementCity;

    @FindBy(name = "postcode")
    WebElement elementZipCode;

    @FindBy(name = "phone")
    WebElement elementPhone;

    @FindBy(name = "phone")
    WebElement elementSubmitAddress;


    public void dodawanieAdresu(String alias, String address, String city, String zip, String country, String phone) {
        // wpisanie aliasu
        elementAlias.click();
        elementAlias.clear();
        elementAlias.sendKeys(alias);

        // wpisanie adresu
        elementAddress.click();
        elementAddress.clear();
        elementAddress.sendKeys(address);

        // wpisanie miasta
        elementCity.click();
        elementCity.clear();
        elementCity.sendKeys(city);

        // wpisanie kodu pocztowego
        elementZipCode.click();
        elementZipCode.clear();
        elementZipCode.sendKeys(zip);

        // wpisanie numeru
        elementPhone.click();
        elementPhone.clear();
        elementPhone.sendKeys(phone);

        // wybranie kraju z listy rozwijalnej (nowy obiekt z klasy Select)
        Select elementSelect = new Select(driver.findElement(By.name("id_country")));
        elementSelect.selectByVisibleText(country);

        // zatwierdzenie formularza
        elementSubmitAddress.submit();
    }
}
