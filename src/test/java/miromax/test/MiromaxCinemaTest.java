package miromax.test;

import WebDriverConfig.WebDriverConfig;
import org.testng.annotations.BeforeTest;
import pages.Miromax.Locators;
import pages.Miromax.MainPage;
import org.testng.annotations.Test;

import java.text.ParseException;

public class MiromaxCinemaTest extends WebDriverConfig {

    @BeforeTest
    public void openPage() {
        driver.get(Locators.MAIN_PAGE_LINK);
        new MainPage(driver).closeLocationSelector();
    }

    @Test()
    public void filterTest() {
        MainPage page = new MainPage(driver);
        driver.get(Locators.MAIN_PAGE_LINK);
        page.clickFilterInHeader();
        page.click2DButtonFilter();
        page.clickAnimationButtonFilter();
        page.clickApplyFilterButton();
        page.checkMinionsAvailableToOrder();

    }
    @Test(dependsOnMethods = "filterTest")
    public void selectFirstSessionTest(){
        MainPage page = new MainPage(driver);
        page.findMinionsMovieAndOpenDetailPage();
        page.clickMinionsFirstSession();
        page.checkWeOnOrderPage();
    }

    @Test(dependsOnMethods = "selectFirstSessionTest")
    public void getMovieInfo() throws ParseException {
        new MainPage(driver).getMovieInfo();
    }

    @Test(dependsOnMethods = "getMovieInfo")
    public void getSeatsInfoTest(){
        new MainPage(driver).getSeatValue();
    }

    @Test(dependsOnMethods = "getSeatsInfoTest")
    public void testTicketBuy() {
        MainPage page = new MainPage(driver);
        page.chooseTwoCentralSeats(4);
        page.checkTicketsInBasket();
        page.deleteTicketFromBasket();
    }
}
