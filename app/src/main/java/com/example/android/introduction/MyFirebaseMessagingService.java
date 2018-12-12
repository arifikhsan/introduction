package com.example.android.introduction;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Objects;


/**
 * Created by Arif Ikhsanudin on Tuesday, 11 December 2018.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();

    public MyFirebaseMessagingService() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        String title = Objects.requireNonNull(remoteMessage.getNotification()).getTitle();
        String message = remoteMessage.getNotification().getBody();
        Log.d(TAG, "onMessageReceived: \n" +
                "Title: " + title + "\n" +
                "Message: " + message
        );
        uiThread();
    }

    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
    }

    @Override
    public void onNewToken(String token) {
        Log.d(TAG, "Refreshed token: " + token);
        super.onNewToken(token);
    }

    private void uiThread() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), "FCM" , Toast.LENGTH_SHORT).show();
            }
        });
    }
}
