package com.wjx.travelwithm_master.ui.activity;

import android.app.Person;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.wjx.travelwithm_master.R;
import com.wjx.travelwithm_master.base.Constants;
import com.wjx.travelwithm_master.base.SimpleActivity;
import com.wjx.travelwithm_master.utils.SpUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingNameActivity extends SimpleActivity {


    @BindView(R.id.setting_name_ok)
    TextView settingNameOk;
    @BindView(R.id.setting_name_toolBar)
    Toolbar settingNameToolBar;
    @BindView(R.id.setting_name_et)
    EditText settingNameEt;
    @BindView(R.id.setting_name_zero)
    TextView settingNameZero;
    private TextWatcher textWatcher;

    @Override
    protected int createLayout() {
        return R.layout.activity_setting_name;
    }

    @Override
    protected void initView() {

        // ToolBar初始化
        initToolBarView();

        initZeroView();

        String name = (String) SpUtil.getParam(Constants.NAME, "");
        if (!name.equals("未登录")) {
            settingNameEt.setText(name);
        }else {
            settingNameEt.setText("");
        }

    }

    // 名字多长 数字的值就是多少 最大限制27个字
    private void initZeroView() {
        /*int NameLength = settingNameEt.getText().toString().length();
        for (int i = 0; i < NameLength; i++) {
            settingNameZero.setText(i);
        }*/

        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int length = s.length();
                settingNameZero.setText(length + "");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        settingNameEt.addTextChangedListener(textWatcher);
    }

    private void initToolBarView() {
        settingNameToolBar.setTitle("");
        setSupportActionBar(settingNameToolBar);
        settingNameToolBar.setNavigationIcon(getResources().getDrawable(R.mipmap.back_white));
    }

    @Override
    protected void initListener() {
        // ToolBar点击事件
        ToolBarListener();
        // Ok点击事件
        OkListener();

    }

    private void OkListener() {
        settingNameOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 把修改后的名字传递回去
                String name = settingNameEt.getText().toString();
                Log.i("-----", "s: " + name);
                SpUtil.setParam(Constants.NAME, name);
                startActivity(new Intent(SettingNameActivity.this, PersonalInfomationActivity.class));
                finish();
            }
        });
    }

    private void ToolBarListener() {
        settingNameToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
