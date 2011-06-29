<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="includeTags.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Left Column</title>
</head>
<body>
<div id="leftColumn">
		<div id="navcolumn">
			<ul>
			<li class="expanded"/>
              <a href="index.htm"><strong>Index</strong></a>
                <ul>
                  	<li class="none">
				    	<a href="listRenners.htm">Renners</a>
				    </li>
				    <li class="none">
				    	<a href="listRennersAndDeelnemers.htm">Ploegen</a>
				    </li>
					<li class="none">
				        <a href="listDeelnemers.htm">Deelnemers</a>
				    </li>
					<li class="none">
              			<a href="listEtappes.htm">Etappes</a>
	        		</li>
	        		<li class="none">
              			<a href="http://tm2011.forumcircle.com">Forum</a>
	        		</li>
	        	</ul>
        	</li>
        	<authz:authorize ifAllGranted="ROLE_ADMIN">
	        	<li class="expanded">
	              	<a href="adminPage.htm"><strong>Admin</strong></a>
	              	<ul>
	              		<li class="none">
	              			<a href="editUitslagBedrag.htm">Edit Bedragen</a>
		        		</li>
		        		<li class="none">
	              			<a href="listSteden.htm">Steden Lijst</a>
		        		</li>
		        	</ul>
		        </li>
		      </authz:authorize>
        	</ul>
         </div>
	</div>
</body>
</html>