package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_03_Relative_Locator {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        }

//        driver = new FirefoxDriver();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    // TestNG: Order testcase theo Alphabet (0-9 A-Z)
    // Firstname textbox - HTML Code
    // HTML Element: <tagname attribute_name_1='attribute_value' attribute_name_2='attribute_value' ...>
    /*
     * <input type="text" data-val="true" data-val-required="First name is required." id="FirstName" name="FirstName">
     */

    @Test
    // rarely use because of changing structure => affects codes
    public void TC_01_Relative() {
        driver.get("https://demo.nopcommerce.com/login?returnUrl=%2Fregister");

        // Login button
        By loginButtonBy = By.cssSelector("button.login-button");
        WebElement loginButtonElement = driver.findElement(By.cssSelector("button.login-button"));

        // Remember Me checkbox
        By rememberMeCheckboxBy = By.id("RememberMe");

        // Forgot Password link
        WebElement forgotPasswordElement = driver.findElement(By.cssSelector("span.forgot-password"));

        // Password textbox
        By passwordTextboxBy = By.cssSelector("input#Password");

        WebElement rememberMeTextElement = driver
                .findElement(RelativeLocator.with(By.tagName("label"))
                        .above(loginButtonBy)
                        .toRightOf(rememberMeCheckboxBy)
                        .toLeftOf(forgotPasswordElement)
                        .below(passwordTextboxBy)
                        .near(forgotPasswordElement));


        System.out.println(rememberMeTextElement.getText());

        List<WebElement> allLinks = driver.findElements(RelativeLocator.with(By.tagName("a")));
        System.out.println(allLinks.size());


    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
