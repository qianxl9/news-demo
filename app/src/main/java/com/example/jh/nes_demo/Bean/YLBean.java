package com.example.jh.nes_demo.Bean;

import java.io.Serializable;

/**
 * Created by jh on 2016/8/21.
 */
public class YLBean implements Serializable {

    /**
     * thumbnail : http://d.ifengimg.com/w132_h94_q75/p1.ifengimg.com/cmpp/2016/08/21/f0eb739d8780bb7ced93290a7030ac6b_size44_w168_h120.jpg
     * online : 1
     * title : 刘金山伤重不治？假的！颈椎受伤没有大碍
     * updateTime : 2016/08/21 07:46:17
     * id : http://api.iclient.ifeng.com/ipadtestdoc?aid=cmpp_010010042670459
     * documentId : cmpp_010010042670459
     * type : doc
     * commentsUrl : http://ent.ifeng.com/a/20160821/42670459_0.shtml
     * comments : 295
     * commentsall : 1513
     * link : {"type":"doc","url":"http://api.iclient.ifeng.com/ipadtestdoc?aid=cmpp_010010042670459"}
     */

    private String thumbnail;
    private String online;
    private String title;
    private String updateTime;
    private String id;
    private String documentId;
    private String type;
    private String commentsUrl;
    private String comments;
    private String commentsall;
    /**
     * type : doc
     * url : http://api.iclient.ifeng.com/ipadtestdoc?aid=cmpp_010010042670459
     */

    private LinkBean link;

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCommentsUrl() {
        return commentsUrl;
    }

    public void setCommentsUrl(String commentsUrl) {
        this.commentsUrl = commentsUrl;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getCommentsall() {
        return commentsall;
    }

    public void setCommentsall(String commentsall) {
        this.commentsall = commentsall;
    }

    public LinkBean getLink() {
        return link;
    }

    public void setLink(LinkBean link) {
        this.link = link;
    }

    public static class LinkBean implements Serializable {
        private String type;
        private String url;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
