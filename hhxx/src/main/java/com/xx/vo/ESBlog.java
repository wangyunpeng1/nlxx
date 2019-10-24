package com.xx.vo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

@Document(indexName="wypawj",type="blog")
public class ESBlog implements Serializable {
    @Id
    private String blogid;
    private String blogname;
    private String bloglabel;
    private String blogtype;
    private String creatdate;

    public ESBlog() {
    }

    public ESBlog(String blogid, String blogname, String bloglabel, String blogtype, String creatdate) {
        this.blogid = blogid;
        this.blogname = blogname;
        this.bloglabel = bloglabel;
        this.blogtype = blogtype;
        this.creatdate = creatdate;
    }

    public String getBlogid() {
        return blogid;
    }

    public void setBlogid(String blogid) {
        this.blogid = blogid;
    }

    public String getBlogname() {
        return blogname;
    }

    public void setBlogname(String blogname) {
        this.blogname = blogname;
    }

    public String getBloglabel() {
        return bloglabel;
    }

    public void setBloglabel(String bloglabel) {
        this.bloglabel = bloglabel;
    }

    public String getBlogtype() {
        return blogtype;
    }

    public void setBlogtype(String blogtype) {
        this.blogtype = blogtype;
    }

    public String getCreatdate() {
        return creatdate;
    }

    public void setCreatdate(String creatdate) {
        this.creatdate = creatdate;
    }
}
