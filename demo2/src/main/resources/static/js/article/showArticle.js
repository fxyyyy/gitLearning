$(document).ready(function() {
	
	//begin:显示全部的文章
	$.ajax({
		url:"showArticle",
		data:
		 { pageNum: 1},  
		type:"post",
		success:function(msg){
			console.log(msg);
			showArticle(msg);
		 },
		 error:function(XMLHttpRequest, textStatus, errorThrown) {
			 alert(XMLHttpRequest.status);
			 alert(XMLHttpRequest.readyState);
			 alert(textStatus);
		 }
		
		}); 
	//end:显示全部的文章
	
	
	
	//begin:显示该标题下的内容
	function toArticleContent(id,title,content,publishTime,comments,userId){
		url = "modelChooseContent.html?id="+id+
		 "&userId="+userId+
		       "&title="+title+"&content="+content+
		       "&publishTime="+publishTime+"&comments="+comments
        window.location.href = encodeURI(url);
	}
	//end:显示该标题下的内容
	
	
	//begin:格式化日期
	function formateDate(date) {
		var date = new Date(date);
		var y = date.getFullYear();
		var m = date.getMonth() + 1;
		var d = date.getDate();
		var h = date.getHours();
		var mi = date.getMinutes();
		m = m > 9 ? m : '0' + m;
		return y + '-' + m + '-' + d + ' ' + h + ':' + mi;
	}
	//end:格式化日期
	
	function to_page(pageNum){
		$.ajax({
			url : "showArticle",
			data : "pageNum=" + pageNum,
			type : "post",
			success : function(msg) {
				showArticle(msg);
			},
			error : function() {
			}
		});
	}
	
	function showArticle(msg){
		//清空table
		$(".page").empty();
		$("#pageHelper").empty();
		//100表示成功
		if (msg.code == 100) {
			//把值拼接到html中
			var pageList = msg.map.pageList;
			console.log("list:"+pageList);
			var articles = msg.map.articles;
			console.log("articles:"+articles);
			var nameList = msg.map.nameList;
			
			$.each(articles, function(index, article) {
				 //拼接上述,创建一个新的div
				var numberCount = $("<td></td>").append(index+1);
				var name = $("<td></td>").append(nameList[index]);

				var articleTime = formateDate(article.publishTime);
				var title = $("<td></td>").append(article.title);
				title.attr("articleTitle",article.title);
				title.click(function(){
					toArticleContent(article.id,article.title,article.content,
							articleTime,article.comments,nameList[index])});
				
				var publishTime = $("<td></td>").append(articleTime);
				var tr = $("<tr class='page'></tr>");
				tr.append(numberCount).append(name).append(title).append(publishTime).appendTo("#table tbody");
			});
			
			//nav
			var nav = $("<nav></nav>");
			//ul
			var ul = $("<ul></ul>").addClass("pagination");
			//首页
			var firstPageLi = $("<li></li>").append($("<a></a>").append("首页").attr("href","#"));
			//上一页
			var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;").attr("href","#"));
			//判断是否为首页
			if(pageList.hasPrePage==false){
				//禁用li
				firstPageLi.addClass("disabled");
				prePageLi.addClass("disabled");
			}else{
				firstPageLi.click(function(){
					to_page(1);
				});
				prePageLi.click(function(){
					to_page(pageList.currentPage-1);
				});
			}
			
			ul.append(firstPageLi);
			ul.append(prePageLi);
			
			//TODO 页面暂时没有
			
			//下一页
			var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;").attr("href","#"));
			//末页
			var lastPageLi = $("<li></li>").append($("<a></a>").append("末页").attr("href","#"));
			
			//判断是否为末页
			if(pageList.hasNextPage==false){
				nextPageLi.addClass("disabled");
				lastPageLi.addClass("disabled");
			}else{
				nextPageLi.click(function(){
					to_page(pageList.currentPage+1);
					
				});
				lastPageLi.click(function(){
					to_page(pageList.totalPage);
				});
			}
			
			ul.append(nextPageLi);
			ul.append(lastPageLi);
			//
			nav.append(ul).appendTo("#pageHelper");
			
			
		} else {
			alert("请求失败了！");
		}
	}
	
});