package com.wjx.travelwithm_master.ui.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.text.TextUtils;

import com.wjx.travelwithm_master.R;
import com.wjx.travelwithm_master.base.Constants;
import com.wjx.travelwithm_master.base.SimpleActivity;
import com.wjx.travelwithm_master.utils.SpUtil;

public class BeginActivity extends SimpleActivity {

    private CountDownTimer mTimer;

    @Override
    protected void initData() {
        //倒计时
        mTimer = new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }
            @Override
            public void onFinish() {
               jump();
            }
        }.start();


    }

    @Override
    protected int createLayout() {
        return R.layout.activity_begin;
    }

    //跳转页面
    private void jump() {
        mTimer.cancel();
        startActivity(new Intent(this,SplashActivity.class));
        if (!TextUtils.isEmpty((String) SpUtil.getParam(Constants.TOKEN,""))){
            startActivity(new Intent(this,MainActivity.class));
        }else if ((boolean) SpUtil.getParam(Constants.BEGIN,false)){
            startActivity(new Intent(this,LoginActivity.class));
        }else {
            startActivity(new Intent(this,SplashActivity.class));
        }
        finish();
    }
}
