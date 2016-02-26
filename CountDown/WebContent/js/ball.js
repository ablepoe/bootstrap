var balls = {x:512, y:100, r:20, g:2, vx:-4, vy:-10, color:"#005588", friction:0.5};
//最大反弹次数
var bottomCount = 5;
//反弹次数
var jumpCount = 0;
$(function(){
	var canvas = $("#canvas")[0];
	var context = canvas.getContext("2d");
	
	canvas.width=1024;
	canvas.height=768;
	
	setInterval(function(){
		render(context);
		update();
	},50)
})

function update(){
	//x轴坐标
	balls.x += balls.vx;
	//y轴坐标
	balls.y += balls.vy;
	//重力加速度
	balls.vy += balls.g;
	if(balls.y >= (768-balls.r) ){
		//落地
		balls.y = (768-balls.r);
		//反弹一定次数
		jumpCount++;
		if(jumpCount < bottomCount){
			//反弹 ball.friction 摩擦系数
			balls.vy = -balls.vy*balls.friction;	
		}
	}
}

function render(ctx){
	ctx.clearRect(0,0,ctx.canvas.width,ctx.canvas.height);
	
	ctx.fillStyle = balls.color;
	ctx.beginPath();
	ctx.arc(balls.x,balls.y,balls.r,0,2*Math.PI);
	ctx.closePath();
	
	ctx.fill();
}

