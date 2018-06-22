$(function(){
	
	//发表通知
	$("#publishInform").click(function(){
	    //通过类名获得输入框中的值	
		var inputTitle = $("#inputTitle").val();
		var inputContent = $("#inputContent").val();
		//发起ajax请求，把发表的通知内容传到后台
		$.ajax({
			url:"urlModelPublishInform",
			data:{inputTitle:inputTitle,inputContent:inputContent},
			type:"post",
			success:function(msg){
				console.log(msg);
				//成功
				if (msg.code = "100") {
					//此处拼接内容
					url = "inform/modelChooseInform.html?id="+msg.map.inform.id+
					"&name="+msg.map.name+
				       "&title="+msg.map.inform.title+"&content="+msg.map.inform.content+
				       "&publishTime="+formateDate(msg.map.inform.publishtime)
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