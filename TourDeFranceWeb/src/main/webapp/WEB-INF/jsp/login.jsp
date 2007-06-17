<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="includeTags.jsp" %>

<%@ page import="org.acegisecurity.ui.AbstractProcessingFilter"%>
<%@ page import="org.acegisecurity.ui.webapp.AuthenticationProcessingFilter"%>
<%@ page import="org.acegisecurity.AuthenticationException"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Login</title>
		<%@ include file="includeStyle.jsp" %>
	</head>
	<body>
		<c:import url="headerTour.jsp"/>
		<c:import url="banner.jsp"/>
		<c:import url="leftColumn.jsp"/>
		<div id="bodyColumn">
			<h2>Login</h2>
			<c:if test="${not empty param.login_error}">
	        	<font color="red">
	        		Your login attempt was not successful, try again.
	        		<br/>
	        		<br/>
	        	</font>
	    	</c:if>
	    	<form action="<c:url value='j_acegi_security_check'/>" method="POST">
				<h3>Enter your details below to login to the site</h3>
				<br/>
				<table class="loginTable">
					    <tr>
					        <td width="20%"><label class="label"><u>U</u>sername:</label></td>
					        <td><input size="30%" type="tex"' name="j_username" accessKey="U"></td>
					    </tr>
					    <tr>
					        <td width="20%"><label class="label"><u>P</u>assword:</label></td>
					        <td>
					        	<input size="30%" type="password" name="j_password" accessKey="P">
					        </td>
					    </tr>
	            		<tr> 
	            			<td/>
	            			<td>
	                			<input id="loginButton" type="submit" value="Log In" />
	                		</td>
	                	</tr>
			        </table>
	    	</form>
	    </div>
	</body>
</html>
