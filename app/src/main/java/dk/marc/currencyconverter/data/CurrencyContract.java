package dk.marc.currencyconverter.data;

import android.provider.BaseColumns;

public final class CurrencyContract {

    private CurrencyContract(){}

    public static final class CurrencyEntry implements BaseColumns{
        public static final String TABLE_NAME = "currency";
        public static final String COLUMN_CURRENCY_DATA = "data";
        public static final String COLUMN_DATE_UPDATED = "date_updated";
    }
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + CurrencyEntry.TABLE_NAME + " (" +
                    CurrencyEntry._ID + " INTEGER PRIMARY KEY," +
                    CurrencyEntry.COLUMN_CURRENCY_DATA + " TEXT," +
                    CurrencyEntry.COLUMN_DATE_UPDATED + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + CurrencyEntry.TABLE_NAME;
}
