package zadanie2;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class ScreenShot {

    private static WebDriver driver;

    // konstruktor do sterowników
    public ScreenShot(WebDriver driver) {
        this.driver = driver;
    }

    public void takeSnapShot() throws Exception{

        //Convert web driver object to TakeScreenshot
        TakesScreenshot scrShot =((TakesScreenshot)driver);

        //Call getScreenshotAs method to create image file
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

        //Move image file to new destination
        File DestFile = new File("c://test.png");

        //Copy file at destination
        FileUtils.copyFile(SrcFile, DestFile);

    }
}
