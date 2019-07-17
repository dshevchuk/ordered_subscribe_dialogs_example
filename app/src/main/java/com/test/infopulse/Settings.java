package com.test.infopulse;

import android.content.Context;
import android.content.SharedPreferences;

public class Settings {
    private static final String PREFS_FILENAME = "prefss";
    private static final String KEY_DISPLAY_DIALOG = "display_dialog";
    private static final String KEY_DISPLAY_ATTEMPT = "display_attempts";

    public static void setDisplayDialog(Context ctx, boolean shoudDisplay) {
        SharedPreferences.Editor editor =
                ctx.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE).edit();

        editor.putBoolean(KEY_DISPLAY_DIALOG, shoudDisplay);
        editor.apply();
    }

    public static boolean isDisplayDialog(Context ctx) {
        SharedPreferences prefs = ctx.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE);
        return prefs.getBoolean(KEY_DISPLAY_DIALOG, true);
    }

    public static void setDisplayAttemptCount(Context ctx, int attemptCount) {
        SharedPreferences.Editor editor =
                ctx.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE).edit();

        editor.putInt(KEY_DISPLAY_ATTEMPT, attemptCount);
        editor.apply();
    }

    public static int getDisplayAttemptCount(Context ctx) {
        SharedPreferences prefs = ctx.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE);
        return prefs.getInt(KEY_DISPLAY_ATTEMPT, 1);
    }

}
