package zadanie1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageAdrdessMenu {
    private static WebDriver driver;

    // konstruktor do sterowników
    public PageAdrdessMenu(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".alert.alert-success")
    WebElement successInformation;

    public String getUpdateInformation() {
        return successInformation.getText();
    }
}
