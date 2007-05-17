<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="includeTags.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<jsp:include flush="true" page="includeStyle.jsp"></jsp:include>
		<title>Nieuwe Stad</title>
	</head>
	<body>
		<h1>Nieuwe Stad</h1>
		<form method="POST">
			<table width="95%" bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
				<tr>
      				<td alignment="right" width="10%">Stad:</td>
      				<spring:bind path="stadCommand.stad">
     					<td width="20%">
         					<input type="text" name="stad" value="<c:out value="${status.value}"/>"/>
       					</td>
       					<td width="60%">
         					<font color="red"><c:out value="${status.errorMessage}"/></font>
       					</td>
       				</spring:bind>
        		</tr>
        		<tr>
        			<td alignment="right" width="10%">Land:</td>
        			<spring:bind path="stadCommand.land">
	       				<td width="20%">
    	     				<input type="text" name="land" value="<c:out value="${status.value}"/>"/>
       					</td>
       					<td width="60%">
       	  					<font color="red"><c:out value="${status.errorMessage}"/></font>
       					</td>
       				</spring:bind>
      			</tr>
      			<tr>
      				<td alignment="right" width="10%">Longitude:</td>
      				<spring:bind path="stadCommand.longitude">
       					<td width="20%">
       						<input type="text" name="longitude" value="<c:out value="${status.value}"/>"/>
       					</td>
       					<td width="60%">
       						<font color="red"><c:out value="${status.errorMessage}"/></font>
       					</td>
       				</spring:bind>
      			</tr>
      			<tr>
      				<td alignment="right" width="10%">Latitude:</td>
      				<spring:bind path="stadCommand.latitude">
	       				<td width="20%">
    	   					<input type="text" name="latitude" value="<c:out value="${status.value}"/>"/>
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
  		<input type="button" name="back" value="Back" class="input" onClick="javascript:window.location='listSteden.htm';"/>
		</form>
	</body>		
</html>