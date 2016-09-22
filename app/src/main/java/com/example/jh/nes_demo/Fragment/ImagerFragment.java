package com.example.jh.nes_demo.Fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.jh.nes_demo.Activity.MainActivity;
import com.example.jh.nes_demo.Adapter.ImagerAdapter;
import com.example.jh.nes_demo.Application.MyApplicatioin;
import com.example.jh.nes_demo.Bean.ZmBean;
import com.example.jh.nes_demo.R;
import com.example.jh.nes_demo.Util.FileUtil;
import com.example.jh.nes_demo.Util.GsonUtil;
import com.example.jh.nes_demo.Util.HttpUtil;
import com.example.jh.nes_demo.Util.OnitemClickListener;
import com.example.jh.nes_demo.Util.OnresponseListener;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by jh on 2016/8/23.
 */
public class ImagerFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private RecyclerView mRecycler;
    private List<ZmBean> zmBeanList = new ArrayList<ZmBean>();
    private StaggeredGridLayoutManager manager;
    private String contentUrl;
    private ImagerAdapter contenAdapter;
    private GsonUtil gsonUtil = GsonUtil.getGsonUtil();
    private  View view;
    private SwipeRefreshLayout refreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.image_main,container,false);
        initView(view);
        return view;
    }

    @Override
    public void onResume() {
        //((MainActivity) getActivity()).displaymage();
        super.onResume();
    }

    public void initView(View view){
        mRecycler = (RecyclerView) view.findViewById(R.id.recycler_center_image);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_image);
        refreshLayout.setOnRefreshListener(this);
        manager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        mRecycler.setLayoutManager(manager);
        contenAdapter = new ImagerAdapter(zmBeanList);
        mRecycler.setAdapter(contenAdapter);
        mRecycler.setHasFixedSize(true);
        this.contentUrl = getArguments().getString("address");

        contenAdapter.setOnitemClickListener(new OnitemClickListener() {
            @Override
            public void onitemClick(View v, Object object) {
                ImagerDialogFragment dialogFragment = new ImagerDialogFragment();
                dialogFragment.setZmBean((ZmBean) object);
                dialogFragment.show(getFragmentManager(),"Imagefragment");
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                String date = FileUtil.ReadBeandata("Zm_Bimap");
                if (!TextUtils.isEmpty(date)) {
                    try {
                        zmBeanList.clear();
                        zmBeanList.addAll(gsonUtil.loadZmdate(date));
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    for (int i = 0 ; i< zmBeanList.size() ; i++) {
                        zmBeanList.get(i).setCompleteUrl(zmBeanList.get(i).getImage_url());
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            contenAdapter.notifyDataSetChanged();
                        }
                    });
                }else {
                    new DownloadAsync().execute(contentUrl);
                }
            }
        }).start();
    }

    @Override
    public void onRefresh() {
        new DownloadAsync().execute(contentUrl);
        refreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(false);
            }
        },3000);
    }

    class DownloadAsync extends AsyncTask<String , Void , Boolean> {

        @Override
        protected Boolean doInBackground(String... strings) {
            try {
                HttpUtil.sendHttprequest(strings[0], new OnresponseListener() {
                    @Override
                    public void onFinish(String response) throws JSONException, IOException {
                        FileUtil.saveBeandata("Zm_Bimap",response);
                        zmBeanList.clear();
                        zmBeanList.addAll(gsonUtil.loadZmdate(response));
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
            if (zmBeanList.size() > 0 ) {
                for (int i = 0 ; i< zmBeanList.size() ; i++) {
                    zmBeanList.get(i).setCompleteUrl(zmBeanList.get(i).getImage_url());
                }
                contenAdapter.notifyDataSetChanged();
            }else {
                Toast.makeText(MyApplicatioin.getContext(), "获取图片失败", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
