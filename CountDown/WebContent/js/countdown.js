var WINDOW_WIDTH = 1024;
var WINDOW_HEIGHT = 768;
var RADIUS = 8;
var MARGIN_TOP = 60;
var MARGIN_LEFT = 20;

var endTime = new Date();
endTime.setTime( endTime.getTime() + 3600*1000);

var curShowTimeSeconds = 0;
//掉落的小球
var balls = [];
//小球可能的颜色
var colors = ["#005588","#FF6EB4","#FAFAD2","#F0FFF0","#EECBAD","#EE82EE"];
//最大反弹次数
var bottomCount = 5;
//反弹次数
var jumpCount = [];
//摩擦系数
var friction = 0.5;

$(function(){
	//自适应周长
	WINDOW_WIDTH = document.body.clientWidth;
	//WINDOW_HEIGHT = document.body.clientHeight;
	//firefox 需要用以下这个
	WINDOW_HEIGHT = document.documentElement.clientHeight
	//自适应半径
	RADIUS = Math.round(WINDOW_WIDTH * 4 / 5 / 108) - 1;
	//自适应左边距
	MARGIN_LEFT = Math.round(WINDOW_WIDTH / 10);
	//自适应右边距
	MARGIN_TOP = Math.round(WINDOW_HEIGHT / 5);
	
	console.log(WINDOW_WIDTH);
	console.log(WINDOW_HEIGHT);
	
	var canvas = $("#canvas")[0];
	
	canvas.width = WINDOW_WIDTH;
	canvas.height = WINDOW_HEIGHT;
	
	var context = canvas.getContext("2d");
	//获取倒计时的时count down 时间
	curShowTimeSeconds = getCurrentShowTimeSeconds();
//	render( context)
	
	setInterval(function(){
		render(context);
		update();
	},50);
	
})

function update(){
	//获取下一秒的时间值
	var nextShowTimeSeconds = getCurrentShowTimeSeconds();
	var nextHours = parseInt(nextShowTimeSeconds / 3600);
	var nextMinutes = parseInt( (nextShowTimeSeconds - nextHours * 3600) / 60);
	var nextSeconds = parseInt(nextShowTimeSeconds % 60);
	//得到当前时间值
	var curHours = parseInt(curShowTimeSeconds / 3600);
	var curMinutes = parseInt( (curShowTimeSeconds - curHours * 3600) / 60);
	var curSeconds = parseInt(curShowTimeSeconds % 60);
	//进入下一秒后替换时间值
	if(nextSeconds != curSeconds){
		//小时的 十 位值
		if( parseInt(curHours/10) != parseInt(nextHours/10) ){
			addBalls( MARGIN_LEFT + 0*(RADIUS+1), MARGIN_TOP, parseInt(curHours/10) );
		}
		//小时的 个 位值
		if( parseInt(curHours%10) != parseInt(nextHours%10) ){
			addBalls( MARGIN_LEFT + 15*(RADIUS+1), MARGIN_TOP, parseInt(curHours%10) );
		}
		//分钟的 十 位值
		if( parseInt(curMinutes/10) != parseInt(nextMinutes/10) ){
			addBalls( MARGIN_LEFT + 39*(RADIUS+1), MARGIN_TOP, parseInt(curMinutes/10) );
		}
		//分钟的 个 位值
		if( parseInt(curMinutes%10) != parseInt(nextMinutes%10) ){
			addBalls( MARGIN_LEFT + 54*(RADIUS+1), MARGIN_TOP, parseInt(curMinutes%10) );
		}
		//秒的 十 位值
		if( parseInt(curSeconds/10) != parseInt(nextSeconds/10) ){
			addBalls( MARGIN_LEFT + 78*(RADIUS+1), MARGIN_TOP, parseInt(curSeconds/10) );
		}
		//秒的 个 位值
		if( parseInt(curSeconds%10) != parseInt(nextSeconds%10) ){
			addBalls( MARGIN_LEFT + 93*(RADIUS+1), MARGIN_TOP, parseInt(curSeconds%10) );
		}
		curShowTimeSeconds = nextShowTimeSeconds;
	}
	//更新掉落的球的状态
	updateBalls();
//	console.log(balls.length);
}

function updateBalls(){
	for (var i = 0; i < balls.length; i++) {
		//x轴坐标
		balls[i].x += balls[i].vx;
		//y轴坐标
		balls[i].y += balls[i].vy;
		//重力加速度
		balls[i].vy += balls[i].g;
		if(balls[i].y >= (WINDOW_HEIGHT-RADIUS) ){
			//落地
			balls[i].y = (WINDOW_HEIGHT-RADIUS);
			//反弹一定次数
//			jumpCount[i]++;
//			if(jumpCount[i] < bottomCount){
				//反弹 ball.friction 摩擦系数
				balls[i].vy = -balls[i].vy*friction;	
//			}
		}
	}
	
	var cnt = 0;
	for (var i = 0; i < balls.length; i++) {
		//左边界  && 右边界
		if(balls[i].x + RADIUS > 0 && balls[i].x - RADIUS < WINDOW_WIDTH){
			balls[cnt++] = balls[i];
		}
	}
	while( balls.length > Math.min(400,cnt)){
		balls.pop();
	}
}

function addBalls(x, y, num){
//	console.log(digit[num]);
	//遍历digit[num]的每一行
	//console.log(digit[num]);
	/*[Array[7], Array[7], Array[7], Array[7], Array[7], Array[7], Array[7], Array[7], Array[7], Array[7]]
		0: Array[7]
		1: Array[7]
		2: Array[7]
		3: Array[7]
		4: Array[7]
		5: Array[7]
		6: Array[7]
		7: Array[7]
		8: Array[7]
		9: Array[7]*/
	for (var i = 0; i < digit[num].length; i++) {
		//遍历digit[num]每一行的每一列
//		console.log(digit[num][i]);
/*		[0, 0, 0, 1, 1, 0, 0] 
		[0, 1, 1, 1, 1, 0, 0] 
		[0, 0, 0, 1, 1, 0, 0] 
		[0, 0, 0, 1, 1, 0, 0] 
		[0, 0, 0, 1, 1, 0, 0] 
		[0, 0, 0, 1, 1, 0, 0] 
		[0, 0, 0, 1, 1, 0, 0] 
		[0, 0, 0, 1, 1, 0, 0] 
		[0, 0, 0, 1, 1, 0, 0] 
		[1, 1, 1, 1, 1, 1, 1]*/
		for (var j = 0; j < digit[num][i].length; j++) {
			//数值检查
			if(digit[num][i][j] == 1){
				
				//添加掉落的小球
				var aBall = {
						x: x + j*2*(RADIUS+1) + (RADIUS+1),
						y: y + i*2*(RADIUS+1) + (RADIUS+1),
						g: 1.5 + Math.random(),
						//正数1*4 负数-1*4
						vx: Math.pow(-1, Math.ceil(Math.random()* 1000) ) *5,
						vy: -5,
						color: colors[ Math.floor( Math.random()*colors.length) ]
				}
				balls.push(aBall);
				jumpCount.push(0);
			}
		}
	}
	
}

function getCurrentShowTimeSeconds(){
	//获取当前时间与结束时间相差的秒数
	var curTime = new Date();
	//得到的结果是毫秒值
	var ret = endTime.getTime() - curTime.getTime();
	//转为秒
	ret = Math.round( ret/1000);
	
	return ret>0?ret:0; 
}

function render (ctx){
	//清空当前绘制的图像
	ctx.clearRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
	
	var hours = parseInt(curShowTimeSeconds / 3600);
	var minutes = parseInt((curShowTimeSeconds - hours * 3600)/60 );
	var seconds = parseInt(curShowTimeSeconds % 60);
	
	renderDigit(MARGIN_LEFT , MARGIN_TOP, parseInt(hours/10), ctx);
	//15*(RADIUS+1) : 2*RADIUS+1为每个小圆圈格子的单边长，由于一个数字中，每行有7列是用于存放数字点阵
	//故单个数字的宽为  2*7*(RADIUS+1)
	//为了美观，在此基础在再加上1个单位的 RADIUS+1
	renderDigit(MARGIN_LEFT + 15*(RADIUS+1) , MARGIN_TOP, parseInt(hours%10), ctx);
	//这里是 : 
	//在digit数组中, : 位于第10个位置
	renderDigit(MARGIN_LEFT + 30*(RADIUS+1) , MARGIN_TOP, parseInt(10), ctx);
	//注意，由于 : 只占据4列
	//故单个 : 的宽为 2*4*(RADIUS+1)
	//为了美观，在此基础在再加上1个单位的 RADIUS+1
	renderDigit(MARGIN_LEFT + 39*(RADIUS+1) , MARGIN_TOP, parseInt(minutes/10), ctx);
	renderDigit(MARGIN_LEFT + 54*(RADIUS+1) , MARGIN_TOP, parseInt(minutes%10), ctx);
	renderDigit(MARGIN_LEFT + 69*(RADIUS+1) , MARGIN_TOP, parseInt(10), ctx);
	renderDigit(MARGIN_LEFT + 78*(RADIUS+1) , MARGIN_TOP, parseInt(seconds/10), ctx);
	renderDigit(MARGIN_LEFT + 93*(RADIUS+1) , MARGIN_TOP, parseInt(seconds%10), ctx);
}

function renderDigit(x,y,num,ctx){
	
	ctx.fillStyle = "rgb(0,102,153)";
	
//	console.log(digit[num]);
	//遍历digit[num]的每一行
	//console.log(digit[num]);
	/*[Array[7], Array[7], Array[7], Array[7], Array[7], Array[7], Array[7], Array[7], Array[7], Array[7]]
		0: Array[7]
		1: Array[7]
		2: Array[7]
		3: Array[7]
		4: Array[7]
		5: Array[7]
		6: Array[7]
		7: Array[7]
		8: Array[7]
		9: Array[7]*/
	for (var i = 0; i < digit[num].length; i++) {
		//遍历digit[num]每一行的每一列
//		console.log(digit[num][i]);
/*		[0, 0, 0, 1, 1, 0, 0] 
		[0, 1, 1, 1, 1, 0, 0] 
		[0, 0, 0, 1, 1, 0, 0] 
		[0, 0, 0, 1, 1, 0, 0] 
		[0, 0, 0, 1, 1, 0, 0] 
		[0, 0, 0, 1, 1, 0, 0] 
		[0, 0, 0, 1, 1, 0, 0] 
		[0, 0, 0, 1, 1, 0, 0] 
		[0, 0, 0, 1, 1, 0, 0] 
		[1, 1, 1, 1, 1, 1, 1]*/
		for (var j = 0; j < digit[num][i].length; j++) {
			//数值检查
			if(digit[num][i][j] == 1){
				ctx.beginPath();
				//绘制小圆圈
				//根据坐标位置确定圆心的坐标
/*				i=0 j=0 → x=r+1 y=r+1
				i=0 j=1 → x=3*(r+1) y=r+1
				i=0 j=2 → x=5(r+1) y=r+1
				
				i=0 j=0 → x=r+1 y=r+1
				i=1 j=0 → x=r+1 y=3*(r+1)
				i=2 j=0 → x=r+1 y=5*(r+1)
						↓
				x = j*(r+1) + r+1;
				y = i*(r+1) + r+1;*/
				ctx.arc(x + j*2*(RADIUS+1) + (RADIUS+1), y + i*2*(RADIUS+1) + (RADIUS+1) , RADIUS, 0 , 2*Math.PI);
				ctx.closePath();
				ctx.fill();
			}
		}
	}
	
	//绘制掉落的小球
	for (var i = 0; i < balls.length; i++) {
		//填充颜色
		ctx.fillStyle = balls[i].color;
		
		ctx.beginPath();
		ctx.arc(balls[i].x, balls[i].y, RADIUS, 0, 2*Math.PI);
		ctx.closePath();
		ctx.fill();
		
	}
	
}