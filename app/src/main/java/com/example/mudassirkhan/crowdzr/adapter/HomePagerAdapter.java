package com.example.mudassirkhan.crowdzr.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.mudassirkhan.crowdzr.ui.home.RequestsFragment;
import com.example.mudassirkhan.crowdzr.ui.stack.StacksFragment;

public class HomePagerAdapter extends FragmentPagerAdapter {

    String title;

    public HomePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                RequestsFragment requestsFragment=RequestsFragment.newInstance();
               return requestsFragment;
            case 1:
                StacksFragment stacksFragment=StacksFragment.newInstance();
                return stacksFragment;


        }
        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                title="Requests";
               // return "Requests";
                break;
            case 1:
                //return "Stacks";
              //  return getPageTitle(1);
                title="Stacks";
                break;
            default:
                title="";
                break;
        }
        return title;

    }

    @Override
    public int getCount() {
        return 2;
    }
}
