$(document).ready(function() {
	//begin:根据session获得path实体类对应信息

	 $.ajax({
			url : "urlGetPath",
			data : {},
			type : "post",
			success : function(msg) {
				console.log(msg.map.path);
				// 100表示成功
				if (msg.code == 100) {
					$("#showPathCrawl").empty();
					// 把值拼接到html中
					var html = "";
					html +="<div class='panel-header' style='width: 900px;'>";
					html +="<div class='panel-title'>"+msg.map.path.pathname+"</div>";
					html +="<div class='panel-tool'><a class='panel-tool-collapse' href='javascript:void(0)'></a></div></div>";
					html += msg.map.path.pathintroduce;
					html += "点击下载：<a href='"+msg.map.path.pathdownload +"'  target='_blank'>"+ msg.map.path.pathdownload +"</a>";
					$("#showPathCrawl").append(html);
				} else {
					alert("请求失败了！");
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(XMLHttpRequest.status);
				alert(XMLHttpRequest.readyState);
				alert(textStatus);
			}

		});
	 //end:根据session获得path实体类对应信息 
 });