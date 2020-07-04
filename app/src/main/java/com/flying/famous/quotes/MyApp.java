package com.flying.famous.quotes;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.flying.famous.quotes.db.DBManager;
import com.flying.famous.quotes.manager.LikeManager;
import com.heima.easysp.SharedPreferencesUtils;

import java.util.UUID;

public class MyApp extends Application {
    public static Context context;
    public static String uuid;

    @Override
    public void onCreate() {
        super.onCreate();
        DBManager.INSTANCE().init(this);
        context = this;
        initUuid();
        LikeManager.getInstance().init();
    }

    private void initUuid() {
        uuid = SharedPreferencesUtils.init(context).getString("uuid");
        if (TextUtils.isEmpty(uuid)) {
            uuid = UUID.randomUUID().toString();
            SharedPreferencesUtils.init(context).put("uuid", uuid);
        }
    }
}
