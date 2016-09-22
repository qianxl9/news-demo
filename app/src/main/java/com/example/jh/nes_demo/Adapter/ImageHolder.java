package com.example.jh.nes_demo.Adapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import com.example.jh.nes_demo.Activity.MainActivity;
import com.example.jh.nes_demo.Application.MyApplicatioin;
import com.example.jh.nes_demo.Bean.YLBean;
import com.example.jh.nes_demo.Fragment.NewsHeaderFragment;
import com.example.jh.nes_demo.Fragment.NewsImageFragment;
import com.example.jh.nes_demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jh on 2016/8/24.
 */
public class ImageHolder extends RecyclerView.ViewHolder {

    public ViewPager imagePager;
    private List<YLBean> list;
    private FragmentManager manager;
    public List<NewsImageFragment> fragmentList = new ArrayList<NewsImageFragment>();

    public ImageHolder(View itemView , FragmentManager manager , final List<YLBean> list) {
        super(itemView);
        this.list = list;
        this.manager = manager;
        for (int i = 0 ; i < list.size();i++){
            fragmentList.add(setUpFragment(i));
        }
    }

    public void initPage() {
        imagePager = (ViewPager) itemView.findViewById(R.id.news_title_pager);
        imagePager.setAdapter(new FragmentStatePagerAdapter(manager) {

            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });
        imagePager.setOffscreenPageLimit(list.size()-1);
    }

    private NewsImageFragment setUpFragment(int position){
        NewsImageFragment fragment = new NewsImageFragment(list.get(position));
        return fragment;
    }
}
