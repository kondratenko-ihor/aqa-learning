package pages.Miromax;

import org.apache.commons.lang3.time.DurationFormatUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.List;

public class MainPage {
    private final WebDriver driver;

    String middleChosenTicketPrice;
    String secondChosenTicketPrice;

    int middleSeatTicketPrice;
    int secondSeatTicketPrice;

    int middleBasketTicketPrice;
    int secondBasketTicketPrice;

    WebElement MIDDLE_SEAT;
    WebElement RIGHT_SEAT;
    WebElement LEFT_SEAT;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }


    public void closeLocationSelector() {
        waitToBeVisibleAll(Locators.LOCATION_SELECTOR);
        driver.findElement(Locators.LOCATION_SELECTOR).click();
    }

    public void clickFilterInHeader() {
        waitClickableAndClick(Locators.FILTER_HEADER_BUTTON);
    }

    public void clickApplyFilterButton() {
        driver.findElement(Locators.APPLY_FILTER_BUTTON).click();
    }

    public void clickAnimationButtonFilter() {
        driver.findElement(Locators.ANIMATION_BUTTON_IN_FILTER).click();
    }

    public void click2DButtonFilter() {
        waitClickableAndClick(Locators.FILTER_2D_BUTTON);
    }

    public void findMinionsMovieAndOpenDetailPage() {
        waitClickableAndClick(Locators.MINIONS_POSTER);

    }

    public void checkMinionsAvailableToOrder() {
        Assert.assertTrue(driver.findElement(Locators.MINIONS_FIRST_SESSION).isDisplayed());
    }

    public void clickMinionsFirstSession() {
        waitUrlContains(Locators.MINIONS_DETAIL_PATH);
        waitToBeVisibleAll(Locators.MINIONS_FIRST_SESSION);
        waitClickableAndClick(Locators.MINIONS_FIRST_SESSION);
    }

    public void checkWeOnOrderPage() {
        waitToBeVisibleAll(Locators.BASKET_HEADER);
        Assert.assertTrue(driver.findElement(Locators.BASKET_HEADER).isDisplayed(),
                AssertErrorMessage.NO_ORDER_PAGE);
    }

    public String getMovieDuration(String movieDate) throws ParseException {
        String fullTimeOfMovie = movieDate.replaceAll(" ", "");
        String movieEndsAt = fullTimeOfMovie.substring(fullTimeOfMovie.length() - 5);
        String movieStartsAt = fullTimeOfMovie.substring(fullTimeOfMovie.length() - 11, fullTimeOfMovie.length() - 6);

        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return DurationFormatUtils.formatDuration(format.parse(movieEndsAt).getTime() - format.parse(movieStartsAt).getTime(), "HH:mm");
    }

    public String beInCinemaAt(String date) throws ParseException {
        String fullTimeOfMovie = date.replaceAll(" ", "");
        String movieStartsAt = fullTimeOfMovie.substring(fullTimeOfMovie.length() - 11, fullTimeOfMovie.length() - 6);

        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return DurationFormatUtils.formatDuration(format.parse(movieStartsAt).getTime() - format.parse("00:10").getTime(), "HH:mm");
    }

    public void getMovieInfo() throws ParseException {
        waitToBeVisibleAll(Locators.MOVIE_NAME);
        String movieTitle = driver.findElement(Locators.MOVIE_NAME).getText();
        String ageLimit = driver.findElement(Locators.AGE_LIMIT).getText();
        String location = driver.findElement(Locators.MOVIE_PLACE).getText();
        String dateAndTime = driver.findElement(Locators.MOVIE_DATE).getText();
        String movieDuration = getMovieDuration(dateAndTime);
        String beInCinemaAt = beInCinemaAt(dateAndTime);
        String url = driver.findElement(Locators.URL_IMAGE).getAttribute(Locators.URL_ATTRIBUTE);
        PrintMessage.getMovieInfo(movieTitle, ageLimit, location, movieDuration, beInCinemaAt);
        System.out.println(url.substring(23, url.length() - 3));
    }

    public void getSeatValue() {
        List<WebElement> rowList = driver.findElements(Locators.ROWS_LIST);
        PrintMessage.showRowsInfo(rowList.size());
        for (int i = 0; i < rowList.size(); i++) {
            int seatsTaken = rowList.get(i).findElements(Locators.SEAT_TAKEN).size();
            int seatsFree = rowList.get(i).findElements(Locators.SEAT_FREE).size();
            int allSeats = seatsFree + seatsTaken;
            PrintMessage.showSeatsInfo((i + 1), allSeats, seatsTaken);
        }
    }

    public int getRowCount() {
        List<WebElement> rowList = driver.findElements(Locators.ROW_COUNT);
        return rowList.size();
    }

    public void chooseTwoCentralSeats(int row) {

        int rowsCount = getRowCount();
        List<WebElement> seatsInRow = driver.findElements(By.xpath(Locators.cinemaRowValue(row) + Locators.setValue()));
        int middleSeatInt = (seatsInRow.size() / 2);

        for (int i = row; i <= rowsCount; i++) {
            MIDDLE_SEAT = driver.findElement(By.xpath(Locators.cinemaRowValue(i) + Locators.seatValue(middleSeatInt)));
            LEFT_SEAT = driver.findElement(By.xpath(Locators.cinemaRowValue(i) + Locators.seatValue(middleSeatInt -1)));
            RIGHT_SEAT = driver.findElement(By.xpath(Locators.cinemaRowValue(i) + Locators.seatValue(middleSeatInt + 1)));

            if (isMiddleAndLeftSeatsFree(MIDDLE_SEAT, LEFT_SEAT)) {

                MIDDLE_SEAT.click();
                waitVisible(driver.findElement(By.xpath(Locators.cinemaRowValue(i) + Locators.seatValue(middleSeatInt) + Locators.tooltip())));
                middleChosenTicketPrice = setMiddleTicketPrice(i, middleSeatInt);

                LEFT_SEAT.click();
                waitVisible(driver.findElement(By.xpath(Locators.cinemaRowValue(i) + Locators.seatValue(middleSeatInt - 1) + Locators.tooltip())));
                secondChosenTicketPrice = setLeftTicketPrice(i, middleSeatInt);
                break;
            }
            else if (isMiddleAndRightSeatsFree(MIDDLE_SEAT, RIGHT_SEAT)) {
                MIDDLE_SEAT.click();
                waitVisible(driver.findElement(By.xpath(Locators.cinemaRowValue(i) + Locators.seatValue(middleSeatInt) + Locators.tooltip())));
                middleChosenTicketPrice = setMiddleTicketPrice(i, middleSeatInt);

                RIGHT_SEAT.click();
                waitVisible(driver.findElement(By.xpath(Locators.cinemaRowValue(i) + Locators.seatValue(middleSeatInt + 1) + Locators.tooltip())));
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
        return driver.findElement(By.xpath(Locators.cinemaRowValue(i) + Locators.seatValue(middleSeatInt) + Locators.tooltip()))
                .getText().replaceAll("\\D", "");
    }

    public String setLeftTicketPrice(int i, int middleSeatInt) {
        return driver.findElement(By.xpath(Locators.cinemaRowValue(i) + Locators.seatValue(middleSeatInt - 1) + Locators.tooltip()))
                .getText().replaceAll("\\D", "");
    }

    public String setRightTicketPrice(int i, int middleSeatInt) {
        return driver.findElement(By.xpath(Locators.cinemaRowValue(i) + Locators.seatValue(middleSeatInt + 1) + Locators.tooltip()))
                .getText().replaceAll("\\D", "");
    }


    public void checkTicketsInBasket() {

        middleSeatTicketPrice = Integer.parseInt(middleChosenTicketPrice);
        secondSeatTicketPrice = Integer.parseInt(secondChosenTicketPrice);

        middleBasketTicketPrice = Integer.parseInt(driver.findElement(Locators.MIDDLE_BASKET_TICKET_PRICE)
                .getText().replaceAll("\\D", ""));
        secondBasketTicketPrice = Integer.parseInt(driver.findElement(Locators.SECOND_BASKET_TICKET_PRICE)
                .getText().replaceAll("\\D", ""));

        List<WebElement> ticketBasket = driver.findElements(Locators.TICKETS_IN_BASKET);

        int totalOrderPrice = Integer.parseInt(driver.findElement(Locators.TOTAL_ORDER_PRICE)
                .getText().replaceAll("\\D", ""));

        Assert.assertEquals(ticketBasket.size(), 2, AssertErrorMessage.WRONG_TICKETS_COUNT_IN_BASKET + ticketBasket.size());

        Assert.assertEquals(middleBasketTicketPrice, middleSeatTicketPrice, AssertErrorMessage.TICKET_PRICE_NOT_EQUAL);

        Assert.assertEquals((middleSeatTicketPrice + secondSeatTicketPrice), middleBasketTicketPrice + secondBasketTicketPrice,
                AssertErrorMessage.TWO_TICKETS_PRICE_NOT_EQUAL);

        Assert.assertEquals(totalOrderPrice, middleBasketTicketPrice + secondBasketTicketPrice,
                AssertErrorMessage.ORDER_AND_TOTAL_PRICE_NOT_EQUAL);
    }

    public void deleteTicketFromBasket() {
        driver.findElement(Locators.DELETE_FIRST_TICKET_IN_BASKET).click();

        List<WebElement> ticketBasketAfterDeletion = driver.findElements(Locators.TICKETS_IN_BASKET);

        Assert.assertTrue(MIDDLE_SEAT.getAttribute("class").contains("free"), AssertErrorMessage.SEAT_STILL_BUSY_AFTER_DELETE);

        Assert.assertEquals(ticketBasketAfterDeletion.size(), 1,
                AssertErrorMessage.WRONG_TICKETS_COUNT_IN_BASKET + ticketBasketAfterDeletion.size());
    }

    public void waitClickableAndClick(By element) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void waitUrlContains(String element) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlContains(element));
    }

    public void waitToBeVisibleAll(By element) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(element));
    }

    public void waitVisible(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(element));
    }
}