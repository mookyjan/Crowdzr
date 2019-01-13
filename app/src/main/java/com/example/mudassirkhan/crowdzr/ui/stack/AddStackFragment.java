package com.example.mudassirkhan.crowdzr.ui.stack;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mudassirkhan.crowdzr.HomeActivity;
import com.example.mudassirkhan.crowdzr.R;
import com.example.mudassirkhan.crowdzr.api.CrowdZrServiceProvider;
import com.example.mudassirkhan.crowdzr.api.eventmessages.BaseResponse;
import com.example.mudassirkhan.crowdzr.api.prefs.AppPreferencesHelper;
import com.example.mudassirkhan.crowdzr.api.request.PostRequestService;
import com.example.mudassirkhan.crowdzr.model.login.LoginResponse;
import com.example.mudassirkhan.crowdzr.model.request.PostRequestResponse;
import com.example.mudassirkhan.crowdzr.ui.errorprompt.PromptProvider;
import com.example.mudassirkhan.crowdzr.ui.errorprompt.snackbar.SnackbarParams;
import com.example.mudassirkhan.crowdzr.ui.errorprompt.snackbar.SnackbarPrompt;
import com.example.mudassirkhan.crowdzr.util.AppConstants;
import com.example.mudassirkhan.crowdzr.util.ConnectivityUtils;
import com.example.mudassirkhan.crowdzr.util.TextViewDatePicker;
import com.example.mudassirkhan.crowdzr.viewModel.PostRequestViewModel;
import com.google.gson.JsonObject;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddStackFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddStackFragment extends Fragment {

    public static final String TAG=AddStackFragment.class.getSimpleName();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    HomeActivity homeActivity;
    View mParentView;
    PostRequestViewModel mPostRequestViewModel;
    AVLoadingIndicatorView mProgressView;
    @BindView(R.id.txt_item_title)
    EditText etTitle;
    @BindView(R.id.txt_descreption)
    EditText etDescription;
    @BindView(R.id.txt_hint_expiring)
    TextView txtExpiryDate;
    @BindView(R.id.seekBarPrice)
    SeekBar seekBarPrice;
    @BindView(R.id.editTags)
    EditText etTags;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;
    @BindView(R.id.txt_user_name)
    TextView txtUserName;
    @BindView(R.id.img_profile)
    ImageView imgProfile;
    public AddStackFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddStackFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddStackFragment newInstance(String param1, String param2) {
        AddStackFragment fragment = new AddStackFragment();
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
        homeActivity.toolbar.setVisibility(View.GONE);
        homeActivity.toolbar.setTitle(getResources().getString(R.string.menu_profile));
        homeActivity.toolbar.setBackground(null);
        //homeActivity.toolbar.setBackgroundColor(ContextCompat.getColor(getActivity(),android.R.color.transparent));
        homeActivity.getSupportActionBar().setHomeButtonEnabled(true);

        mParentView=inflater.inflate(R.layout.fragment_add_stack, container, false);
        mProgressView = mParentView.findViewById(R.id.progress_bar_post_request);

        ButterKnife.bind(this,mParentView);

        initViews();
        initViewModel();

        // Inflate the layout for this fragment
        return mParentView;
    }

    private void initViews(){

        txtUserName.setText(AppPreferencesHelper.get().getCurrentUserName());

                Glide.with(getActivity())
                .load(AppPreferencesHelper.get().getCurrentUserProfilePicUrl())
                .into(imgProfile);

                txtExpiryDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        TextViewDatePicker editTextDatePicker = new TextViewDatePicker(context, myEditText, minDate, maxDate);
                        TextViewDatePicker editTextDatePicker = new TextViewDatePicker(getActivity(), txtExpiryDate);
                    }
                });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  title,description,date,tags;
                int price=-1;
                title=etTitle.getText().toString();
                description=etDescription.getText().toString();
                tags=etTags.getText().toString();
                 price=seekBarPrice.getProgress();
//                postRequest("USB","new product in market",50,"23 Dec 2018","Usb");
                date=txtExpiryDate.getText().toString();
                if (validatePostRequestData(title,description,date,tags,price)){
                    postRequest(title,description,price,date,tags);
                }else {
                    Toast.makeText(getActivity(),"Please fill the detail ",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private boolean validatePostRequestData(String tittle,String description,String date,String tags,int price){

        if (tittle.isEmpty()){
            etTitle.setError(getString(R.string.error_field_required));
            return false;
        }
        if (description.isEmpty()){
            etDescription.setError(getString(R.string.error_field_required));
            return false;
        }
        if (date.isEmpty()){
            txtExpiryDate.setError(getString(R.string.error_field_required));
            return false;
        }
        if (tags.isEmpty()){
            etTags.setError(getString(R.string.error_field_required));
            return false;
        }
        if (price==-1){
            return false;
        }
        return true;

    }

    private void initViewModel(){
        mPostRequestViewModel= ViewModelProviders.of(this).get(PostRequestViewModel.class);
    }


    private void observeUpdatePosts(){

        mPostRequestViewModel.getObservablePostRequestResponse().observe(this, new Observer<PostRequestResponse>() {
            @Override
            public void onChanged(@Nullable PostRequestResponse postRequestResponse) {
                Log.d(TAG,"add response "+postRequestResponse);
                if (postRequestResponse != null) {
                    showProgress(false);
                    if (postRequestResponse.isNetworkError()) {

                        showNetworkError();
                    } else if (postRequestResponse.getStatus() == AppConstants.STATUS_CODE_SUCCESS) {
                        if (postRequestResponse.getStatus() != BaseResponse.FAILURE_STATUS) {

                            Log.d("request",""+postRequestResponse.getResponse());
//                            integrateDataWithUI();

                        }
                    } else if (postRequestResponse.getStatus() == AppConstants.STATUS_CODE_FAILED) {
                        showNetworkError();
                    }
                } else {
                    showProgress(false);
                    showNetworkError();
                }
            }

        });
    }

    private void postRequest(String title,String content,int price,String date,String tags){

        PostRequestService postRequestService= CrowdZrServiceProvider.get().postRequest();
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("title",title);
        jsonObject.addProperty("content",content);
        jsonObject.addProperty("price",price);
        jsonObject.addProperty("expiry",date);
        jsonObject.addProperty("tags",tags);
        showProgress(true);
        postRequestInfo(jsonObject);

    }

    private void postRequestInfo(JsonObject jsonObjectPostRequest){
        if (ConnectivityUtils.isConnected(getActivity())) {
            showProgress(true);
          LiveData<PostRequestResponse> postRequestViewModelLiveData=  mPostRequestViewModel.postRequest(jsonObjectPostRequest);

          postRequestViewModelLiveData.observe(this, new Observer<PostRequestResponse>() {
              @Override
              public void onChanged(@Nullable PostRequestResponse postRequestResponse) {
                  Log.d(TAG,"add response "+postRequestResponse);
                  if (postRequestResponse != null) {
                      showProgress(false);
                      if (postRequestResponse.isNetworkError()) {
                          showNetworkError();
                      } else if (postRequestResponse.getErrorMessage()==null) {
                         // if (postRequestResponse.getStatus() != BaseResponse.FAILURE_STATUS) {

                              Log.d("request",""+postRequestResponse.getResponse());
//                            integrateDataWithUI();
                              Toast.makeText(getActivity(),"response success "+postRequestResponse.getResponse(),Toast.LENGTH_SHORT).show();
                            //go back to previous fragment
                             getFragmentManager().popBackStack();
                        //  }
                      } else if (postRequestResponse.getStatus() == AppConstants.STATUS_CODE_FAILED) {
                          showNetworkError();
                      }
                  } else {
                      showProgress(false);
                      //showNetworkError();
                  }
              }


          });
          //observeUpdatePosts();
        } else {
            showNetworkError();
        }
    }

    public void showNetworkError() {
        PromptProvider.get()
                .provide(SnackbarPrompt.class)
                .showNetworkError(new SnackbarParams.Builder(getActivity())
                        .rootView(mParentView)
                        .build());
    }


    void showProgress(boolean show) {
        if (show) {
            mProgressView.smoothToShow();
        } else {
            if (mProgressView.isShown())
                mProgressView.smoothToHide();
        }
    }


}
