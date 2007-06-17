<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="includeTags.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<%@ include file="includeStyle.jsp" %>
  		<script src="<%=request.getContextPath()%>/js/RowHandlers.js" language="javascript" type="text/javascript" /></script>
		<title>Gewonnen bedragen na etappe: <c:out value="${model.etappenummer}"/></title>
	</head>
	
	<body class="listDeelnemersAndBedragenBody" onload="addMouseOverHighlight('deelnemersAndBedragen', 'rowMouseOver')">
		<c:import url="headerTour.jsp"/>
		<c:import url="banner.jsp"/>
		<c:import url="leftColumn.jsp"/>
		<div id="bodyColumn">
			<h2>Gewonnen Bedragen</h2>
			<display:table id="deelnemersAndBedragen" name="model.deelnemersAndBedragen" class="listTable deelnemersAndBedragenTable" requestURI="listRennersAndDeelnemers.htm" sort="list" defaultsort="1">
				<display:column property="deelnemer.nummer" class="nummerColumn" title="Deelnemernummer" sortable="true"/>
	  			<display:column property="deelnemer.voornaam" title="Deelnemer Voornaam" sortable="true"/>
	  			<display:column property="deelnemer.achternaam" title="Deelnemer Achternaam" sortable="true"/>
	  			<display:column property="gewonnenBedrag" class="bedragColumn" title="Bedrag t/m etappe ${model.etappenummer}" sortable="true"/>
			</display:table>
		</div>
	</body>
</html>