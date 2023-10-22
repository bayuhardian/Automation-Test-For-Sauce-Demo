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

public class AddToChartAndCheckout {
    WebDriver driver;
    WebElement product;

    @Given("User is on the Sauce Demo product page")
    public void userIsOnProductPage() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions opt = new FirefoxOptions();
        opt.setHeadless(false);

        driver = new FirefoxDriver(opt);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    @When("User is logged in")
    public void userIsLoggedIn() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }

    @When("User selects a product and adds it to the cart")
    public void userSelectsProductAndAddsToCart() {
        product = driver.findElement(By.xpath("(//button[text()='Add to cart'])[1]")); // Pilih produk pertama
        product.click();
    }

    @When("User proceeds to checkout")
    public void userProceedsToCheckout() {
        WebElement cart = driver.findElement(By.className("shopping_cart_badge"));
        cart.click();
        driver.findElement(By.id("checkout")).click();
    }

    @When("User enters shipping information")
    public void userEntersShippingInformation() {
        driver.findElement(By.id("first-name")).sendKeys("Tatang");
        driver.findElement(By.id("last-name")).sendKeys("Doang");
        driver.findElement(By.id("postal-code")).sendKeys("12345");
        driver.findElement(By.id("continue")).click();
    }

    @When("User completes the purchase")
    public void userCompletesThePurchase() {
        driver.findElement(By.id("finish")).click();
    }

    @Then("Purchase is successful")
    public void purchaseIsSuccessful() {
        assertTrue(driver.getCurrentUrl().contains("checkout-complete.html"));
        driver.quit();
    }

}
