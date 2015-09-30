<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://taglib.framework.al24.afcepf.fr" prefix="frmk" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Connexion</title>
</head>
<body>
		<form action="deconnexion.frmk" method="post">
			<frmk:hello name="yan"/>
			<span>Vous etes bien connecté</span>
			<input type="submit" value="Se déconnecter" />
		</form>
</body>
</html>