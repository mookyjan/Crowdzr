package com.example.mudassirkhan.crowdzr.ui.sales;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mudassirkhan.crowdzr.HomeActivity;
import com.example.mudassirkhan.crowdzr.R;
import com.example.mudassirkhan.crowdzr.adapter.FavoriteAdapter;
import com.example.mudassirkhan.crowdzr.adapter.RecyclerViewClickedInterface;
import com.example.mudassirkhan.crowdzr.model.RequestItemModel;
import com.example.mudassirkhan.crowdzr.ui.BackableFragment;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ManageSalesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ManageSalesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private  View mParentView;
    HomeActivity homeActivity;
    ViewPager mViewPager;
    TabLayout mTabLayout;
    RecyclerView mRecyclerViewFavorite;
    FavoriteAdapter myRecyclerAdapter;
    private RecyclerViewClickedInterface mRecyclerViewClickedInterface;
    private List<RequestItemModel> mFavoriteItemModelList;


    public ManageSalesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ManageSalesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ManageSalesFragment newInstance(String param1, String param2) {
        ManageSalesFragment fragment = new ManageSalesFragment();
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
        homeActivity.toolbar.setVisibility(View.VISIBLE);
        homeActivity.toolbar.setTitle(getResources().getString(R.string.menu_manage_sales));
        homeActivity.getSupportActionBar().setHomeButtonEnabled(true);
        mParentView=inflater.inflate(R.layout.fragment_manage_sales, container, false);
        initViews();
        // Inflate the layout for this fragment
        return mParentView;
    }

    public void initViews(){
        mTabLayout=mParentView.findViewById(R.id.tab_layout_manage_sales);
        mViewPager=mParentView.findViewById(R.id.viewPagerManageSales);
        ManageSalesPagerAdapter manageSalesPagerAdapter=new ManageSalesPagerAdapter(getFragmentManager());
        mViewPager.setAdapter(manageSalesPagerAdapter);
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

//    @Override
//    public void onBackButtonPressed() {
//       // getActivity().getSupportFragmentManager().popBackStack();
//        getActivity().onBackPressed();
//    }
}
