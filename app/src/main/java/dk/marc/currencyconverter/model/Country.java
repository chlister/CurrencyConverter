package dk.marc.currencyconverter.model;

import android.content.Context;
import android.graphics.drawable.Drawable;

import dk.marc.currencyconverter.utility.DrawableManager;

public class Country {
    Drawable flagIcon;
    String countryCode, countryName;
    float exchangeValue;

    public Country(String countryCode) {
        this.countryCode = countryCode;
    }

    public void setFlagIcon(Context context) {
        DrawableManager.getDrawable("ic_" + countryCode, context);
    }

}
