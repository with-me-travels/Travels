package com.wjx.travelwithm_master.view;

import com.wjx.travelwithm_master.base.BaseView;
import com.wjx.travelwithm_master.ui.bean.MiListBean;

public interface WithMView extends BaseView {

    void requestWithMDataSuccess(MiListBean miListBean);

    void requestFailed(String errorMsg);
}
