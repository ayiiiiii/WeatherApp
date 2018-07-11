package com.mobven.weatherapp.data.location;

import android.content.Context;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppLocationHelper implements LocationHelper {
    private FusedLocationProviderClient mFusedLocationClient;
    private Context context;
    public static final int MY_PERMISSION_ACCESS_COURSE_LOCATION = 101;

    @Inject
    public AppLocationHelper(Context context) {
        this.context = context;
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
    }

    @Override
    public void getLocation(OnSuccessListener onSuccessListener) {
        try {
            mFusedLocationClient.getLastLocation().addOnSuccessListener(onSuccessListener);
        } catch (SecurityException e) {
        }


    }

}
