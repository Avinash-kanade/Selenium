package RevisionSelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import static org.assertj.core.api.Assertions.assertThat;

public class FileUploadingRobotClassAssertionTestNG {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testFileUploadUsingRobotClass() throws Exception {
        String localPagePath = "C:\\Users\\ccst\\Desktop\\SeleniumMaterial\\fileUpload.html";
        driver.get(localPagePath);

        Thread.sleep(2000);
        WebElement fileInput = driver.findElement(By.id("fileInput"));


        assertThat(fileInput.isDisplayed())
                .as("File input element should be visible on the web page")
                .isTrue();

        Actions actions = new Actions(driver);
        actions.moveToElement(fileInput).click().perform();

        Thread.sleep(2000);

        String filePath = "C:\\Users\\ccst\\Desktop\\SeleniumMaterial\\hiddenElementJavascriptExecutor.html";
        StringSelection selection = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

        Robot robot = new Robot();


        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        Thread.sleep(1000);


        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        Thread.sleep(2000);

        String uploadedValue = fileInput.getAttribute("value");


        assertThat(uploadedValue)
                .as("Uploaded file path value should contain the selected file name")
                .contains("hiddenElementJavascriptExecutor.html");

        System.out.println("Assert Successfully");
    }

    @AfterMethod
    public void tearDown() {
            driver.quit();
    }

}