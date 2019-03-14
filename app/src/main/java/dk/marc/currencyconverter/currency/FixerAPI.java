package dk.marc.currencyconverter.currency;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import dk.marc.currencyconverter.currency.model.CurrencyData;
import dk.marc.currencyconverter.currency.model.Rate;

public class FixerAPI implements CurrencyDAO {
    private URL apiEndpoint;
    private Gson apiJsonData;

    public FixerAPI() {
        try {
            // Hide API key for release
            apiEndpoint = new URL("http://data.fixer.io/api/latest?access_key=511e802e903882108a974da7cf58e1cb");
            apiJsonData = new Gson();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Rate> getRates() {
        try {
            CurrencyData data = retrieveData();
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

    private CurrencyData retrieveData() {
        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection) apiEndpoint.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");
            InputStream inputStream = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String inputLine;
            StringBuffer sb = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }
            br.close();
//            con.disconnect();
            return apiJsonData.fromJson(sb.toString(), CurrencyData.class);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if (con != null)
                con.disconnect();
        }
        return null;
    }
}
