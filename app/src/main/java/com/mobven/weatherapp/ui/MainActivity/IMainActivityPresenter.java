package com.mobven.weatherapp.ui.MainActivity;

import android.location.Location;

import com.mobven.weatherapp.ui.base.IPresenter;

public interface IMainActivityPresenter<V extends IMainActivityView> extends IPresenter<V> {
    void getWeather(String cityName);
    void getWeatherWithLocation(Location location);
}