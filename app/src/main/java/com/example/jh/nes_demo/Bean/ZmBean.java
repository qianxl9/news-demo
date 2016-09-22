package com.example.jh.nes_demo.Bean;

import com.example.jh.nes_demo.Util.Urladdress;

/**
 * Created by jh on 2016/8/22.
 */
public class ZmBean {


    /**
     * pub_time : 2016-08-22
     * description : 在群山之巅，看红日初升
     * description_en :
     * height : 1280
     * photo_user : {"user_name":"张红伟","user_photo":"http://q.qlogo.cn/qqapp/101105874/BB5C18D56B900A20BE37E1856977EB9E/100","user_id":653678,"user_link":""}
     * width : 1440
     * up_times : 11673
     * desc_user : {"user_name":"张红伟","user_photo":"http://q.qlogo.cn/qqapp/101105874/BB5C18D56B900A20BE37E1856977EB9E/100","user_id":653678}
     * image_url : images/8358a9fe0fef3ac009003a3798d90056_1440x1280.jpg
     * id : 79730
     */

    private String pub_time;
    private String description;
    private String description_en;
    private String height;
    private String completeUrl;
    private String image_url;
    private int id;

    public void setCompleteUrl(String url) {
        this.completeUrl = Urladdress.ZUIMEI_JTI+url;
    }

    public String getCompleteUrl() {
        return completeUrl;
    }

    public String getPub_time() {
        return pub_time;
    }

    public void setPub_time(String pub_time) {
        this.pub_time = pub_time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription_en() {
        return description_en;
    }

    public void setDescription_en(String description_en) {
        this.description_en = description_en;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
