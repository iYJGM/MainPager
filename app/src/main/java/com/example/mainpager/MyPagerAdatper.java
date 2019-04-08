package com.example.mainpager;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

//自定义PagerAdapter，以便将步骤2中的Views数据加载到ViewPager容器中
public class MyPagerAdatper extends PagerAdapter {
    private List<View> mViewList;

    public MyPagerAdatper(List<View> mViewList) {
        this.mViewList = mViewList;
    }

    @Override
    public int getCount() {
        return mViewList.size();//返回页卡的数量
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;//官方提示这么写

    }

    //instantiateItem该方法的功能是创建指定位置的页面视图。
    //finishUpdate（ViewGroup）返回前，页面应该保证被构造好
    // 返回值：返回一个对应该页面的object，这个不一定必须是View，但是应该是对应页面的一些其他容器
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mViewList.get(position));
        return mViewList.get(position);
    }

    //该方法的功能是移除一个给定位置的页面。
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViewList.get(position));
    }
}
