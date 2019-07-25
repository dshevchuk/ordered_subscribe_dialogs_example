package com.test.infopulse.module.emailsubscription.dialogs;

public class DontMissOutDialogFragment extends BaseDialog {
    @Override
    void initTextElements() {
        mainText = "Don't Miss Out";
        subText = "Subscribe for special offers, promotions, tidbits of wisdom and personal horoscopes!";
        inputHintText = "Email address";
        positiveButtonText = "Send";
        negativeButtonText = "Maybe later";
    }
}
