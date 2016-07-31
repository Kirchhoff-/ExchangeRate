package com.kirchhoff.exchangerates.currencydetails;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kirchhoff.exchangerates.CurrencyItem;
import com.kirchhoff.exchangerates.R;

/**
 * @author Kirchhoff-
 */
public class CurrencyDetailsFragment extends Fragment implements CurrencyDetailsContract.View {

    private CurrencyDetailsContract.Presenter presenter;

    private TextView rate;

    public CurrencyDetailsFragment() {
        // Requires empty public constructor.
    }

    public static CurrencyDetailsFragment newInstance() {
        return new CurrencyDetailsFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.currency_details_frag, container, false);

        rate = (TextView) root.findViewById(R.id.rate);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }


    @Override
    public void showWeatherDetails(CurrencyItem item) {
        rate.setText(item.getRate());
    }

    public boolean isActive(){
        return  isAdded();
    }

    @Override
    public void setPresenter(CurrencyDetailsContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
