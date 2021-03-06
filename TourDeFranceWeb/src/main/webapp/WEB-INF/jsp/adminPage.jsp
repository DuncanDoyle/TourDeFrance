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
		<c:import url="headerTour.jsp"/>
		<c:import url="banner.jsp"/>
		<c:import url="leftColumn.jsp"/>
		<div id="bodyColumn">
			<h2>Administrator Page</h2>
			<br/>
			<br/>
			<a href="initializeGame.htm"><strong>Initialize Game</strong></a>
			<br/>
			<br/>
			<a href="generateTeams.htm"><strong>Generate Teams</strong></a>
			<br/>		
			<br/>
			<a href="generateTestData.htm"><strong>Generate Test Data</strong></a>
			<br/>
			<br/>
		</div>
	</body>
</html>