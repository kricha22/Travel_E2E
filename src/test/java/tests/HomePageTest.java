package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import java.io.IOException;

public class HomePageTest extends Base{
    private static final Logger logger = LogManager.getLogger(HomePageTest.class);
    @Test
    public void validatePortOfCalls() {
        loginPage.navigateToSecondaryLoginPage();
        logger.info("application launched .....");
        loginPage.enterBookingCredentialsAndLogin();
        logger.info("logged into application .....");
        homePage.isPortOfCallsPresent();
        logger.info("port of calls displayed .....");
        homePage.isSeeAllPresent();
        homePage.clickSeeAllCta();
        homePage.validateSeeAllBehavior();
        logger.info("see all validated .....");
        homePage.selectAnyPort();
        assertAll(homePage.validatePort());
        logger.info("ui validated .....");
    }

  //  @Test
    public void validateAddOns() throws IOException {
        loginPage.navigateToSecondaryLoginPage();
        loginPage.enterBookingCredentialsAndLogin();
        homePage.isAddOnsPresent();
        homePage.validateAddOnsTileNavigation();
    }

   // @Test
    public void validateEnhanceYourHoliday() throws IOException {
        loginPage.navigateToSecondaryLoginPage();
        loginPage.enterBookingCredentialsAndLogin();
        homePage.isEnhanceYourHolidayPresent();
        homePage.validateHolidayTileNavigation();
    }
}
