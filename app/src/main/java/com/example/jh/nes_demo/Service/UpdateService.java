package com.example.jh.nes_demo.Service;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;

import com.example.jh.nes_demo.Broadcast.AlerBroadcast;
import com.example.jh.nes_demo.Util.FileUtil;
import com.example.jh.nes_demo.Util.HttpUtil;
import com.example.jh.nes_demo.Util.OnresponseListener;
import com.example.jh.nes_demo.Util.Urladdress;

import java.net.HttpURLConnection;

/**
 * Created by jh on 2016/8/30.
 */
public class UpdateService extends IntentService {

    public UpdateService() {
        super("UpdateService");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        downloadContent(new String[]{Urladdress.PHOENIX_FL,Urladdress.PHOENIX_TL,Urladdress.PHOENIX_YL,Urladdress.ZUIMEI_B},
                new String[]{"FunlName","NewsName","Ylname","Zm_Bimap"});
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int anHour = 60*1000;
        long triggerAtTime = SystemClock.elapsedRealtime()+anHour;
        Intent intent1 = new Intent(this,AlerBroadcast.class);
        PendingIntent intent2 = PendingIntent.getBroadcast(this,0,intent1,0);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerAtTime,intent2);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void downloadContent(String[] addrss, final String[] fileName){
        for (int i = 0 ; i<addrss.length ; i++) {
            final int finalI = i;
            HttpUtil.sendHttprequest(addrss[i], new OnresponseListener() {
                @Override
                public void onFinish(String response) throws Exception {
                    FileUtil.saveBeandata(fileName[finalI],response);
                }

                @Override
                public void onError(Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
