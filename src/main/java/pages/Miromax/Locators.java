package pages.Miromax;

import org.openqa.selenium.By;

public class Locators {
    public static final String MAIN_PAGE_LINK = "https://miromax.film";
    public static final By SEAT_TAKEN = By.cssSelector(" [class$='taken GOOD']");
    public static final By SEAT_FREE = By.cssSelector(" [class$='free GOOD']");
    public static final By ROWS_LIST = By.cssSelector(".cinema-seats .cinema-row");
    public static final By MOVIE_NAME = By.cssSelector("[class='order-film-about'] h4");
    public static final By AGE_LIMIT = By.cssSelector("[class='age-limit']");
    public static final By MOVIE_PLACE = By.cssSelector("[class='order-film-about'] :first-child [class='order-film-text']");
    public static final By MOVIE_DATE = By.cssSelector("[class='order-film-items'] [class='order-film-item']:nth-child(2) span");
    public static final By CITY_NAME = By.cssSelector("[class='order-film-item order-film-item--uniq'] span");
    public static final By FILTER_HEADER_BUTTON = By.cssSelector("[class='header-meta__item filters']:first-child button");
    public static final By MINIONS_FIRST_SESSION = By.cssSelector("ul.schedule-proposals li:nth-child(1)");
    public static final By LOCATION_SELECTOR = By.cssSelector(".confirm-geolocation__bottom :first-child");
    public static final By MINIONS_POSTER = By.xpath("//div[@class='movie__content'] //h2[text()='Посіпаки. Становлення Лиходія'] /preceding::div[@class='movie__action']");
    public static final String MINIONS_DETAIL_PATH = "/posipaki-stanovlennya-lixodiya";
    //Filter section
    public static final By APPLY_FILTER_BUTTON = By.cssSelector(".pick-movie-buttons:nth-child(2) button:nth-child(2)");
    public static final By ANIMATION_BUTTON_IN_FILTER = By.xpath("//li[contains(p, 'Анімація')]");
    public static final By FILTER_2D_BUTTON = By.xpath("//li[contains(p, '2D')]");

    //Ticket Basket
    public static final By MIDDLE_BASKET_TICKET_PRICE = By.cssSelector("[class='order-tickets'] li:nth-child(1) [class='order-ticket-price']");
    public static final By DELETE_FIRST_TICKET_IN_BASKET = By.cssSelector("[class='order-tickets'] li:nth-child(1) [class='order-ticket-remove']");
    public static final By SECOND_BASKET_TICKET_PRICE = By.cssSelector("[class='order-tickets'] li:nth-child(2) [class='order-ticket-price']");
    public static final By DELETE_SECOND_TICKET_IN_BASKET = By.cssSelector("[class='order-tickets'] li:nth-child(2) [class='order-ticket-remove']");

    public static final By TOTAL_ORDER_PRICE = By.cssSelector("[class='order-total-header']>span>b ");

    // Cinema Hall
    public static final By BASKET_HEADER = By.cssSelector("[class='order-tickets-header']");
    public static final By ERROR_PAGE = By.cssSelector("[class='error-page']");
    public static final By URL_IMAGE = By.cssSelector("[class='order-film-image']");
    public static final String URL_ATTRIBUTE = "style";
    public static final By TICKETS_IN_BASKET = By.cssSelector("[class='order-tickets'] li");
    public static final By ROW_COUNT = By.cssSelector(".cinema-seats .cinema-row");


    public static String cinemaRowValue(int i){
        return "//div [@class='cinema-row'][" + i + "]";
    }

    public static String seatValue(int i){
        return " /div[contains(@class, 'cinema-seat') and not(contains(@class, 'hidden'))][" + i + "]";
    }

    public static String setValue(){
        return " /div[contains(@class, 'cinema-seat') and not(contains(@class, 'hidden'))]";
    }

    public static String tooltip(){
        return " /div[@class='seat-tooltip GOOD'] /div[2] //b";
    }
}
