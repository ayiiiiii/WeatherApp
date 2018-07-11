package com.mobven.weatherapp.data.network.service;

import android.location.Location;

import com.mobven.weatherapp.data.network.ApiClient;
import com.mobven.weatherapp.data.network.ApiInterface;
import com.mobven.weatherapp.data.network.reqres.BaseResponse;
import com.mobven.weatherapp.data.network.reqres.GetWeatherResponse;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mobven.weatherapp.util.Const.SUCCESS_CODE;

public class GetWeatherImp implements GetWeatherService {
    private ApiInterface apiService;

    @Inject
    public GetWeatherImp() {
        apiService = ApiClient.getClient().create(ApiInterface.class);
    }

    @Override
    public void getWeather(String cityName, final ServiceCallback<BaseResponse> callback) {
        apiService.getWeather(cityName).enqueue(new Callback<GetWeatherResponse>() {
            @Override
            public void onResponse(Call<GetWeatherResponse> call, Response<GetWeatherResponse> response) {
                if (response.code() == SUCCESS_CODE) {
                    callback.onResponse(response.body());
                } else {
                    callback.onError(response.message());
                }
            }

            @Override
            public void onFailure(Call<GetWeatherResponse> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    @Override
    public void getWeatherWithLocation(Location location, final ServiceCallback<BaseResponse> callback) {
        apiService.getWeatherWithLocation(location.getLatitude(), location.getLongitude()).enqueue(new Callback<GetWeatherResponse>() {
            @Override
            public void onResponse(Call<GetWeatherResponse> call, Response<GetWeatherResponse> response) {
                if (response.code() == SUCCESS_CODE) {
                    callback.onResponse(response.body());
                } else {
                    callback.onError(response.message());
                }
            }

            @Override
            public void onFailure(Call<GetWeatherResponse> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }
}
