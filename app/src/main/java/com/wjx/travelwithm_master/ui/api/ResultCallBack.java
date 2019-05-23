package com.wjx.travelwithm_master.ui.api;

public interface ResultCallBack<T> {

    void onSuccess(T bean);

    void onFail(String msg);
}
