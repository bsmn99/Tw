package com.tw.retail.utils;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Custom Application class to configure the Realm Database Object.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);

        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("retail.realm")
                .schemaVersion(1)
                .build();

        Realm.setDefaultConfiguration(config);
    }
}
