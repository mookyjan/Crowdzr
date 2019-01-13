package com.example.mudassirkhan.crowdzr.ui.errorprompt.snackbar;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.example.mudassirkhan.crowdzr.ui.errorprompt.PromptParams;


/**
 * Created by muhammadrashid on 02/01/2018.
 */

public class SnackbarParams extends PromptParams {
    public final String message;
    public final View rootView;
    public final int duration;
    public final View.OnClickListener listener;

    public static class Builder {
        // Required parameters
        private final Context context;
        private String message;
        private View rootView;
        public View.OnClickListener listener;
        // Optional parameters - initialized to default values
        private int duration = Snackbar.LENGTH_LONG;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder rootView(View rootView) {
            this.rootView = rootView;
            return this;
        }

        public Builder duration(int duration) {
            this.duration = duration;
            return this;
        }
        public Builder listener(View.OnClickListener listener) {
            this.listener = listener;
            return this;
        }

        public SnackbarParams build() {
            return new SnackbarParams(this);
        }
    }

    public SnackbarParams(Builder builder) {
        super(builder.context);
        this.rootView = builder.rootView;
        this.duration = builder.duration;
        this.message = builder.message;
        this.listener = builder.listener;
    }
}
