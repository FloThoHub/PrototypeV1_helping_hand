package com.example.medion.prototypev1;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import org.apache.commons.lang3.StringUtils;

import java.util.Locale;

public class pushclickActivity extends AppCompatActivity {
   TextView tvNotification;
   Button gobackButton, locateButton;
   String notificationLongitude;
   String notificationLatitude;
   String notificationName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pushclick);



        gobackButton = (Button) findViewById(R.id.gobackButton);
        locateButton = (Button) findViewById(R.id.locateButton);
        tvNotification = (TextView)findViewById(R.id.tvNotification);

        notificationLatitude =getLatitude(ExampleNotificationOpenedHandler.notificationText);
        notificationLongitude = getLongitude(ExampleNotificationOpenedHandler.notificationText);
        notificationName = getName(ExampleNotificationOpenedHandler.notificationText);


        tvNotification.setText("Hi, my name is " +notificationName+ ".\nI could use your help.\n");

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
}
