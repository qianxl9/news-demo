package com.example.jh.nes_demo.Util;

import com.example.jh.nes_demo.Bean.FunleBean;
import com.example.jh.nes_demo.Bean.YLBean;
import com.example.jh.nes_demo.Bean.ZmBean;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jh on 2016/8/22.
 */
public class  GsonUtil{

    private static Gson gson = new Gson();
    private static GsonUtil gsonUtil;

    private GsonUtil(){};

    public  static GsonUtil getGsonUtil() {
        synchronized (GsonUtil.class) {
            if (gsonUtil == null) {
                gsonUtil = new GsonUtil();
            }
        }
        return gsonUtil;
    }

    public <T> List<YLBean> loadResponse(String response,int type , int postion) throws JSONException {
        String date = null;
        if(type == 0 ){
            date = loadPhoenix(response,postion);
        }
        List<YLBean> list = gson.fromJson(date,new TypeToken<List<YLBean>>(){}.getType());
        if (postion == 0 )
        return select(list);
        else
            return list;
    }

    private List<YLBean> select(List<YLBean> list) {
        List<YLBean> ylList = new ArrayList<YLBean>();

        for (int i = 0 ; i < list.size() ; i++) {
           if (list.get(i).getLink().getType().equals("doc")) {
               ylList.add(list.get(i));
           }
        }

        return ylList;
    }

    public static String loadcontent(String response) throws Exception {
        JSONObject object = new JSONObject(response);
        return object.getJSONObject("body").getString("text");
    }

    public List<ZmBean> loadZmdate(String response) throws JSONException {
        return gson.fromJson(loadZm(response),new TypeToken<List<ZmBean>>(){}.getType());
    }

    private  String loadPhoenix(String response , int postion) throws JSONException {
        JSONArray jsonArray = new JSONArray(response);
        return jsonArray.getJSONObject(postion).getString("item");
    }

    private  String loadZm(String response) throws JSONException {
        JSONObject object = new JSONObject(response);
        return object.getJSONObject("data").getString("images");
    }
}
