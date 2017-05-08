		//给按钮追加单击事件
		$(function(){
			$("#login").click(function(){
				//清空原有提示信息
				$("#count_span").empty();
				$("#pwd_span").empty();
				
				var username=$("#count").val().trim();
				var pwd=$("#password").val().trim();
				if(username==""){
					$("#count_span").text("用户名不能为空");
					return;
				}
				 if(pwd==""){
				 	$("#pwd_span").text("密码不能为空");
				 	return;
				 }
			//	alert(username+pwd);
				$.ajax({
					url:"http://localhost:8080/cloud_note/checkLogin.do",
					type:"post",
					data:{"username":username,"pwd":pwd},
					success:function(result){
						if(result.status==0){//成功
							//获取用户ID，写入Cookie
							var userId=result.data;
							addCookie("uid",userId,2)//存储2小时
							window.location.href="edit.html";
						}else if(result.status==1){
							$("#count_span").text(result.msg);
						}else{
							$("#pwd_span").text(result.msg);
						}
						
					}
				});
			});
		});