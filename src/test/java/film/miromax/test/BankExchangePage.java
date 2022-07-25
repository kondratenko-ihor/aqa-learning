package film.miromax.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class BankExchangePage {
    private final WebDriver driver;
    Locators.BankLocators locator = new Locators.BankLocators();
    List<Bank> bankList = new ArrayList<>();

    public BankExchangePage(WebDriver driver) {
        this.driver = driver;
    }


    static class Bank {
        private final String NAME;
        private final double SALE_RATE;
        private final double BUY_RATE;

        public Bank(String name, double saleRate, double buyRate) {
            this.NAME = name;
            this.SALE_RATE = saleRate;
            this.BUY_RATE = buyRate;
        }


        public String getNAME() {
            return NAME;
        }

        public double getSALE_RATE() {
            return SALE_RATE;
        }

        public double getBUY_RATE() {
            return BUY_RATE;
        }
    }


    public void getBankInfo(String link, String bankName, By rateBuy, By rateSale) {
        driver.get(link);
        double rateBuyDouble = Double.parseDouble(driver.findElement(rateBuy).getText());
        double rateSaleDouble = Double.parseDouble(driver.findElement(rateSale).getText());
        bankList.add(new Bank(bankName, rateBuyDouble, rateSaleDouble));
    }


    public void SaveUkrsibbankInfo() {

        driver.get("https://my.ukrsibbank.com/ua/personal");
        String name = "Укрсиббанк";
        String rateBuyUkrsibBan = driver.findElement(locator.UKRSIBBANK_BUY_RATE).getText();
        double buyRate = Double.parseDouble(rateBuyUkrsibBan);
        String rateSaleUkrsibBank = driver.findElement(locator.UKRSIBBANK_SALE_RATE).getText();
        double saleRate = Double.parseDouble(rateSaleUkrsibBank);
        bankList.add(new Bank(name, saleRate, buyRate));
    }

    public void SaveUniversalBankInfo() {
        driver.get("https://www.universalbank.com.ua/");
        String name = "ЮниверсалБанк";
        String rateBuyUniversalBank = driver.findElement(locator.UNIVERSAL_BANK_BUY_RATE).getText();
        double buyRate = Double.parseDouble(rateBuyUniversalBank);
        String rateSaleUniversalBank = driver.findElement(locator.UNIVERSAL_BANK_SALE_RATE).getText();
        double saleRate = Double.parseDouble(rateSaleUniversalBank);
        bankList.add(new Bank(name, saleRate, buyRate));

    }
    public void SaveOschadBankInfo() {
        driver.get("https://www.oschadbank.ua");
        String name = "Ощадбанк";
        String BuyOschadbank = driver.findElement(locator.OSCHADBANK_BUY_RATE).getText();
        double buyRate = Double.parseDouble(BuyOschadbank);
        String SaleOschadbank = driver.findElement(locator.OSCHADBANK_SALE_RATE).getText();
        double saleRate = Double.parseDouble(SaleOschadbank);
        bankList.add(new Bank(name, saleRate, buyRate));
    }

    public void SaveGovUaInfo() {
        driver.get("https://bank.gov.ua/ua/markets/currency-market");
        String name = "Нацбанк";
        String BuyBankGovUa = driver.findElement(locator.GOVUA_BANK_BUY_RATE)
                .getText()
                .replaceAll(",", ".")
                .replaceAll(" ", "");

        String SaleBankGovUa = driver.findElement(locator.GOVUA_BANK_SALE_RATE)
                .getText()
                .replaceAll(",", ".")
                .replaceAll(" ", "");
        double buyRate = Double.parseDouble(BuyBankGovUa);
        double saleRate = Double.parseDouble(SaleBankGovUa);
        bankList.add(new Bank(name, saleRate, buyRate));
    }

    public void ShowAverageSale() {
        double totalSale = 0;
        double avgSale;
        for (BankExchangePage.Bank bank : bankList) totalSale += bank.getSALE_RATE();
        avgSale = totalSale / bankList.size();
        System.out.println("Средний курс продажи среди банков: " + avgSale);

    }

    public void ShowAverageBuy() {
        double totalBuy = 0;
        double avgBuy;
        for (BankExchangePage.Bank bank : bankList) totalBuy += bank.getBUY_RATE();
        avgBuy = totalBuy / bankList.size();
        System.out.println("Средний курс покупки среди банков: " + avgBuy);
    }

    public void ShowMinBuyRate() {
        BankExchangePage.Bank minBuyRateBank = bankList.stream()
                .min(Comparator.comparing(BankExchangePage.Bank::getBUY_RATE))
                .orElseThrow(NoSuchElementException::new);
        System.out.println("Банк с самым низким курсом покупки доллара: " + minBuyRateBank.getNAME());
    }

    public void ShowMaxSaleRate() {
        BankExchangePage.Bank maxSaleRateBank = bankList.stream()
                .max(Comparator.comparing(BankExchangePage.Bank::getSALE_RATE))
                .orElseThrow(NoSuchElementException::new);
        System.out.println("Банк с самым высоким курсом продажи доллара: " + maxSaleRateBank.getNAME());
    }


}
