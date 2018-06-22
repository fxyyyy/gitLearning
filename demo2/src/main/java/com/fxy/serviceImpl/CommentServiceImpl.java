package com.fxy.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fxy.bean.Comment;
import com.fxy.bean.CommentExample;
import com.fxy.bean.ArticleExample.Criteria;
import com.fxy.dao.CommentMapper;
import com.fxy.service.CommentService;
@Service("CommentService")
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentMapper commentMapper;

	/**
	 * @Title: insertMainComment
	 * @param content
	 * @param user_id
	 * @param article_id
	 * @return int
	 * @Description: 插入主评论
	 * @author: fxy
	 * @date: 2017年12月7日
	 */
	public int insertMainComment(Comment comment) {
		// 先把评论插入数据库
		int flagOne = commentMapper.insert(comment);
		return flagOne;
	}

	/**
	 * @Title: selectById
	 * @param id
	 * @return boolean
	 * @Description: 根据id算出主评论的评论数量
	 * @author: fxy
	 * @date: 2017年12月8日
	 */
	public int countById(int id) {
		int num = commentMapper.countById(id);
		return num;
	}

	/**
	 * @Title: deleteByPrimaryKey
	 * @param id
	 * @return int
	 * @Description: 根据Id删除主评论
	 * @author: fxy
	 * @date: 2017年12月8日
	 */
	public int deleteByPrimaryKey(int id) {
		int num = commentMapper.deleteByPrimaryKey(id);
		return num;
	}
	
	/**
	 * @Title: updateByPrimaryKeySelective 
	 * @Description: 根据comment_replies选择性的修改comment表
	 * @param comment
	 * @return int        
	 * @throws 
	 * @author: fxy
	 * @date: 2017年12月9日
	 */
	public int updateByPrimaryKeySelective(Comment comment) {
		int num = commentMapper.updateByPrimaryKeySelective(comment);
		return num;
	}

	/**
	 * @Title: selectByCommentId 
	 * @Description: 通过主评论的id获得文章的id
	 * @param comment_id
	 * @return   int      
	 * @throws 
	 * @author: fxy
	 * @date: 2017年12月9日
	 */
	public int selectByCommentId(int comment_id) {
		int num = commentMapper.selectByCommentId(comment_id);
		return num;
	}

	/**
	 * @Title: selectById 
	 * @Description: 得到全部主评论拼接到页面上
	 * @param idArticle
	 * @return  List<Comment>       
	 * @throws 
	 * @author: fxy
	 * @date: 2018年3月14日
	 */
	@Override
	public List<Comment> selectById(int idArticle) {
		CommentExample example = new CommentExample();
		CommentExample.Criteria criteria = example.createCriteria();
		criteria.andArticleIdEqualTo(idArticle);
		List<Comment> listComments = commentMapper.selectByExample(example);
		return listComments;
	}

	//根据一级评论的内容获得该评论的实体类
	@Override
	public Comment selectByContent(String reply) {
		Comment comment = commentMapper.selectByContent(reply);
		return comment;
	}


}
