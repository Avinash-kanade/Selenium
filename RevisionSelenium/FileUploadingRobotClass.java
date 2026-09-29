package RevisionSelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class FileUploadingRobotClass {
    public static void main(String[] args)  throws Exception{
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("C:\\Users\\ccst\\Desktop\\SeleniumMaterial\\fileUpload.html");

        Thread.sleep(2000);
        WebElement fileInput = driver.findElement(By.id("fileInput"));
        Actions actions = new Actions(driver);
        actions.moveToElement(fileInput).click().perform();

        Thread.sleep(2000);

        String FilePath = "C:\\Users\\ccst\\Desktop\\SeleniumMaterial\\hiddenElementJavascriptExecutor.html";
        StringSelection selection = new StringSelection(FilePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection,null);


        Robot robat = new Robot();


        robat.keyPress(KeyEvent.VK_CONTROL);
        robat.keyPress(KeyEvent.VK_V);
        robat.keyPress(KeyEvent.VK_V);
        robat.keyPress(KeyEvent.VK_CONTROL);

        Thread.sleep(1000);

        driver.quit();

    }
}
