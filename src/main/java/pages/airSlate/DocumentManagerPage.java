package pages.airSlate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import pages.basePage.BasePage;

public class DocumentManagerPage extends BasePage {

    private static final By IFRAME_ID = By.id("editor-iframe-web-form");
    private static final By I_CONSENT_BUTTON = By.cssSelector("[aria-label='Give your consent to do business electronically']");
    private static final By SINGLE_LINE_FIRST = By.cssSelector("[placeholder='first_single_line']");
    private static final By NUMBER_FIELD = By.cssSelector("[data-type='number'] div:nth-child(2)");
    private static final By SINGLE_LINE_WITH_VALIDATION = By.xpath("//input[@placeholder='Enter your answer here']");
    private static final By COMPLETE_BUTTON = By.cssSelector("[aria-label='Complete']");
    private static final By LINK_IN_DOC = By.cssSelector("[class*='HtmlContainer'] p a");
    private static final By VALIDATION_MESSAGE = By.cssSelector("div [class*='ValidationMessage']");
    private static final By TRY_AIR_SLATE_BUTTON = By.cssSelector("[aria-label='Try airSlate']");
    private static final By THANK_YOU_PAGE_TITLE = By.cssSelector("[class='headline-h3']");
    private static final By SIGNATURE_ICON = By.cssSelector("[data-qa-tag='signature-wrap']");
    private static final By CAMERA_CAPTURE = By.cssSelector("[data-test='optionCapture']");
    private static final By HTML = By.xpath("//html");
    private static final By SAVE = By.cssSelector("[data-test='saveAndUse']");
    private static final By CAMERA_FRAME = By.cssSelector("[class='draw-editor__box__1UwF']");

    public DocumentManagerPage(WebDriver driver) {
       super(driver);
    }

    public void openSingleLinesPublicLink(){
        driver.get(TestData.PB_LINK_1);
    }

    public void openSignaturePublicLink(){
        driver.get(TestData.PB_LINK_SIGN);
    }

    public void consentButtonClick() {
        waitForElementAndClick(I_CONSENT_BUTTON);
    }

    public void switchToiFrame() {
        waitForFrameAndSwitch(IFRAME_ID);
    }

    public void switchBackToDefaultIFrame() {
        driver.switchTo().defaultContent();
    }

    public void clickOnLink() {
        waitForElementAndClick(LINK_IN_DOC);
        Assert.assertEquals(driver.getWindowHandles().size(), 2, AssertErrorMessage.ERROR_COUNT_MESSAGE);
    }

    public void opensLinkInNewTabCheck(){
        clickOnLink();
        switchToNewTab(TestData.NEW_TAB_URL);
        closeSecondTab();
    }
    public void clickSomewhere() {
        driver.findElement(HTML).click();
    }

    public void assertValidationMessageIsDisplayed() {
        waitAllElementsPresents(VALIDATION_MESSAGE);
        Assert.assertTrue(driver.findElement(VALIDATION_MESSAGE).isDisplayed(),
                AssertErrorMessage.VALIDATION_NOT_SHOWN);
    }

    public void fillInFirstSingleLine() {
        waitAllElementsPresents(SINGLE_LINE_FIRST);
        driver.findElement(SINGLE_LINE_FIRST)
                .sendKeys(TestData.INPUT_VALID_DATA);
    }

    public void fillValidData() {
        waitFieldPresentAndFillIt(SINGLE_LINE_WITH_VALIDATION, TestData.INPUT_VALID_DATA);
    }

    public void fillInNotValidData() {
        waitFieldPresentAndFillIt(SINGLE_LINE_WITH_VALIDATION, TestData.INPUT_INVALID_DATA);
    }

    public void completeButtonClick() {
        waitClickableAndClick(COMPLETE_BUTTON);
    }

    public void checkCompleteButtonIsDisabled() {
        Assert.assertFalse(driver.findElement(COMPLETE_BUTTON)
                .isEnabled(), AssertErrorMessage.COMPLETE_IS_ENABLED);
    }

    public void waitForThankYouPage() {
        waitUrlContains(TestData.THANK_YOU_PAGE_PATH);
        waitAllElementsPresents(THANK_YOU_PAGE_TITLE);
        waitAllElementsPresents(TRY_AIR_SLATE_BUTTON);
    }

    public void checkWeOnTryAirSlatePage() {
        Assert.assertTrue(driver.findElement(TRY_AIR_SLATE_BUTTON).isDisplayed(),
                AssertErrorMessage.NO_THANK_YOU_PAGE_BUTTON);
        Assert.assertTrue(driver.findElement(THANK_YOU_PAGE_TITLE).isDisplayed(),
                AssertErrorMessage.NO_THANK_YOU_PAGE_TITLE);
    }


    public void clickSignatureIcon() {
        waitClickableAndClick(SIGNATURE_ICON);
    }

    public void createCameraSignature() {
        waitClickableAndClick(CAMERA_CAPTURE);
        waitClickableAndClick(SAVE);
        waitClickableAndClick(SAVE);
    }
}

