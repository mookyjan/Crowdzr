package com.example.mudassirkhan.crowdzr.ui.inbox;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mudassirkhan.crowdzr.HomeActivity;
import com.example.mudassirkhan.crowdzr.R;
import com.example.mudassirkhan.crowdzr.adapter.InboxAdapter;
import com.example.mudassirkhan.crowdzr.model.InboxViewModel;
import com.example.mudassirkhan.crowdzr.ui.BackableFragment;
import com.example.mudassirkhan.crowdzr.ui.home.RequestsFragment;
import com.example.mudassirkhan.crowdzr.ui.sales.detail.MSDetailChatFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InboxFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InboxFragment extends BackableFragment implements InboxAdapter.InboxItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View mParentView;
    HomeActivity homeActivity;
    List<InboxViewModel> mInboxModelList;
    RecyclerView mRecyclerViewInbox;
    InboxAdapter.InboxItemClickListener inboxItemClickListener;
    public InboxFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InboxFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InboxFragment newInstance(String param1, String param2) {
        InboxFragment fragment = new InboxFragment();
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

         if (container!=null){
             container.removeAllViews();
         }

        homeActivity=(HomeActivity)getActivity();
        homeActivity.tabLayout.setVisibility(View.GONE);
        homeActivity.viewPager.setVisibility(View.GONE);
        homeActivity.toolbar.setVisibility(View.VISIBLE);
        homeActivity.toolbar.setTitle(getResources().getString(R.string.menu_inbox));
        // Inflate the layout for this fragment
        mParentView= inflater.inflate(R.layout.fragment_inbox, container, false);

        initViews();

        populateRecyclerView();

         return mParentView;
    }

    public void initViews(){

        mRecyclerViewInbox=(RecyclerView)mParentView.findViewById(R.id.recyclerViewInbox);
        mInboxModelList=new ArrayList<>();
        inboxItemClickListener=this;
        InboxViewModel inboxViewModel=new InboxViewModel("Chris","USB 2.0","2 Min ago","Hello ! I need about 8 designs.I am working ...");
        for (int i=0;i<8;i++){
            mInboxModelList.add(inboxViewModel);
        }
    }

    public void populateRecyclerView(){

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        InboxAdapter inboxAdapter=new InboxAdapter(mInboxModelList,inboxItemClickListener);
        mRecyclerViewInbox.setAdapter(inboxAdapter);
        mRecyclerViewInbox.setLayoutManager(linearLayoutManager);
        inboxAdapter.notifyDataSetChanged();


    }

    public void setToolbarTitle(String title) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
    }


    @Override
    public void onBackButtonPressed() {

       homeActivity.onBackPressed();
    }

    public void goToFragment(){

        Fragment fragment = new MSDetailChatFragment();
        String backStateName = fragment.getClass().getName();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.containerHome, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onInboxClicked(InboxViewModel inboxViewModel) {

        goToFragment();
    }
}
