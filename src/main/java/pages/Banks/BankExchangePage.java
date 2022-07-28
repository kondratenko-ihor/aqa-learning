package pages.Banks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class BankExchangePage {
    private final WebDriver driver;
    public static final String AVERAGE_BUY_RATE_MESSAGE = "Средний курс покупки среди банков: ";
    public static final String AVERAGE_SALE_RATE_MESSAGE = "Средний курс продажи среди банков: ";
    public static final String LOW_BUY_RATE_MESSAGE = "Банк с самым низким курсом покупки доллара: ";
    public static final String HIGH_SALE_MESSAGE = "Банк с самым высоким курсом продажи доллара: ";

    public BankExchangePage(WebDriver driver) {
        this.driver = driver;
    }

    public void getBankInfo(String link, String bankName, By rateBuy, By rateSale, List<Bank> bankList) {
        driver.get(link);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(rateBuy)));
        double BuyDouble = Double.parseDouble(driver.findElement(rateBuy).getText()
                .replaceAll(",", ".")
                .replaceAll(" ", ""));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(rateSale)));
        double SaleDouble = Double.parseDouble(driver.findElement(rateSale).getText()
                .replaceAll(",", ".")
                .replaceAll(" ", ""));
        bankList.add(new Bank(bankName, BuyDouble, SaleDouble));
    }

    public void showAverageSale(List<Bank> bankList) {
        double totalSale = 0;
        double avgSale;
        for (Bank bank : bankList) totalSale += bank.getSALE_RATE();
        avgSale = totalSale / bankList.size();
        System.out.println(AVERAGE_SALE_RATE_MESSAGE + avgSale);

    }

    public void showAverageBuy(List<Bank> bankList) {
        double totalBuy = 0;
        double avgBuy;
        for (Bank bank : bankList) totalBuy += bank.getBUY_RATE();
        avgBuy = totalBuy / bankList.size();
        System.out.println(AVERAGE_BUY_RATE_MESSAGE + avgBuy);
    }

    public void showMinBuyRate(List<Bank> bankList) {
        Bank minBuyRateBank = bankList.stream()
                .min(Comparator.comparing(Bank::getBUY_RATE))
                .orElseThrow(NoSuchElementException::new);
        System.out.println(LOW_BUY_RATE_MESSAGE + minBuyRateBank.getNAME());
    }

    public void showMaxSaleRate(List<Bank> bankList) {
        Bank maxSaleRateBank = bankList.stream()
                .max(Comparator.comparing(Bank::getSALE_RATE))
                .orElseThrow(NoSuchElementException::new);
        System.out.println(HIGH_SALE_MESSAGE + maxSaleRateBank.getNAME());
    }
}


