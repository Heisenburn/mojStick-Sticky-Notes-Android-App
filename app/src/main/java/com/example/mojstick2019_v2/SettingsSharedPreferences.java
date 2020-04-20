package com.example.mojstick2019_v2;

import android.content.Context;
import android.content.SharedPreferences;



class SettingsSharedPreferences {


    SharedPreferences sharedPref;

    static final String MyPREFERENCES = "com.example.mojstick2019_v2_preferences" ;



    SettingsSharedPreferences(Context context) { //constructor

        sharedPref = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

    }


    int getFontColor() {


        return sharedPref.getInt("fontColor", -14654801); //defValue - default value


    }


    int getFontSize() {

        return sharedPref.getInt("fontSizeSeekBar", 40);


    }

    int getBackgroundColor() {


        return sharedPref.getInt("backgroundColor", -14654801);


    }





}