package com.fxy.bean;

import java.util.Date;

public class Comment {
    private Integer id;

    private String content;

    private Date publishTime;

    private Integer userId;

    private Integer articleId;

    private Integer commentReplies;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getCommentReplies() {
        return commentReplies;
    }

    public void setCommentReplies(Integer commentReplies) {
        this.commentReplies = commentReplies;
    }
}