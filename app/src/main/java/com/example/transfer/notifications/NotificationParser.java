package com.example.transfer.notifications;

import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NotificationParser {

    private static final String TAG = "NotificationParser";

    public static String parseNotification(String packageName, String content) {
        if (content == null) {
            Log.i(TAG, "Notification content is empty");
            return null;
        }

        switch (packageName) {
            case "com.idamob.tinkoff.android":
                return parseNotificationTink(content);
            case "com.sberbankmobile":
                return parseNotificationSber(content);
            case "ru.raiffeisennews":
                return parseNotificationRaiffeisen(content);
            default:
                return null;
        }
    }

    private static String parseNotificationTink(String input) {
        String regex = "Пополнение на\\s*(\\d+)\\s*₽,\\s*счет\\s*(\\w+).\\s*(.+)\\s*.";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            String amount = matcher.group(1);
            String currency = matcher.group(2);
            String name = matcher.group(3);

            return String.format("Tinkoff - Amount: %s, Currency: %s, Name: %s", amount, currency, name);
        } else {
            Log.i(TAG, "No match found for Tinkoff notification.");
            return null;
        }
    }

    private static String parseNotificationSber(String input) {
        String regex = "([А-Яа-яA-Za-z]+) \\+\\s*(\\d+)\\s*₽\\s*([A-Z]{4})\\s*•+\\s*(\\d+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            String bankName = matcher.group(1);
            String amount = matcher.group(2);
            String cardType = matcher.group(3);
            String cardNumber = matcher.group(4);

            return String.format("Sberbank - Bank Name: %s, Amount: %s ₽, Card Type: %s, Card Number: %s", bankName, amount, cardType, cardNumber);
        } else {
            Log.i(TAG, "No match found for Sberbank notification.");
            return null;
        }
    }

    private static String parseNotificationRaiffeisen(String input) {
        String regex = "\\+\\s([0-9,.]+)\\s₽\\sот\\s(\\+\\d+),\\s([^,]+)\\..*Теперь на счете\\s([0-9,.]+)\\s₽";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            String amountReceived = matcher.group(1);
            String phoneNumber = matcher.group(2);
            String senderName = matcher.group(3);
            String accountBalance = matcher.group(4);

            return String.format("Raiffeisen - Amount Received: %s, Phone Number: %s, Sender Name: %s, Account Balance: %s", amountReceived, phoneNumber, senderName, accountBalance);
        } else {
            Log.i(TAG, "No match found for Raiffeisen notification.");
            return null;
        }
    }
}
