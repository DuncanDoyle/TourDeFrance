<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="includeTags.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="nl.doyle.mccloud.tourdefrance.web.spring.model.ListUitslagModel"%>
<%@page import="java.util.Map" %>
<%@page import="nl.doyle.mccloud.tourdefrance.web.spring.model.ListUitslagModel.EtappeType"%>
<%Map model = (Map) pageContext.getAttribute("model", PageContext.REQUEST_SCOPE); 
  EtappeType pageEtappeType = ((ListUitslagModel) model.get("uitslagmodel")).getTypeEtappe();		
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<%@ include file="includeStyle.jsp" %>
		<script src="<%=request.getContextPath()%>/js/RowHandlers.js" language="javascript" type="text/javascript" /></script>
		<title>${model.uitslagmodel.typeEtappe} Uitslag</title>
	</head>
	<body class="listEtappeUitslag">
		<c:import url="headerTour.jsp"/>
		<c:import url="banner.jsp"/>
		<c:import url="leftColumn.jsp"/>
		<div id="bodyColumn">
			<h2>${model.uitslagmodel.typeEtappe}</h2>
			Etappe: <c:out value="${model.uitslagmodel.etappe.omschrijving}"/>
			<br/>
			<%
			if (pageEtappeType != EtappeType.EindUitslag) {
			%>
				Datum: <c:out value="${model.uitslagmodel.etappe.datum}"/>
				<br/>
				Startplaats: <c:out value="${model.uitslagmodel.etappe.startplaats.stad}"/>
				<br/>
				Finishplaats: <c:out value="${model.uitslagmodel.etappe.finishplaats.stad}"/>
				<br/>
			<%}%>
			<%	
	   		if (pageEtappeType == EtappeType.Etappe) {		
	   		%>
		   		<h3>Etappe Uitslag</h3>
				<display:table id="etappeUitslagTable" name="model.uitslagmodel.etappe.etappeUitslag" class="listTable etappeUitslagTable" requestURI="listEtappeUitslag.htm" sort="list" defaultsort="1">
					<display:column property="positie" title="Positie" sortable="true"/>
					<display:column property="renner.nummer" title="Rennernummer" sortable="false"/>
		  			<display:column property="renner.voornaam" title="Renner Voornaam" sortable="false"/>
		  			<display:column property="renner.achternaam" title="Renner Achternaam" sortable="false"/>
		  			<display:column property="deelnemer.nummer" title="Deelnemernummer" sortable="false"/>
		  			<display:column property="deelnemer.voornaam" title="Deelnemer Voornaam" sortable="false"/>
		  			<display:column property="deelnemer.achternaam" title="Deelnemer Achternaam" sortable="false"/>
				</display:table>
			<%}%>
			<h3>GeleTruiUitslag</h3>
			<display:table id="geleTruiUitslagTable" name="model.uitslagmodel.etappe.geleTruiUitslag" class="listTable geleTruiUitslagTable" requestURI="listEtappeUitslag.htm" sort="list" defaultsort="1">
				<display:column property="positie" title="Positie" sortable="true"/>
				<display:column property="renner.nummer" title="Rennernummer" sortable="false"/>
	  			<display:column property="renner.voornaam" title="Renner Voornaam" sortable="false"/>
	  			<display:column property="renner.achternaam" title="Renner Achternaam" sortable="false"/>
	  			<display:column property="deelnemer.nummer" title="Deelnemernummer" sortable="false"/>
		  		<display:column property="deelnemer.voornaam" title="Deelnemer Voornaam" sortable="false"/>
		  		<display:column property="deelnemer.achternaam" title="Deelnemer Achternaam" sortable="false"/>
			</display:table>
			<h3>GroeneTruiUitslag</h3>
			<display:table id="groeneTruiUitslagTable" name="model.uitslagmodel.etappe.groeneTruiUitslag" class="listTable groeneTruiUitslagTable" requestURI="listEtappeUitslag.htm" sort="list" defaultsort="1">
				<display:column property="positie" title="Positie" sortable="true"/>
				<display:column property="renner.nummer" title="Rennernummer" sortable="false"/>
	  			<display:column property="renner.voornaam" title="Renner Voornaam" sortable="false"/>
	  			<display:column property="renner.achternaam" title="Renner Achternaam" sortable="false"/>
	  			<display:column property="deelnemer.nummer" title="Deelnemernummer" sortable="false"/>
		  		<display:column property="deelnemer.voornaam" title="Deelnemer Voornaam" sortable="false"/>
		  		<display:column property="deelnemer.achternaam" title="Deelnemer Achternaam" sortable="false"/>
			</display:table>
			<h3>BolletjesTruiUitslag</h3>
			<display:table id="bolletjesTruiUitslagTable" name="model.uitslagmodel.etappe.bolletjesTruiUitslag" class="listTable bolletjesTruiUitslagTable" requestURI="listEtappeUitslag.htm" sort="list" defaultsort="1">
				<display:column property="positie" title="Positie" sortable="true"/>
				<display:column property="renner.nummer" title="Rennernummer" sortable="false"/>
	  			<display:column property="renner.voornaam" title="Renner Voornaam" sortable="false"/>
	  			<display:column property="renner.achternaam" title="Renner Achternaam" sortable="false"/>
	  			<display:column property="deelnemer.nummer" title="Deelnemernummer" sortable="false"/>
		  		<display:column property="deelnemer.voornaam" title="Deelnemer Voornaam" sortable="false"/>
		  		<display:column property="deelnemer.achternaam" title="Deelnemer Achternaam" sortable="false"/>
			</display:table>
			<br/>
			<%
			if (pageEtappeType == EtappeType.EindUitslag) {
			%>
				<h3>WitteTruiUitslag</h3>
				<display:table id="witteTruiUitslagTable" name="model.uitslagmodel.etappe.witteTrui" class="listTable witteTruiUitslagTable" requestURI="listEtappeUitslag.htm" sort="list" defaultsort="1">
					<display:column property="renner.nummer" title="Rennernummer" sortable="false"/>
			 		<display:column property="renner.voornaam" title="Renner Voornaam" sortable="false"/>
			 		<display:column property="renner.achternaam" title="Renner Achternaam" sortable="false"/>
			 		<display:column property="deelnemer.nummer" title="Deelnemernummer" sortable="false"/>
		  			<display:column property="deelnemer.voornaam" title="Deelnemer Voornaam" sortable="false"/>
		  			<display:column property="deelnemer.achternaam" title="Deelnemer Achternaam" sortable="false"/>
				</display:table>
				<h3>RodeLantaren</h3>
				<display:table id="rodeLantarenUitslagTable" name="model.uitslagmodel.etappe.rodeLantaren" class="listTable rodeLantarenUitslagTable" requestURI="listEtappeUitslag.htm" sort="list" defaultsort="1">
					<display:column property="renner.nummer" title="Rennernummer" sortable="false"/>
			 		<display:column property="renner.voornaam" title="Renner Voornaam" sortable="false"/>
			 		<display:column property="renner.achternaam" title="Renner Achternaam" sortable="false"/>
			 		<display:column property="deelnemer.nummer" title="Deelnemernummer" sortable="false"/>
		  			<display:column property="deelnemer.voornaam" title="Deelnemer Voornaam" sortable="false"/>
		  			<display:column property="deelnemer.achternaam" title="Deelnemer Achternaam" sortable="false"/>
				</display:table>
				<h3>EersteUitvaller</h3>
				<display:table id="eersteUitvallerUitslagTable" name="model.uitslagmodel.etappe.eersteUitvaller" class="listTable eersteUitvallerUitslagTable" requestURI="listEtappeUitslag.htm" sort="list" defaultsort="1">
					<display:column property="renner.nummer" title="Rennernummer" sortable="false"/>
			 		<display:column property="renner.voornaam" title="Renner Voornaam" sortable="false"/>
			 		<display:column property="renner.achternaam" title="Renner Achternaam" sortable="false"/>
			 		<display:column property="deelnemer.nummer" title="Deelnemernummer" sortable="false"/>
		  			<display:column property="deelnemer.voornaam" title="Deelnemer Voornaam" sortable="false"/>
		  			<display:column property="deelnemer.achternaam" title="Deelnemer Achternaam" sortable="false"/>
				</display:table>
			<%}%>
			<br/>
			<br/>
			<authz:authorize ifAllGranted="ROLE_ADMIN">
				<a href="editEtappeUitslag.htm?etappe=${model.uitslagmodel.etappe.etappenummer}"/><strong>Edit Etappe Uitslag</strong></a>
			</authz:authorize>
		</div>
	</body>
</html>