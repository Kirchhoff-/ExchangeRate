package com.kirchhoff.exchangerates.currencylist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.kirchhoff.exchangerates.R;

/**
 * @author Kirchhoff-
 */
public class CurrencyListActivity extends AppCompatActivity {


    private CurrencyListPresenter presenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.currency_list_act);


        // Set up the toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(false);

        CurrencyListFragment currencyListFragment =
                (CurrencyListFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);

        if (currencyListFragment == null) {
            // Create the fragment
            currencyListFragment = CurrencyListFragment.newInstance();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragmentContainer, currencyListFragment);
            transaction.commit();

        }

        // Create the presenter
        presenter = new CurrencyListPresenter(currencyListFragment, this);
        presenter.onViewCreated();
    }


    @Override
    protected void onDestroy() {
        presenter.onViewDestroyed();
        super.onDestroy();
    }
}
