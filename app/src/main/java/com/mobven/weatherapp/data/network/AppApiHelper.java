package com.mobven.weatherapp.data.network;

import android.location.Location;

import com.mobven.weatherapp.data.network.reqres.BaseResponse;
import com.mobven.weatherapp.data.network.service.GetWeatherService;
import com.mobven.weatherapp.data.network.service.ServiceCallback;

import javax.inject.Inject;

public class AppApiHelper implements ApiHelper
{
    private GetWeatherService getWeatherService;

    @Inject
    public AppApiHelper(GetWeatherService getWeatherService) {
        this.getWeatherService = getWeatherService;
    }

    @Override
    public void getWeather(String cityName, ServiceCallback<BaseResponse> callback) {
        getWeatherService.getWeather(cityName, callback);
    }

    @Override
    public void getWeatherWithLocation(Location location, ServiceCallback<BaseResponse> callback) {
        getWeatherService.getWeatherWithLocation(location, callback);
    }
}
