package com.test.infopulse.dialogs;

public class WantToSaveDialogFragment extends BaseDialog {
    @Override
    void initTextElements() {
        mainText = "Want to Save on Your Psychic Readings?";
        subText = "Along with useful wisdom and insights, we send out subscribers special offers and promotions that save them money";
        inputHintText = "Email address";
        positiveButtonText = "Send";
        negativeButtonText = "No thank you";
    }
}
