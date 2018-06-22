$(document).ready(function() {

	//发起ajax请求，判断是不是教师，如果是教师就显示发表通知功能；
	//学生就清空发表通知功能
	$.ajax({
		url:"urlRoleInform",
		data:{},
		type:"post",
		success:function(msg){

			console.log(msg);
			//成功码100:教师	
			if (msg.map.intRoleId == 1) {
				$(".roldInform").show();
			}
			if (msg.map.intRoleId == 2) {
				$(".roldInform").hide();
			} 
		},
	    error:function(){
	    }
	 });
	
});