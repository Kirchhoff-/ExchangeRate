package com.kirchhoff.exchangerates.database;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.kirchhoff.exchangerates.utils.Time;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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


    public void writeCurrencyToDatabase(ArrayList<CurrencyItem> list) {

        for (int i = 0; i < list.size(); i++) {
            CurrencyItem item = list.get(i);
            try {
                CurrencyItem currencyItem = this.queryForSameId(item);
                if (currencyItem == null) {
                    this.createOrUpdate(item);
                } else {
                    if (currencyItem.getHistory() != null) {
                        currencyItem.getHistory().put(Time.getTime(0), item.getRate());
                    } else {
                        LinkedHashMap<String, String> map = new LinkedHashMap<>();
                        map.put(Time.getTime(0), item.getRate());
                        currencyItem.setHistory(map);
                    }
                    this.update(currencyItem);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public CurrencyItem getItem(String id) {

        CurrencyItem item = new CurrencyItem();
        item.setId(id);
        try {
            return this.queryForSameId(item);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
