package com.mobven.weatherapp.data.location;


import com.google.android.gms.tasks.OnSuccessListener;

public interface LocationHelper {
    void getLocation(OnSuccessListener onSuccessListener);
}
