package com.flying.famous.quotes;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.drawerlayout.widget.DrawerLayout;

import com.bumptech.glide.Glide;
import com.flying.famous.quotes.db.DBManager;
import com.flying.famous.quotes.db.Type;
import com.google.android.material.navigation.NavigationView;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AutoLayoutActivity implements View.OnClickListener {
    private static final String TAG = MoodActivity.class.getSimpleName();
    private GridView gridView;
    private EditText editText;
    private LayoutInflater layoutInflater;
    private DBManager dbManager = DBManager.INSTANCE();

    private List<Type> data = new ArrayList<>();
    private MyAdapter myAdapter = new MyAdapter();
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.delete).setOnClickListener(this);
        findViewById(R.id.menu).setOnClickListener(this);
        gridView = findViewById(R.id.gridView);
        editText = findViewById(R.id.input);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Log.v(TAG,"onNavigationItemSelected.menuItem = "+menuItem.getGroupId());
               switch (menuItem.getItemId()){
                   case  R.id.like:
                       Log.v(TAG,"like");
                       break;
                   case  R.id.quote_of_the_day:
                       Log.v(TAG,"quote_of_the_day");
                       break;
                   case  R.id.tap_sound:
                       break;
                   case  R.id.faqs:
                       break;
                   case  R.id.contact_us:
                       break;
                   case  R.id.rate_us:
                       break;
                   case  R.id.share_app:
                       break;
                   case  R.id.facebook:
                       break;
                   case  R.id.privacy_policy:
                       break;
               }
                return false;
            }
        });

        layoutInflater = LayoutInflater.from(this);


        gridView.setAdapter(myAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Type type = data.get(i);
                MoodActivity.start(MainActivity.this, type.getId());
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Type> list = DBManager.INSTANCE().getType().loadAll();
                if (list != null && list.size() > 0) {
                    data.addAll(list);
                }
            }
        }).start();
//        DataManager.getInstance().init();
    }


    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.delete) {

        } else if (view.getId() == R.id.menu) {
            drawerLayout.openDrawer(Gravity.END);
        }
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int i) {
            return data.get(i);
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
            convertView.setBackground(drawable);
            Type type = data.get(i);
            drawable.setColor(Color.parseColor(type.getBg()));
            holder.name.setText(type.getName());
            Glide
                    .with(MainActivity.this)
                    .load(type.getIconUrl())
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
