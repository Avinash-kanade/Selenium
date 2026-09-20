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

public class Filterprice {
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
    public void CheckFilter() throws InterruptedException {
        WebElement objUsername = driver.findElement(By.id("user-name"));
        WebElement objPassword = driver.findElement(By.id("password"));
        WebElement objLoginButton = driver.findElement(By.id("login-button"));

        objUsername.sendKeys("standard_user");
        objPassword.sendKeys("secret_sauce");
        objLoginButton.click();
        Thread.sleep(2000);
        wait.until(ExpectedConditions.urlContains("inventory.html"));



        WebElement Dropdown = driver.findElement(By.xpath("//select[@class='product_sort_container']"));
        Dropdown.click();

        WebElement Filter = driver.findElement(By.xpath("//option[@value='lohi']"));

        Filter.click();
        Thread.sleep(2000);
        WebElement item1 = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        WebElement item2 = driver.findElement(By.id("add-to-cart-sauce-labs-bike-light"));
        WebElement item3 = driver.findElement(By.id("add-to-cart-test.allthethings()-t-shirt-(red)"));
        item1.click();
        item2.click();
        item3.click();
        String activeOption = driver.findElement(By.className("active_option")).getText();
        Assert.assertEquals(activeOption, "Price (low to high)");

    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
