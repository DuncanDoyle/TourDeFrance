<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="includeTags.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<jsp:include flush="true" page="includeStyle.jsp"></jsp:include>
		<title>Insert title here</title>
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
			<h3>Etappe Uitslag</h3>
			<table width="95%" bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
				<tr>
      				<td alignment="right" width="10%">1e plaats:</td>
      				<spring:bind path="etappeUitslagCommand.uitslag.eerste">
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
      			<tr>
      				<td alignment="right" width="10%">2e plaats:</td>
      				<spring:bind path="etappeUitslagCommand.uitslag.tweede">
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
      			<tr>
      				<td alignment="right" width="10%">3e plaats:</td>
      				<spring:bind path="etappeUitslagCommand.uitslag.derde">
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
      			<tr>
      				<td alignment="right" width="10%">4e plaats:</td>
      				<spring:bind path="etappeUitslagCommand.uitslag.vierde">
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
      			<tr>
      				<td alignment="right" width="10%">5e plaats:</td>
      				<spring:bind path="etappeUitslagCommand.uitslag.vijfde">
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
      			<tr>
      				<td alignment="right" width="10%">6e plaats:</td>
      				<spring:bind path="etappeUitslagCommand.uitslag.zesde">
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
      			<tr>
      				<td alignment="right" width="10%">7e plaats:</td>
      				<spring:bind path="etappeUitslagCommand.uitslag.zevende">
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
      			<tr>
      				<td alignment="right" width="10%">8e plaats:</td>
      				<spring:bind path="etappeUitslagCommand.uitslag.achtste">
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
      			<tr>
      				<td alignment="right" width="10%">9e plaats:</td>
      				<spring:bind path="etappeUitslagCommand.uitslag.negende">
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
      			<tr>
      				<td alignment="right" width="10%">10e plaats:</td>
      				<spring:bind path="etappeUitslagCommand.uitslag.tiende">
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
      			<tr>
      				<td alignment="right" width="10%">11e plaats:</td>
      				<spring:bind path="etappeUitslagCommand.uitslag.elfde">
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
      			<tr>
      				<td alignment="right" width="10%">12e plaats:</td>
      				<spring:bind path="etappeUitslagCommand.uitslag.twaalfde">
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
      			<tr>
      				<td alignment="right" width="10%">13e plaats:</td>
      				<spring:bind path="etappeUitslagCommand.uitslag.dertiende">
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
      			<tr>
      				<td alignment="right" width="10%">14e plaats:</td>
      				<spring:bind path="etappeUitslagCommand.uitslag.veertiende">
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
      			<tr>
      				<td alignment="right" width="10%">15e plaats:</td>
      				<spring:bind path="etappeUitslagCommand.uitslag.vijftiende">
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
      			
      	</table>
		<h3>Geletrui Uitslag</h3>
		<table width="95%" bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
				<tr>
      				<td alignment="right" width="10%">1e plaats:</td>
      				<spring:bind path="etappeUitslagCommand.geleTruiUitslag.eerste">
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
      			<tr>
      				<td alignment="right" width="10%">2e plaats:</td>
      				<spring:bind path="etappeUitslagCommand.geleTruiUitslag.tweede">
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
      			<tr>
      				<td alignment="right" width="10%">3e plaats:</td>
      				<spring:bind path="etappeUitslagCommand.geleTruiUitslag.derde">
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
      			<tr>
      				<td alignment="right" width="10%">4e plaats:</td>
      				<spring:bind path="etappeUitslagCommand.geleTruiUitslag.vierde">
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
      			<tr>
      				<td alignment="right" width="10%">5e plaats:</td>
      				<spring:bind path="etappeUitslagCommand.geleTruiUitslag.vijfde">
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
		</table>
		<h3>Groenetrui Uitslag</h3>
		<table width="95%" bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
				<tr>
      				<td alignment="right" width="10%">1e plaats:</td>
      				<spring:bind path="etappeUitslagCommand.groeneTruiUitslag.eerste">
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
      			<tr>
      				<td alignment="right" width="10%">2e plaats:</td>
      				<spring:bind path="etappeUitslagCommand.groeneTruiUitslag.tweede">
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
      			<tr>
      				<td alignment="right" width="10%">3e plaats:</td>
      				<spring:bind path="etappeUitslagCommand.groeneTruiUitslag.derde">
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
      	</table>
		<h3>Bolletjestrui Uitslag</h3>
		<table width="95%" bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
				<tr>
      				<td alignment="right" width="10%">1e plaats:</td>
      				<spring:bind path="etappeUitslagCommand.bolletjesTruiUitslag.eerste">
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
      			<tr>
      				<td alignment="right" width="10%">2e plaats:</td>
      				<spring:bind path="etappeUitslagCommand.bolletjesTruiUitslag.tweede">
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
      			<tr>
      				<td alignment="right" width="10%">3e plaats:</td>
      				<spring:bind path="etappeUitslagCommand.bolletjesTruiUitslag.derde">
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