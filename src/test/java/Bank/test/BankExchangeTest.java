package Bank.test;

import pages.Banks.Bank;
import pages.Banks.BankExchangePage;
import pages.Banks.Locators;
import WebDriverConfig.WebDriverConfig;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


public class BankExchangeTest extends WebDriverConfig {


    List<Bank> bankList = new ArrayList<>();

    @DataProvider(name = "BankData")
    public static Object[][] bankData() {
        return new Object[][]{
                {Locators.UKRSIBBANK_URL, Locators.UKRISBBANK_NAME, Locators.UKRSIBBANK_BUY_RATE, Locators.UKRSIBBANK_SALE_RATE},
                {Locators.OSCHADBANK_URL, Locators.OSCHADBANK_NAME, Locators.OSCHADBANK_BUY_RATE, Locators.OSCHADBANK_SALE_RATE},
                {Locators.GOVUA_URL, Locators.GOVUA_BANK_NAME, Locators.GOVUA_BANK_BUY_RATE, Locators.GOVUA_BANK_SALE_RATE},
        };
    }

    @Test(dataProvider = "BankData")
    public void getBanksInfo(String link, String bankName, By buyRate, By saleRate) {
        driver.get(link);
        new BankExchangePage(driver).getBankInfo(link, bankName, buyRate, saleRate, bankList);
    }

    @Test(dependsOnMethods = "getBanksInfo")
    public void showInfo() {
        BankExchangePage page = new BankExchangePage(driver);
        page.showAverageSale(bankList);
        page.showAverageBuy(bankList);
        page.showMaxSaleRate(bankList);
        page.showMinBuyRate(bankList);
    }
}


