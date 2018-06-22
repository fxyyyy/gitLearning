/*$(document).ready(function() {*/
 $(function(){
	 //begin:显示全部学生进度
	 $.ajax({
			url : "urlShowAllStu",
			data : { pageNum: 1},
			type : "post",
			success : function(msg) {
				showStu(msg);
				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(XMLHttpRequest.status);
				alert(XMLHttpRequest.readyState);
				alert(textStatus);
			}

		});
	 //end:显示全部学生进度
	 
    
   
    
    function to_page(pageNum){
		$.ajax({
			url : "urlShowAllStu",
			data : "pageNum=" + pageNum,
			type : "post",
			success : function(msg) {
				showStu(msg);
			},
			error : function() {
			}
		});
	}
	
	function showStu(msg){
		//清空table
		$("#showLearnRate").empty();
		$("#pageHelper").empty();
		console.log("msg:"+msg.map.pageList.hasPrePage);
		// 100表示成功
		if (msg.code == 100) {
			// 把值拼接到html中
			var list = msg.map.learnRates;
			var pageList = msg.map.pageList;
			console.log("list:"+pageList);
			var html = "";
			html += "<table class='table table-striped' align='center' style='width: 940px;'>";
			html += "<tr><td>序号</td><td>姓名</td><td>视频名</td><td>观看时长</td></tr>";
			$.each(list, function(index, learnRate) {
					
html += "<tr>";
html += "<td>"+ (index + 1) +"</td>";
html += "<td>"+ learnRate.username +"</td>";
html += "<td>"+ learnRate.videoname +"</td>";
html += "<td>"+ learnRate.playtime +"</td>";
				});
				 html += "</tr></table>";
			$("#showLearnRate").append(html);
			
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