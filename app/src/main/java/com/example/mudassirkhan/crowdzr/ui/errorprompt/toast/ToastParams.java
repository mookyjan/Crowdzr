package com.example.mudassirkhan.crowdzr.ui.errorprompt.toast;

import android.content.Context;
import android.widget.Toast;

import com.example.mudassirkhan.crowdzr.ui.errorprompt.PromptParams;


/**
 * Created by muhammadrashid on 02/01/2018.
 */

public class ToastParams extends PromptParams {
    public final String message;
    public final int duration;

    public static class Builder {
        // Required parameters
        private final Context context;
        private final String message;
        //Optional parameters
        private int duration = Toast.LENGTH_SHORT;

        public Builder(Context context, String message) {
            this.context = context;
            this.message = message;
        }

        public Builder duration(int duration) {
            this.duration = duration;
            return this;
        }

        public ToastParams build() {
            return new ToastParams(this);
        }
    }

    private ToastParams(Builder builder) {
        super(builder.context);
        this.duration = builder.duration;
        this.message = builder.message;
    }
}

