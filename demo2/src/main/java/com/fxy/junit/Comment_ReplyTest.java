package com.fxy.junit;/*package com.fxy.junit;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fxy.bean.Article;
import com.fxy.bean.Comment;
import com.fxy.dao.Comment_ReplyMapper;
import com.fxy.service.ArticleService;
import com.fxy.service.CommentService;
import com.fxy.service.Comment_ReplyService;

*//**
 * 
 * @Title: Comment_ReplyTest
 * @Param:
 * @Description: 次评论的控制层所需Controller
 * @author: fxy
 * @date: 2017年12月9日
 *//*
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class Comment_ReplyTest {
	@Autowired
	ArticleService articleService;

	@Autowired
	CommentService commentService;

	@Autowired
	Comment_ReplyService comment_ReplyService;

	@Autowired
	Comment_ReplyMapper comment_ReplyMapper;

	// comment_reply comment_id == comment id
	// comment_reply comment_reply_id == comment user_id

	// jsp中设置一个class，点击了那个class传ajax请求，就是次评论
	// 根据那个class还要获取那条评论的用户id
	*//**
	 * @Title: insertSecondaryComment
	 * @Description: 次评论，需要传String content, Date date, int user_id, int
	 *               comment_id, int comment_reply_id
	 * @author: fxy
	 * @date: 2017年12月9日
	 *//*
	@Test
	public void insertSecondaryComment() {
		// 谁的评论内容
		String content = "这是222的次评论，评论fxy";
		// 222用户的id 评论用户的Id
		int user_id = 2;
		// 设置次评论的时间
		Date date = new Date();

		// 被该用户评论的用户id
		// 被222用户评论的用户id=1 fxy-->comment--->user_id
		int comment_reply_id = 1;

		// 被评论的fxy的评论信息的id
		// 被该用户评论的用户评论信息的id 即主评论表的主键
		int comment_id = 1;

		// 插入成功，返回1
		int flag = comment_ReplyService.insertSecondaryComment(content, date, user_id, comment_id, comment_reply_id);
		System.out.println("次评论插入成功返回1-->flag:" + flag);

		// ① 然后主评论表的次评论数+1
		// 通过comment_reply comment_id == comment id去求comment_replies
		System.out.println("主评论的Id-->comment_id:" + comment_id);

		// 先获得主评论表的评论数量
		int replies = commentService.countById(comment_id);
		System.out.println("没有添加这个次评论时候的主评论被评论数replies:" + replies);
		replies = replies + 1;

		// 把已经+1的评论数存到comment表
		Comment comment = new Comment();
		comment.setId(comment_id);
		comment.setCommentReplies(replies);

		// 根据id跟comment_replies选择性的修改comment表
		int num = commentService.updateByPrimaryKeySelective(comment);
		System.out.println("把已经+1的评论数存到comment表的num为1则成功:" + num);

		// ②把article表comments评论数+1
		// comment_id = comment id--> article_id = article id --->comments

		// 通过主评论的id获得文章的id
		int articleId = commentService.selectByCommentId(comment_id);
		// 获得没插入该次评论前的评论数
		int comments = articleService.countById(articleId);
		// 将该次评论数+1
		comments = comments + 1;
		// 将+1之后的评论数插入article表
		Article article = new Article();
		article.setId(articleId);
		article.setComments(comments);
		int flag3 = articleService.addComments(article);

	}

	// 删除次评论，必须是在已经确定是次评论的情况下才可调用
	// 此时comment主评论表id获得次评论comment_replies的评论数>=1
	// 主评论表comment的comment_replies要-1
	// 文章表article的comments要-1
	// 然后才开始删除次评论
	*//**
	 * 先根据comment_reply表的次评论id获得cpmment_id = comment主评论表id
	 * 然后由comment主评论表id将次评论comment_replies的评论数 - 1 接着由comment主评论表id获得文章的id
	 * article_id 并将comments - 1 最后将次评论根据id删除
	 * 
	 * @Title: deleteSecondaryComment
	 * @Description: 删除次评论（单条删除）
	 * @param comment_reply表的次评论id int
	 * @return void
	 * @author: fxy
	 * @date: 2017年12月9日
	 *//*
	@Test
	public void deleteSecondaryComment() {
		// 传入的次评论id
		int id = 1;

		// ①先根据comment_reply表的次评论id获得comment_id = comment主评论表id
		// commentId  = comment-->id
		int commentId = comment_ReplyService.selectBySecondaryId(id);
		
		// ②根据id算出主评论的评论数量
		int commentReplies = commentService.countById(commentId);
		//由comment主评论表id将次评论comment_replies的评论数 - 1
		int commentRepliesStay = commentReplies - 1;
		//封装comment对象
		Comment comment = new Comment();
		comment.setId(commentId);
		comment.setCommentReplies(commentRepliesStay);
		//调用函数 - 次评论数（数量为1）
		int updateCommentReplies = commentService.updateByPrimaryKeySelective(comment);
		System.out.println("updateCommentReplies不为0则主评论数修改成功:"+updateCommentReplies);
		
		// ③接着由comment主评论表id获得文章的id article_id
		//  articleId = Article-->id
		int articleId = commentService.selectByCommentId(commentId);
		
		// ④由文章的id获得全部的评论数量comments
		//  并将comments - 1
		int commentsAll = articleService.countById(articleId);
		// 该文章的评论数量  - 删除的单条次评论的数量（数量为1）
		int commentsStay = commentsAll - 1;
		// 封装修改后的属性
		Article article = new Article();
		article.setId(articleId);
		article.setComments(commentsStay);
		// 把原article文章的comments数量-主次评论的数量
		int num4 = articleService.updateByPrimaryKeySelective(article);
		System.out.println("如果num4为1则说明文章的评论数减少成功:" + num4);
		
		// ⑤最后将次评论根据id删除
		int num2 = comment_ReplyService.deleteByPrimaryKey(id);
		System.out.println("如果num2不为0则说明删除成功:" + num2);
	};
}
*/