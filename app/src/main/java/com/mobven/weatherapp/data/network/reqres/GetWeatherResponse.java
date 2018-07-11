package com.mobven.weatherapp.data.network.reqres;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetWeatherResponse extends BaseResponse {
    public List<Weather> weather;
    public Main main;

    public class Weather {
        public String description;
        public String icon;
    }

    public class Main {
        public Double temp;
        @SerializedName("temp_min")
        public Double tempMin;
        @SerializedName("temp_max")
        public Double tempMax;
    }
}
