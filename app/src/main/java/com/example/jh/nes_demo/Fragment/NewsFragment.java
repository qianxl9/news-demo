package com.example.jh.nes_demo.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.example.jh.nes_demo.Activity.MainActivity;
import com.example.jh.nes_demo.Adapter.NewsPagerAdapter;
import com.example.jh.nes_demo.Application.MyApplicatioin;
import com.example.jh.nes_demo.R;
import com.example.jh.nes_demo.Util.Urladdress;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jh on 2016/8/22.
 */
public class NewsFragment extends Fragment {

    private PagerSlidingTabStrip mPagerTab;
    private ViewPager mPager;
    private  List<Fragment> list;
    private View view;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.news_viewpager,container,false);
        initView(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).hidImage();
    }

    private void initView(View view) {
        mPagerTab = (PagerSlidingTabStrip) view.findViewById(R.id.tab_title_display);
        mPager = (ViewPager) view.findViewById(R.id.pager_master_Content);
        mPager.setAdapter(new NewsPagerAdapter(getActivity().getSupportFragmentManager(),initFragment()));
        mPager.setOffscreenPageLimit(2);
        mPagerTab.setViewPager(mPager);
    }

    private List<Fragment> initFragment(){
        list = new ArrayList<Fragment>();
        list.add(setUpFragment("NewsName",Urladdress.PHOENIX_TL));
        list.add(setUpFragment("FunlName",Urladdress.PHOENIX_FL));
        list.add(setUpFragment("YLname",Urladdress.PHOENIX_YL));
        return list;
    }

    private NewsHeaderFragment setUpFragment(String fileName , String address){
        Bundle bundle = new Bundle();
        NewsHeaderFragment fragment = new NewsHeaderFragment();
        bundle.putString("fileName",fileName);
        fragment.setResources(address);
        fragment.setArguments(bundle);
        return fragment;
    }
}
