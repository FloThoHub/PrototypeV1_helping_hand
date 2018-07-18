package com.example.medion.prototypev1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.onesignal.OneSignal;

public class pushclickActivity extends AppCompatActivity {
   // TextView tvNotification = (TextView)findViewById(R.id.tvNotification);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pushclick);

/*
        OneSignal.startInit(this)
                .setNotificationOpenedHandler(new ExampleNotificationOpenedHandler(this))
                .init();
*/
    //tvNotification.setText(ExampleNotificationOpenedHandler.notificationText);
    }
}
