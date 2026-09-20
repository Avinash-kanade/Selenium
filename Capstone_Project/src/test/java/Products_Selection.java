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

public class Products_Selection {
    WebDriver driver;
    WebDriverWait wait;



    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com");
    }
    @Test(priority = 1)
    public void SelectProduct() throws InterruptedException {
        WebElement objUsername = driver.findElement(By.id("user-name"));
        WebElement objPassword = driver.findElement(By.id("password"));
        WebElement objLoginButton = driver.findElement(By.id("login-button"));

        objUsername.sendKeys("standard_user");
        objPassword.sendKeys("secret_sauce");
        objLoginButton.click();

        wait.until(ExpectedConditions.urlContains("inventory.html"));

        WebElement item1 = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        WebElement item2 = driver.findElement(By.id("add-to-cart-sauce-labs-bike-light"));
        WebElement item3 = driver.findElement(By.id("add-to-cart-test.allthethings()-t-shirt-(red)"));
        item1.click();
        item2.click();
        item3.click();

        WebElement Cart = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
        Thread.sleep(1000);
        Cart.click();
       // wait.until(ExpectedConditions.urlContains("cart.html"));
        Thread.sleep(1000);

        WebElement cartBadge = driver.findElement(By.className("shopping_cart_badge"));
        Assert.assertEquals(cartBadge.getText(), "3");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

