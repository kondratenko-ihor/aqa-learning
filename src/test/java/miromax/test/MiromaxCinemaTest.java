package miromax.test;

import WebDriverConfig.WebDriverConfig;
import org.testng.annotations.BeforeTest;
import pages.Miromax.CinemaHallPage;
import pages.Miromax.MainPage;
import org.testng.annotations.Test;
import pages.Miromax.MovieDetailPage;

import java.text.ParseException;

public class MiromaxCinemaTest extends WebDriverConfig {
    MainPage mainPage;
    MovieDetailPage movieDetailPage;
    CinemaHallPage cinemaHallPage;

    @BeforeTest
    public void openPage() {
        mainPage = new MainPage(driver);
        movieDetailPage = new MovieDetailPage(driver);
        cinemaHallPage = new CinemaHallPage(driver);
        mainPage.openMainPage();
        mainPage.closeLocationSelector();
    }

    @Test()
    public void acceptFilter() {
        mainPage.setFilter();
        mainPage.checkMinionsAvailableToOrder();
    }
    @Test(dependsOnMethods = "acceptFilter")
    public void checkMinionsMovieAvailableTest(){
        mainPage.findMinionsMovieAndOpenDetailPage();
    }

    @Test(dependsOnMethods = "checkMinionsMovieAvailableTest")
    public void selectFirstSessionTest() {
        movieDetailPage.clickMinionsFirstSession();
    }

    @Test(dependsOnMethods = "selectFirstSessionTest")
    public void getMovieInfo() throws ParseException {
        cinemaHallPage.getMovieInfo();
    }

    @Test(dependsOnMethods = "getMovieInfo")
    public void getSeatsInfoTest() {
        cinemaHallPage.getSeatValue();
    }

    @Test(dependsOnMethods = "getSeatsInfoTest")
    public void testTicketBuy() {
        cinemaHallPage.chooseTwoCentralSeats(4);
        cinemaHallPage.checkTicketsInBasket();
        cinemaHallPage.deleteTicketFromBasket();
    }
}
