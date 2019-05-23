package com.wjx.travelwithm_master.presenter;

import com.wjx.travelwithm_master.base.BasePresenter;
import com.wjx.travelwithm_master.model.WithMModel;
import com.wjx.travelwithm_master.ui.api.ResultCallBack;
import com.wjx.travelwithm_master.ui.bean.MiListBean;
import com.wjx.travelwithm_master.view.WithMView;

public class WithMPresenter<V extends WithMView> extends BasePresenter<WithMView> {

    private WithMModel withMModel=new WithMModel();

    @Override
    protected void initModel() {
        mModels.add(withMModel);
    }

    public void getWithMModelRequestData(int page,String header){
        withMModel.requestWithMDataSuccess(new ResultCallBack<MiListBean>() {
            @Override
            public void onSuccess(MiListBean bean) {
                mView.requestWithMDataSuccess(bean);
            }

            @Override
            public void onFail(String msg) {
                mView.requestFailed(msg);
            }
        },page,header);
    }
}
