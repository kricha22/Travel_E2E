package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;
import page.HomePage;
import page.LoginPage;

import java.time.Duration;
import java.util.Map;

public class Base {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeTest
    public void launchApplication() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get("https://www.pocruises.com");
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
    }

    @AfterTest
    public void tearDown() {
        driver.close();
    }

    public void assertAll(Map<String, String> map) {
        map.forEach((k,v)-> softAssert.assertEquals(k, v));
        softAssert.assertAll();
    }
}
