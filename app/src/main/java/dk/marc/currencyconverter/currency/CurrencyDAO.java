package dk.marc.currencyconverter.currency;

import java.util.List;

public interface CurrencyDAO {
    public List<Rate> getRates(String base);

}
