package dk.marc.currencyconverter.currency;

import java.util.List;

import dk.marc.currencyconverter.currency.model.Rate;

public interface CurrencyDAO {
    public List<Rate> getRates();
}
