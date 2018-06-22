$(function(){ 

	var numMail = 0;
	//邮箱验证码
	$("#mailBtn").click(function(){
		
		// 验证邮箱
		var mail = $("#mailID").val();
		var reg_mail = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/; 
		if (!reg_mail.test(mail)) {
			show_validate_msg("#mailID", "error", "邮箱格式错误");
			return false;
		} else {
			//发起ajax进行添加操作
			$.ajax({
				url:"urlMail",
				data:{"mail":mail},  
				type:"post",
				success:function(msg){
					numMail = msg.map.num;
					alert("激活码发送成功，请到邮箱确认!");
				},
				error:function(){
				}
			});
		}
		
		
	});
	
	
	
//注册按钮	
$("#user_insert_btn").click(function(){
	
	//前端正则表达式验证
	if(!validate_add_form()){
		return false;
	}
	//验证用户名是否已经被占用
	if($(this).attr("ajax-va")=="error"){
		return false;
	} 
	
	var kaptcha = $("#kaptcha").val();
	var mailPwd = $("#jihuoma").val();
	if (kaptcha.length == 0) {
		alert("您没有输入验证码！");
	}else if(numMail != mailPwd || numMail == 0){
		alert("您的邮箱激活码有误！");
	} else {
		var name = $("#insert_name").val();
		var password = $("#insert_password").val();
		var select = $("#select option:selected").val();
		var mail = $("#mailID").val();
		//发起ajax进行添加操作
		$.ajax({
			url:"register",
			data:{"name":name,"password":password,
				"select":select,"mail":mail},  
			type:"post",
			success:function(msg){
				if(msg.code==100){
					//这里再次经过请求才进登录页面
					//如果放在静态static下静态页面就可以直接这样子跳页面:window.location.href = "signin.html";
					//但是在templates下必须经过请求否则就会暴露页面不安全
					
					//跳转到登录页面
					window.location.href = "signin";
				}else{
					alert("---注册失败---");
					//显示失败信息
					//有哪个字段错误,就显示哪个字段
					if(undefined!=msg.map.map.username){
						//显示账号错误信息
						show_validate_msg("#insert_name","error",msg.map.map.username);
					}
					if(undefined!=msg.map.map.password){
						show_validate_msg("#insert_password","error",msg.map.map.password);
					}
				}

			},
			error:function(){
			}
		});
	}
	
	
});


$("#insert_name").bind({
	focus : function() {
		var a  = $("#insert_name").val();
		console.log(a);
		if (a  == "2-5位中文或6-19位英文和数字的组合") {
			$("#insert_name").val("");
		}
		
	},
	blur : function() {
		
	}
});
$("#insert_password").bind({
	focus : function() {
		var a  = $("#insert_password").val();
		console.log(a);
		if (a  == "必须是6-19位英文和数字的组合") {
			$("#insert_password").val("");
		}
		
	},
	blur : function() {
		
	}
});

}); 