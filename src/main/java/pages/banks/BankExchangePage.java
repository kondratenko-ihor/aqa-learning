package pages.banks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import pages.basePage.BasePage;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class BankExchangePage extends BasePage {
    public static final String UKRISBBANK_NAME = "Укрсиббанк";
    public static final String UKRSIBBANK_URL = "https://my.ukrsibbank.com/ua/personal";
    public static final By UKRSIBBANK_BUY_RATE = By.cssSelector("#NALUSD [class='rate__buy'] p");
    public static final By UKRSIBBANK_SALE_RATE = By.cssSelector("#NALUSD [class='rate__sale'] p");

    public static final String OSCHADBANK_NAME = "Ощадбанк";
    public static final String OSCHADBANK_URL = "https://www.oschadbank.ua";
    public static final By OSCHADBANK_BUY_RATE = By.cssSelector("[class='currency__item'] [class='currency__item_value']:nth-child(2) span");
    public static final By OSCHADBANK_SALE_RATE = By.cssSelector("[class='currency__item'] [class='currency__item_value']:nth-child(3) span");

    public static final String GOVUA_BANK_NAME = "Нацбанк";
    public static final String GOVUA_URL = "https://bank.gov.ua/ua/markets/currency-market";
    public static final By GOVUA_BANK_BUY_RATE = By.cssSelector("[class='collection-data-index macro-indicators'] :nth-child(4) [class='col-md-3 col-sm-3 col-xs-6'] .value");
    public static final By GOVUA_BANK_SALE_RATE = By.cssSelector("[class='collection-data-index macro-indicators'] :nth-child(4) [class='col-lg-3 col-md-4 col-sm-4 col-xs-6'] .value");


    public BankExchangePage(WebDriver driver) {
        super(driver);
    }

    public double parseToDouble(By rate) {
        waitForElementVisible(rate);
        return Double.parseDouble(driver.findElement(rate).getText()
                .replaceAll(",", ".")
                .replaceAll(" ", ""));

    }

    public Bank getBank(String bankName, By rateBuy, By rateSale) {
        return new Bank(bankName, parseToDouble(rateSale), parseToDouble(rateBuy));
    }

    @DataProvider()
    public static Object[][] bankData() {
        return new Object[][]{
                {UKRSIBBANK_URL, UKRISBBANK_NAME, UKRSIBBANK_BUY_RATE, UKRSIBBANK_SALE_RATE},
                {OSCHADBANK_URL, OSCHADBANK_NAME, OSCHADBANK_BUY_RATE, OSCHADBANK_SALE_RATE},
                {GOVUA_URL, GOVUA_BANK_NAME, GOVUA_BANK_BUY_RATE, GOVUA_BANK_SALE_RATE},
        };
    }

    public void showAverageSale(List<Bank> bankList) {
        double totalSale = 0;
        double avgSale;
        for (Bank bank : bankList) totalSale += bank.getSALE_RATE();
        avgSale = totalSale / bankList.size();
        System.out.println(TestData.AVERAGE_SALE_RATE_MESSAGE + avgSale);
    }

    public void showAverageBuy(List<Bank> bankList) {
        double totalBuy = 0;
        double avgBuy;
        for (Bank bank : bankList) totalBuy += bank.getBUY_RATE();
        avgBuy = totalBuy / bankList.size();
        System.out.println(TestData.AVERAGE_BUY_RATE_MESSAGE + avgBuy);
    }

    public void showMinBuyRate(List<Bank> bankList) {
        Bank minBuyRateBank = bankList.stream()
                .min(Comparator.comparing(Bank::getBUY_RATE))
                .orElseThrow(NoSuchElementException::new);
        System.out.println(TestData.LOW_BUY_RATE_MESSAGE + minBuyRateBank.getNAME());
    }

    public void showMaxSaleRate(List<Bank> bankList) {
        Bank maxSaleRateBank = bankList.stream()
                .max(Comparator.comparing(Bank::getSALE_RATE))
                .orElseThrow(NoSuchElementException::new);
        System.out.println(TestData.HIGH_SALE_MESSAGE + maxSaleRateBank.getNAME());
    }
}
