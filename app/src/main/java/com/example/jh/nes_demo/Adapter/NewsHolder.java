package com.example.jh.nes_demo.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jh.nes_demo.R;

/**
 * Created by jh on 2016/8/23.
 */
public class NewsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public ImageView headerImage;
    public TextView titleText;
    public TextView timeText;
    public TextView dateText;
    private NewsContentAdapter contentAdapter;

    public NewsHolder(View itemView ,NewsContentAdapter contentAdapter) {
        super(itemView);
        this.contentAdapter = contentAdapter;
        itemView.setOnClickListener(this);
        headerImage = (ImageView) itemView.findViewById(R.id.news_start_content);
        titleText = (TextView) itemView.findViewById(R.id.news_center_title);
        timeText = (TextView) itemView.findViewById(R.id.news_center_time);
        dateText = (TextView)itemView.findViewById(R.id.news_center_date);
    }

    @Override
    public void onClick(View view) {
        if (contentAdapter.listener != null) {
            int x= this.getLayoutPosition()-1;
            contentAdapter.listener.onitemClick(view,contentAdapter.list.get(this.getLayoutPosition()-1));
        }
    }
}
