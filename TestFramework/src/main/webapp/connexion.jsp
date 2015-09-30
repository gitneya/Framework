<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Page de connexion</title>
</head>
<body>

	<form action="connection.frmk" method="post">
		<div>
			<span>Login :</span> <input type="text" name="login"
				value="" />
		</div>
		<div>
			<span>Mot de passe :</span> <input type="text" name="pwd"
				value="" />
		</div>
		<div>
			<span>Code numérique" :</span> <input type="text" name="codeNum"
				value="" />
		</div>
		<input type="submit" value="Se connecter" />
	</form>
	<% if(session.getAttribute("message") != null) { %>
	<%= session.getAttribute("message") %>
	<% } %>
</body>
</html>