<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="includeTags.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<jsp:include flush="true" page="includeStyle.jsp"></jsp:include>
		<title>Edit Etappe Uitslag</title>
	</head>
	<body>
		Etappenummer: <c:out value="${etappeUitslagCommand.etappenummer }"/>
		<br/>
		Datum: <c:out value="${etappeUitslagCommand.datum}"/>
		<br/>
		Start: <c:out value="${etappeUitslagCommand.startPlaats.stad}"/>
		<br/>
		Finish: <c:out value="${etappeUitslagCommand.finishPlaats.stad}"/>
		<br/>
		<form method="POST">
			<c:if test="${etappeUitslagCommand.standaardEtappe}">
				<h3>Etappe Uitslag</h3>
				<table width="95%" bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
					<c:forEach var="counter" begin="0" end="${fn:length(etappeUitslagCommand.uitslag) - 1}">
						<tr>
		      				<td alignment="right" width="10%">${counter+1}e plaats:</td>
		      					<spring:bind path="etappeUitslagCommand.uitslag[${counter}]">
		        				<td width="20%">
		          					<select name="${status.expression}">
		          						<option value="0">Uitslag nog niet ingesteld</option>
		          						<c:forEach var="renner" items="${etappeUitslagCommand.renners}">
		      								<option <c:if test="${renner.nummer == status.value}"> selected </c:if> value="<c:out value="${renner.nummer}"/>">
		         								<c:out value="${renner.nummer} - ${renner.voornaam} ${renner.achternaam}"/>
		              						</option>
		              					</c:forEach>
		          					</select>	
		          				</td>
		          				<td width="60%">
		          					<font color="red"><c:out value="${status.errorMessage}"/></font>
		        				</td>
		        			</spring:bind>
		      			</tr>
	      			</c:forEach>
	      		</table>
	      	</c:if>
			<h3>Geletrui Uitslag</h3>
			<table width="95%" bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
				<c:forEach var="counter" begin="0" end="${fn:length(etappeUitslagCommand.geleTruiUitslag) - 1}">
					<tr>
	      				<td alignment="right" width="10%">${counter+1}e plaats:</td>
	      					<spring:bind path="etappeUitslagCommand.geleTruiUitslag[${counter}]">
	        				<td width="20%">
	          					<select name="${status.expression}">
	          						<option value="0">Uitslag nog niet ingesteld</option>
	          						<c:forEach var="renner" items="${etappeUitslagCommand.renners}">
	      								<option <c:if test="${renner.nummer == status.value}"> selected </c:if> value="<c:out value="${renner.nummer}"/>">
	         								<c:out value="${renner.nummer} - ${renner.voornaam} ${renner.achternaam}"/>
	              						</option>
	              					</c:forEach>
	          					</select>	
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
				<c:forEach var="counter" begin="0" end="${fn:length(etappeUitslagCommand.groeneTruiUitslag) - 1}">
					<tr>
	      				<td alignment="right" width="10%">${counter+1}e plaats:</td>
	      					<spring:bind path="etappeUitslagCommand.groeneTruiUitslag[${counter}]">
	        				<td width="20%">
	          					<select name="${status.expression}">
	          						<option value="0">Uitslag nog niet ingesteld</option>
	          						<c:forEach var="renner" items="${etappeUitslagCommand.renners}">
	      								<option <c:if test="${renner.nummer == status.value}"> selected </c:if> value="<c:out value="${renner.nummer}"/>">
	         								<c:out value="${renner.nummer} - ${renner.voornaam} ${renner.achternaam}"/>
	              						</option>
	              					</c:forEach>
	          					</select>	
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
				<c:forEach var="counter" begin="0" end="${fn:length(etappeUitslagCommand.bolletjesTruiUitslag) - 1}">
					<tr>
	      				<td alignment="right" width="10%">${counter+1}e plaats:</td>
	      					<spring:bind path="etappeUitslagCommand.bolletjesTruiUitslag[${counter}]">
	        				<td width="20%">
	          					<select name="${status.expression}">
	          						<option value="0">Uitslag nog niet ingesteld</option>
	          						<c:forEach var="renner" items="${etappeUitslagCommand.renners}">
	      								<option <c:if test="${renner.nummer == status.value}"> selected </c:if> value="<c:out value="${renner.nummer}"/>">
	         								<c:out value="${renner.nummer} - ${renner.voornaam} ${renner.achternaam}"/>
	              						</option>
	              					</c:forEach>
	          					</select>	
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