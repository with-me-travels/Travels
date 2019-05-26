package com.wjx.travelwithm_master.model;

import com.wjx.travelwithm_master.base.BaseModel;
import com.wjx.travelwithm_master.ui.api.ApiService;
import com.wjx.travelwithm_master.ui.api.ResultCallBack;
import com.wjx.travelwithm_master.ui.bean.MiLineBean;
import com.wjx.travelwithm_master.utils.net.BaseObserver;
import com.wjx.travelwithm_master.utils.net.HttpUtils;
import com.wjx.travelwithm_master.utils.net.RxUtils;

import io.reactivex.disposables.Disposable;


public class LineModel extends BaseModel {

    public void requestDynamicData(final ResultCallBack<MiLineBean> resultCallBack, String banmiId, int page, String header){
        ApiService apiserver = HttpUtils.getInstance().getApiserver(ApiService.banmiUrl, ApiService.class);
        apiserver.getMiLineData(banmiId,page,header)
                .compose(RxUtils.<MiLineBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<MiLineBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(MiLineBean miDynamicBean) {
                        resultCallBack.onSuccess(miDynamicBean);
                    }
                });
    }
}
