package com.flying.famous.quotes;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.heima.easysp.SharedPreferencesUtils;

import java.util.UUID;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;

public class MyApp extends Application {
    public static Context context;
    public static String uuid;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        initUuid();

        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .setDefaultFontPath("fonts/Roboto-RobotoRegular.ttf")
                                .setFontAttrId(R.attr.fontPath)
                                .build()))
                .build());
    }


    private void initUuid() {
        uuid = SharedPreferencesUtils.init(context).getString("uuid");
        if (TextUtils.isEmpty(uuid)) {
            uuid = UUID.randomUUID().toString();
            SharedPreferencesUtils.init(context).put("uuid", uuid);
        }
    }


}
