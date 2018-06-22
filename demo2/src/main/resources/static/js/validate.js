//显示验证结果
function show_validate_msg(ele, status, msg) {
	if ("success" == status) {
		// 让父容器变色
		$(ele).parent().addClass("has-success");
		// 给sapn赋值正确信息
		//$(ele).next("span").text(msg);
	} else if ("error" == status) {
		// 让父容器变色
		$(ele).parent().addClass("has-error");
		// 给sapn赋值错误信息
		//$(ele).next("span").text(msg);
		$(ele).attr("placeholder",msg);
		//var a = $(ele).attr("placeholder");
		//console.log(a);
		$(ele).val(""); 
		
	}
}

// 验证账号密码
function validate_add_form() {
	
	// 验证用户名
	var name = $("#insert_name").val();
	var reg_name = /(^[a-zA-Z0-9_-]{6,19}$)|(^[\u2E80-\u9FFF]{2,5})/;
	if (!reg_name.test(name)) {
		show_validate_msg("#insert_name", "error", "2-5位中文或6-19位英文和数字的组合");
		return false;
	} else {
		show_validate_msg("#insert_name", "success", "");
	}

	// 验证密码
	var password = $("#insert_password").val();
	var reg_password = /(^[a-zA-Z0-9_-]{6,19}$)/;
	if (!reg_password.test(password)) {
		show_validate_msg("#insert_password", "error", "必须是6-19位英文和数字的组合");
		return false;
	} else {
		show_validate_msg("#insert_password", "success", "");
	}
	return true;
}


