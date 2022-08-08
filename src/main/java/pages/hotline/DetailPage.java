package pages.hotline;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.basePage.BasePage;

import java.util.List;

public class DetailPage extends BasePage {
    public static final By THIRD_PRODUCT_IN_LIST = By.cssSelector(":nth-child(3) [class=\"list-item__title-container m_b-5\"] a");
    public static final By LOADER = By.cssSelector("[class=\"catalog-list__preloader\"]");
    public static final By FILTER_ON_DETAIL_PAGE = By.xpath("//span[contains(text(),\"Оцінка магазину\")]");
    public static final By PRICE_ON_DETAIL_PAGE = By.cssSelector(" [class=\"price__value\"]");
    public static final By RATING = By.cssSelector(" [class=\"shop__rating-value text-lg\"]");
    public static final By SHOP_NAME = By.cssSelector(" [class=\"shop__title mb-row\"]");
    public static final By STORE_LIST = By.cssSelector("[class=\"price content\"] .list [class=\"list__item row flex\"]");


    public DetailPage(WebDriver driver) {
        super(driver);
    }

    public void getInfoForThirdProduct() {
        waitLoaderEndsIfAppears(LOADER);
        waitClickableAndClick(THIRD_PRODUCT_IN_LIST);
        waitClickableAndClick(FILTER_ON_DETAIL_PAGE);

        waitForElementToBeClickable(STORE_LIST);
        List<WebElement> storeList = driver.findElements(STORE_LIST);
        for (WebElement store : storeList) {
            if ((Double.parseDouble(store.findElement(RATING).getText())) >= 8.0) {
                System.out.print(store.findElement(SHOP_NAME).getText() + ": ");
                System.out.println(store.findElement(PRICE_ON_DETAIL_PAGE).getText().replaceAll("\\D", "") + " грн., ");
            } else break;
        }
    }

}
