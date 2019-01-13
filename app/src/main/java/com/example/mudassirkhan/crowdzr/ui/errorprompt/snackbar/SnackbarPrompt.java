package com.example.mudassirkhan.crowdzr.ui.errorprompt.snackbar;

import android.content.Intent;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

import com.example.mudassirkhan.crowdzr.R;
import com.example.mudassirkhan.crowdzr.ui.errorprompt.IPrompt;
import com.example.mudassirkhan.crowdzr.ui.errorprompt.PromptParams;


/**
 * Created by muhammadrashid on 02/01/2018.
 */

public class SnackbarPrompt implements IPrompt {

    public void show(){

    }

    @Override
    public void showNetworkError(final PromptParams params) {
        final View rootView = ((SnackbarParams)params).rootView;
        if(rootView != null) {
            final Snackbar snackbar = Snackbar.make(((SnackbarParams) params).rootView
                    , rootView.getContext().getString(R.string.network_fail_message), ((SnackbarParams) params).duration);

            snackbar.setAction(rootView.getContext().getString(R.string.settings), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    snackbar.dismiss();
                    rootView.getContext().startActivity(new Intent(Settings.ACTION_SETTINGS));
                   // ((SnackbarParams) params).listener.onClick(v);
                }
            });
            snackbar.setActionTextColor(rootView.getContext().getResources().getColor(R.color.colorAccent));

            snackbar.show();
            View snackbarView = snackbar.getView();
            TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setMaxLines(5);
        }
    }

    @Override
    public void showError(PromptParams params) {
        show(params);
    }

    @Override
    public void showSuccess(PromptParams params) {
        show(params);
    }

    @Override
    public void showInfo(PromptParams params) {
        show(params);
    }

    private void show(PromptParams params){
        SnackbarParams snackbarPromptParams = ((SnackbarParams)params);

        final Snackbar snackbar = Snackbar.make(((SnackbarParams) params).rootView
                , snackbarPromptParams.message, ((SnackbarParams) params).duration);

        snackbar.show();
        View snackbarView = snackbar.getView();
        TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setMaxLines(5);

    }
}
