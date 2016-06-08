<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet">
<script src="//cdn.bootcss.com/jquery/2.2.4/jquery.min.js" type="text/javascript"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.js" type="text/javascript"></script>
<script src="//cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.js" type="text/javascript"></script>
<script src="//cdn.bootcss.com/jquery.countdown/2.1.0/jquery.countdown.js" type="text/javascript"></script>
<script src="/seckill/resources/js/detail.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="panel panel-default">
		  <!-- Default panel contents -->
		  <div class="panel-heading">Panel heading</div>
		  <!-- Table -->
		  <table class="table">
		    <thead>
				<td>id</td>
				<td>name</td>
				<td>num</td>
				<td>startTime</td>
				<td>endTime</td>
			</thead>
			<tbody>
				<tr>
					<td>${data.id }</td>
					<td>${data.name }</td>
					<td>${data.num }</td>
					<td>${data.startTime }</td>
					<td><fmt:formatDate value="${data.endTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>
			</tbody>
		  </table>
		  <div class="panel-body">
		    <p><span class="glyphicon glyphicon-phone"></span><label id="countDown" class="label label-default">temp</label></p>
		    <p><button id="seckillBtn" class="btn btn-default">抢购</button></p>
		    <p><label id="seckillMsg" class="label label-danger"></label></p>
		  </div>
		</div>
		
		<div id="login" class="modal fade">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title">Modal title</h4>
		      </div>
		      <div class="modal-body">
		        <label for="recipient-name" class="control-label">请输入手机号:</label>
            	<input type="text" class="form-control" id="userPhone">
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		        <label id="alertMsg" class="label label-danger"></label>
		        <button id="checkPhone" type="button" class="btn btn-primary">Submit</button>
		      </div>
		    </div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
	</div>
</body>
<script type="text/javascript">
	$(function(){
		detail.init('${data.id }','${data.startTime.time }','${data.endTime.time }');
	})
</script>
</html>