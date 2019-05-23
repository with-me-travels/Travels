package com.wjx.travelwithm_master.ui.activity;


import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wjx.travelwithm_master.R;
import com.wjx.travelwithm_master.base.BaseActivity;
import com.wjx.travelwithm_master.presenter.EmptyPresenter;
import com.wjx.travelwithm_master.ui.bean.MiListBean;
import com.wjx.travelwithm_master.ui.fragment.DynamicFragment;
import com.wjx.travelwithm_master.ui.fragment.LineFragment;
import com.wjx.travelwithm_master.view.EmptyView;

import java.lang.reflect.Field;

import butterknife.BindView;

public class WithMDetailsActivity extends BaseActivity<EmptyView, EmptyPresenter<EmptyView>> {

    @BindView(R.id.banmi_toolbar)
    Toolbar mBanmi_Toolbar;
    @BindView(R.id.banxiang_back)
    ImageView mBanxiang_Back;
    @BindView(R.id.banxiang_banphoto)
    ImageView mBanxiang_Banphoto;
    @BindView(R.id.banxiang_follow)
    ImageView mBanxiang_Follow;
    @BindView(R.id.banxiang_bantitle)
    TextView mBanxiang_Bantitle;
    @BindView(R.id.banxiang_guanzhu)
    TextView mBanxiang_Guanzhu;
    @BindView(R.id.banxiang_location)
    TextView mBanxiang_Location;
    @BindView(R.id.banxiang_desc)
    TextView mBanxiang_Desc;
    @BindView(R.id.banxiang_jianjie)
    TextView mBanxiang_Jianjie;
    @BindView(R.id.banxiang_tab)
    TabLayout mBanxiang_tab;
    private FragmentManager sFm;
    private DynamicFragment dynamicFragment;
    private LineFragment lineFragment;

    @Override
    protected void initView() {

        mBanmi_Toolbar.setTitle("");
        setSupportActionBar(mBanmi_Toolbar);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        MiListBean.ResultBean.BanmiBean banmiBeans = (MiListBean.ResultBean.BanmiBean) intent.getSerializableExtra("banmiBeans");

        if (banmiBeans != null) {
            Glide.with(this).load(banmiBeans.getPhoto()).into(mBanxiang_Banphoto);
            mBanxiang_Bantitle.setText(banmiBeans.getName());
            mBanxiang_Location.setText(banmiBeans.getLocation());
            mBanxiang_Guanzhu.setText(banmiBeans.getFollowing() + "人关注");
            mBanxiang_Desc.setText(banmiBeans.getOccupation());
            mBanxiang_Jianjie.setText("简介：" + banmiBeans.getIntroduction());
        }
        /*mBanxiang_tab.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(mBanxiang_tab,30,30);
            }
        });*/
        mBanxiang_tab.addTab(mBanxiang_tab.newTab().setText("动态"));
        mBanxiang_tab.addTab(mBanxiang_tab.newTab().setText("线路"));

        sFm = getSupportFragmentManager();
        FragmentTransaction fTran = sFm.beginTransaction();
        dynamicFragment = new DynamicFragment();
        lineFragment = new LineFragment();
        fTran.add(R.id.banxiang_frag, dynamicFragment);
        fTran.add(R.id.banxiang_frag, lineFragment);
        fTran.hide(lineFragment);
        fTran.commit();
    }

    @Override
    protected void initListener() {
        mBanxiang_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mBanxiang_tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FragmentTransaction fTran = sFm.beginTransaction();
                if (tab.getPosition()==0){
                    fTran.show(dynamicFragment);
                    fTran.hide(lineFragment);
                    fTran.commit();
                }else{
                    fTran.show(lineFragment);
                    fTran.hide(dynamicFragment);
                    fTran.commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    /*** 通过反射机制 修改TabLayout 的下划线长度*/
    public void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }
    @Override
    protected int createLayout() {
        return R.layout.activity_with_mdetails;
    }

    @Override
    protected EmptyPresenter<EmptyView> createPresenter() {
        return new EmptyPresenter<>();
    }

    @Override
    public void showProgressbar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
