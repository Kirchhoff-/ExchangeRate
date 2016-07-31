package com.kirchhoff.exchangerates.service;

import com.octo.android.robospice.persistence.memory.CacheItem;
import com.octo.android.robospice.persistence.memory.LruCache;
import com.octo.android.robospice.persistence.memory.LruCacheObjectPersister;

public class CurrencyRequestPersistent extends LruCacheObjectPersister<Object> {

    public CurrencyRequestPersistent(int cacheSize) {
        super(Object.class, new LruCache<Object, CacheItem<Object>>(cacheSize));
    }

    @Override
    public boolean canHandleClass(Class<?> clazz) {
        return true;
    }

}
