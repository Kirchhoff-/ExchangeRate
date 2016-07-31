package com.kirchhoff.exchangerates.database;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.LinkedHashMap;

/**
 * @author Kirchhoff-
 */
@DatabaseTable(tableName = "weather_table")
public class CurrencyItem {

    public static final String TIME_COLUMN = "time";
    public static final String NAME_COLUMN = "name";
    public static final String RATE_COLUMN = "rate";
    public static final String HISTORY_COLUMN = "history";
    public static final String ID_COLUMN = "id";

    @DatabaseField(columnName = ID_COLUMN, id = true)
    private String id;
    @DatabaseField(columnName = TIME_COLUMN)
    private long time;
    @DatabaseField(columnName = NAME_COLUMN)
    private String name;
    @DatabaseField(columnName = RATE_COLUMN)
    private String rate;
    @DatabaseField(columnName = HISTORY_COLUMN, dataType = DataType.SERIALIZABLE)
    private LinkedHashMap<String, String> history;

    public LinkedHashMap<String, String> getHistory() {
        return history;
    }

    public void setHistory(LinkedHashMap<String, String> history) {
        this.history = history;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
