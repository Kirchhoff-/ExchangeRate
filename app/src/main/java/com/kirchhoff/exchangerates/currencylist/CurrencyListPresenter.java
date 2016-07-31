package com.kirchhoff.exchangerates.currencylist;

import android.content.Context;
import android.support.annotation.NonNull;

import com.kirchhoff.exchangerates.CurrencyItem;
import com.kirchhoff.exchangerates.service.CurrencyRequest;
import com.kirchhoff.exchangerates.service.OnlineService;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.util.ArrayList;

/**
 * @author Kirchhoff-
 */
public class CurrencyListPresenter implements CurrencyListContract.Presenter {

    private final CurrencyListContract.View mainView;

    private SpiceManager onlineService;

    private Context context;

    private boolean firstLoad = true;


    public CurrencyListPresenter(@NonNull CurrencyListContract.View view, Context context) {

        mainView = view;

        onlineService = new SpiceManager(OnlineService.class);

        this.context = context;

        mainView.setPresenter(this);
    }


    @Override
    public void onViewCreated() {
        // Start robospice manager when activity created
        // Fix looping bug when application is in background,
        // when spice manager is started
        if (!onlineService.isStarted()) {
            onlineService.start(context);
        }
    }


    @Override
    public void onViewDestroyed() {
        // Stop robospice manager when activity finished
        onlineService.shouldStop();
    }

    @Override
    public void start() {
        if (firstLoad) {
            mainView.showLoadIndicator(true);
            loadCurrencyList();
        }
    }


    @Override
    public void loadCurrencyList() {
        onlineService.execute(new CurrencyRequest(), new GetCurrencyListener());
    }


    @Override
    public void showCurrencyDetails(CurrencyItem item) {
       // weatherListView.showWeatherDetails(weatherElement);
    }


    public class GetCurrencyListener implements RequestListener<ArrayList<CurrencyItem>> {
        @Override
        public void onRequestFailure(SpiceException spiceException) {

            // The view may not be able to handle UI updates anymore
            if (!mainView.isActive())
                return;
        }

        @Override
        public void onRequestSuccess(ArrayList<CurrencyItem> currencyList) {

            // The view may not be able to handle UI updates anymore
            if (!mainView.isActive())
                return;

            mainView.showLoadIndicator(false);
            mainView.showRefreshIndicator(false);

            firstLoad = false;
            mainView.showCurrencyList(currencyList);
        }
    }


}
