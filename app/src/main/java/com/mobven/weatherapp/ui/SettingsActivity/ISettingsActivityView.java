package com.mobven.weatherapp.ui.SettingsActivity;

import android.location.Location;

import com.mobven.weatherapp.ui.base.IView;

public interface ISettingsActivityView extends IView {
    void locationReady(Location location);
}