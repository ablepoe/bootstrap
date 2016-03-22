<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="X-UA-Compatible"content="IE=9; IE=8; IE=7; IE=EDGE" />
		<title>内容列表页面</title>
		<link href="<%=basePath %>resources/css/all.css" rel="stylesheet" type="text/css" />
		<script src="<%=basePath %>resources/js/common/jquery.min.js"></script>
		<script src="<%=basePath %>resources/js/back/list.js"></script>
	</head>
	<body style="background: #e1e9eb;">
		<form action="<%=basePath %>List.action" id="mainForm" method="post">
			<div class="right">
				<div class="current">当前位置：<a href="javascript:void(0)" style="color:#6E6E6E;">内容管理</a> &gt; 内容列表</div>
				<div class="rightCont">
					<p class="g_title fix">内容列表 <a class="btn03" href="javascript:void(0)" onclick="insertOne('<%=basePath %>')">新 增</a>
					&nbsp;&nbsp;&nbsp;&nbsp;<a class="btn03" href="javascript:void(0)" onclick="removeBatch('<%=basePath %>')">删 除</a></p>
					<table class="tab1" >
						<tbody>
							<tr>
								<td width="90" align="right">指令：</td>
								<td>
									<input name="command" id="command" type="text" class="allInput" value="${command }"/>
								</td>
								<td width="90" align="right">描述：</td>
								<td>
									<input name="description" id="description" type="text" class="allInput" value="${description }"/>
								</td>
	                            <td width="85" align="right"><input type="submit" class="tabSub" value="查 询" /></td>
	       					</tr>
						</tbody>
					</table>
					<div class="zixun fix">
						<table class="tab2" width="100%">
							<tbody>
								<tr>
								    <th><input type="checkbox" id="all" onclick="selectAll(this)"/></th>
								    <th>序号</th>
								    <th>指令名称</th>
								    <th>描述</th>
								    <th>操作</th>
								</tr>
								<c:forEach items="${microMessageList }" var ="message" varStatus = "status">
									<tr <c:if test="${status.index % 2 !=0 }">style='background-color:#ECF6EE;'</c:if>>
										<td><input type="checkbox" name="ids" value="${message.id}"/></td>
										<td>${status.index + 1}</td>
										<td>${message.command }</td>
										<td>${message.description }</td>
										<td>
											<a href="#">修改</a>&nbsp;&nbsp;&nbsp;
											<a href="javascript:void(0)" onclick="removeOne('<%=basePath %>')" >删除</a>
											<input type="hidden" name="id" value="${message.id}"></input>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<div class='page fix'>
							共 <b>${page.totalCounts }</b> 条
							<a href='javascript:void(0);' onclick="changePage(1)" class='first'>首页</a>
							<c:if test="${page.currentPage > 1}">
								<a href='javascript:void(0);' onclick="changePage(${page.currentPage - 1})" class='pre'>上一页</a>
							</c:if>
							当前第<span>${page.currentPage}/${page.totalPages }</span>页
							<c:if test="${page.totalPages > page.currentPage}">
								<a href='javascript:void(0);' onclick="changePage(${page.currentPage + 1})" class='next'>下一页</a>
							</c:if>
							<a href='javascript:void(0);' onclick="changePage(${page.totalPages })" class='last'>末页</a>
							跳至&nbsp;<input type='text' id="targetPage" name="targetPage" class='allInput w28' />&nbsp;页&nbsp;
							<a href='javascript:void(0);' onclick="changePage()"  class='go'>GO</a>
						</div>
					</div>
				</div>
			</div>
	    </form>
	</body>
</html>