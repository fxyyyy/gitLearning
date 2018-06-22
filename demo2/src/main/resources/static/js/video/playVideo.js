$(document).ready(function() {
	var videoID = 0;
	var intCount = 0;
	//begin:播放选中的视频
	$.ajax({
		url:"urlPlayLast",
		data:
		{},
		type:"post",
		success:function(msg){
			console.log(msg);
			//100表示成功
			if (msg.code == 100) {
				
				//拼接左上角返回(超链接)/modelV>视频文件夹strFolder>视频介绍info
				var strFolder = msg.map.strFolder;
				var videoList = msg.map.videoList;
				
				videoID = videoList.id;
				var html = "";
				html += "<h2 style='font-size:20px;'><a href='/modelV'>首页</a>";
				html += "><a href='/hrefFolderVideo?strFolder="+strFolder+"'>"+strFolder.substring(0,strFolder.length-1)+"</a>";
				html += ">"+videoList.info;
				html += "</h2>";
				$("#videoProduce").append(html);
				
				//拼接视频播放链接
				var strUrlPlay = msg.map.strUrlPlay;
				var articleBox = document.createElement('div');
				articleBox.innerHTML =
			'<center><video id="my-video" onclick="stop()"  src='+strUrlPlay+' width="640" height="480"'
			+'  autoplay controls></center>'
				var video = document.getElementsByClassName('play')[0];
				video.appendChild(articleBox);
					
				var html2 = "";
				html2 += "<h2 style='width:300px;margin:0 auto;font-size:20px;display: flex;flex-direction: row;justify-content: space-between;'>";
				
				if(intCount != 0 ){
					var flagVideoPre = msg.map.flagVideoPre;
					if(flagVideoPre.length != 0){
						alert("没有上一节了！");
					}else{
						html2 += "<a href='/urlPlayVideoPre?id="+videoList.id+"&path="+videoList.path+"&time="+formateDate(videoList.publishTime)+"'>上一节</a>";
					}
					
					var flagVideoNex = msg.map.flagVideoNex;
					if(flagVideoNex.length != 0){
						alert("没有下一节了！");
					}else{
						html2 += "<a href='/urlPlayVideoNex?id="+videoList.id+"&path="+videoList.path+"&time="+formateDate(videoList.publishTime)+"'>下一节</a>";
					}
					
				}else{
					html2 += "<a href='/urlPlayVideoPre?id="+videoList.id+"&path="+videoList.path+"&time="+formateDate(videoList.publishTime)+"'>上一节</a>";
					html2 += "<a href='/urlPlayVideoNex?id="+videoList.id+"&path="+videoList.path+"&time="+formateDate(videoList.publishTime)+"'>下一节</a>";	
				}
				
				html += "</h2>";
				$("#preNext").append(html2);
				
				//视频评论显示
				var videoComments = msg.map.videoComments;
				console.log("videoComments:"+videoComments);
				$.each(videoComments, function(index, videoComment) {
					//主评论的用户名
					var commentUserName = videoComment.username;
					//主评论的内容
					var commentContent = videoComment.content;
			        //主评论的时间
					var commentTime = formateDate(videoComment.publishtime);
					var html = '';
					html += '<div id="list">';
					html += '<div class="content">';
					html += '<div class="praises-total" total="0" style="display: none;"></div>';
					html += '<div class="comment-list">';
					html += '<div class="comment-box clearfix" user="other">';
					html += '<img class="myhead" src="images/2.jpg" alt=""/>';
					html += '<div class="comment-content">';
					html += '<p class="comment-text"><span class="user">'+commentUserName+'：</span>'+commentContent+'</p>';
					html += '<p class="comment-time">'+commentTime;
					html += '</p></div></div> </div></div></div>';
					$("#commentContent").append(html);
				});
				
				//第几次进入标识
				intCount++;
			} else {
				alert("请求失败了！");
			}
		 },
		 error:function(XMLHttpRequest, textStatus, errorThrown) {
		 }
		
		}); 
	//end:播放选中的视频
	
	//begin:视频页评论
	$("#comment").click(function() {
		//获得主评论的内容
		var inputComment = $(".inputComment").val();
		//评论的视频id
		var idVideo = videoID;
		
		// 发起ajax请求，把发表的文章内容传到后台
		$.ajax({
			url : "commentVideo",
			data : {
				idVideo : idVideo,
				inputComment : inputComment
			},
			type : "post",
			success : function(msg) {
				console.log(msg);
				// 成功
				if (msg.code = "100") {
					//主评论的用户名
					var commentUserName = msg.map.commentUserName;
					//主评论的内容
					var commentContent = msg.map.comment.content;
			        //主评论的时间
					var commentTime = formateDate(msg.map.comment.publishtime);
					
					var html = '';
					html += '<div id="list">';
					html += '<div class="content">';
					html += '<div class="praises-total" total="0" style="display: none;"></div>';
					html += '<div class="comment-list">';
					html += '<div class="comment-box clearfix" user="other">';
					html += '<img class="myhead" src="images/2.jpg" alt=""/>';
					html += '<div class="comment-content">';
					html += '<p class="comment-text"><span class="user">'+commentUserName+'：</span>'+commentContent+'</p>';
					html += '<p class="comment-time">'+commentTime;
					html += '</p></div></div> </div></div></div>';
					$("#commentContent").append(html);
			        //清空评论内容输入框
			        $(".inputComment").val("");
			        
				} else {
					alert("发表失败！");
				}
			},
			error : function() {
			}
		});

	});
	//end:视频页评论
	
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