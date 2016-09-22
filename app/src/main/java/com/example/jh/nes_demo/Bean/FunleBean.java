package com.example.jh.nes_demo.Bean;

/**
 * Created by jh on 2016/8/21.
 */
public class FunleBean {

    /**
     * thumbnail : http://d.ifengimg.com/w132_h94_q75/p1.ifengimg.com/ifengimcp/pic/20160821/ede38ea74499d4f1398e_size22_w330_h235.jpg
     * online : 1
     * title : [囧图]这部宇宙科幻大片的主角是只小龙虾|34图
     * source : 凤凰新闻客户端
     * channel : FUN来了
     * updateTime : 2016/08/21 18:09:33
     * id : http://api.iclient.ifeng.com/ipadtestdoc?aid=112337590
     * documentId : imcp_112337590
     * type : doc
     * commentsUrl : http://wap.ifeng.com/news.jsp?aid=112337590
     * comments : 18
     * commentsall : 45
     * link : {"type":"doc","url":"http://api.iclient.ifeng.com/ipadtestdoc?aid=112337590"}
     */

    private String thumbnail;
    private String online;
    private String title;
    private String source;
    private String channel;
    private String updateTime;
    private String id;
    private String documentId;
    private String type;
    private String commentsUrl;
    private String comments;
    private String commentsall;
    /**
     * type : doc
     * url : http://api.iclient.ifeng.com/ipadtestdoc?aid=112337590
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
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
