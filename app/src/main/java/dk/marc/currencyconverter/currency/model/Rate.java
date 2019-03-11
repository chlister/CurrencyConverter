package dk.marc.currencyconverter.currency.model;

public class Rate {
    private String base;
    private float rate;

    public String getBase() {
        return base;
    }

    public float getRate() {
        return rate;
    }

    public Rate(String base, float rate) {
        this.base = base;
        this.rate = rate;
    }
}
