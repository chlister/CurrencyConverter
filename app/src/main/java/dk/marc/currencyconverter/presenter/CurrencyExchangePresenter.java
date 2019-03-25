package dk.marc.currencyconverter.presenter;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import java.util.List;

import dk.marc.currencyconverter.currency.CurrencyDAO;
import dk.marc.currencyconverter.currency.FixerAPI;
import dk.marc.currencyconverter.currency.FixerMock;
import dk.marc.currencyconverter.currency.model.Rate;
import dk.marc.currencyconverter.utility.CountryCodeFiller;
import dk.marc.currencyconverter.utility.CurrencyConverter;

public class CurrencyExchangePresenter {

    private View view;
    private String[] countryCodes;
    private static List<Rate> rates;
    private CurrencyDAO dao;

    public String[] getCountryCodes() {
        return countryCodes;
    }

    public CurrencyExchangePresenter(View view) {
        this.view = view;
        countryCodes = CountryCodeFiller.getCountryCodes(); // TODO - Get codes from API
        @SuppressLint("StaticFieldLeak") AsyncTask task = new AsyncTask() {
            @Override
            protected List<Rate> doInBackground(Object[] objects) {
                dao = new FixerAPI();
                return dao.getRates();
            }

            @Override
            protected void onPostExecute(Object result) {
                rates = ((List<Rate>) result);
            }
        }.execute();
    }

    public synchronized void initData() {

    }

    public void calculateExchange(float valueToExchange, int spinnerFromPosition, int spinnerToPosition) {
        if (rates != null) {
            String countryCodeFrom = countryCodes[spinnerFromPosition];
            String countryCodeTo = countryCodes[spinnerToPosition];

            Rate baseRate = getRateUsingCountryCode(countryCodeFrom);
            Rate targetRate = getRateUsingCountryCode(countryCodeTo);

            try {
                view.onUpdatedValue(CurrencyConverter.ConvertFromBaseToTarget(
                        baseRate.getRate(),
                        targetRate.getRate(),
                        valueToExchange));

            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }

    }

    private Rate getRateUsingCountryCode(String countryCode) {
        for (Rate r : rates) {
            if (r.getBase().equals(countryCode))
                return r;
        }
        return null;
    }

    public interface View {
        void onUpdatedValue(float value);
    }
}
