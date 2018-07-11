package com.mobven.weatherapp.ui.MainActivity;

import com.mobven.weatherapp.data.network.reqres.GetWeatherResponse;
import com.mobven.weatherapp.ui.base.IView;

public interface IMainActivityView extends IView
{
    void weatherReady(GetWeatherResponse response);
}