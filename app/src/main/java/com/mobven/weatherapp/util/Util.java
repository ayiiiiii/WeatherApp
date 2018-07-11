package com.mobven.weatherapp.util;

import com.mobven.weatherapp.AppController;

import java.io.File;

public class Util {

    public static String getImageUrl(String iconID) {
        return "https://openweathermap.org/img/w/" + iconID + ".png";
    }

    public static String getInternalStoragePath() {
        File file = new File(AppController.getInstance().getFilesDir().getPath(), "Weather");
        file.mkdirs();

        return file.getPath();
    }

    public static int kelvinToCelsius(double kelvin) {
        return (int) (kelvin - 273.16);
    }
}
