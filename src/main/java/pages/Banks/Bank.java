package pages.Banks;

public class Bank {
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
