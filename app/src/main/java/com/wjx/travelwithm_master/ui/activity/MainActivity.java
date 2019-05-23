package com.wjx.travelwithm_master.ui.activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.wjx.travelwithm_master.R;
import com.wjx.travelwithm_master.base.BaseApp;
import com.wjx.travelwithm_master.base.BaseFragment;
import com.wjx.travelwithm_master.base.SimpleActivity;
import com.wjx.travelwithm_master.ui.fragment.HomePageFragment;
import com.wjx.travelwithm_master.ui.fragment.WithMFragment;
import com.wjx.travelwithm_master.utils.ToastUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends SimpleActivity {

    @BindView(R.id.main_head_portrait)
    ImageButton mMainHeadPortrait;
    @BindView(R.id.main_toolbar)
    Toolbar mMainToolbar;
    @BindView(R.id.main_container)
    FrameLayout mMainContainer;
    @BindView(R.id.main_tab)
    TabLayout mMainTab;
    @BindView(R.id.main_dl)
    DrawerLayout mMainDl;
    @BindView(R.id.main_nv)
    NavigationView mMainNv;
    @BindView(R.id.main_letter_box)
    ImageButton mMainLetterBox;

    private int TYPE_HOMEPAGE = 0;
    private int TYPE_WITHM = 1;
    private FragmentManager mManager;
    private ArrayList<BaseFragment> mFragments;
    private ArrayList<Integer> mTitles;
    private int mPosition;
    private int mHideFragmentm;

    @Override
    protected int createLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        //Tablayout
        tab();

        //获取布局管理器
        mManager = getSupportFragmentManager();

        //设置toolbar
        mMainToolbar.setTitle("");
        setSupportActionBar(mMainToolbar);

        //fragment的集合
        initFragment();

        //默认添加第一个homepage页面
        addHomePageFragment();


    }

    private void initFragment() {
        mFragments = new ArrayList<>();
        mFragments.add(new HomePageFragment());
        mFragments.add(new WithMFragment());
    }

    private void addHomePageFragment() {
        FragmentTransaction transaction = mManager.beginTransaction();
        transaction.add(R.id.main_container, mFragments.get(TYPE_HOMEPAGE));
        transaction.commit();
    }

    @Override
    protected void initListener() {
        mMainTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //fragment下标
                mPosition = tab.getPosition();

                //tab绑定fragment
                switchFragment();

                //要隐藏的fragment下标
                mHideFragmentm = mPosition;
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void switchFragment() {
        //显示一个fragment
        BaseFragment fragment = mFragments.get(mPosition);
        //需要隐藏的fragment
        BaseFragment hideFragment = mFragments.get(mHideFragmentm);
        FragmentTransaction transaction = mManager.beginTransaction();
        if (!fragment.isAdded()) {
            transaction.add(R.id.main_container, fragment);
        }
        transaction.hide(hideFragment);
        transaction.show(fragment);
        transaction.commit();
    }

    private void tab() {
        //给tablayout设置图片和文字
        mMainTab.addTab(mMainTab.newTab().setCustomView(getTab(TYPE_HOMEPAGE)));
        mMainTab.addTab(mMainTab.newTab().setCustomView(getTab(TYPE_WITHM)));
    }

    private View getTab(int type) {
        View inflate = LayoutInflater.from(BaseApp.getInstance()).inflate(R.layout.main_tab_item, null);
        ImageView iv = inflate.findViewById(R.id.main_tab_item_iv);
        TextView tv = inflate.findViewById(R.id.main_tab_item_tv);

        if (type == TYPE_HOMEPAGE) {
            iv.setImageResource(R.drawable.main_home_page_selector);
            tv.setText(R.string.home_page);
        } else {
            iv.setImageResource(R.drawable.main_with_m_selector);
            tv.setText(R.string.with_m);
        }
        return inflate;
    }

    @OnClick({R.id.main_head_portrait,R.id.main_letter_box})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.main_head_portrait:
                mMainDl.openDrawer(GravityCompat.START);
//                ToastUtil.showShort("这是一个头像哦！！");
                break;
            case R.id.main_letter_box:
//                ToastUtil.showShort("1111111");
                Intent intent = new Intent(MainActivity.this,MailboxActivity.class);
                startActivity(intent);
                break;
        }
    }
}
