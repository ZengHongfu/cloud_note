function loadNoteBook() {
	var uid=getCookie("uid");
	if(uid==null||uid==""){
		window.location.href="login.html";
	}
	$.ajax({
		url:"http://localhost:8080/cloud_note/listNoteBook.do",
		type:"post",
		data:{"uid":uid},
		dataType:"json",
		success:function(result){
			if(result.status==1){
				alert(result.msg);
				return;
			}
			for(var i=0;i<result.data.length;i++){
			//	alert(result.data[i].cn_notebook_name);
				var str_li='<li class="online">'+
								'<a class="unchecked">'+
								'	<i class="fa fa-book" title="笔记本" rel="tooltip-bottom"></i>'+result.data[i].cn_notebook_name +
								'	<button type="button" class="btn btn-default btn-xs btn_position btn_delete"><i class="fa fa-times"></i></button>'+
								'</a>'+
							'</li>';
				var $li=$(str_li);
				var bookId=result.data[i].cn_notebook_id;
				$li.data("bookId",bookId);//藏bookId
			//	alert("aaa"+$li.data("bookId"));
				$("#first_side_right ul").append($li);
			}
		}
	});
}

function addNoteBook(){
	$(".opacity_bg").show();//显示背景
	$("#can").load("alert/alert_notebook.html");//显示对话框
}

function closeWindow(){
	$("#can").empty();
	$(".opacity_bg").hide();
}

function addNoteBookSure() {
	var bookName=$("#input_notebook").val();
	var userId=getCookie("uid");
	
	if(bookName==""){
		alert("名称不能为空！");
		return;
	}
	$.ajax({
		url:"http://localhost:8080/cloud_note/addNoteBook.do",
		type:"post",
		dataType:"json",
		data:{"userId":userId,"bookName":bookName},
		success:function(result){
			if(result.status==0){//成功
				var bookId=result.data;
				var str_li='<li class="online">'+
								'<a class="unchecked">'+
								'	<i class="fa fa-book" title="笔记本" rel="tooltip-bottom"></i>'+bookName +
								'	<button type="button" class="btn btn-default btn-xs btn_position btn_delete"><i class="fa fa-times"></i></button>'+
								'</a>'+
							'</li>';
				var $li=$(str_li);
				$li.data("bookId",bookId);//藏bookId
				$("#first_side_right ul").prepend($li);
				closeWindow();
				$(".opacity_bg").hide();
			
			}else{
				alert("创建失败！");
			}
			
		}
	});
	
}


