package film.miromax.test;

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
import java.util.concurrent.TimeUnit;

public class MiromaxCinemaPage {
    private final WebDriver driver;
    By SEAT_TAKEN = By.cssSelector(" [class$=\"taken GOOD\"]");
    By SEAT_FREE = By.cssSelector(" [class$=\"free GOOD\"]");
    By ROWS_LIST = By.cssSelector(".cinema-seats .cinema-row");
    By MOVIE_NAME = By.cssSelector("[class=\"order-film-about\"] h4");
    By AGE_LIMIT = By.cssSelector("[class=\"age-limit\"]");
    By MOVIE_PLACE = By.cssSelector("[class=\"order-film-about\"] :first-child [class=\"order-film-text\"]");
    By MOVIE_DATE = By.cssSelector("[class=\"order-film-items\"] [class=\"order-film-item\"]:nth-child(2) span");
    By CITY_NAME = By.cssSelector("[class=\"order-film-item order-film-item--uniq\"] span");
    By FILTER_HEADER = By.cssSelector("[class=\"header-meta__item filters\"]:first-child");
    By MINIONS_FIRST_SESSION = By.xpath("//div[@class=\"movie__content\"] //h2[text()=\"Посіпаки. Становлення Лиходія\"] /following::ul[@class=\"schedule-proposals\"][1]//li[1]");
    By LOCATION_SELECTOR = By.cssSelector(".confirm-geolocation__bottom :first-child");

    //Filter section
    By APPLY_FILTER_BUTTON = By.cssSelector(".pick-movie-buttons:nth-child(2) button:nth-child(2)");
    By ANIMATION_BUTTON_IN_FILTER = By.xpath("//li[contains(p, 'Анімація')]");
    By FILTER_2D_BUTTON = By.xpath("//li[contains(p, '2D')]");

    //Ticket Basket
    By MIDDLE_BASKET_TICKET_PRICE = By.cssSelector("[class=\"order-tickets\"] li:nth-child(1) [class=\"order-ticket-price\"]");
    By DELETE_FIRST_TICKET_IN_BASKET = By.cssSelector("[class=\"order-tickets\"] li:nth-child(1) [class=\"order-ticket-remove\"]");
    By SECOND_BASKET_TICKET_PRICE = By.cssSelector("[class=\"order-tickets\"] li:nth-child(2) [class=\"order-ticket-price\"]");
    By DELETE_SECOND_TICKET_IN_BASKET = By.cssSelector("[class=\"order-tickets\"] li:nth-child(2) [class=\"order-ticket-remove\"]");

    By TOTAL_ORDER_PRICE = By.cssSelector("[class=\"order-total-header\"]>span>b ");

    // Cinema Hall
    By BASKET_HEADER = By.cssSelector("[class=\"order-tickets-header\"]");
    By ERROR_PAGE = By.cssSelector("[class=\"error-page\"]");
    By URL_IMAGE = By.cssSelector("[class=\"order-film-image\"]");

    String middleChosenTicketPrice;
    String secondChosenTicketPrice;

    WebElement MIDDLE_SEAT;
    WebElement RIGHT_SEAT;
    WebElement LEFT_SEAT;


    public MiromaxCinemaPage(WebDriver driver) {
        this.driver = driver;

    }

    public void CloseLocationSelector() {
//        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("[class=\"confirm-geolocation\"]"))));
       // wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(LOCATION_SELECTOR))).click();
        WebElement confirmLocation = driver.findElement(LOCATION_SELECTOR);
        confirmLocation.click();
    }

    public void ClickFilterInHeader() {
        driver.findElement(FILTER_HEADER).click();
    }

    public void ClickApplyFilterButton() {
        driver.findElement(APPLY_FILTER_BUTTON).click();
    }

    public void ClickAnimationButtonFilter() {
        driver.findElement(ANIMATION_BUTTON_IN_FILTER).click();
    }

    public void Click2DButtonFilter() {
        driver.findElement(FILTER_2D_BUTTON).click();
    }


    public void FindMinionsMovieAndClickFirstSession() {
        WebElement MinionsMovie = driver.findElement(MINIONS_FIRST_SESSION);
        MinionsMovie.click();
    }

    public void CheckWeOnOrderPage() {
        Assert.assertTrue(driver.findElement(BASKET_HEADER).isDisplayed(),
                "We are not on a order page");
    }

    public String getMovieDuration(String movieDate) throws ParseException {
        String fullTimeOfMovie = movieDate.replaceAll(" ", "");
        String endMovie = fullTimeOfMovie.substring(fullTimeOfMovie.length() - 5);
        String startMovie = fullTimeOfMovie.substring(fullTimeOfMovie.length() - 11, fullTimeOfMovie.length() - 6);

        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return DurationFormatUtils.formatDuration(format.parse(endMovie).getTime() - format.parse(startMovie).getTime(), "HH:mm");
    }

    public String beAt(String date) throws ParseException {
        String fullTimeOfMovie = date.replaceAll(" ", "");
        String startMovie = fullTimeOfMovie.substring(fullTimeOfMovie.length() - 11, fullTimeOfMovie.length() - 6);

        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return DurationFormatUtils.formatDuration(format.parse(startMovie).getTime() - format.parse("00:10").getTime(), "HH:mm");
    }

    public void GetMovieInfo() throws ParseException {
        String movieTitle = driver.findElement(MOVIE_NAME).getText();
        String ageLimit = driver.findElement(AGE_LIMIT).getText();
        String location = driver.findElement(MOVIE_PLACE).getText();
        String city = driver.findElement(CITY_NAME).getText().replaceAll("\\,.*$", "");
        String dateAndTime = driver.findElement(MOVIE_DATE).getText();

        System.out.println("Фильм: " + movieTitle + "\nМин возраст: " + ageLimit);
        System.out.println("Кинотеарт : " + location);
        System.out.println("В кинотеатре нужно быть в " + beAt(dateAndTime) + " , длительность фильма " + getMovieDuration(dateAndTime));
        System.out.println("Ссылка на афишу: " + driver.findElement(URL_IMAGE).getAttribute("style"));
    }

    public void getSeatValue() {
        List<WebElement> rowList = driver.findElements(ROWS_LIST);
        System.out.println("Всего в зале: " + rowList.size() + " рядов");
        for (int i = 0; i < rowList.size(); i++) {
            int seatsTaken = rowList.get(i).findElements(SEAT_TAKEN).size();
            int seatsFree = rowList.get(i).findElements(SEAT_FREE).size();
            int allSeats = seatsFree + seatsTaken;
            System.out.println("Ряд " + (i + 1) + ": всего мест: " + allSeats + ", из них занято " + seatsTaken);
        }
    }


    public int getRowCount() {
        List<WebElement> rowList = driver.findElements(By.cssSelector(".cinema-seats .cinema-row"));
        return rowList.size();
    }


    public void ChooseTwoCentralSeats(int row) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        int rowsCount = getRowCount();
        List<WebElement> fourthRowSeat = driver.findElements(By.xpath("//div [@class=\"cinema-row\"][" + row + "]" +
                " /div[contains(@class, 'cinema-seat') and not(contains(@class, 'hidden'))]"));

        int middleSeatInt = (fourthRowSeat.size() / 2);

        for (int i = row; i <= rowsCount; i++) {
            MIDDLE_SEAT = driver.findElement(By.xpath("//div [@class=\"cinema-row\"][" + i + "] /div[contains(@class, 'cinema-seat') and not(contains(@class, 'hidden'))][" + middleSeatInt + "]"));
            LEFT_SEAT = driver.findElement(By.xpath("//div [@class=\"cinema-row\"][" + i + "] /div[contains(@class, 'cinema-seat') and not(contains(@class, 'hidden'))][" + (middleSeatInt - 1) + "]"));
            RIGHT_SEAT = driver.findElement(By.xpath("//div [@class=\"cinema-row\"][" + i + "] /div[contains(@class, 'cinema-seat') and not(contains(@class, 'hidden'))][" + (middleSeatInt + 1) + "]"));

            System.out.println("Пробуем выбрать два билета для ряда " + i);
            if (MiddleAndLeftSeatsFree(MIDDLE_SEAT, LEFT_SEAT)) {
                MIDDLE_SEAT.click();
                wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div [@class=\"cinema-row\"][" + i + "]" +
                        " /div[contains(@class, 'cinema-seat') and not(contains(@class, 'hidden'))]["
                        + middleSeatInt + "] /div[@class=\"seat-tooltip GOOD\"] /div[2] //b"))));

                middleChosenTicketPrice = setMiddleTicketPrice(i, middleSeatInt);
                LEFT_SEAT.click();
                wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div [@class=\"cinema-row\"][" + i + "]" +
                        " /div[contains(@class, 'cinema-seat') and not(contains(@class, 'hidden'))]["
                        + (middleSeatInt - 1) + "] /div[@class=\"seat-tooltip GOOD\"] /div[2] //b"))));
                secondChosenTicketPrice = setLeftTicketPrice(i, middleSeatInt);
                System.out.println("Цена за первый билет - " + middleChosenTicketPrice + " грн");
                System.out.println("Цена за второй билет - " + secondChosenTicketPrice + " грн");
                break;
            } else if (MiddleAndRightSeatsFree(MIDDLE_SEAT, RIGHT_SEAT)) {
                MIDDLE_SEAT.click();
                wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div [@class=\"cinema-row\"][" + i + "]" +
                        " /div[contains(@class, 'cinema-seat') and not(contains(@class, 'hidden'))]["
                        + middleSeatInt + "] /div[@class=\"seat-tooltip GOOD\"] /div[2] //b"))));
                middleChosenTicketPrice = setMiddleTicketPrice(i, middleSeatInt);
                RIGHT_SEAT.click();
                wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div [@class=\"cinema-row\"][" + i + "]" +
                        " /div[contains(@class, 'cinema-seat') and not(contains(@class, 'hidden'))]["
                        + (middleSeatInt - 1) + "] /div[@class=\"seat-tooltip GOOD\"] /div[2] //b"))));
                secondChosenTicketPrice = setRightTicketPrice(i, middleSeatInt);

                System.out.println("Цена за первый билет - " + middleChosenTicketPrice + " грн");
                System.out.println("Цена за второй билет - " + secondChosenTicketPrice + " грн");
                break;
            } else System.out.println("Все центральные места заняты");
        }
    }

    public boolean MiddleAndLeftSeatsFree(WebElement middleSeat, WebElement leftSeat) {
        return middleSeat.getAttribute("class").contains("free") && leftSeat.getAttribute("class").contains("free");
    }

    public boolean MiddleAndRightSeatsFree(WebElement middleSeat, WebElement rightSeat) {
        return middleSeat.getAttribute("class").contains("free") && rightSeat.getAttribute("class").contains("free");
    }

    public String setMiddleTicketPrice(int i, int middleSeatInt) {
        return driver.findElement(By.xpath("//div [@class=\"cinema-row\"][" + i + "]" +
                " /div[contains(@class, 'cinema-seat') and not(contains(@class, 'hidden'))]["
                + middleSeatInt + "] /div[@class=\"seat-tooltip GOOD\"] /div[2] //b")).getText().replaceAll("\\D", "");
    }

    public String setLeftTicketPrice(int i, int middleSeatInt) {
        return driver.findElement(By.xpath("//div [@class=\"cinema-row\"][" + i + "]" +
                " /div[contains(@class, 'cinema-seat') and not(contains(@class, 'hidden'))]["
                + (middleSeatInt - 1) + "] /div[@class=\"seat-tooltip GOOD\"] /div[2] //b")).getText().replaceAll("\\D", "");
    }

    public String setRightTicketPrice(int i, int middleSeatInt) {
        return driver.findElement(By.xpath("//div [@class=\"cinema-row\"][" + i + "]" +
                " /div[contains(@class, 'cinema-seat') and not(contains(@class, 'hidden'))]["
                + (middleSeatInt + 1) + "] /div[@class=\"seat-tooltip GOOD\"] /div[2] //b")).getText().replaceAll("\\D", "");
    }


    public void CheckTicketsInBasket() {
        int MiddleChosenTicketInt = Integer.parseInt(middleChosenTicketPrice);
        int SecondChosenTicketInt = Integer.parseInt(secondChosenTicketPrice);

        int middleBasketTicketPrice = Integer.parseInt(driver.findElement(MIDDLE_BASKET_TICKET_PRICE).getText().replaceAll("\\D", ""));
        int secondBasketTicketPrice = Integer.parseInt(driver.findElement(SECOND_BASKET_TICKET_PRICE).getText().replaceAll("\\D", ""));
        List<WebElement> ticketBasket = driver.findElements(By.cssSelector("[class=\"order-tickets\"] li"));

        int totalOrderPrice = Integer.parseInt(driver.findElement(TOTAL_ORDER_PRICE).getText().replaceAll("\\D", ""));

        System.out.println("Количество билетов в корзине: " + ticketBasket.size());

        Assert.assertEquals(ticketBasket.size(), 2);

        System.out.println("Цена за первый билет в корзине: " + middleBasketTicketPrice + " грн");
        System.out.println("Цена за второй билет в корзине: " + secondBasketTicketPrice + " грн\n");

        Assert.assertEquals(middleBasketTicketPrice, MiddleChosenTicketInt, "Цена билетов не совпадает");
        Assert.assertEquals((MiddleChosenTicketInt + SecondChosenTicketInt), middleBasketTicketPrice + secondBasketTicketPrice,
                "Сумма двух билетов в корзине не равна сумме в tooltips");
        Assert.assertEquals(totalOrderPrice, middleBasketTicketPrice + secondBasketTicketPrice,
                "Сумма билетов в корзине не равна сумме в разделе \"Всего\"");

        driver.findElement(DELETE_FIRST_TICKET_IN_BASKET).click();
        List<WebElement> ticketBasketAfterDeletion = driver.findElements(By.cssSelector("[class=\"order-tickets\"] li"));
        Assert.assertTrue(MIDDLE_SEAT.getAttribute("class").contains("free"), "Место все еще занято, после удаления из корзины");

        Assert.assertEquals(ticketBasketAfterDeletion.size(), 1, "После удаления одного билета неверное " +
                "количество билетов: " + ticketBasketAfterDeletion.size() + " билетов в корзине");



    }
}