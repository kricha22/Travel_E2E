package page;

import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.WaitUtility;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class HomePage {
    WebDriver driver;
    WaitUtility wait = new WaitUtility();

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@data-testid='po-cuk-slider']//h1")
    WebElement portOfCalls;

    @FindBy(xpath = "//div[@data-testid='po-cuk-slider']/div/div/a")
    WebElement seeAllCta;

    @FindBy(xpath = "//div[@data-testid='po-cuk-slider']/div/div/a/following-sibling::div/button[2]")
    WebElement seeAllRightArrow;

    @FindBy(xpath = "//div[@data-testid='po-cuk-slider']/div/div/a/following-sibling::div/button[1]")
    WebElement seeAllLeftArrow;

    @FindBy(xpath = "//button[@data-testid='po-cuk-all-destination']//*[@role='img']")
    WebElement allDestinationTickMark;

    @FindBy(xpath = "//button[@data-testid='po-cuk-HeroTileBackButton']")
    WebElement backCta;

    @FindBy(xpath = "//a[@data-testid='destination-card']")
    List<WebElement> ports;

    @FindBy(xpath = "//*[contains(@data-testid,'po-cuk-sliderItem')]//*[@role='img']")
    WebElement selectedPortTick;

    @FindBy(xpath = "//*[contains(@data-testid,'po-cuk-sliderItem')]//*[@role='img']/parent::div/following-sibling::p[1]")
    WebElement selectedPortName;

    @FindBy(xpath = "//*[contains(@data-testid,'po-cuk-sliderItem')]//*[@role='img']/parent::div/following-sibling::p[2]")
    WebElement selectedPortDate;

    @FindBy(xpath = "//a[@data-testid='destination-card']//h4")
    List<WebElement> listPortDate;

    @FindBy(xpath = "//a[@data-testid='destination-card']//p")
    List<WebElement> listLocation;

    @FindBy(xpath = "//a[@data-testid='destination-card']//h3")
    List<WebElement> listCountry;

    @FindBy(xpath = "//div[@data-testid='po-cuk-add-ons-wrapper']//h5")
    WebElement addOnsLabel;

    @FindBy(xpath = "//button[@aria-label='Previous slide']")
    WebElement addOnsLeftArrow;

    @FindBy(xpath = "//button[@aria-label='Next slide']")
    WebElement addOnsRightArrow;

    @FindBy(xpath = "//a//*[contains(text(),'basketicon')]/ancestor::a")
    WebElement basketIcon;

    @FindBy(xpath = "//div[@data-testid='po-cuk-cuk-po-avatar-icon-wrapper-wrapper'] | //button[contains(@class,'sign_in')]")
    WebElement avatar;

    @FindBy(xpath = "//div[@data-testid='po-cuk-tile-wrapper']")
    List<WebElement> listAddOnsTile;

    @FindBy(xpath = "//a[@data-testid='po-cuk-details-button']")
    List<WebElement> listAddOnsTileCta;

    @FindBy(xpath = "//div[@data-testid='po-cuk-tile-wrapper']//h6")
    List<WebElement> listAddOnsTileTitle;

    @FindBy(xpath = "//div[@data-testid='po-cuk-tile-wrapper']//*[@role='img']")
    List<WebElement> listAddOnsTileIcon;

    @FindBy(xpath = "//div[@data-testid='po-cuk-c-tileWrapper-wrapper']/a")
    List<WebElement> listHolidayTile;

    @FindBy(xpath = "//div[@data-testid='po-cuk-c-tileWrapper-wrapper']//span")
    List<WebElement> listHolidayTileCta;

    @FindBy(xpath = "//div[@data-testid='po-cuk-c-tileWrapper-wrapper']//h6")
    List<WebElement> listHolidayTileTitle;

    @FindBy(xpath = "//div[@data-testid='po-cuk-c-tileWrapper-wrapper']//img[contains(@src,'im=Feature')]")
    List<WebElement> listHolidayTileImage;

    @FindBy(xpath = "//section[@data-testid='sc005-PersonaliseTile']//h2")
    WebElement enhanceHolidayLabel;

    public void isPortOfCallsPresent() {
        wait.waitTillElementPresent(portOfCalls, driver, Duration.ofSeconds(10));
        portOfCalls.isDisplayed();
    }

    public void isSeeAllPresent() {
        seeAllCta.isDisplayed();
        seeAllLeftArrow.isDisplayed();
        seeAllRightArrow.isDisplayed();
    }

    public void clickSeeAllCta() {
        seeAllCta.click();
    }

    public void validateSeeAllBehavior() {
        wait.waitTillElementPresent(allDestinationTickMark, driver, Duration.ofSeconds(20));
        backCta.click();
        wait.waitTillElementPresent(portOfCalls, driver, Duration.ofSeconds(20));
    }

    String portDate;
    String portName;
    public void selectAnyPort() {
      //  int random = ThreadLocalRandom.current().nextInt(1, ports.size()+1);
        String date = listPortDate.get(1).getText().replace("th", "");
        portDate = date.replace(String.valueOf(date.charAt(6)), "");
        portName = listLocation.get(1).getText()+", "+listCountry.get(1).getText();
        ports.get(1).click();
        wait.waitTillElementPresent(selectedPortTick, driver, Duration.ofSeconds(20));
        //Haugesund, Norway 15 Jun 2026
        //Haugesund Norway 15th June 2026
    }

    public Map<String, String> validatePort() {
        Map<String, String> map = new HashMap<>();
        map.put(selectedPortDate.getText(), portDate);
        map.put(selectedPortName.getText(), portName);
        return map;
    }

    public void isAddOnsPresent() {
        addOnsLabel.isDisplayed();
        addOnsRightArrow.isDisplayed();
        addOnsLeftArrow.isDisplayed();
        if(listAddOnsTile.size()!=listAddOnsTileCta.size()) {
            System.out.println("cta is missing for one of the add ons tile");
        } else if(listAddOnsTile.size()!=listAddOnsTileIcon.size()) {
            System.out.println("icon is missing for one of the add ons tile");
        } else if(listAddOnsTile.size()!=listAddOnsTileTitle.size()) {
            System.out.println("title is missing for one of the add ons tile");
        } else {
            System.out.println("Add ons component is available..!");
        }
    }

    public void validateAddOnsTileNavigation() throws IOException {
        List<String> addOnUrls = listAddOnsTileCta.stream()
                .map(element -> element.getDomAttribute("href")).toList();
        for(String url : addOnUrls) {
            driver.get(url);
            wait.waitForPageLoad(driver, Duration.ofSeconds(20));
            String currentUrl = driver.getCurrentUrl();
            if(currentUrl.contains("my-pocruises")) {
                if(basketIcon.isDisplayed()) {
                    System.out.println("page has content");
                }
            } else {
                HttpURLConnection connection = (HttpURLConnection) new URL(currentUrl).openConnection();
                connection.setRequestMethod("GET");
                int statusCode = connection.getResponseCode();
                if (statusCode == 200 && !driver.getPageSource().trim().isEmpty()) {
                    System.out.println("page has content");
                } else if (statusCode == 404) {
                    System.out.println("page not found!(404)");
                } else {
                    System.out.println("page validation failed.. status code" + statusCode);
                }
            }
            driver.navigate().back();
            wait.waitForPageLoad(driver, Duration.ofSeconds(20));
        }
    }

    public void isEnhanceYourHolidayPresent() {
        enhanceHolidayLabel.isDisplayed();
        if(listHolidayTile.size()!=listHolidayTileCta.size()) {
            System.out.println("cta is missing for one of the holiday tile");
        } else if(listHolidayTile.size()!=listHolidayTileImage.size()) {
            System.out.println("image is missing for one of the holiday tile");
        } else if(listHolidayTile.size()!=listHolidayTileTitle.size()) {
            System.out.println("title is missing for one of the holiday tile");
        } else {
            System.out.println("holiday component is available..!");
        }
    }

    public void validateHolidayTileNavigation() throws IOException {
        List<String> holidayUrls = listHolidayTile.stream()
                .map(element -> element.getDomAttribute("href")).toList();
        for(String url : holidayUrls) {
            driver.get("https://www.pocruises.com/" +url);
            wait.waitForPageLoad(driver, Duration.ofSeconds(20));
            String currentUrl = driver.getCurrentUrl();
            if(currentUrl.equals("https://www.pocruises.com/" +url) && avatar.isDisplayed()) {
                    System.out.println("user successfully navigated to page "+ url);
            }
            driver.navigate().back();
            wait.waitForPageLoad(driver, Duration.ofSeconds(20));
        }
    }
}
