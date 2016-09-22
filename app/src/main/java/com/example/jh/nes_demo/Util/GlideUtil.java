package com.example.jh.nes_demo.Util;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.jh.nes_demo.Application.MyApplicatioin;
import com.example.jh.nes_demo.R;

/**
 * Created by jh on 2016/8/22.
 */
public class GlideUtil {

    public static void  loadpicture(String url, ImageView view){
        Glide.with(MyApplicatioin.getContext()).
                load(url).
                error(R.drawable.ic_image_loadfail).
                into(view);
    }
}
