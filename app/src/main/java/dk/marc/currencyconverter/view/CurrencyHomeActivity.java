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

import dk.marc.currencyconverter.R;
import dk.marc.currencyconverter.currency.FixerMock;
import dk.marc.currencyconverter.customElements.RecyclerAdapter;
import dk.marc.currencyconverter.presenter.CurrencyHomePresenter;
import dk.marc.currencyconverter.customElements.IconNameSpinner;

public class CurrencyHomeActivity extends AppCompatActivity {

    private Spinner countrySpinner;
    private TextView numberInput;
    private String[] countryNames;
    private String[] countryCodes;
    private int[] countryFlags;
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private CurrencyHomePresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_home);
        presenter = new CurrencyHomePresenter(new FixerMock());
        countryNames = getResources().getStringArray(R.array.country_names);
        countryFlags = getResources().getIntArray(R.array.country_icons);
        countryCodes = getResources().getStringArray(R.array.country_code);
        numberInput = findViewById(R.id.textInputValue);

        // Create recycleview
        recyclerView = findViewById(R.id.convertedCurrencyRecycler);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter = new RecyclerAdapter(Arrays.asList(countryCodes), countryFlags);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(recyclerAdapter);

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
        System.out.println("btnConverter --");
        float inputValue = 0;
        String base = countryCodes[countrySpinner.getSelectedItemPosition()];
        System.out.println(base);

        if (!numberInput.getText().toString().equals(""))
            inputValue = Integer.parseInt(numberInput.getText().toString());

        System.out.println(inputValue);
        presenter.getCurrencyExchange(inputValue, base);
    }
}
