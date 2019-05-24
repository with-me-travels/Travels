package com.wjx.travelwithm_master.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.wjx.travelwithm_master.R;
import com.wjx.travelwithm_master.base.Constants;
import com.wjx.travelwithm_master.base.SimpleActivity;
import com.wjx.travelwithm_master.utils.SpUtil;
import com.wjx.travelwithm_master.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonalInfomationActivity extends SimpleActivity {

    @BindView(R.id.personal_toolBar)
    Toolbar personal_mToolbar;
    @BindView(R.id.my_heard_img)
    ImageView mMyHeardImageView;
    @BindView(R.id.my_heard_name)
    TextView mMyHeardName;
    @BindView(R.id.my_heard_sex)
    TextView mMyHeardSex;
    @BindView(R.id.my_heard_info)
    TextView mMyHeardInfo;
    @BindView(R.id.my_heard_changes)
    TextView mMyHeardChanges;
    @BindView(R.id.my_heard_setPhone)
    TextView mMyHeardSetPhone;
    @BindView(R.id.backAll)
    Button backAll;
    private String name;


    @Override
    protected int createLayout() {
        return R.layout.activity_personal_infomation;
    }

    @Override
    protected void initView() {

        // 初始化toolBar
        initToolBarView();

        // 获取数据并且赋值
        getMessage();

    }

    private void getMessage() {

        name = (String) SpUtil.getParam(Constants.NAME, "");

        if (name.equals("未登录")) {
            backAll.setVisibility(View.GONE);
            mMyHeardInfo.setText("快来登陆设置你的个性签名吧");
        } else {
            backAll.setVisibility(View.VISIBLE);
            String info = (String) SpUtil.getParam(Constants.INFO, "");
            mMyHeardInfo.setText(info);
        }

        mMyHeardName.setText(name);

    }

    private void initToolBarView() {
        personal_mToolbar.setTitle("");
        setSupportActionBar(personal_mToolbar);
        personal_mToolbar.setNavigationIcon(getResources().getDrawable(R.mipmap.back_white));
    }

    @Override
    protected void initListener() {
        // ToolBar图标点击事件
        ToolBarListener();
        // 修改名字
        nameListenre();
        // 修改签名
        infoListener();
        //退出登陆
        BackListener();

    }

    private void infoListener() {

        mMyHeardInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.equals("未登录")) {
                    ToastUtil.showShort("请先登录");
                } else {
                    startActivity(new Intent(PersonalInfomationActivity.this, SettingInfoActivity.class));
                }
            }
        });
    }

    private void BackListener() {
        backAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameBack = "未登录";
                String infoBack = "快来登陆设置你的个性签名吧";
                SpUtil.setParam(Constants.NAME, nameBack);
                SpUtil.setParam(Constants.INFO, infoBack);
                startActivity(new Intent(PersonalInfomationActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    private void nameListenre() {
        mMyHeardName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PersonalInfomationActivity.this, SettingNameActivity.class));
            }
        });

    }

    private void ToolBarListener() {
        personal_mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = mMyHeardName.getText().toString();
//                Log.i("----------", "onClick: "+s);
                SpUtil.setParam(Constants.NAME, s);
                startActivity(new Intent(PersonalInfomationActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}
