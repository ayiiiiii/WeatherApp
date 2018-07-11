package com.mobven.weatherapp.data.pref;

import android.location.Location;

public interface PrefHelper {
    void setSelectedCity(String city);
    String getSelectedCity();

    void setGpsLocation(Location location);
    Location getGpsLocation();
}
