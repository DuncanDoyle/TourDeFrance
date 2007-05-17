<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isErrorPage="true" import="java.lang.String" 
    import="nl.doyle.mccloud.tourdefrance.web.spring.controller.EditRennerFormRequestException"
    isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error</title>
</head>
<body>
<h1 style="color: red">Error</h1>
  
  <!-- <%= exception.getMessage() %>  -->
  <br><br>
  <c:out value="${requestScope['javax.servlet.error.message']}"/>
</body>
</html>
