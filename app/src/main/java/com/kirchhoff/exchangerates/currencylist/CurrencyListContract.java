package com.kirchhoff.exchangerates.currencylist;

import com.kirchhoff.exchangerates.BasePresenter;
import com.kirchhoff.exchangerates.BaseView;
import com.kirchhoff.exchangerates.CurrencyItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kirchhoff-
 */
public interface CurrencyListContract {

    interface View extends BaseView<Presenter> {

        void showRefreshIndicator(boolean show);

        void showLoadIndicator(boolean show);

        void showCurrencyList(ArrayList<CurrencyItem> list);

        void showCurrencyDetails(String id);

        void setDate(long date);

        boolean isActive();
    }


    interface Presenter extends BasePresenter{

        void loadCurrencyList();

        void refreshCurrencyList();

        void showCurrencyDetails(String id);

        void onViewCreated();

        void onViewDestroyed();
    }

}
