package zadanie2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPage {
    private static WebDriver driver;

    // konstruktor do sterowników
    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    // metoda wyszukiwania towarów
    public void searchProduct(String product) {
        WebElement search = driver.findElement(By.name("s"));
        search.click();
        search.clear();
        search.sendKeys(product);
        search.submit();
    }

    // metoda do wybrania towaru
    public void selectProduct(String product) {
        WebElement search = driver.findElement(By.partialLinkText(product));
        search.click();
    }

    // sprawdzenie czy jest rabat na produkcie
    public void checkDiscount(){
        List<WebElement> lista = driver.findElements(By.className("discount discount-percentage"));

        System.out.println(lista.size());

        if (lista.size()>0) {
            System.out.println("Rabat wynosi ");
            System.out.println("===============================================================");
        } else {
            System.out.println("Brak rabatu");
            System.out.println("===============================================================");
        }
    }

}
