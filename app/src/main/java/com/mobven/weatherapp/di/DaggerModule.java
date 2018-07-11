package com.mobven.weatherapp.di;

import android.app.Application;
import android.content.Context;

import com.mobven.weatherapp.data.AppDataManager;
import com.mobven.weatherapp.data.DataManager;
import com.mobven.weatherapp.data.location.AppLocationHelper;
import com.mobven.weatherapp.data.location.LocationHelper;
import com.mobven.weatherapp.data.network.ApiHelper;
import com.mobven.weatherapp.data.network.AppApiHelper;
import com.mobven.weatherapp.data.network.service.GetWeatherImp;
import com.mobven.weatherapp.data.network.service.GetWeatherService;
import com.mobven.weatherapp.data.pref.AppPrefHelper;
import com.mobven.weatherapp.data.pref.PrefHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DaggerModule {
    private Context context;


    public DaggerModule(Application app) {
        this.context = app;
    }


    @Provides
    Context providesContext() {
        return context;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(ApiHelper apiHelper, PrefHelper prefHelper, LocationHelper locationHelper) {
        return new AppDataManager(apiHelper, prefHelper, locationHelper);
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(GetWeatherService getWeatherService) {
        return new AppApiHelper(getWeatherService);
    }

    @Provides
    @Singleton
    PrefHelper providePrefHelper(Context context) {
        return new AppPrefHelper(context);
    }

    @Provides
    @Singleton
    LocationHelper provideLocationHelper(Context context) {
        return new AppLocationHelper(context);
    }

    @Provides
    @Singleton
    GetWeatherService provideGetWeather() {
        return new GetWeatherImp();
    }

}
