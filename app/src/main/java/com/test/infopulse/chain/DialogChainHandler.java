package com.test.infopulse.chain;

import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;

import com.test.infopulse.Settings;

public class DialogChainHandler extends ChainHandler {

    private int dialogOrderNumber;
    private Context ctx;
    private FragmentManager fragmentManager;
    private DialogFragment dialog;

    public DialogChainHandler(int dialogOrderNumber, DialogFragment dialog, Context ctx,
                              FragmentManager fragmentManager) {
        this.dialogOrderNumber = dialogOrderNumber;
        this.ctx = ctx;
        this.fragmentManager = fragmentManager;
        this.dialog = dialog;
    }

    @Override
    public boolean show() {
        if (getCurrentDialogAttempt() == dialogOrderNumber) {
            dialog.show(fragmentManager, getClass().getSimpleName());
            return true;
        }

        return showNext();
    }


    protected int getCurrentDialogAttempt() {
        return Settings.getDisplayAttemptCount(ctx);
    }
}
