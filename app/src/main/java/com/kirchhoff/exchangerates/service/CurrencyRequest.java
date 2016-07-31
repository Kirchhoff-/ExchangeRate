package com.kirchhoff.exchangerates.service;

import android.net.Uri;

import com.kirchhoff.exchangerates.database.CurrencyItem;
import com.kirchhoff.exchangerates.database.DatabaseManager;
import com.kirchhoff.exchangerates.utils.Time;
import com.octo.android.robospice.request.okhttp.OkHttpSpiceRequest;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.ArrayList;

/**
 * @author Kirchhoff-
 */
public class CurrencyRequest extends OkHttpSpiceRequest<ArrayList<CurrencyItem>> {

    private final static String STERLING = "R01035";
    private final static String BEL_RUB = "R01090B";
    private final static String DOLLAR_USA = "R01235";
    private final static String EURO = "R01239";
    private final static String YUAN = "R01375";


    private ArrayList<CurrencyItem> currencyList;

    public CurrencyRequest() {
        super(null);
        currencyList = new ArrayList<>(0);

    }

    @Override
    public ArrayList<CurrencyItem> loadDataFromNetwork() throws Exception {

        CurrencyItem item = null;
        String text = null;

        // With Uri.Builder class we can build our url is a safe manner
        Uri.Builder uriBuilder = Uri.parse("http://www.cbr.ru/scripts/XML_daily.asp?").buildUpon();
        //uriBuilder.appendQueryParameter("date_req", Time.getTime(0));
        uriBuilder.appendQueryParameter("date_req", "30/07/2016");

        URI uri = new URI(uriBuilder.build().toString());

        HttpURLConnection connection = getOkHttpClient().open(uri.toURL());
        InputStream in = null;
        try {
            // Read the response.
            in = connection.getInputStream();
            boolean requiredValute = false;
            String id = null;

            XmlPullParserFactory factory;
            XmlPullParser parser;

            factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            parser = factory.newPullParser();

            parser.setInput(in, null);

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String targetName = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (targetName.equalsIgnoreCase("Valute") &&
                                isRequiredValute(parser.getAttributeValue(0))) {

                            requiredValute = true;
                            id = parser.getAttributeValue(0);
                            item = new CurrencyItem();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if (requiredValute) {
                            if (targetName.equalsIgnoreCase("Valute")) {

                                currencyList.add(item);
                                item.setId(id);
                                item.setTime(System.currentTimeMillis());
                                requiredValute = false;
                            } else if (targetName.equalsIgnoreCase("name")) {
                                item.setName(text);
                            } else if (targetName.equalsIgnoreCase("value")) {
                                item.setRate(text);
                            } else if (targetName.equalsIgnoreCase("id")) {
                                item.setId(targetName);
                            }
                        }

                        break;

                    default:
                        break;
                }

                eventType = parser.next();
            }

            // Write currency to database
            if(currencyList.size() > 0) {
                DatabaseManager.getHelper().getCurrencyDao().writeCurrencyToDatabase(currencyList);
            }


            return currencyList;
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    private boolean isRequiredValute(String valuteId) {

        return valuteId.equalsIgnoreCase(STERLING) ||
                valuteId.equalsIgnoreCase(BEL_RUB) ||
                valuteId.equalsIgnoreCase(DOLLAR_USA) ||
                valuteId.equalsIgnoreCase(EURO) ||
                valuteId.equalsIgnoreCase(YUAN);

    }
}
