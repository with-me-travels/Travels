package com.wjx.travelwithm_master.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wjx.travelwithm_master.R;

public class BoxContentActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mBoxcontentReturnIv;
    private Toolbar mBoxcontentToobar;
    /**
     * 2018/07/25
     */
    private TextView mTvBoxContent1;
    /**
     * 2018/07/25
     */
    private TextView mTvBoxContent2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box_content);
        initView();
    }

    private void initView() {
        mBoxcontentReturnIv = (ImageView) findViewById(R.id.boxcontent_return_iv);
        mBoxcontentReturnIv.setOnClickListener(this);
        mBoxcontentToobar = (Toolbar) findViewById(R.id.boxcontent_toobar);
        setSupportActionBar(mBoxcontentToobar);
        mTvBoxContent1 = (TextView) findViewById(R.id.tv_box_content1);
        mTvBoxContent2 = (TextView) findViewById(R.id.tv_box_content2);

        Intent intent = getIntent();
        String time = intent.getStringExtra("time");
        mTvBoxContent1.setText(time);
        mTvBoxContent2.setText(time);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.boxcontent_return_iv:
                finish();
                break;
        }
    }
}
