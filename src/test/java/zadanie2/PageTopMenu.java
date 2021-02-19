package zadanie2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class PageTopMenu {

    private static WebDriver driver;

    // konstruktor do sterowników
    public PageTopMenu(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goToUserMenu(){
        WebElement elementUserMenu = driver.findElement(By.cssSelector(".account > .hidden-sm-down"));
        elementUserMenu.click();
    }

    public void goToHistoryOrders(){
        WebElement elementHistoryOrders = driver.findElement(By.cssSelector("#history-link .material-icons"));
        elementHistoryOrders.click();
    }
}
