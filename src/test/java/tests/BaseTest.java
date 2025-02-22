package tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.LoginPage;
import pages.PIMPage;
import utils.TestData;

import java.time.Duration;

public class BaseTest {

    protected static WebDriver driver;
    protected static HomePage homePage;
    protected PIMPage pimPage;
    protected static WebDriverWait wait;
    protected static final String FEMALE_GENDER = "Female";
    protected static final String MALE_GENDER = "Male";

    @BeforeAll
    public static void setup() {
        driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        login();
    }

    private static void login() {
        LoginPage loginPage = new LoginPage(driver);
        wait.until(ExpectedConditions.visibilityOfAllElements(loginPage.getLoginElements()));
        homePage = loginPage.login(driver, TestData.USER_NAME, TestData.PASSWORD);
        wait.until(ExpectedConditions.visibilityOfAllElements(homePage.getPimMenuOption()));
    }

    @AfterAll
    public static void closeAll() {
        if (driver != null) {
            driver.quit();
        }
    }
}
