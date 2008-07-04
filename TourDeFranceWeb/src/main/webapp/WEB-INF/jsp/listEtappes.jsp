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
	<authz:authorize ifAllGranted="ROLE_ADMIN">
		<body class="listEtappesBody" onload="addRowHandlers('etappesTable', 'rowMouseOver', 'editEtappe.htm', 'etappe', 0)">
	</authz:authorize>
	<authz:authorize ifNotGranted="ROLE_ADMIN">
		<body class="listEtappesBody" onload="addMouseOverHighlight('etappesTable', 'rowMouseOver')">
	</authz:authorize>
		<c:import url="headerTour.jsp"/>
		<c:import url="banner.jsp"/>
		<c:import url="leftColumn.jsp"/>
		<div id="bodyColumn">
			<h2>Etappes</h2>
			<display:table id="etappesTable" name="model.etappes" class="listTable etappesTable" requestURI="listEtappes.htm" sort="list" defaultsort="1">
				<display:column property="etappenummer" class="hidden" headerClass="hidden" title="Etappe Nummer" sortable="true"/>
				<display:column property="omschrijving" title="Etappe Omschrijving" class="etappeomschrijving"/>
	  			<display:column property="datum" title="Datum" sortable="true"/>
	  			<display:column property="startplaats.stad" title="Startplaats" sortable="true"/>
	  			<display:column property="finishplaats.stad" title="Finishplaats" sortable="true"/>
	  			<display:column href="listEtappeUitslag.htm" paramId="etappe" paramProperty="etappenummer" value="Etappe Uitslag" title="Etappe Uitslag"/>
	  			<display:column href="listGewonnenBedragen.htm" paramId="etappe" paramProperty="etappenummer" value="Stand na etappe" title="Stand na etappe"/>
	  		</display:table>
	  		<br/>
	  		<br/>
	  		<h2>EindUitslag</h2>
	  		<display:table id="eindUitslagTable" name="model.einduitslag" class="listTable eindUitslagTable" requestURI="listEtappes.htm" sort="list" defaultsort="1">
				<display:column property="etappenummer" class="hidden" headerClass="hidden" title="Etappe Nummer" sortable="true"/>
				<display:column property="omschrijving" title="Etappe Omschrijving" class="etappeomschrijving"/>
	  			<display:column href="listEtappeUitslag.htm" paramId="etappe" paramProperty="etappenummer" value="Etappe Uitslag" title="Etappe Uitslag"/>
	  			<display:column href="listGewonnenBedragen.htm" paramId="etappe" paramProperty="etappenummer" value="Stand na etappe" title="Stand na etappe"/>
	  		</display:table>
	  		
	  	</div>
	</body>
</html>