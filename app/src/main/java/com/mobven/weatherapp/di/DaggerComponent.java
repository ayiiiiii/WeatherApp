package com.mobven.weatherapp.di;

import com.mobven.weatherapp.ui.MainActivity.MainActivity;
import com.mobven.weatherapp.ui.SettingsActivity.SettingsActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules =  {DaggerModule.class})
public interface DaggerComponent {
    void inject(MainActivity mainActivity);
    void inject(SettingsActivity settingsActivity);
}