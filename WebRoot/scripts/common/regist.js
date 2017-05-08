$(function(){
			$("#regist_button").click(function(){
				var username=$("#regist_username").val().trim();
				var pwd=$("#regist_password").val().trim();
				var nickname=$("#nickname").val().trim();
				var pwd2=$("#final_password").val().trim();
				
				//清空原有输入
				$("#regist_username").val("");
				$("#regist_password").val("");
				$("#nickname").val("");
				$("#final_password").val("");
				$("#warning_2").hide();
				$("#warning_3").hide();
				
				if(username==""){
					alert("用户名不能为空！");
					return;
				}
				if(nickname==""){
					alert("昵称不能为空！");
					return;
				}
				if(pwd.length<6){
					alert("密码长度不能少于6位");
					return;
				}
				if(pwd!=pwd2){
					alert("2次输入密码不一致，请重新输入！");
					return;
				}
				$.ajax({
					url:"http://localhost:8080/cloud_note/regist.do",
					type:"post",
					data:{"username":username,"pwd":pwd,"nickname":nickname},
					success:function(result){
						if(result.status==1){
							alert(result.msg);	
							return;
						}
						alert(result.msg);
						//返回登录界面
						$("#back").click();
					}
				});
			});
});