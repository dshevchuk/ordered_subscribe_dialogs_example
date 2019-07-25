package com.test.infopulse.module.emailsubscription.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.test.infopulse.R;

public abstract class BaseInfoDialog extends DialogFragment {

    private InfoDialogInterface listener;

    protected String mainText = "Main text stub!";
    protected String closeButtonText = "Close button stub!";


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();

        View v = inflater.inflate(R.layout.dialog_base_info, null);
        initTextElements();
        bindDialogElements(v);
        builder.setView(v);

        setCancelable(false);

        return builder.create();
    }

    public void setListener(InfoDialogInterface listener) {
        this.listener = listener;
    }

    protected void bindDialogElements(View v) {
        TextView mainTextView = v.findViewById(R.id.dialog_main_text);
        TextView closeButton = v.findViewById(R.id.dialog_button_close);

        mainTextView.setText(mainText);

        closeButton.setText(closeButtonText);
        closeButton.setOnClickListener((view) -> {
            if (listener != null) {
                listener.onCloseButtonClick();
            }
            dismiss();
        });
    }

    abstract void initTextElements();
}
