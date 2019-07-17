package com.test.infopulse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.test.infopulse.chain.EmailSubscribeManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showSubscribeInvitation();
    }

    private void showSubscribeInvitation() {
        if (Settings.isDisplayDialog(this)) {
            EmailSubscribeManager subscriberManager =
                    new EmailSubscribeManager(this, getSupportFragmentManager());

            // if we don't match to any handler in chain - stop trying to display dialogs
            if(!subscriberManager.showDialog()) {
                Settings.setDisplayDialog(this, false);
            }
        }
    }

}
