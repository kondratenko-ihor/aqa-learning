package film.miromax.test;

import film.miromax.WebDriverConfig;
import org.testng.annotations.Test;

public class AirSlateTest extends WebDriverConfig {
    String pbLink1 = "https://arsl.at/VmKWLkym";
    String loginPageUrl = "https://my.airslate.com/login";
    String pbLinkSign = "https://arsl.at/ElPJ3ajm";

    @Test(priority = 0)
    public void FirstTest() {
        driver.get(pbLink1);
        AirSlatePage page = new AirSlatePage(driver);
        page.consentButtonClick();
        page.switchToiFrame();
        page.fillInFirstSingleLine();
        page.fillInValidField();
        page.switchBackIFrame();
        page.completeButtonClick();
        page.waitForThankYouPage();
        page.checkWeOnTryAirSlatePage();
    }

    @Test(priority = 1)
    public void SecondTest() {
        driver.get(pbLink1);
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
    public void ThirdTest() {
        driver.get(pbLink1);
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
    public void FourthTest() {
        driver.get(pbLinkSign);
        AirSlatePage page = new AirSlatePage(driver);
        page.consentButtonClick();
        page.switchToiFrame();
        page.clickSignatureIcon();
        page.createCameraSignature();
        page.switchBackIFrame();
        page.completeButtonClick();
        page.waitForThankYouPage();
        page.checkWeOnTryAirSlatePage();
    }

    @Test(priority = 3)
    public void AddProfileImage() {
        driver.get(loginPageUrl);
        AirSlatePage page = new AirSlatePage(driver);
        page.loginUser();
        page.navigateToProfilePage();
        page.changeAvatar();
        driver.manage().deleteAllCookies();
        driver.get(driver.getCurrentUrl());
    }
}
