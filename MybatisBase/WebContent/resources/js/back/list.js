function removeOne(basePath){
	var removeConfirm = confirm("是否确认删除");
	if(removeConfirm){
		var form  = $("#mainForm");
	    var path = basePath + "RemoveOne.action";  
	    form.attr("action", path);  
		form.submit();
	}
}

function removeBatch(basePath){
	var removeConfirm = confirm("是否确认删除");
	if(removeConfirm){
		var form  = $("#mainForm");
	    var path = basePath + "RemoveBatch.action";  
	    form.attr("action", path);  
		form.submit();
	}
}

function insertOne(basePath){
	var command = $("#command").val();
	var description = $("#description").val();
	var insertConfirm = confirm("是否确认添加以下指令: 指令名称="+command+" 描述="+description);
	if(insertConfirm){
		var form  = $("#mainForm");
	    var path = basePath + "insertOne.action";  
	    form.attr("action", path);  
		form.submit();
	}
}

function selectAll(all){
	if(all.checked){
		$(":checkbox").prop("checked",all.checked);	
	}else{
		$(":checkbox").prop("checked",all.checked);
	}
}

function changePage(pageNum){
	var form = $("#mainForm");
	if(pageNum == undefined || pageNum == null){
		alert("请输入想要跳转的页面");
		return;
	}else{
		$("#targetPage").val(pageNum);
	}
	form.submit();
}