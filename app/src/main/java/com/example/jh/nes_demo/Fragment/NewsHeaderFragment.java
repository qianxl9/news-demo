package com.example.jh.nes_demo.Fragment;

import android.annotation.TargetApi;
import android.app.DownloadManager;
import android.content.Intent;
import android.content.ReceiverCallNotAllowedException;
import android.database.Observable;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.jh.nes_demo.Activity.NewContentActivity;
import com.example.jh.nes_demo.Adapter.NewsContentAdapter;
import com.example.jh.nes_demo.Adapter.NewsPagerAdapter;
import com.example.jh.nes_demo.Bean.YLBean;
import com.example.jh.nes_demo.R;
import com.example.jh.nes_demo.Util.FileUtil;
import com.example.jh.nes_demo.Util.GsonUtil;
import com.example.jh.nes_demo.Util.HttpUtil;
import com.example.jh.nes_demo.Util.OnitemClickListener;
import com.example.jh.nes_demo.Util.OnresponseListener;

import org.json.JSONException;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jh on 2016/8/23.
 */
public class NewsHeaderFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{


    private RecyclerView mRecycler;
    private SwipeRefreshLayout mRefresh;
    private List<YLBean> ylBeen = new ArrayList<YLBean>();
    private List<YLBean> imagel = new ArrayList<YLBean>();
    private String contentUrl;
    private int pager = 1;
    private final int REFRESH_Z =1;
    private GsonUtil gsonUtil = GsonUtil.getGsonUtil();
    private  NewsContentAdapter contentAdapter;
    private LinearLayoutManager linear;
    public void setResources(String contentUrl){
        this.contentUrl = contentUrl;
    }
    private  String fileName;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.headlines_l,container,false);
        initView(view);
        return view;
    }


    @TargetApi(Build.VERSION_CODES.M)
    private void initView(View view) {
        mRecycler = (RecyclerView) view.findViewById(R.id.recycler_master_content);
        mRefresh = (SwipeRefreshLayout) view.findViewById(R.id.refresh_widgt);
        linear = new LinearLayoutManager(getContext());
        linear.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycler.setLayoutManager(linear);
        contentAdapter = new NewsContentAdapter(ylBeen, getChildFragmentManager(),imagel);
        contentAdapter.setOnitemClickListener(new OnitemClickListener() {
            @Override
            public void onitemClick(View v, Object object) {
                Intent intent = new Intent(getActivity(), NewContentActivity.class);
                intent.putExtra("YLBean_data",((YLBean) object));
                getActivity().startActivity(intent);
            }
        });


        mRecycler.addOnScrollListener(listener);
        mRecycler.setAdapter(contentAdapter);
        mRecycler.setHasFixedSize(true);
        mRefresh.setColorSchemeResources(
                R.color.lavender,
                R.color.lightcyan,
                R.color.burlywood
        );
        mRefresh.setOnRefreshListener(this);
        fileName = getArguments().getString("fileName");
        Preloading();
    }

    @Override
    public void onRefresh() {

        pager = 1;
        new DownloadTak().execute(contentUrl+REFRESH_Z);
        mRefresh.postDelayed(new Runnable() {
            @Override
            public void run() {
                mRefresh.setRefreshing(false);
            }
        },3000);
    }

    private void Preloading(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String readdata = FileUtil.ReadBeandata(fileName);

                if(!TextUtils.isEmpty(readdata)){
                    try {
                        ylBeen.addAll(gsonUtil.loadResponse(readdata,0,0));
                        imagel.addAll(gsonUtil.loadResponse(readdata,0,1));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            contentAdapter.notifyDataSetChanged();
                        }
                    });
                }else {
                    pager = 1;
                    new DownloadTak().execute(contentUrl+REFRESH_Z);
                }
            }
        }).start();
    }

    private RecyclerView.OnScrollListener listener = new RecyclerView.OnScrollListener() {
        private int postion;
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            postion = linear.findLastVisibleItemPosition();
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
                if(contentAdapter.getItemViewType(postion) == 1 &&
                        newState == RecyclerView.SCROLL_STATE_IDLE){
                    pager+=1;
                    new DownloadTak().execute(contentUrl+pager);
                }
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    //加载内容。
    class DownloadTak extends AsyncTask<String ,Void ,Boolean>{

        @Override
        protected Boolean doInBackground(String... strings) {
            try {
                HttpUtil.sendHttprequest(strings[0], new OnresponseListener() {
                    @Override
                    public void onFinish(String response) throws JSONException, IOException {
                        if(pager ==1) {
                            FileUtil.saveBeandata(fileName,response);
                            ylBeen.clear();
                            imagel.clear();
                            ylBeen.addAll(gsonUtil.loadResponse(response,0,0));
                            imagel.addAll(gsonUtil.loadResponse(response,0,1));
                        }else {
                            ylBeen.addAll(gsonUtil.loadResponse(response,0,0));
                        }

                    }
                    @Override
                    public void onError(Exception e) {
                        e.printStackTrace();
                    }
                });
            }catch (Exception e){
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            if(ylBeen !=null){
                contentAdapter.notifyDataSetChanged();
                contentAdapter.update(); //查询完后更新viewpager的内容。

            }else {
                Toast.makeText(getContext(), "查询新闻失败", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
