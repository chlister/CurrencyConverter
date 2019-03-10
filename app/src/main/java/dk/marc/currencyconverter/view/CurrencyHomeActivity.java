package dk.marc.currencyconverter.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.CursorIndexOutOfBoundsException;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.DrawableWrapper;
import android.graphics.drawable.VectorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;

import dk.marc.currencyconverter.R;
import dk.marc.currencyconverter.presenter.CurrencyHomePresenter;
import dk.marc.currencyconverter.utility.IconNameSpinner;

public class CurrencyHomeActivity extends AppCompatActivity {

    private Spinner countrySpinner;
    private TextView numberInput;
    private Button btnConvertCurrency;
    private String[] countryNames;
    private int[] countryFlags;
    private CurrencyHomePresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_home);
        presenter = new CurrencyHomePresenter();
        countryNames = getResources().getStringArray(R.array.country_names);
        countryFlags = getResources().getIntArray(R.array.country_icons); // TODO: make work
//        countryFlags = new int[]{R.drawable.ic_dkk, R.drawable.ic_eur};

        // Find the spinner on the page
        countrySpinner = findViewById(R.id.countrySpinner);
        IconNameSpinner spinAdapter = new IconNameSpinner(this, countryNames, countryFlags);
        countrySpinner.setAdapter(spinAdapter);

        countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), countryNames[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    public void btnConvertCurrency(View view) {
        presenter.getCurrencyExchange();
    }
}
