package pages.Miromax;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.basePage.BasePage;

public class MovieDetailPage extends BasePage {

    public static final By MINIONS_FIRST_SESSION = By.cssSelector("ul.schedule-proposals li:nth-child(1)");
    public static final String MINIONS_DETAIL_PATH = "/posipaki-stanovlennya-lixodiya";

    public MovieDetailPage(WebDriver driver) {
        super(driver);
    }

    public void clickMinionsFirstSession() {
        waitUrlContains(MINIONS_DETAIL_PATH);
        waitAllElementsPresents(MINIONS_FIRST_SESSION);
        waitClickableAndClick(MINIONS_FIRST_SESSION);
    }
}
