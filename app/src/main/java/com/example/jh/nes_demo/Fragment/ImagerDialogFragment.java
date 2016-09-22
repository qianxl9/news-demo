package com.example.jh.nes_demo.Fragment;

import android.app.Dialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.example.jh.nes_demo.Application.MyApplicatioin;
import com.example.jh.nes_demo.Bean.ZmBean;
import com.example.jh.nes_demo.R;
import com.example.jh.nes_demo.Util.FileUtil;
import com.example.jh.nes_demo.Util.GlideUtil;

/**
 * Created by jh on 2016/8/26.
 */
public class ImagerDialogFragment extends DialogFragment implements View.OnLongClickListener {

    private ImageView imageView;
    private ZmBean zmBean;

//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
//        View view = inflater.inflate(R.layout.imager_dialog,container);
//        imageView =(ImageView) view.findViewById(R.id.image_dialog_display);
//        imageView.setOnLongClickListener(this);
//        GlideUtil.loadpicture(zmBean.getCompleteUrl(),imageView);
//        //   initImager(view);
//        return view;
//    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
       AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.imager_dialog,null);
        imageView = (ImageView) view.findViewById(R.id.image_dialog_display);
        imageView.setOnLongClickListener(this);
        if (zmBean != null)
        GlideUtil.loadpicture(zmBean.getCompleteUrl(),imageView);
        builder.setView(view);
        return builder.create();
    }


    public void setZmBean(ZmBean zmBean) {
        this.zmBean = zmBean;
    }

    public void initImager(View view) {
        imageView =(ImageView) view.findViewById(R.id.image_dialog_display);
        imageView.setOnLongClickListener(this);
        GlideUtil.loadpicture(zmBean.getCompleteUrl(),imageView);
    }

    @Override
    public boolean onLongClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
            }
        });
        builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
               Bitmap bitmapDrawable = ((GlideBitmapDrawable) imageView.getDrawable()).getBitmap();
               if(FileUtil.saveBitmap(bitmapDrawable,String.valueOf(SystemClock.currentThreadTimeMillis()))) {
                   Toast.makeText(getActivity(), "已保存到"+FileUtil.file.toString(), Toast.LENGTH_SHORT).show();
               }
                else {
                   Toast.makeText(getActivity(),"保存失败", Toast.LENGTH_SHORT).show();
               }
            }
        });
        builder.setMessage("是否保存图片呢？").create().show();
        return true;
    }


}
