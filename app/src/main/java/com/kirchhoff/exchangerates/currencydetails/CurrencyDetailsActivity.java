package com.kirchhoff.exchangerates.currencydetails;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.kirchhoff.exchangerates.database.CurrencyItem;
import com.kirchhoff.exchangerates.R;
import com.kirchhoff.exchangerates.database.DatabaseManager;
import com.kirchhoff.exchangerates.utils.LogUtils;

/**
 * @author Kirchhoff-
 */
public class CurrencyDetailsActivity extends AppCompatActivity {

    public static final String CURRENCY_ITEM_ID = "currency_element_id";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.currency_details_act);

        String id = getIntent().getStringExtra(CURRENCY_ITEM_ID);
        CurrencyItem item = DatabaseManager.getHelper().getCurrencyDao().getItem(id);

        // Set up the toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);
        ab.setTitle(item.getName());


        CurrencyDetailsFragment currencyDetailsFragment =
                (CurrencyDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);


        if (currencyDetailsFragment == null) {

            currencyDetailsFragment = CurrencyDetailsFragment.newInstance();

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragmentContainer, currencyDetailsFragment);
            transaction.commit();
        }


        new CurrencyDetailsPresenter(currencyDetailsFragment, item);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
