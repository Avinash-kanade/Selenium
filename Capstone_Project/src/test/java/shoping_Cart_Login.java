import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;

public class shoping_Cart_Login {
    WebDriver driver;
    WebDriverWait wait;
    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com");
    }
    @Test    (priority = 1)
    public void testPositive() throws InterruptedException {
        WebElement objUsername = driver.findElement(By.id("user-name"));
        WebElement objPassword = driver.findElement( By.id("password"));
        WebElement objLoginButton = driver.findElement(By.id("login-button"));

        objUsername.sendKeys("standard_user");
        objPassword.sendKeys("secret_sauce");
        objLoginButton.click();

        wait.until(ExpectedConditions.urlContains("inventory.html"));

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("inventory.html"), "Login Failed. URL: " + currentUrl);

        Thread.sleep(1000);
    }
    @Test (priority = 2)
    public void testNegativeWithIncorrectCredentials() {
        WebElement objUsername = driver.findElement(By.id("user-name"));
        WebElement objPassword = driver.findElement(By.id("password"));
        WebElement objLoginButton = driver.findElement(By.id("login-button"));

        objUsername.sendKeys("greyhound");
        objPassword.sendKeys("xyz");
        objLoginButton.click();
        By errorMessage = By.xpath("//h3[@data-test='error']");
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        Assert.assertTrue(error.isDisplayed(), "Error message should be visible for invalid credentials");
        Assert.assertTrue(error.getText().contains("Username and password do not match any user in this service"));
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
        }
    }



