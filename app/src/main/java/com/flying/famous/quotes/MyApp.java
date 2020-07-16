package com.flying.famous.quotes;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.heima.easysp.SharedPreferencesUtils;

import java.util.UUID;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MyApp extends Application {
    public static Context context;
    public static String uuid;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        initUuid();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/font.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }


    private void initUuid() {
        uuid = SharedPreferencesUtils.init(context).getString("uuid");
        if (TextUtils.isEmpty(uuid)) {
            uuid = UUID.randomUUID().toString();
            SharedPreferencesUtils.init(context).put("uuid", uuid);
        }
    }


}
