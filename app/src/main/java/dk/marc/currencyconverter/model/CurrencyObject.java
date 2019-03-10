package dk.marc.currencyconverter.model;

import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class CurrencyObject {
    // Are all currencies three character? (Maybe a char[3] would be enough
    private boolean success;
    private String base = "";
    private Date date = new Date();
    private Map<String, Float> rates = new HashMap<String, Float>();

    //region Getters & Setters

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Map<String, Float> getRates() {
        return rates;
    }

    public void setRates(Map<String, Float> rates) {
        this.rates = rates;
    }
    //endregion

    // Primary use to build JSON
    public CurrencyObject() {

    }

    public CurrencyObject(String base, Date date, Map<String, Float> rates) {
        this.base = base;
        this.date = date;
        this.rates = rates;
    }

    public float calculateExchange(String base, String target, float value){
        float baseRate = rates.get(base);
        float targetRate = rates.get(target);

        // TODO: find the exchange rate using EUR
        float exchangeRate = baseRate / targetRate;
        // TODO: Calculate the return value
        float returnValue = exchangeRate * value;

        return returnValue;
    }
}
