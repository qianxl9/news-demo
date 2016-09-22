package com.example.jh.nes_demo.Adapter;

import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.jh.nes_demo.Application.MyApplicatioin;
import com.example.jh.nes_demo.Bean.YLBean;
import com.example.jh.nes_demo.R;
import com.example.jh.nes_demo.Util.GlideUtil;
import com.example.jh.nes_demo.Util.OnitemClickListener;

import java.util.List;

/**
 * Created by jh on 2016/8/23.
 */
public class NewsContentAdapter extends RecyclerView.Adapter{

    public List<YLBean> list;
    private List<YLBean> imagel;
    private final int FOOTER_LAYOUT = 1;
    private final int CONTENT_LAYOUT = 0;
    private final int IMAAGE_LAYOUT = 2;
    public boolean term = true;
    public OnitemClickListener listener;
    private FragmentManager manager;
    private ImageHolder holder1;
    private boolean lock = true;

    public NewsContentAdapter(List<YLBean> ylBeen ,FragmentManager manager , List<YLBean> imagel) {
        this.list = ylBeen;
        this.imagel = imagel;
        this.manager = manager;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == CONTENT_LAYOUT) {
           View view = LayoutInflater.from(MyApplicatioin.getContext()).inflate(R.layout.news_content_l,parent,false );
            return new NewsHolder(view,this);
        }else if (viewType == FOOTER_LAYOUT){
            View view = LayoutInflater.from(MyApplicatioin.getContext()).inflate(R.layout.load_wait,parent,false);
            return new RecyclerView.ViewHolder(view) {};
        }else {
            View view = LayoutInflater.from(MyApplicatioin.getContext()).inflate(R.layout.news_image_pager,parent,false);
            holder1 = new ImageHolder(view,manager,imagel);
            return holder1;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof NewsHolder) {
            NewsHolder holder1 = (NewsHolder) holder;
            holder1.titleText.setText(list.get(position-1).getTitle());
            holder1.timeText.setText(list.get(position-1).getUpdateTime());
            holder1.dateText.setText(list.get(position-1).getCommentsall());
            GlideUtil.loadpicture(list.get(position-1).getThumbnail(),holder1.headerImage);
        }else {
            if (lock) {
                holder1.initPage();
                lock = false;
            }
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return IMAAGE_LAYOUT;
        }
        return position == list.size()+1 ? FOOTER_LAYOUT:CONTENT_LAYOUT;
    }

    @Override
    public int getItemCount() {
        return   term ? list.size()+2:list.size();
    }

    public void update(){
        if (holder1 !=null) {
            for (int i = 0 ;i < holder1.fragmentList.size() ; i++ ) {
                holder1.fragmentList.get(i).setUp();
            }
        }
    }

    public void setOnitemClickListener(OnitemClickListener listener){
        this.listener = listener;
    }
}
