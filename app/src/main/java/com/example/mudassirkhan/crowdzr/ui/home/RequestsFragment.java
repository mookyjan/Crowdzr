package com.example.mudassirkhan.crowdzr.ui.home;

import android.content.Context;
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
import android.widget.Toast;

import com.example.mudassirkhan.crowdzr.HomeActivity;
import com.example.mudassirkhan.crowdzr.R;
import com.example.mudassirkhan.crowdzr.SwingDetector;
import com.example.mudassirkhan.crowdzr.model.RequestItemModel;
import com.example.mudassirkhan.crowdzr.adapter.RecyclerViewClickedInterface;
import com.example.mudassirkhan.crowdzr.adapter.RequestAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RequestsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RequestsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RequestsFragment extends Fragment implements RecyclerViewClickedInterface{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    HomeActivity homeActivity;
    View requestFragmentLayout;
    RecyclerView mRecyclerViewReviewList;
    RequestAdapter myRecyclerAdapter;
    private RecyclerViewClickedInterface mRecyclerViewClickedInterface;
    private OnFragmentInteractionListener mListener;
    private List<RequestItemModel> mRequestItemModelList;
    SwingDetector swingDetector;
    public RequestsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment RequestsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RequestsFragment newInstance() {
        RequestsFragment fragment = new RequestsFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
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
      //  setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        requestFragmentLayout=inflater.inflate(R.layout.fragment_requests, container, false);
        initViews();
        populateRecyclerView();

        // Inflate the layout for this fragment
        return requestFragmentLayout;
    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public void initViews(){
        //requestFragmentLayout=requestFragmentLayout.findViewById(R.id.recyclerViewRequest);

        mRecyclerViewReviewList=requestFragmentLayout.findViewById(R.id.recyclerViewRequest);
        mRequestItemModelList=new ArrayList<>();
        mRecyclerViewClickedInterface=this;
        RequestItemModel requestItemModel=new RequestItemModel("USB 2.0","$19.0","Expiring in 2 hours 5 minute","2000","3000");
        for (int i=0;i<8;i++){
            mRequestItemModelList.add(requestItemModel);
        }
    }

    public void populateRecyclerView(){
        myRecyclerAdapter=new RequestAdapter(getActivity(),mRequestItemModelList,mRecyclerViewClickedInterface);
        mRecyclerViewReviewList.setAdapter(myRecyclerAdapter);
        mRecyclerViewReviewList.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

//    /**
//     * to hide the search bar menu
//     * @param menu
//     */
//
//    @Override
//    public void onPrepareOptionsMenu(Menu menu) {
//        menu.findItem(R.id.action_search).setVisible(false);
//        super.onPrepareOptionsMenu(menu);
//    }

    @Override
    public void onListItemClicked(int position, View view) {

        Toast.makeText(getActivity(),"clicked "+position,Toast.LENGTH_SHORT).show();

        Fragment fragment = new RequestDetailFragment();
        String backStateName = fragment.getClass().getName();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.drawer_layout, fragment);
        fragmentTransaction.addToBackStack(backStateName);
        fragmentTransaction.commit();
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
