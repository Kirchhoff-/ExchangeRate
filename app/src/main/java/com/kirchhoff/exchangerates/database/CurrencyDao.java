package com.kirchhoff.exchangerates.database;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.kirchhoff.exchangerates.CurrencyItem;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO object for weather table
 *
 * @author Kirchhoff-
 */
public class CurrencyDao extends BaseDaoImpl<CurrencyItem, Integer> {


    protected CurrencyDao(ConnectionSource connectionSource, Class<CurrencyItem> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }


    public List<CurrencyItem> getAllRecord() {

        try {
            List<CurrencyItem> all = this.queryForAll();

            if (all.size() == 0)
                return null;

            return all;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public void writeCurrencyToDatabase(ArrayList<CurrencyItem> list){

        for(int i = 0; i < list.size(); i++){
            try {
                this.createOrUpdate(list.get(i));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
