/**
 * 页面初始化后，绑定函数。
 */
$(function(){
	//注册
	$("#regist_button").click(function(){
		register();
	});
	
	//登录
	$("#login").click(function(){
		login();
	});
	
	//登出
	$("#logout").click(function(){
		logout();
	});
	
	//修改密码
	$("#changePassword").click(function(){
		changepwd();
	})
	
});

//注册
function register() {
	alert("注册成功.");
	$("#zc").attr("class","sig sig_out");
	$("#dl").attr("class","log log_in");
}

//登陆
function login() {
	alert("登陆成功.");
	location.href="edit.html";
}

/**
 * 退出登录
 */
function logout(){
	location.href="login.html";
}

/**
 * 修改密码
 */
function changepwd(){
	alert("修改成功.");
	logout();
}


