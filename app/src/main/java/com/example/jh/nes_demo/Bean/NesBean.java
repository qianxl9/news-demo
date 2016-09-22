package com.example.jh.nes_demo.Bean;

/**
 * Created by jh on 2016/8/21.
 */
public class NesBean {

    /**
     * thumbnail : http://d.ifengimg.com/w134_h96_q75/p2.ifengimg.com/ifengimcp/pic/20160820/8ce4c479067e911fdb19_size30_w508_h362.jpg
     * online : 0
     * title : 习近平：把人民健康放在优先发展战略地位
     * id : http://api.iclient.ifeng.com/api.3g.ifeng.com/ixinwen/khdzt/zsn/zsni?json=y&ad=y
     * documentId : imcp_crc_2919900569
     * type : topic2
     * commentsUrl : http://wap.ifeng.com/zsni
     * comments : 12193
     * commentsall : 238120
     * styleType : topic
     * link : {"type":"topic2","url":"http://api.iclient.ifeng.com/api.3g.ifeng.com/ixinwen/khdzt/zsn/zsni?json=y&ad=y"}
     */

    private String thumbnail;
    private String online;
    private String title;
    private String id;
    private String documentId;
    private String type;
    private String commentsUrl;
    private String comments;
    private String commentsall;
    private String styleType;
    /**
     * type : topic2
     * url : http://api.iclient.ifeng.com/api.3g.ifeng.com/ixinwen/khdzt/zsn/zsni?json=y&ad=y
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

    public String getStyleType() {
        return styleType;
    }

    public void setStyleType(String styleType) {
        this.styleType = styleType;
    }

    public LinkBean getLink() {
        return link;
    }

    public void setLink(LinkBean link) {
        this.link = link;
    }

    public static class LinkBean {
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
