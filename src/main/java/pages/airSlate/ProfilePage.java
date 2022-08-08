package pages.airSlate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.basePage.BasePage;

import java.io.File;

public class ProfilePage extends BasePage {
    private static final By CHANGE_ICON_BUTTON = By.cssSelector("[class='photo-uploader__action-btn'] button");
    private static final By SELECT_IMAGE_BUTTON = By.cssSelector("[accept='image/jpeg,image/png,image/gif']");
    private static final By TAKE_PICTURE_BUTTON = By.cssSelector("[class='drag-and-drop__action-item']:nth-child(2)");
    private static final By NOTIFICATION = By.cssSelector("[class='notifications__item notifications__item--success is-show']");
    private static final By SAVE_AVATAR_BUTTON = By.cssSelector("[class='button button--sm button--primary']");
    private static final By SAVE_IMAGE = By.cssSelector("[class='button button--sm button--primary']");

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public void openProfilePage() {
        String URL = TestData.PERSONAL_INFORMATION_URL;
        driver.get(URL);
        waitUrlContains(URL);
        Assert.assertTrue(driver.getCurrentUrl().contains(URL));
    }

    public void changeAvatar() {
        waitClickableAndClick((CHANGE_ICON_BUTTON));
        File file = new File(TestData.IMAGE_PATH);
        driver.findElement(SELECT_IMAGE_BUTTON)
                .sendKeys(file.getAbsolutePath());
        waitClickableAndClick((SAVE_IMAGE));
        waitAllElementsPresents(NOTIFICATION);
        Assert.assertTrue(driver.findElement(NOTIFICATION).isDisplayed(),
                AssertErrorMessage.NOTIFICATION_MISSING);
    }


}
