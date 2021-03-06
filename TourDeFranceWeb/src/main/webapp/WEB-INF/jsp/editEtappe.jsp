<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="includeTags.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<jsp:include flush="true" page="includeStyle.jsp"></jsp:include>
		<title>Edit Etappe</title>
	</head>
	<body>
		<c:import url="headerTour.jsp"/>
		<c:import url="banner.jsp"/>
		<c:import url="leftColumn.jsp"/>
		<div id="bodyColumn">
			<h2>Edit Etappe</h2>
			<h4>Etappenummer: <c:out value="${etappeCommand.etappenummer}"/></h4>
			<br/>
			<form method="POST">
				<table width="95%" class="editTable" border="0" cellspacing="0" cellpadding="5">
			    	<tr>
	      				<td alignment="right" width="10%">Omschrijving:</td>
	      				<spring:bind path="etappeCommand.omschrijving">
	        				<td width="20%">
	          					<input type="text" name="omschrijving" value="<c:out value="${status.value}"/>"/>
	        				</td>
	        				<td width="60%">
	          					<font color="red"><c:out value="${status.errorMessage}"/></font>
	        				</td>
	        			</spring:bind>
	        		</tr>
	        		<tr>
	        			<td alignment="right" width="10%">Datum:</td>
	      				<spring:bind path="etappeCommand.datum">
	        				<td width="20%">
	          					<input type="text" name="datum" value="<c:out value="${status.value}"/>"/>
	        				</td>
	        				<td width="60%">
	          					<font color="red"><c:out value="${status.errorMessage}"/></font>
	        				</td>
	      				</spring:bind>
	      			</tr>
	      			<tr>
	      				<td alignment="right" width="10%">Startplaats:</td>
	      				<spring:bind path="etappeCommand.startPlaatsIndex">
	        				<td width="20%">
	          					<select name="${status.expression}">
	          						<option value="0">Stad nog niet ingesteld</option>
	          						<c:forEach var="stad" items="${etappeCommand.steden}">
	      								<option <c:if test="${stad.id == status.value}"> selected </c:if> value="<c:out value="${stad.id}"/>">
	         								<c:out value="${stad.stad}"/>
	              						</option>
	              							
	    							</c:forEach>
	          					
	          					</select>	
	          				</td>
	          				<td>
	          					<a href="nieuweStad.htm">Nieuwe Stad</a>
	          				</td>
	        				<td width="60%">
	          					<font color="red"><c:out value="${status.errorMessage}"/></font>
	        				</td>
	        			</spring:bind>
	      			</tr>
	      			<tr>
	      				<td alignment="right" width="10%">Finishplaats:</td>
	      				<spring:bind path="etappeCommand.finishPlaatsIndex">
	        				<td width="20%">
	          					<select name="${status.expression}">
	          						<option value="0">Stad nog niet ingesteld</option>
	          						<c:forEach var="stad" items="${etappeCommand.steden}">
	      								<option <c:if test="${stad.id == status.value}"> selected </c:if> value="<c:out value="${stad.id}"/>">
	         								<c:out value="${stad.stad}"/>
	              						</option>
	              					</c:forEach>
	          					</select>	
	          				</td>
	          				<td>
	          					<a href="nieuweStad.htm">Nieuwe Stad</a>
	          				</td>
	        				<td width="60%">
	          					<font color="red"><c:out value="${status.errorMessage}"/></font>
	        				</td>
	        			</spring:bind>
	      			</tr>
	
	  		</table>
	  		<br>
	  		<spring:hasBindErrors name="etappeCommand">
	    		<b>Please fix all errors!</b>
	  		</spring:hasBindErrors>
	  		<br><br>
	  		<input type="submit" alignment="center" value="Save"/>
	  		<input type="button" name="back" value="Back" class="input" onClick="javascript:window.location='listEtappes.htm';"/>
			</form>
		</div>
	</body>
</html>