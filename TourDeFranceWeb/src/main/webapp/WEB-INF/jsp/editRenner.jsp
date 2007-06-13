<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="includeTags.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<jsp:include flush="true" page="includeStyle.jsp"></jsp:include>
		<title>Edit Renner</title>
	</head>
<body>
<h1>Edit Renner</h1>
<form method="POST">
	<table width="95%" class="editTable" border="0" cellspacing="0" cellpadding="5">
    	<tr>
      		<td alignment="right" width="10%">Rennernummer:</td>
      		<spring:bind path="rennerCommand.nummer">
        		<td width="20%">
          			<input type="text" name="nummer" value="<c:out value="${status.value}"/>">
        		</td>
        		<td width="60%">
          			<font color="red"><c:out value="${status.errorMessage}"/></font>
        		</td>
        	</spring:bind>
        </tr>
        <tr>
        	<td alignment="right" width="10%">Voornaam:</td>
      		<spring:bind path="rennerCommand.voornaam">
        		<td width="20%">
          			<input type="text" name="voornaam" value="<c:out value="${status.value}"/>">
        		</td>
        		<td width="60%">
          			<font color="red"><c:out value="${status.errorMessage}"/></font>
        		</td>
      		</spring:bind>
      	</tr>
      	<tr>
      		<td alignment="right" width="10%">Achternaam:</td>
      		<spring:bind path="rennerCommand.achternaam">
        		<td width="20%">
          			<input type="text" name="achternaam" value="<c:out value="${status.value}"/>">
        		</td>
        		<td width="60%">
          			<font color="red"><c:out value="${status.errorMessage}"/></font>
        		</td>
      		</spring:bind>
      	</tr>
  </table>
  <br>
  <spring:hasBindErrors name="rennerCommand">
    <b>Please fix all errors!</b>
  </spring:hasBindErrors>
  <br><br>
  <input type="submit" alignment="center" value="Save"/>
  <input type="button" name="back" value="Back" class="input" onClick="javascript:window.location='listRenners.htm';"/>
</form>
</body>
</html>