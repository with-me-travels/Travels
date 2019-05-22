package com.wjx.travelwithm_master.base;

import android.view.View;

import com.wjx.travelwithm_master.utils.NetConnectedUtils;

public abstract class BaseActivity<V extends BaseView, P extends BasePresenter> extends SimpleActivity implements BaseView {
    protected P mPresenter;

    @Override
    protected void viewCreate(View inflate) {
        super.viewCreate(inflate);
        if (!NetConnectedUtils.isNetConnected(BaseApp.getInstance())) {
            showProgressbar();
        } else {
            mPresenter = createPresenter();
            if(mPresenter!=null){
                mPresenter.attachView(this);
            }
        }

    }

    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
    }
}
