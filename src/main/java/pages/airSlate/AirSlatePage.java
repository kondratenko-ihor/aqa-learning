package pages.airSlate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.time.Duration;

public class AirSlatePage {
    private final WebDriver driver;

    public AirSlatePage(WebDriver driver) {
        this.driver = driver;

    }

    public void consentButtonClick() {
        waitClickableAndClick(Locators.DocumentManager.I_CONSENT_BUTTON);
    }

    public void switchToiFrame() {
        waitForFrameAndSwitch(By.cssSelector(Locators.DocumentManager.IFRAME));
    }

    public void switchBackIFrame() {
        driver.switchTo().defaultContent();
    }

    public void clickOnLink() {
        waitClickableAndClick(Locators.DocumentManager.LINK_IN_DOC);
        Assert.assertEquals(driver.getWindowHandles().size(), 2, AssertErrorMessage.ERROR_COUNT_MESSAGE);
    }

    public void switchToNewTab() {
        String originalWindow = driver.getWindowHandle();
        System.out.println(driver.getWindowHandles().size());
        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    public void closeSecondTab() {
        String originalWindow = driver.getWindowHandle();
        driver.close();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
            }
        }
    }

    public void clickSomewhere() {
        driver.findElement(By.xpath("//html")).click();
    }

    public void assertValidationMessageIsDisplayed() {
        waitElementsPresents(Locators.DocumentManager.VALIDATION_MESSAGE);
        Assert.assertTrue(driver.findElement(Locators.DocumentManager.VALIDATION_MESSAGE).isDisplayed(),
                AssertErrorMessage.VALIDATION_NOT_SHOWN);
    }

    public void fillInFirstSingleLine() {
        waitElementsPresents(Locators.DocumentManager.SINGLE_LINE_FIRST);
        driver.findElement(Locators.DocumentManager.SINGLE_LINE_FIRST)
                .sendKeys(Locators.DocumentManager.INPUT_VALID_DATA);
    }

    public void fillValidData() {
        waitFieldPresentAndFillIt(Locators.DocumentManager.VALID_SINGLE_LINE, Locators.DocumentManager.INPUT_VALID_DATA);
    }

    public void fillInNotValidData() {
        waitFieldPresentAndFillIt(Locators.DocumentManager.VALID_SINGLE_LINE, Locators.DocumentManager.INPUT_INVALID_DATA);
    }

    public void completeButtonClick() {
        waitClickableAndClick(Locators.DocumentManager.COMPLETE_BUTTON);
    }

    public void checkCompleteButtonIsDisabled() {
        Assert.assertFalse(driver.findElement(Locators.DocumentManager.COMPLETE_BUTTON)
                .isEnabled(), AssertErrorMessage.COMPLETE_IS_ENABLED);
    }

    public void waitForThankYouPage() {
        waitUrlContains(Locators.ThankYouPage.THANK_YOU_PAGE_PATH);
        waitElementsPresents(Locators.ThankYouPage.THANK_YOU_PAGE_TITLE);
        waitElementsPresents(Locators.ThankYouPage.TRY_AIR_SLATE_BUTTON);
    }

    public void checkWeOnTryAirSlatePage() {
        Assert.assertTrue(driver.findElement(Locators.ThankYouPage.TRY_AIR_SLATE_BUTTON).isDisplayed(),
                AssertErrorMessage.NO_THANK_YOU_PAGE_BUTTON);
        Assert.assertTrue(driver.findElement(Locators.ThankYouPage.THANK_YOU_PAGE_TITLE).isDisplayed(),
                AssertErrorMessage.NO_THANK_YOU_PAGE_TITLE);
    }


    public void clickSignatureIcon() {
        waitClickableAndClick(Locators.SigningConfigSection.SIGNATURE_ICON);
    }

    public void createCameraSignature() {
        waitClickableAndClick(Locators.SigningConfigSection.CAMERA_CAPTURE);
        waitClickableAndClick(Locators.SigningConfigSection.SAVE);
        waitClickableAndClick(Locators.SigningConfigSection.SAVE);
    }

    public void loginUser() {
        waitFieldPresentAndFillIt(Locators.Login.LOGIN_EMAIL_INPUT, Locators.Login.USER_EMAIL);
        waitFieldPresentAndFillIt(Locators.Login.LOGIN_PASSWORD_INPUT, Locators.Login.PASSWORD);
        waitClickableAndClick(Locators.Login.LOGIN_BUTTON);
        waitUrlContains(Locators.DomainSelect.DOMAIN_PATH);
        Assert.assertTrue(driver.getCurrentUrl()
                .contains(Locators.DomainSelect.DOMAIN_PATH), AssertErrorMessage.DOMAIN_SELECT_PATH_MISSING);
    }

    public void openProfilePage() {
        String URL = Locators.ProfilePage.PERSONAL_INFORMATION_URL;
        driver.get(URL);
        waitUrlContains(URL);
        Assert.assertTrue(driver.getCurrentUrl().contains(URL));

    }

    public void changeAvatar() {
        waitClickableAndClick((Locators.ProfilePage.CHANGE_ICON_BUTTON));
        File file = new File(Locators.ProfilePage.IMAGE_PATH);
        driver.findElement(Locators.ProfilePage.SELECT_IMAGE_BUTTON)
                .sendKeys(file.getAbsolutePath());
        waitClickableAndClick((Locators.ProfilePage.SAVE_IMAGE));
        waitElementsPresents(Locators.ProfilePage.NOTIFICATION);
        Assert.assertTrue(driver.findElement(Locators.ProfilePage.NOTIFICATION).isDisplayed(),
                AssertErrorMessage.NOTIFICATION_MISSING);
    }

    public void waitUrlContains(String url) {
        new WebDriverWait(driver, Duration.ofMillis(3700))
                .until(ExpectedConditions.urlContains(url));
    }

    public void waitClickableAndClick(By element) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void waitForFrameAndSwitch(By element) {
        new WebDriverWait(driver, Duration.ofSeconds(6))
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
    }

    public void waitElementsPresents(By element) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element));
    }

    public void waitFieldPresentAndFillIt(By element, String fulfillData) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(element)).sendKeys(fulfillData);
    }
}
