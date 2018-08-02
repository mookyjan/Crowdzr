package com.example.mudassirkhan.crowdzr.util;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.List;

import static android.support.v4.util.Preconditions.checkNotNull;

public class FragmentUtils {

    public static void addFragmentToBackStack(@NonNull FragmentManager fragmentManager,
                                              @NonNull Fragment fragment, int frameId) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        fragmentManager.beginTransaction()
                .add(frameId, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit();
    }
    public static void addFragment(@NonNull FragmentManager fragmentManager,
                                   @NonNull Fragment fragment, int frameId) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        fragmentManager.beginTransaction()
                .add(frameId, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }

    public static void replaceFragmentToBackStack(@NonNull FragmentManager fragmentManager,
                                                  @NonNull Fragment fragment, int frameId) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        fragmentManager.beginTransaction()
                .replace(frameId, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit();
    }

    public static void replaceFragment(@NonNull FragmentManager fragmentManager,
                                       @NonNull Fragment fragment, int frameId) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        fragmentManager.beginTransaction()
                .replace(frameId, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }

    public static void removeFragment(@NonNull FragmentManager fragmentManager,
                                      @NonNull Fragment fragment) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        fragmentManager.beginTransaction()
                .remove(fragment)
                .commit();
    }

    public static void removeAllChildFragments(@NonNull FragmentManager fragmentManager) {
        checkNotNull(fragmentManager);
        List<Fragment> childFragments = fragmentManager.getFragments();
        if (childFragments != null && !childFragments.isEmpty()) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            for (Fragment childFragment : childFragments) {
                if (childFragment != null) {
                    fragmentTransaction.remove(childFragment);
                }
            }
            fragmentTransaction.commitAllowingStateLoss();
        }
    }
}
