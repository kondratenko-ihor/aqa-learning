package film.miromax.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class AirSlatePage {
    private final WebDriver driver;
    private final By I_CONSENT_BUTTON = By.cssSelector("[aria-label=\"Give your consent to do business electronically\"]");
    private final By SINGLE_LINE_FIRST = By.cssSelector("[placeholder=\"first_single_line\"]");
    private final By NUMBER_FIELD = By.cssSelector("[data-type=\"number\"] div:nth-child(2)");
    private final By VALID_SINGLE_LINE = By.xpath("//input[@placeholder=\"Enter your answer here\"]");
    private final By COMPLETE_BUTTON = By.cssSelector("[aria-label=\"Complete\"]");
    private final By LINK_IN_DOC = By.cssSelector("[class*=\"HtmlContainer\"]>p>a");
    private final By VALIDATION_MESSAGE = By.cssSelector("div [class*=\"ValidationMessage\"]");
    //login page
    private final By LOGIN_EMAIL_INPUT = By.cssSelector("[data-qa-tag=\"user-email-input\"]");
    private final By LOGIN_PASSWORD_INPUT = By.cssSelector("[data-qa-tag=\"user-password-input\"]");
    private final String USER_EMAIL = "kondratenko.ihor+selenium@airslate.com";
    private final String PASSWORD = "Qwerty123!";
    private final By LOGIN_BUTTON = By.cssSelector("[type=\"submit\"]");
    // domain-select
    private final By SIDEBAR = By.cssSelector("[data-qa-tag=\"navBarprimary\"]");
    private final By PROFILE_CIRCLE = By.cssSelector("[class=\"thumb thumb--circle\"]");
    private final By MY_ACCOUNT_BUTTON = By.cssSelector("[data-qa-tag=\"btnUserAvatar\"]");
    // profile-page
    private final By CHANGE_ICON_BUTTON = By.cssSelector("[class=\"photo-uploader__action-btn\"] button");
    private final By SELECT_IMAGE_BUTTON = By.cssSelector("[accept=\"image/jpeg,image/png,image/gif\"]");
    private final By TAKE_PICTURE_BUTTON = By.cssSelector("[class=\"drag-and-drop__action-item\"]:nth-child(2)");
    private final By NOTIFICATION = By.cssSelector("[class=\"notifications__item notifications__item--success is-show\"]");
    // thank you page
    private final By TRY_AIR_SLATE_BUTTON = By.cssSelector("[aria-label=\"Try airSlate\"]");
    private final By THANK_YOU_PAGE_TITLE = By.cssSelector("[class=\"headline-h3\"]");

    public AirSlatePage(WebDriver driver) {
        this.driver = driver;

    }

    public void consentButtonClick() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(I_CONSENT_BUTTON)));
        driver.findElement(I_CONSENT_BUTTON).click();
    }

    public void switchToiFrame() {
        driver.switchTo().frame("editor-iframe-web-form");
    }

    public void switchBackIFrame() {
        driver.switchTo().defaultContent();
    }

    public void clickOnLink() {
        driver.findElement(LINK_IN_DOC).click();
        Assert.assertEquals(driver.getWindowHandles().size(), 2, "Wrong window number");
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
        Assert.assertTrue(driver.findElement(VALIDATION_MESSAGE).isDisplayed(), "Validation message isn't shown");
    }

    public void fillInFirstSingleLine() {
        driver.findElement(SINGLE_LINE_FIRST).sendKeys("!@#$^&YGFDFSA$%6fg fgds fgjl;sdg");
    }

    public void fillInNumberField() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(NUMBER_FIELD)));
        driver.findElement(NUMBER_FIELD).click();
        driver.findElement(NUMBER_FIELD).sendKeys("1235678");
    }

    public void fillInValidField() {
        driver.findElement(VALID_SINGLE_LINE).sendKeys("123456789");
    }

    public void fillInNotValidData() {
        driver.findElement(VALID_SINGLE_LINE).sendKeys("123432SDFSERDSFA@");
    }

    public void completeButtonClick() {
        driver.findElement(COMPLETE_BUTTON).click();
    }

    public void checkCompleteButtonIsDisabled() {
        Assert.assertFalse(driver.findElement(COMPLETE_BUTTON).isEnabled());
    }

    public void waitForThankYouPage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(2100));
        wait.until(ExpectedConditions.urlContains("/thank-you"));
    }

    public void checkWeOnTryAirSlatePage() {
        Assert.assertTrue(driver.findElement(TRY_AIR_SLATE_BUTTON).isDisplayed(), "No Try AirSlate button");
        Assert.assertTrue(driver.findElement(THANK_YOU_PAGE_TITLE).isDisplayed(), "No Thank You Page title");
    }

    private final By SIGNATURE_ICON = By.cssSelector("[class*=\"EmptySignatureView\"]");
    private final By CAMERA_CAPTURE = By.cssSelector("[data-test=\"optionCapture\"]");
    private final By CAPTURE = By.cssSelector("[data-test=\"saveAndUse\"]");

    public void clickSignatureIcon() {
        driver.findElement(SIGNATURE_ICON).click();
    }

    public void createCameraSignature() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(CAMERA_CAPTURE)));
        driver.findElement(CAMERA_CAPTURE).click();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(CAPTURE)));
        driver.findElement(CAPTURE).click();
        driver.findElement(CAPTURE).click();
    }



    public void loginUser() {
        driver.findElement(LOGIN_EMAIL_INPUT).sendKeys(USER_EMAIL);
        driver.findElement(LOGIN_PASSWORD_INPUT).sendKeys(PASSWORD);
        driver.findElement(LOGIN_BUTTON).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(3000));
        wait.until(ExpectedConditions.urlContains("/domain-select"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/domain-select"));
    }

    public void navigateToProfilePage() {
        driver.get("https://my.airslate.com/account/personal-information");

    }

    public void changeAvatar()  {
        driver.findElement(CHANGE_ICON_BUTTON).click();
        driver.findElement(SELECT_IMAGE_BUTTON).sendKeys("/IdeaProjects/MiromaxCinema/src/test/test_image.jpeg");
        driver.findElement(By.cssSelector("[class=\"button button--sm button--primary\"]")).click();
        Assert.assertTrue(driver.findElement(NOTIFICATION).isDisplayed());
    }

}
