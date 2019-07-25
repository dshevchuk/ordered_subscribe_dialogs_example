package com.test.infopulse.module.emailsubscription.chain;

import android.content.Context;
import android.content.Intent;

import com.test.infopulse.Settings;
import com.test.infopulse.module.emailsubscription.EmailSubscribeManager;

public class DialogChainHandler extends ChainHandler {

    private int dialogOrderNumber;
    private Context ctx;

    public DialogChainHandler(int dialogOrderNumber, Context ctx) {
        this.dialogOrderNumber = dialogOrderNumber;
        this.ctx = ctx;
    }

    @Override
    public boolean show() {
        if (getCurrentDialogAttempt() == dialogOrderNumber) {
            ctx.sendBroadcast(createShowDialogIntent());
            return true;
        }

        return showNext();
    }

    private Intent createShowDialogIntent() {
        Intent intent = new Intent(EmailSubscribeManager.ACTION_SHOW_DIALOG);
        intent.putExtra(EmailSubscribeManager.EXTRAS_ORDER_NUM, dialogOrderNumber);
        return intent;
    }


    protected int getCurrentDialogAttempt() {
        return Settings.getDisplayAttemptCount(ctx);
    }
}
