package film.miromax.test;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.Locale;

public class HotlinePage {
    private WebDriver driver;
    // JS
    String JS_CLOSE_SUBSCRIPTION_DIALOG = "document.querySelector('.grv-dialog-host').shadowRoot.querySelector('.buttons-wrapper > :nth-child(1)').click();";

    // Search field
    private final By SEARCH_FIELD = By.id("searchbox");
    private final By SUGGESTIONS_ELEMENTS = By.cssSelector("#suggestion-results > a");
    private final By SUGGESTION_ELEMENT_TITLE = By.cssSelector(".result__title");
    private final By FIRST_ELEMENT_IN_SEARCH = By.cssSelector("#suggestion-results > :nth-child(1)");

    // Category page
    private final By ADVERT_PRODUCT_MIN_PRICE = By.cssSelector("[class=\"promo-list-item__value-price\"] :nth-child(1)");
    private final By ADVERT_PRODUCT_MAX_PRICE = By.cssSelector("[class=\"promo-list-item__value-price\"] :nth-child(2)");
    private final By ADVERT_ITEM = By.id("catalogAdvertProduct");
    private final By ADVERT_MODEL_NAME_SECTION = By.cssSelector("#catalogAdvertProduct :nth-child(2) a");
    private final By PRODUCT_PRICES_ELEMENTS = By.cssSelector("[class=\"list-body__content content flex-wrap\"] [class=\"list-item list-item--row\"] [class=\"price m_b-5\"] :nth-child(1)");
    private final By LOADER = By.cssSelector("[class=\"catalog-list__preloader\"]");
    private final By THIRD_PRODUCT_IN_LIST = By.cssSelector(":nth-child(3) [class=\"list-item__title-container m_b-5\"] a");
    // Filter section
    private final By PRICE_FILTER = By.cssSelector("[class=\"filter-price__range flex middle-xs evenly-xs\"] :nth-child(4)");
    private final String PRICE_VALUE = "15000";
    private final By BRAND_FILTER = By.cssSelector("[class=\"sidebar-filters__container\"] [class=\"sidebar-filters__item sidebar-filters__item--top\"]:nth-child(3)");
    private final By BRAND_CHECKBOX = By.cssSelector("[class=\"sidebar-filters__container\"] [class=\"sidebar-filters__item sidebar-filters__item--top\"]:nth-child(3) [class=\"filter-checklist__item checkbox flex-inline middle-xs\"]:nth-child(7)");
    private final By BRAND_NAME = By.cssSelector("[class=\"sidebar-filters__container\"] [class=\"sidebar-filters__item sidebar-filters__item--top\"]:nth-child(3) [class=\"filter-checklist__item checkbox flex-inline middle-xs\"]:nth-child(7) span:nth-child(1)");
    // Page picker
    private final By PAGE_PIKER_1 = By.cssSelector("[class=\"pagination__pages flex\"] a:nth-child(1)");
    private final By PAGE_PIKER_2 = By.cssSelector("[class=\"pagination__pages flex\"] a:nth-child(2)");
    // Product detail page
    By FILTER_ON_DETAIL_PAGE = By.xpath("//span[contains(text(),\"Оцінка магазину\")]");
    By PRICE_ON_DETAIL_PAGE = By.cssSelector(" [class=\"price__value\"]");
    By RATING = By.cssSelector(" [class=\"shop__rating-value text-lg\"]");
    By SHOP_NAME = By.cssSelector(" [class=\"shop__title mb-row\"]");
    By STORE_LIST = By.cssSelector("[class=\"price content\"] .list [class=\"list__item row flex\"]");

    public HotlinePage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitUntilClickable(By selector) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        wait.until(ExpectedConditions.elementToBeClickable(selector));
    }

    public void waitUntilVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitInvisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        wait.until(ExpectedConditions.stalenessOf(element));
    }

    public void closeSubscriptionRequestIfAppears() {
        try {
            ((JavascriptExecutor) driver).executeScript(JS_CLOSE_SUBSCRIPTION_DIALOG);
        } catch (JavascriptException ignored) {
        }
    }

    public void checkPlaceholder() {
        waitUntilClickable(SEARCH_FIELD);
        WebElement searchField = driver.findElement(SEARCH_FIELD);
        Assert.assertEquals(searchField.getAttribute("autocomplete"), "off", "Autocomplete is turned on!");
    }

    public void searchForItem() {
        driver.findElement(SEARCH_FIELD).sendKeys("Теле");
        waitUntilClickable(SUGGESTIONS_ELEMENTS);
        List<WebElement> suggestionsList = driver.findElements(SUGGESTIONS_ELEMENTS);
        for (WebElement suggestion : suggestionsList) {
            Assert.assertTrue(suggestion.findElement(SUGGESTION_ELEMENT_TITLE).getText().toLowerCase(Locale.ROOT).contains("теле") ||
                    suggestion.findElement(SUGGESTION_ELEMENT_TITLE).getText().toLowerCase(Locale.ROOT).contains("tele"));
        }
        waitUntilClickable(FIRST_ELEMENT_IN_SEARCH);
        WebElement suggestionFirstElement = driver.findElement(FIRST_ELEMENT_IN_SEARCH);
        suggestionFirstElement.click();
    }

    public void advertProductTest() {
        // Проверить что есть рекламное предложение и вывести о нем информацию: модель и средняя цена
        WebElement advertisementItem = driver.findElement(ADVERT_ITEM);
        Assert.assertTrue(advertisementItem.isDisplayed(), "Advert element not presented");

        String model = driver.findElement(ADVERT_MODEL_NAME_SECTION).getText();
        String minPrice = driver.findElement(ADVERT_PRODUCT_MIN_PRICE).getText().replaceAll(" ", "");
        String maxPrice = driver.findElement(ADVERT_PRODUCT_MAX_PRICE).getText().replaceAll(" ", "");
        int minPriceInt = Integer.parseInt(minPrice);
        int maxPriceInt = Integer.parseInt(maxPrice);
        System.out.println("Model: " + model);
        System.out.println("Average price is: " + ((minPriceInt + maxPriceInt) / 2) + " грн");
    }

    public void filterPriceTest() {
        // В фильтре: ввести граничную цену до 15к
        WebElement priceField = driver.findElement(PRICE_FILTER);
        priceField.clear();
        priceField.sendKeys(PRICE_VALUE);
        priceField.sendKeys(Keys.ENTER);

        // проверить что на 1й странице цены не превышают фильтр

        //waitInvisibility(driver.findElement(LOADER));
        waitLoaderEndsIfAppears();
        List<WebElement> price15000 = driver.findElements(PRODUCT_PRICES_ELEMENTS);
        for (
                WebElement price : price15000) {
            String priceS = price.getText().replaceAll(" ", "");
            Assert.assertTrue(Double.parseDouble(priceS) <= 15000, "Not selected price shown in list");
        }
    }

    public void filterModelTest() throws InterruptedException {
        // Выбрать марку: проверить что вывело только эту марку на 2х страницах
        WebElement brandCheckbox = driver.findElement(BRAND_CHECKBOX);
        String brandName = driver.findElement(BRAND_NAME).getText();
        WebElement pagePicker_1 = driver.findElement(PAGE_PIKER_1);
        WebElement pagePicker_2 = driver.findElement(PAGE_PIKER_2);
        brandCheckbox.click();
        //Thread.sleep(2000);

        //driver.findElement(By.cssSelector("[class=\" admix-close-2 dark\"]")).click();
        waitLoaderEndsIfAppears();
        List<WebElement> productsNameList = driver.findElements(By.cssSelector("[class=\"list-item__title-container m_b-5\"] a"));
        for (
                WebElement product : productsNameList) {
            System.out.println(product.getText());
            Assert.assertTrue(product.getText().contains(brandName), "Brand name not in all list models");
        }

        waitLoaderEndsIfAppears();
        pagePicker_2.click();

        waitLoaderEndsIfAppears();
        List<WebElement> productListPage2 = driver.findElements(By.cssSelector("[class=\"list-item__title-container m_b-5\"] a"));
        for (WebElement product : productListPage2) {
            System.out.println(product.getText());
            Assert.assertTrue(product.getText().contains(brandName), "Brand name not in all list models");
        }
        waitLoaderEndsIfAppears();
        pagePicker_1.click();
    }

    public void getInfoForThirdProduct() {
        waitLoaderEndsIfAppears();
        // waitUntilClickable(THIRD_PRODUCT_IN_LIST);
        driver.findElement(THIRD_PRODUCT_IN_LIST).click();
        // waitUntilClickable(FILTER_ON_DETAIL_PAGE);
        driver.findElement(FILTER_ON_DETAIL_PAGE).click();

        waitUntilClickable(STORE_LIST);
        List<WebElement> storeList = driver.findElements(STORE_LIST);
        for (WebElement store : storeList) {
            if ((Double.parseDouble(store.findElement(RATING).getText())) >= 8.0) {
                System.out.print(store.findElement(SHOP_NAME).getText() + ": ");
                System.out.println(store.findElement(PRICE_ON_DETAIL_PAGE).getText().replaceAll("\\D", "") + " грн., ");
                System.out.println("Рейтинг - " + store.findElement(RATING).getText() + "\n");
            } else break;
        }
    }

    public void waitLoaderEndsIfAppears(){
        try {
            waitInvisibility(driver.findElement(LOADER));
        }
        catch (NoSuchElementException e){
            System.out.println("No Loader");
        }
    }
}

