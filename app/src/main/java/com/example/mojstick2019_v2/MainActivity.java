package com.example.mojstick2019_v2;


import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.RemoteViews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;


public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {



    EditText mEditText;


    LocalStick notatkaLokalnie = new LocalStick();

    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);


        mEditText = findViewById(R.id.editText);
        mEditText.setText(notatkaLokalnie.getStick(this));

        applySharedPreferencesToMainActivity();



    }


    void applyPreferencesToWidget() {



        SettingsSharedPreferences sharedPreferencesObject = new SettingsSharedPreferences(MainActivity.this);



        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);

        RemoteViews remoteViews = new RemoteViews(this.getPackageName(), R.layout.new_app_widget);
        ComponentName thisWidget = new ComponentName(this, NewAppWidget.class);

        remoteViews.setTextViewText(R.id.appwidget_text, mEditText.getText().toString());



        //apply settings to widget
        remoteViews.setTextViewTextSize(R.id.appwidget_text, TypedValue.COMPLEX_UNIT_SP, sharedPreferencesObject.getFontSize());
        remoteViews.setTextColor(R.id.appwidget_text, sharedPreferencesObject.getFontColor());
        remoteViews.setInt(R.id.appwidget_text, "setBackgroundColor", sharedPreferencesObject.getBackgroundColor());



        appWidgetManager.updateAppWidget(thisWidget, remoteViews);


    }



    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {


        applySharedPreferencesToMainActivity();
        applyPreferencesToWidget();


    }

    @Override
    protected void onStart() {
        super.onStart();
        PreferenceManager.getDefaultSharedPreferences(this)
                .registerOnSharedPreferenceChangeListener(this);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this)
                .unregisterOnSharedPreferenceChangeListener(this);
    }




    public void saveButton(View v){




        notatkaLokalnie.setStick(mEditText.getText().toString(),this);
        applyPreferencesToWidget();

        moveTaskToBack(true);

    }





    void applySharedPreferencesToMainActivity() {


        SettingsSharedPreferences sharedPreferencesObject = new SettingsSharedPreferences(MainActivity.this);


          mEditText.setTextSize(sharedPreferencesObject.getFontSize());

    }



    public void startSettings(View view){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }






    public void quitButton(View view) {


        moveTaskToBack(true);


    }


}
 
