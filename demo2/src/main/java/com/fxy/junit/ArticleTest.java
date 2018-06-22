package com.fxy.junit;/*
package com.fxy.junit;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


*/
/**
 * @ClassName: ArticleTest 
* @Description: 文章的控制层Controller
* @author fxy 
* @date 2017年12月9日 
*
 *//*

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class ArticleTest {
	//TODO:发表文章、删除文章
	*/
/*发表文章：传title、content、user_id、section_id
	 * publish_time、comments默认为0
	 * 
	 *//*

	
	
	*/
/*删除文章：传要删除的文章id
	 * 删除步骤：
	 *①article id = comment article_id 文章Id即主评论表的article_id
	 *根据article_id获取主评论comment表这条评论的id
	 *函数待写。。。。。
	 * ②comment id = comment_reply comment_id  主评论表id即次评论表的comment_id
	 * 根据comment_reply comment_id删除次评论
	 * comment_ReplyService.deleteByCommentPK(comment_id);
	 * ③根据comment id删除主评论
	 * commentService.deleteByPrimaryKey(id);
	 * ④根据传入的文章id删除该文章
	 * 函数待写。。。。
	 *//*

	 
}
*/
