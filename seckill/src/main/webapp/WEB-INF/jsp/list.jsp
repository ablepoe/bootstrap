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


<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="panel panel-default">
		  <!-- Default panel contents -->
		  <div class="panel-heading">Panel heading</div>
		  <div class="panel-body">
		    <p>...</p>
		  </div>
		
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
				<c:forEach items="${list }" var="item">
				<tr>
					<td>${item.id }</td>
					<td>${item.name }</td>
					<td>${item.num }</td>
					<td>${item.startTime }</td>
					<td><fmt:formatDate value="${item.endTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td><a href="${item.id }/detail" class="navbar-link">Link</a></td>
				</tr>
			</c:forEach>		
			</tbody>
		  </table>
		</div>
	</div>
</body>
</html>