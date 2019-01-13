package com.example.mudassirkhan.crowdzr.ui.errorprompt;

/**
 * Created by muhammadrashid on 02/01/2018.
 */

public interface IPrompt {
    void showNetworkError(PromptParams params);

    void showError(PromptParams params);

    void showSuccess(PromptParams params);

    void showInfo(PromptParams params);
}
