var canvas;
var context;

var window_width;
var window_height;

var radius = 50;

var increase = 50;

var show_interval;

var clipRegion = {newx: -1, newy : -1, r : radius};

var topMargin = 0, leftMargin = 0;
var theLeft,theTop;

var image = new Image();
image.src = "image/image.jpg";
//image.src = "image/image_800x600.jpg";

image.onload = function(){
	
	window_width = window.innerWidth;
	window_height = window.innerHeight;
	
	$("#blur-div").css("width",window.width+"px");
	$("#blur-div").css("height",window_height+"px");
	
	$("#blur-image").css("width",image.width+"px");
	$("#blur-image").css("height",image.height+"px");
	
	canvas = $("#canvas")[0];
	context = canvas.getContext("2d");
	
	canvas.width = window_width;
	canvas.height = window_height;
	
	leftMargin = (image.width - canvas.width)/2;
	topMargin = (image.height - canvas.height)/2;
	
	$("#blur-image").css("top", String(-topMargin)+"px");
	$("#blur-image").css("left", String(-leftMargin)+"px");
	
	initCanvas();
}

function initCanvas(){
	
	clearInterval(show_interval);
	
	theLeft = leftMargin < 0 ? -leftMargin : 0;
	theTop = topMargin < 0 ? -topMargin : 0;
	
	reset();
}

function setClippingRegion(x,y,redius){
	context.beginPath();
	context.arc(x,y,clipRegion.r,0,2*Math.PI);
	context.clip();
}

function draw(image){
	
	context.clearRect(0,0,canvas.width,canvas.height);
	
	context.beginPath();
	context.save();
	setClippingRegion(clipRegion.newx,clipRegion.newy,clipRegion.r);
	
	
//	context.drawImage(image,0,0);
	context.drawImage(image, Math.max(leftMargin,0), Math.max(topMargin,0),
			Math.min(canvas.width,image.width), Math.min(canvas.height,image.height),
			leftMargin<0?-leftMargin:0, topMargin<0?-topMargin:0,
			Math.min(canvas.width,image.width), Math.min(canvas.height,image.height));
	
	context.restore();
}

function show(){

	clearInterval(show_interval);
	
	var maxium = Math.sqrt(canvas.width * canvas.width + canvas.height * canvas.height);
	
	show_interval = setInterval(function(){
		clipRegion.r += increase;
		if(clipRegion.r > maxium){
			clearInterval(show_interval);
		}
		draw(image);
	},30)
}

function reset(){
	clearInterval(show_interval);
	
	clipRegion.newx = Math.random()*(canvas.width - 2*radius - 2*theLeft) + radius + theLeft;
	clipRegion.newy = Math.random()*(canvas.height - 2*radius - 2*theTop) + radius + theTop;
	clipRegion.r = radius;
	
	draw(image);
}