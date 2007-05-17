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
	</head>
	<body>
		<c:if test="${not empty param.login_error}">
        	<font color="red">
        		Your login attempt was not successful, try again.
        		<BR>
        		<BR>
        	</font>
    	</c:if>
    	<form action="<c:url value='j_acegi_security_check'/>" method="POST">
			<center>
				<table align="center" cellpadding="4" cellspacing="0" border="0"
				            class="loginform">
				    <tr>
				        <td bgcolor="f0f0f0" colspan="2">Enter your details below to
				            login to admin site:</td>
				    </tr>
				    <tr />
				    <tr>
				        <td nowrap align="right" valign="top"><label class="label"><u>U</u>sername:</label></td>
				        <td><input type='text' name='j_username' accessKey="U"></td>
				    </tr>
				    <tr>
				        <td nowrap align="right" valign="top">
				        	<label class="label"><u>P</u>assword:</label>
				        </td>
				        <td>
				        	<input type='password' name='j_password' accessKey="P">
				        </td>
				    </tr>
            		<tr>
                		<td valign="middle" align="center" colspan="2">
			                <input id="loginButton" type="submit" value="Log In" />
						</td>
        		    </tr>
		        </table>
    		</center>
    	</form>
	</body>
</html>
