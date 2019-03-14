package dk.marc.currencyconverter.utility;

public class CurrencyConverter {

    /**
     * Converts a value from the baseRate to the targetRate
     * Usable if the exchange rate between baseRate and targetRate is <b>unknown</b>
     *
     * @param baseRate   <i>float</i> currency exchange rate
     * @param targetRate <i>float</i> currency exchange rate
     * @param value      <i>float</i> value to be converted
     * @return <i>float</i> value converted to the targetRate
     */
    public static float ConvertFromBaseToTarget(float baseRate, float targetRate, float value) {

        float exchangeRate =   targetRate / baseRate;
        return exchangeRate * value;
    }
}
