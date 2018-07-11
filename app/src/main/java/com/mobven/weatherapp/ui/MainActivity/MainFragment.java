package com.mobven.weatherapp.ui.MainActivity;

import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobven.weatherapp.R;
import com.mobven.weatherapp.data.DataManager;
import com.mobven.weatherapp.data.network.reqres.GetWeatherResponse;
import com.mobven.weatherapp.ui.base.BaseFragment;
import com.mobven.weatherapp.util.Util;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainFragment extends BaseFragment
{
    ImageView ivImage;
    TextView tvMinTemp, tvMaxTemp, tvCity, tvDescription;
    DataManager dataManager;
    NumberFormat formatter = new DecimalFormat("#0.00");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        ivImage = view.findViewById(R.id.ivImage);
        tvMinTemp = view.findViewById(R.id.tvMinTemp);
        tvMaxTemp = view.findViewById(R.id.tvMaxTemp);
        tvCity = view.findViewById(R.id.tvCity);
        tvDescription = view.findViewById(R.id.tvDescription);

        return view;
    }

    public void weatherReady(GetWeatherResponse response, Location location) {
        Picasso.get().load(Util.getImageUrl(response.weather.get(0).icon)).into(ivImage);

        tvMinTemp.setText(Util.kelvinToCelsius(response.main.tempMin)+"°");
        tvMaxTemp.setText(Util.kelvinToCelsius(response.main.tempMax)+"°");
        if (location == null)
        {
            tvCity.setText(dataManager.getSelectedCity());
        }
        else
        {
            tvCity.setText(formatter.format(location.getLatitude())+"-"+formatter.format(location.getLongitude()));
        }

        tvDescription.setText(response.weather.get(0).description);
    }

    public void setDataManager(DataManager dataManager) {
        this.dataManager = dataManager;
    }
}
