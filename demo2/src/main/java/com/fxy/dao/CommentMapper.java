package com.fxy.dao;

import com.fxy.bean.Comment;
import com.fxy.bean.CommentExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper 
public interface CommentMapper {
    int countByExample(CommentExample example);

    int deleteByExample(CommentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

    int insertSelective(Comment record);

    List<Comment> selectByExample(CommentExample example);

    Comment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Comment record, @Param("example") CommentExample example);

    int updateByExample(@Param("record") Comment record, @Param("example") CommentExample example);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);
    
    //根据id算出主评论的评论数量
  	int countById(int id);
  	
  	//通过主评论的id获得文章的id
  	int selectByCommentId(int comment_id);

    //根据一级评论的内容获得该评论的实体类
	Comment selectByContent(String reply);
    
}