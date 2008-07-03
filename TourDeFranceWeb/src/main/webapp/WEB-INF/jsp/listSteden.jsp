<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="includeTags.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<%@ include file="includeStyle.jsp" %>
		<script src="<%=request.getContextPath()%>/js/RowHandlers.js" language="javascript" type="text/javascript" /></script>
		<title>List Steden</title>
	</head>
	<body class="listStedenBody" onload="addRowHandlers('stedenTable', 'rowMouseOver', 'editStad.htm', 'stadId', 0)">
		<c:import url="headerTour.jsp"/>
		<c:import url="banner.jsp"/>
		<c:import url="leftColumn.jsp"/>
		<div id="bodyColumn">
			<h2>Steden</h2>
			<display:table id="stedenTable" name="model.steden" class="listTable stedenTable" requestURI="listSteden.htm" sort="list" defaultsort="1">
				<display:column property="id" class="nummerColumn" title="Stad ID" sortable="true"/>
	  			<display:column property="stad" title="Stad" sortable="true"/>
	  			<display:column property="land" title="Land" sortable="true"/>
	  			<display:column property="longitude" title="Longitude" sortable="true"/>
	  			<display:column property="latitude" title="Latitude" sortable="true"/>
			</display:table>
			<a href="nieuweStad.htm"/>Nieuwe Stad</a>
		</div>
	</body>
</html>