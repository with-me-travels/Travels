package com.wjx.travelwithm_master.view;

import com.wjx.travelwithm_master.base.BaseView;
import com.wjx.travelwithm_master.ui.bean.MiLineBean;

public interface LineView extends BaseView {

    void requestDynamicData(MiLineBean miDynamicBean);

    void requestFailed(String errorMsg);
}
