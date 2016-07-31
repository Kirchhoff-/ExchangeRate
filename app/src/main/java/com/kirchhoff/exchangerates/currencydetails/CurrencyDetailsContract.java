package com.kirchhoff.exchangerates.currencydetails;

import com.kirchhoff.exchangerates.BasePresenter;
import com.kirchhoff.exchangerates.BaseView;
import com.kirchhoff.exchangerates.database.CurrencyItem;

/**
 * @author Kirchhoff-
 */
public class CurrencyDetailsContract {

    interface View extends BaseView<Presenter>{

        void showWeatherDetails(CurrencyItem item);

        boolean isActive();

    }


    interface Presenter extends BasePresenter {

    }
}
