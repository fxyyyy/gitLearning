$(function(){
	
	//布置练习
	$("#publishPractice").click(function(){
	    //通过类名获得输入框中的值	
		var inputTitle = $("#inputTitle").val();
		var inputContent = $("#inputContent").val();
		//发起ajax请求，把发表的通知内容传到后台
		$.ajax({
			url:"urlModelPublishPractice",
			data:{inputTitle:inputTitle,inputContent:inputContent},
			type:"post",
			success:function(msg){
				console.log(msg.map.practice);
				//成功
				if (msg.code = "100") {
					//此处拼接内容
					url = "practice/modelChoosePractice.html?id="+msg.map.practice.id+
					"&name="+msg.map.name+
				       "&title="+msg.map.practice.title+"&content="+msg.map.practice.content+
				       "&starttime="+formateDate(msg.map.practice.starttime)
				       +"&answernum="+msg.map.practice.answernum
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