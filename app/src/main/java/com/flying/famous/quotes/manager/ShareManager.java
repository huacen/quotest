package com.flying.famous.quotes.manager;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.flying.famous.quotes.MyApp;

public class ShareManager {
    private static final String TAG = ShareManager.class.getSimpleName();


    private static class Inner {
        private static final ShareManager INSTANCE = new ShareManager();
    }

    public static ShareManager getInstance() {
        return Inner.INSTANCE;
    }

    private ShareManager() {
    }


    public void shareTxt(Context context, String txt) {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        //要分享的文本内容，选择某项后会直接把这段文本发送出去，相当于调用选中的应用的接口，并传参
        shareIntent.putExtra(Intent.EXTRA_TEXT, txt);
        //需要使用Intent.createChooser，这里我们直接复用。第二个参数并不会显示出来
        shareIntent = Intent.createChooser(shareIntent, "正在分享");
        context.startActivity(shareIntent);
    }

    public void shareImg(Context context, String path) {
        //指定要分享的图片路径
        Uri imgUri = Uri.parse(path);
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, imgUri);
        shareIntent.setType("image/*");
        shareIntent = Intent.createChooser(shareIntent, "正在分享");
        context.startActivity(shareIntent);
    }

}
