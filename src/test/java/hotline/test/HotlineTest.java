package hotline.test;

import WebDriverConfig.WebDriverConfig;

import pages.Hotline.HotlinePage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HotlineTest extends WebDriverConfig {
    final String MAIN_PAGE_URL = "https://hotline.ua";

    @BeforeTest
    public void testHotlinePage() {
        driver.get(MAIN_PAGE_URL);
        new HotlinePage(driver).closeSubscriptionRequestIfAppears();
    }

    @Test
    public void placeholderTest() {
        new HotlinePage(driver).checkPlaceholder();
    }

    @Test(dependsOnMethods = "placeholderTest")
    public void searchFieldTest() {
        new HotlinePage(driver).searchForItem();
    }

    @Test(dependsOnMethods = "searchFieldTest")
    public void checkForAdvertProduct() {
        new HotlinePage(driver).checkAdProductIsShownInList();
    }

    @Test(dependsOnMethods = "checkForAdvertProduct")
    public void filterByPriceTest() {
        new HotlinePage(driver).filterPriceTest();
    }

    @Test(dependsOnMethods = "filterByPriceTest")
    public void filterByModelTest() {
        new HotlinePage(driver).filterByModelTest();
    }

    @Test(dependsOnMethods = "filterByModelTest")
    public void modelDetailPageTest() {
        new HotlinePage(driver).getInfoForThirdProduct();
    }
}