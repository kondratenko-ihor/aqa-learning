package hotline.test;

import WebDriverConfig.WebDriverConfig;
import pages.hotline.CategoryPage;
import pages.hotline.DetailPage;
import pages.hotline.MainPage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HotlineTest extends WebDriverConfig {
    MainPage mainPage;
    CategoryPage categoryPage;
    DetailPage detailPage;

    @BeforeTest
    public void openMainPageAndCloseSubscription() {
        mainPage = new MainPage(driver);
        categoryPage = new CategoryPage(driver);
        detailPage = new DetailPage(driver);
        mainPage.closeSubscriptionRequestIfAppears();
    }

    @Test
    public void searchFieldTest() {
        mainPage.checkPlaceholder();
        mainPage.searchForItem();
    }


    @Test(dependsOnMethods = "searchFieldTest")
    public void checkForAdvertProduct() {
        categoryPage.checkAdProductIsShownInList();
    }

    @Test(dependsOnMethods = "checkForAdvertProduct")
    public void filteringTest() {
        categoryPage.filterPriceTest();
        categoryPage.filterByModelTest();
    }


    @Test(dependsOnMethods = "filteringTest")
    public void modelDetailPageTest() {
        detailPage.getInfoForThirdProduct();
    }
}