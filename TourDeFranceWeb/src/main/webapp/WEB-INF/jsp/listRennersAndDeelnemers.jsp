<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="includeTags.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<%@ include file="includeStyle.jsp" %>
  		<script src="<%=request.getContextPath()%>/js/RowHandlers.js" language="javascript" type="text/javascript" /></script>
		<title>Renners & Deelnemers</title>
	</head>
	<body class="listRennersEnDeelnemersBody" onload="addRowHandlers('rennersAndDeelnemersTable', 'rowMouseOver', 'editRenner.htm', 'renner', 0)">
		<h2>Renners & Deelnemers</h2>
		<display:table id="rennersAndDeelnemersTable" name="model.rennersAndDeelnemers" class="listTable rennersEnDeelnemersTable" requestURI="listRennersAndDeelnemers.htm" sort="list" defaultsort="1">
			<display:column property="renner.nummer" class="nummerColumn" title="Renner Nummer" sortable="true"/>
  			<display:column property="renner.voornaam" title="Renner Voornaam" sortable="true"/>
  			<display:column property="renner.achternaam" title="Renner Achternaam" sortable="true"/>
  			<display:column property="deelnemer.nummer" class="nummerColumn" title="Deelnemer Nummer" sortable="true"/>
  			<display:column property="deelnemer.voornaam" title="Deelnemer Voornaam" sortable="true"/>
  			<display:column property="deelnemer.achternaam" title="Deelnemer Achternaam" sortable="true"/>
		</display:table>
	</body>
</html>