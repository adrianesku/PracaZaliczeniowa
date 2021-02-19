package zadanie2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PageHistoryOrders {
    private static WebDriver driver;

    // konstruktor do sterowników
    public PageHistoryOrders(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    // pobierz numer zamówienia
    public String getNumberOrder() {
        WebElement elementNumberOrder = driver.findElement(By.cssSelector("ul:nth-child(2) > li:nth-child(1)"));
        return elementNumberOrder.getText();
    }

    // pobierz cenę zamówienia
    public String getPriceOrder() {
        WebElement elementPrice = driver.findElement(By.cssSelector(".font-weight-bold > td:nth-child(2)"));
        return elementPrice.getText();
    }

    // wypisz na konsolę ilość zamówień (-1 do size ze wzgląd na nagłówki)
    public void infoOrders() {
        List<WebElement> lista = driver.findElements(By.tagName("tr"));
        System.out.println("Ilość zamówień: " + (lista.size() - 1));

    // pętla zrobi na konsoli listę zamówień
        for(int i=1; i<lista.size(); i++){
            WebElement elementOrder = driver.findElement(By.cssSelector("tbody > tr:nth-child(" + i + ") > th"));
            System.out.println("Zamówienie " + i + " to: " + elementOrder.getText());
        }
    }

    // sprawdzenie danych zamówienia
    public void findeOrder(String order) {
        List<WebElement> lista = driver.findElements(By.tagName("tr"));

    // pętla sprawdzi każdą nazwę zamówienia i wyciągnię dane z zamówienia które nas interesuje
        for (int i = 1; i < lista.size(); i++) {
            WebElement elementOrder = driver.findElement(By.cssSelector("tbody > tr:nth-child(" + i + ") > th"));

            if (order.equals(elementOrder.getText())) {
                WebElement elementPrice =  driver.findElement(By.cssSelector("tr:nth-child(" + i + ") > .text-xs-right"));
                WebElement elementStatus =  driver.findElement(By.cssSelector("tr:nth-child(" + i + ") .label"));
                System.out.println("====== Informacje o zamówieniu ======");
                System.out.println("Cena zamówienia: " + elementPrice.getText());
                System.out.println("Status zamówienia: " + elementStatus.getText());
                System.out.println("=====================================");
            }
        }
    }
}
