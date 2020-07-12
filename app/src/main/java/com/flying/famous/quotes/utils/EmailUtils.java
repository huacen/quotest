package com.flying.famous.quotes.utils;

import android.content.Context;
import android.content.Intent;

public class EmailUtils {
    private static final String SHARE_APP = "Best Quotes & Status\n" +
            "\n" +
            "Download and Share: https://play.google.com/store/apps/details?id=com.manchesterapps.quotesandstatus\n" +
            "\n" +
            "Click here to install:\n" +
            "https://play.google.com/store/apps/details?id=com.manchesterapps.quotesandstatus";


    public static void sendEmailDuo(Context context) {
        String[] tos = {"421665909@qq.com"};
        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, tos);
        intent.setType("text/plain");
//        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
        intent.setClassName("com.android.email", "com.android.email.activity.MessageCompose");
        context.startActivity(Intent.createChooser(intent, "Choose Email Client"));
    }

    public static void shareApp(Context context) {
        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, "");
//        intent.putExtra(Intent.EXTRA_CC, ccs); //抄送这
//        intent.putExtra(Intent.EXTRA_BCC, bccs); //密送这
        intent.putExtra(Intent.EXTRA_TEXT, SHARE_APP);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Best Quotes & Status");

        intent.setType("text/plain");
//        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
        intent.setClassName("com.android.email", "com.android.email.activity.MessageCompose");
        context.startActivity(Intent.createChooser(intent, "Choose Email Client"));
    }
}
