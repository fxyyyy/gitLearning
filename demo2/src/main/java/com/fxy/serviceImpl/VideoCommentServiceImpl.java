package com.fxy.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fxy.bean.Comment;
import com.fxy.bean.CommentExample;
import com.fxy.bean.VideoComment;
import com.fxy.bean.VideoCommentExample;
import com.fxy.bean.ArticleExample.Criteria;
import com.fxy.dao.CommentMapper;
import com.fxy.dao.VideoCommentMapper;
import com.fxy.service.CommentService;
import com.fxy.service.VideoCommentService;
@Service("VideoCommentService")
public class VideoCommentServiceImpl implements VideoCommentService {

	@Autowired
	private VideoCommentMapper commentMapper;

	@Override
	public int addComment(VideoComment comment) {
		int flag = commentMapper.insert(comment);
		return flag;
	}

	@Override
	public List<VideoComment> showById(Integer id) {
		VideoCommentExample example = new VideoCommentExample();
		VideoCommentExample.Criteria criteria = example.createCriteria();
		criteria.andVideoidEqualTo(id);
		List<VideoComment> videoComments = commentMapper.selectByExample(example);
		return videoComments;
	}

}
