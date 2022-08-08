import WebDriverConfig.WebDriverConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.prefs.BackingStoreException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.banks.Bank;
import pages.banks.BankExchangePage;


public class testing {
//    WebDriver driver;
//
//    List<Bank> bankList = new ArrayList<>();
//
//    public void openLink(String link){
//        WebDriverManager.chromedriver().setup();
//        WebDriver driver = new ChromeDriver();
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.get(link);
//    }
//
//    public double parseToDouble(By rate) {
//        System.out.println(driver.findElement(rate).getText());
//        return Double.parseDouble(driver.findElement(rate).getText()
//                .replaceAll(",", ".")
//                .replaceAll(" ", ""));
//
//    }

    @Test(dataProvider = "loginData")
    public void testBanks(String link, String bankName, By buyRate, By saleRate) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(link);
//        openLink(link);
        double rateBuy = Double.parseDouble(driver.findElement(buyRate).getText()
                        .replaceAll(",", ".")
                        .replaceAll(" ", ""));

        double rateSale = Double.parseDouble(driver.findElement(saleRate).getText()
                .replaceAll(",", ".")
                .replaceAll(" ", ""));


//        bankList.add(new Bank(bankName, rateSale, rateBuy));
        driver.quit();
    }

//    @Test(dependsOnMethods = "testBanks")
//    public void showInfo() {
//        BankExchangePage bankExchangePage = new BankExchangePage(driver);
//        bankExchangePage.showAverageSale(bankList);
//        bankExchangePage.showAverageBuy(bankList);
//        bankExchangePage.showMaxSaleRate(bankList);
//        bankExchangePage.showMinBuyRate(bankList);
//    }

    @DataProvider(parallel = true)
    public Object[][] loginData() {
        return new Object[][]{
                {BankExchangePage.UKRSIBBANK_URL, BankExchangePage.UKRISBBANK_NAME, BankExchangePage.UKRSIBBANK_BUY_RATE, BankExchangePage.UKRSIBBANK_SALE_RATE},
                {BankExchangePage.OSCHADBANK_URL, BankExchangePage.OSCHADBANK_NAME, BankExchangePage.OSCHADBANK_BUY_RATE, BankExchangePage.OSCHADBANK_SALE_RATE},
                {BankExchangePage.GOVUA_URL, BankExchangePage.GOVUA_BANK_NAME, BankExchangePage.GOVUA_BANK_BUY_RATE, BankExchangePage.GOVUA_BANK_SALE_RATE},
        };
    }

}
