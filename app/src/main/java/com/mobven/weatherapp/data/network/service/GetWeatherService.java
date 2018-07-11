package com.mobven.weatherapp.data.network.service;


import android.location.Location;

import com.mobven.weatherapp.data.network.reqres.BaseResponse;

public interface GetWeatherService {
    void getWeather(String name, ServiceCallback<BaseResponse> callback);

    void getWeatherWithLocation(Location location, ServiceCallback<BaseResponse> callback);
}
