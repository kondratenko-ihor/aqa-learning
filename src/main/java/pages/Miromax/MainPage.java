package pages.Miromax;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.basePage.BasePage;


public class MainPage extends BasePage {

    public static final String MAIN_PAGE_LINK = "https://miromax.film";
    public static final By LOCATION_SELECTOR = By.cssSelector(".confirm-geolocation__bottom :first-child");
    public static final By MINIONS_POSTER = By.xpath("//div[@class='movie__content'] //h2[text()='Посіпаки. Становлення Лиходія'] /preceding::div[@class='movie__action']");
    public static final By FILTER_HEADER_BUTTON = By.cssSelector("[class='header-meta__item filters']:first-child button");
    public static final By APPLY_FILTER_BUTTON = By.cssSelector(".pick-movie-buttons:nth-child(2) button:nth-child(2)");
    public static final By ANIMATION_BUTTON_IN_FILTER = By.xpath("//li[contains(p, 'Анімація')]");
    public static final By FILTER_2D_BUTTON = By.xpath("//li[contains(p, '2D')]");
    public static final By MINIONS_FIRST_SESSION = By.cssSelector("ul.schedule-proposals li:nth-child(1)");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void openMainPage() {
        driver.get(MAIN_PAGE_LINK);
    }

    public void closeLocationSelector() {
        waitClickableAndClick(LOCATION_SELECTOR);
    }

    public void setFilter() {
        waitClickableAndClick(FILTER_HEADER_BUTTON);
        waitForElementAndClick(ANIMATION_BUTTON_IN_FILTER);
        waitForElementAndClick(FILTER_2D_BUTTON);
        waitForElementAndClick(APPLY_FILTER_BUTTON);
    }

    public void findMinionsMovieAndOpenDetailPage() {
        waitClickableAndClick(MINIONS_POSTER);

    }

    public void checkMinionsAvailableToOrder() {
        Assert.assertTrue(driver.findElement(MINIONS_FIRST_SESSION).isDisplayed());
    }


}