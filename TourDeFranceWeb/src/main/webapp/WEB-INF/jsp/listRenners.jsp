<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script LANGUAGE="JavaScript" SRC="<%=request.getContextPath()%>/js/stripeTable.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/stripeTable.css"/>
<title>List Renners</title>
</head>
<body>
<h2>Renners</h2>
<table summary="Submitted table designs" class="stripetables" style="width:650px;">
  <thead>
    <tr>
      <th scope="col"> Renner nummer</th>
      <th scope="col"> Voornaam </th>
      <th scope="col"> Achternaam </th>
    </tr>
  </thead>
  <tbody>
    <!-- <c:out value="${renner.nummer}"/> <c:out value="${renner.voornaam}"/> <c:out value="${renner.achternaam}"/><br><br>  -->
	<c:forEach items="${model.renners}" var="renner">
		<tr>
      		<td ><a href="editRenner.htm?renner=<c:out value="${renner.nummer}"/>" title="Edit Renner"><c:out value="${renner.nummer}"/></a></td>
      		<td><c:out value="${renner.voornaam}"/></td>
			<td><c:out value="${renner.achternaam}"/></td>
		</tr>
	</c:forEach>
</table>

</body>
</html>