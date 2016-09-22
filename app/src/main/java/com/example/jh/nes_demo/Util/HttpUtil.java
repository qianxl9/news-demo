package com.example.jh.nes_demo.Util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by jh on 2016/8/22.
 */
public class HttpUtil {

    public static void sendHttprequest(final String addss,final OnresponseListener listener){
        HttpURLConnection connection = null;
        try{
            URL url = new URL(addss);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            if(connection.getResponseCode() == 200 ){
                InputStream inputStream= connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder builder = new StringBuilder();
                String lin;

                while ((lin=reader.readLine())!=null){
                    builder.append(lin);
                }

                if(listener !=null){
                    listener.onFinish(builder.toString());
                }
            }
        }catch (Exception e){
            if(listener != null )
                listener.onError(e);
        }finally {
            if(connection != null)
                connection.disconnect();
        }
    }


}
