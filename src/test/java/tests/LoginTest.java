package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends Base{
//    @Test
    public void validateSecondaryLogin() {
        loginPage.navigateToSecondaryLoginPage();
        assertAll(loginPage.secondaryPageUI());
        loginPage.enterBookingCredentialsAndLogin();
        Assert.assertEquals(loginPage.currentUrl(), "https://www.pocruises.com/my-pocruises");
    }
}
