package com.example.mudassirkhan.crowdzr.ui.sales;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ManageSalesPagerAdapter extends FragmentPagerAdapter{

    String title;
    public ManageSalesPagerAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                MSRequestFragment msRequestFragment=new MSRequestFragment();
                return msRequestFragment;
            case 1:
                MSStackFragment msStackFragment=new MSStackFragment();
                return msStackFragment;
        }
        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Request";
            case 1:
                return "Stack";
            default:
                return "";
        }
    }

    @Override
    public int getCount() {

        return 2;
    }
}
