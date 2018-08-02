package com.example.mudassirkhan.crowdzr.ui.profile;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.mudassirkhan.crowdzr.HomeActivity;
import com.example.mudassirkhan.crowdzr.R;
import com.example.mudassirkhan.crowdzr.adapter.FavoriteAdapter;
import com.example.mudassirkhan.crowdzr.adapter.HistoryAdapter;
import com.example.mudassirkhan.crowdzr.adapter.RecyclerViewClickedInterface;
import com.example.mudassirkhan.crowdzr.model.HistoryItemModel;
import com.example.mudassirkhan.crowdzr.model.RequestItemModel;
import com.example.mudassirkhan.crowdzr.ui.BackableFragment;
import com.example.mudassirkhan.crowdzr.ui.user.UserDetailFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends BackableFragment implements RecyclerViewClickedInterface {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    HomeActivity homeActivity;
    View mParentView;
    ImageView imgEditProfile;
    FloatingActionButton btnInbox;
    RecyclerView mRecyclerViewHistory;
    HistoryAdapter myRecyclerAdapter;
    private RecyclerViewClickedInterface mRecyclerViewClickedInterface;
    private List<HistoryItemModel> mHistoryItemModelList;
    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        //to hide the option menu enable this
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }

        homeActivity=(HomeActivity)getActivity();
        homeActivity.tabLayout.setVisibility(View.GONE);
        homeActivity.viewPager.setVisibility(View.GONE);
        homeActivity.toolbar.setVisibility(View.VISIBLE);
        homeActivity.toolbar.setTitle(getResources().getString(R.string.menu_profile));
        homeActivity.toolbar.setBackground(null);
        //homeActivity.toolbar.setBackgroundColor(ContextCompat.getColor(getActivity(),android.R.color.transparent));
        homeActivity.getSupportActionBar().setHomeButtonEnabled(true);
        // Inflate the layout for this fragment
        mParentView=inflater.inflate(R.layout.fragment_profile, container, false);
        initViews();
        populateRecyclerView();
        imgEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            goToEditUserFragment();
            }
        });
        return mParentView;
    }

    public void initViews(){
        mRecyclerViewHistory=(RecyclerView)mParentView.findViewById(R.id.recyclerviewProfile);
        imgEditProfile=(ImageView)mParentView.findViewById(R.id.imgEdit);
        btnInbox=(FloatingActionButton)mParentView.findViewById(R.id.img_inbox);
        mHistoryItemModelList=new ArrayList<>();
        mRecyclerViewClickedInterface=this;
        HistoryItemModel requestItemModel=new HistoryItemModel("Chris Jones","14 May 2018 10:00 Am","Pending","$100.00");
        for (int i=0;i<2;i++){
            mHistoryItemModelList.add(requestItemModel);
        }

    }
    public void populateRecyclerView(){
        myRecyclerAdapter=new HistoryAdapter(getActivity(),mHistoryItemModelList,mRecyclerViewClickedInterface);
        mRecyclerViewHistory.setAdapter(myRecyclerAdapter);
        mRecyclerViewHistory.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerAdapter.notifyDataSetChanged();
    }

    public void goToEditUserFragment(){
        Fragment fragment = new EditProfileFragment();
        String backStateName = fragment.getClass().getName();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.profileFrag, fragment);
        fragmentTransaction.addToBackStack(backStateName);
        fragmentTransaction.commit();
    }

    @Override
    public void onListItemClicked(int position, View view) {

    }

    /**
     * to hide the search bar menu
     * @param menu
     */

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_search).setVisible(false);
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onBackButtonPressed() {

        Intent myIntent=new Intent(getActivity(), HomeActivity.class);
        startActivity(myIntent);
        getActivity().finish();
    }
}
