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
	
	<body class="listDeelnemersAndBedragenBody" onload="addRowHandlers('deelnemersAndBedragenTable', 'rowMouseOver', 'editRenner.htm', 'renner', 0)">
		<h2>Gewonnen Bedragen</h2>
		<display:table id="deelnemersAndBedragen" name="model.deelnemersAndBedragen" class="listTable deelnemersAndBedragenTable" requestURI="listRennersAndDeelnemers.htm" sort="list" defaultsort="1">
			<display:column property="deelnemer.nummer" class="nummerColumn" title="Renner Nummer" sortable="true"/>
  			<display:column property="deelnemer.voornaam" title="Renner Voornaam" sortable="true"/>
  			<display:column property="deelnemer.achternaam" title="Renner Achternaam" sortable="true"/>
  			<display:column property="gewonnenBedrag" title="Bedrag" sortable="true"/>
		</display:table>
	</body>
</html>