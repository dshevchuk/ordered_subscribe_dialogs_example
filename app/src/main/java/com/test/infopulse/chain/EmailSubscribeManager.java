package com.test.infopulse.chain;

import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.test.infopulse.App;
import com.test.infopulse.Settings;
import com.test.infopulse.dialogs.BaseDialog;
import com.test.infopulse.dialogs.BaseDialogInterface;
import com.test.infopulse.dialogs.BaseInfoDialog;
import com.test.infopulse.dialogs.DontMissOutDialogFragment;
import com.test.infopulse.dialogs.DontWalkDialogFragment;
import com.test.infopulse.dialogs.GetTheMostDialogFragment;
import com.test.infopulse.dialogs.GreatInfoDialogFragment;
import com.test.infopulse.dialogs.WantToSaveDialogFragment;
import com.test.infopulse.dialogs.WeHaveMoreDialogFrogment;
import com.test.infopulse.http.pojo.EmailValidationResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmailSubscribeManager {

    private Context ctx;
    private FragmentManager fragmentManager;
    private ChainHandler chainHandler;

    public EmailSubscribeManager(Context ctx, FragmentManager fragmentManager) {
        this.ctx = ctx;
        this.fragmentManager = fragmentManager;

        createChain();
    }

    private void createChain() {
        // Create chains
        BaseDialog dialog = new GetTheMostDialogFragment();
        dialog.setListener(getDialogListener());
        DialogChainHandler getTheMostHandler = createChainHandler(1, dialog);

        dialog = new DontMissOutDialogFragment();
        dialog.setListener(getDialogListener());
        DialogChainHandler dontMissOutHandler = createChainHandler(2, dialog);

        dialog = new WeHaveMoreDialogFrogment();
        dialog.setListener(getDialogListener());
        DialogChainHandler weHaveMoreHandler = createChainHandler(3, dialog);

        dialog = new DontWalkDialogFragment();
        dialog.setListener(getDialogListener());
        DialogChainHandler dontWalkHandler = createChainHandler(4, dialog);

        dialog = new WantToSaveDialogFragment();
        dialog.setListener(getDialogListener());
        DialogChainHandler wantToSaveHandler = createChainHandler(5, dialog);

        // Order chains
        chainHandler = getTheMostHandler;
        getTheMostHandler.setNextHandler(dontMissOutHandler);
        dontMissOutHandler.setNextHandler(weHaveMoreHandler);
        weHaveMoreHandler.setNextHandler(dontWalkHandler);
        dontWalkHandler.setNextHandler(wantToSaveHandler);
    }

    public boolean showDialog() {
        return chainHandler.show();
    }

    private DialogChainHandler createChainHandler(int orderNumber, DialogFragment dialog) {
        return new DialogChainHandler(orderNumber, dialog, ctx, fragmentManager);
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
        App.getInstance().getApiClient().validateEmail(email, new Callback<EmailValidationResult>() {
            @Override
            public void onResponse(Call<EmailValidationResult> call, Response<EmailValidationResult> response) {
                if(response.isSuccessful()) {
                    EmailValidationResult result = response.body();

                    if (result != null && result.getFormatValid()) {
                        Settings.setDisplayDialog(ctx, false);
                        showConfirmationDialog();
                    } else {
                        showMessage("You input wrong email. Try next time");
                    }
                } else {
                    showMessage("Verify email request failed");
                }
            }

            @Override
            public void onFailure(Call<EmailValidationResult> call, Throwable t) {
                showMessage(t.getMessage());
            }
        });
    }

    private void showConfirmationDialog() {
        BaseInfoDialog dialog = new GreatInfoDialogFragment();
        dialog.show(fragmentManager, "GreatInfoDialog");
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

    private void showMessage(String message) {
        Toast.makeText(ctx, "" + message, Toast.LENGTH_SHORT).show();
    }
}
