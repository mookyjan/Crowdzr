package com.example.mudassirkhan.crowdzr.ui.sales.detail;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mudassirkhan.crowdzr.HomeActivity;
import com.example.mudassirkhan.crowdzr.R;
import com.example.mudassirkhan.crowdzr.adapter.MSRequestAdapter;
import com.example.mudassirkhan.crowdzr.model.managesales.ResponseMSRequest;
import com.example.mudassirkhan.crowdzr.model.managesales.ResponseOrderDetail;
import com.example.mudassirkhan.crowdzr.ui.BackableFragment;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.app.FragmentManager.POP_BACK_STACK_INCLUSIVE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MSDetailOrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MSDetailOrderFragment extends BackableFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View mParentView;
    RecyclerView mRecyclerViewOrder;
    List<ResponseOrderDetail> mOrderDetailList;
    MSDetailOrderAdapter mMsDetailOrderdapter;
    HomeActivity homeActivity;

    public MSDetailOrderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MSDetailOrderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MSDetailOrderFragment newInstance(String param1, String param2) {
        MSDetailOrderFragment fragment = new MSDetailOrderFragment();
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
        // Inflate the layout for this fragment
        mParentView = inflater.inflate(R.layout.fragment_msdetail_order, container, false);

        initViews();
        populateRecyclerView();
        return mParentView;
    }

    public void initViews() {
//        homeActivity=(HomeActivity)getActivity();
//        homeActivity.tabLayout.setVisibility(View.GONE);
//        homeActivity.viewPager.setVisibility(View.GONE);
//        homeActivity.toolbar.setVisibility(View.GONE);
        mRecyclerViewOrder = (RecyclerView) mParentView.findViewById(R.id.recyclerViewOrder);
        ResponseOrderDetail responseOrderDetail = new ResponseOrderDetail("24 May 2018", "10.00", "Detail of the prodcust", "New York");

        mOrderDetailList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {

            mOrderDetailList.add(responseOrderDetail);
        }
    }

    public void populateRecyclerView() {

        mMsDetailOrderdapter = new MSDetailOrderAdapter(mOrderDetailList);
        mRecyclerViewOrder.setAdapter(mMsDetailOrderdapter);
        mRecyclerViewOrder.setLayoutManager(new LinearLayoutManager(getActivity()));
        mMsDetailOrderdapter.notifyDataSetChanged();

    }

    @Override
    public void onBackButtonPressed() {

//        tellFragments();
//       getActivity().getSupportFragmentManager().popBackStack();
       // getActivity().getSupportFragmentManager().popBackStack("com.example.mudassirkhan.crowdzr.ui.sales.detail.MSDetailFragment", POP_BACK_STACK_INCLUSIVE);
       getActivity().onBackPressed();

    }

    private void tellFragments() {
        List<Fragment> fragments = getActivity().getSupportFragmentManager().getFragments();
        for (Fragment f : fragments) {
            if (f != null && f instanceof BackableFragment)
                ((BackableFragment) f).onBackButtonPressed();
        }
    }
}
