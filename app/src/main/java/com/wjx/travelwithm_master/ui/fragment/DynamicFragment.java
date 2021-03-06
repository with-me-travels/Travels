package com.wjx.travelwithm_master.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wjx.travelwithm_master.R;
import com.wjx.travelwithm_master.adapters.DynamicRecAdapter;
import com.wjx.travelwithm_master.base.BaseFragment;
import com.wjx.travelwithm_master.presenter.DynamicPresenter;
import com.wjx.travelwithm_master.ui.bean.MiDynamicBean;
import com.wjx.travelwithm_master.view.DynamicView;

import java.util.ArrayList;

import butterknife.BindView;

public class DynamicFragment extends BaseFragment<DynamicView, DynamicPresenter<DynamicView>> implements DynamicView{

    @BindView(R.id.rec)
    RecyclerView mRec;
    private ArrayList<MiDynamicBean.ResultBean.ActivitiesBean> activitiesBeans;
    private DynamicRecAdapter dynamicRecAdapter;
    private int page=1;
    private String header="JVy0IvZamK7f7FBZLKFtoniiixKMlnnJ6dWZ6NlsY4HGsxcAA9qvFo8yacHCKHE8YAcd0UF9L59nEm7zk9AUixee0Hl8EeWA880c0ikZBW0KEYuxQy5Z9NP3BNoBi3o3Q0g";


    @Override
    protected void initData() {

        Bundle arguments = getArguments();
        int banmiId = arguments.getInt("banmiId");
        if(String.valueOf(banmiId)!=null){
            mPresenter.getMoudelRequestData(String.valueOf(banmiId),page,header);
        }

        activitiesBeans = new ArrayList<>();
        mRec.setLayoutManager(new LinearLayoutManager(getContext()));
        dynamicRecAdapter = new DynamicRecAdapter(getContext());
        mRec.setAdapter(dynamicRecAdapter);
    }

    @Override
    protected DynamicPresenter<DynamicView> createPresenter() {
        return new DynamicPresenter<>();
    }

    @Override
    protected int createLayut() {
        return R.layout.fragment_dynamic;
    }

    @Override
    public void showProgressbar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void requestMiDynamicSuccess(MiDynamicBean miDynamicBean) {
        if (miDynamicBean!=null){
            dynamicRecAdapter.setActivitiesBeans(miDynamicBean.getResult().getActivities());
        }
    }

    @Override
    public void requestFailed(String errorMsg) {

    }
}
