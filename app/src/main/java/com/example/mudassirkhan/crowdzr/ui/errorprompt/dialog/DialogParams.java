package com.example.mudassirkhan.crowdzr.ui.errorprompt.dialog;

import android.content.Context;

import com.example.mudassirkhan.crowdzr.ui.errorprompt.PromptParams;


/**
 * Created by muhammadrashid on 02/01/2018.
 */

public class DialogParams extends PromptParams {
    public final String message;
    public final String title;
    public final String buttonText;
    public final boolean cancelable;
    public static class Builder {
        // Required parameters
        private final Context context;
        private String message;
        // Optional parameters - initialized to default values
        private String title;
        private String positiveButtonText;
        private boolean cancelable = true;

        public Builder(Context context) {
            this.context = context;
        }
        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder positiveButtonText(String text) {
            this.positiveButtonText = text;
            return this;
        }
        public Builder message(String text) {
            this.message = text;
            return this;
        }

        public Builder cancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }
        public DialogParams build() {
            return new DialogParams(this);
        }
    }
    private DialogParams(Builder builder) {
        super(builder.context);
        title = builder.title;
        buttonText = builder.positiveButtonText;
        cancelable = builder.cancelable;
        message = builder.message;
    }
}
