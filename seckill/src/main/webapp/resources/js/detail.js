var clickDown;
var SUCCESSSTATUS = 1;
var detail = {
	count : 5,
	btnClickCountDown : function(){
	   if(detail.count==0){
		   $('#seckillBtn').html('立即抢购');
	       $("#seckillBtn").removeClass("disabled");
	       clearInterval(clickDown);
	       detail.count = 5;
	       return;
	   }
	   $("#seckillBtn").addClass("disabled");
	   $("#seckillBtn").html("请稍等 "+ detail.count +" 秒！");
	   detail.count--;
	},
	url : {
		getTime : function(){
			return '/seckill/getTime';
		},
		getUrl : function(id){
			return '/seckill/'+id+'/getUrl';
		},
		executeKill : function(id,md5){
			return '/seckill/'+id+'/'+md5+'/executeSeckill';
		}
	},
	checkMobile : function(str){
		var re = /^1\d{10}$/
	    if (re.test(str)) {
	        return true;
	    } else {
	        return false;
	    };
	},
	handleSeckill : function(id,btn){
		btn.click(function(){
			detail.btnClickCountDown();
			clickDown = setInterval(detail.btnClickCountDown,1000);
			//询问秒杀地址暴露
			$.post(detail.url.getUrl(id),{ },function(data){
				console.log(data);
				var exposer = data.data;
				if(exposer.status == SUCCESSSTATUS){
					//正处于秒杀状态中
					//进行秒杀请求
					detail.executeSeckill(id,exposer.md5,btn);
				}else{
					//不在秒杀状态中
					btn.html('未开始');
					btn.removeClass('btn-info disabled');
					btn.addClass('btn-warning disabled');
				}
			})
		});
	},
	executeSeckill : function(id,md5,btn){
		console.log(id);
		console.log(md5);
		$.post(detail.url.executeKill(id, md5),{ } ,function(data){
			console.log(data);
			if(data.status){
				clearInterval(clickDown);
				btn.hide();
//				btn.removeClass('btn-danger');
//				btn.addClass('btn-success disabled');
				$('#seckillMsg').hide().html('恭喜您秒杀成功'+data.message).show(300);
			}else{
				//clearInterval(clickDown);
				$('#seckillMsg').hide().html('秒杀失败:'+data.message).show(300);
			}
		});
	},
	countDown : function(label,btn,id,nowTime,startTime,endTime){
		if(nowTime > endTime){
			label.hide().html('秒杀已结束').show(300);
			btn.addClass('btn-info disabled');
		}else if(nowTime < startTime){
			label.countdown(new Date(parseInt(startTime)), function(event) {
			    $(this).html(event.strftime('%w weeks %d days %H:%M:%S'));
			    btn.html('即将开始');
			    btn.addClass('btn-success disabled');
			}).on('finish.countdown', function(){ //倒计时完成后回调事件
				detail.handleSeckill(id,btn);
			});
		}else{
			label.hide().html('秒杀进行中').show(300);
			btn.html('立即抢购');
			btn.removeClass('btn-success disabled');
			btn.addClass('btn-danger');
			detail.handleSeckill(id,btn);
		}
	},
	init : function(id, startTime, endTime){
		var user_phone = $.cookie('phone');
		if(user_phone){
			console.log(user_phone);
			//日期比较
			$.get(detail.url.getTime(), { }, function (data){
				detail.countDown($('#countDown'),$('#seckillBtn'),id,data.data,startTime,endTime);
			});
		}else{
			$("#login").modal({
				backdrop: 'static',
				keyboard: false,
				show: true
			});
		};
		//用户手机号检查
		$('#checkPhone').click(function(){
			var inputPhone = $('#userPhone');
			if(detail.checkMobile(inputPhone.val())){
				$.cookie('phone', inputPhone.val(), {expires: 7, path: '/seckill'});
				window.location.reload();
			}else{
				$('#alertMsg').hide().html('手机号错误！请重新输入').show(300);
			}
		});
	}
}

