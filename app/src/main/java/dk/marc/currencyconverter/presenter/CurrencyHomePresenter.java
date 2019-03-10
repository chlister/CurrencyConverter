package dk.marc.currencyconverter.presenter;

import dk.marc.currencyconverter.model.CurrencyMockObject;
import dk.marc.currencyconverter.model.CurrencyObject;

public class CurrencyHomePresenter {
    private CurrencyObject currencyObject;
    public CurrencyHomePresenter() {

    }

    public void getCurrencyExchange() {
        CurrencyMockObject mockObject = new CurrencyMockObject();
        currencyObject = mockObject.getMockCurrencyObject();
        System.out.println(currencyObject.getBase());
        System.out.println(currencyObject.getDate());
//        System.out.println(currencyObject.getRates());
    }

    // TODO: method for calling API
    // TODO: method for building the currency object
    // API standard endpoint http://data.fixer.io/api/
    // http://data.fixer.io/api/latest?access_key=511e802e903882108a974da7cf58e1cb


}
