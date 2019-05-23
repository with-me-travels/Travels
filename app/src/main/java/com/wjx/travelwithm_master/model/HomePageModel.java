package com.wjx.travelwithm_master.model;

import com.wjx.travelwithm_master.base.BaseModel;
import com.wjx.travelwithm_master.ui.api.ApiService;
import com.wjx.travelwithm_master.ui.api.ResultCallBack;
import com.wjx.travelwithm_master.ui.bean.CircuitBean;
import com.wjx.travelwithm_master.utils.ToastUtil;
import com.wjx.travelwithm_master.utils.net.BaseObserver;
import com.wjx.travelwithm_master.utils.net.HttpUtils;
import com.wjx.travelwithm_master.utils.net.RxUtils;

import io.reactivex.disposables.Disposable;

public class HomePageModel extends BaseModel {

    public void getData(int page, String header, final ResultCallBack<CircuitBean> callBack) {
        HttpUtils.getInstance().getApiserver(ApiService.banmiUrl, ApiService.class)
                .getCircuitData(page, header)
                .compose(RxUtils.<CircuitBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<CircuitBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(CircuitBean circuitBean) {
                        callBack.onSuccess(circuitBean);
                    }
                });
    }
}
