package com.wjx.travelwithm_master.model;

import com.wjx.travelwithm_master.base.BaseModel;
import com.wjx.travelwithm_master.ui.api.ApiService;
import com.wjx.travelwithm_master.ui.api.ResultCallBack;
import com.wjx.travelwithm_master.ui.bean.MiListBean;
import com.wjx.travelwithm_master.utils.net.BaseObserver;
import com.wjx.travelwithm_master.utils.net.HttpUtils;
import com.wjx.travelwithm_master.utils.net.RxUtils;

import io.reactivex.disposables.Disposable;

public class WithMModel extends BaseModel {

    public void requestWithMDataSuccess(final ResultCallBack<MiListBean> resultCallBack, int page, String header){

        ApiService apiserver = HttpUtils.getInstance().getApiserver(ApiService.banmiUrl, ApiService.class);
        apiserver.getMiListData(page,header)
                .compose(RxUtils.<MiListBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<MiListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(MiListBean miListBean) {
                        resultCallBack.onSuccess(miListBean);
                    }
                });
    }
}
