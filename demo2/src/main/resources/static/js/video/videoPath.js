/*$(document).ready(function() {*/
 $(function(){
    //java学习路线标题下加一个搜索框，根据输入的技术名称去和存到数据库里的名称对比。
    $(".search_btns").click(function() {
    	//获得输入值
    	var pathName = $(".pathName").val();
    	//TODO 把搜索改为搜索视频并显示
    	$.ajax({
			url : "urlChooseVideoFolder",
			data : {pathName:pathName},
			type : "post",
			success : function(msg) {
				console.log(msg.map.list);
				//清空原视频的html
				$("#videoAll").empty();
				$("#pageHelper").empty();
				//清空表格
				$("#tableVideoList").hide();
				$("#table").show();
				$(".page").empty();
		    	// 把值拼接到html中
				var list = msg.map.list;
				//把值拼接到html中
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
				/*$.each(list, function(index, videoFolder) {
					//拼接上述,创建一个新的div
					var numberCount = $("<td></td>").append(index+1);
					var title = $("<td></td>").append(videoFolder.folder.substring(0,videoFolder.folder.length-1));
					title.click(function(){
						toArticleContent(videoFolder.id)});
					
					var tr = $("<tr class='page'></tr>");
					tr.append(numberCount).append(title).appendTo("#table tbody");
				});*/
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
			}

		});
    });
    
    //点击显示该文件夹下全部视频
    function toArticleContent(x){
    	$.ajax({
			url : "urlChooseVideoList",
			data : {path:x},
			type : "post",
			success : function(msg) {
				console.log("666:"+msg.map.videoLists);
				//清空原视频的html
				$("#videoAll").empty();
				$("#pageHelper").empty();
				//清空表格
				//$("#tableVideoList").empty();
				$("#table").hide();
				$("#tableVideoList").show();
				// 把值拼接到html中
				var list = msg.map.videoLists;
				//把值拼接到html中
				$.each(list, function(index, videoLists) {
					//拼接上述,创建一个新的div
					var numberCount = $("<td></td>").append(index+1);
					
					var title = $("<td></td>");
					title.append('<a href="/urlPlayVideo?id='+videoLists.id+'">'+videoLists.info +'</a>');
					
					var videoListTime = $("<td></td>").append(formateDate(videoLists.publishTime));
					var tr = $("<tr class='page'></tr>");
					tr.append(numberCount).append(title).append(videoListTime);
					$("#tableVideoList").append(tr);
				});
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
			}

		});
    }
    
    
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
    
 });