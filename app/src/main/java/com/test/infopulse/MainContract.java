package com.test.infopulse;

import android.support.v4.app.DialogFragment;

public class MainContract {

    public interface ViewModel {
        void initEmailSubscription();
        DialogFragment getDialogByNum(int orderNum);
        DialogFragment getConfirmationDialog();
    }

    public interface View {
    }
}
