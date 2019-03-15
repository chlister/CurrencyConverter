package dk.marc.currencyconverter.currency.model;

public class Rate {
    private String base;
    private float rate;
    private float valueExchanged;

    public float getValueExchanged() {
        return valueExchanged;
    }

    public void setValueExchanged(float valueExchanged) {
        this.valueExchanged = valueExchanged;
    }

    public String getBase() {
        return base;
    }

    public float getRate() {
        return rate;
    }

    public Rate(String base, float rate) {
        this.base = base;
        this.rate = rate;
        valueExchanged = 0;
    }
}
