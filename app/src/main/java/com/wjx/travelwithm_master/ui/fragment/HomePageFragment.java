package com.wjx.travelwithm_master.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.wjx.travelwithm_master.R;
import com.wjx.travelwithm_master.adapters.HomePageAdapter;
import com.wjx.travelwithm_master.base.BaseApp;
import com.wjx.travelwithm_master.base.BaseFragment;
import com.wjx.travelwithm_master.presenter.HomePagePresenter;
import com.wjx.travelwithm_master.ui.bean.CircuitBean;
import com.wjx.travelwithm_master.utils.NetConnectedUtils;
import com.wjx.travelwithm_master.utils.ToastUtil;
import com.wjx.travelwithm_master.view.HomePageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class HomePageFragment extends BaseFragment<HomePageView, HomePagePresenter> implements HomePageView {
    private static final String TAG = "HomePageFragment";
    @BindView(R.id.home_page_rlv)
    RecyclerView mHomePageRlv;
    @BindView(R.id.home_page_srl)
    SmartRefreshLayout mHomePageSrl;
    @BindView(R.id.home_page_ll)
    LinearLayout mHomePageLl;
    @BindView(R.id.home_page_tv)
    TextView mHomePageTv;

    private int mPage = 1;
    private String mHeader = "JVy0IvZamK7f7FBZLKFtoniiixKMlnnJ6dWZ6NlsY4HGsxcAA9qvFo8yacHCKHE8YAcd0UF9L59nEm7zk9AUixee0Hl8EeWA880c0ikZBW0KEYuxQy5Z9NP3BNoBi3o3Q0g";
    private HomePageAdapter mHomePageAdapter;
    private ArrayList<CircuitBean.ResultBean.RoutesBean> data = new ArrayList<>();

    @Override
    protected HomePagePresenter createPresenter() {
        return new HomePagePresenter();
    }

    @Override
    protected int createLayut() {
        return R.layout.fragment_home_page;
    }

    @Override
    public void showProgressbar() {
        mHomePageLl.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    protected void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(BaseApp.getInstance());
        mHomePageRlv.setLayoutManager(manager);
        mHomePageAdapter = new HomePageAdapter(getContext(), data);
        mHomePageRlv.setAdapter(mHomePageAdapter);
    }

    @Override
    protected void initData() {
        mPresenter.getData(mPage, mHeader);
//        Log.d(TAG, "page: " + mPage + ",header：" + mHeader);
    }

    @Override
    public void setData(CircuitBean bean) {
        Log.d(TAG, "CircuitBean: " + bean.getResult().getBanners().toString());
        if (bean != null && bean.getResult() != null && bean.getResult().getBanners() != null && bean.getResult().getRoutes() != null && bean.getResult().getRoutes().size() > 0 && bean.getResult().getBanners().size() > 0) {
            List<CircuitBean.ResultBean.RoutesBean> result = bean.getResult().getRoutes();
            data.addAll(result);
            mHomePageAdapter.setData(data);
            mHomePageAdapter.notifyDataSetChanged();
        }
    }

    @OnClick(R.id.home_page_tv)
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.home_page_tv:
                if (!NetConnectedUtils.isNetConnected(BaseApp.getInstance())) {
                    ToastUtil.showShort("请检查网络后重试！！！");
                } else {
                    mHomePageLl.setVisibility(View.GONE);
                    ToastUtil.showShort("访问数据");
//                    mPresenter.getData(mPage, mHeader);
                }
                break;
        }
    }
}
