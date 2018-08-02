package com.example.mudassirkhan.crowdzr.ui.sales;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mudassirkhan.crowdzr.R;
import com.example.mudassirkhan.crowdzr.adapter.MSRequestAdapter;
import com.example.mudassirkhan.crowdzr.adapter.RequestAdapter;
import com.example.mudassirkhan.crowdzr.model.managesales.ResponseMSRequest;
import com.example.mudassirkhan.crowdzr.ui.BackableFragment;
import com.example.mudassirkhan.crowdzr.ui.sales.detail.MSDetailFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MSRequestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MSRequestFragment extends BackableFragment implements MSRequestAdapter.OnItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

   private View mParentView;
   private MSRequestAdapter mMsRequestAdapter;
   RecyclerView mRecyclerViewMSRequest;
   List<ResponseMSRequest> mRequestMSRequestList;
   MSRequestAdapter.OnItemClickListener mOnItemClickListener;
    public MSRequestFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MSRequestFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MSRequestFragment newInstance(String param1, String param2) {
        MSRequestFragment fragment = new MSRequestFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mParentView=inflater.inflate(R.layout.fragment_msrequest, container, false);

        initViews();
        populateRecyclerView();
        return mParentView;
    }

    public void initViews(){
        mRecyclerViewMSRequest=(RecyclerView)mParentView.findViewById(R.id.recyclerViewMSRequests);
        mOnItemClickListener=this;

        ResponseMSRequest responseMSRequest=new ResponseMSRequest("ALiza","USB 2.0","24 May 2018","$15.00");

        mRequestMSRequestList=new ArrayList<>();
        for (int i=0;i<3;i++){

            mRequestMSRequestList.add(responseMSRequest);
        }
    }

    public void populateRecyclerView(){

            mMsRequestAdapter=new MSRequestAdapter(mRequestMSRequestList,mOnItemClickListener);
            mRecyclerViewMSRequest.setAdapter(mMsRequestAdapter);
            mRecyclerViewMSRequest.setLayoutManager(new LinearLayoutManager(getActivity()));
            mMsRequestAdapter.notifyDataSetChanged();

    }

    @Override
    public void onItemClick(ResponseMSRequest responseMSRequest) {

        MSDetailFragment msDetailFragment=new MSDetailFragment();
        goToFragment(msDetailFragment);
    }

    public void goToFragment(Fragment fragmentArg){

        Fragment fragment = fragmentArg;
        String backStateName = fragment.getClass().getName();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.drawer_layout, fragment);
        fragmentTransaction.addToBackStack(backStateName);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackButtonPressed() {
        getActivity().onBackPressed();
    }
}
