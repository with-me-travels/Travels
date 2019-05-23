package com.wjx.travelwithm_master.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;
import com.wjx.travelwithm_master.R;
import com.wjx.travelwithm_master.ui.activity.BoxContentActivity;
import com.wjx.travelwithm_master.ui.bean.NationBean;

import java.util.ArrayList;

public class NationAdapter extends RecyclerView.Adapter {
    private ArrayList<NationBean> mList;
    private Context mContext;

    public NationAdapter(ArrayList<NationBean> list, Context context) {
        mList = list;
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.layout_nation_item, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, final int i) {
        final ViewHolder holder = (ViewHolder) viewHolder;
        NationBean nationBean = mList.get(i);

        RequestOptions requestOptions = new RequestOptions().circleCrop();
        Glide.with(mContext).load(R.drawable.orgenback).apply(requestOptions).into(holder.mNationItemIv);

        holder.mNationItemName.setText(nationBean.getName());
        holder.mNationItemTime.setText(nationBean.getTime());
        holder.mNationItemTitle.setText(nationBean.getTitle());

        holder.mNationeDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //删除点击的条目
                mList.remove(i);
                //刷新适配器
                notifyDataSetChanged();
                //关闭窗口
                holder.mSwmenulayout.quickClose();
            }
        });
        holder.mNationrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, BoxContentActivity.class);
                intent.putExtra("time", mList.get(i).getTime());
                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public static
    class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView mNationItemIv;
        public TextView mNationItemName;
        public TextView mNationItemTime;
        public TextView mNationItemTitle;
        public Button mNationeDelete;
        public SwipeMenuLayout mSwmenulayout;
        public RelativeLayout mNationrl;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.mNationItemIv = (ImageView) rootView.findViewById(R.id.nation_item_iv);
            this.mNationItemName = (TextView) rootView.findViewById(R.id.nation_item_name);
            this.mNationItemTime = (TextView) rootView.findViewById(R.id.nation_item_time);
            this.mNationItemTitle = (TextView) rootView.findViewById(R.id.nation_item_title);
            this.mNationeDelete = (Button) rootView.findViewById(R.id.natione_delete);
            this.mSwmenulayout = (SwipeMenuLayout) rootView.findViewById(R.id.swmenulayout);
            this.mNationrl = (RelativeLayout) rootView.findViewById(R.id.nation_rl);
        }

    }
}
