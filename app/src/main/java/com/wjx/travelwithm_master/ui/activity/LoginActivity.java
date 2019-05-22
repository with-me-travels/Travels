package com.wjx.travelwithm_master.ui.activity;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.wjx.travelwithm_master.R;
import com.wjx.travelwithm_master.base.BaseActivity;
import com.wjx.travelwithm_master.presenter.LoginPresenter;
import com.wjx.travelwithm_master.view.LoginView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginView, LoginPresenter> {

    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.btn_send_verify)
    Button mBtnSendVerify;
    @BindView(R.id.iv_wechat)
    ImageView mIvWechat;
    @BindView(R.id.tv_wechat)
    TextView mTvWechat;
    @BindView(R.id.tv_agree)
    TextView mTvAgree;

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void initView() {
        super.initView();
        mTvAgree.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG ); //下划线
    }

    @Override
    public void showProgressbar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    protected int createLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.et_phone, R.id.btn_send_verify, R.id.tv_agree})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_phone:

                break;
            case R.id.btn_send_verify:
                break;
            case R.id.tv_agree:
                break;
        }
    }
}
