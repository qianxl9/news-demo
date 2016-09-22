package com.example.jh.nes_demo.Util;

import org.json.JSONException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by jh on 2016/8/22.
 */
public interface OnresponseListener {
    void onFinish(String response) throws Exception;
    void onError(Exception e);


}
