package com.wjx.travelwithm_master.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wjx.travelwithm_master.R;
import com.wjx.travelwithm_master.adapters.DynamicRecAdapter;
import com.wjx.travelwithm_master.base.BaseFragment;
import com.wjx.travelwithm_master.presenter.DynamicPresenter;
import com.wjx.travelwithm_master.view.DynamicView;

import butterknife.BindView;

public class DynamicFragment extends BaseFragment<DynamicView, DynamicPresenter<DynamicView>> {

    @BindView(R.id.rec)
    RecyclerView mRec;
    private int page=1;
    private String header="JVy0IvZamK7f7FBZLKFtoniiixKMlnnJ6dWZ6NlsY4HGsxcAA9qvFo8yacHCKHE8YAcd0UF9L59nEm7zk9AUixee0Hl8EeWA880c0ikZBW0KEYuxQy5Z9NP3BNoBi3o3Q0g";

    @Override
    protected void initData() {
    }

    @Override
    protected DynamicPresenter<DynamicView> createPresenter() {
        return new DynamicPresenter<>();
    }

    @Override
    protected int createLayut() {
        return R.layout.fragment_dynamic;
    }

    @Override
    public void showProgressbar() {

    }

    @Override
    public void hideProgressBar() {

    }
}
