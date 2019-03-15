<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head><meta name="viewport" content="initial-scale=1.0, user-scalable=no"/><meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
        <title>Stops in Route</title>
        <link href="http://code.google.com/apis/maps/documentation/javascript/examples/default.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false">
        </script>
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
        <script type="text/javascript">
        var directionDisplay;
	    var directionsService = new google.maps.DirectionsService();
	    var map;
	    var marker;
	    var geocoder = new google.maps.Geocoder();
		  var markers = [];
           $(document).ready(function(){
        	   var lat=$("#lat").val();
		  		var long1=$("#long").val();
        	    //alert(lat +" "+long1);
        	   initialize(lat,long1);
			   
			
			    function initialize(lat,long1) {
			        directionsDisplay = new google.maps.DirectionsRenderer();
			        var chicago = new google.maps.LatLng(lat,long1);
			        var myOptions = {
			            zoom: 6,
			            mapTypeId: google.maps.MapTypeId.ROADMAP,
			            center: chicago
			        }
			        map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
			        directionsDisplay.setMap(map);
			        calcRoute();
			    }
           });
           function calcRoute() {
   			
		        var waypts = [];
		
				//stop = new google.maps.LatLng(-39.419, 175.57);
				var stops=$("#stops").val();
				//alert(stops);
				var stops_json=jQuery.parseJSON(stops );
				//alert(stops_json[0].longitude);
				
				var start,end;
				$.each(stops_json, function (index, value) {
				  //alert(index);
				  //alert(value.stop.latitude +"----"+value.stop.longitude);
				  if(index==0){
					  start  = new google.maps.LatLng(value.stop.latitude,value.stop.longitude);
					  
				  }
			       stop = new google.maps.LatLng(value.stop.latitude,value.stop.longitude);
			      
			       waypts.push({
			            location:stop,
			            stopover:true
			       
			        });
			    });
				       
			 	var lat=$("#lat").val();
	  		    var long1=$("#long").val();
				//alert(waypts);
		        end = new google.maps.LatLng(lat,long1 );
		        //alert(start +" "+end);
		        var request = {
		            origin: start,
		            destination: end,
		            waypoints: waypts,
		            optimizeWaypoints: true,
		            travelMode: google.maps.DirectionsTravelMode.DRIVING
		        };
		        //alert("finished");
		        directionsService.route(request, function(response, status) {
		        	//alert(status);
		            if (status == google.maps.DirectionsStatus.OK) {
		                directionsDisplay.setDirections(response);
		                var route = response.routes[0];
		
		            }
		        });
		    }
        </script>
    </head>
    <body onload="initialize()">
        <div id="map_canvas" style="width:90%;height:80%;">
        </div>
        <br />
        <div>

        </div>
    </body>
     
    <input type="hidden" value="<c:out value="${stops }"></c:out>" id="stops">
    <input type="hidden" value="<c:out value="${admin1.school_longitude }"></c:out>" id="long">
	<input type="hidden" value="<c:out value="${admin1.school_latitude }"></c:out>" id="lat">
</html>