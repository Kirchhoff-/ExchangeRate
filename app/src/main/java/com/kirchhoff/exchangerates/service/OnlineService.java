package com.kirchhoff.exchangerates.service;


import android.app.Application;

import com.octo.android.robospice.okhttp.OkHttpSpiceService;
import com.octo.android.robospice.persistence.CacheManager;
import com.octo.android.robospice.persistence.exception.CacheCreationException;

/**
 * @author Kirchhoff-
 */
public class OnlineService extends OkHttpSpiceService {


    @Override
    public CacheManager createCacheManager(Application application) throws CacheCreationException {
        CacheManager manager = new CacheManager();
        manager.addPersister(new CurrencyRequestPersistent(2048));
        return manager;
    }
}
