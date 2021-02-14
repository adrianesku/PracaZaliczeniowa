package zadanie2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DetailsPage {
    private static WebDriver driver;

    public DetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setSize(String s){
        Select size = new Select(driver.findElement(By.id("group_1")));
        size.selectByVisibleText(s);
    }

    public void setQuantity(String q){
        WebElement quantity = driver.findElement(By.name("qty"));
        quantity.clear();
        quantity.sendKeys(q);
    }
    public void addToCart(){
        WebElement cart = driver.findElement(By.className("btn btn-primary add-to-cart"));
        cart.click();
    }
}
