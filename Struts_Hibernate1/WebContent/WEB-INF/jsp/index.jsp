<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Index</title>
</head>
<body>
	Action succeed!
	<h2>totalCounts : <s:property value ="totalCount" /></h2> 
	
	<table border="1">
		<thead>
			<td>sid</td>
			<td>username</td> 
			<td>password</td> 
			<td>birthDay</td> 
			<td>sex</td>	
		</thead>
		<tbody>
			<s:iterator var="item" value="allStudents">
				<tr>
					<td><s:property value="#item.sid"/></td>
					<td><s:property value="#item.username"/></td>
					<td><s:property value="#item.password"/></td>
					<td><s:date name="#item.birthDay" format="yyyy-MM-dd"/></td>
					<s:if test="1==#item.sex">
						<td><span>男</span></td>
					</s:if>
					<s:else>
						<td><span>女</span></td>
					</s:else>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	 
	
</body>
</html>