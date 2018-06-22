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
					alert("验证码发送成功，请到邮箱确认!");
				},
				error:function(){
				}
			});
		}
		
		
	});
	
	
	
//注册按钮	
$("#findBtn").click(function(){
	//前端正则表达式验证
	if(!validate_add_form()){
		return false;
	}
	
	var mailPwd = $("#jihuoma").val();
	if(numMail != mailPwd){
		alert("您的邮箱验证码有误！");
	} else {
		var password = $("#insert_password").val();
		var mail = $("#mailID").val();
		
		var str = "";
		str = mail;
		alert(str+"   ");
		//发起ajax进行添加操作
		$.ajax({
			url:"urlFindPwd",
			data:{"password":password,"mailID":str},  
			type:"post",
			success:function(msg){
				alert("密码修改成功！跳转到登录页面！");
				//跳转到登录页面
				window.location.href = "signin";
			},
			error:function(){
			}
		});
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