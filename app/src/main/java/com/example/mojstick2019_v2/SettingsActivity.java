package com.example.mojstick2019_v2;

import android.Manifest;
import android.app.WallpaperManager;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.FrameLayout;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.preference.PreferenceManager;


public class SettingsActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener{


    LocalStick notatkaLokalnie = new LocalStick();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_layout);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.preferences, new SettingsFragment())
                .commit();

        TextView previewOfWidget = findViewById(R.id.previewOfWidget);
        FrameLayout previewOfWidgetContainer = findViewById(R.id.previewOfWidgetContainer);

        previewOfWidget.setText(notatkaLokalnie.getStick(this));


        try {


            final WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
            final Drawable wallpaperDrawable = wallpaperManager.getDrawable();

            previewOfWidgetContainer.setBackground(wallpaperDrawable);


        } catch (SecurityException e) { //permission not granted

            ActivityCompat.requestPermissions(
                    this,
                    new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,

                    },
                    0);


        }

        applyPreferencesToPreview();


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        recreate();
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
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



    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        applyPreferencesToPreview();

    }

    void applyPreferencesToPreview(){


        SettingsSharedPreferences sharedPreferencesObject = new SettingsSharedPreferences(this);


        TextView previewOfWidget = findViewById(R.id.previewOfWidget);

        previewOfWidget.setTextSize(sharedPreferencesObject.getFontSize());
        previewOfWidget.setBackgroundColor(sharedPreferencesObject.getBackgroundColor());
        previewOfWidget.setTextColor(sharedPreferencesObject.getFontColor());
    }





}