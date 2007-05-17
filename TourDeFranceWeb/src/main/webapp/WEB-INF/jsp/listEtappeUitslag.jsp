<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="includeTags.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<%@ include file="includeStyle.jsp" %>
		<script src="<%=request.getContextPath()%>/js/RowHandlers.js" language="javascript" type="text/javascript" /></script>
		<title>Etappe Uitslag</title>
	</head>
	<body class="listEtappeUitslag">
		<h2>EtappeUitslag</h2>
		Etappe Nummer: <c:out value="${model.etappe.etappenummer}"/>
		<br/>
		Datum: <c:out value="${model.etappe.datum}"/>
		<br/>
		Startplaats: <c:out value="${model.etappe.startplaats}"/>
		<br/>
		Finishplaats: <c:out value="${model.etappe.finishplaats}"/>
		<br/>
		<h3>Etappe Uitslag</h3>
		<display:table id="etappeUitslagTable" name="model.etappe.etappeUitslag" class="listTable etappeUitslagTable" requestURI="listEtappeUitslag.htm" sort="list" defaultsort="1">
			<display:column property="positie" title="Positie" sortable="true"/>
			<display:column property="renner.nummer" title="Rennernummer" sortable="false"/>
  			<display:column property="renner.voornaam" title="Renner Voornaam" sortable="false"/>
  			<display:column property="renner.achternaam" title="Renner Achternaam" sortable="false"/>
		</display:table>
		<h3>GeleTruiUitslag</h3>
		<display:table id="geleTruiUitslagTable" name="model.etappe.geleTruiUitslag" class="listTable geleTruiUitslagTable" requestURI="listEtappeUitslag.htm" sort="list" defaultsort="1">
			<display:column property="positie" title="Positie" sortable="true"/>
			<display:column property="renner.nummer" title="Rennernummer" sortable="false"/>
  			<display:column property="renner.voornaam" title="Renner Voornaam" sortable="false"/>
  			<display:column property="renner.achternaam" title="Renner Achternaam" sortable="false"/>
		</display:table>
		<h3>GroeneTruiUitslag</h3>
		<display:table id="groeneTruiUitslagTable" name="model.etappe.groeneTruiUitslag" class="listTable groeneTruiUitslagTable" requestURI="listEtappeUitslag.htm" sort="list" defaultsort="1">
			<display:column property="positie" title="Positie" sortable="true"/>
			<display:column property="renner.nummer" title="Rennernummer" sortable="false"/>
  			<display:column property="renner.voornaam" title="Renner Voornaam" sortable="false"/>
  			<display:column property="renner.achternaam" title="Renner Achternaam" sortable="false"/>
		</display:table>
		<h3>BolletjesTruiUitslag</h3>
		<display:table id="bolletjesTruiUitslagTable" name="model.etappe.bolletjesTruiUitslag" class="listTable bolletjesTruiUitslagTable" requestURI="listEtappeUitslag.htm" sort="list" defaultsort="1">
			<display:column property="positie" title="Positie" sortable="true"/>
			<display:column property="renner.nummer" title="Rennernummer" sortable="false"/>
  			<display:column property="renner.voornaam" title="Renner Voornaam" sortable="false"/>
  			<display:column property="renner.achternaam" title="Renner Achternaam" sortable="false"/>
		</display:table>
		<br/>
		
		<a href
	</body>
</html>