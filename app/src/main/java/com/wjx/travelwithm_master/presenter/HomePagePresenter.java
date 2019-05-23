package com.wjx.travelwithm_master.presenter;

import com.wjx.travelwithm_master.base.BasePresenter;
import com.wjx.travelwithm_master.model.HomePageModel;
import com.wjx.travelwithm_master.ui.api.ResultCallBack;
import com.wjx.travelwithm_master.ui.bean.CircuitBean;
import com.wjx.travelwithm_master.view.HomePageView;

public class HomePagePresenter extends BasePresenter<HomePageView> {

    private HomePageModel mModel;

    @Override
    protected void initModel() {
        mModel = new HomePageModel();
        mModels.add(mModel);
    }


    public void getData(int page, String header) {
        if (mModel != null) {
            mModel.getData(page,header,new ResultCallBack<CircuitBean>() {
                @Override
                public void onSuccess(CircuitBean bean) {
                    if (mView != null) {
                        mView.setData(bean);
                    }
                }

                @Override
                public void onFail(String msg) {

                }
            });
        }
    }
}
