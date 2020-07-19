package com.flying.famous.quotes.utils;

import android.content.Intent;
import android.net.Uri;

import com.flying.famous.quotes.MyApp;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class WebUtils {

    public static void rateUs() {
        open("https://play.google.com/store/apps/details?id=" + MyApp.context.getPackageName());
    }

    public static void toFacebook() {
        open("https://www.facebook.com/");
    }

    public static void open(String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
        MyApp.context.startActivity(intent);
    }
}
