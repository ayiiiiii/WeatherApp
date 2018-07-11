package com.mobven.weatherapp.ui.MainActivity;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import com.mobven.weatherapp.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class PagerAdapter extends FragmentPagerAdapter {

    private static final String TAG = "PagerAdapter";
    private Context mContext;
    private List<BaseFragment> fragments = new ArrayList<>();

    public PagerAdapter(FragmentManager fragmentManager, Context context) {
        super(fragmentManager);
        mContext = context;
    }

    public void setMainFragment(MainFragment mainFragment) {
        fragments.add(mainFragment);
    }

    public void addImageFragment(String imagePath) {
        ImageFragment fragment = new ImageFragment();
        fragment.setImagePath(imagePath);
        fragments.add(fragment);

        notifyDataSetChanged();
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        Log.d(TAG, "Item Destroyed");
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }


}

