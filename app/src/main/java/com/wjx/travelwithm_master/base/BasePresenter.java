package com.wjx.travelwithm_master.base;

import java.lang.ref.WeakReference;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.WeakHashMap;

public abstract class BasePresenter<V extends BaseView> {
    //弱引用，防止内存泄漏
    private WeakReference<V> mWeakReference;

    protected V mView;

    public BasePresenter() {
        initModel();
    }

    protected abstract void initModel();

    protected ArrayList<BaseModel> mModels = new ArrayList<>();

    public void attachView(V v) {
        mWeakReference = new WeakReference<V>(v);
        this.mView = mWeakReference.get();
    }

    public void detachView() {
        mView = null;
        if (mModels.size() > 0) {
            for (BaseModel model : mModels) {
                model.onDestory();
            }
        }
    }
}
