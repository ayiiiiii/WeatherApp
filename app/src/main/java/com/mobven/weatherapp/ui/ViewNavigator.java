package com.mobven.weatherapp.ui;

import com.mobven.weatherapp.ui.base.BaseActivity;

import java.util.Stack;

public class ViewNavigator {
    public static Stack<BaseActivity> mBackStackOfActivity = new Stack<>();

    public static void removeActivity(BaseActivity activity) {
        mBackStackOfActivity.remove(activity);
    }

    public static void addActivityToStack(BaseActivity activity) {
        mBackStackOfActivity.add(activity);
    }
}
