package com.flying.famous.quotes;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.bumptech.glide.Glide;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AutoLayoutActivity implements View.OnClickListener {
    private GridView gridView;
    private EditText editText;
    private LayoutInflater layoutInflater;

    private List<Mood> list = new ArrayList<>();
    private MyAdapter myAdapter = new MyAdapter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.delete).setOnClickListener(this);
        findViewById(R.id.menu).setOnClickListener(this);
        gridView = findViewById(R.id.gridView);
        editText = findViewById(R.id.input);

        layoutInflater = LayoutInflater.from(this);
        initTestData();

        gridView.setAdapter(myAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MoodActivity.start(MainActivity.this,list.get(i).list);
            }
        });
    }

    private void initTestData() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("“Nothing we do , however virtuous ,can be alone ; therefore we are saved by love.”");
        strings.add("“Using simple equipment and daylight alone is for me a pleasure and a replenishment.");
        Mood mood = new Mood("http://img.mp.itc.cn/upload/20170530/93871219ad7a45b090e334b8f999c01d_th.jpg", "AAA", strings);
        list.add(mood);
        list.add(mood);
        list.add(mood);
        list.add(mood);
        list.add(mood);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.delete) {

        } else if (view.getId() == R.id.menu) {

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

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            ViewHolder holder = null;
            if (holder == null) {
                convertView = layoutInflater.inflate(R.layout.main_item, null);
                holder = new ViewHolder();
                holder.name = convertView.findViewById(R.id.name);
                holder.icon = convertView.findViewById(R.id.icon);
                holder.bg = convertView.findViewById(R.id.bg);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            GradientDrawable drawable = (GradientDrawable) holder.bg.getBackground();
            drawable.setColor(Color.parseColor("#00FFFF"));
            convertView.setBackground(drawable);
            Mood mood = list.get(i);
            holder.name.setText(mood.name);
            Glide
                    .with(MainActivity.this)
                    .load(mood.url)
                    .centerCrop()
//                    .placeholder(R.drawable.loading_spinner)
                    .into(holder.icon);
            return convertView;
        }


        class ViewHolder {
            TextView name;
            ImageView icon;
            View bg;
        }
    }
}
