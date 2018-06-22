$(function(){ 
$("#user_insert_btn").click(function(){
	
	var kaptcha = $("#kaptcha").val();
	if (kaptcha.length == 0) {
		alert("您没有输入验证码！");
	} else {
		
	
	var name = $("#name").val();
	var password = $("#password").val();
	//发送ajax请求修改员工数据
	$.ajax({
		url:"login",
		data:
		{name:name,password:password},
		type:"post",
		success:function(msg){
			console.log(msg);
			//100表示成功
			if (msg.code == 100) {
				//跳到发表文章页面:因为路线二，所以把这个注释先
				//window.location.href = "article/publish.html?";
				
				//TODO:现在登录之后跳到显示全部文章页面
				
				
				//①跳到显示全部文章页面
				//window.location.href = "article/modelShowAllArticle.html?";
				
				//②跳到后台系统管理页面
				//window.location.href = "manageSys";
				
				//③跳到视频播放页面
				window.location.href = "modelV";
				
				//③跳转到视频管理系统页面
				//window.location.href = "urlManageVideo";
				
				//作业的这个：跳转到创建文件夹及显示全部文件夹功能页面
				//window.location.href = "showAllFolder";
			
				//后台获取要加一杠/showAllArticle
				//window.location.href = "showAllArticle";
			} else {
				 $('#prompt').text(msg.map.errMsg);  
			}
		 },
		 error:function(XMLHttpRequest, textStatus, errorThrown) {
			 console.log(XMLHttpRequest.status);
			 console.log(XMLHttpRequest.readyState);
			 console.log(textStatus);
		 }
		
		}); 
	
	}
	
	
	
	
});
}); 