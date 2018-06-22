$(function(){
	$("#publish").click(function(){
	//通过类名获得输入框中的值	
	var inputTitle = $("#inputTitle").val();
	var inputContent = $("#inputContent").val();
	//alert(inputTitle+"   "+inputContent);
	//发起ajax请求，把发表的文章内容传到后台
	//后台响应这个路径要加前缀/article/publishArticle
	$.ajax({
		  url:"urlModelPublishArticle",
		  data:{inputTitle:inputTitle,inputContent:inputContent},
		  type:"post",
		  success:function(msg){
			 console.log(msg);
			 //成功
			 if (msg.code = "100") {
				//此处拼接内容
				 url = "article/modelChooseContent.html?id="+msg.map.article.id+
				 "&name="+msg.map.name+
				       "&title="+msg.map.article.title+"&content="+msg.map.article.content+
				       "&publishTime="+formateDate(msg.map.article.publishTime)+"&comments="+msg.map.article.comments
		         window.location.href = encodeURI(url);
			 } else {
				  alert("发表失败！");
		     }
		  },
		  error:function(){
		  }
 	 });
	
    });
	
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