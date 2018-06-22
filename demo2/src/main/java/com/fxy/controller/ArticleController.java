package com.fxy.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fxy.assist.ErrEnum;
import com.fxy.assist.Msg;
import com.fxy.bean.Article;
import com.fxy.bean.User;
import com.fxy.service.ArticleService;
import com.fxy.service.UserService;
import com.fxy.util.Page;
import com.fxy.util.PageUtil;
@Controller
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	@Autowired
	private UserService userService;
	
	protected static final Logger log = LoggerFactory.getLogger(ArticleController.class);

	
	
	/**
	 * @Title: 
	 * @Description: 跳转到发表文章的页面
	 * @return String
	 * @throws 
	 * @author fxy 
	 * @date 2018年3月7日
	 */
	//TODO:注意  这个页面放在了templates下面，因为配置的页面后缀是templates下的hml
	@RequestMapping(value = "/modelPublishURL")
	public String modelPublishURL() {
		log.info("进入到跳转发表文章的中转url...");
		return "modelPublishArticle";
	}
	
	
	/**
	 * @Title: PublishArticle 
	 * @Description:  发表文章,传title、content、user_id(存到session)
	 * publish_time、comments默认为0
	 * @param request
	 * @param inputTitle
	 * @param inputContent
	 * @return Msg    
	 * @throws 
	 * @author fxy 
	 * @date 2018年3月5日
	 */
	
	@ResponseBody
	@RequestMapping(value = "/urlModelPublishArticle", method = RequestMethod.POST)
	public Msg PublishArticle(HttpServletRequest request, String inputTitle, String inputContent) {
		log.info(inputTitle);
		HttpSession session = request.getSession();
		int intUserId =  (int) session.getAttribute("intUserId");
		log.info("intUserId:"+intUserId);
		
		//把内容封装到数据库Article表
		Article article = new Article();
		article.setTitle(inputTitle);
		article.setContent(inputContent);
		article.setPublishTime(new Date());
		//最开始发表，评论数为0
		article.setComments(0);
		article.setUserId(intUserId);
		//把新发表的文章插入数据库
		int intFlagArticle = articleService.addArticle(article);
		
		//
		log.info("article.getId():"+article.getId());
		if (0 != intFlagArticle ) {
			
			//先获得该用户id已经发表的文章数
			int intCountArticles = userService.selectByIdForArticles(intUserId);
			
			//封装发表文章后的用户信息
			User user = new User();
			user.setId(intUserId);
			user.setArticles(intCountArticles);
			
			//根据用户ID把用户发表的文章数加1
			int intFlagUserArticlesAdd = userService.addArticles(user); 
			//通过用户Id获得用户名
			User userForName = userService.selectByPrimaryKey(intUserId);
			//如果成功
			if (0 != intFlagUserArticlesAdd) {
				return Msg.success().add("article", article).add("name", userForName.getName());
			} else {
				return Msg.error().add("errMsg", ErrEnum.ADD_ERROR.getErrorMessage());
			}
			
		} else {
			return Msg.error().add("errMsg", ErrEnum.ADD_ERROR.getErrorMessage());
		}
		
	}
	
	
	/**
	 * @Title: ShowAllArticle
	 * @Description: 显示全部文章
	 * @param request
	 * @return Msg
	 * @throws 
	 * @author fxy 
	 * @date 2018年3月7日
	 */
	@ResponseBody
	@RequestMapping(value = "/article/showArticle", method = RequestMethod.POST)
	public Msg ShowArticle(HttpServletRequest request,int pageNum) {
		List<Article> articleList = articleService.showAllArticle();
		int totalCount = articleList.size();
		Page page = PageUtil.createPage(5, totalCount, pageNum);
		int intIndex =  page.getBeginIndex();
		int intEveryPage = page.getEveryPage();
		log.info("intIndex:"+intIndex+"   intEveryPage:"+intEveryPage);
		List<Article> articles = articleService.findByPage(page,intIndex,intEveryPage);
		List<String> nameList = new ArrayList<>();
		for (Article article : articles) {
			nameList.add(userService.getNameById(article.getUserId()));
		}
		return Msg.success().add("pageList", page).add("articles", articles).add("nameList", nameList);

	}
	
	
	/**
	 * @Title: ShowChooseArticle
	 * @Description: 显示刚刚选择的文章的内容
	 * @param request
	 * @param title
	 * @param content
	 * @param publishTime
	 * @param comments
	 * @param userId
	 * @return
	 * @throws 
	 * @author fxy 
	 * @date 2018年3月12日
	 */
	/*TODO 删掉了
	 * @ResponseBody
	@RequestMapping(value = "/article/showChooseArticle", method = RequestMethod.POST)
	public Msg ShowChooseArticle(HttpServletRequest request,String title,String content,
			String publishTime,int comments, int userId) {
		log.info("title:"+title+"content:"+content+"publishTime:"+publishTime+
				"comments:"+comments+"userId:"+userId);
		Map<Object, Object> mapArticle = new HashMap<>();
		mapArticle.put("title",title );
		mapArticle.put("content",content );
		mapArticle.put("publishTime",publishTime );
		mapArticle.put("comments",comments );
		mapArticle.put("userId",userId );
		
		return Msg.success().add("articleMap", mapArticle);
	}*/
	
	
	@ResponseBody
	@RequestMapping(value = "/article/urlRoleInform", method = RequestMethod.POST)
	public Msg urlRoleInform(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		int intUserId = (int) session.getAttribute("intUserId");
		
		//根据id去获得role_id
		User user = userService.selectByIdForName(intUserId);
		int intRoleId = user.getRoleId();
		log.info("intRoleId:"+intRoleId);
		return Msg.success().add("intRoleId", intRoleId);
	}
	
	
}