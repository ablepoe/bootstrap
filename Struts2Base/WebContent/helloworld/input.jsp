<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>input</title>
</head>
<body>
	This is input.jsp
	<form action="helloworld/helloworld" method="post">
		username: <input type="text" name="user.username" /><s:fielderror fieldName="rusername"/>
		password: <input type="password" name="user.password" /><s:fielderror fieldName="rpassword"/>
		<input type="submit" value="commit" />
		
	</form>
</body>
</html>