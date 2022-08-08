package pages.Miromax;

import org.apache.commons.lang3.time.DurationFormatUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.basePage.BasePage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class CinemaHallPage extends BasePage {

    public static final By MIDDLE_BASKET_TICKET_PRICE = By.cssSelector("[class='order-tickets'] li:nth-child(1) [class='order-ticket-price']");
    public static final By DELETE_FIRST_TICKET_IN_BASKET = By.cssSelector("[class='order-tickets'] li:nth-child(1) [class='order-ticket-remove']");
    public static final By SECOND_BASKET_TICKET_PRICE = By.cssSelector("[class='order-tickets'] li:nth-child(2) [class='order-ticket-price']");
    public static final By DELETE_SECOND_TICKET_IN_BASKET = By.cssSelector("[class='order-tickets'] li:nth-child(2) [class='order-ticket-remove']");
    public static final By TOTAL_ORDER_PRICE = By.cssSelector("[class='order-total-header']>span>b ");
    public static final By SEAT_TAKEN = By.cssSelector(" [class$='taken GOOD']");
    public static final By ALL_SEATS_IN_ROW = By.cssSelector(" [class$='GOOD']");
    public static final By SEAT_FREE = By.cssSelector(" [class$='free GOOD']");
    public static final By ROWS_LIST = By.cssSelector(".cinema-seats .cinema-row");
    public static final By MOVIE_NAME = By.cssSelector("[class='order-film-about'] h4");
    public static final By AGE_LIMIT = By.cssSelector("[class='age-limit']");
    public static final By MOVIE_PLACE = By.cssSelector("[class='order-film-about'] :first-child [class='order-film-text']");
    public static final By MOVIE_DATE = By.cssSelector("[class='order-film-items'] [class='order-film-item']:nth-child(2) span");
    public static final By CITY_NAME = By.cssSelector("[class='order-film-item order-film-item--uniq'] span");
    public static final By BASKET_HEADER = By.cssSelector("[class='order-tickets-header']");
    public static final By ERROR_PAGE = By.cssSelector("[class='error-page']");
    public static final By URL_IMAGE = By.cssSelector("[class='order-film-image']");
    public static final String URL_ATTRIBUTE = "style";
    public static final By TICKETS_IN_BASKET = By.cssSelector("[class='order-tickets'] li");
    public static final By ROW_COUNT = By.cssSelector(".cinema-seats .cinema-row");

    public static String cinemaRowValue(int i) {
        return "//div [@class='cinema-row'][" + i + "]";
    }

    public static String seatValue(int i) {
        return " /div[contains(@class, 'cinema-seat') and not(contains(@class, 'hidden'))][" + i + "]";
    }

    public static String setValue() {
        return " /div[contains(@class, 'cinema-seat') and not(contains(@class, 'hidden'))]";
    }

    public static String tooltip() {
        return " /div[@class='seat-tooltip GOOD'] /div[2] //b";
    }

    String middleChosenTicketPrice;
    String secondChosenTicketPrice;

    int middleSeatTicketPrice;
    int secondSeatTicketPrice;

    int middleBasketTicketPrice;
    int secondBasketTicketPrice;

    WebElement MIDDLE_SEAT;
    WebElement RIGHT_SEAT;
    WebElement LEFT_SEAT;

    public CinemaHallPage(WebDriver driver) {
        super(driver);
    }

    public void checkWeOnOrderPage() {
        waitAllElementsPresents(BASKET_HEADER);
        Assert.assertTrue(driver.findElement(BASKET_HEADER).isDisplayed(),
                AssertErrorMessage.NO_ORDER_PAGE);
    }

    public String getMovieDuration(String movieDate) throws ParseException {
        String fullTimeOfMovie = movieDate.replaceAll(" ", "");
        String movieEndsAt = fullTimeOfMovie.substring(fullTimeOfMovie.length() - 5);
        String movieStartsAt = fullTimeOfMovie.substring(fullTimeOfMovie.length() - 11, fullTimeOfMovie.length() - 6);

        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return DurationFormatUtils.formatDuration(format.parse(movieEndsAt)
                .getTime() - format.parse(movieStartsAt).getTime(), "HH:mm");
    }

    public String beInCinemaAt(String date) throws ParseException {
        String fullTimeOfMovie = date.replaceAll(" ", "");
        String movieStartsAt = fullTimeOfMovie.substring(fullTimeOfMovie.length() - 11, fullTimeOfMovie.length() - 6);

        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return DurationFormatUtils.formatDuration(format.parse(movieStartsAt).getTime() - format.parse("00:10").getTime(), "HH:mm");
    }

    public void getMovieInfo() throws ParseException {
        StringBuilder movieName = new StringBuilder();
        waitAllElementsPresents(MOVIE_NAME);
        String dateAndTime = getString(MOVIE_DATE);
        String movieDuration = getMovieDuration(getString(MOVIE_DATE));
        String beInCinemaAt = beInCinemaAt(dateAndTime);
        String url = getAttribute(URL_IMAGE, URL_ATTRIBUTE);
        movieName
                .append("Фильм: ").append(getString(MOVIE_NAME))
                .append("\nМин возраст: ").append(getString(AGE_LIMIT))
                .append("\nКинотеатр находится: ").append(getString(MOVIE_PLACE))
                .append("\nВ кинотеатре нужно быть в ").append(beInCinemaAt)
                .append("\nДлительность фильма: ").append(movieDuration)
                .append("\n").append(getAttribute(URL_IMAGE, URL_ATTRIBUTE), 23, url.length() - 3);
        System.out.println(movieName);
    }

    public void getSeatValue() {
        List<WebElement> rowList = driver.findElements(ROWS_LIST);
        PrintMessage.showRowsInfo(rowList.size());
        for (int i = 0; i < rowList.size(); i++) {
            int seatsTaken = rowList.get(i).findElements(SEAT_TAKEN).size();
            int seatsFree = rowList.get(i).findElements(SEAT_FREE).size();
           int allSeats = seatsFree + seatsTaken;
//            int allSeats = rowList.get(i).findElements(ALL_SEATS_IN_ROW).size();
            PrintMessage.showSeatsInfo((i), allSeats, seatsTaken);
        }
    }

    public int getRowCount() {
        List<WebElement> rowList = driver.findElements(ROW_COUNT);
        return rowList.size();
    }

    public void chooseTwoCentralSeats(int row) {

        int rowsCount = getRowCount();
        List<WebElement> seatsInRow = driver.findElements(By.xpath(cinemaRowValue(row) + setValue()));
        int middleSeatInt = (seatsInRow.size() / 2);

        for (int i = row; i <= rowsCount; i++) {
            MIDDLE_SEAT = driver.findElement(By.xpath(cinemaRowValue(i) + seatValue(middleSeatInt)));
            LEFT_SEAT = driver.findElement(By.xpath(cinemaRowValue(i) + seatValue(middleSeatInt - 1)));
            RIGHT_SEAT = driver.findElement(By.xpath(cinemaRowValue(i) + seatValue(middleSeatInt + 1)));

            if (isMiddleAndLeftSeatsFree(MIDDLE_SEAT, LEFT_SEAT)) {

                MIDDLE_SEAT.click();
                waitForElementVisible(By.xpath(cinemaRowValue(i) + seatValue(middleSeatInt) + tooltip()));
                middleChosenTicketPrice = setMiddleTicketPrice(i, middleSeatInt);

                LEFT_SEAT.click();
                waitForElementVisible(By.xpath(cinemaRowValue(i) + seatValue(middleSeatInt - 1) + tooltip()));
                secondChosenTicketPrice = setLeftTicketPrice(i, middleSeatInt);
                break;
            } else if (isMiddleAndRightSeatsFree(MIDDLE_SEAT, RIGHT_SEAT)) {
                MIDDLE_SEAT.click();
                waitForElementVisible(By.xpath(cinemaRowValue(i) + seatValue(middleSeatInt) + tooltip()));
                middleChosenTicketPrice = setMiddleTicketPrice(i, middleSeatInt);

                RIGHT_SEAT.click();
                waitForElementVisible(By.xpath(cinemaRowValue(i) + seatValue(middleSeatInt + 1) + tooltip()));
                secondChosenTicketPrice = setRightTicketPrice(i, middleSeatInt);
                break;
            } else PrintMessage.allCentralSeatsAreTaken();
        }
        PrintMessage.showTwoCentralFreeSeatsPrice(middleChosenTicketPrice, secondChosenTicketPrice);
    }

    public boolean isMiddleAndLeftSeatsFree(WebElement middleSeat, WebElement leftSeat) {
        return middleSeat.getAttribute("class").contains("free") && leftSeat.getAttribute("class").contains("free");
    }

    public boolean isMiddleAndRightSeatsFree(WebElement middleSeat, WebElement rightSeat) {
        return middleSeat.getAttribute("class").contains("free") && rightSeat.getAttribute("class").contains("free");
    }


    public String setMiddleTicketPrice(int i, int middleSeatInt) {
        return driver.findElement(By.xpath(cinemaRowValue(i) + seatValue(middleSeatInt) + tooltip()))
                .getText().replaceAll("\\D", "");
    }

    public String setLeftTicketPrice(int i, int middleSeatInt) {
        return driver.findElement(By.xpath(cinemaRowValue(i) + seatValue(middleSeatInt - 1) + tooltip()))
                .getText().replaceAll("\\D", "");
    }

    public String setRightTicketPrice(int i, int middleSeatInt) {
        return driver.findElement(By.xpath(cinemaRowValue(i) + seatValue(middleSeatInt + 1) + tooltip()))
                .getText().replaceAll("\\D", "");
    }


    public void checkTicketsInBasket() {

        middleSeatTicketPrice = Integer.parseInt(middleChosenTicketPrice);
        secondSeatTicketPrice = Integer.parseInt(secondChosenTicketPrice);

        middleBasketTicketPrice = Integer.parseInt(driver.findElement(MIDDLE_BASKET_TICKET_PRICE)
                .getText().replaceAll("\\D", ""));
        secondBasketTicketPrice = Integer.parseInt(driver.findElement(SECOND_BASKET_TICKET_PRICE)
                .getText().replaceAll("\\D", ""));

        List<WebElement> ticketBasket = driver.findElements(TICKETS_IN_BASKET);

        int totalOrderPrice = Integer.parseInt(driver.findElement(TOTAL_ORDER_PRICE)
                .getText().replaceAll("\\D", ""));

        Assert.assertEquals(ticketBasket.size(), 2, AssertErrorMessage.WRONG_TICKETS_COUNT_IN_BASKET + ticketBasket.size());

        Assert.assertEquals(middleBasketTicketPrice, middleSeatTicketPrice, AssertErrorMessage.TICKET_PRICE_NOT_EQUAL);

        Assert.assertEquals((middleSeatTicketPrice + secondSeatTicketPrice), middleBasketTicketPrice + secondBasketTicketPrice,
                AssertErrorMessage.TWO_TICKETS_PRICE_NOT_EQUAL);

        Assert.assertEquals(totalOrderPrice, middleBasketTicketPrice + secondBasketTicketPrice,
                AssertErrorMessage.ORDER_AND_TOTAL_PRICE_NOT_EQUAL);
    }

    public void deleteTicketFromBasket() {
        driver.findElement(DELETE_FIRST_TICKET_IN_BASKET).click();

        List<WebElement> ticketBasketAfterDeletion = driver.findElements(TICKETS_IN_BASKET);

        Assert.assertTrue(MIDDLE_SEAT.getAttribute("class").contains("free"), AssertErrorMessage.SEAT_STILL_BUSY_AFTER_DELETE);

        Assert.assertEquals(ticketBasketAfterDeletion.size(), 1,
                AssertErrorMessage.WRONG_TICKETS_COUNT_IN_BASKET + ticketBasketAfterDeletion.size());
    }

}
