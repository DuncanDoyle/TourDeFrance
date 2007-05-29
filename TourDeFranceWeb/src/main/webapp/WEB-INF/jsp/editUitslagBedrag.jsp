<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="includeTags.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<jsp:include flush="true" page="includeStyle.jsp"></jsp:include>
		<title>Edit Uitslag Bedragen</title>
	</head>
	<body>
		
		<br/>
		<form method="POST">
			<h3>Etappe Uitslag</h3>
			<table width="95%" bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
				<c:forEach var="counter" begin="0" end="${fn:length(uitslagBedragCommand.etappe) - 1}">
					<tr>
	      				<td alignment="right" width="10%">${counter+1}e plaats:</td>
	      					<spring:bind path="uitslagBedragCommand.etappe[${counter}]">
	        				<td width="20%">
	          					<input type="text" name="etappe[${counter}]" value="<c:out value="${status.value}"/>"/>
	          				</td>
	          				<td width="60%">
	          					<font color="red"><c:out value="${status.errorMessage}"/></font>
	        				</td>
	        			</spring:bind>
	      			</tr>
      			</c:forEach>
      		</table>
	      	
			<h3>Geletrui Uitslag</h3>
			<table width="95%" bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
				<c:forEach var="counter" begin="0" end="${fn:length(uitslagBedragCommand.geleTrui) - 1}">
					<tr>
	      				<td alignment="right" width="10%">${counter+1}e plaats:</td>
	      					<spring:bind path="uitslagBedragCommand.geleTrui[${counter}]">
	        				<td width="20%">
	          					<input type="text" name="geleTrui[${counter}]" value="<c:out value="${status.value}"/>"/>
	          				</td>
	          				<td width="60%">
	          					<font color="red"><c:out value="${status.errorMessage}"/></font>
	        				</td>
	        			</spring:bind>
	      			</tr>
	      		</c:forEach>
	      	</table>
			<h3>Groenetrui Uitslag</h3>
			<table width="95%" bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
				<c:forEach var="counter" begin="0" end="${fn:length(uitslagBedragCommand.groeneTrui) - 1}">
					<tr>
	      				<td alignment="right" width="10%">${counter+1}e plaats:</td>
	      					<spring:bind path="uitslagBedragCommand.groeneTrui[${counter}]">
	        				<td width="20%">
	          					<input type="text" name="groeneTrui[${counter}]" value="<c:out value="${status.value}"/>"/>
	          				</td>
	          				<td width="60%">
	          					<font color="red"><c:out value="${status.errorMessage}"/></font>
	        				</td>
	        			</spring:bind>
	      			</tr>
	      		</c:forEach>
	      	</table>
			<h3>Bolletjestrui Uitslag</h3>
			<table width="95%" bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
				<c:forEach var="counter" begin="0" end="${fn:length(uitslagBedragCommand.bolletjesTrui) - 1}">
					<tr>
	      				<td alignment="right" width="10%">${counter+1}e plaats:</td>
	      					<spring:bind path="uitslagBedragCommand.bolletjesTrui[${counter}]">
	        				<td width="20%">
	          					<input type="text" name="bolletjesTrui[${counter}]" value="<c:out value="${status.value}"/>"/>
	          				</td>
	          				<td width="60%">
	          					<font color="red"><c:out value="${status.errorMessage}"/></font>
	        				</td>
	        			</spring:bind>
	      			</tr>
	      		</c:forEach>
	      	</table>
	      	<h3>Geletrui Einduitslag</h3>
			<table width="95%" bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
				<c:forEach var="counter" begin="0" end="${fn:length(uitslagBedragCommand.geleTruiEind) - 1}">
					<tr>
	      				<td alignment="right" width="10%">${counter+1}e plaats:</td>
	      					<spring:bind path="uitslagBedragCommand.geleTruiEind[${counter}]">
	        				<td width="20%">
	          					<input type="text" name="geleTruiEind[${counter}]" value="<c:out value="${status.value}"/>"/>
	          				</td>
	          				<td width="60%">
	          					<font color="red"><c:out value="${status.errorMessage}"/></font>
	        				</td>
	        			</spring:bind>
	      			</tr>
	      		</c:forEach>
	      	</table>
	      	<h3>Groenetrui Einduitslag</h3>
			<table width="95%" bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
				<c:forEach var="counter" begin="0" end="${fn:length(uitslagBedragCommand.groeneTruiEind) - 1}">
					<tr>
	      				<td alignment="right" width="10%">${counter+1}e plaats:</td>
	      					<spring:bind path="uitslagBedragCommand.groeneTruiEind[${counter}]">
	        				<td width="20%">
	          					<input type="text" name="groeneTruiEind[${counter}]" value="<c:out value="${status.value}"/>"/>
	          				</td>
	          				<td width="60%">
	          					<font color="red"><c:out value="${status.errorMessage}"/></font>
	        				</td>
	        			</spring:bind>
	      			</tr>
	      		</c:forEach>
	      	</table>
	      	<h3>Bolletjestrui Einduitslag</h3>
			<table width="95%" bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
				<c:forEach var="counter" begin="0" end="${fn:length(uitslagBedragCommand.bolletjesTruiEind) - 1}">
					<tr>
	      				<td alignment="right" width="10%">${counter+1}e plaats:</td>
	      					<spring:bind path="uitslagBedragCommand.bolletjesTruiEind[${counter}]">
	        				<td width="20%">
	          					<input type="text" name="bolletjesTruiEind[${counter}]" value="<c:out value="${status.value}"/>"/>
	          				</td>
	          				<td width="60%">
	          					<font color="red"><c:out value="${status.errorMessage}"/></font>
	        				</td>
	        			</spring:bind>
	      			</tr>
	      		</c:forEach>
	      	</table>
	
	  		<br>
	  		<spring:hasBindErrors name="etappeUitslagCommand">
	    		<b>Please fix all errors!</b>
	  		</spring:hasBindErrors>
	  		<br><br>
	  		
	  		<input type="submit" alignment="center" value="Save"/>
	  		<input type="button" name="back" value="Back" class="input" onClick="javascript:window.location='listEtappes.htm';"/>
		</form>
	</body>
</html>