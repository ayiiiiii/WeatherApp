package com.mobven.weatherapp.ui.SettingsActivity;

import android.app.Activity;
import android.location.Location;

import com.google.android.gms.tasks.OnSuccessListener;
import com.mobven.weatherapp.data.DataManager;
import com.mobven.weatherapp.ui.base.BasePresenter;

public class SettingsActivityPresenter<V extends ISettingsActivityView> extends BasePresenter<V> implements ISettingsActivityPresenter<V> {
    private DataManager dataManager;

    public SettingsActivityPresenter(Activity activity, DataManager dataManager) {
        super(activity);
        this.dataManager = dataManager;
    }

    @Override
    public void getLocation() {
        getMvpView().showLoading();
        dataManager.getLocation(new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                getMvpView().locationReady((Location) o);
                getMvpView().dissmisLoading();
            }
        });
    }
}
