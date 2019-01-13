package com.example.mudassirkhan.crowdzr.ui.request;


import android.content.Intent;
import android.os.Bundle;
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
import android.widget.Button;
import android.widget.ImageView;

import com.example.mudassirkhan.crowdzr.HomeActivity;
import com.example.mudassirkhan.crowdzr.R;
import com.example.mudassirkhan.crowdzr.adapter.FavoriteAdapter;
import com.example.mudassirkhan.crowdzr.adapter.RecyclerViewClickedInterface;
import com.example.mudassirkhan.crowdzr.model.RequestItemModel;
import com.example.mudassirkhan.crowdzr.ui.BackableFragment;
import com.example.mudassirkhan.crowdzr.ui.stack.AddStackFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddRequestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddRequestFragment  extends BackableFragment implements RecyclerViewClickedInterface {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private  View mParentView;
    HomeActivity homeActivity;
    RecyclerView mRecyclerViewMyRequest;
    FavoriteAdapter myRecyclerAdapter;
    private RecyclerViewClickedInterface mRecyclerViewClickedInterface;
    private List<RequestItemModel> mMyRequestItemModelList;
    ImageView btnAddRequest;
    public AddRequestFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddRequestFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddRequestFragment newInstance(String param1, String param2) {
        AddRequestFragment fragment = new AddRequestFragment();
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
        setHasOptionsMenu(true);
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
        homeActivity.toolbar.setTitle(getResources().getString(R.string.menu_myrequests));
        homeActivity.toolbar.setBackgroundColor(ContextCompat.getColor(getActivity(),android.R.color.transparent));
        homeActivity.getSupportActionBar().setHomeButtonEnabled(true);
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_add_request, container, false);
        mParentView = inflater.inflate(R.layout.fragment_add_request, container, false);
        initViews();
        populateRecyclerView();
        return mParentView;
    }

    public void initViews() {
        mRecyclerViewMyRequest = (RecyclerView) mParentView.findViewById(R.id.recyclerviewMyRequests);
        btnAddRequest=mParentView.findViewById(R.id.btnAddRequest);
        mMyRequestItemModelList = new ArrayList<>();
        mRecyclerViewClickedInterface = this;
        RequestItemModel requestItemModel = new RequestItemModel("USB 2.0", "$19.0", "Expiring in 6 days 2 hour", "2000", "3000");
        for (int i = 0; i < 3; i++) {
            mMyRequestItemModelList.add(requestItemModel);
        }

        btnAddRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToPostRequestFrag();
            }
        });

    }

    private void goToPostRequestFrag(){
        Fragment fragment = new AddStackFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragAddRequest, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void populateRecyclerView() {
        myRecyclerAdapter = new FavoriteAdapter(getActivity(), mMyRequestItemModelList, mRecyclerViewClickedInterface);
        mRecyclerViewMyRequest.setAdapter(myRecyclerAdapter);
        mRecyclerViewMyRequest.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerAdapter.notifyDataSetChanged();
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
//        Fragment fragment = new RequestsFragment();
//        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//        String backStateName = fragment.getClass().getName();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.fragFavorite, fragment);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();
        Intent intent = new Intent(getActivity(), HomeActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}