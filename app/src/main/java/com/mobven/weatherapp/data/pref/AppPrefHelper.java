package com.mobven.weatherapp.data.pref;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;

import com.mobven.weatherapp.util.Const;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppPrefHelper implements PrefHelper {

    private final SharedPreferences mPrefs;
    private final String PREF_SELECTED_CITY = "selected_city";
    private final String PREF_LOCATION = "location";
    private String prefFileName="Weather";
    private Context context;


    @Inject
    public AppPrefHelper(Context activity) {
        mPrefs = activity.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
        this.context=activity;
    }

    @Override
    public void setSelectedCity(String city) {
        mPrefs.edit().putString(PREF_SELECTED_CITY, city).apply();
    }

    @Override
    public String getSelectedCity() {
        return mPrefs.getString(PREF_SELECTED_CITY, Const.CITIES[7]);
    }

    @Override
    public void setGpsLocation(Location location) {
        if (location != null)
        {
            mPrefs.edit().putString(PREF_LOCATION, location.getLatitude()+","+location.getLongitude()).apply();
        }
        else
        {
            mPrefs.edit().putString(PREF_LOCATION, "").apply();
        }
    }

    @Override
    public Location getGpsLocation() {
        String locationText = mPrefs.getString(PREF_LOCATION, "");

        if (locationText.equals(""))
        {
            return null;
        }
        else
        {
            String[] locationArray = locationText.split(",");

            Location location = new Location("");
            location.setLatitude(Double.parseDouble(locationArray[0]));
            location.setLongitude(Double.parseDouble(locationArray[1]));

            return location;
        }

    }
}
