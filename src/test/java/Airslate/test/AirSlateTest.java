package Airslate.test;

import WebDriverConfig.WebDriverConfig;
import pages.airSlate.AirSlatePage;
import org.testng.annotations.Test;

public class AirSlateTest extends WebDriverConfig {
    final String PB_LINK_1 = "https://arsl.at/VmKWLkym";
    final String LOGIN_URL = "https://my.airslate.com/login";
    final String PB_LINK_SIGN = "https://arsl.at/ElPJ3ajm";


    @Test
    public void fillInSingleLinesTest() {
        driver.get(PB_LINK_1);
        AirSlatePage page = new AirSlatePage(driver);
        page.consentButtonClick();
        page.switchToiFrame();
        page.fillInFirstSingleLine();
        page.fillValidData();
        page.switchBackIFrame();
        page.completeButtonClick();
        page.waitForThankYouPage();
        page.checkWeOnTryAirSlatePage();
    }

    @Test(priority = 1)
    public void linkOpensInNewTabTest() {
        driver.get(PB_LINK_1);
        AirSlatePage page = new AirSlatePage(driver);
        page.consentButtonClick();
        page.switchToiFrame();
        page.clickOnLink();
        page.switchToNewTab();
        page.closeSecondTab();
        page.switchToiFrame();
        page.fillInFirstSingleLine();
        page.switchBackIFrame();
        page.completeButtonClick();
        page.waitForThankYouPage();
        page.checkWeOnTryAirSlatePage();
    }

    @Test(priority = 4)
    public void validationMessageTest() {
        driver.get(PB_LINK_1);
        AirSlatePage page = new AirSlatePage(driver);
        page.consentButtonClick();
        page.switchToiFrame();
        page.fillInNotValidData();
        page.clickSomewhere();
        page.assertValidationMessageIsDisplayed();
        page.switchBackIFrame();
        page.checkCompleteButtonIsDisabled();
    }

    @Test(priority = 2)
    public void signDocViaCameraTest() {
        driver.get(PB_LINK_SIGN);
        AirSlatePage page = new AirSlatePage(driver);
        page.consentButtonClick();
        page.switchToiFrame();
        page.clickSignatureIcon();
        page.createCameraSignature();
        page.switchBackIFrame();
        page.completeButtonClick();
        page.waitForThankYouPage();
        page.checkWeOnTryAirSlatePage();
        driver.manage().deleteAllCookies();
        driver.get(driver.getCurrentUrl());
    }

    @Test(priority = 3)
    public void changeProfileImageTest() {
        driver.get(LOGIN_URL);
        AirSlatePage page = new AirSlatePage(driver);
        page.loginUser();
        page.openProfilePage();
        page.changeAvatar();
        driver.manage().deleteAllCookies();
        driver.get(driver.getCurrentUrl());
    }
}
