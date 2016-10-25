package com.tm.arifin.timbangan;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;

public class MyApplication extends Application {

    private static final String RALEWAY_REGULAR_PATH = "fonts/ralewayregular.ttf";
    public static Typeface ralewayRegular;
    private static final String RALEWAY_LIGHT_PATH = "fonts/ralewaylight.ttf";
    public static Typeface ralewayLight;


    @Override
    public void onCreate() {
        super.onCreate();
        initTypeface();
    }

    private void initTypeface() {
        ralewayRegular = Typeface.createFromAsset(getAssets(), RALEWAY_REGULAR_PATH);
        ralewayLight = Typeface.createFromAsset(getAssets(), RALEWAY_LIGHT_PATH);

    }

    public static String encodeTobase64(Bitmap bitmap)
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    public static Bitmap decodeBase64(String b64)
    {
        byte[] imageAsBytes = Base64.decode(b64.getBytes(), Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
    }





}
