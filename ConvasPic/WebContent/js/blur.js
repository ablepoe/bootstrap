var canvasWidth = window.innerWidth;
var canvasHeight = window.innerHeight;
//获取canvas对象
var canvas = $("#canvas")[0];
//等同于 var canvas = document.getElementById("canvas");
//获取canvas的上下文环境
var context = canvas.getContext("2d");

canvas.width = canvasWidth;
canvas.height = canvasHeight;
//定义自适应图片的显示canvas顶边距/左边距
var topMargin = 0, leftMargin = 0;

//创建image对象
var image = new Image();
//定义需要具体显示的区域范围
var radius = 50;
var clippingRegion = {x:-1, y:-1, r:radius};
//设置image路径
image.src = "images/image.jpg";
//设置载入时调用的方法
image.onload = function(e){
	//设置blur-div的区域大小
	$("#blur-div").css("width", canvasWidth+"px");
	$("#blur-div").css("height", canvasHeight+"px");
	//设置blur-image的区域大小，使用图片的默认大小
	$("#blur-image").css("width", image.width+"px");
	$("#blur-image").css("height", image.height+"px");
	//获取真正需要截取的背景图的边界值
	leftMargin = (image.width - canvas.width)/2;
	topMargin = (image.height - canvas.height)/2;
	
	$("#blur-image").css("top", String(-topMargin)+"px");
	$("#blur-image").css("left", String(-leftMargin)+"px");
	//initCanvas();
	reset();
}

var showAnimate;
var resetAnimate;

//定义初始化图像
function initCanvas(){
	//处理图像小于剪辑区域的情况
	var theLeft = leftMargin<0?-leftMargin:0;
	var theTop = topMargin<0?-toptMargin:0;
	
	//清除定时
	clearInterval(showAnimate);
	//初始化参数
	//随机初始话圆心坐标。确保圆心距离边界至少有一个半价的距离
	clippingRegion = {x: Math.random()*(canvas.width-2*radius-2*theLeft) + radius + theLeft,
			y: Math.random()*(canvas.height-2*radius-2*theTop) + radius +theTop,
			r: radius};
	
	draw(image);
}

function setClippingRegion(clippingRegion){
	//开启路径绘制
	context.beginPath();
	//绘制弧线
	context.arc(clippingRegion.x, clippingRegion.y, clippingRegion.r, 0 , Math.PI*2, false);
	//设置区域为剪辑区域
	context.clip();
}

//作图逻辑
function draw(image){
	//清除绘图区域
	context.clearRect(0 ,0 , canvas.width, canvas.height);
	//保存当前绘图状态
	context.save();
	//设置绘图剪辑区域
	setClippingRegion(clippingRegion);
	//作图
	//context.drawImage(image, 0, 0);
	//作部分截取的图 参数分别为-图片 ; 原图x , 原图 y , 原图宽 , 原图高  ; 目标绘图x , 目标绘图y , 目标绘图宽 , 目标绘图高
	//context.drawImage(image, leftMargin, topMargin, canvas.width, canvas.height, 0, 0,canvas.width, canvas.height);
	//处理图像小于剪辑区域的问题
	context.drawImage(image, Math.max(leftMargin,0), Math.max(topMargin,0),
			Math.min(canvas.width,image.width), Math.min(canvas.height,image.height),
			leftMargin<0?-leftMargin:0, topMargin<0?-topMargin:0,
			Math.min(canvas.width,image.width), Math.min(canvas.height,image.height));
	
	
	//恢复之前绘图状态
	context.restore();
}

function reset(){
	var timeInterval = 10;
	//initCanvas();
	//清除定时
	clearInterval(showAnimate);
	clearInterval(resetAnimate);
	//初始化参数
	//随机初始话圆心坐标。确保圆心距离边界至少有一个半价的距离
	clippingRegion = {x: Math.random()*(canvas.width-2*radius) + radius, y: Math.random()*(canvas.height-2*radius) + radius, r: radius};
	//渐变半径
	var initRadius = 0;
	resetAnimate = setInterval(function(e){
		//清除绘图区域
		context.clearRect(0 ,0 , canvas.width, canvas.height);
		//保存当前绘图状态
		context.save();
		if(clippingRegion.r < initRadius){
			clearInterval(resetAnimate);
		}
		//开启路径绘制
		context.beginPath();
		//绘制弧线
		context.arc(clippingRegion.x, clippingRegion.y, initRadius, 0 , Math.PI*2, false);
		//设置区域为剪辑区域
		context.clip();
		//作图
		context.drawImage(image, 0, 0);
		//恢复之前绘图状态
		context.restore();
		initRadius ++;
	},timeInterval);
}

function show(){
	//清除定时
	clearInterval(showAnimate);
	clearInterval(resetAnimate);
	
	var maxium = Math.sqrt(canvas.width * canvas.width + canvas.height * canvas.height);
	var timeInterval = 30;
	var radiusIncrease = 30;
	
	showAnimate = setInterval(function(e){
		//设置绘图边界
		clippingRegion.r += radiusIncrease;
		//圆心最大范围
		if(clippingRegion.r > maxium ){
			//清除定时
			clearInterval(showAnimate);
		}
		//绘图
		draw(image);
	},timeInterval);
}