
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
import java.time.OffsetDateTime;

import static org.junit.Assert.assertEquals;

public class MyStepdefs {
    WebDriver driver;


    @Given("I have opened {string} browser")
    public void iHaveOpenedBrowser(String browser) {

        if (browser.equals("edge")) {
            System.setProperty("webdriver.edge.driver", "C:/selenium/msedgedriver.exe");
            driver = new EdgeDriver();
        } else {
            System.setProperty("webdriver.chrome.driver", "C:/selenium/chromedriver.exe");
            driver = new ChromeDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Given("Navigate to page {string}")
    public void navigateToPage(String page) {
        driver.get(page);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }


    @Given("I have written my email {string}")
    public void iHaveWrittenMyEmail(String email) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        if(email.equals("generated"))
            email = "Igor123" + OffsetDateTime.now().toEpochSecond() + "@mail.com";
        sendKeys(driver, By.id("email"), email);
        Thread.sleep(1000);

    }

    @Given("I have written my username {string}")
    public void iHaveWrittenMyUsername(String username) throws InterruptedException {
        if(username.equals("generated"))
            username = "Igor123" + OffsetDateTime.now().toEpochSecond();
        sendKeys(driver, By.id("new_username"), username);
        Thread.sleep(1000);

    }

    @Given("I have written my password {string}")
    public void iHaveWrittenMyPassword(String password) throws InterruptedException {
        sendKeys(driver, By.xpath("//*[@id=\"new_password\"]"), password);
        Thread.sleep(1000);

    }

    @Then("my email is open")
    public void myEmailIsOpen() throws InterruptedException {
        assertEquals("Check your email", driver.findElement(By.xpath("//*[@id=\"signup-content\"]/div/div/div/h1")).getText());
        Thread.sleep(1000);
    }

    @Then("missing email")
    public void missingEmail() throws InterruptedException {
        assertEquals(true, driver.findElement(By.id("email")).getAttribute("class").contains("invalid"));
        Thread.sleep(1000);

    }

    @Then("long username")
    public void longUsername() throws InterruptedException {
        assertEquals("Enter a value less than 100 characters long", driver.findElement(By.cssSelector("#new_username ~ span")).getText());
        Thread.sleep(1000);

    }

    @Then("username is upptagen")
    public void usernameIsUpptagen() throws InterruptedException {
        assertEquals("Another user with this username already exists. Maybe it's your evil twin. Spooky.", driver.findElement(By.xpath("//*[@id=\"signup-form\"]/fieldset/div[2]/div/span")).getText());


    }


    @When("I click the Sign Up button")
    public void iClickTheSignUpButton() throws InterruptedException {
        click(driver, By.id("create-account"));
        Thread.sleep(1000);
    }

    @Then("Registration result is {string}")
    public void registrationResultIs(String result) {
        switch (result) {
            case "ok":
                assertEquals("Check your email", driver.findElement(By.xpath("//*[@id=\"signup-content\"]/div/div/div/h1")).getText());
                break;
            case "usernametoolong":
                assertEquals("Enter a value less than 100 characters long", driver.findElement(By.cssSelector("#new_username ~ span")).getText());
                break;
            case "usernameexists":
                assertEquals("Another user with this username already exists. Maybe it's your evil twin. Spooky.", driver.findElement(By.xpath("//*[@id=\"signup-form\"]/fieldset/div[2]/div/span")).getText());
                break;
            case "emailrequired":
                assertEquals(true, driver.findElement(By.id("email")).getAttribute("class").contains("invalid"));
                break;
            default:
                assertEquals(true, false);
                break;
        }
        driver.quit();

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




