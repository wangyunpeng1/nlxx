package com.xx.vo;

public class UserSum {
    private String userId;
    private int fabulous;   //点赞数
    private int fans;       //关注数
    private int blogs;      //博客数
    private int Visit;      //浏览数

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getFabulous() {
        return fabulous;
    }

    public void setFabulous(int fabulous) {
        this.fabulous = fabulous;
    }

    public int getFans() {
        return fans;
    }

    public void setFans(int fans) {
        this.fans = fans;
    }

    public int getBlogs() {
        return blogs;
    }

    public void setBlogs(int blogs) {
        this.blogs = blogs;
    }

    public int getVisit() {
        return Visit;
    }

    public void setVisit(int visit) {
        Visit = visit;
    }
}
