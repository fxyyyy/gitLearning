package com.fxy.bean;

import java.util.Date;

public class Practice {
    private Integer id;

    private String title;

    private Date starttime;

    private Date endtime;

    private Integer tuserid;

    private Integer answernum;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Integer getTuserid() {
        return tuserid;
    }

    public void setTuserid(Integer tuserid) {
        this.tuserid = tuserid;
    }

    public Integer getAnswernum() {
        return answernum;
    }

    public void setAnswernum(Integer answernum) {
        this.answernum = answernum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}