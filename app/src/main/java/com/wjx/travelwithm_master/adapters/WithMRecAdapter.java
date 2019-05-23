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
import com.wjx.travelwithm_master.ui.bean.MiListBean;

import java.util.ArrayList;
import java.util.List;

public class WithMRecAdapter extends RecyclerView.Adapter<WithMRecAdapter.ViewHolder> {

    private List<MiListBean.ResultBean.BanmiBean> banmiBeans=new ArrayList<>();
    private Context context;
    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public WithMRecAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(context).inflate(R.layout.fragment_with_m_recycler_view,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        Glide.with(context).load(banmiBeans.get(i).getPhoto()).into(viewHolder.banmi_touxiang);
        viewHolder.banmi_name.setText(banmiBeans.get(i).getName());
        viewHolder.banmi_guanzhu.setText(banmiBeans.get(i).getFollowing()+"人关注");
        viewHolder.banmi_location.setText(banmiBeans.get(i).getLocation());
        viewHolder.banmi_zuozhe.setText(banmiBeans.get(i).getOccupation());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnItemClick(i);
            }
        });
    }

    public void setBanmiBeans(List<MiListBean.ResultBean.BanmiBean> banmiBeans) {
        this.banmiBeans = banmiBeans;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return banmiBeans.size();
    }

    public void cleanList() {
        banmiBeans.clear();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView banmi_touxiang;
        private TextView banmi_name;
        private ImageView banmi_follow;
        private TextView banmi_guanzhu;
        private TextView banmi_location;
        private TextView banmi_zuozhe;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            banmi_touxiang=itemView.findViewById(R.id.banmi_touxiang);
            banmi_name=itemView.findViewById(R.id.banmi_name);
            banmi_follow=itemView.findViewById(R.id.banmi_follow);
            banmi_location=itemView.findViewById(R.id.banmi_location);
            banmi_zuozhe=itemView.findViewById(R.id.banmi_zuozhe);
            banmi_guanzhu=itemView.findViewById(R.id.banmi_guanzhu);
        }
    }

    public interface OnItemClickListener{
        void OnItemClick(int position);
    }
}
