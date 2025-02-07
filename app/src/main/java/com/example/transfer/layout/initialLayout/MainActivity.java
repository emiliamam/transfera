package com.example.transfer.layout.initialLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.transfer.notifications.NotificationParser;
import com.example.transfer.R;
import com.example.transfer.helper.LocaleHelper;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;

public class MainActivity extends AppCompatActivity {
    private final static String QUEUE_NAME = "hello";
    private final static String HOST = "168.100.10.137";
    private final static String USERNAME = "admin";
    private final static String PASSWORD = "xnb27A$";
    private ExecutorService executor;
    private NotificationParser notificationReceiver;

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LocaleHelper.setLocale(this, LocaleHelper.getLanguage(this));
        Log.i("LNG", "Setlng"+LocaleHelper.getLanguage(this) );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button but = findViewById(R.id.button);
        executor = Executors.newFixedThreadPool(2);
        executor.execute(new RabbitMQTask());


        notificationReceiver = new NotificationParser();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.example.transfer.NOTIFICATION_LISTENER");
//        registerReceiver(notificationReceiver, filter, Context.RECEIVER_EXPORTED);
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        unregisterReceiver(notificationReceiver);
//        if (executor != null) {
//            executor.shutdown();
//        }
//    }

    private static class RabbitMQTask implements Runnable {
        @Override
        public void run() {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(HOST);
            factory.setUsername(USERNAME);
            factory.setPassword(PASSWORD);
            try (Connection connection = factory.newConnection();
                 Channel channel = connection.createChannel()) {
                channel.queueDeclare(QUEUE_NAME, false, false, false, null);
                String message = "Hello World!";
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
                Log.i("RabbitMQ", " [x] Sent '" + message + "'");

                DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                    String receivedMessage = new String(delivery.getBody(), "UTF-8");
                    Log.i("RabbitMQ", " [x] Received '" + receivedMessage + "'");
                };
                channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
            } catch (IOException | TimeoutException e) {
                Log.e("RabbitMQ", "Error: " + e, e);
            }
        }
    }
}
