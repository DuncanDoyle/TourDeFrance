<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="nl.doyle.mccloud.tourdefrance.dao.DeelnemerDao" %>
<%@page import="java.util.*" %>
<%@page import="nl.doyle.mccloud.tourdefrance.valueobjects.Deelnemer"%>
<%@page import="testspringcontext.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
DeelnemerDao deelnemerDao = (DeelnemerDao) context.getBean("deelnemerDao");
List<Deelnemer> alleDeelnemers = deelnemerDao.loadAllDeelnemers();
Iterator<Deelnemer> iter = alleDeelnemers.iterator();
while(iter.hasNext()) {
	System.out.println(iter.next().toString());
}
%>
<%=((Test)context.getBean("test")).getMessage() %>
<h1>Performance Aspects Executed</h1>
</body>
</html>