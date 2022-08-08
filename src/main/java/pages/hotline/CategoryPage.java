package pages.hotline;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.basePage.BasePage;

import java.util.List;

public class CategoryPage extends BasePage {
    private static final By ADVERT_PRODUCT_MIN_PRICE = By.cssSelector("[class='promo-list-item__value-price'] :nth-child(1)");
    private static final By ADVERT_PRODUCT_MAX_PRICE = By.cssSelector("[class='promo-list-item__value-price'] :nth-child(2)");
    private static final By ADVERT_ITEM = By.id("catalogAdvertProduct");
    private static final By ADVERT_MODEL_NAME_SECTION = By.cssSelector("#catalogAdvertProduct :nth-child(2) a");
    private static final By PRODUCT_PRICES_ELEMENTS = By.cssSelector("[class='list-body__content content flex-wrap'] [class='list-item list-item--row'] [class='price m_b-5'] :nth-child(1)");
    private static final By LOADER = By.cssSelector("[class='catalog-list__preloader']");
    private static final By PRODUCT_NAME_ELEMENTS = By.cssSelector("[class='list-item__title-container m_b-5'] a");
    private static final By PAGE_PIKER_1 = By.cssSelector("[class='pagination__pages flex'] a:nth-child(1)");
    private static final By PAGE_PIKER_2 = By.cssSelector("[class='pagination__pages flex'] a:nth-child(2)");
    private static final By PRICE_FILTER = By.cssSelector("[class='filter-price__range flex middle-xs evenly-xs'] :nth-child(4)");
    private static final By BRAND_FILTER = By.cssSelector("[class='sidebar-filters__container'] [class='sidebar-filters__item sidebar-filters__item--top']:nth-child(3)");
    private static final By BRAND_CHECKBOX = By.cssSelector("[class='sidebar-filters__container'] [class='sidebar-filters__item sidebar-filters__item--top']:nth-child(3) [class='filter-checklist__item checkbox flex-inline middle-xs']:nth-child(7)");
    private static final By BRAND_NAME = By.cssSelector("[class='sidebar-filters__container'] [class='sidebar-filters__item sidebar-filters__item--top']:nth-child(3) [class='filter-checklist__item checkbox flex-inline middle-xs']:nth-child(7) span:nth-child(1)");

    public CategoryPage(WebDriver driver) {
        super(driver);
    }

    public void checkAdProductIsShownInList() {
        checkElementIsPresents(driver.findElement(ADVERT_ITEM), AssertErrorMessage.AD_MISSING);
        String model = driver.findElement(ADVERT_MODEL_NAME_SECTION).getText();
        String minPrice = driver.findElement(ADVERT_PRODUCT_MIN_PRICE)
                .getText()
                .replaceAll(" ", "");
        String maxPrice = driver.findElement(ADVERT_PRODUCT_MAX_PRICE)
                .getText()
                .replaceAll(" ", "");
        int minPriceInt = Integer.parseInt(minPrice);
        int maxPriceInt = Integer.parseInt(maxPrice);
        System.out.println("Model: " + model);
        System.out.println("Average price is: " + ((minPriceInt + maxPriceInt) / 2) + " грн");
    }

    public void filterPriceTest() {
        WebElement priceField = driver.findElement(PRICE_FILTER);
        priceField.clear();
        priceField.sendKeys(TestData.PRICE_VALUE);
        priceField.sendKeys(Keys.ENTER);

        waitLoaderEndsIfAppears(LOADER);
        List<WebElement> price15000 = driver.findElements(PRODUCT_PRICES_ELEMENTS);
        for (
                WebElement price : price15000) {
            String priceS = price.getText().replaceAll(" ", "");
            int PRICE = 15000;
            Assert.assertTrue(Double.parseDouble(priceS) <= PRICE, AssertErrorMessage.PRICE_FILTER_ERROR);
        }
    }

    public void filterByModelTest() {
        WebElement brandCheckbox = driver.findElement(BRAND_CHECKBOX);
        String brandName = driver.findElement(BRAND_NAME).getText();
        WebElement pagePicker_1 = driver.findElement(PAGE_PIKER_1);
        WebElement pagePicker_2 = driver.findElement(PAGE_PIKER_2);
        brandCheckbox.click();

        waitLoaderEndsIfAppears(LOADER);
        List<WebElement> productsNameList = driver.findElements(PRODUCT_NAME_ELEMENTS);
        for (
                WebElement product : productsNameList) {
            Assert.assertTrue(product.getText().contains(brandName), AssertErrorMessage.BRAND_FILTER_ERROR);
        }

        waitLoaderEndsIfAppears(LOADER);
        pagePicker_2.click();

        waitLoaderEndsIfAppears(LOADER);
        List<WebElement> productListPage2 = driver.findElements(PRODUCT_NAME_ELEMENTS);
        for (WebElement product : productListPage2) {
            Assert.assertTrue(product.getText().contains(brandName), AssertErrorMessage.BRAND_FILTER_ERROR);
        }
        waitLoaderEndsIfAppears(LOADER);
        pagePicker_1.click();
    }


}
