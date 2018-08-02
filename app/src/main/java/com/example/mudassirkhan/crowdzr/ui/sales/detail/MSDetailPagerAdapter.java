package com.example.mudassirkhan.crowdzr.ui.sales.detail;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MSDetailPagerAdapter extends FragmentPagerAdapter {

    String title;
    public MSDetailPagerAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                MSDetailOrderFragment msRequestFragment=new MSDetailOrderFragment();
                return msRequestFragment;
            case 1:
                MSDetailChatFragment msStackFragment=new MSDetailChatFragment();
                return msStackFragment;
        }
        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Order";
            case 1:
                return "Chat";
            default:
                return "";
        }
    }

    @Override
    public int getCount() {

        return 2;
    }
}
