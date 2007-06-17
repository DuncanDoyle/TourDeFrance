<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="includeTags.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<jsp:include flush="true" page="includeStyle.jsp"></jsp:include>
		<title>Edit Deelnemer</title>
	</head>
	<body>
		<c:import url="headerTour.jsp"/>
		<c:import url="banner.jsp"/>
		<c:import url="leftColumn.jsp"/>
		<div id="bodyColumn">
			<h2>Edit Deelnemer: <c:out value="${deelnemerCommand.nummer}"/></h2>
				<form method="POST">
					<table class="editTable">
				    	<tr>
		      				<td alignment="right" width="10%">Voornaam:</td>
		      				<spring:bind path="deelnemerCommand.voornaam">
		        				<td width="20%">
		          					<input size="30%" type="text" name="voornaam" value="<c:out value="${status.value}"/>"/>
		        				</td>
		        				<td width="60%">
		          					<font color="red"><c:out value="${status.errorMessage}"/></font>
		        				</td>
		        			</spring:bind>
		        		</tr>
		        		<tr>
		      				<td alignment="right" width="10%">Achternaam:</td>
		      				<spring:bind path="deelnemerCommand.achternaam">
		        				<td width="20%">
		          					<input size="30%" type="text" name="achternaam" value="<c:out value="${status.value}"/>"/>
		        				</td>
		        				<td width="60%">
		          					<font color="red"><c:out value="${status.errorMessage}"/></font>
		        				</td>
		        			</spring:bind>
		        		</tr>
		        		<tr>
		        			<td alignment="right" width="10%">E-Mail:</td>
		      				<spring:bind path="deelnemerCommand.email">
		        				<td width="20%">
		          					<input size="30%" type="text" name="email" value="<c:out value="${status.value}"/>"/>
		        				</td>
		        				<td width="60%">
		          					<font color="red"><c:out value="${status.errorMessage}"/></font>
		        				</td>
		      				</spring:bind>
		      			</tr>
		      			<tr>
		        			<td alignment="right" width="10%">Rekeningnummer:</td>
		      				<spring:bind path="deelnemerCommand.rekeningnummer">
		        				<td width="20%">
		          					<input size="30%" type="text" name="rekeningnummer" value="<c:out value="${status.value}"/>"/>
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
		  		<input type="button" name="back" value="Back" class="input" onClick="javascript:window.location='listDeelnemers.htm';"/>
				</form>
			</div>
	</body>
</html>