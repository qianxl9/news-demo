package com.example.jh.nes_demo.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jh.nes_demo.Application.MyApplicatioin;
import com.example.jh.nes_demo.R;

import java.util.List;

/**
 * Created by jh on 2016/8/27.
 */
public class NewsTextAdapter extends RecyclerView.Adapter {

    private List<String> mlist;

    public NewsTextAdapter(List<String> contentList) {
        super();
        this.mlist = contentList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(MyApplicatioin.getContext()).inflate(R.layout.layout_newcontent_text,parent,false);
        return new ContentTextHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }



    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class ContentTextHolder extends  RecyclerView.ViewHolder {

        private ImageView mimage;
        private TextView mText;

        public ContentTextHolder(View itemView) {
            super(itemView);
            mimage = (ImageView) itemView.findViewById(R.id.image_master_content);
            mText = (TextView) itemView.findViewById(R.id.text_master_z);
        }
    }
}
