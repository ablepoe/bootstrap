var tangram = [
   {p:[{x:0,y:0},{x:800,y:0},{x:400,y:400}],color:"#caff67"},
   {p:[{x:0,y:0},{x:400,y:400},{x:0,y:800}],color:"#67becf"},
   {p:[{x:800,y:0},{x:800,y:400},{x:600,y:600},{x:600,y:200}],color:"#ef3d61"},
   {p:[{x:600,y:200},{x:600,y:600},{x:400,y:400}],color:"#f9f51a"},
   {p:[{x:400,y:400},{x:600,y:600},{x:400,y:800},{x:200,y:600}],color:"#a594c0"},
   {p:[{x:200,y:600},{x:400,y:800},{x:00,y:800}],color:"#fa8ecc"},
   {p:[{x:800,y:400},{x:800,y:800},{x:400,y:800}],color:"#f6ca29"},
   ]

$(function(){
	//获取canvas元素
	var canvas = $("#canvas")[0];
	//获取canvas元素上下文环境
	var context = canvas.getContext("2d");
	
	canvas.width = 800;
	canvas.height = 800;
	//分别绘制每一块小的七巧板
	for (var i = 0; i < tangram.length; i++) {
		draw( tangram[i], context);
	}
})

function draw(piece , ctx){
	//绘制起始
	ctx.beginPath();
	//移动到起始点位置
	ctx.moveTo( piece.p[0].x, piece.p[0].y );
	//绘制连接线至其他顶点
	for (var i = 0; i < piece.p.length; i++) {
		ctx.lineTo(piece.p[i].x, piece.p[i].y);
	}
	//关闭绘制
	ctx.closePath();
	//设置填充颜色
	ctx.fillStyle = piece.color;
	//填充绘制区间
	ctx.fill();
}
	