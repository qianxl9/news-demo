package com.example.jh.nes_demo.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jh.nes_demo.Application.MyApplicatioin;
import com.example.jh.nes_demo.Bean.ZmBean;
import com.example.jh.nes_demo.R;
import com.example.jh.nes_demo.Util.GlideUtil;
import com.example.jh.nes_demo.Util.OnitemClickListener;

import java.util.List;

/**
 * Created by jh on 2016/8/25.
 */
public class ImagerAdapter extends RecyclerView.Adapter  {

    private List<ZmBean> zmlist;
    public ImagerAdapter(List<ZmBean> zmBeanList) {
            this.zmlist = zmBeanList;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(MyApplicatioin.getContext()).inflate(R.layout.layout_image_quan,parent,false);
        return new ZmImageHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ZmImageHolder zmImageHolder = (ZmImageHolder) holder;
        zmImageHolder.mText.setText(zmlist.get(position).getDescription());
        GlideUtil.loadpicture(zmlist.get(position).getCompleteUrl(),zmImageHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return zmlist.size();
    }


    private OnitemClickListener itemlistener;

    public void setOnitemClickListener(OnitemClickListener listener) {
            this.itemlistener = listener;
    }

    class ZmImageHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageView;
        private TextView mText;
        public ZmImageHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            imageView = (ImageView) itemView.findViewById(R.id.image_content_main2);
            mText = (TextView) itemView.findViewById(R.id.image_content_text2);
        }
        @Override
        public void onClick(View view) {
            if (itemlistener != null)
                itemlistener.onitemClick(view,zmlist.get(this.getLayoutPosition()));
        }
    }
}
