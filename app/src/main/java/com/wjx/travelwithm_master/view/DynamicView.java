package com.wjx.travelwithm_master.view;

import com.wjx.travelwithm_master.base.BaseView;
import com.wjx.travelwithm_master.ui.bean.CircuitBean;

public interface DynamicView extends BaseView {

    void requestDynamicData(CircuitBean circuitBean);

    void requestFailed(String errorMsg);
}
