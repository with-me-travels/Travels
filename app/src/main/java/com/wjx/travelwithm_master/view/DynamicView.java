package com.wjx.travelwithm_master.view;

import com.wjx.travelwithm_master.base.BaseView;
import com.wjx.travelwithm_master.ui.bean.MiDynamicBean;

public interface DynamicView extends BaseView {

    void requestMiDynamicSuccess(MiDynamicBean miDynamicBean);

    void requestFailed(String errorMsg);
}
