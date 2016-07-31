package com.kirchhoff.exchangerates.currencydetails;

import android.support.annotation.NonNull;

import com.kirchhoff.exchangerates.CurrencyItem;

/**
 * @author Kirchhoff-
 */
public class CurrencyDetailsPresenter implements CurrencyDetailsContract.Presenter {

    private final CurrencyDetailsContract.View mainView;

    private final CurrencyItem element;

    public CurrencyDetailsPresenter(@NonNull CurrencyDetailsContract.View view,
                                   @NonNull CurrencyItem element){
        mainView = view;
        this.element = element;

        mainView.setPresenter(this);
    }



    @Override
    public void start() {

        // The view may not be able to handle UI updates anymore
        if (!mainView.isActive())
            return;

        mainView.showWeatherDetails(element);
    }
}
