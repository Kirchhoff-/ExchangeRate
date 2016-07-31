package com.kirchhoff.exchangerates.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.kirchhoff.exchangerates.utils.LogUtils;

import java.sql.SQLException;

/**
 * @author Kirchhoff-
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "currencyDatabase";

    private static final int DATABASE_VERSION = 9;

    private CurrencyDao currencyDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {

        try {
            TableUtils.createTable(connectionSource, CurrencyItem.class);
        } catch (SQLException e) {
            LogUtils.e("Error during creating database " + DATABASE_NAME, e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

        try {

            /**
             * In production application should copy previous data
             * to new instance of the table.
             *
             * For now just drop and create new table
             */
            TableUtils.dropTable(connectionSource, CurrencyItem.class, true);
            TableUtils.createTable(connectionSource, CurrencyItem.class);
        } catch (SQLException e) {
            LogUtils.e("Error during update db from " + oldVersion + " to " + newVersion, e);
        }
    }


    public CurrencyDao getCurrencyDao() {

        try {
            if (currencyDao == null)
                currencyDao = new CurrencyDao(getConnectionSource(), CurrencyItem.class);
        } catch (SQLException e) {
            LogUtils.d("Can't get weather DAO ", e);
        }

        return currencyDao;
    }

    @Override
    public void close() {
        super.close();
        currencyDao = null;
    }
}
