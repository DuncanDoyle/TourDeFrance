<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<%@ include file="includeStyle.jsp" %>
		<script src="http://maps.google.com/maps?file=api&v=2&key=ABQIAAAAz_QdJW5g0CuP3XmHFGX5QBR9V-ksuyAS0m6XdK7SynyOWYJ5CxQBMNvq7kxBd-h5mDVjrg_doXWzug" type="text/javascript"></script>
		<title>Etappe Details</title>
	</head>
	<body onload="showMap();" onunload="GUnload()">
		<div id="map" style="width: 1000px; height: 600px;margin: 50px auto"></div>
		<script type="text/javascript">
    		//<![CDATA[
			function showMap() {
				if (GBrowserIsCompatible()) {
					var map = new GMap2(document.getElementById("map"));
					map.addControl(new GLargeMapControl());
        			map.addControl(new GMapTypeControl());
        			map.setCenter(new GLatLng(51.924266,4.479895), 11, G_HYBRID_MAP);
        			var point = new GLatLng(51.924266,4.479895); 
        			map.addOverlay(createMarker(point, "<b>This is the location where the<br/>TourDeFrance application<br/>is being developed</b>"));
        			var point = new GLatLng(51.920241,4.493076);
        			map.addOverlay(createMarker(point, "<b>Home of the<br/>Tour Game<br/>organisation</b>")); 
        		}
			}
			// Creates a marker at the given point with the given number label
			function createMarker(point, message) {
  				var marker = new GMarker(point);
  				GEvent.addListener(marker, "click", function() {
    				marker.openInfoWindowHtml(message);
  				});
  				return marker;
			}
	
 	 	  //]]>
    	</script>
    	<%-- Show Map --%>
		<%-- <a href="#" onclick="moveMapTo('+index+')">'+element.name+'</a><br />'; --%>
		
  </body>
</html>