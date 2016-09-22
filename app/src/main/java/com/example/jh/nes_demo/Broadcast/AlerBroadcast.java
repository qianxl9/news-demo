package com.example.jh.nes_demo.Broadcast;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.NotificationCompat;

import com.example.jh.nes_demo.Activity.MainActivity;
import com.example.jh.nes_demo.R;
import com.example.jh.nes_demo.Service.UpdateService;

/**
 * Created by jh on 2016/8/30.
 */
public class AlerBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intent1 = new Intent(context, UpdateService.class);
        context.startService(intent1);

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder =new NotificationCompat.Builder(context);
        Bitmap larger = ((BitmapDrawable) context.getResources().getDrawable(R.drawable.yv_tu,null)).getBitmap();
        int bitmap = R.drawable.news_icon;
        Intent intent2 = new Intent(context, MainActivity.class);
        PendingIntent intent3 = PendingIntent.getActivity(context,0,intent2,0);
        String info = "新闻内容已更新，请点击查看！！！";

        builder.setLargeIcon(larger)
                .setSmallIcon(bitmap)
                .setContentTitle("更新，更新")
                .setContentText(info)
                .setContentIntent(intent3)
                .setDefaults(Notification.DEFAULT_ALL);
        Notification notification = builder.build();
        //manager.notify(0,notification);
    }
}
