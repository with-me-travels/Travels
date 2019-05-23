package com.wjx.travelwithm_master.ui.fragment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.wjx.travelwithm_master.R;
import com.wjx.travelwithm_master.adapters.WithMRecAdapter;
import com.wjx.travelwithm_master.base.BaseFragment;
import com.wjx.travelwithm_master.presenter.WithMPresenter;
import com.wjx.travelwithm_master.ui.activity.WithMDetailsActivity;
import com.wjx.travelwithm_master.ui.bean.MiListBean;
import com.wjx.travelwithm_master.view.WithMView;

import java.util.ArrayList;

import butterknife.BindView;

public class WithMFragment extends BaseFragment<WithMView, WithMPresenter<WithMView>> implements WithMView{

    @BindView(R.id.rec)
    RecyclerView mRec;
    @BindView(R.id.sm_with_m)
    SmartRefreshLayout mSm;
    private int page=1;
    private String header="JVy0IvZamK7f7FBZLKFtoniiixKMlnnJ6dWZ6NlsY4HGsxcAA9qvFo8yacHCKHE8YAcd0UF9L59nEm7zk9AUixee0Hl8EeWA880c0ikZBW0KEYuxQy5Z9NP3BNoBi3o3Q0g";
    private ArrayList<MiListBean.ResultBean.BanmiBean> banmiBeans;
    private WithMRecAdapter withMRecAdapter;

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        mPresenter.getWithMModelRequestData(page,header);

        banmiBeans = new ArrayList<>();
        mRec.setLayoutManager(new LinearLayoutManager(getContext()));
        withMRecAdapter = new WithMRecAdapter(getContext());
        mRec.setAdapter(withMRecAdapter);

    }

    @Override
    protected void initListener() {

        mSm.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

                page++;
                mPresenter.getWithMModelRequestData(page,header);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                banmiBeans.clear();
                withMRecAdapter.cleanList();
                page=1;
                mPresenter.getWithMModelRequestData(page,header);
            }
        });

        withMRecAdapter.setListener(new WithMRecAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                Intent intent = new Intent(getContext(), WithMDetailsActivity.class);
                intent.putExtra("banmiBeans",banmiBeans.get(position));
                startActivity(intent);
            }
        });
    }

    @Override
    protected WithMPresenter<WithMView> createPresenter() {
        return new WithMPresenter<>();
    }

    @Override
    protected int createLayut() {
        return R.layout.fragment_with_m;
    }

    @Override
    public void showProgressbar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void requestWithMDataSuccess(MiListBean miListBean) {
        if(miListBean!=null){
            banmiBeans.addAll(miListBean.getResult().getBanmi());
            withMRecAdapter.setBanmiBeans(banmiBeans);
            mSm.finishRefresh();
            mSm.finishLoadMore();
        }
    }

    private static final String TAG = "WithMFragment";

    @Override
    public void requestFailed(String errorMsg) {

        Log.e(TAG, "requestFailed: "+errorMsg );
    }
}
