package zadanie2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class PageDetails {
    private static WebDriver driver;

    // konstruktor do sterowników
    public PageDetails(WebDriver driver) {
        this.driver = driver;
    }

    // metoda do ustalenia rozmiaru towaru
    public void setSize(String s){
        Select size = new Select(driver.findElement(By.id("group_1")));
        size.selectByVisibleText(s);
    }

    // metoda do ustalenia ilości towaru
    public void setQuantity(String q){
        WebElement quantity = driver.findElement(By.name("qty"));
        quantity.clear();
        quantity.click();
        quantity.sendKeys(q);
    }

    // metoda dodająca towar do koszyku
    public void addToCart(){
        WebElement cart = driver.findElement(By.cssSelector(".add-to-cart"));
        cart.click();
    }

    // metoda przechodząca do szczegółów zamówienia
    public void goToCheckout(){
        WebElement checkout = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div[2]/div/div/a"));
        checkout.click();
        WebElement checkout2 = driver.findElement(By.xpath("/html/body/main/section/div/div/section/div/div[2]/div[1]/div[2]/div/a"));
        checkout2.click();
    }
}
