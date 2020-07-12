package com.flying.famous.quotes.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;

import com.flying.famous.quotes.R;
import com.flying.famous.quotes.db.DBManager;
import com.flying.famous.quotes.manager.DataManager;
import com.flying.famous.quotes.manager.LikeManager;
import com.flying.famous.quotes.manager.TypeManager;
import com.heima.easysp.SharedPreferencesUtils;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

import static android.Manifest.permission.ACCESS_NETWORK_STATE;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class SplashActivity extends AutoLayoutActivity implements EasyPermissions.PermissionCallbacks {
    private static final String TAG = "SplashActivity";
    public final static String[] perms = {WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE, ACCESS_NETWORK_STATE};
    private Handler handler = new Handler();
    private long time = System.currentTimeMillis();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        boolean ok = EasyPermissions.hasPermissions(this, perms);
        if (ok) {
            init();
        } else {
            EasyPermissions.requestPermissions(this, "存储", 111, perms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        Log.v(TAG, "用户授权成功");
        init();
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Log.d(TAG, "用户授权失败");
        finish();
    }

    private void init() {
        // 加载应用资源
        new Thread(new Runnable() {
            @Override
            public void run() {
                DBManager.INSTANCE().init(SplashActivity.this);
                TypeManager.getInstance().init();
                if (TypeManager.getInstance().getAllTypes().size() == 0) {
                    DataManager.getInstance().syncInit();
                }
                LikeManager.getInstance().init();
                TypeManager.getInstance().init();
                long tmp = System.currentTimeMillis() - time;
                if (tmp < 1000) {
                    handler.postDelayed(goMain, 1000 - tmp);
                } else {
                    goMainAc();
                }
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
