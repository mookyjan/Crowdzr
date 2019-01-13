package com.example.mudassirkhan.crowdzr.ui.errorprompt.dialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

import com.example.mudassirkhan.crowdzr.R;
import com.example.mudassirkhan.crowdzr.ui.errorprompt.IPrompt;
import com.example.mudassirkhan.crowdzr.ui.errorprompt.PromptParams;


/**
 * Created by muhammadrashid on 02/01/2018.
 */

public class DialogPrompt implements IPrompt {
    @Override
    public void showNetworkError(final PromptParams params) {
        AlertDialog.Builder builder = new AlertDialog.Builder(params.context)
        .setTitle(params.context.getString(R.string.network_error))
        .setMessage(params.context.getString(R.string.network_fail_message))
        .setCancelable(true)
        .setPositiveButton(params.context.getString(R.string.settings),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        params.context.startActivity(new Intent(Settings.ACTION_SETTINGS));

                    }
                })
         .setNegativeButton(params.context.getString(R.string.cancel),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

        AlertDialog dialog = builder.create();
        dialog.show();
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
        DialogParams dialogPromptParams = (DialogParams) params;
        AlertDialog.Builder builder = new AlertDialog.Builder(params.context);
        if(!TextUtils.isEmpty(dialogPromptParams.title)) {
            builder.setTitle(dialogPromptParams.title);
        }
        builder.setMessage(dialogPromptParams.message);
        if(!TextUtils.isEmpty(dialogPromptParams.title)) {
            builder.setTitle(dialogPromptParams.title);
        }
        builder.setCancelable(dialogPromptParams.cancelable);
        CharSequence buttonText = TextUtils.isEmpty(dialogPromptParams.buttonText)?params.context.getString(R.string.ok)
                :dialogPromptParams.buttonText;

        builder.setPositiveButton((buttonText),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();


                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
