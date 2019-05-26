package com.wjx.travelwithm_master.presenter;

import com.wjx.travelwithm_master.base.BasePresenter;
import com.wjx.travelwithm_master.model.LineModel;
import com.wjx.travelwithm_master.ui.api.ResultCallBack;
import com.wjx.travelwithm_master.ui.bean.MiLineBean;
import com.wjx.travelwithm_master.view.LineView;

public class LinePresenter<V extends LineView> extends BasePresenter<LineView> {

    private LineModel dynamicModel=new LineModel();

    @Override
    protected void initModel() {
        mModels.add(dynamicModel);
    }

    public void getDynamicModelData(String banmiId,int page,String header){
        dynamicModel.requestDynamicData(new ResultCallBack<MiLineBean>() {
            @Override
            public void onSuccess(MiLineBean bean) {
                mView.requestDynamicData(bean);
            }

            @Override
            public void onFail(String msg) {
                mView.requestFailed(msg);
            }
        },banmiId,page,header);
    }
}
