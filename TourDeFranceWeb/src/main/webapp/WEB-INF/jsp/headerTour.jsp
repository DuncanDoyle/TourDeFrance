<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="includeTags.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="org.acegisecurity.context.SecurityContextHolder"%>
<%@page import="org.acegisecurity.context.SecurityContext"%>
<%@page import="org.acegisecurity.Authentication"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<div id="breadcrumbs">
		<div class="xleft">
			<strong>Logged in as: <authz:authentication operation="username"></authz:authentication> </strong>
		</div>
      	<div class="xright">
      	<% 
    	  	SecurityContext context = SecurityContextHolder.getContext();
			boolean isAuthenticated = false;
          	if (context != null) {
          		Authentication authentication = context.getAuthentication();
          		if (authentication != null)
            	  isAuthenticated = authentication.isAuthenticated(); 
          	}
          	if (isAuthenticated) {
        %>
 			<a href="j_acegi_logout"><strong>Logout</strong></a>
 		<% } else { %>
			<a href="login.htm"><strong>Login</strong></a>
		<%}%>
        </div>
      	<div class="clear">
        	<hr/>
      	</div>
    </div>
</body>
</html>