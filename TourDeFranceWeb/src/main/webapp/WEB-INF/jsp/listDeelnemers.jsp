<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="includeTags.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<%@ include file="includeStyle.jsp" %>
  		<script src="<%=request.getContextPath()%>/js/RowHandlers.js" language="javascript" type="text/javascript" /></script>
		<title>List Deelnemers</title>
	</head>
	<authz:authorize ifAllGranted="ROLE_ADMIN">
		<body class="listDeelnemersBody composite" onload="addRowHandlers('deelnemersTable', 'rowMouseOver', 'editDeelnemer.htm', 'deelnemer', 0)">
	</authz:authorize>
	<authz:authorize ifNotGranted="ROLE_ADMIN">
		<body class="listDeelnemersBody" onload="addMouseOverHighlight('deelnemersTable', 'rowMouseOver')">
	</authz:authorize>
		<c:import url="headerTour.jsp"/>
		<c:import url="banner.jsp"/>
		<c:import url="leftColumn.jsp"/>
		<div id="bodyColumn">
			<h2>Deelnemers</h2>
			<authz:authorize ifAllGranted="ROLE_ADMIN">
				<display:table id="deelnemersTable" name="model.deelnemers" class="listTable deelnemersTable" requestURI="listDeelnemers.htm" sort="list" defaultsort="1">
					<display:column property="nummer" class="nummerColumn" title="Deelnemernummer" sortable="true"/>
		  			<display:column property="voornaam" title="Voornaam" sortable="true"/>
		  			<display:column property="achternaam" class="achternaamColumn" title="Achternaam" sortable="true"/>
		  			<display:column property="email" title="E-Mail" sortable="true"/>
		  			<display:column property="rekeningnummer" class="rekeningnummerColumn" title="Rekeningnummer" sortable="true"/>
				</display:table>
			</authz:authorize>
			<authz:authorize ifNotGranted="ROLE_ADMIN">
				<display:table id="deelnemersTable" name="model.deelnemers" class="listTable deelnemersTableNonAdmin" requestURI="listDeelnemers.htm" sort="list" defaultsort="1">
					<display:column property="nummer" class="nummerColumn" title="Deelnemernummer" sortable="true"/>
		  			<display:column property="voornaam" title="Voornaam" sortable="true"/>
		  			<display:column property="achternaam" class="achternaamColumn" title="Achternaam" sortable="true"/>
		  		</display:table>
			</authz:authorize>
				
			
		</div>
	</body>
</html>