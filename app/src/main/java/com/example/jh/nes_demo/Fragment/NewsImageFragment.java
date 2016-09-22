package com.example.jh.nes_demo.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jh.nes_demo.Bean.YLBean;
import com.example.jh.nes_demo.R;
import com.example.jh.nes_demo.Util.GlideUtil;

/**
 * Created by jh on 2016/8/24.
 */
public class NewsImageFragment extends Fragment{

    private YLBean ylBean;
    private ImageView imageView;
    private TextView textView;

    public NewsImageFragment(){
        super();
    }

    public NewsImageFragment(YLBean ylBean){
        this.ylBean = ylBean;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_image_l,container,false);
        imageView = (ImageView) view.findViewById(R.id.news_title_image);
        textView = (TextView) view.findViewById(R.id.news_title_text);
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        if (ylBean != null) {
            GlideUtil.loadpicture(ylBean.getThumbnail(),imageView);
            textView.setText(ylBean.getTitle());
        }
    }

    public void setUp(){
        if (imageView !=null){
            GlideUtil.loadpicture(ylBean.getThumbnail(),imageView);
            textView.setText(ylBean.getTitle());
        }
    }
}
