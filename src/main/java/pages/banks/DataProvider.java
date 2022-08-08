package pages.banks;

public class DataProvider {

    @org.testng.annotations.DataProvider(parallel = true)
    public static Object[][] bankData() {
        return new Object[][]{
                {BankExchangePage.UKRSIBBANK_URL, BankExchangePage.UKRISBBANK_NAME, BankExchangePage.UKRSIBBANK_BUY_RATE, BankExchangePage.UKRSIBBANK_SALE_RATE},
                {BankExchangePage.OSCHADBANK_URL, BankExchangePage.OSCHADBANK_NAME, BankExchangePage.OSCHADBANK_BUY_RATE, BankExchangePage.OSCHADBANK_SALE_RATE},
                {BankExchangePage.GOVUA_URL, BankExchangePage.GOVUA_BANK_NAME, BankExchangePage.GOVUA_BANK_BUY_RATE, BankExchangePage.GOVUA_BANK_SALE_RATE},
        };
    }

}

