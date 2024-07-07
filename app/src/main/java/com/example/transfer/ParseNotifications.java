package com.example.transfer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ParseNotifications extends BroadcastReceiver {
    private static final String TAG = "NotificationReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String notificationEvent = intent.getStringExtra("notification_event");
//        String notificationEvent = intent.getStringExtra();
        Log.i(TAG, "Notification Event: " + notificationEvent);


    }
}

