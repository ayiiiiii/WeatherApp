package com.mobven.weatherapp.ui.SettingsActivity;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mobven.weatherapp.R;
import com.mobven.weatherapp.data.DataManager;
import com.mobven.weatherapp.util.Const;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<String> list = Arrays.asList(Const.CITIES);
    private List<Boolean> isSelectedList = new ArrayList<>();
    String selectedCity;
    Activity activity;
    DataManager dataManager;
    boolean locationSelected;

    public CityAdapter(Activity activity, DataManager dataManager) {
        this.dataManager = dataManager;

        this.activity = activity;

    }

    public void updateData() {
        isSelectedList.clear();
        selectedCity = dataManager.getSelectedCity();
        for (int i = 0; i < list.size(); i++) {
            if (selectedCity.equals(list.get(i)) && !locationSelected) {
                isSelectedList.add(true);
            } else {
                isSelectedList.add(false);
            }
        }
        notifyDataSetChanged();
    }

    public void setLocationSelected(boolean locationSelected) {
        this.locationSelected = locationSelected;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_city, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        String city = list.get(position);
        final boolean isSelected = isSelectedList.get(position);
        MyViewHolder mHolder = (MyViewHolder) holder;

        mHolder.tvCity.setText(city);
        if (isSelected) {
            mHolder.ivTick.setVisibility(View.VISIBLE);
            mHolder.llContainer.setBackgroundColor(activity.getResources().getColor(R.color.lightGray));
        } else {
            mHolder.ivTick.setVisibility(View.GONE);
            mHolder.llContainer.setBackgroundColor(activity.getResources().getColor(R.color.white));
        }
        mHolder.llContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!locationSelected) {
                    for (int i = 0; i < isSelectedList.size(); i++) {
                        isSelectedList.set(i, false);
                    }
                    isSelectedList.set(position, true);
                    notifyDataSetChanged();
                }
            }
        });
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCity;
        private ImageView ivTick;
        private LinearLayout llContainer;

        public MyViewHolder(View view) {
            super(view);
            tvCity = view.findViewById(R.id.tvCity);
            ivTick = view.findViewById(R.id.ivTick);
            llContainer = view.findViewById(R.id.llContainer);
        }
    }

    public String getSelectedCity() {
        for (int i = 0; i < isSelectedList.size(); i++) {
            if (isSelectedList.get(i)) {
                return list.get(i);
            }
        }
        return null;
    }

}
