package pages.Hotline;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.Locale;

public class HotlinePage {
    private final WebDriver driver;
    final String ATTRIBUTE_NAME = "autocomplete";
    final String ATTRIBUTE_VALUE_EXPECTED = "off";
    final String SEARCH_WORD = "Теле";
    final String SEARCH_WORD_LATIN = "tele";
    final String SEARCH_WORD_CYRILLIC = "теле";

    public HotlinePage(WebDriver driver) {
        this.driver = driver;
    }

    public void closeSubscriptionRequestIfAppears() {
        try {
            ((JavascriptExecutor) driver).executeScript(Locators.JS_CLOSE_SUBSCRIPTION_DIALOG);
        } catch (JavascriptException ignored) {
        }
    }

    public void checkPlaceholder() {
        waitToBeClickable(Locators.SearchField.SEARCH_FIELD);
        WebElement searchField = driver.findElement(Locators.SearchField.SEARCH_FIELD);
        Assert.assertEquals(searchField.getAttribute(ATTRIBUTE_NAME), ATTRIBUTE_VALUE_EXPECTED, AssertErrorMessage.AUTOCOMPLETE_ON);
    }

    public void searchForItem() {
        driver.findElement(Locators.SearchField.SEARCH_FIELD).sendKeys(SEARCH_WORD);
        waitToBeClickable(Locators.SearchField.SUGGESTIONS_ELEMENTS);
        List<WebElement> suggestionsList = driver.findElements(Locators.SearchField.SUGGESTIONS_ELEMENTS);
        for (WebElement suggestion : suggestionsList) {
            Assert.assertTrue(suggestion.findElement(Locators.SearchField.SUGGESTION_ELEMENT_TITLE).getText().toLowerCase(Locale.ROOT).contains(SEARCH_WORD_CYRILLIC) ||
                            suggestion.findElement(Locators.SearchField.SUGGESTION_ELEMENT_TITLE).getText().toLowerCase(Locale.ROOT).contains(SEARCH_WORD_LATIN),
                    AssertErrorMessage.SEARCH_SUGGEST_ERROR);
        }
        waitToBeClickable(Locators.SearchField.FIRST_ELEMENT_IN_SEARCH);
        WebElement suggestionFirstElement = driver.findElement(Locators.SearchField.FIRST_ELEMENT_IN_SEARCH);
        suggestionFirstElement.click();
    }

    public void checkAdProductIsShownInList() {
        WebElement advertisementItem = driver.findElement(Locators.CategoryPage.ADVERT_ITEM);
        Assert.assertTrue(advertisementItem.isDisplayed(), AssertErrorMessage.AD_MISSING);

        String model = driver.findElement(Locators.CategoryPage.ADVERT_MODEL_NAME_SECTION).getText();
        String minPrice = driver.findElement(Locators.CategoryPage.ADVERT_PRODUCT_MIN_PRICE).getText().replaceAll(" ", "");
        String maxPrice = driver.findElement(Locators.CategoryPage.ADVERT_PRODUCT_MAX_PRICE).getText().replaceAll(" ", "");
        int minPriceInt = Integer.parseInt(minPrice);
        int maxPriceInt = Integer.parseInt(maxPrice);
        System.out.println("Model: " + model);
        System.out.println("Average price is: " + ((minPriceInt + maxPriceInt) / 2) + " грн");
    }

    public void filterPriceTest() {
        WebElement priceField = driver.findElement(Locators.Filter.PRICE_FILTER);
        priceField.clear();
        priceField.sendKeys(Locators.Filter.PRICE_VALUE);
        priceField.sendKeys(Keys.ENTER);

        waitLoaderEndsIfAppears();
        List<WebElement> price15000 = driver.findElements(Locators.CategoryPage.PRODUCT_PRICES_ELEMENTS);
        for (
                WebElement price : price15000) {
            String priceS = price.getText().replaceAll(" ", "");
            int PRICE = 15000;
            Assert.assertTrue(Double.parseDouble(priceS) <= PRICE, AssertErrorMessage.PRICE_FILTER_ERROR);
        }
    }

    public void filterByModelTest() {
        WebElement brandCheckbox = driver.findElement(Locators.Filter.BRAND_CHECKBOX);
        String brandName = driver.findElement(Locators.Filter.BRAND_NAME).getText();
        WebElement pagePicker_1 = driver.findElement(Locators.CategoryPage.PAGE_PIKER_1);
        WebElement pagePicker_2 = driver.findElement(Locators.CategoryPage.PAGE_PIKER_2);
        brandCheckbox.click();

        waitLoaderEndsIfAppears();
        List<WebElement> productsNameList = driver.findElements(Locators.CategoryPage.PRODUCT_NAME_ELEMENTS);
        for (
                WebElement product : productsNameList) {
            Assert.assertTrue(product.getText().contains(brandName), AssertErrorMessage.BRAND_FILTER_ERROR);
        }

        waitLoaderEndsIfAppears();
        pagePicker_2.click();

        waitLoaderEndsIfAppears();
        List<WebElement> productListPage2 = driver.findElements(Locators.CategoryPage.PRODUCT_NAME_ELEMENTS);
        for (WebElement product : productListPage2) {
            Assert.assertTrue(product.getText().contains(brandName), AssertErrorMessage.BRAND_FILTER_ERROR);
        }
        waitLoaderEndsIfAppears();
        pagePicker_1.click();
    }

    public void getInfoForThirdProduct() {
        waitLoaderEndsIfAppears();
        waitClickableAndClick(Locators.CategoryPage.THIRD_PRODUCT_IN_LIST);
        waitClickableAndClick(Locators.ProductDetailPage.FILTER_ON_DETAIL_PAGE);

        waitToBeClickable(Locators.ProductDetailPage.STORE_LIST);
        List<WebElement> storeList = driver.findElements(Locators.ProductDetailPage.STORE_LIST);
        for (WebElement store : storeList) {
            if ((Double.parseDouble(store.findElement(Locators.ProductDetailPage.RATING).getText())) >= 8.0) {
                System.out.print(store.findElement(Locators.ProductDetailPage.SHOP_NAME).getText() + ": ");
                System.out.println(store.findElement(Locators.ProductDetailPage.PRICE_ON_DETAIL_PAGE).getText().replaceAll("\\D", "") + " грн., ");
                System.out.println("Рейтинг - " + store.findElement(Locators.ProductDetailPage.RATING).getText() + "\n");
            } else break;
        }
    }

    public void waitLoaderEndsIfAppears() {
        try {
            waitInvisibility(driver.findElement(Locators.CategoryPage.LOADER));
        } catch (NoSuchElementException ignored) {
        }
    }

    public void waitToBeClickable(By element) {
        new WebDriverWait(driver, Duration.ofSeconds(7))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitInvisibility(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(7))
                .until(ExpectedConditions.stalenessOf(element));
    }

    public void waitClickableAndClick(By element) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(element)).click();
    }
}

