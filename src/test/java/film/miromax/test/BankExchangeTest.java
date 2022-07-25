package film.miromax.test;

import film.miromax.WebDriverConfig;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class BankExchangeTest extends WebDriverConfig {
    Locators.BankLocators locator = new Locators.BankLocators();

    @Test
    public void TestCurrencyExchange() {
        BankExchangePage page = new BankExchangePage(driver);
        page.SaveUkrsibbankInfo();
        page.SaveGovUaInfo();
        page.SaveGovUaInfo();
        page.SaveUniversalBankInfo();
        page.SaveOschadBankInfo();
        page.ShowAverageSale();
        page.ShowAverageBuy();
        page.ShowMaxSaleRate();
        page.ShowMinBuyRate();

    }
}



//
//    @DataProvider(name = "bank")
//    public Object[][] urlsMethod() {
//        return new Object[][]{{"https://my.ukrsibbank.com/ua/personal", "Укрсиббанк", locator.UKRSIBBANK_BUY_RATE, locator.UKRSIBBANK_SALE_RATE},
//                {"https://www.universalbank.com.ua/", "ЮниверсалБанк", locator.UNIVERSAL_BANK_BUY_RATE, locator.UNIVERSAL_BANK_SALE_RATE},
//                {"https://www.oschadbank.ua", "Ощадбанк", locator.OSCHADBANK_BUY_RATE, locator.OSCHADBANK_SALE_RATE}
//        };
//    }
//
//    @Test(dataProvider = "bank")
//    public void test(String link, String bankName, By buyRate, By saleRate){
//        BankExchangePage page = new BankExchangePage(driver);
//        driver.get(link);
//        page.getBankInfo(link, bankName, buyRate, saleRate);
//        page.ShowMinBuyRate();
//        page.ShowMaxSaleRate();
//        page.ShowAverageBuy();
//        page.ShowAverageSale();
//    }
//}
