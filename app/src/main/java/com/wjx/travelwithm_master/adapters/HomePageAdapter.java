package com.wjx.travelwithm_master.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.wjx.travelwithm_master.R;
import com.wjx.travelwithm_master.ui.bean.CircuitBean;

import java.util.ArrayList;

public class HomePageAdapter extends RecyclerView.Adapter {
    private final Context mContext;
    private ArrayList<CircuitBean.ResultBean.RoutesBean> mData;
    private int TYPE_ROUTES = 1;
    private int TYPE_BUNDLE = 2;

    public HomePageAdapter(Context context, ArrayList<CircuitBean.ResultBean.RoutesBean> data) {
        mContext = context;
        mData = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(mContext);
        if (i == TYPE_ROUTES) {
            View inflate = from.inflate(R.layout.home_page_route_item, null);
            return new RoutesVh(inflate);
        } else {
            View inflate = from.inflate(R.layout.home_page_bundle_item, null);
            return new BundleVh(inflate);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        int itemViewType = getItemViewType(position);
        CircuitBean.ResultBean.RoutesBean routesBean = mData.get(position);
        RoundedCorners roundedCorners = new RoundedCorners(20);
        RequestOptions requestOptions = RequestOptions.bitmapTransform(roundedCorners);
        if (viewHolder instanceof RoutesVh) {
            RoutesVh routesVh = (RoutesVh) viewHolder;
            routesVh.mCity.setText(routesBean.getCity());
            routesVh.mIntro.setText(routesBean.getIntro());
            routesVh.mPrice.setText("¥" + routesBean.getPrice());
            routesVh.mTitle.setText(routesBean.getTitle());
            routesVh.mPriceInCents.setText(routesBean.getPriceInCents() + "人购买");
            Glide.with(mContext).load(routesBean.getCardURL()).apply(requestOptions).into(routesVh.mCardURL);
        } else{
            BundleVh bundleVh = (BundleVh) viewHolder;
            Glide.with(mContext).load(routesBean.getCardURL()).apply(requestOptions).into(bundleVh.mCardURL);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mData.get(position).getType().equals("route")) {
            return TYPE_BUNDLE;
        } else {
            return TYPE_ROUTES;
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(ArrayList<CircuitBean.ResultBean.RoutesBean> data) {
        mData = data;
    }

    //routes的数据
    class RoutesVh extends RecyclerView.ViewHolder {

        private final ImageView mCardURL;
        private final TextView mCity;
        private final TextView mIntro;
        private final TextView mTitle;
        private final TextView mPriceInCents;
        private final TextView mPrice;

        public RoutesVh(@NonNull View itemView) {
            super(itemView);
            mCardURL = itemView.findViewById(R.id.home_page_item_cardURL);
            mCity = itemView.findViewById(R.id.home_page_item_city);
            mIntro = itemView.findViewById(R.id.home_page_item_intro);
            mTitle = itemView.findViewById(R.id.home_page_item_title);
            mPriceInCents = itemView.findViewById(R.id.home_page_item_priceInCents);
            mPrice = itemView.findViewById(R.id.home_page_item_price);
        }
    }

    //bundle的数据
    class BundleVh extends RecyclerView.ViewHolder {

        private final ImageView mCardURL;

        public BundleVh(@NonNull View itemView) {
            super(itemView);
            mCardURL = itemView.findViewById(R.id.home_page_bundle_item_cardURL);
        }
    }
}
