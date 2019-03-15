<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head><meta name="viewport" content="initial-scale=1.0, user-scalable=no"/><meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
        <title>Google Maps JavaScript API v3 Example: Directions Waypoints</title>
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
		  		
        	   setInterval(function() {
		  			
		  			$.ajax({
		  				
		  				url:"/sts/school_admin/map/getSingleBusMapData",
		  				type:"POST",
		  				data:"bus_id="+$("#bus_id").val(),
		  				success:function(data){
		  					//alert(data);
							
		  					var recv=data.split("+");
		  					
		  					removeFunction();	
		  					for(var i=0;i<recv.length;i++){
		  						
		  						if(recv[i]!=""){
		  							var data=recv[i].split(":");
			  						
		  							
						  			var latLng = new google.maps.LatLng(data[1], data[2]);
						  			
						  			var address=geocodePosition(latLng);
						  			//alert(address);
						  			//alert("Lat:"+data[1]+" Long:"+data[2]);
			  						var data4="<div style='width:400px;height:100px;overflow: auto;'>Bus ID:&nbsp;<font color=sienna><b>"+data[4]+"</font></b><br>" +
			  								"No of Students:<b>"+data[0]+"</b><br>"+
									  "Current Location: <font color=sienna>"+address+"</font>";
									 
			  						var img_path="";
			  						data[3]=$.trim(data[3]);
			  						if(data[3]=="ontime"){
			  							
			  							//data4=data4+ "<br>Bus Status:<span class='label label-success'>On Time</span></div>";
			  							img_path=img_path+"/sts/resources/icons/ontime.png";
			  						}
			  						if(data[3]=="late"){
			  							img_path=img_path+"/sts/resources/icons/late.png";
			  							//data4=data4+ "<br>Bus Status:<span class='label label-warning'>Late</span></div>";
			  						}
			  						if(data[3]=="verylate"){
			  							
			  							img_path=img_path+"/sts/resources/icons/verylate.png";
			  							//data4=data4+ "<br>Bus Status:<span class='label label-danger'>Very Late</span></div>";
			  							//alert(data4);
			  						}
			  						
			  						var locations = [
														[ data4, data[1],data[2], 1 ]
														
													];	
			  						
			  						//alert(locations);
			  						load(locations,img_path);
		  						}
		  						
		  					}
		  				}
		  			});
		  			
		  		}, 3000);
        	   initialize(lat,long1);
			   
			
			    function initialize() {
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
		var stops_json=jQuery.parseJSON(stops );
		//alert(stops_json[0].id);
		var i=1;
	
		$.each(stops_json, function (index, value) {
		   
		  if(i==1){
			  start  = new google.maps.LatLng(value.lattitude,value.longitude);
		  }
	       stop = new google.maps.LatLng(value.lattitude,value.longitude);
	       waypts.push({
	            location:stop,
	            stopover:true
	        });
	    });
		       
			
		
		var admin=$("#admin").val();
		var school=jQuery.parseJSON(admin );
		        end = new google.maps.LatLng(school.latitude,school.longitude );
		        var request = {
		            origin: start,
		            destination: end,
		            waypoints: waypts,
		            optimizeWaypoints: true,
		            travelMode: google.maps.DirectionsTravelMode.WALKING
		        };
		        directionsService.route(request, function(response, status) {
		            if (status == google.maps.DirectionsStatus.OK) {
		                directionsDisplay.setDirections(response);
		                var route = response.routes[0];
		
		            }
		        });
		    }
           function clear(){
		  		clearTimeout(time_interval);
		  	}
		  	function removeFunction(){
		  		
		  		while(markers.length){
		            markers.pop().setMap(null);
		        }
		  		
		  	}
		  	function load(loc,img_path){
				
				//alert(loc);
				
		
		  		 var infowindow = new google.maps.InfoWindow();
		
				    for (var i = 0; i < loc.length; i++) {  
				        marker = new google.maps.Marker({
				        position: new google.maps.LatLng(loc[i][1], loc[i][2]),
				        map: map,
				        icon: img_path
				      });
				      markers.push(marker);
				      google.maps.event.addListener(marker, 'mouseover', (function(marker, i) {
				        return function() {
				          infowindow.setContent(loc[i][0]);
				          infowindow.open(map, marker);
				        };
				      })(marker, i));
				      
				      google.maps.event.addListener(marker, 'mouseout', function() {
				    	    infowindow.close();
				    	});
				    }
				    
			}
		  	function removeFunction(){
		  		while(markers.length){
		            markers.pop().setMap(null);
		        }
		  		
		  	}
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

		  	function updateMarkerAddress(str) {
		  	  alert(str);
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
    <input type="hidden" value="<c:out value="${admin }"></c:out>" id="admin">
    <input type="hidden" value="<c:out value="${bus_id }"></c:out>" id="bus_id">
    <input type="hidden" value="<c:out value="${admin1.longitude }"></c:out>" id="long">
	<input type="hidden" value="<c:out value="${admin1.latitude }"></c:out>" id="lat">
</html>