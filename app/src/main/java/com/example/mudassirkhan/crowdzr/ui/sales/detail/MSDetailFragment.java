package com.example.mudassirkhan.crowdzr.ui.sales.detail;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mudassirkhan.crowdzr.HomeActivity;
import com.example.mudassirkhan.crowdzr.R;
import com.example.mudassirkhan.crowdzr.ui.sales.ManageSalesPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MSDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MSDetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View mParentView;
    ViewPager mViewPager;
    HomeActivity homeActivity;
    TabLayout mTabLayout;

    public MSDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MSDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MSDetailFragment newInstance(String param1, String param2) {
        MSDetailFragment fragment = new MSDetailFragment();
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
        // Inflate the layout for this fragment
        mParentView= inflater.inflate(R.layout.fragment_msdetail, container, false);
        initViews();
        return mParentView;
    }

    public void initViews(){
        mTabLayout=mParentView.findViewById(R.id.tab_layout_manage_sales_detail);
        mViewPager=mParentView.findViewById(R.id.viewPagerManageSalesDetail);
        MSDetailPagerAdapter manageSalesDetailPagerAdapter=new MSDetailPagerAdapter(getFragmentManager());
        mViewPager.setAdapter(manageSalesDetailPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

}
