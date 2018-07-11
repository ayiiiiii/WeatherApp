package com.mobven.weatherapp.ui.notify;

import com.mobven.weatherapp.ui.ViewNavigator;
import com.mobven.weatherapp.ui.base.BaseActivity;

public class ViewNotifyHelper {
    public static void onCityChanged()
    {
        for (BaseActivity ac : ViewNavigator.mBackStackOfActivity)
        {
            if (ac instanceof ICityChanged)
            {
                ((ICityChanged) ac).onCityChanged();
            }
        }
    }
}
