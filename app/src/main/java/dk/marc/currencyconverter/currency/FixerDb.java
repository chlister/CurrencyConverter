package dk.marc.currencyconverter.currency;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import dk.marc.currencyconverter.currency.model.CurrencyData;
import dk.marc.currencyconverter.currency.model.Rate;
import dk.marc.currencyconverter.data.DatabaseHelper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FixerDb implements CurrencyDAO {
    private URL apiEndpoint;
    private Gson apiJsonData;
    private OkHttpClient client;
    private Context context;
    private static final String TAG = "Fixerdb";

    public FixerDb(Context context) {
        this.context = context;
        client = new OkHttpClient();
        apiJsonData = new Gson();
    }

    @Override
    public List<Rate> getRates() {
        Log.d(TAG, "getRates() called");
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        // See if entry exists in database at this date
        // GET RATES
        List<Rate> rates = dbHelper.getRate();
        if (rates.size() > 2) {

            Log.d(TAG, "getRates: It had more then one row. Rows: " + rates.size());
//            for (Rate r :
//                    rates) {
//                System.out.println("----- My base is: " + r.getBase());
//                System.out.println("----- My Rate is: " + r.getRate());
//            }
            return rates;
        }else {
            // TODO: add new entry to db
            return getRatesUsingAPI(dbHelper);

        }
    }

    private List<Rate> getRatesUsingAPI(DatabaseHelper dbHelper) {
        try {
            CurrencyData data = retrieveDataUsingOkHttpClient(dbHelper);
            if (data != null) {
                String[] countryCodes = new String[data.getRates().keySet().size()];
                data.getRates().keySet().toArray(countryCodes);
                Float[] exchangeRates = new Float[data.getRates().values().size()];
                data.getRates().values().toArray(exchangeRates);
                List<Rate> rates = new ArrayList<>();
                for (int i = 0; i < data.getRates().size(); i++) {
                    rates.add(new Rate(countryCodes[i], exchangeRates[i]));
                    data.getRates().values();
                }
                return rates;
            } else
                return null;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private CurrencyData retrieveDataUsingOkHttpClient(DatabaseHelper dbHelper) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        Request request = new Request.Builder()
                .url("http://data.fixer.io/api/latest?access_key=511e802e903882108a974da7cf58e1cb")
                .build();
        try {
            Log.d(TAG, "retrieveDataUsingOkHttpClient: API call");
            Response response = client.newCall(request).execute();
            String resp = response.body().string();
            dbHelper.addData(resp, format.format(Calendar.getInstance().getTime()));
            return apiJsonData.fromJson(resp, CurrencyData.class);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String retrieveJson() {
        Request request = new Request.Builder()
                .url("http://data.fixer.io/api/latest?access_key=511e802e903882108a974da7cf58e1cb")
                .build();
        try {
            Response response = client.newCall(request).execute();
            String resp = response.body().string();
            System.out.println(resp);
            return resp;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }
}
