package com.wjx.travelwithm_master.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wjx.travelwithm_master.R;
import com.wjx.travelwithm_master.base.BaseActivity;
import com.wjx.travelwithm_master.presenter.VerifyPresenter;
import com.wjx.travelwithm_master.utils.ToastUtil;
import com.wjx.travelwithm_master.view.VerifyView;
import com.wjx.travelwithm_master.widget.IdentifyingCodeView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VerifyActivity extends BaseActivity<VerifyView, VerifyPresenter> {
    @BindView(R.id.iv_bake)
    ImageView mIvBake;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.tv_verify)
    TextView mTvVerify;
    @BindView(R.id.identifyingCodeVie)
    IdentifyingCodeView mIdentifyingCodeVie;
    @BindView(R.id.tv_state)
    TextView mTvState;
    private int mSenfTime = 60;
    private int verifyTime = 0;
    private Timer mTimer;
    private Timer mTimer1;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 10) {
                mSenfTime--;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTvTime.setText(getString(R.string.verify_send) + "(" + mSenfTime + ")");
                        mTvTime.setTextColor(getResources().getColor(R.color.c_a9a9a9));
                        if (mSenfTime == 0) {
                            mTvTime.setText(getString(R.string.verify_send));
                            mTvTime.setTextColor(getResources().getColor(R.color.c_fa6a13));
                            mTimer.cancel();
                        }
                    }
                });

            } else if (msg.what == 20) {
                //设置验证码
                verifyTime--;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTvState.setText(getString(R.string.verify_state));
                        if (verifyTime == 0) {
                            mTvState.setText(verifyCode());
                            mTimer1.cancel();
                        }
                    }
                });

            }
        }
    };

    @Override
    protected VerifyPresenter createPresenter() {
        return new VerifyPresenter();
    }

    @Override
    protected void initView() {
        initSend();
        initVerify();
    }

    @Override
    protected void initListener() {
        super.initListener();
        mTvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSenfTime = 60;
                initSend();
            }
        });

        //返回按钮
        mIvBake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VerifyActivity.this, LoginActivity.class));
                finish();
            }
        });

        mIdentifyingCodeVie.setInputCompleteListener(new IdentifyingCodeView.InputCompleteListener() {
            @Override
            public void inputComplete() {
                if (mIdentifyingCodeVie.getTextContent().length() == mIdentifyingCodeVie.getTextCount()) {
                    Log.i("mazhuxi", "inputComplete: +++" + mIdentifyingCodeVie.getTextContent() + "******" + mTvState.getText().toString().trim());
                    if (mIdentifyingCodeVie.getTextContent().equals(mTvState.getText().toString().trim())) {
                        mIdentifyingCodeVie.setBackgroundEnter(true);
                        mIdentifyingCodeVie.setEditable(false);

                        mTvTime.setText(getString(R.string.verify_send));
                        mTvTime.setTextColor(getResources().getColor(R.color.c_fa6a13));
                        mTimer.cancel();

                        ToastUtil.showShort(getResources().getString(R.string.login_success));
                        startActivity(new Intent(VerifyActivity.this, MainActivity.class));
                        finish();
                    } else {
                        ToastUtil.showShort(getResources().getString(R.string.verify_error));
                    }
                }
            }

            @Override
            public void deleteContent() {
                // mIdentifyingCodeVie.clearAllText();
            }
        });
    }

    private void initSend() {
        //重新发送
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(10);
            }
        }, 1000, 1000);
    }

    private void initVerify() {
        mTimer1 = new Timer();
        mTimer1.schedule(new TimerTask() {
            @Override
            public void run() {
                verifyTime = new Random().nextInt(10);
                mHandler.sendEmptyMessage(20);
            }
        }, 1000, 1000);
    }

    @Override
    public void showProgressbar() {

    }

    @Override
    public void hideProgressBar() {

    }

    /**
     * 生成随机的验证码
     *
     * @return
     */
    public String verifyCode() {
        String str = "";
        char[] ch = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        /*
        , 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
                'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
                'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
         */
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            char num = ch[random.nextInt(ch.length)];
            str += num;
        }
        return str;
    }

    @Override
    protected int createLayout() {
        return R.layout.activity_verify;
    }
}
