package com.wjx.travelwithm_master.presenter;

import com.wjx.travelwithm_master.base.BasePresenter;
import com.wjx.travelwithm_master.model.DynamicModel;
import com.wjx.travelwithm_master.ui.api.ResultCallBack;
import com.wjx.travelwithm_master.ui.bean.MiDynamicBean;
import com.wjx.travelwithm_master.view.DynamicView;

public class DynamicPresenter<V extends DynamicView> extends BasePresenter<DynamicView> {

    private DynamicModel dynamicModel=new DynamicModel();

    @Override
    protected void initModel() {
        mModels.add(dynamicModel);
    }

    public void getMoudelRequestData(String id, int page, String header){
        dynamicModel.requestDynamicData(new ResultCallBack<MiDynamicBean>() {
            @Override
            public void onSuccess(MiDynamicBean bean) {
                mView.requestMiDynamicSuccess(bean);
            }

            @Override
            public void onFail(String msg) {
                mView.requestFailed(msg);
            }
        },id,page,header);
    }
}
