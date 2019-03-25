package dk.marc.currencyconverter.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import dk.marc.currencyconverter.R;
import dk.marc.currencyconverter.currency.FixerMock;
import dk.marc.currencyconverter.currency.model.Rate;
import dk.marc.currencyconverter.customElements.RecyclerAdapter;
import dk.marc.currencyconverter.presenter.CurrencyHomePresenter;
import dk.marc.currencyconverter.customElements.IconNameSpinner;

public class CurrencyHomeActivity extends AppCompatActivity implements CurrencyHomePresenter.View {

    private Spinner countrySpinner;
    private TextView numberInput;
    private String[] countryNames; // Data
    private String[] countryCodes; // Data
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private CurrencyHomePresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_home);
        presenter = new CurrencyHomePresenter(this);
        countryNames = getResources().getStringArray(R.array.country_names); // TODO moved to presenter
        countryCodes = getResources().getStringArray(R.array.country_code); // TODO moved to presenter
        numberInput = findViewById(R.id.textInputValue);
        System.out.println("All done ---------------- Starting create recycler view");
        // Create RecyclerView
        recyclerView = findViewById(R.id.convertedCurrencyRecycler);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter = new RecyclerAdapter(presenter.getRates());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(recyclerAdapter);
        System.out.println("All done ---------------- Starting create spinner view");

        // Find the spinner on the page
        countrySpinner = findViewById(R.id.countrySpinner);
        IconNameSpinner spinAdapter = new IconNameSpinner(this, countryNames); // TODO countryNames moved to presenter
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
        System.out.println("btnConverter --");
        float inputValue = 0;
        String base = countryCodes[countrySpinner.getSelectedItemPosition()];
        System.out.println(base);

        if (!numberInput.getText().toString().equals(""))
            inputValue = Integer.parseInt(numberInput.getText().toString());

        System.out.println(inputValue);
        presenter.getCurrencyExchange(inputValue, base);
    }

    @Override
    public void onRatesUpdated() {
        recyclerAdapter.notifyDataSetChanged();
    }
}
