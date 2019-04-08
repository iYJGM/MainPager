package com.example.mainpager;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SearchView mSearchView;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    //写一个List集合，把每个页面，也就是Fragment,存进去
    private List<Fragment> fragmentList;
    private String[] titles = new String[]{"本地音乐", "网络乐库", "我的"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //toolbar.setOnMenuItemClickListener(this);
        //构造适配器
        List<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(new Fragment1());
        fragments.add(new Fragment2());
        fragments.add(new Fragment3());

        //ViewPager的适配器，获得Fragment管理器
        FragAdapter adapter = new FragAdapter(getSupportFragmentManager(), fragments);
        //设定适配器
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        //将TabLayout和ViewPager绑定在一起，一个动另一个也会跟着动
        viewPager.setAdapter(adapter);
        tabLayout = findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);

    }

    //创建Fragment的适配器
    public class FragAdapter extends FragmentPagerAdapter {
        public FragAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            fragmentList = fragments;
        }

        //获得每个页面的下标
        @Override
        public Fragment getItem(int arg0) {
            return fragmentList.get(arg0);
        }

        //获得List的大小
        @Override
        public int getCount() {
            return fragmentList.size();
        }

        //返回对应下标的标题
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        //通过MenuItem得到SearchView
        mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_item_one:
                Toast.makeText(this, "扫描本地", Toast.LENGTH_SHORT).show();
                Log.v("","扫描本地");
                break;
            case R.id.action_item_two:
                Toast.makeText(this, "设置", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }

//    @Override
//    public boolean onMenuItemClick(MenuItem menuItem) {
//
//        switch (menuItem.getItemId()) {
//            case R.id.action_search:
//                //Toast.makeText(this, "您点击了搜索", Toast.LENGTH_SHORT).show();
//                break;
//        }
//        return false;
//    }

}
