package com.wjx.travelwithm_master.adapters;

import android.content.Context;
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
import com.wjx.travelwithm_master.R;
import com.wjx.travelwithm_master.ui.bean.MiLineBean;

import java.util.ArrayList;
import java.util.List;

public class LineRecAdapter extends RecyclerView.Adapter<LineRecAdapter.ViewHolder> {

    private List<MiLineBean.ResultBean.RoutesBean> routesBeans=new ArrayList<>();
    private Context context;

    public LineRecAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(context).inflate(R.layout.fragment_line_recycler_view,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Glide.with(context).load(routesBeans.get(i).getCardURL()).into(viewHolder.dynamic_img);
        viewHolder.dynamic_city.setText(routesBeans.get(i).getCity());
        viewHolder.dynamic_title.setText(routesBeans.get(i).getTitle());
        viewHolder.dynamic_price.setText("¥"+routesBeans.get(i).getPrice());
        viewHolder.dynamic_rl.getBackground().setAlpha(150);
        viewHolder.dynamic_intro.setText(routesBeans.get(i).getIntro());
        viewHolder.sequence.setText(routesBeans.get(i).getSequence()+"人购买");

    }

    public void setRoutesBeans(List<MiLineBean.ResultBean.RoutesBean> routesBeans) {
        this.routesBeans = routesBeans;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return routesBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView dynamic_img;
        private TextView dynamic_city;
        private TextView dynamic_title;
        private TextView dynamic_intro;
        private Button dynamic_price;
        private RelativeLayout dynamic_rl;
        private TextView sequence;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dynamic_img=itemView.findViewById(R.id.dynamic_img);
            dynamic_city=itemView.findViewById(R.id.dynamic_city);
            dynamic_title=itemView.findViewById(R.id.dynamic_title);
            dynamic_intro=itemView.findViewById(R.id.dynamic_intro);
            dynamic_price=itemView.findViewById(R.id.dynamic_price);
            dynamic_rl=itemView.findViewById(R.id.dynamic_rl);
            sequence=itemView.findViewById(R.id.sequence);
        }
    }
}
