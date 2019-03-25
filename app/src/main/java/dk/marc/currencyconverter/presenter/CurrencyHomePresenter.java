package dk.marc.currencyconverter.presenter;

import java.util.ArrayList;
import java.util.List;

import dk.marc.currencyconverter.currency.CurrencyDAO;
import dk.marc.currencyconverter.currency.FixerMock;
import dk.marc.currencyconverter.currency.model.Rate;
import dk.marc.currencyconverter.utility.CurrencyConverter;

public class CurrencyHomePresenter {
    private View view;
    private String[] countryCodes;
    private List<Rate> rates;
    private CurrencyDAO dao;

    public List<Rate> getRates() {
        System.out.println("--------------------------------------------------------------");
        if (rates != null)
            return rates;
        else return dao.getRates();
    }

    private List<Rate> populateRates(){
        List<Rate> r = new ArrayList<>();
        // TODO: Find where country codes match

        return null;
    }

    public CurrencyHomePresenter(View view) {
        this.dao = new FixerMock();
        this.view = view;
        this.countryCodes = countryCodes;
    }


    public void getCurrencyExchange(float valueToExchange, String baseCurrency) {
        if (rates == null)
            rates = dao.getRates();
        for (Rate r : rates
        ) {
            System.out.println(r.getBase() + " : " + r.getRate());
            r.setValueExchanged(CurrencyConverter.ConvertFromBaseToTarget(
                    r.getRate(),
                    findRate(baseCurrency).getRate(),
                    valueToExchange));
        }

        onRatesUpdated();
    }

    private Rate findRate(String countryCode) {
        for (Rate rate : rates) {
            if (rate.getBase().equals(countryCode))
                return rate;
        }
        return null;
    }


    public void onRatesUpdated() {
        view.onRatesUpdated();
    }

    public interface View {

        void onRatesUpdated();
    }

    // TODO: method for calling API
    // TODO: method for building the currency object
    // API standard endpoint http://data.fixer.io/api/
    // http://data.fixer.io/api/latest?access_key=511e802e903882108a974da7cf58e1cb


}
