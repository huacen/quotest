package com.flying.famous.quotes.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.flying.famous.quotes.R;
import com.flying.famous.quotes.db.DBManager;
import com.flying.famous.quotes.manager.DataManager;
import com.flying.famous.quotes.manager.LikeManager;
import com.flying.famous.quotes.manager.TypeManager;
import com.tbruyelle.rxpermissions3.RxPermissions;

import io.reactivex.rxjava3.functions.Consumer;
import pub.devrel.easypermissions.AppSettingsDialog;

import static android.Manifest.permission.ACCESS_NETWORK_STATE;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class SplashActivity extends BaseActivity {
    private static final String TAG = "SplashActivity";
    public final static String[] perms = {WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE, ACCESS_NETWORK_STATE};
    private Handler handler = new Handler();
    private RxPermissions rxPermissions;
    long time = System.currentTimeMillis();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        rxPermissions = new RxPermissions(this);
        request();
    }

    private void request() {
        time = System.currentTimeMillis();
        rxPermissions.request(perms)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean granted) throws Throwable {
                        Log.v(TAG, "granted= " + granted);
                        if (granted) {
                            init();
                        } else {
                            long tmp = System.currentTimeMillis() - time;
                            Log.v(TAG, "tmpTime = " + tmp);
                            if (tmp < 400) {
                                new AppSettingsDialog.Builder(SplashActivity.this)
                                        .setRationale("     Please modify application permission in the application settings!")
                                        .setTitle("Permission required")
                                        .setNegativeButton("cancel")
                                        .setPositiveButton("ok")
                                        .build()
                                        .show();
                                return;
                            }
                            finish();
                        }
                    }
                });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            //从设置页面返回，判断权限是否申请。
            rxPermissions.request(perms)
                    .subscribe(new Consumer<Boolean>() {
                        @Override
                        public void accept(Boolean granted) throws Throwable {
                            Log.v(TAG, "granted= " + granted);
                            if (granted) {
                                init();
                            } else {
                                finish();
                            }
                        }
                    });
        }
    }

    private void init() {
        // 加载应用资源
        new Thread(new Runnable() {
            @Override
            public void run() {
                DataManager.getInstance().copyDataBaseIfNeed(false);
                DBManager.INSTANCE().init(SplashActivity.this);
                TypeManager.getInstance().init();
                if (TypeManager.getInstance().getAllTypes().size() == 0) {
                    DataManager.getInstance().copyDataBaseIfNeed(true);
                }
                LikeManager.getInstance().init();
                TypeManager.getInstance().init();
//                long tmp = System.currentTimeMillis() - time;
//                if (tmp < 1000) {
//                    handler.postDelayed(goMain, 1000 - tmp);
//                } else {
                goMainAc();
//                }
            }
        }).start();
    }

    private Runnable goMain = new Runnable() {
        @Override
        public void run() {
            goMainAc();
        }
    };
    private Runnable finish = new Runnable() {
        @Override
        public void run() {
            finish();
        }
    };

    private void goMainAc() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        handler.postDelayed(finish, 500);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(finish);
        handler.removeCallbacks(goMain);
    }
}
