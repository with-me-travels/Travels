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
public class MessageFragment extends Fragment {


    private View view;
    private RecyclerView mMessageRlv;

    public MessageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_message, container, false);
        initView(inflate);
        initData();
        return inflate;
    }

    private void initData() {
        ArrayList<NationBean> list = new ArrayList<>();
        list.add(new NationBean("消息回复", "2019/05/21", "张某回复了你的留言"));
        list.add(new NationBean("系统通知", "2019/05/21", "祁某回复了你的留言"));
        list.add(new NationBean("系统通知", "2019/05/21", "方某回复了你的留言"));


        NationAdapter nationAdapter = new NationAdapter(list, getContext());
        mMessageRlv.setAdapter(nationAdapter);
        mMessageRlv.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void initView(View inflate) {
        mMessageRlv = (RecyclerView) inflate.findViewById(R.id.message_rlv);
    }
}
