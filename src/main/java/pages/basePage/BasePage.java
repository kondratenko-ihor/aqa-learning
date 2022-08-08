package pages.basePage;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.airSlate.AssertErrorMessage;

import java.time.Duration;

public class BasePage {
    protected static WebDriver driver;
    protected static String SWITCH_NEW_TAB_ERROR = "We're not in new tab";
    Duration duration = Duration.ofSeconds(15);

    public BasePage(WebDriver driver)
    {
        BasePage.driver = driver;
    }

    public void waitForElementAndClick(By element){
        new WebDriverWait(driver, duration)
                .until(ExpectedConditions.visibilityOfElementLocated(element)).click();
    }

    public void waitUrlContains(String url) {
        new WebDriverWait(driver, duration)
                .until(ExpectedConditions.urlContains(url));
    }

    public void waitClickableAndClick(By element) {
        new WebDriverWait(driver, duration)
                .until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void waitClickableAndClick(WebElement element) {
        new WebDriverWait(driver, duration)
                .until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void waitForFrameAndSwitch(By element) {
        new WebDriverWait(driver, duration)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));

    }

    public void waitAllElementsPresents(By element) {
        new WebDriverWait(driver, duration)
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element));
    }

    public void waitForElementVisible(By element){
        new WebDriverWait(driver, duration)
                .until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public void waitFieldPresentAndFillIt(By element, String fulfillData) {
        new WebDriverWait(driver, duration)
                .until(ExpectedConditions.presenceOfElementLocated(element)).sendKeys(fulfillData);
    }
    public void switchToNewTab(String newTabUrl) {
        String originalWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        Assert.assertTrue(driver.getCurrentUrl().contains(newTabUrl), SWITCH_NEW_TAB_ERROR);
    }

    public void closeSecondTab() {
        String originalWindow = driver.getWindowHandle();
        driver.close();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
            }
        }
        Assert.assertEquals(driver.getWindowHandles().size(), 1, AssertErrorMessage.ERROR_COUNT_MESSAGE);
    }
    public void waitInvisibility(WebElement element) {
        new WebDriverWait(driver, duration)
                .until(ExpectedConditions.stalenessOf(element));
    }

    public void waitForElementToBeClickable(By element){
        new WebDriverWait(driver, duration)
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    public void checkElementIsPresents(WebElement element, String assertError){
        new WebDriverWait(driver, duration)
                .until(ExpectedConditions.visibilityOf(element));
        Assert.assertTrue(element.isDisplayed(), assertError);
    }

    public String getString(By element) {
        return driver.findElement(element).getText();
    }

    public String getAttribute(By element, String attributeName) {
        return driver.findElement(element).getAttribute(attributeName);
    }

    public void waitLoaderEndsIfAppears(By element) {
        try {
            waitInvisibility(driver.findElement(element));
        } catch (NoSuchElementException ignored) {
        }
    }
}
