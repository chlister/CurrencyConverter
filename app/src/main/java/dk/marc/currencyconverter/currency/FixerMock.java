package dk.marc.currencyconverter.currency;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import dk.marc.currencyconverter.currency.model.CurrencyData;
import dk.marc.currencyconverter.currency.model.Rate;

public class FixerMock implements CurrencyDAO {
    //region JSON string
    private static final String currencyMock = "{" +
            "  \"success\":true," +
            "  \"timestamp\":1552157346," +
            "  \"base\":\"EUR\"," +
            "  \"date\":\"2019-03-09\"," +
            "  \"rates\":{" +
            "    \"AED\":4.12831," +
            "    \"AFN\":84.07584," +
            "    \"ALL\":124.590154," +
            "    \"AMD\":548.930913," +
            "    \"ANG\":2.051099," +
            "    \"AOA\":353.617155," +
            "    \"ARS\":46.295894," +
            "    \"AUD\":1.595225," +
            "    \"AWG\":2.024231," +
            "    \"AZN\":1.916379," +
            "    \"BAM\":1.959946," +
            "    \"BBD\":2.238287," +
            "    \"BDT\":94.238639," +
            "    \"BGN\":1.956964," +
            "    \"BHD\":0.423824," +
            "    \"BIF\":2057.949848," +
            "    \"BMD\":1.123949," +
            "    \"BND\":1.518234," +
            "    \"BOB\":7.766092," +
            "    \"BRL\":4.349236," +
            "    \"BSD\":1.123892," +
            "    \"BTC\":0.000291," +
            "    \"BTN\":78.666317," +
            "    \"BWP\":12.105373," +
            "    \"BYN\":2.405194," +
            "    \"BYR\":22029.392151," +
            "    \"BZD\":2.265375," +
            "    \"CAD\":1.507946," +
            "    \"CDF\":1833.160539," +
            "    \"CHF\":1.132986," +
            "    \"CLF\":0.028152," +
            "    \"CLP\":754.511078," +
            "    \"CNY\":7.554737," +
            "    \"COP\":3557.409648," +
            "    \"CRC\":683.484814," +
            "    \"CUC\":1.123949," +
            "    \"CUP\":29.784637," +
            "    \"CVE\":110.595983," +
            "    \"CZK\":25.658668," +
            "    \"DJF\":199.748584," +
            "    \"DKK\":7.46426," +
            "    \"DOP\":56.899942," +
            "    \"DZD\":134.428615," +
            "    \"EGP\":19.584247," +
            "    \"ERN\":16.859631," +
            "    \"ETB\":32.212807," +
            "    \"EUR\":1," +
            "    \"FJD\":2.400811," +
            "    \"FKP\":0.854218," +
            "    \"GBP\":0.863451," +
            "    \"GEL\":3.012621," +
            "    \"GGP\":0.863488," +
            "    \"GHS\":6.227114," +
            "    \"GIP\":0.854218," +
            "    \"GMD\":55.652359," +
            "    \"GNF\":10368.426042," +
            "    \"GTQ\":8.662328," +
            "    \"GYD\":234.804535," +
            "    \"HKD\":8.823727," +
            "    \"HNL\":27.576123," +
            "    \"HRK\":7.414806," +
            "    \"HTG\":94.005378," +
            "    \"HUF\":315.504041," +
            "    \"IDR\":16083.704167," +
            "    \"ILS\":4.079601," +
            "    \"IMP\":0.863488," +
            "    \"INR\":78.672921," +
            "    \"IQD\":1337.498809," +
            "    \"IRR\":47323.85532," +
            "    \"ISK\":136.339252," +
            "    \"JEP\":0.863488," +
            "    \"JMD\":143.011651," +
            "    \"JOD\":0.797221," +
            "    \"JPY\":124.943788," +
            "    \"KES\":111.979429," +
            "    \"KGS\":78.43307," +
            "    \"KHR\":4318.776685," +
            "    \"KMF\":492.430016," +
            "    \"KPW\":1011.63561," +
            "    \"KRW\":1274.423246," +
            "    \"KWD\":0.341771," +
            "    \"KYD\":0.936569," +
            "    \"KZT\":425.538602," +
            "    \"LAK\":9642.921085," +
            "    \"LBP\":1694.85869," +
            "    \"LKR\":200.670209," +
            "    \"LRD\":181.826825," +
            "    \"LSL\":16.219007," +
            "    \"LTL\":3.318728," +
            "    \"LVL\":0.679866," +
            "    \"LYD\":1.567952," +
            "    \"MAD\":10.828687," +
            "    \"MDL\":19.154898," +
            "    \"MGA\":3990.017846," +
            "    \"MKD\":61.574965," +
            "    \"MMK\":1708.293664," +
            "    \"MNT\":2952.371857," +
            "    \"MOP\":9.087016," +
            "    \"MRO\":401.250031," +
            "    \"MUR\":39.406063," +
            "    \"MVR\":17.37667," +
            "    \"MWK\":818.925838," +
            "    \"MXN\":21.909584," +
            "    \"MYR\":4.594257," +
            "    \"MZN\":70.567155," +
            "    \"NAD\":16.241481," +
            "    \"NGN\":403.497927," +
            "    \"NIO\":36.843458," +
            "    \"NOK\":9.825001," +
            "    \"NPR\":125.696833," +
            "    \"NZD\":1.651924," +
            "    \"OMR\":0.433029," +
            "    \"PAB\":1.12378," +
            "    \"PEN\":3.723586," +
            "    \"PGK\":3.793158," +
            "    \"PHP\":58.73872," +
            "    \"PKR\":157.072235," +
            "    \"PLN\":4.304278," +
            "    \"PYG\":6840.13042," +
            "    \"QAR\":4.09234," +
            "    \"RON\":4.743405," +
            "    \"RSD\":117.981302," +
            "    \"RUB\":74.560056," +
            "    \"RWF\":989.07475," +
            "    \"SAR\":4.218123," +
            "    \"SBD\":9.15001," +
            "    \"SCR\":15.387274," +
            "    \"SDG\":53.496023," +
            "    \"SEK\":10.603448," +
            "    \"SGD\":1.527563," +
            "    \"SHP\":1.484628," +
            "    \"SLL\":9834.550448," +
            "    \"SOS\":651.890556," +
            "    \"SRD\":8.382451," +
            "    \"STD\":23659.791521," +
            "    \"SVC\":9.833655," +
            "    \"SYP\":578.833898," +
            "    \"SZL\":16.218994," +
            "    \"THB\":35.664055," +
            "    \"TJS\":10.604736," +
            "    \"TMT\":3.93382," +
            "    \"TND\":3.43052," +
            "    \"TOP\":2.551308," +
            "    \"TRY\":6.115184," +
            "    \"TTD\":7.621327," +
            "    \"TWD\":34.736797," +
            "    \"TZS\":2634.87674," +
            "    \"UAH\":29.654302," +
            "    \"UGX\":4163.334407," +
            "    \"USD\":1.123949," +
            "    \"UYU\":36.742291," +
            "    \"UZS\":9431.393785," +
            "    \"VEF\":11.225441," +
            "    \"VND\":26075.045061," +
            "    \"VUV\":128.562959," +
            "    \"WST\":2.95608," +
            "    \"XAF\":657.341737," +
            "    \"XAG\":0.073277," +
            "    \"XAU\":0.000866," +
            "    \"XCD\":3.037528," +
            "    \"XDR\":0.810391," +
            "    \"XOF\":660.882138," +
            "    \"XPF\":119.704569," +
            "    \"YER\":281.440768," +
            "    \"ZAR\":16.229709," +
            "    \"ZMK\":10116.889989," +
            "    \"ZMW\":13.5144," +
            "    \"ZWL\":362.310457" +
            "  }" +
            "}";
    //endregion

    @Override
    public List<Rate> getRates() {
        Gson g = new Gson();
        ArrayList<Rate> rates = new ArrayList<>();
        CurrencyData c = g.fromJson(currencyMock, CurrencyData.class);
        String[] countryCodes = new String[c.getRates().keySet().size()];
        c.getRates().keySet().toArray(countryCodes);
        Float[] exchangeRates = new Float[c.getRates().values().size()];
        c.getRates().values().toArray(exchangeRates);
        for (int i = 0; i < c.getRates().size(); i++) {
            rates.add(new Rate(countryCodes[i], exchangeRates[i]));
            c.getRates().values();
        }

        return rates;
    }

}