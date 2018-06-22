package com.fxy.serviceImpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.fxy.bean.Article;
import com.fxy.bean.ArticleExample;
import com.fxy.dao.ArticleMapper;
import com.fxy.service.ArticleService;
import com.fxy.util.Page;
@Service("ArticleService")
public class ArticleServiceImpl implements ArticleService{  
    @Autowired  
	private ArticleMapper	articleMapper;

    /**
	 * @Title: addArticle
	 * @Description: 把新发表的文章插入数据库
	 * @param article
	 * @return int
	 * @throws 
	 * @author fxy 
	 * @date 2018年3月6日
	 */
	@Override
	public int addArticle(Article article) {
		int intFlagArticle = articleMapper.insert(article);
		return intFlagArticle;
	}

	/**
	 * @Title: countById 
	 * @Description: 获得没修改主次评论前的评论数
	 * @param article_id
	 * @return         
	 * @throws 
	 * @author: fxy
	 * @date: 2017年12月9日
	 */
	public int countById(int article_id) {
		int num = articleMapper.countById(article_id);
		return num;
	}
  
	
	/**
	 * @Title: addComments 
	 * @Description: 根据文章id把文章表中的评论数comments+1
	 * @param article_id
	 * @return int        
	 * @throws 
	 * @author: fxy
	 * @date: 2017年12月9日
	 */
	public int addComments(Article article) {
		int num = articleMapper.updateByPrimaryKeySelective(article);
		return num;
	}

	
	/**
	 * @Title: showAllArticle
	 * @Description: 获得全部的文章
	 * @return List<Article>
	 * @throws 
	 * @author fxy 
	 * @date 2018年3月12日
	 */
	@Override
	public List<Article> showAllArticle() {
		List<Article> articleList = articleMapper.selectByExample(new ArticleExample());
		//List<Article> articleList = articleMapper.selectAll();
		return articleList;
	}

	//分页
	@Override
	public List<Article> findByPage(Page page, int intIndex, int intEveryPage) {
		List<Article> articleList = articleMapper.selectByPage(intIndex,intEveryPage);
		return articleList;
	}
	
}  