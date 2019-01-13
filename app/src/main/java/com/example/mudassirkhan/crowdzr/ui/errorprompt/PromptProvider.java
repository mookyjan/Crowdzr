package com.example.mudassirkhan.crowdzr.ui.errorprompt;

/**
 * Created by muhammadrashid on 02/01/2018.
 */

public class PromptProvider {
    private static PromptProvider mInstance;
    private IPrompt mPrompt;
    private PromptProvider(){

    }
    public static synchronized PromptProvider get() {
        if (mInstance == null) {
            mInstance = new PromptProvider();
        }
        return mInstance;
    }

    public <T extends IPrompt> T provide(Class<T> clazz)  {
        if (mPrompt == null) {
            try {
                mPrompt = clazz.newInstance();
            }catch (IllegalAccessException e) {
                // Ignore
            } catch (InstantiationException e) {
                // Ignore
            }
        }

        return  (T) mPrompt;
    }
}
