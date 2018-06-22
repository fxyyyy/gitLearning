$(function() {

	//查看学生回答
$("#commentAnswer").click(function() {
		
		//答题的title
		var title = save[2];
		
		// 发起ajax请求，把发表的文章内容传到后台
		$.ajax({
			url : "urlAnswerStu",
			data : {
				title : title
			},
			type : "post",
			success : function(msg) {
				console.log(msg);
				// 成功
				if (msg.code = "100") {
					
					$("#answerShow").empty();
					
					//把值拼接到html中
					var listAnswer = msg.map.listAnswer;
					var nameList = msg.map.nameList;
					
					$.each(listAnswer, function(index, answer) {
						 //拼接上述,创建一个新的div
						var commentBox = document.createElement('div');
				        commentBox.className = 'aw-item';
				        commentBox.setAttribute('id', 'answer_list_49038');
				        commentBox.innerHTML =
	'<div class="mod-head"><a class="anchor" name="answer_49038"></a><a class="aw-user-img aw-border-radius-5"'
	+'<img src="http://wenda.wecenter.com/static/common/avatar-mid-img.png" alt="" /></a><div class="title">'
	+'<p><a class="aw-user-name" href="#" data-id="20067">'
	+nameList[index]+'</a> <i class="icon-v" style="background:transparent;"  title="个人认证"><img src="http://wenda.wecenter.com/static/common/avatar-mid-img.png" alt="" /></i>'
	+'</p><p class="text-color-999 aw-agree-by collapse">赞同来自:</p></div></div>'
	+'<div class="mod-body clearfix"><div class="markitup-box">'+answer.answer+'</div></div><div class="mod-footer">'
	+'<div class="meta clearfix"><span class="text-color-999 pull-right">'+formateDate(answer.answertime)+'</span>'
	+'</div></div>'
				                
	//要拼接到的位置
    var commentList = document.getElementsByClassName('mod-body aw-feed-list')[0];
    commentList.appendChild(commentBox);
					});
				} else {
					alert("发表失败！");
				}
			},
			error : function() {
			}
		});

	});
	
	
	
	
	$("#comment").click(function() {
		
		//获得主评论的内容
		var inputComment = $(".inputComment").val();
		// 定义了全局变量的save数组，所以可以直接这样子取得刚才发表的文章信息
		//主评论评论的文章id
		var idArticle = save[0];
		//答题的title
		var title = save[2];
		
		//主评论用户的id
		//var userIdArticle = save[3];userIdArticle : userIdArticle,int userIdArticle,
		
		// 发起ajax请求，把发表的文章内容传到后台
		$.ajax({
			url : "urlAnswerPractice",
			data : {
				idPractice : idArticle,
				inputComment : inputComment,
				title : title
			},
			type : "post",
			success : function(msg) {
				console.log(msg);
				// 成功
				if (msg.code = "100") {
					
					//主评论的用户名
					var commentUserName = msg.map.name;
					//主评论的内容
					var commentContent = msg.map.answer.answer;
			        //主评论的时间
					var commentTime = formateDate(msg.map.answer.answertime);
			        //拼接上述,创建一个新的div
					var commentBox = document.createElement('div');
			        commentBox.className = 'aw-item';
			        commentBox.setAttribute('id', 'answer_list_49038');
			        commentBox.innerHTML =
'<div class="mod-head"><a class="anchor" name="answer_49038"></a><a class="aw-user-img aw-border-radius-5"'
+'<img src="http://wenda.wecenter.com/static/common/avatar-mid-img.png" alt="" /></a><div class="title">'
+'<p><a class="aw-user-name" href="#" data-id="20067">'
+commentUserName+'</a> <i class="icon-v"  style="background:transparent;" title="个人认证"><img src="http://wenda.wecenter.com/static/common/avatar-mid-img.png" alt="" /></i>' 
+'</p><p class="text-color-999 aw-agree-by collapse">赞同来自:</p></div></div>'
+'<div class="mod-body clearfix"><div class="markitup-box">'+commentContent+'</div></div><div class="mod-footer">'
+'<div class="meta clearfix"><span class="text-color-999 pull-right">'+commentTime+'</span>'
+'</div></div>'
			                
			        //要拼接到的位置
			        var commentList = document.getElementsByClassName('mod-body aw-feed-list')[0];
			        commentList.appendChild(commentBox);
			        //清空评论内容输入框
			       /* commentContent.value = '';*/
			        $(".inputComment").val("");
			        //获得现在主评论的数目
			        var intComments = save[5];
			        //+1
			        intComments++;
			        //拼接到页面上
			        $('#articleComments').text(intComments+"个学生已回答" );
			        
				} else {
					alert("发表失败！");
				}
			},
			error : function() {
			}
		});

	});
	
	//格式化日期
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
	
	
	
	// begin:次评论的回复:会不会出现多个class相同导致打开很多回复页面？
	// TODO:先完成这一步先：别人点击文章链接，然后显示全部文章和主次评论？
	function commentReply() {
		alert("666");
	}
	//end:次评论的回复
	
	
});
