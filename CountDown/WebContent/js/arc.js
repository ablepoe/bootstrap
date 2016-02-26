 $(function(){
	var canvas = $("#canvas")[0];
	canvas.width = 1024;
	canvas.height = 768;
	
	var context = canvas.getContext("2d");
	//定义填充颜色
	context.fillStyle = "#005588"
	for (var i = 0; i < 10; i++) {
		//绘制起始
		context.beginPath();
		context.arc(50 + 100*i ,100,30,0,2*(i+1)/10*Math.PI,false);
		//绘制结束
		context.closePath();
		//绘制线
		context.stroke();
		//填充绘制区间
		context.fill();
	}
	
	//线条颜色
	context.strokeStyle = "orange";
	//填充颜色
	context.fillStyle = "red"
	for (var i = 0; i < 10; i++) {
		//绘制起始
		context.beginPath();
		context.arc(50 + 100*i ,200,30,0,2*(i+1)/10*Math.PI,true);
		//绘制结束
		context.closePath();
		//绘制线
		context.stroke();
		//填充绘制区间
//		context.fill();
	}
	
	//填充颜色
	context.fillStyle = "blue"
	for (var i = 0; i < 10; i++) {
		//绘制起始
		context.beginPath();
		context.arc(50 + 100*i ,300,30,0,2*(i+1)/10*Math.PI,false);
		//绘制线
		context.stroke();
		//填充绘制区间
		context.fill();
	}

	//线条颜色
	context.strokeStyle = "red"
	//填充颜色
	context.fillStyle = "green"
	for (var i = 0; i < 10; i++) {
		//绘制起始
		context.beginPath();
		context.arc(50 + 100*i ,400,30,0,2*(i+1)/10*Math.PI,true);
		//绘制线
		context.stroke();
		//填充绘制区间
//		context.fill();
	}
	
 });