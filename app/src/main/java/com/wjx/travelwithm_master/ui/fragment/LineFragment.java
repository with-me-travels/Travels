package com.wjx.travelwithm_master.ui.fragment;

import com.wjx.travelwithm_master.R;
import com.wjx.travelwithm_master.base.BaseFragment;
import com.wjx.travelwithm_master.presenter.LinePresenter;
import com.wjx.travelwithm_master.view.LineView;

public class LineFragment extends BaseFragment<LineView, LinePresenter<LineView>> {
    @Override
    protected LinePresenter<LineView> createPresenter() {
        return new LinePresenter<>();
    }

    @Override
    protected int createLayut() {
        return R.layout.fragment_line;
    }

    @Override
    public void showProgressbar() {

    }

    @Override
    public void hideProgressBar() {

    }
}
