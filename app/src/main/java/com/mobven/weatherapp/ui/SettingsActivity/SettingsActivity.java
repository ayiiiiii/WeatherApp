package com.mobven.weatherapp.ui.SettingsActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.mobven.weatherapp.AppController;
import com.mobven.weatherapp.R;
import com.mobven.weatherapp.data.DataManager;
import com.mobven.weatherapp.ui.base.BaseActivity;
import com.mobven.weatherapp.ui.notify.ViewNotifyHelper;

import javax.inject.Inject;

import static com.mobven.weatherapp.data.location.AppLocationHelper.MY_PERMISSION_ACCESS_COURSE_LOCATION;

public class SettingsActivity extends BaseActivity implements ISettingsActivityView {
    @Inject
    DataManager dataManager;
    RecyclerView rvCities;
    CityAdapter cityAdapter;
    Button bSave;
    TextView tvBatteryStatus;
    Switch sWifiStatus;
    CheckBox cbLocation;
    SettingsActivityPresenter settingsActivityPresenter;
    Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ((AppController) getApplication()).getDaggerComponent().inject(this);

        Toolbar tb = findViewById(R.id.toolbar);
        rvCities = findViewById(R.id.rvCities);
        bSave = findViewById(R.id.bSave);
        tvBatteryStatus = findViewById(R.id.tvBatteryStatus);
        sWifiStatus = findViewById(R.id.sWifiStatus);
        cbLocation = findViewById(R.id.cbLocation);

        sWifiStatus.setClickable(false);

        setSupportActionBar(tb);
        getSupportActionBar().setTitle(getText(R.string.settings));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rvCities.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        cityAdapter = new CityAdapter(this, dataManager);
        rvCities.setAdapter(cityAdapter);

        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbLocation.isChecked()) {
                    dataManager.setGpsLocation(location);
                } else {
                    dataManager.setGpsLocation(null);
                    dataManager.setSelectedCity(cityAdapter.getSelectedCity());
                }
                ViewNotifyHelper.onCityChanged();
                finish();
            }
        });

        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
                tvBatteryStatus.setText(String.valueOf(level) + "%");
            }
        }, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (mWifi.isConnected()) {
            sWifiStatus.setChecked(true);
        } else {
            sWifiStatus.setChecked(false);
        }

        if (dataManager.getGpsLocation() == null) {
            cityAdapter.setLocationSelected(false);
            cbLocation.setChecked(false);
        } else {
            cityAdapter.setLocationSelected(true);
            cbLocation.setChecked(true);
        }

        cityAdapter.updateData();

        cbLocation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    requestLocation();
                } else {
                    cityAdapter.setLocationSelected(false);
                    cityAdapter.updateData();
                }
            }
        });

        settingsActivityPresenter = new SettingsActivityPresenter(this, dataManager);
    }

    @Override
    public void locationReady(Location location) {
        this.location = location;
        cityAdapter.setLocationSelected(true);
        cityAdapter.updateData();
    }

    private void requestLocation() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    MY_PERMISSION_ACCESS_COURSE_LOCATION);
        } else {
            settingsActivityPresenter.getLocation();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSION_ACCESS_COURSE_LOCATION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    settingsActivityPresenter.getLocation();
                } else {
                    //not granted
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
