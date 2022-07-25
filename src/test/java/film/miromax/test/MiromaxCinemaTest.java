package film.miromax.test;

import film.miromax.WebDriverConfig;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.text.ParseException;

public class MiromaxCinemaTest extends WebDriverConfig {


    @Test(enabled = false)
    public void testOpenPage() throws ParseException {
        driver.get("https://miromax.film");
        MiromaxCinemaPage page = new MiromaxCinemaPage(driver);
        page.CloseLocationSelector();
        page.ClickFilterInHeader();
        page.Click2DButtonFilter();
        page.ClickAnimationButtonFilter();
        page.ClickApplyFilterButton();
        page.FindMinionsMovieAndClickFirstSession();
        page.GetMovieInfo();

    }

    @BeforeTest
    public void openPage() {
        driver.get("https://miromax.film");
    }

    @Test
    public void TestFindMovieAndOpenIt() throws ParseException, InterruptedException {
        MiromaxCinemaPage page = new MiromaxCinemaPage(driver);
        page.CloseLocationSelector();
        page.FindMinionsMovieAndClickFirstSession();
        page.GetMovieInfo();
        page.CheckWeOnOrderPage();
    }


    @Test
    public void testCss() {
        MiromaxCinemaPage page = new MiromaxCinemaPage(driver);
        page.getSeatValue();
        page.ChooseTwoCentralSeats(4);
        page.CheckTicketsInBasket();
    }
}

