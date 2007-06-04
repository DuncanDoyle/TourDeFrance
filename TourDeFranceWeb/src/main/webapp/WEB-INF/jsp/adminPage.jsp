<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="includeTags.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<%@ include file="includeStyle.jsp" %>
		<script src="<%=request.getContextPath()%>/js/RowHandlers.js" language="javascript" type="text/javascript" /></script>
		<title>Admin Page</title>
	</head>
	<body>
		<a href="initializeGame.htm">Initialize Game</a>
		<br/>
		<br/>
		<a href="generateTeams.htm">Generate Teams</a>
		<br/>		
		<br/>
	</body>
</html>