<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>input2</title>
</head>
<body>
	This is input.jsp
	<form action="helloworld2/helloworld2" method="post">
		username: <input type="text" name="username" /><s:fielderror fieldName="rusername"/>
		password: <input type="password" name="password" /><s:fielderror fieldName="rpassword"/>
		<input type="submit" value="commit" />
		
	</form>
</body>
</html>