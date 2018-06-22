package com.fxy.junit;/*package com.fxy.junit;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fxy.bean.Article;
import com.fxy.bean.Comment;
import com.fxy.service.ArticleService;
import com.fxy.service.CommentService;
import com.fxy.service.Comment_ReplyService;

*//**
 * 
 * @Title: CommentTest
 * @Param:
 * @Description: 主评论控制层所需Controller
 * @author: fxy
 * @date: 2017年12月9日
 *//*
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class CommentTest {
	@Autowired
	CommentService commentService;

	@Autowired
	Comment_ReplyService comment_ReplyService;

	@Autowired
	ArticleService articleService;

	// jsp中设置一个class，点击了那个class传ajax请求，就是主评论
	// 主评论拼接的页面代码也有class标识评论的文章article_id(直接获取页面上方的article_id就好)
	// 测试一下主评论,需要传入的参数为String content = "这是主评论";
	// int user_id = 1;
	// int article_id = 1;

	*//**
	 * 先根据参数插入comment主评论表，comment_replies=0 然后article表的comments先获得原评论数，再+1
	 * 
	 * @Title: insertMainComment 要传入的参数 主评论内容：String content = "这是222的主评论";
	 *         主评论用户的id:int user_id= 2; 主评论刚开始的被评论数都是0：int comment_replies = 0;
	 *         主评论评论的文章id:int article_id = 8; 主评论的时间：Date date = new Date();
	 * @Description: 添加主评论
	 * @author: fxy
	 * @date: 2017年12月8日
	 *//*
	@Test
	public void insertMainComment() {
		String content = "这是fxy的主评论";
		int user_id = 1;
		// 刚刚添加主评论的时候 次评论数都为0
		int comment_replies = 0;
		// article_id 主评论次评论同用这个
		int article_id = 1;
		// 设置次评论的时间
		Date date = new Date();

		// 封装传过来的值
		Comment c = new Comment();
		c.setContent(content);
		c.setArticleId(article_id);
		c.setPublishTime(date);
		c.setUserId(user_id);
		c.setCommentReplies(comment_replies);

		// 调用插入数据库函数
		int flag = commentService.insertMainComment(c);
		// 插入成功，返回1
		System.out.println("主评论插入成功返回1-->flag:" + flag);

		// article_id = article id
		// 获得没插入该主评论前的评论数
		int comments = articleService.countById(article_id);
		// 评论数+1
		comments = comments + 1;
		// 把已经+1的评论数封装到article表
		Article article = new Article();
		article.setId(article_id);
		article.setComments(comments);

		int flag2 = articleService.addComments(article);
		System.out.println("article文章表的评论数+1成功返回1:" + flag2);
	}

	// 删除主评论,要把对应的次评论也一起删除
	// 传入要删除的主评论int id = 1;
	// 只对某一用户的删除
	*//**
	 * @Title: deleteMainComment 要传入的参数 comment表主评论内容的id: int id = 1;
	 * @Description: 删除主评论（单条删除）
	 * @author: fxy
	 * @date: 2017年12月9日
	 *//*
	@Test
	public void deleteMainComment() {
		// 要删除的主评论的id
		int id = 1;
		// 先判断这个id的主评论有没有次评论,num为次评论的数量
		int num = commentService.countById(id);

		// 根据主评论的Id获得article_id
		int articleID = commentService.selectByCommentId(id);

		// 该文章article全部的评论数量comments
		int commentsAll = articleService.countById(articleID);

		// 该用户的主评论数量 1 + 次评论用户的数量
		int comments_nums = num + 1;
		// 删除主评论，那么附带的次评论也要删除，所以这里求的是总评论数-该用户主次评论数
		int commentsStay = commentsAll - comments_nums;

		// 封装修改后的属性
		Article article = new Article();
		article.setId(articleID);
		article.setComments(commentsStay);
		// 把原article文章的comments数量-主次评论的数量
		int num4 = articleService.updateByPrimaryKeySelective(article);
		System.out.println("如果num4为1则说明文章的评论数减少成功:" + num4);

		// 如果存在次评论，先删次评论
		if (num != 0) {
			// 因为 comment_reply comment_id = comment id
			// 所以先根据 comment_reply comment_id删次评论，再删主评论
			int comment_id = id;
			int num2 = comment_ReplyService.deleteByCommentPK(comment_id);
			System.out.println("如果num2不为0则说明删除成功:" + num2);
		}
		// 不管有没有次评论，都要根据Id删除主评论
		int num3 = commentService.deleteByPrimaryKey(id);
		System.out.println("如果num3为1则说明删除成功:" + num3);

	}

}
*/