package zadanie2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageSearch {
    private static WebDriver driver;

    // konstruktor do sterowników
    public PageSearch(WebDriver driver) {
        this.driver = driver;
    }

    // pobranie informacji o rabacie
    public String checkDiscount() {
        WebElement discountInformation = driver.findElement(By.cssSelector(".discount-percentage"));
        return discountInformation.getText();
    }

    // metoda wyszukiwania towarów
    public void searchProduct(String product) {
        WebElement elementSearch = driver.findElement(By.name("s"));
        elementSearch.click();
        elementSearch.clear();
        elementSearch.sendKeys(product);
        elementSearch.submit();
    }

    // metoda do wybrania towaru
    public void selectProduct(String product) {
        WebElement search = driver.findElement(By.partialLinkText(product));
        search.click();
    }

}
