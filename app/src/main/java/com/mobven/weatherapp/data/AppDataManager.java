package com.mobven.weatherapp.data;


import android.location.Location;

import com.google.android.gms.tasks.OnSuccessListener;
import com.mobven.weatherapp.data.location.LocationHelper;
import com.mobven.weatherapp.data.network.ApiHelper;
import com.mobven.weatherapp.data.network.reqres.BaseResponse;
import com.mobven.weatherapp.data.network.service.ServiceCallback;
import com.mobven.weatherapp.data.pref.PrefHelper;


import javax.inject.Inject;

public class AppDataManager implements DataManager {
    ApiHelper apiHelper;
    PrefHelper prefHelper;
    LocationHelper locationHelper;

    @Inject
    public AppDataManager(ApiHelper apiHelper, PrefHelper prefHelper, LocationHelper locationHelper) {
        this.apiHelper = apiHelper;
        this.prefHelper = prefHelper;
        this.locationHelper = locationHelper;
    }


    @Override
    public void getWeather(String cityName, ServiceCallback<BaseResponse> callback) {
        apiHelper.getWeather(cityName, callback);
    }

    @Override
    public void getWeatherWithLocation(Location location, ServiceCallback<BaseResponse> callback) {
        apiHelper.getWeatherWithLocation(location, callback);
    }

    @Override
    public void setSelectedCity(String city) {
        prefHelper.setSelectedCity(city);
    }

    @Override
    public String getSelectedCity() {
        return prefHelper.getSelectedCity();
    }

    @Override
    public void getLocation(OnSuccessListener onSuccessListener) {
        locationHelper.getLocation(onSuccessListener);
    }

    @Override
    public void setGpsLocation(Location location) {
        prefHelper.setGpsLocation(location);
    }

    @Override
    public Location getGpsLocation() {
        return prefHelper.getGpsLocation();
    }
}
