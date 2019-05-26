package com.wjx.travelwithm_master.ui.fragment.Mailbox;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wjx.travelwithm_master.R;
import com.wjx.travelwithm_master.adapters.NationAdapter;
import com.wjx.travelwithm_master.ui.bean.NationBean;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NationFragment extends Fragment {

    private View view;
//    private ListView mNationRlv;
    private RecyclerView mNationRlv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_nation, container, false);
        initView(inflate);
        initData();
        return inflate;
    }

    private void initData() {
        ArrayList<NationBean> list = new ArrayList<>();
        list.add(new NationBean("系统通知", "2018/05/21", "用户注册成功"));
        list.add(new NationBean("系统通知", "2018/05/21", "通过分享获得2米粒"));
        list.add(new NationBean("系统通知", "2018/05/21", "通过分享获得2米粒"));

        NationAdapter nationAdapter = new NationAdapter(list, getContext());
        mNationRlv.setLayoutManager(new LinearLayoutManager(getContext()));
        mNationRlv.setAdapter(nationAdapter);

    }

    private void initView(View inflate) {
        mNationRlv = (RecyclerView) inflate.findViewById(R.id.nation_rlv);

    }
}
