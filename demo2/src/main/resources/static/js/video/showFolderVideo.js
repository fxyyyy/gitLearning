$(document).ready(function() {
	//begin:显示全部的视频
	$.ajax({
		url:"urlShowFolderVideo",
		data:
		{ pageNum: 1},  
		type:"post",
		success:function(msg){
			var list = msg.map.listVideoForPlay;
			console.log(list);
			showVideo(msg)
		 },
		 error:function(XMLHttpRequest, textStatus, errorThrown) {
		 }
		
		}); 
	//end:显示全部的文章
	
	
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
			url : "urlShowAllVideo",
			data : "pageNum=" + pageNum,
			type : "post",
			success : function(msg) {
				showVideo(msg);
			},
			error : function() {
			}
		});
	}
	
	function showVideo(msg){
		//清空table
		$("#page").empty();
		$("#pageHelper").empty();
		
		//100表示成功
		if (msg.code == 100) {
			//清空文件夹搜索拼接的内容
			$("#table").hide();
			$("#tableVideoList").hide();
			$("#videoAll").empty();
			//把值拼接到html中
			var pageList = msg.map.pageList;
			var list = msg.map.listVideoForPlay;
			$.each(list, function(index, videoList) {
				var articleBox = document.createElement('div');
				articleBox.className = 'col-sm-4 col-md-4';
				articleBox.innerHTML =
					'<div class="aw-item"> <img src='
					+videoList.smallimage+'  class="img-responsive" alt=""/>'						
					+'<a href="/urlPlayVideo?id='+videoList.id+'">'+videoList.info +'</a></div>'
				
				var listImage = document.getElementsByClassName('clearfix catchImages')[0];
				listImage.appendChild(articleBox);
			
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