package com.example.jh.nes_demo.Util;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import com.example.jh.nes_demo.Application.MyApplicatioin;
import com.example.jh.nes_demo.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by jh on 2016/8/22.
 */
public class FileUtil {

    private static boolean lock =true;
    public static File file;

    public synchronized static Boolean saveBitmap(final Bitmap bitmap , String jpgMark) {
        try {
            file = new File(Environment.getExternalStoragePublicDirectory("Icon"),"zuimei"+jpgMark+".png");
            if(!file.exists()){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        FileOutputStream outputStream = null;
                        try {
                            outputStream = new FileOutputStream(file);
                            bitmap.compress(Bitmap.CompressFormat.PNG,99, outputStream);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }finally {
                            try {
                                if(outputStream != null) {
                                    outputStream.close();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public synchronized static void saveBeandata(String fileName , String data) throws IOException {
        FileOutputStream outputStream = null ;
        BufferedWriter writer = null ;

        try {
            outputStream = MyApplicatioin.getContext().openFileOutput(fileName, Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(outputStream));
            writer.write(data);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(writer !=null)
                    writer.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public synchronized static String ReadBeandata(String filName) {
        FileInputStream inputStream = null;
        BufferedReader reader = null;
        String lin;
        StringBuilder builder = new StringBuilder();

        try {
            inputStream = MyApplicatioin.getContext().openFileInput(filName);
            reader = new BufferedReader(new InputStreamReader(inputStream));

            while((lin = reader.readLine())!= null){
                builder.append(lin);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(reader != null )
                    reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (builder !=null) {
            return builder.toString();
        }else
            return  null;
    }
}
