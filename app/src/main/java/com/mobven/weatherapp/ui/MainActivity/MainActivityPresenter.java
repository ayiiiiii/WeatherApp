package com.mobven.weatherapp.ui.MainActivity;

import android.app.Activity;
import android.location.Location;

import com.mobven.weatherapp.data.DataManager;
import com.mobven.weatherapp.data.network.reqres.BaseResponse;
import com.mobven.weatherapp.data.network.reqres.GetWeatherResponse;
import com.mobven.weatherapp.data.network.service.ServiceCallback;
import com.mobven.weatherapp.ui.base.BasePresenter;

public class MainActivityPresenter<V extends IMainActivityView> extends BasePresenter<V> implements IMainActivityPresenter<V> {
    private DataManager dataManager;

    public MainActivityPresenter(Activity activity, DataManager dataManager) {
        super(activity);
        this.dataManager = dataManager;
    }

    @Override
    public void getWeather(String cityName) {
        getMvpView().showLoading();
        dataManager.getWeather(cityName, new ServiceCallback<BaseResponse>() {
            @Override
            public void onResponse(BaseResponse response) {
                getMvpView().dissmisLoading();
                GetWeatherResponse mResponse = (GetWeatherResponse) response;
                getMvpView().weatherReady(mResponse);
            }

            @Override
            public void onError(String errorMessage) {
                getMvpView().dissmisLoading();
            }
        });
    }

    @Override
    public void getWeatherWithLocation(Location location) {
        getMvpView().showLoading();
        dataManager.getWeatherWithLocation(location, new ServiceCallback<BaseResponse>() {
            @Override
            public void onResponse(BaseResponse response) {
                getMvpView().dissmisLoading();
                GetWeatherResponse mResponse = (GetWeatherResponse) response;
                getMvpView().weatherReady(mResponse);
            }

            @Override
            public void onError(String errorMessage) {
                getMvpView().dissmisLoading();
            }
        });
    }
}
