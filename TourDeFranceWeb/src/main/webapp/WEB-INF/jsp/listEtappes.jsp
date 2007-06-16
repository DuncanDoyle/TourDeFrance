<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="includeTags.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<%@ include file="includeStyle.jsp" %>
		<script src="<%=request.getContextPath()%>/js/RowHandlers.js" language="javascript" type="text/javascript" /></script>
		<title>Etappes</title>
	</head>
	<body class="listEtappesBody" onload="addRowHandlers('etappesTable', 'rowMouseOver', 'editEtappe.htm', 'etappe', 0)">
		<h2>Etappes</h2>
		<display:table id="etappesTable" name="model.etappes" class="listTable etappesTable" requestURI="listEtappes.htm" sort="list" defaultsort="1">
			<display:column property="etappenummer" class="hidden" headerClass="hidden" title="Etappe Nummer" sortable="true"/>
			<display:column property="omschrijving" title="Etappe Omschrijving"/>
  			<display:column property="datum" title="Datum" sortable="true"/>
  			<%-- Hier gaan nog dingen fout met Lazy-Initialization. Moet nog een object tussen
  			gezet worden die een mapping naar een String doet.
  			 --%>
  			<display:column property="startplaats.stad" title="Startplaats" sortable="true"/>
  			<display:column property="finishplaats.stad" title="Finishplaats" sortable="true"/> 
		</display:table>
	</body>
</html>