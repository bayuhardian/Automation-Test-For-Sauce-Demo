package cucumber.stepDef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class Login {

    WebDriver driver;

    @Given("User is on the Sauce Demo login page")
    public void userIsOnLoginPage() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions opt = new FirefoxOptions();
        opt.setHeadless(false);

        driver = new FirefoxDriver(opt);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    @When("User enters valid username and password")
    public void userEntersValidCredentials() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }

    @When("User enters invalid username and password")
    public void userEntersInvalidCredentials() {
        driver.findElement(By.id("user-name")).sendKeys("invalid_user");
        driver.findElement(By.id("password")).sendKeys("invalid_password");
        driver.findElement(By.id("login-button")).click();
    }

    @Then("User is logged in successfully")
    public void userIsLoggedInSuccessfully() {
        assertTrue(driver.findElement(By.className("title")).isDisplayed());
        driver.quit();
    }

    @Then("User sees error message indicating login failure")
    public void userSeesErrorMessage() {
        assertTrue(driver.findElement(By.cssSelector("[data-test=error]")).isDisplayed());
        driver.quit();
    }
}
