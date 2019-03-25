package dk.marc.currencyconverter.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import dk.marc.currencyconverter.R;
import dk.marc.currencyconverter.customElements.IconNameSpinner;
import dk.marc.currencyconverter.presenter.CurrencyExchangePresenter;

public class CurrencyExchange extends AppCompatActivity implements CurrencyExchangePresenter.View {

    private Spinner currencyFromSpinner;
    private Spinner currencyToSpinner;
    private TextView outPutValue;
    private EditText inputValue;
    private CurrencyExchangePresenter presenter;
    private Button btnCurrencyExchange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_exhcange);
        // Init presenter
        presenter = new CurrencyExchangePresenter(this);
        outPutValue = findViewById(R.id.exchange_textView);
        inputValue = findViewById(R.id.exchange_editText);
        btnCurrencyExchange = findViewById(R.id.btn_convert_currency);
        // Create spinners
        currencyFromSpinner = findViewById(R.id.currencyFromSpinner);
        IconNameSpinner fromAdapter = new IconNameSpinner(this, presenter.getCountryCodes());
        currencyFromSpinner.setAdapter(fromAdapter);

        currencyToSpinner = findViewById(R.id.currencyToSpinner);
        IconNameSpinner toAdapter = new IconNameSpinner(this, presenter.getCountryCodes());
        currencyToSpinner.setAdapter(toAdapter);

        btnCurrencyExchange = findViewById(R.id.btn_convert_currency);
    }

    public void exchangeCurrency(View view) {
        float valueToExchange = Float.valueOf(inputValue.getText().toString());
        presenter.calculateExchange(valueToExchange,
                currencyFromSpinner.getSelectedItemPosition(),
                currencyToSpinner.getSelectedItemPosition());
    }

    @Override
    public void onUpdatedValue(float value) {
        outPutValue.setText(String.valueOf(value));
    }
}
