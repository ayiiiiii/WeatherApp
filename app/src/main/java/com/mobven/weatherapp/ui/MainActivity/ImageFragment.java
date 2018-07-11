package com.mobven.weatherapp.ui.MainActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mobven.weatherapp.R;
import com.mobven.weatherapp.ui.base.BaseFragment;
import com.squareup.picasso.Picasso;

import java.io.File;

public class ImageFragment extends BaseFragment {
    ImageView ivImage;
    String imagePath;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image, container, false);

        ivImage = view.findViewById(R.id.ivImage);
        Picasso.get().load(new File(imagePath)).into(ivImage);

        return view;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

}
