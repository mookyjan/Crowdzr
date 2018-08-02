package com.example.mudassirkhan.crowdzr.ui.stack;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import android.widget.ImageView;

import com.example.mudassirkhan.crowdzr.HomeActivity;
import com.example.mudassirkhan.crowdzr.R;
import com.example.mudassirkhan.crowdzr.adapter.FavoriteAdapter;
import com.example.mudassirkhan.crowdzr.adapter.RecyclerViewClickedInterface;
import com.example.mudassirkhan.crowdzr.model.RequestItemModel;
import com.example.mudassirkhan.crowdzr.ui.BackableFragment;
import com.example.mudassirkhan.crowdzr.ui.profile.EditProfileFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyStacksFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyStacksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyStacksFragment extends BackableFragment implements RecyclerViewClickedInterface {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private  View mParentView;
    HomeActivity homeActivity;
    ImageView imgAddStack;
    RecyclerView mRecyclerViewMyStacks;
    FavoriteAdapter myRecyclerAdapter;
    private RecyclerViewClickedInterface mRecyclerViewClickedInterface;
    private List<RequestItemModel> mMyStackstItemModelList;

    public MyStacksFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyStacksFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyStacksFragment newInstance(String param1, String param2) {
        MyStacksFragment fragment = new MyStacksFragment();
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
        homeActivity.toolbar.setTitle(getResources().getString(R.string.menu_my_stacks));
        homeActivity.toolbar.setBackgroundColor(ContextCompat.getColor(getActivity(),android.R.color.transparent));
        homeActivity.getSupportActionBar().setHomeButtonEnabled(true);
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_add_request, container, false);
        mParentView = inflater.inflate(R.layout.fragment_my_stacks, container, false);
        initViews();
        populateRecyclerView();
        imgAddStack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAddStackFragment();
            }
        });
        return mParentView;
    }

    public void initViews() {
        imgAddStack=(ImageView)mParentView.findViewById(R.id.imgAddStack);
        mRecyclerViewMyStacks = (RecyclerView) mParentView.findViewById(R.id.recyclerviewMyStacks);
        mMyStackstItemModelList = new ArrayList<>();
        mRecyclerViewClickedInterface = this;
        RequestItemModel requestItemModel = new RequestItemModel("USB 2.0", "$19.0", "Expiring in 6 days 2 hour", "2000", "3000");
        for (int i = 0; i < 3; i++) {
            mMyStackstItemModelList.add(requestItemModel);
        }

    }

    public void populateRecyclerView() {
        myRecyclerAdapter = new FavoriteAdapter(getActivity(), mMyStackstItemModelList, mRecyclerViewClickedInterface);
        mRecyclerViewMyStacks.setAdapter(myRecyclerAdapter);
        mRecyclerViewMyStacks.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerAdapter.notifyDataSetChanged();
    }

    public void goToAddStackFragment(){
        Fragment fragment = new AddStackFragment();
        String backStateName = fragment.getClass().getName();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragMyStacks, fragment);
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
