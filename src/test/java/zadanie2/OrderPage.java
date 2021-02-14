package zadanie2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;


public class OrderPage {
    private static WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }
    public void setAddress(){
        // znajdź elementy typu "article" na stronie
        List<WebElement> lista = driver.findElements(By.tagName("article"));
        // ustalenie guzika potwierdzającego adress
        WebElement confirmAddress = driver.findElement(By.name("confirm-addresses"));
        // sprawdzenie czy są dodane jakieś adresy jeżeli tak to wybierz ostatni / jeżeli nie to dodaj nowy
        if (lista.size()>0) {
            WebElement element = driver.findElement(By.xpath("html/body/section/div/section/div/div[1]/section[2]/div/div/form/div[1]/article[" + lista.size() + "]/header/label/span[1]/input"));
            // sprawdzenie czy adres jest odznaczony
            if (!element.isSelected())
                element.click();
            confirmAddress.click();
        } else {
            //tworzenie dwóch tablic 1 z polami formularza druga z danymi do wypełnienia
            String[] tablica = {"firstname","lastname","company","vat_number","address1", "address2", "postcode", "city", "phone" };
            String[] tablica2 = {"Adrian","Mierzwiak","Coders Lab","123", "adres test 1", "adres test 2", "55-555", "TestoCity","666 666 666" };

            // wypełnienie pól formularza stringami
            for (int i = 0; i < tablica.length; i++) {
                // Znajdź element strony wg. jego nazwy
                WebElement elementFormularza = driver.findElement(By.name(tablica[i]));
                // Wyczyść teskst zapisany w elemencie (dla bezpieczeństwa gdyby coś już w nim było)
                elementFormularza.clear();
                // Uzupełnij formularz wg. dwóch tablic oraz pokaż na konsoli co gdzie zostało wpisane
                elementFormularza.sendKeys(tablica2[i]);
                System.out.println(" Czy element jest dostępny: " + elementFormularza.isDisplayed());
                System.out.println(" Do pola " + tablica[i] + " wpisano wartość: " + elementFormularza.getAttribute("value"));
                System.out.println("===============================================================");
            }
            // wybranie kraju z listy rozwijalnej (nowy obiekt z klasy Select)
            Select elementSelect = new Select(driver.findElement(By.name("id_country")));
            elementSelect.selectByVisibleText("United Kingdom");

            // zatwierdzenie formularza
            WebElement potwierdzAdres = driver.findElement(By.name("submitAddress"));
            potwierdzAdres.submit();
            confirmAddress.click();
        }
    }
    public void setShippingMethod(){
        WebElement element = driver.findElement(By.id("delivery_option_1"));
        WebElement submit = driver.findElement(By.name("confirmDeliveryOption"));
        // sprawdzenie czy opcja dostawy jest odznaczona
        if (!element.isSelected())
            element.click();
        submit.click();
    }

    public void setPayment(){
        WebElement element = driver.findElement(By.id("payment-option-1"));
        WebElement element2 = driver.findElement(By.id("conditions_to_approve[terms-and-conditions]"));
        WebElement submit = driver.findElement(By.xpath("html/body/section/div/section/div/div[1]/section[4]/div/div[3]/div[1]/button"));
        // sprawdzenie czy opcja płatności jest odznaczona
        if (!element.isSelected())
            element.click();
        // sprawdzenie czy akceptacja zasad jest odznaczona
        if (!element2.isSelected())
            element2.click();

        submit.click();
    }

}
