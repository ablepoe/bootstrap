function addLine(){
	//定义输入框
	var command_content = "<input type='text' name='command_content'/>";
	var command_id = "<input type='text' name='command_id'/>";
	//定义表单
	var data = "<tr><td>" + command_content + "</td><td>" + command_id + "</td></tr>";
	//填充表单
	var additionLine = $("#append").html();
	additionLine += data
	//渲染表单
	$("#append").html(additionLine);
}

function batchAdd(){
	//获取输入参数数组
	var command_ids_array = getArrayValues("command_id");
	var command_contents_array = getArrayValues("command_content");
	//为提交表单的属性赋值
	$("#ids").attr("value",command_ids_array);
	$("#contents").attr("value",command_contents_array);
	//提交表单
	var form = $("#mainForm");
	form.submit();
	alert("您已经提交成功");
}

function getArrayValues(target){
	var array = [];
	//获取输入参数
	var targets = $("input[name='"+target+"']");
	for (var int = 0; int < targets.length; int++) {
		array.push(targets[int].value);
	}
	return array;
}