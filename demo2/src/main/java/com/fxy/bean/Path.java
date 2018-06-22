package com.fxy.bean;

public class Path {
    private Integer id;

    private String pathname;

    private String pathdownload;

    private String pathintroduce;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPathname() {
        return pathname;
    }

    public void setPathname(String pathname) {
        this.pathname = pathname == null ? null : pathname.trim();
    }

    public String getPathdownload() {
        return pathdownload;
    }

    public void setPathdownload(String pathdownload) {
        this.pathdownload = pathdownload == null ? null : pathdownload.trim();
    }

    public String getPathintroduce() {
        return pathintroduce;
    }

    public void setPathintroduce(String pathintroduce) {
        this.pathintroduce = pathintroduce == null ? null : pathintroduce.trim();
    }
}