package com.example.mudassirkhan.crowdzr.ui.stack;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mudassirkhan.crowdzr.HomeActivity;
import com.example.mudassirkhan.crowdzr.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddStackFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddStackFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    HomeActivity homeActivity;
    View mParentView;

    public AddStackFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddStackFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddStackFragment newInstance(String param1, String param2) {
        AddStackFragment fragment = new AddStackFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (container != null) {
            container.removeAllViews();
        }

        homeActivity=(HomeActivity)getActivity();
        homeActivity.tabLayout.setVisibility(View.GONE);
        homeActivity.viewPager.setVisibility(View.GONE);
        homeActivity.toolbar.setVisibility(View.GONE);
        homeActivity.toolbar.setTitle(getResources().getString(R.string.menu_profile));
        homeActivity.toolbar.setBackground(null);
        //homeActivity.toolbar.setBackgroundColor(ContextCompat.getColor(getActivity(),android.R.color.transparent));
        homeActivity.getSupportActionBar().setHomeButtonEnabled(true);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_stack, container, false);
    }

}
