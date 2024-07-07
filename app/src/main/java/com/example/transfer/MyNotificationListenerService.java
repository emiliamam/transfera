package com.example.transfer;

import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.content.Intent;
import android.util.Log;

public class MyNotificationListenerService extends NotificationListenerService {

    private static final String TAG = "NotificationListener";

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        super.onNotificationPosted(sbn);
        String notificationContent = sbn.getNotification().extras.getString("android.text");
        String notificationBigText = sbn.getNotification().extras.getString("android.bigText");
        Log.i(TAG, "Notification Posted: " + sbn.getPackageName());
        Log.i(TAG, "Notification Content: " + notificationContent);
        Log.i(TAG, "Big Text: " + notificationBigText);

        Intent intent = new Intent("com.example.transfer.NOTIFICATION_LISTENER");
        intent.putExtra("notification_event", sbn.getPackageName() + ": " + sbn.getNotification().tickerText + ": " + sbn);
        sendBroadcast(intent);
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        super.onNotificationRemoved(sbn);
        Log.i(TAG, "Notification Removed: " + sbn.getPackageName());
    }
}
