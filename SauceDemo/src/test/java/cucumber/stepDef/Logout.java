package cucumber.stepDef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class Logout {
    WebDriver driver;

    @Given("User is logged in to Sauce Demo")
    public  void userLogIn() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions opt = new FirefoxOptions();
        opt.setHeadless(false);

        driver = new FirefoxDriver(opt);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }

    @When("User logs out from the application")
    public void userLogsOut() {
        WebElement menuButton = driver.findElement(By.className("bm-burger-button"));
        menuButton.click();
        WebElement logoutLink = driver.findElement(By.xpath("//*[@id=\"logout_sidebar_link\"]"));
        logoutLink.click();
    }

    @Then("User is redirected to the login page")
    public void userIsRedirectedToLoginPage() {
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("https://www.saucedemo.com/"));
    }
}
