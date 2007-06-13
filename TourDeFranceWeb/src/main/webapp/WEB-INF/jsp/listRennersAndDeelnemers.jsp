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
			<display:column property="rennerNummer" class="nummerColumn" title="Renner Nummer" sortable="true"/>
  			<display:column property="rennerVoornaam" title="Renner Voornaam" sortable="true"/>
  			<display:column property="rennerAchternaam" title="Renner Achternaam" sortable="true"/>
  			<display:column property="deelnemerNummer" class="nummerColumn" title="Deelnemer Nummer" sortable="true"/>
  			<display:column property="deelnemerVoornaam" title="Deelnemer Voornaam" sortable="true"/>
  			<display:column property="deelnemerAchternaam" title="Deelnemer Achternaam" sortable="true"/>
		</display:table>
	</body>
</html>