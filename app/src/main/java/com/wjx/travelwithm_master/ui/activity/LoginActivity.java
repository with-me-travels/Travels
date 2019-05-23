package com.wjx.travelwithm_master.ui.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wjx.travelwithm_master.R;
import com.wjx.travelwithm_master.base.BaseActivity;
import com.wjx.travelwithm_master.presenter.LoginPresenter;
import com.wjx.travelwithm_master.utils.ToastUtil;
import com.wjx.travelwithm_master.view.LoginView;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginView, LoginPresenter> {


    @BindView(R.id.iv_bake)
    ImageView mIvBake;
    @BindView(R.id.tv_hello)
    TextView mTvHello;
    @BindView(R.id.tv_login)
    TextView mTvLogin;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.btn_send_verify)
    Button mBtnSendVerify;
    @BindView(R.id.iv_wechat)
    ImageView mIvWechat;
    @BindView(R.id.tv_wechat)
    TextView mTvWechat;
    @BindView(R.id.tv_protocol)
    TextView mTvProtocol;
    @BindView(R.id.tv_agree)
    TextView mTvAgree;
    @BindView(R.id.ll_or)
    LinearLayout mLlOr;
    private TextWatcher mTextWatcher;

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void initView() {
        super.initView();
        //下划线
        mTvAgree.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);

        //监听文字输入状态
        mTextWatcher = new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String phone = mEtPhone.getText().toString();
                Log.i("mazhuxi", "-1-onTextChanged-->" + phone + "<--");
                if (!TextUtils.isEmpty(mEtPhone.getText().toString().trim())) {
                    mBtnSendVerify.setBackground(getResources().getDrawable(R.drawable.login_btn_bg));
                } else {
                    mBtnSendVerify.setBackground(getResources().getDrawable(R.drawable.login_btn_unbg));
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Log.i(TAG, "-2-beforeTextChanged-->" + mWrite.getText().toString() + "<--");
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Log.i(TAG, "-3-afterTextChanged-->" + mWrite.getText().toString() + "<--");
            }
        };
        mEtPhone.addTextChangedListener(mTextWatcher);
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

    @OnClick({R.id.iv_bake, R.id.et_phone, R.id.btn_send_verify, R.id.iv_wechat, R.id.tv_wechat, R.id.tv_agree})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            default:
                break;
            case R.id.iv_bake:
                mIvBake.setVisibility(View.INVISIBLE);
                mTvHello.setText(getString(R.string.for_more_service));
                mTvLogin.setText(getString(R.string.login_app));

                mLlOr.setVisibility(View.VISIBLE);
                mIvWechat.setVisibility(View.VISIBLE);
                mTvWechat.setVisibility(View.VISIBLE);
                break;
            case R.id.et_phone:
                mEtPhone.setFocusable(true);
                mEtPhone.setFocusableInTouchMode(true);
                break;
            case R.id.btn_send_verify:
                String phone = mEtPhone.getText().toString().trim();
                String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
                if(phone.isEmpty()){
                    ToastUtil.showShort("请输入手机号");
                }else  if (phone.length() != 11) {
                    ToastUtil.showShort("手机号应为11位数");
                } else {
                    Pattern p = Pattern.compile(regex);
                    Matcher m = p.matcher(phone);
                    boolean isMatch = m.matches();
                    if (!isMatch) {
                        ToastUtil.showShort("请填入正确的手机号");
                    }else {
                        startActivity(new Intent(LoginActivity.this, VerifyActivity.class));
                    }
                }
                break;
            case R.id.iv_wechat:
                mIvBake.setVisibility(View.VISIBLE);
                mTvHello.setText(getString(R.string.for_more_wechat));
                mTvLogin.setText(getString(R.string.login_bind));

                mLlOr.setVisibility(View.INVISIBLE);
                mIvWechat.setVisibility(View.INVISIBLE);
                mTvWechat.setVisibility(View.INVISIBLE);
                break;
            case R.id.tv_wechat:
                mIvBake.setVisibility(View.VISIBLE);
                mTvHello.setText(getString(R.string.for_more_wechat));
                mTvLogin.setText(getString(R.string.login_bind));

                mLlOr.setVisibility(View.INVISIBLE);
                mIvWechat.setVisibility(View.INVISIBLE);
                mTvWechat.setVisibility(View.INVISIBLE);
                break;
            case R.id.tv_agree:
                break;
        }
    }
}
