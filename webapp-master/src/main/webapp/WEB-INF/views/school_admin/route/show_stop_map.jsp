<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
  <head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
    <title>Simple markers</title>
    <style>
      html, body, #map-canvas {
        height: 100%;
        margin: 0px;
        padding: 0px
      }
    </style>
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp"></script>
    <script>
    var geocoder = new google.maps.Geocoder();
				function initialize() {
					  var myLatlng = new google.maps.LatLng(<c:out value='${stop_lat}'/>,<c:out value='${stop_long}'/>);
					  var mapOptions = {
					    zoom: 14,
					    center: myLatlng
					  }
					  var map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
					
					  var marker = new google.maps.Marker({
					      position: myLatlng,
					      map: map,
					      
					      icon:"/sts/resources/icons/bus-1.png"
					  });
					
			  			
			  			var address=geocodePosition(myLatlng);
					  var data="<div > Stop Name: <font color=sienna><c:out value='${stop_name}'/></font><br>"+
					  			"Stop Number: <font color=sienna><c:out value='${stop_number}'/></font><br>"+
					  			"Location:<font color=sienna> "+address+"</font>"+
					  			"</div>";
					  var infowindow = new google.maps.InfoWindow(
					      { content: data,
					        size: new google.maps.Size(450,450)
					      });
					  google.maps.event.addListener(marker, 'mouseover', function() {
						    infowindow.open(map,marker);
						  });
					  google.maps.event.addListener(marker, 'mouseout', function() {
				    	    infowindow.close();
				    	});
				}
				
				google.maps.event.addDomListener(window, 'load', initialize);
					
				var add;
			  	function geocodePosition(pos) {
			  		
			  	  geocoder.geocode({
			  	    latLng: pos
			  	  }, function(responses) {
			  	    if (responses && responses.length > 0) {
			  	      add= responses[0].formatted_address;
			  	    } else {
			  	    	add='Cannot determine address at this location.';
			  	    }
			  	  });
			  	  return add;
			  	}
    </script>
  </head>
  <body>
    <div id="map-canvas"></div>
  </body>
</html>