<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<%@ include file="includeTags.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<%@ include file="includeStyle.jsp" %>
  		<script src="<%=request.getContextPath()%>/js/RowHandlers.js" language="javascript" type="text/javascript" /></script>
		<title>List Renners</title>
	</head>
	<authz:authorize ifAllGranted="ROLE_ADMIN">
		<body class="listRennersBody" onload="addRowHandlers('rennersTable', 'rowMouseOver', 'editRenner.htm', 'renner', 0)">
	</authz:authorize>
	<authz:authorize ifNotGranted="ROLE_ADMIN">
		<body class="listRennersEnDeelnemersBody" onload="addMouseOverHighlight('rennersTable', 'rowMouseOver')">
	</authz:authorize>
		<c:import url="headerTour.jsp"/>
		<c:import url="banner.jsp"/>
		<c:import url="leftColumn.jsp"/>
		<div id="bodyColumn">
			<h2>Renners</h2>
			<display:table id="rennersTable" name="model.renners" class="listTable rennersTable" requestURI="listRenners.htm" sort="list" defaultsort="1">
				<display:column property="nummer" class="nummerColumn" title="Rennernummer" sortable="true"/>
	  			<display:column property="voornaam" title="Voornaam" sortable="true"/>
	  			<display:column property="achternaam" title="Achternaam" sortable="true"/>
			</display:table>
		</div>
	</body>
</html>
