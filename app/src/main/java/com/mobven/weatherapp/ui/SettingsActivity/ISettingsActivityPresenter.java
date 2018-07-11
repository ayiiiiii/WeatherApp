package com.mobven.weatherapp.ui.SettingsActivity;

import com.mobven.weatherapp.ui.base.IPresenter;

public interface ISettingsActivityPresenter<V extends ISettingsActivityView> extends IPresenter<V> {
    void getLocation();
}