package com.example.mudassirkhan.crowdzr.ui.home;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mudassirkhan.crowdzr.HomeActivity;
import com.example.mudassirkhan.crowdzr.R;
import com.example.mudassirkhan.crowdzr.adapter.RecyclerViewClickedInterface;
import com.example.mudassirkhan.crowdzr.adapter.RequestAdapter;
import com.example.mudassirkhan.crowdzr.adapter.ReviewListAdapter;
import com.example.mudassirkhan.crowdzr.model.RequestItemModel;
import com.example.mudassirkhan.crowdzr.model.request.RequestReviewModel;
import com.example.mudassirkhan.crowdzr.ui.BackableFragment;
import com.example.mudassirkhan.crowdzr.ui.favorite.FavoriteFragment;
import com.example.mudassirkhan.crowdzr.ui.profile.ProfileFragment;
import com.example.mudassirkhan.crowdzr.ui.user.UserDetailFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RequestDetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RequestDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RequestDetailFragment extends BackableFragment implements RecyclerViewClickedInterface{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ImageView imgBidIcon,imgCommentIcon,imgBidUpArrow,imgCommentUpArrow,imgBidCancel,imgCommentCancel;
    Button btnBidNow,btnPlaceBid,btnCommentPost;
    TextView txtNoComments;
    Button btnPostComment;
    View parentView;
    RecyclerView mRecyclerViewBids,mRecyclerViewComments;
    ReviewListAdapter mReviewListAdapter;
    private List<RequestReviewModel> mRequestReviewModelList;
    private RecyclerViewClickedInterface mRecyclerViewClickedInterface;
    CardView cardViewUser,cardViewBidNow,cardViewBidNowLayout,cardViewPostComment,cardViewPostCommentLayout;
    private OnFragmentInteractionListener mListener;
    RequestsFragment mRequestFragment;
    public RequestDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RequestDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RequestDetailFragment newInstance(String param1, String param2) {
        RequestDetailFragment fragment = new RequestDetailFragment();
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
        if (container != null) {
            container.removeAllViews();
        }
        //mRequestFragment=RequestsFragment.newInstance();
      //  getChildFragmentManager().beginTransaction().replace(R.id.drawer_layout,mRequestFragment).addToBackStack(null).commit();
        // Inflate the layout for this fragment
        parentView=inflater.inflate(R.layout.fragment_request_detail, container, false);
        initViews(parentView);
        imgBidIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerViewBids.setVisibility(View.VISIBLE);
                imgBidIcon.setVisibility(View.GONE);
                imgBidUpArrow.setVisibility(View.VISIBLE);
                populateBidRecyclerView();
            }
        });

        imgBidUpArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgBidUpArrow.setVisibility(View.GONE);
                imgBidIcon.setVisibility(View.VISIBLE);
                mRecyclerViewBids.setVisibility(View.GONE);

            }
        });

        imgCommentIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgCommentIcon.setVisibility(View.GONE);
                imgCommentUpArrow.setVisibility(View.VISIBLE);
                mRecyclerViewComments.setVisibility(View.VISIBLE);
                txtNoComments.setVisibility(View.GONE);
                btnPostComment.setVisibility(View.VISIBLE);
                populateCommentsRecyclerView();
            }
        });

        imgCommentUpArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imgCommentUpArrow.setVisibility(View.GONE);
                imgCommentIcon.setVisibility(View.VISIBLE);
                mRecyclerViewComments.setVisibility(View.GONE);
                btnPostComment.setVisibility(View.GONE);
                txtNoComments.setVisibility(View.VISIBLE);
            }
        });

        return parentView;
    }

    public void initViews(View view){
        cardViewUser=(CardView)view.findViewById(R.id.cardViewUser);
        imgBidIcon=(ImageView)view.findViewById(R.id.img_bid_icon);
        imgBidUpArrow=(ImageView)view.findViewById(R.id.img_bid_up);
        imgCommentIcon=(ImageView)view.findViewById(R.id.img_comments_icon);
        imgCommentUpArrow=(ImageView)view.findViewById(R.id.img_comments_up);
        btnBidNow=(Button)view.findViewById(R.id.btnBidNow);
        mRecyclerViewBids=(RecyclerView)view.findViewById(R.id.recyclerViewBids);
        mRecyclerViewComments=(RecyclerView)view.findViewById(R.id.recyclerViewComments);
        txtNoComments=(TextView)view.findViewById(R.id.txtNoComments);
        btnPostComment=(Button)view.findViewById(R.id.btnPostComment);
        cardViewBidNow=(CardView)view.findViewById(R.id.cardViewBids);
        cardViewBidNowLayout=(CardView)view.findViewById(R.id.cardViewBidNow);
        cardViewPostComment=(CardView)view.findViewById(R.id.cardViewPostComment);
        cardViewPostCommentLayout=(CardView)view.findViewById(R.id.cardViewPostCommentLayout);
        btnPlaceBid=(Button)view.findViewById(R.id.btnPlaceBid);
        imgBidCancel=(ImageView)view.findViewById(R.id.imgCancel);
        btnPostComment=(Button)view.findViewById(R.id.btnPostComment);
        btnCommentPost=(Button)view.findViewById(R.id.btnCommentPost);
        imgCommentCancel=(ImageView) view.findViewById(R.id.imgCommentCancel);
        mRecyclerViewClickedInterface=this;
        mRequestReviewModelList=new ArrayList<>();
        RequestReviewModel requestReviewModel=new RequestReviewModel(R.drawable.profile_pic,"Jennifer alley",4.3f,"I will sell you this usb in $10 with brand new edition");
        for (int i=0;i<2;i++){
            mRequestReviewModelList.add(requestReviewModel);
        }

        cardViewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToUserFragment();
            }
        });

         btnBidNow.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 cardViewBidNow.setVisibility(View.GONE);
                 cardViewBidNowLayout.setVisibility(View.VISIBLE);
             }
         });

        btnPostComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cardViewPostComment.setVisibility(View.GONE);
                cardViewPostCommentLayout.setVisibility(View.VISIBLE);
            }
        });

        btnPlaceBid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardViewBidNowLayout.setVisibility(View.GONE);
                cardViewBidNow.setVisibility(View.VISIBLE);
            }
        });

        imgBidCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardViewBidNowLayout.setVisibility(View.GONE);
                cardViewBidNow.setVisibility(View.VISIBLE);
            }
        });

        btnCommentPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardViewPostCommentLayout.setVisibility(View.GONE);
                cardViewPostComment.setVisibility(View.VISIBLE);
            }
        });

        imgCommentCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardViewPostCommentLayout.setVisibility(View.GONE);
                cardViewPostComment.setVisibility(View.VISIBLE);
            }
        });

    }

    public void populateBidRecyclerView(){
        mReviewListAdapter=new ReviewListAdapter(getActivity(),mRequestReviewModelList,mRecyclerViewClickedInterface);
        mRecyclerViewBids.setAdapter(mReviewListAdapter);
        mRecyclerViewBids.setLayoutManager(new LinearLayoutManager(getActivity()));
        mReviewListAdapter.notifyDataSetChanged();
    }

    public void populateCommentsRecyclerView(){
        mReviewListAdapter=new ReviewListAdapter(getActivity(),mRequestReviewModelList,mRecyclerViewClickedInterface);
        mRecyclerViewComments.setAdapter(mReviewListAdapter);
        mRecyclerViewComments.setLayoutManager(new LinearLayoutManager(getActivity()));
        mReviewListAdapter.notifyDataSetChanged();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
        //mListener = null;
    }

    @Override
    public void onListItemClicked(int position, View view) {

        goToUserFragment();
    }

    public void goToUserFragment(){
        Fragment fragment = new UserDetailFragment();
        String backStateName = fragment.getClass().getName();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragRequestDetail, fragment);
        fragmentTransaction.addToBackStack(backStateName);
        fragmentTransaction.commit();

    }

    @Override
    public void onBackButtonPressed() {
       // goToUserFragment();
        Intent myIntent=new Intent(getActivity(), HomeActivity.class);
        startActivity(myIntent);
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
