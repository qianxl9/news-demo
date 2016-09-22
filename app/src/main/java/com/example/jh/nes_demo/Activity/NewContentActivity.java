package com.example.jh.nes_demo.Activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.jh.nes_demo.Adapter.NewsTextAdapter;
import com.example.jh.nes_demo.Application.MyApplicatioin;
import com.example.jh.nes_demo.Bean.NesBean;
import com.example.jh.nes_demo.Bean.YLBean;
import com.example.jh.nes_demo.R;
import com.example.jh.nes_demo.Util.GsonUtil;
import com.example.jh.nes_demo.Util.HttpUtil;
import com.example.jh.nes_demo.Util.OnresponseListener;
import com.example.jh.nes_demo.Util.XmlUtil;

import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jh on 2016/8/27.
 */
public class NewContentActivity extends BaseActivity {

    private RecyclerView mRecycler;
    private LinearLayoutManager manager;
    private List<String> contentList = new ArrayList<String>();
    private NewsTextAdapter mAdapter;
    private YLBean mYlBean;
    private WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_text_layout);
        webView = (WebView) findViewById(R.id.webview_mater_content);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        //webView.loadUrl("http://www.baidu.comm");
       // initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mYlBean = (YLBean) getIntent().getSerializableExtra("YLBean_data");
        new DownloadTask().execute(mYlBean.getLink().getUrl());
    }

    @Override
    protected void onDestroy() {
        finish();
        super.onDestroy();
    }

    private void initView() {
     //   mRecycler = (RecyclerView) findViewById(R.id.refresh_widgt_text);
        manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mAdapter = new NewsTextAdapter(contentList);
        mRecycler.setLayoutManager(manager);
        mRecycler.setAdapter(mAdapter);
    }

    private ProgressDialog dialog;

    private void showProgressDialog() {
        if (dialog == null) {
            dialog = new ProgressDialog(this);
            dialog.setMessage("正在加载中");
            dialog.setCanceledOnTouchOutside(false);
        }
        dialog.show();
    }

    private void closeProgressDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    class DownloadTask extends AsyncTask<String , String , Boolean> {

        @Override
        protected Boolean doInBackground(final String... strings) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    showProgressDialog();
                }
            });
           try {
               HttpUtil.sendHttprequest(strings[0], new OnresponseListener() {
                   @Override
                   public void onFinish(String response) throws Exception {
                       String content ="<head> <style> img{max-width:100%;height:auto;}</style></head>"+"<div style=\"font-size:21px\">"+GsonUtil.loadcontent(response);
                       publishProgress(content);

                       // contentList.addAll(XmlUtil.loadNewsXml(content));
                      // contentList.size();

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
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
          //  webView.loadData(values[0],"text/html","UTF-8");
            webView.loadDataWithBaseURL(null,values[0],"text/html","UTF-8",null);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            closeProgressDialog();

        }
    }
}
