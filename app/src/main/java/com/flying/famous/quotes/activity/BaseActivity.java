package com.flying.famous.quotes.activity;

import android.content.Context;

import com.zhy.autolayout.AutoLayoutActivity;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class BaseActivity extends AutoLayoutActivity {
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}
