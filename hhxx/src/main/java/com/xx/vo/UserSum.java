package com.xx.vo;

public class UserSum
{
    private String userId;
    private long fabulous;   //点赞数
    private long fans;       //关注数
    private long blogs;      //博客数
    private long visit;      //浏览数

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
}
