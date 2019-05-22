package com.wjx.travelwithm_master.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wjx.travelwithm_master.utils.NetConnectedUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<V extends BaseView, P extends BasePresenter> extends Fragment implements BaseView {
    protected P mPresenter;
    private Unbinder mBind;
    private EventBus mBus;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(createLayut(), null);
        mBind = ButterKnife.bind(this, inflate);
        mBus = EventBus.getDefault();
        if (!NetConnectedUtils.isNetConnected(BaseApp.getInstance())) {
            showProgressbar();
        } else {
            //获取P层对象
            mPresenter = createPresenter();
            if(mPresenter!=null){
                mPresenter.attachView(this);
            }
            initView();
            initData();
            initListener();
        }
        return inflate;
    }

    protected void initListener() {

    }

    protected void initData() {
    }

    protected void initView() {
    }

    protected abstract P createPresenter();

    protected abstract int createLayut();

    @Override
    public void onResume() {
        super.onResume();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.attachView(this);
            mPresenter = null;
        }

        if (mBind != null) {
            mBind.unbind();
        }
    }
}
