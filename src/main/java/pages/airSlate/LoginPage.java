package pages.airSlate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.basePage.BasePage;

public class LoginPage extends BasePage {
    private static final By LOGIN_EMAIL_INPUT = By.cssSelector("[data-qa-tag='user-email-input']");
    private static final By LOGIN_PASSWORD_INPUT = By.cssSelector("[data-qa-tag='user-password-input']");
    private static final By LOGIN_BUTTON = By.cssSelector("[type='submit']");

    public LoginPage(WebDriver driver) {
       super(driver);
    }


    public void openLoginPage(){
       driver.get(TestData.LOGIN_URL);
    }


    public void loginUser() {
        waitFieldPresentAndFillIt(LOGIN_EMAIL_INPUT, TestData.USER_EMAIL);
        waitFieldPresentAndFillIt(LOGIN_PASSWORD_INPUT, TestData.PASSWORD);
        waitClickableAndClick(LOGIN_BUTTON);
        waitUrlContains(TestData.DOMAIN_PATH);
        Assert.assertTrue(driver.getCurrentUrl()
                .contains(TestData.DOMAIN_PATH), AssertErrorMessage.DOMAIN_SELECT_PATH_MISSING);
    }


}
