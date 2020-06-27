package com.flying.famous.quotes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;
import java.util.List;


public class MoodActivity extends AutoLayoutActivity implements View.OnClickListener {

    private static final String TAG = MoodActivity.class.getSimpleName();

    private static final String DATA = "data";

    private ArrayList<String> list = new ArrayList();
    private LayoutInflater layoutInflater;
    private GridView gridView;
    private List<String> imageList = new ArrayList<>();
    private int index = -1;
    private View.OnClickListener change = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int index = (int) view.getTag();
            String url = getImageUrl();
            if (!TextUtils.isEmpty(url)) {
                Glide
                        .with(MoodActivity.this)
                        .load(url)
                        .centerCrop()
//                    .placeholder(R.drawable.loading_spinner)
                        .apply(RequestOptions.bitmapTransform(mation))
                        .into((ImageView) view);
            }
        }
    };
    private View.OnClickListener like = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };
    private View.OnClickListener save = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int index = (int) view.getTag();
        }
    };
    private View.OnClickListener copy = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int index = (int) view.getTag();
        }
    };
    private View.OnClickListener share = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int index = (int) view.getTag();

        }
    };


    public static void start(Context context, ArrayList<String> data) {
        Intent intent = new Intent(context, MoodActivity.class);
        intent.putStringArrayListExtra(DATA, data);
        context.startActivity(intent);
    }

    private MultiTransformation<android.graphics.Bitmap> mation;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);
        Log.i(TAG, "xxxxxxxxx");

        ArrayList<String> data = getIntent().getStringArrayListExtra(DATA);
        if (data != null && data.size() > 0) {
            list.addAll(data);
        }

        gridView = findViewById(R.id.gridView);
        layoutInflater = LayoutInflater.from(this);
        gridView.setAdapter(new MyAdapter());
        findViewById(R.id.back).setOnClickListener(this);

        // 圆角图片 new RoundedCornersTransformation 参数为 ：半径 , 外边距 , 圆角方向(ALL,BOTTOM,TOP,RIGHT,LEFT,BOTTOM_LEFT等等)
        RoundedCornersTransformation transformation = new RoundedCornersTransformation(30, 0, RoundedCornersTransformation.CornerType.TOP_LEFT);
        RoundedCornersTransformation transformation1 = new RoundedCornersTransformation(30, 0, RoundedCornersTransformation.CornerType.TOP_RIGHT);
        mation = new MultiTransformation<>(new CenterCrop(), transformation, transformation1);

        imageList.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3984473917,238095211&fm=26&gp=0.jpg");
        imageList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1593274233826&di=127a806c1f6250db0905c9cfc2d086a2&imgtype=0&src=http%3A%2F%2Fa3.att.hudong.com%2F14%2F75%2F01300000164186121366756803686.jpg");
        imageList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1593274233826&di=6f5a480c8e05a8fcc87dc7444625f103&imgtype=0&src=http%3A%2F%2Fa0.att.hudong.com%2F56%2F12%2F01300000164151121576126282411.jpg");
        imageList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1593274287279&di=d89ac4e0e0e6a198c7480bdd49b87704&imgtype=0&src=http%3A%2F%2Fattach.bbs.miui.com%2Fforum%2F201401%2F23%2F095609lsejfi4thjrrwydj.jpg");
        imageList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1593274287279&di=aca05c3accb56a195ca99b9889257580&imgtype=0&src=http%3A%2F%2Fattach.bbs.miui.com%2Fforum%2F201208%2F05%2F070711tbbgstxstin0mxr5.jpg");
        imageList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1593274287279&di=6382ce83cd868eac56b1dcffc56cd98c&imgtype=0&src=http%3A%2F%2F01.minipic.eastday.com%2F20170330%2F20170330044723_a0c69f758cc90e87e8c8e620eb55308e_2.jpeg");

    }


    private String getImageUrl() {
        Log.i(TAG, "index = " + index);
        index++;
        if (index >= imageList.size() - 1) {
            index = 0;
        }
        return imageList.get(index);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.back) {

        }
    }


    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            ViewHolder holder = null;
            if (holder == null) {
                convertView = layoutInflater.inflate(R.layout.mood_item, null);
                holder = new ViewHolder();
                holder.text = convertView.findViewById(R.id.text);
                holder.image = convertView.findViewById(R.id.image);
                holder.like = convertView.findViewById(R.id.like);
                holder.save = convertView.findViewById(R.id.save);
                holder.copy = convertView.findViewById(R.id.copy);
                holder.share = convertView.findViewById(R.id.share);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.image.setOnClickListener(change);
            holder.like.setOnClickListener(like);
            holder.save.setOnClickListener(save);
            holder.copy.setOnClickListener(copy);
            holder.share.setOnClickListener(share);

            holder.image.setTag(i);
            holder.like.setTag(i);
            holder.save.setTag(i);
            holder.copy.setTag(i);
            holder.share.setTag(i);


            String mood = list.get(i);
            holder.text.setText(mood);

            String url = getImageUrl();
            if (!TextUtils.isEmpty(url)) {
                Glide
                        .with(MoodActivity.this)
                        .load(url)
                        .centerCrop()
//                    .placeholder(R.drawable.loading_spinner)
                        .apply(RequestOptions.bitmapTransform(mation))

                        .into(holder.image);
            }
            return convertView;
        }


        class ViewHolder {
            TextView text;
            ImageView image;
            View like;
            View save;
            View copy;
            View share;

        }
    }

}
