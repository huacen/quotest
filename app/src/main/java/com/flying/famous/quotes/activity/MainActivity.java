package com.flying.famous.quotes.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.drawerlayout.widget.DrawerLayout;

import com.flying.famous.quotes.R;
import com.flying.famous.quotes.adapter.TextWatcherAdapter;
import com.flying.famous.quotes.db.entity.Type;
import com.flying.famous.quotes.manager.TypeManager;
import com.flying.famous.quotes.utils.EmailUtils;
import com.flying.famous.quotes.utils.WebUtils;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class MainActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = MoodActivity.class.getSimpleName();
    private GridView gridView;
    private EditText editText;
    private LayoutInflater layoutInflater;

    private List<Type> data = new ArrayList<>();
    private MyAdapter myAdapter = new MyAdapter();
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private long lastTime = 0;
    private View delete;

    private TextWatcherAdapter textWatcherAdapter = new TextWatcherAdapter() {
        @Override
        public void afterTextChanged(Editable editable) {
            super.afterTextChanged(editable);
            String keyword = editable.toString();
            List<Type> list = TextUtils.isEmpty(keyword) ? data : filterData(keyword);
            myAdapter.setData(list);
            delete.setVisibility(TextUtils.isEmpty(keyword) ? View.GONE : View.VISIBLE);
        }
    };
    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            if (System.currentTimeMillis() - lastTime > 500) {
                Type type = data.get(i);
                MoodActivity.start(MainActivity.this, type.getId());
            }
            lastTime = System.currentTimeMillis();
        }
    };

    private NavigationView.OnNavigationItemSelectedListener listener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Log.v(TAG, "onNavigationItemSelected.menuItem = " + menuItem.getGroupId());
            switch (menuItem.getItemId()) {
                case R.id.like:
                    MoodActivity.startLike(MainActivity.this);
                    break;
//                case R.id.quote_of_the_day:
//                    Log.v(TAG, "quote_of_the_day");
//                    break;
//                case R.id.tap_sound:
//                    break;
//                case R.id.faqs:
//                    break;
                case R.id.contact_us:
                    EmailUtils.sendEmailDuo(MainActivity.this);
                    break;
                case R.id.rate_us:
                    WebUtils.rateUs();
                    break;
                case R.id.share_app:
                    EmailUtils.shareApp(MainActivity.this);
                    break;
//                case R.id.facebook:
//                    break;
//                case R.id.privacy_policy:
//                    break;
            }
            drawerLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    drawerLayout.closeDrawers();
                }
            }, 100);
            return false;
        }
    };


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        setContentView(R.layout.activity_main);
        findViewById(R.id.delete).setOnClickListener(this);
        findViewById(R.id.menu).setOnClickListener(this);
        gridView = findViewById(R.id.gridView);
        editText = findViewById(R.id.input);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        editText.addTextChangedListener(textWatcherAdapter);
        navigationView.setNavigationItemSelectedListener(listener);
        layoutInflater = LayoutInflater.from(this);
        delete = findViewById(R.id.delete);
        gridView.setAdapter(myAdapter);
        gridView.setOnItemClickListener(onItemClickListener);

        Collection<Type> collection = TypeManager.getInstance().getAllTypes();
        if (collection != null && collection.size() > 0) {
            data.addAll(collection);
            myAdapter.setData(data);
        }
        for (Type type : data) {
            TypeManager.TypeConfig config = TypeManager.getInstance().getConfig(type.getName());
            if (config == null) {
                Log.i(TAG, "has not  config:" + type.getName());
            }
        }
    }


    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.delete) {
            editText.setText("");
        } else if (view.getId() == R.id.menu) {
            drawerLayout.openDrawer(Gravity.END);
        }
    }

    private List<Type> filterData(String keyword) {
        List<Type> types = new ArrayList<>();
        for (Type type : data) {
            if (type.getName().toLowerCase().contains(keyword.toLowerCase())) {
                types.add(type);
            }
        }
        return types;
    }


    class MyAdapter extends BaseAdapter {
        private List<Type> data = new ArrayList<>();

        public void setData(List<Type> data) {
            this.data.clear();
            if (data != null && data.size() > 0) {
                this.data.addAll(data);
            }
            notifyDataSetChanged();
        }

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
            holder.name.setText(type.getName());

            TypeManager.TypeConfig config = TypeManager.getInstance().getConfig(type.getName());
            boolean hasConfig = config != null;
            String color = hasConfig ? config.color : type.getBg();
            try {
                drawable.setColor(Color.parseColor(color));
            } catch (Exception e) {
                Log.e(TAG, "parseColor error:" + color);
            }

            if (hasConfig) {
                holder.icon.setImageResource(config.iconRes);
            }

//            Glide
//                    .with(MainActivity.this)
//                    .load(type.getIconUrl())
//                    .centerCrop()
////                    .placeholder(R.drawable.loading_spinner)
//                    .into(holder.icon);
            return convertView;
        }


        class ViewHolder {
            TextView name;
            ImageView icon;
            View bg;
        }
    }


}
