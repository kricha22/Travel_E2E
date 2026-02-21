package tests;

import org.testng.annotations.Test;

import java.io.IOException;

public class HomePageTest extends Base{
    @Test
    public void validatePortOfCalls() {
        loginPage.navigateToSecondaryLoginPage();
        loginPage.enterBookingCredentialsAndLogin();
        homePage.isPortOfCallsPresent();
        homePage.isSeeAllPresent();
        homePage.clickSeeAllCta();
        homePage.validateSeeAllBehavior();
        homePage.selectAnyPort();
        assertAll(homePage.validatePort());
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
