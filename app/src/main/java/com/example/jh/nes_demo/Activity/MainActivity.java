package com.example.jh.nes_demo.Activity;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.jh.nes_demo.Fragment.ImagerFragment;
import com.example.jh.nes_demo.Fragment.NewsFragment;
import com.example.jh.nes_demo.R;
import com.example.jh.nes_demo.Service.UpdateService;
import com.example.jh.nes_demo.Util.Urladdress;

/**
 * Created by jh on 2016/8/22.
 */
public class MainActivity extends BaseActivity {

    private NavigationView mNavigation;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mTool;
    private DrawerLayout mDrawer;
    private NewsFragment newsFragment;
    private FragmentTransaction transaction;
    private FrameLayout frameLayout;
    private ImagerFragment imagerFragment;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thelord_main);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((NotificationManager) getSystemService(NOTIFICATION_SERVICE)).cancel(0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    public void hidImage() {
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.show(newsFragment);
        transaction.commit();
    }

    public void displaymage() {
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.show(imagerFragment);
        transaction.commit();
    }

    private void initView(){
        mNavigation = (NavigationView) findViewById(R.id.navigation_l);
        mTool = (Toolbar) findViewById(R.id.toolbar);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_l);
        frameLayout = (FrameLayout) findViewById(R.id.fragment_l);
        newsFragment = new NewsFragment();
        imagerFragment = new ImagerFragment();
        Bundle bundle = new Bundle();
        bundle.putString("address", Urladdress.ZUIMEI_B);
        imagerFragment.setArguments(bundle);
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_l,newsFragment,"NEWS");
        transaction.add(R.id.fragment_l,imagerFragment,"IMAGER");
        transaction.hide(imagerFragment);
        transaction.commit();
        mToggle = new ActionBarDrawerToggle(this,mDrawer,mTool,R.string.drawer_open,R.string.drawer_close);
        mToggle.syncState();
        mDrawer.addDrawerListener(mToggle);
        mNavigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                selectionItem(item);
                item.setChecked(true);
                mDrawer.closeDrawers();
                return true;
            }
        });

        startService(new Intent(this, UpdateService.class));
    }

    private void selectionItem(MenuItem item) {
            switch (item.getItemId()){
                case R.id.news_id:
                    transaction = getSupportFragmentManager().beginTransaction();
                    transaction.show(newsFragment);
                    transaction.hide(imagerFragment);
                    transaction.commit();
                    break;

                case R.id.picture_id:
                    transaction = getSupportFragmentManager().beginTransaction();
                    transaction.show(imagerFragment);
                    transaction.hide(newsFragment);
                    transaction.commit();
                    break;
            }
    }
}
