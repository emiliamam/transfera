package com.example.transfer.notifications;

import android.app.Notification;
import android.content.Intent;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

public class MyNotificationListenerService extends NotificationListenerService {

    private static final String TAG = "NotificationListener";
    private static final String[] BANK_PACKAGES = {
            "com.idamob.tinkoff.android",
            "com.sberbankmobile",
            "ru.raiffeisennews",
            "ru.uralsib",
            "ru.sberbankmobile"
    };

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        super.onNotificationPosted(sbn);

        String packageName = sbn.getPackageName();
        boolean isBankNotification = false;

        for (String bankPackage : BANK_PACKAGES) {
            if (packageName.equals(bankPackage)) {
                isBankNotification = true;
                break;
            }
        }

        if (!isBankNotification) {
            return;
        }

        Notification notification = sbn.getNotification();
        String notificationContent = notification.extras.getString("android.text");
        String notificationBigText = notification.extras.getString("android.bigText");

        Log.i(TAG, "Notification Posted from: " + packageName);
        Log.i(TAG, "Notification Content: " + notificationContent);
        Log.i(TAG, "Big Text: " + notificationBigText);

        String parsedData = NotificationParser.parseNotification(packageName, notificationContent != null ? notificationContent : notificationBigText);

        if (parsedData != null) {
            Intent intent = new Intent("com.example.transfer.NOTIFICATION_LISTENER");
            intent.putExtra("notification_event", parsedData);
            sendBroadcast(intent);
        }
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        super.onNotificationRemoved(sbn);
        Log.i(TAG, "Notification Removed from: " + sbn.getPackageName());
    }
}
