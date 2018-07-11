package com.mobven.weatherapp.data.network;

import com.mobven.weatherapp.data.network.reqres.GetWeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface
{
    @GET("weather")
    Call<GetWeatherResponse> getWeather(@Query("q") String cityName);

    @GET("weather")
    Call<GetWeatherResponse> getWeatherWithLocation(@Query("lat") Double lat, @Query("lon") Double lon);
}
