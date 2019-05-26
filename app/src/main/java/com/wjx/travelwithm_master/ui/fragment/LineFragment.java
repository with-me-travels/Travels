package com.wjx.travelwithm_master.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wjx.travelwithm_master.R;
import com.wjx.travelwithm_master.adapters.LineRecAdapter;
import com.wjx.travelwithm_master.base.BaseFragment;
import com.wjx.travelwithm_master.presenter.LinePresenter;
import com.wjx.travelwithm_master.ui.bean.MiLineBean;
import com.wjx.travelwithm_master.view.LineView;

import butterknife.BindView;

public class LineFragment extends BaseFragment<LineView, LinePresenter<LineView>> implements LineView {

    @BindView(R.id.rec)
    RecyclerView mRec;
    private int page=1;
    private String header="JVy0IvZamK7f7FBZLKFtoniiixKMlnnJ6dWZ6NlsY4HGsxcAA9qvFo8yacHCKHE8YAcd0UF9L59nEm7zk9AUixee0Hl8EeWA880c0ikZBW0KEYuxQy5Z9NP3BNoBi3o3Q0g";
    private LineRecAdapter dynamicRecAdapter;

    @Override
    protected void initData() {
        Bundle arguments = getArguments();
        int banmiId = arguments.getInt("banmiId");
        if(String.valueOf(banmiId)!=null){
            mPresenter.getDynamicModelData(String.valueOf(banmiId),page,header);
        }
        mRec.setLayoutManager(new LinearLayoutManager(getContext()));
        dynamicRecAdapter = new LineRecAdapter(getContext());
        mRec.setAdapter(dynamicRecAdapter);
    }

    @Override
    protected LinePresenter<LineView> createPresenter() {
        return new LinePresenter<>();
    }

    @Override
    protected int createLayut() {
        return R.layout.fragment_line;
    }

    @Override
    public void showProgressbar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void requestDynamicData(MiLineBean miDynamicBean) {
        if(miDynamicBean!=null){
            dynamicRecAdapter.setRoutesBeans(miDynamicBean.getResult().getRoutes());
        }
    }

    @Override
    public void requestFailed(String errorMsg) {

    }
}
