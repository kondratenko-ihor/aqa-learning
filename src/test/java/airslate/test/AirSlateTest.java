package airslate.test;

import WebDriverConfig.WebDriverConfig;
import org.testng.annotations.*;
import pages.airSlate.LoginPage;
import pages.airSlate.DocumentManagerPage;
import pages.airSlate.ProfilePage;

public class AirSlateTest extends WebDriverConfig {
    DocumentManagerPage documentManagerPage;
    LoginPage loginPage;
    ProfilePage profilePage;

    @BeforeTest
    public void init(){
        documentManagerPage = new DocumentManagerPage(driver);
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);
    }

    @Test(alwaysRun = true)
    public void fillInSingleLinesTest() {
        documentManagerPage.openSingleLinesPublicLink();
        documentManagerPage.consentButtonClick();
        documentManagerPage.switchToiFrame();
        documentManagerPage.fillInFirstSingleLine();
        documentManagerPage.fillValidData();
        documentManagerPage.switchBackToDefaultIFrame();
        documentManagerPage.completeButtonClick();
        documentManagerPage.waitForThankYouPage();
        documentManagerPage.checkWeOnTryAirSlatePage();
    }

    @Test(alwaysRun = true)
    public void linkOpensInNewTabTest() {
        documentManagerPage.openSingleLinesPublicLink();
        documentManagerPage.consentButtonClick();
        documentManagerPage.switchToiFrame();
        documentManagerPage.opensLinkInNewTabCheck();
        documentManagerPage.switchToiFrame();
        documentManagerPage.fillInFirstSingleLine();
        documentManagerPage.switchBackToDefaultIFrame();
        documentManagerPage.completeButtonClick();
        documentManagerPage.waitForThankYouPage();
        documentManagerPage.checkWeOnTryAirSlatePage();
    }

    @Test(priority = 4, alwaysRun = true)
    public void validationMessageTest() {
        documentManagerPage.openSingleLinesPublicLink();
        documentManagerPage.consentButtonClick();
        documentManagerPage.switchToiFrame();
        documentManagerPage.fillInNotValidData();
        documentManagerPage.clickSomewhere();
        documentManagerPage.assertValidationMessageIsDisplayed();
        documentManagerPage.switchBackToDefaultIFrame();
        documentManagerPage.checkCompleteButtonIsDisabled();
    }

    @Test(alwaysRun = true)
    public void signDocViaCameraTest() {
        documentManagerPage.openSignaturePublicLink();
        documentManagerPage.consentButtonClick();
        documentManagerPage.switchToiFrame();
        documentManagerPage.clickSignatureIcon();
        documentManagerPage.createCameraSignature();
        documentManagerPage.switchBackToDefaultIFrame();
        documentManagerPage.completeButtonClick();
        documentManagerPage.waitForThankYouPage();
        documentManagerPage.checkWeOnTryAirSlatePage();

    }

    @Test(alwaysRun = true)
    public void changeProfileImageTest() {
        loginPage.openLoginPage();
        loginPage.loginUser();
        profilePage.openProfilePage();
        profilePage.changeAvatar();
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
    }
}
