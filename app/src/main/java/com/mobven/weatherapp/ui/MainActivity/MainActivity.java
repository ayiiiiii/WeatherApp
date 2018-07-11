package com.mobven.weatherapp.ui.MainActivity;

import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.FileProvider;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;


import com.mobven.weatherapp.AppController;
import com.mobven.weatherapp.R;
import com.mobven.weatherapp.data.DataManager;
import com.mobven.weatherapp.data.network.reqres.GetWeatherResponse;
import com.mobven.weatherapp.ui.SettingsActivity.SettingsActivity;
import com.mobven.weatherapp.ui.base.BaseActivity;
import com.mobven.weatherapp.ui.notify.ICityChanged;
import com.mobven.weatherapp.util.Util;


import java.io.File;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements IMainActivityView, ICityChanged {

    @Inject
    DataManager dataManager;
    MainFragment mainFragment;
    MainActivityPresenter mainActivityPresenter;
    BottomNavigationView navigation;
    String imageFilePath;
    PagerAdapter pagerAdapter;
    ViewPager viewPager;

    private static final int REQUEST_CAPTURE_IMAGE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((AppController) getApplication()).getDaggerComponent().inject(this);

        mainFragment = new MainFragment();
        mainFragment.setDataManager(dataManager);

        viewPager = findViewById(R.id.viewPager);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), this);
        pagerAdapter.setMainFragment(mainFragment);
        viewPager.setAdapter(pagerAdapter);

        mainActivityPresenter = new MainActivityPresenter(this, dataManager);
        mainActivityPresenter.getWeather(dataManager.getSelectedCity());

        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        navigation.setSelectedItemId(R.id.navigationHome);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigationHome:
                    return true;
                case R.id.navigationCamera:
                    openCameraIntent();
                    return true;
                case R.id.navigationSettings:
                    Intent i = new Intent(MainActivity.this, SettingsActivity.class);
                    startActivity(i);
                    return true;
            }
            return false;
        }
    };

    @Override
    public void weatherReady(GetWeatherResponse response) {
        mainFragment.weatherReady(response, dataManager.getGpsLocation());
    }

    @Override
    public void onCityChanged() {
        Location location = dataManager.getGpsLocation();
        if (location != null) {
            mainActivityPresenter.getWeatherWithLocation(location);
        } else {
            mainActivityPresenter.getWeather(dataManager.getSelectedCity());
        }

    }

    private void openCameraIntent() {
        Intent pictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (pictureIntent.resolveActivity(getPackageManager()) != null) {

            String imageFileName = "IMG_" + System.currentTimeMillis() + ".jpg";
            File image = new File(Util.getInternalStoragePath(), imageFileName);

            imageFilePath = image.getAbsolutePath();

            Uri photoURI = FileProvider.getUriForFile(this, "com.mobven.weatherapp.fileprovider", image);
            pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                    photoURI);

            startActivityForResult(pictureIntent, REQUEST_CAPTURE_IMAGE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if (requestCode == REQUEST_CAPTURE_IMAGE &&
                resultCode == RESULT_OK) {
            pagerAdapter.addImageFragment(imageFilePath);
            viewPager.setCurrentItem(pagerAdapter.getCount(), true);
        }
    }
}
