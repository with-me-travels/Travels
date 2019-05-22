package com.wjx.travelwithm_master.ui.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.wjx.travelwithm_master.R;
import com.wjx.travelwithm_master.base.Constants;
import com.wjx.travelwithm_master.base.SimpleActivity;
import com.wjx.travelwithm_master.utils.SpUtil;
import com.wjx.travelwithm_master.widget.PreviewIndicator;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class SplashActivity extends SimpleActivity {

    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.previewIndicator)
    PreviewIndicator mPreviewIndicator;
    @BindView(R.id.immediately)
    Button mImmediately;
    private ArrayList<View> mViews;

    @Override
    protected void initData() {
        mPreviewIndicator.initSize(80, 32, 6);
        mPreviewIndicator.setNumbers(3);

        //创建pager集合
        mViews = new ArrayList<>();
        mViews.add(View.inflate(SplashActivity.this, R.layout.activity_splash_item1, null));
        mViews.add(View.inflate(SplashActivity.this, R.layout.activity_splash_item2, null));
        mViews.add(View.inflate(SplashActivity.this, R.layout.activity_splash_item3, null));

        //设置适配器
        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return mViews.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
                return view == (View) o;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                View view = mViews.get(position);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView(mViews.get(position));
            }
        });

        //和指示器绑定
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 2) {
                    mPreviewIndicator.setVisibility(View.GONE);
                    mImmediately.setVisibility(View.VISIBLE);
                } else {
                    mPreviewIndicator.setVisibility(View.VISIBLE);
                    mImmediately.setVisibility(View.GONE);
                }
                mPreviewIndicator.setSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    protected int createLayout() {
        return R.layout.activity_splash;
    }

    @OnClick(R.id.immediately)
    public void onViewClicked() {
        SpUtil.setParam(Constants.BEGIN,true);
        startActivity(new Intent(SplashActivity.this,LoginActivity.class));
        finish();
    }
}
