package dk.marc.currencyconverter.currency;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class CurrencyData {
    // Are all currencies three character? (Maybe a char[3] would be enough
    private boolean success;
    private String base = "";
    private Date date = new Date();
    private Map<String, Float> rates = new LinkedHashMap<String, Float>();

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

    public void setRates(LinkedHashMap<String, Float> rates) {
        this.rates = rates;
    }
    //endregion

    // Primary use to build JSON
    public CurrencyData() {

    }

    public CurrencyData(String base, Date date, LinkedHashMap<String, Float> rates) {
        this.base = base;
        this.date = date;
        this.rates = rates;
    }

}
