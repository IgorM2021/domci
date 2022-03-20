import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import static org.junit.Assert.assertEquals;

public class MyStepdefs {
    WebDriver driver;





    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:/Selenium/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Given("Navigate to page {string}")
    public void navigateToPage(String page) {
        driver.get(page);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }


    @Given("I have written my email {string}")
    public void iHaveWrittenMyEmail(String email) throws InterruptedException {



        sendKeys(driver, By.id("email"), email);
        Thread.sleep(1000);

    }

    @Then("missing email")
    public void missingEmail() throws InterruptedException {
        assertEquals(true, driver.findElement(By.id("email")).getAttribute("class").contains("invalid"));

        Thread.sleep(1000);

    }


    @Given("I have written my username {string}")
    public void iHaveWrittenMyUsername(String username) throws InterruptedException {
        sendKeys(driver, By.id("new_username"), username);
       Thread.sleep(1000);


    }
    @Then("long username")
    public void longUsername() throws InterruptedException  {
        assertEquals("Enter a value less than 100 characters long", driver.findElement(By.cssSelector("#new_username ~ span")).getText());
        Thread.sleep(1000);

    }




    @Given("I have written my password {string}")
    public void iHaveWrittenMyPassword(String password) throws InterruptedException {
        sendKeys(driver, By.xpath("//*[@id=\"new_password\"]"), password);
        Thread.sleep(1000);

    }


    @When("I click the Sign Up button")
    public void iClickTheSignUpButton() throws InterruptedException {
        click(driver, By.id("create-account"));
        Thread.sleep(1000);

    }

    @Then("my email is open")
    public void myEmailIsOpen() throws InterruptedException {
        Thread.sleep(1000);
        assertEquals("Another user with this username already exists. Maybe it's your evil twin. Spooky.", driver.findElement(By.cssSelector("#new_username ~ span")).getText());

        driver.quit();
    }




    private String getText(WebDriver driver, String idName) {
        WebDriverWait foobar = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = foobar.until(ExpectedConditions.presenceOfElementLocated(By.id(idName)));
        String text = element.getText();
        return text;

    }


    private static void scroll(WebDriver driver, String length) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)", "");
    }


    private static void sendKeys(WebDriver driver, By by, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));


        element.sendKeys(text);


    }


    private static void click(WebDriver driver, By by) {
        WebDriverWait foobar = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = foobar.until(ExpectedConditions.elementToBeClickable(by));

        element.click();


    }



}

