package com.example.mudassirkhan.crowdzr.ui.errorprompt.toast;

import android.widget.Toast;

import com.example.mudassirkhan.crowdzr.ui.errorprompt.IPrompt;
import com.example.mudassirkhan.crowdzr.ui.errorprompt.PromptParams;


/**
 * Created by muhammadrashid on 02/01/2018.
 */

public class ToastPrompt implements IPrompt {
    @Override
    public void showNetworkError(PromptParams params) {
        show(params);
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
        ToastParams toastPromptParams = (ToastParams)params;
        Toast.makeText(toastPromptParams.context,toastPromptParams.message, toastPromptParams.duration).show();
    }
}
