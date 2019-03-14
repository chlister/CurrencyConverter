package dk.marc.currencyconverter.presenter;

import java.util.List;

import dk.marc.currencyconverter.currency.CurrencyDAO;
import dk.marc.currencyconverter.currency.model.Rate;
import dk.marc.currencyconverter.utility.CurrencyConverter;

public class CurrencyHomePresenter {

    private List<Rate> rates;
    private CurrencyDAO dao;

    public CurrencyHomePresenter(CurrencyDAO dao) {
        this.dao = dao;
    }

    public void getCurrencyExchange(float valueToExchange, String baseCurrency) {
        if (rates == null)
            rates = dao.getRates();
        for (Rate r : rates
        ) {
            System.out.println(r.getBase() + " : " + r.getRate());
        }
        System.out.println("Value converted: ");
        System.out.println("Value before: " + valueToExchange);
        System.out.println("Value after: " + CurrencyConverter.ConvertFromBaseToTarget(findRate(baseCurrency).getRate(), findRate("EUR").getRate(),valueToExchange) );

    }

    private Rate findRate(String countryCode){
        for (Rate rate : rates){
            if (rate.getBase().equals(countryCode))
                return rate;
        }
        return null;
    }

    // TODO: method for calling API
    // TODO: method for building the currency object
    // API standard endpoint http://data.fixer.io/api/
    // http://data.fixer.io/api/latest?access_key=511e802e903882108a974da7cf58e1cb


}
