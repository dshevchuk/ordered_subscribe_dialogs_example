package com.test.infopulse.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.test.infopulse.R;

public abstract class BaseDialog extends DialogFragment {

    private BaseDialogInterface listener;

    protected String mainText = "Main text stub!";
    protected String subText = "Sub text stub!";
    protected String positiveButtonText = "Positive button stub!";
    protected String negativeButtonText = "Negative button stub!";
    protected String inputHintText = "Hint stub!";


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();

        View v = inflater.inflate(R.layout.dialog_base, null);
        initTextElements();
        bindDialogElements(v);
        builder.setView(v);

        setCancelable(false);

        return builder.create();
    }

    public void setListener(BaseDialogInterface listener) {
        this.listener = listener;
    }

    protected void bindDialogElements(View v) {
        TextView mainTextView = v.findViewById(R.id.dialog_main_text);
        TextView subTextView = v.findViewById(R.id.dialog_sub_text);
        TextView positiveButton = v.findViewById(R.id.dialog_button_positive);
        TextView negativeButton = v.findViewById(R.id.dialog_button_negative);
        EditText inputEditText = v.findViewById(R.id.dialog_input_text);

        mainTextView.setText(mainText);
        subTextView.setText(subText);

        inputEditText.setHint(inputHintText);

        positiveButton.setText(positiveButtonText);
        positiveButton.setOnClickListener((view) -> {
            if (listener != null) {
                listener.onPositiveClick(view);
            }
            dismiss();
        });

        negativeButton.setText(negativeButtonText);
        negativeButton.setOnClickListener((view) -> {
            if (listener != null) {
                listener.onNegativeClick(view);
            }
            dismiss();
        });
    }

    abstract void initTextElements();
}
