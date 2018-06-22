/*$(document).ready(function() {*/
 $(function(){
    
    //java学习路线标题下加一个搜索框，根据输入的技术名称去和存到数据库里的名称对比。
    $(".search_btns").click(function() {
    	//获得输入值
    	var pathName = $(".pathName").val();
    	$.ajax({
			url : "urlChoosePathName",
			data : {pathName:pathName},
			type : "post",
			success : function(msg) {
				console.log(msg);
				// 100表示成功
				if (msg.code == 100) {
					// 把值拼接到html中
					var list = msg.map.pathList;
					$("#showPathCrawl").empty();
					 var html = "";
					 html += "<table class='table table-striped' align='center' style='width: 940px;'>";
					 html += "<tr><td>序号</td><td>课程</td><td>打包下载地址</td></tr>";
					 $.each(list, function(index, pathList) {
						
html += "<tr>";
html += "<td>"+ (index + 1) +"</td>";
html += "<td><a href='/hrefShowIntroduce?pathname="+pathList.pathname +"'  target='_blank'>"+ pathList.pathname +"</a></td>";
html += "<td><a href='"+pathList.pathdownload +"'  target='_blank'>"+ pathList.pathdownload +"</a></td>";

					});
					 html += "</tr></table>";
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
    });
    
    function toName(x){
    	alert(x);
    }
    
    
  //begin:点击某项技术显示的技术解释及视频过程
	var intCount = 0;
    $("#J2SE").click(function() {
    	intCount++;
    	if (intCount == 1) {
    		$.ajax({
    			url : "urlShowIntroduce",
    			data : {},
    			type : "post",
    			success : function(msg) {
    				// 100表示成功
    				if (msg.code == 100) {
    					// 把值拼接到html中
    					var list = msg.map.pathList;
    					$("#w").append(list[0].pathintroduce);
    					$('#w').window('open');

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
		} else {
			$('#w').window('open');
		}
		
	});
  //end:点击某项技术显示的技术解释及视频过程
    
 });