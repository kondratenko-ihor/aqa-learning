package film.miromax.test;

import film.miromax.WebDriverConfig;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HotlineTest extends WebDriverConfig {

    @BeforeTest
    public void TestHotlinePage() {
        driver.get("https://hotline.ua");
        HotlinePage page = new HotlinePage(driver);
        page.closeSubscriptionRequestIfAppears();
    }

    @Test(priority = 0)
    public void TestPlaceholder() {
        HotlinePage page = new HotlinePage(driver);
        page.checkPlaceholder();
    }

    @Test(dependsOnMethods = "TestPlaceholder")
    public void TestSearchField() {
        HotlinePage page = new HotlinePage(driver);
        page.searchForItem();

    }

    @Test(dependsOnMethods = "TestSearchField")
    public void CheckForAdvertProduct() {
        HotlinePage page = new HotlinePage(driver);
        page.advertProductTest();
    }

    @Test(dependsOnMethods = "CheckForAdvertProduct")
    public void TestFilterByPrice() {
        HotlinePage page = new HotlinePage(driver);
        page.filterPriceTest();
    }

    @Test(dependsOnMethods = "TestFilterByPrice")
    public void TestFilterByModel() throws InterruptedException {
        HotlinePage page = new HotlinePage(driver);
        page.filterModelTest();
    }

    @Test(dependsOnMethods = "TestFilterByModel")
    public void TestModelDetailPage() {
        HotlinePage page = new HotlinePage(driver);
        page.getInfoForThirdProduct();
    }
}

