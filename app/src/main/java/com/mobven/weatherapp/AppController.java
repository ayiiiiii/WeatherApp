package com.mobven.weatherapp;

import android.app.Application;

import com.mobven.weatherapp.di.DaggerComponent;
import com.mobven.weatherapp.di.DaggerDaggerComponent;
import com.mobven.weatherapp.di.DaggerModule;

import dagger.Module;

@Module
public class AppController extends Application {
    private DaggerComponent daggerComponent;
    private static AppController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        daggerComponent = createDaggerComponent();

        mInstance = this;
    }

    public DaggerComponent getDaggerComponent() {
        return daggerComponent == null ? createDaggerComponent() : daggerComponent;
    }


    private DaggerComponent createDaggerComponent() {
        return DaggerDaggerComponent.builder().daggerModule(new DaggerModule(this)).build();
    }

    public void clearComponent() {
        daggerComponent = null;
    }

    public static AppController getInstance() {
        return mInstance;
    }
}
