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
	<body class="listDeelnemersBody" onload="addRowHandlers('deelnemersTable', 'rowMouseOver', 'editDeelnemer.htm', 'deelnemer', 0)">
		<h2>Deelnemers</h2>
		<display:table id="deelnemersTable" name="model.deelnemers" class="listTable deelnemersTable" requestURI="listDeelnemers.htm" sort="list" defaultsort="1">
			<display:column property="nummer" class="nummerColumn" title="Deelnemernummer" sortable="true"/>
  			<display:column property="voornaam" title="Voornaam" sortable="true"/>
  			<display:column property="achternaam" title="Achternaam" sortable="true"/>
  			<display:column property="email" title="E-Mail" sortable="true"/>
  			<display:column property="rekeningnummer" title="Rekeningnummer" sortable="true"/>
		</display:table>
	</body>
</html>