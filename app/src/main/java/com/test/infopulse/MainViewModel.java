package com.test.infopulse;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import com.test.infopulse.module.emailsubscription.EmailSubscribeManager;
import com.test.infopulse.module.emailsubscription.validation.EmailHttpValidator;

public class MainViewModel extends AndroidViewModel implements MainContract.ViewModel {

    private EmailSubscribeManager emailSubscribeManager;
    private boolean wasDialogShowed = false;

    public MainViewModel(@NonNull Application application) {
        super(application);

        emailSubscribeManager = new EmailSubscribeManager(getApplication().getApplicationContext());
        emailSubscribeManager.setEmailValidator(new EmailHttpValidator());
    }

    @Override
    public void initEmailSubscription() {
        if (Settings.isDisplayDialog(getApplication().getApplicationContext()) && !wasDialogShowed) {
            boolean showDialog = emailSubscribeManager.showInvitationDialog();
            wasDialogShowed = true;

            if (!showDialog) {
                // if we don't match to any handler in chain - stop trying to display dialogs
                Settings.setDisplayDialog(getApplication().getApplicationContext(), false);
            }
        }
    }

    @Override
    public DialogFragment getDialogByNum(int orderNum) {
        return emailSubscribeManager.getDialogByNum(orderNum);
    }

    @Override
    public DialogFragment getConfirmationDialog() {
        return emailSubscribeManager.getConfirmationDialog();
    }


}
