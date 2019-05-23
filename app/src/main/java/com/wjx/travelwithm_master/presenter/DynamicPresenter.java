package com.wjx.travelwithm_master.presenter;

import com.wjx.travelwithm_master.base.BasePresenter;
import com.wjx.travelwithm_master.model.DynamicModel;
import com.wjx.travelwithm_master.view.DynamicView;

public class DynamicPresenter<V extends DynamicView> extends BasePresenter<DynamicView> {

    private DynamicModel dynamicModel=new DynamicModel();

    @Override
    protected void initModel() {
        mModels.add(dynamicModel);
    }
}
