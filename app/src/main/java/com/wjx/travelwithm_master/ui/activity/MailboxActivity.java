package com.wjx.travelwithm_master.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.wjx.travelwithm_master.R;
import com.wjx.travelwithm_master.ui.fragment.Mailbox.MessageFragment;
import com.wjx.travelwithm_master.ui.fragment.Mailbox.NationFragment;

public class MailboxActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mMailboxReturnIv;
    /**
     * 通知
     */
    private TextView mMailboxTvNotification;
    private ImageView mMailboxIv1;
    /**
     * 消息
     */
    private TextView mMaiboxTvMessage;
    private ImageView mMailboxIv2;
    private Toolbar mMaiboxToobar;
    private TextView mMailboxPosi1;
    private TextView mMailboxPosi2;
    private FrameLayout mMailboxFly;
    private FragmentManager mFragmentManager;
    private NationFragment mNationFragment;
    private MessageFragment mMessageFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mailbox);
        mFragmentManager = getSupportFragmentManager();
        initView();
    }

    private void initView() {
        mMailboxReturnIv = (ImageView) findViewById(R.id.mailbox_return_iv);
        mMailboxReturnIv.setOnClickListener(this);
        mMailboxTvNotification = (TextView) findViewById(R.id.mailbox_tv_notification);
        mMailboxTvNotification.setOnClickListener(this);
        mMailboxIv1 = (ImageView) findViewById(R.id.mailbox_iv1);
        mMaiboxTvMessage = (TextView) findViewById(R.id.maibox_tv_message);
        mMaiboxTvMessage.setOnClickListener(this);
        mMailboxIv2 = (ImageView) findViewById(R.id.mailbox_iv2);
        mMaiboxToobar = (Toolbar) findViewById(R.id.maibox_toobar);
        setSupportActionBar(mMaiboxToobar);
        mMailboxPosi1 = (TextView) findViewById(R.id.mailbox_posi1);
        mMailboxPosi2 = (TextView) findViewById(R.id.mailbox_posi2);

        //设置圆形的图标
        RequestOptions requestOptions = new RequestOptions().circleCrop();
        Glide.with(this).load(R.drawable.orgenback).apply(requestOptions).into(mMailboxIv1);
        Glide.with(this).load(R.drawable.orgenback).apply(requestOptions).into(mMailboxIv2);

        //通知标题默认不显示
        mMailboxIv1.setVisibility(View.INVISIBLE);
        mMailboxIv2.setVisibility(View.VISIBLE);

        //默认显示
        mMailboxPosi1.setVisibility(View.VISIBLE);
        mMailboxPosi2.setVisibility(View.INVISIBLE);
        //初始化fragment
        iniFragment();


    }

    private void iniFragment() {
        mMailboxFly = (FrameLayout) findViewById(R.id.mailbox_fly);
        mNationFragment = new NationFragment();
        mMessageFragment = new MessageFragment();
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.add(R.id.mailbox_fly, mNationFragment);
        transaction.add(R.id.mailbox_fly, mMessageFragment);
        transaction.show(mNationFragment).hide(mMessageFragment).commit();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.mailbox_return_iv:
                finish();
                break;
            case R.id.mailbox_tv_notification:
                mMailboxIv1.setVisibility(View.INVISIBLE);
                mMailboxPosi1.setVisibility(View.VISIBLE);
                mMailboxPosi2.setVisibility(View.INVISIBLE);
                mFragmentManager.beginTransaction()
                        .show(mNationFragment)
                        .hide(mMessageFragment)
                        .commit();
                break;
            case R.id.maibox_tv_message:
                mFragmentManager.beginTransaction()
                        .show(mMessageFragment)
                        .hide(mNationFragment)
                        .commit();
                mMailboxIv2.setVisibility(View.INVISIBLE);
                mMailboxPosi2.setVisibility(View.VISIBLE);
                mMailboxPosi1.setVisibility(View.INVISIBLE);
                break;
        }
    }
}
