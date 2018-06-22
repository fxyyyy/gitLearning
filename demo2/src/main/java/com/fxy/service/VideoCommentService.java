package com.fxy.service;

import java.util.List;

import com.fxy.bean.Comment;
import com.fxy.bean.VideoComment;

public interface VideoCommentService {

	int addComment(VideoComment comment);

	List<VideoComment> showById(Integer id);
	
}
