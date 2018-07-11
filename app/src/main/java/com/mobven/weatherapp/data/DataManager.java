package com.mobven.weatherapp.data;

import com.mobven.weatherapp.data.location.LocationHelper;
import com.mobven.weatherapp.data.network.ApiHelper;
import com.mobven.weatherapp.data.pref.PrefHelper;

public interface DataManager extends ApiHelper, PrefHelper, LocationHelper {
}
