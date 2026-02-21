package utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtility {
    public void waitTillElementPresent(WebElement element, WebDriver driver, Duration duration) {
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForPageLoad(WebDriver driver, Duration duration) {
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until((ExpectedCondition<Boolean>) d ->
                ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));
    }
}
