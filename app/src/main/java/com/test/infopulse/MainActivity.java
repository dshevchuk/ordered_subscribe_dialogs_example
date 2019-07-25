package com.test.infopulse;

import android.arch.lifecycle.ViewModelProviders;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.test.infopulse.module.emailsubscription.EmailSubscribeManager;


public class MainActivity extends AppCompatActivity implements MainContract.View {

    private MainContract.ViewModel viewModel;

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (EmailSubscribeManager.ACTION_SHOW_DIALOG.equals(action)) {
                int orderNum = intent.getIntExtra(EmailSubscribeManager.EXTRAS_ORDER_NUM, 0);

                showDialog(viewModel.getDialogByNum(orderNum));

            } else if(EmailSubscribeManager.ACTION_SHOW_CONFIRMATION_DIALOG.equals(action)) {
                showDialog(viewModel.getConfirmationDialog());

            } else if(EmailSubscribeManager.ACTION_SHOW_MESSAGE.equals(action)) {
                String message = intent.getStringExtra(EmailSubscribeManager.EXTRAS_MESSAGE_TEXT);

                if(message != null && !TextUtils.isEmpty(message)) {
                    showMessage(message);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

    }

    @Override
    protected void onStart() {
        super.onStart();

        IntentFilter filter = new IntentFilter();
        filter.addAction(EmailSubscribeManager.ACTION_SHOW_DIALOG);
        filter.addAction(EmailSubscribeManager.ACTION_SHOW_CONFIRMATION_DIALOG);
        filter.addAction(EmailSubscribeManager.ACTION_SHOW_MESSAGE);

        getApplicationContext().registerReceiver(receiver, filter);

        viewModel.initEmailSubscription();
    }

    @Override
    protected void onStop() {
        super.onStop();

        getApplicationContext().unregisterReceiver(receiver);
    }

    public void showDialog(DialogFragment dialog) {
        dialog.show(getSupportFragmentManager(), dialog.getClass().getSimpleName());
    }

    private void showMessage(String message) {
        Toast.makeText(this, "" + message, Toast.LENGTH_SHORT).show();
    }
}
