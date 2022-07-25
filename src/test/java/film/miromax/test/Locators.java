package film.miromax.test;

import org.openqa.selenium.By;

public class Locators {
    public static class BankLocators {
        final By UKRSIBBANK_BUY_RATE = By.cssSelector("#NALUSD [class=\"rate__buy\"] p");
        final By UKRSIBBANK_SALE_RATE = By.cssSelector("#NALUSD [class=\"rate__sale\"] p");

        final By UNIVERSAL_BANK_BUY_RATE = By.xpath("//div[@class=\"cell col-xs-12 col-sm-6 col-lg-6 rate-table\"][1] //tr[2] /td[2]");
        final By UNIVERSAL_BANK_SALE_RATE = By.xpath("//div[@class=\"cell col-xs-12 col-sm-6 col-lg-6 rate-table\"][1] //tr[2] /td[3]");

        final By OSCHADBANK_BUY_RATE = By.cssSelector("[class=\"currency__item\"] [class=\"currency__item_value\"]:nth-child(2) span");
        final By OSCHADBANK_SALE_RATE = By.cssSelector("[class=\"currency__item\"] [class=\"currency__item_value\"]:nth-child(3) span");

        final By GOVUA_BANK_BUY_RATE = By.cssSelector("[class=\"collection-data-index macro-indicators\"] :nth-child(4) [class=\"col-md-3 col-sm-3 col-xs-6\"] .value");
        final By GOVUA_BANK_SALE_RATE = By.cssSelector("[class=\"collection-data-index macro-indicators\"] :nth-child(4) [class=\"col-lg-3 col-md-4 col-sm-4 col-xs-6\"] .value");
    }
}
