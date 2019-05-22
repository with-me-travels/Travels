package com.wjx.travelwithm_master.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class SimpleActivity extends AppCompatActivity {

    private Unbinder mBind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View inflate = LayoutInflater.from(this).inflate(createLayout(), null);
        setContentView(inflate);
        mBind = ButterKnife.bind(this);
        viewCreate(inflate);
        initView();
        initData();
        initListener();
    }

    protected void initListener() {
    }

    protected void initData() {
    }

    protected void initView() {
    }

    protected void viewCreate(View inflate) {
    }

    protected abstract int createLayout();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBind != null) {
            mBind.unbind();
        }
    }
}
