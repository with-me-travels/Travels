package com.wjx.travelwithm_master.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wjx.travelwithm_master.R;
import com.wjx.travelwithm_master.ui.bean.MiDynamicBean;

import java.util.ArrayList;
import java.util.List;

public class DynamicRecAdapter extends RecyclerView.Adapter<DynamicRecAdapter.ViewHolder> {

    private List<MiDynamicBean.ResultBean.ActivitiesBean> activitiesBeans=new ArrayList<>();
    private Context context;

    public DynamicRecAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(context).inflate(R.layout.fragment_dynamic_recycler_view,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.date.setText(activitiesBeans.get(i).getDate());
        viewHolder.content.setText(activitiesBeans.get(i).getContent());
        if(activitiesBeans.get(i).getImages()==null){
            viewHolder.img.setVisibility(View.GONE);
        }else if(activitiesBeans.get(i).getImages().size()>0){
            Glide.with(context).load(activitiesBeans.get(i).getImages().get(0)).into(viewHolder.img);
        }
    }

    public void setActivitiesBeans(List<MiDynamicBean.ResultBean.ActivitiesBean> activitiesBeans) {
        this.activitiesBeans = activitiesBeans;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return activitiesBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView date;
        private TextView content;
        private ImageView img;
        private ImageView bg_line;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date=itemView.findViewById(R.id.date);
            content=itemView.findViewById(R.id.content);
            img=itemView.findViewById(R.id.img);
            bg_line=itemView.findViewById(R.id.bg_line);
        }
    }
}
