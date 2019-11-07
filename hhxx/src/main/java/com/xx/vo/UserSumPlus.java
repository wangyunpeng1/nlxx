package com.xx.vo;

public class UserSumPlus {
    private String userId;
    private long fabulous;   //点赞数
    private long fans;       //粉丝数
    private long blogs;      //博客数
    private long visit;      //浏览数
    private long collections;//收藏数
    private long flows;      //关注数

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getFabulous() {
        return fabulous;
    }

    public void setFabulous(long fabulous) {
        this.fabulous = fabulous;
    }

    public long getFans() {
        return fans;
    }

    public void setFans(long fans) {
        this.fans = fans;
    }

    public long getBlogs() {
        return blogs;
    }

    public void setBlogs(long blogs) {
        this.blogs = blogs;
    }

    public long getVisit() {
        return visit;
    }

    public void setVisit(long visit) {
        this.visit = visit;
    }

    public long getCollections() {
        return collections;
    }

    public void setCollections(long collections) {
        this.collections = collections;
    }

    public long getFlows() {
        return flows;
    }

    public void setFlows(long flows) {
        this.flows = flows;
    }
}
