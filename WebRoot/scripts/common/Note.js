function loadNote(){
//	$("#notebookList li").click(function(){//出不来效果，因为异步的原因，此时li还没有回调显示出来
	//		alert("aaaaaaaaaaaa");
	//	});
		//那就换一种方式,使用动态绑定
		$("#notebookList").on("click","li",function(){
			$("#notebookList a").removeClass("checked");
			$(this).find("a").addClass("checked");
			
			$("#pc_part_2").show();
			$("#pc_part_4").hide();
			$("#pc_part_6").hide();
			$("#pc_part_7").hide();
			$("#pc_part_8").hide();
			$("#pc_part_3").show();
			$("#pc_part_5").hide();
			
			var bookId=$(this).data("bookId");
			//alert(bookId);
			$.ajax({
				url: "http://localhost:8080/cloud_note/loadNote.do",
				type: "post",
				dataType:"json" ,
				data: {"notebookId":bookId},
				success:function(result){
					$("#noteList").empty();//清空原有信息
					if(result.status==0){
						
						var notes=result.data;
						for(var i=0;i<notes.length;i++){
							var noteId=notes[i].cn_note_id;
							var noteTitle=notes[i].cn_note_title;
							var noteStatusId=notes[i].cn_note_status_id;
							//alert(noteStatusId);
							if(noteStatusId==null||noteStatusId=="1"){//已添加至回收站的不显示
								var str_li='<li class="online">'+
												'<a >'+
													'<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+noteTitle+'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>'+
												'</a>'+
												'<div class="note_menu" tabindex="-1">'+
													'<dl>'+
														'<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>'+
														'<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>'+
														'<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>'+
													'</dl>'+
												'</div>'+
											'</li>';
								var $li=$(str_li);
								$li.data("noteId",noteId);
								$("#noteList").append($li);
							}
						}
					}
				}
			});
		});
	
}

function addNote(){
	$(".opacity_bg").show();//显示背景
	$("#can").load("alert/alert_note.html");//显示对话框
}

function addNoteSure() {
	var noteTitle=$("#input_note").val();
	var userId=getCookie("uid");
	var bookId=$("#notebookList a.checked").parent().data("bookId");
	alert(userId+",,,"+bookId+",,,"+noteTitle);
	
	if(noteTitle==""){
		alert("名称不能为空！");
		return;
	}
	$.ajax({
		url:"http://localhost:8080/cloud_note/addNote.do",
		type:"post",
		dataType:"json",
		data:{"userId":userId,"bookId":bookId,"noteTitle":noteTitle},
		success:function(result){
			if(result.status==0){//成功
				var noteId=result.data;
				var str_li='<li class="online">'+
								'<a >'+
									'<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+noteTitle+'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>'+
								'</a>'+
								'<div class="note_menu" tabindex="-1">'+
									'<dl>'+
										'<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>'+
										'<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>'+
										'<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>'+
									'</dl>'+
								'</div>'+
							'</li>';
				var $li=$(str_li);
				$li.data("noteId",noteId);
				$("#noteList").prepend($li);
				closeWindow();
				$(".opacity_bg").hide();
			
			}else{
				alert("创建失败！");
			}
			
		}
	});
	
}


function findNoteBody(){
	$("#noteList li a").removeClass("checked");
//	$(this).find("a").addClass("checked");
//	var noteId=$(this).data("noteId");
//	var title=$(this).find("a").text();
	$(this).addClass("checked");
	var noteId=$(this).parent().data("noteId");
	var title=$(this).text();
	$.ajax({
		url:"http://localhost:8080/cloud_note/findNoteBody.do",
		type:"post",
		dataType:"json",
		data:{"noteId":noteId},
		success:function(result){
			if(result.status==0){
				//alert(title);
				$("#input_note_title").val(title);
				var body=result.data==null?"":result.data;
				um.setContent(body);
			}
			
		}
	});
}

function updateNote(){
	var noteId=$("#noteList a.checked").parent().data("noteId");
	var noteTitle=$("#input_note_title").val();
	var noteBody=um.getContent();
	//alert(noteId+",,,,"+noteTitle+",,,,"+noteBody);
	$.ajax({
		url:"http://localhost:8080/cloud_note/updateNote.do",
		type:"post",
		dataType:"json",
		data:{"noteId":noteId,"noteTitle":noteTitle,"noteBody":noteBody},
		success:function(result){
			if(result.status==0){
				var str='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+noteTitle+'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';		
				$("#noteList a.checked").html(str);
				
			}

		}
	});
}

function recoveryNote(){
	var noteId=$(this).parents("li").data("noteId");
	$.ajax({
		url:"http://localhost:8080/cloud_note/recoveryNote.do",
		type:"post",
		dataType:"json",
		data:{"noteId":noteId},
		success:function(result){
			if(result.status==0){
				$("#input_note_title").val("");
				um.setContent("");
				$("#notebookList a.checked").parent().click();
				alert("已经移入回收站！");
			}else {
				alert(result.msg);
			}

		}
	});
}

function shareNote() {
	var noteId=$(this).parents("li").data("noteId");
	var shareTitle=$("#input_note_title").val();
	var shareBody=um.getContent();
	//alert(noteId+"---"+shareTitle+"---"+shareBody);
	$.ajax({
		url:"http://localhost:8080/cloud_note/shareNote.do",
		type:"post",
		dataType:"json",
		data:{"noteId":noteId,"shareTitle":shareTitle,"shareBody":shareBody},
		success:function(result){
			if(result.status==0){
				alert("分享成功！");
			}else if(result.status==1){
				alert("该笔记已经被分享过了！");
			}
		}
	});
}

function searchNote(event){
	var code=event.keyCode;
	//alert(code);
	if(code==13){
		//alert(code);
		var content=$(this).val().trim();
		if(content==""){
			alert("搜索内容不能为空！");
		}else{
			var $ul=$("#pc_part_6 ul");
			$ul.empty();
			//alert(content);
			$.ajax({
				url:"http://localhost:8080/cloud_note/searchNote.do",
				type:"post",
				dataType:"json",
				data:{"content":content},
				success:function(result){
					if(result.status==0){
						//alert("搜索成功！");
						$("#pc_part_2").hide();
						$("#pc_part_4").hide();
						$("#pc_part_6").show();
						$("#pc_part_7").hide();
						$("#pc_part_8").hide();
						var notes=result.data;
						for(var i=0;i<notes.length;i++){
							var str='<li class="online">'+
										'<a href="#">'+
											'<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+notes[i].cn_share_title+'<button type="button" class="btn btn-default btn-xs btn_position btn_like"><i class="fa fa-star-o"></i></button><div class="time"></div>'+
										'</a>'+
									'</li>';
							var $li=$(str);
							$li.data("noteId",notes[i].cn_note_id);
							$ul.append($li);
						}
					}else if(result.status==1){
						alert("没有相关笔记！");
					}
				}
			});
		}
	}
}

function seeShare(){
	var noteId=$(this).data("noteId");
	//alert(noteId);
	$("#pc_part_3").hide();
	$("#pc_part_5").show();
	$.ajax({
		url:"http://localhost:8080/cloud_note/seeShare.do",
		type:"post",
		dataType:"json",
		data:{"noteId":noteId},
		success:function(result){
			if(result.status==0){
				$("#noput_note_title").text(result.data.cn_share_title);
				$("#shareBody").html(result.data.cn_share_body);
				
			}
		}
		
	});
}

function seeRecoveryNote(){
	$("#pc_part_2").hide();
	$("#pc_part_4").show();
	$("#pc_part_6").hide();
	$("#pc_part_7").hide();
	$("#pc_part_8").hide();
	$.ajax({
		url:"http://localhost:8080/cloud_note/seeRecoveryNote.do",
		type:"post",
		success:function(result){
			if(result.status==0){
				var notes=result.data;
				$("#pc_part_4 ul").empty();
				for(var i=0;i<notes.length;i++){
					var str='<li class="disable"><a >'+
								'<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i> '+
								notes[i].cn_note_title+
								'<button type="button" class="btn btn-default btn-xs btn_position btn_delete"><i class="fa fa-times"></i></button>'+
								'<button type="button" class="btn btn-default btn-xs btn_position_2 btn_replay"><i class="fa fa-reply"></i></button>'+
							'</a></li>';
					var $li=$(str);
					$li.data("noteId",notes[i].cn_note_id);
					$("#pc_part_4 ul").append($li);
				}
			}

		}
	});
}

function replayNote(){
	var $li=$(this).parents("li");
	var noteId=$li.data("noteId");
	//alert(noteId);
	$.ajax({
		url:"http://localhost:8080/cloud_note/replayNote.do",
		type:"post",
		dataType:"json",
		data:{"noteId":noteId},
		success:function(result){
			if(result.status==0){
				alert("还原成功！");
				$li.remove();
			}else{
				alert("操作失败！");
			}
		}
	});
}

function deleteNote(){
	var $li=$(this).parents("li");
	var noteId=$li.data("noteId");
	//alert(noteId);
	$.ajax({
		url:"http://localhost:8080/cloud_note/deleteNote.do",
		type:"post",
		dataType:"json",
		data:{"noteId":noteId},
		success:function(result){
			if(result.status==0){
				alert("删除成功！");
				$li.remove();
			}else{
				alert("操作失败！");
			}
		}
	});
}
