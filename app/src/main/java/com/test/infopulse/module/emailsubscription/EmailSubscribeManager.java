package com.test.infopulse.module.emailsubscription;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.test.infopulse.Settings;
import com.test.infopulse.module.emailsubscription.chain.ChainHandler;
import com.test.infopulse.module.emailsubscription.chain.DialogChainHandler;
import com.test.infopulse.module.emailsubscription.dialogs.BaseDialog;
import com.test.infopulse.module.emailsubscription.dialogs.BaseDialogInterface;
import com.test.infopulse.module.emailsubscription.dialogs.BaseInfoDialog;
import com.test.infopulse.module.emailsubscription.dialogs.DontMissOutDialogFragment;
import com.test.infopulse.module.emailsubscription.dialogs.DontWalkDialogFragment;
import com.test.infopulse.module.emailsubscription.dialogs.GetTheMostDialogFragment;
import com.test.infopulse.module.emailsubscription.dialogs.GreatInfoDialogFragment;
import com.test.infopulse.module.emailsubscription.dialogs.WantToSaveDialogFragment;
import com.test.infopulse.module.emailsubscription.dialogs.WeHaveMoreDialogFrogment;
import com.test.infopulse.module.emailsubscription.validation.ValidationCallback;
import com.test.infopulse.module.emailsubscription.validation.Validator;


public class EmailSubscribeManager {

    public static final String ACTION_SHOW_DIALOG = "subscription.email.action.show_dialog";
    public static final String ACTION_SHOW_CONFIRMATION_DIALOG = "subscription.email.action.show_info_dialog";
    public static final String ACTION_SHOW_MESSAGE = "subscription.email.action.show_message";
    public static final String EXTRAS_ORDER_NUM = "subscription.email.extras.order_number";
    public static final String EXTRAS_MESSAGE_TEXT = "subscription.email.extras.message_text";

    private Context ctx;
    private ChainHandler chainHandler;
    private Validator<String> emailValidator;

    public EmailSubscribeManager(Context ctx) {
        this.ctx = ctx;

        createChain();
    }

    private void createChain() {
        // Create chains
        DialogChainHandler getTheMostHandler = createChainHandler(1);
        DialogChainHandler dontMissOutHandler = createChainHandler(2);
        DialogChainHandler weHaveMoreHandler = createChainHandler(3);
        DialogChainHandler dontWalkHandler = createChainHandler(4);
        DialogChainHandler wantToSaveHandler = createChainHandler(5);

        // Order chains
        chainHandler = getTheMostHandler;
        getTheMostHandler.setNextHandler(dontMissOutHandler);
        dontMissOutHandler.setNextHandler(weHaveMoreHandler);
        weHaveMoreHandler.setNextHandler(dontWalkHandler);
        dontWalkHandler.setNextHandler(wantToSaveHandler);
    }

    public void setEmailValidator(Validator<String> validator) {
        emailValidator = validator;
    }

    public BaseDialog getDialogByNum(int dialogOrderNum) {
        BaseDialog dialog;

        switch (dialogOrderNum) {
            case 2:
                dialog = new DontMissOutDialogFragment();
                break;
            case 3:
                dialog = new WeHaveMoreDialogFrogment();
                break;
            case 4:
                dialog = new DontWalkDialogFragment();
                break;
            case 5:
                dialog = new WantToSaveDialogFragment();
                break;
            default:
                dialog = new GetTheMostDialogFragment();
        }

        dialog.setListener(getDialogListener());

        return dialog;
    }

    public BaseInfoDialog getConfirmationDialog() {
        // we have only confirmation dialog so return it
        return new GreatInfoDialogFragment();
    }

    public boolean showInvitationDialog() {
        return chainHandler.show();
    }

    private DialogChainHandler createChainHandler(int orderNumber) {
        return new DialogChainHandler(orderNumber, ctx);
    }

    private BaseDialogInterface getDialogListener() {
        return new BaseDialogInterface() {
            @Override
            public void onPositiveClick(View v) {
                String email = getDialogInputData(v);

                if (email != null && !TextUtils.isEmpty(email)) {
                    validateEmail(email);
                } else {
                    showMessage("Incorrect input data");
                }
            }

            @Override
            public void onNegativeClick(View v) {
                Settings.setDisplayAttemptCount(ctx,
                        Settings.getDisplayAttemptCount(ctx) + 1);
            }
        };
    }

    private void validateEmail(String email) {
        if (emailValidator != null) {
            emailValidator.validation(email, getEmailValidationCallback());
        }
    }

    private ValidationCallback getEmailValidationCallback() {
        return new ValidationCallback() {
            @Override
            public void onSuccess() {
                Settings.setDisplayDialog(ctx, false);
                showConfirmationDialog();
            }

            @Override
            public void onFail(String message) {
                showMessage(message);
            }

            @Override
            public void onError(Throwable t) {
                showMessage(t.getMessage());
            }
        };
    }

    private String getDialogInputData(View view) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();

        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View child = viewGroup.getChildAt(i);
            if (child instanceof EditText) {
                return ((EditText) child).getText().toString();
            }
        }

        return null;
    }

    private void showConfirmationDialog() {
        ctx.sendBroadcast(new Intent(ACTION_SHOW_CONFIRMATION_DIALOG));
    }

    private void showMessage(String message) {
        Intent intent = new Intent(ACTION_SHOW_MESSAGE);
        intent.putExtra(EmailSubscribeManager.EXTRAS_MESSAGE_TEXT, "" + message);
        ctx.sendBroadcast(intent);
    }
}
