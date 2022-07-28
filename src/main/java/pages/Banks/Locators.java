package pages.Banks;

import org.openqa.selenium.By;

public class Locators {
    public static final String UKRISBBANK_NAME = "Укрсиббанк";
    public static final String UKRSIBBANK_URL = "https://my.ukrsibbank.com/ua/personal";
    public static final By UKRSIBBANK_BUY_RATE = By.cssSelector("#NALUSD [class='rate__buy'] p");
    public static final By UKRSIBBANK_SALE_RATE = By.cssSelector("#NALUSD [class='rate__sale'] p");

    public static final String UNIVERSAL_NAME = "ЮниверсалБанк";
    public static final String UNIVERSAL_URL = "https://www.universalbank.com.ua";
    public static final By UNIVERSAL_BANK_BUY_RATE = By.xpath("//div[@class='cell col-xs-12 col-sm-6 col-lg-6 rate-table'][1] //tr[2] /td[2]");
    public static final By UNIVERSAL_BANK_SALE_RATE = By.xpath("//div[@class='cell col-xs-12 col-sm-6 col-lg-6 rate-table'][1] //tr[2] /td[3]");

    public static final String OSCHADBANK_NAME = "Ощадбанк";
    public static final String OSCHADBANK_URL = "https://www.oschadbank.ua";
    public static final By OSCHADBANK_BUY_RATE = By.cssSelector("[class='currency__item'] [class='currency__item_value']:nth-child(2) span");
    public static final By OSCHADBANK_SALE_RATE = By.cssSelector("[class='currency__item'] [class='currency__item_value']:nth-child(3) span");

    public static final String GOVUA_BANK_NAME = "Нацбанк";
    public static final String GOVUA_URL = "https://bank.gov.ua/ua/markets/currency-market";
    public static final By GOVUA_BANK_BUY_RATE = By.cssSelector("[class='collection-data-index macro-indicators'] :nth-child(4) [class='col-md-3 col-sm-3 col-xs-6'] .value");
    public static final By GOVUA_BANK_SALE_RATE = By.cssSelector("[class='collection-data-index macro-indicators'] :nth-child(4) [class='col-lg-3 col-md-4 col-sm-4 col-xs-6'] .value");

}
