package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.WaitUtility;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class LoginPage {
    WebDriver driver;
    WaitUtility wait = new WaitUtility();

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@data-testid='signUpPopup-close-btn']")
    WebElement popUpCloseCta;

    @FindBy(xpath = "//*[@data-testid='avatar-login-trigger']")
    WebElement signInCta;

    @FindBy(xpath = "(//*[@id='wrapperDropdown']//button)[2]")
    WebElement signInpopUpCta;

    @FindBy(xpath = "//*[@data-linktext='Manage my booking']")
    WebElement manageMyBookingCta;

    @FindBy(id = "bookingReference")
    WebElement bookingReference;

    @FindBy(id = "firstName")
    WebElement firstName;

    @FindBy(id = "lastName")
    WebElement lastName;

    @FindBy(id = "dob-day")
    WebElement day;

    @FindBy(id = "dob-month")
    WebElement month;

    @FindBy(id = "dob-year")
    WebElement year;

    @FindBy(id = "onetrust-accept-btn-handler")
    WebElement acceptAllCta;

    @FindBy(xpath = "//*[@data-linktext='Manage my booking']")
    WebElement manageBookingCta;

    @FindBy(xpath = "//div[@data-testid='po-cuk-text_div']/h1")
    WebElement noOfDays;

    @FindBy(xpath = "//*[@data-testid='bookingReference-label']/span")
    WebElement bookingRefLabel;

    @FindBy(xpath = "//*[@data-testid='firstName-label']/span")
    WebElement fnamelabel;

    @FindBy(xpath = "//*[@data-testid='lastName-label']/span")
    WebElement lnamelabel;

    @FindBy(xpath = "//*[@data-testid='dob-label']/span")
    WebElement doblabel;

    public void navigateToSecondaryLoginPage() {
/*        if(popUpCloseCta.isDisplayed()) {
            popUpCloseCta.click();
        }*/
        acceptAllCta.click();
        signInCta.click();
        wait.waitTillElementPresent(signInpopUpCta, driver, Duration.ofSeconds(10));
        signInpopUpCta.click();
        manageMyBookingCta.click();
    }

    public void enterBookingCredentialsAndLogin() {
        bookingReference.sendKeys("2P8D6J");
        firstName.sendKeys("TEST");
        lastName.sendKeys("TEST");
        day.sendKeys("01");
        month.sendKeys("01");
        year.sendKeys("1999");
        manageBookingCta.click();
        wait.waitForPageLoad(driver, Duration.ofSeconds(30));
       // wait.waitTillElementPresent(noOfDays, driver, Duration.ofSeconds(20));
    }

    public String currentUrl() {
        return driver.getCurrentUrl();
    }

    public Map<String, String> secondaryPageUI() {
        Map<String, String> map = new HashMap<>();
        map.put(bookingRefLabel.getText(), "Booking reference*");
        map.put(fnamelabel.getText(), "First name*");
        map.put(lnamelabel.getText(), "Last name*");
        map.put(doblabel.getText(), "Date of birth*");
        map.put(manageBookingCta.getText(), "Manage my booking");
        return map;
    }
}
