package com.example.jh.nes_demo.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.example.jh.nes_demo.Application.MyApplicatioin;
import com.example.jh.nes_demo.R;

import java.util.List;

/**
 * Created by jh on 2016/8/22.
 */
public class NewsPagerAdapter extends FragmentPagerAdapter{

    private List<Fragment> list;
    private String[] titleS = MyApplicatioin.getContext().getResources().getStringArray(R.array.title_name);

    public NewsPagerAdapter(FragmentManager supportFragmentManager, List<Fragment> fragmentList) {
        super(supportFragmentManager);
        this.list = fragmentList;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return titleS[position];
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
