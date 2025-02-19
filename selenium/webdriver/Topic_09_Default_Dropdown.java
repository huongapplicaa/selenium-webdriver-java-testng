package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_09_Default_Dropdown {

    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
    }

    @Test
    public void TC_01_Register() {
        //fill out form
//        driver.findElement(By.xpath("//a[@class='ico-register']")).click();
        driver.findElement(By.xpath("//input[@name='FirstName']")).sendKeys("Huong");
        driver.findElement(By.xpath("//input[@name='LastName']")).sendKeys("Chu");
        new Select(driver.findElement(By.name("DateOfBirthDay"))).selectByVisibleText("15");
        new Select(driver.findElement(By.name("DateOfBirthMonth"))).selectByVisibleText("May");
        new Select(driver.findElement(By.name("DateOfBirthYear"))).selectByVisibleText("2000");
        driver.findElement(By.name("Email")).sendKeys("thanhhuong12333@gmail.com");
        driver.findElement(By.name("Password")).sendKeys(("123qwe"));
        driver.findElement(By.name("ConfirmPassword")).sendKeys("123qwe");
        //submit
        driver.findElement(By.id("register-button")).click();
        sleepInSeconds(2);

        //expect
        Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
