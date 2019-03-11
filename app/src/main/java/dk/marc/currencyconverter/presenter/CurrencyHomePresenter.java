package dk.marc.currencyconverter.presenter;

import android.arch.lifecycle.ViewModelStore;
import android.arch.lifecycle.ViewModelStoreOwner;
import android.view.View;

import dk.marc.currencyconverter.R;
import dk.marc.currencyconverter.model.CurrencyMockObject;
import dk.marc.currencyconverter.model.CurrencyObject;

public class CurrencyHomePresenter {
    private CurrencyObject currencyObject;
    public CurrencyHomePresenter() {

    }

    public void getCurrencyExchange(float valueToExchange, String baseCurrency) {
        CurrencyMockObject mockObject = new CurrencyMockObject();
        currencyObject = mockObject.getMockCurrencyObject();
        for (int i = 0; i < currencyObject.getRates().size(); i++) {
            // TODO:
            System.out.println(currencyObject.calculateExchange(baseCurrency, currencyObject.getRates()., valueToExchange));
        }
        System.out.println(currencyObject.getBase());
        System.out.println(currencyObject.getDate());
//        System.out.println(currencyObject.getRates());
    }

    // TODO: method for calling API
    // TODO: method for building the currency object
    // API standard endpoint http://data.fixer.io/api/
    // http://data.fixer.io/api/latest?access_key=511e802e903882108a974da7cf58e1cb


}
