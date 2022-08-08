package banks.test;

import WebDriverConfig.WebDriverConfig;
import org.testng.annotations.BeforeTest;
import pages.banks.Bank;
import pages.banks.BankExchangePage;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;

public class BankExchangeTest extends WebDriverConfig {

    BankExchangePage bankExchangePage;
    List<Bank> bankList = new ArrayList<>();

    @BeforeTest
    public void initPage(){
        bankExchangePage = new BankExchangePage(driver);
    }


    @Test(dataProviderClass = BankExchangePage.class, dataProvider = "bankData")
    public void getBanksInfo(String link, String bankName, By buyRate, By saleRate) {
        driver.get(link);

        bankList.add(bankExchangePage.getBank(bankName, buyRate, saleRate));
    }


    @Test(dependsOnMethods = "getBanksInfo", alwaysRun = true)
    public void showInfo() {
        bankExchangePage.showAverageSale(bankList);
        bankExchangePage.showAverageBuy(bankList);
        bankExchangePage.showMaxSaleRate(bankList);
        bankExchangePage.showMinBuyRate(bankList);
    }
}