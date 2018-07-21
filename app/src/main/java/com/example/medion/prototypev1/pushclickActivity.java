package com.example.medion.prototypev1;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.onesignal.OneSignal;

import org.apache.commons.lang3.StringUtils;

import java.util.Locale;

public class pushclickActivity extends AppCompatActivity {
   TextView tvNotification;
   Button gobackButton, locateButton, callButton, sendSMSButton;
   String notificationLongitude;
   String notificationLatitude;
   String notificationName;
   String notificationPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pushclick);

        sendSMSButton = (Button) findViewById(R.id.sendSMSButton);
        callButton = (Button) findViewById(R.id.callButton);
        gobackButton = (Button) findViewById(R.id.gobackButton);
        locateButton = (Button) findViewById(R.id.locateButton);
        tvNotification = (TextView)findViewById(R.id.tvNotification);

        notificationLatitude =getLatitude(ExampleNotificationOpenedHandler.notificationText);
        notificationLongitude = getLongitude(ExampleNotificationOpenedHandler.notificationText);
        notificationName = getName(ExampleNotificationOpenedHandler.notificationText);
        notificationPhone = getPhone(ExampleNotificationOpenedHandler.notificationText);


        tvNotification.setText("Hi, my name is " +notificationName+ ".\nI could use your help.\n");

        sendSMSButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SmsManager sms = SmsManager.getDefault();
                //sms.sendTextMessage(notificationPhone, null, "Hi, I can help you:)", null, null);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + notificationPhone));
                intent.putExtra("sms_body", "Hi, I can help:)");
                startActivity(intent);
            }
        });




        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + notificationPhone));
                startActivity(intent);
            }
        });

        gobackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(pushclickActivity.this, MainActivity.class);
                finish();
                startActivity(i);
            }
        });

        locateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*
                String uri = "http://maps.google.com/maps?daddr=" + notificationLongitude + "," + notificationLatitude + " (" + "Here I am" + ")";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                intent.setPackage("com.google.android.apps.maps");
                */
                String uri = "http://maps.google.com/maps?daddr=" + notificationLatitude + "," + notificationLongitude+ "(" + "Here I am" + ")";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                intent.setPackage("com.google.android.apps.maps");
                startActivity(intent);
            }
        });

    }

    String getLongitude(String notificationText)
    {
        String notificationLongitude;


        notificationLongitude = StringUtils.substringBetween(notificationText, "Longitude<", ">");
        return notificationLongitude;
    }

    String getLatitude(String notificationText)
    {
        String notificationLatitude;


        notificationLatitude = StringUtils.substringBetween(notificationText, "Latitude<", ">");
        return notificationLatitude;
    }

    String getName(String notificationText)
    {
        String notificationName;


        notificationName = StringUtils.substringBetween(notificationText, "Name<", ">");
        return notificationName;
    }
    String getPhone(String notificationText)
    {
        String notificationPhone;


        notificationPhone = StringUtils.substringBetween(notificationText, "Phone<", ">");
        return notificationPhone;
    }
}


