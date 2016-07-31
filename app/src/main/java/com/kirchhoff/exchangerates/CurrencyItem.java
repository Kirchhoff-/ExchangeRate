package com.kirchhoff.exchangerates;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author Kirchhoff-
 */
@DatabaseTable(tableName = "weather_table")
public class CurrencyItem {

    public static final String TIME_COLUMN = "time";
    public static final String NAME_COLUMN = "name";
    public static final String RATE_COLUMN = "rate";
    public static final String ID_COLUMN = "id";

    @DatabaseField(columnName = ID_COLUMN, id = true)
    private String id;
    @DatabaseField(columnName = TIME_COLUMN)
    private long time;
    @DatabaseField(columnName = NAME_COLUMN)
    private String name;
    @DatabaseField(columnName = RATE_COLUMN)
    private String rate;

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
