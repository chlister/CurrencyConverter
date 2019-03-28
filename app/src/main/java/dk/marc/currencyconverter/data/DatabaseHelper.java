package dk.marc.currencyconverter.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import dk.marc.currencyconverter.currency.model.CurrencyData;
import dk.marc.currencyconverter.currency.model.Rate;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    private static final String DATABASE_NAME = "CurrencyDB.db";
    public static final String TABLE_NAME = "currency";
    public static final String COLUMN_CURRENCY_ID = "ID";
    public static final String COLUMN_CURRENCY_DATA = "data";
    public static final String COLUMN_DATE_UPDATED = "date_updated";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_CURRENCY_ID + " INTEGER PRIMARY KEY," +
                    COLUMN_CURRENCY_DATA + " TEXT," +
                    COLUMN_DATE_UPDATED + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        Log.d(TAG, "DatabaseHelper: Constructor called");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate() called with: db = [" + db + "]");
        db.execSQL(SQL_CREATE_ENTRIES);
        Log.d(TAG, "onCreate: Database created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        Log.d(TAG, "onUpgrade: Deleting entries and rebuilding");
    }


    public boolean addData(String data, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_CURRENCY_DATA, data);
        contentValues.put(COLUMN_DATE_UPDATED, date);

        Log.d(TAG, "addData: Adding " + data + " to " + TABLE_NAME);
        long result = db.insert(TABLE_NAME, null, contentValues);

        // if data is inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public List<Rate> getAllRates() {
        CurrencyData data;        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

        List<Rate> rates = new ArrayList<>();
        try (SQLiteDatabase db = this.getWritableDatabase()) {
            String query = "SELECT * FROM " + TABLE_NAME;
            Cursor cursor = db.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                do {
                    System.out.println("The current date is: " + format.format(Calendar.getInstance().getTime()));
                    System.out.println("Date : " + cursor.getString(2));
                    Gson g = new Gson();
                    data = g.fromJson(cursor.getString(1), CurrencyData.class);
                    if (data != null) {
                        String[] countryCodes = new String[data.getRates().keySet().size()];
                        data.getRates().keySet().toArray(countryCodes);
                        Float[] exchangeRates = new Float[data.getRates().values().size()];
                        data.getRates().values().toArray(exchangeRates);
                        for (int i = 0; i < data.getRates().size(); i++) {
                            rates.add(new Rate(countryCodes[i], exchangeRates[i]));
                            data.getRates().values();
                        }
                    }
                } while (cursor.moveToNext());
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return rates;

    }

    public List<Rate> getRate() {
        CurrencyData data;
        List<Rate> rates = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        String[] projection = {
                COLUMN_CURRENCY_ID,
                COLUMN_CURRENCY_DATA,
                COLUMN_DATE_UPDATED
        };
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_DATE_UPDATED +
                "  ";

        // WHERE
        String selection = COLUMN_DATE_UPDATED + " = '" + format.format(Calendar.getInstance().getTime()) + "'";

        // TODO: Match string Date with today
        Cursor cursor = db.query(TABLE_NAME, projection, selection, null, null, null, null);
        if (cursor.moveToNext()) {
            Log.d(TAG, "getRate: Query returned a row");
            do {
                Gson g = new Gson();
                data = g.fromJson(cursor.getString(1), CurrencyData.class);
                if (data != null) {
                    String[] countryCodes = new String[data.getRates().keySet().size()];
                    data.getRates().keySet().toArray(countryCodes);
                    Float[] exchangeRates = new Float[data.getRates().values().size()];
                    data.getRates().values().toArray(exchangeRates);
                    for (int i = 0; i < data.getRates().size(); i++) {
                        rates.add(new Rate(countryCodes[i], exchangeRates[i]));
                        data.getRates().values();
                    }
            }
            } while (cursor.moveToNext());
        }
        cursor.close();

        return rates;
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}
