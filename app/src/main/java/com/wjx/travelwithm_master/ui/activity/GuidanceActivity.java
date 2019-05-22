package com.wjx.travelwithm_master.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.wjx.travelwithm_master.R;
import com.wjx.travelwithm_master.base.SimpleActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuidanceActivity extends SimpleActivity {

    @BindView(R.id.guidance_image)
    ImageView mGuidanceImage;

    @Override
    protected int createLayout() {
        return R.layout.activity_guidance;
    }

    @Override
    protected void initView() {

    }
}
