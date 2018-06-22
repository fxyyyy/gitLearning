package com.fxy.bean;

public class PathContent {
    private Integer id;

    private String pathcontent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPathcontent() {
        return pathcontent;
    }

    public void setPathcontent(String pathcontent) {
        this.pathcontent = pathcontent == null ? null : pathcontent.trim();
    }
}