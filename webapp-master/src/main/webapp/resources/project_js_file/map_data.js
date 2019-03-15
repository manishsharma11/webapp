
var geocoder = new google.maps.Geocoder();
		  var map;
		  var marker, i;
		  var markers = [];
		
		  	$(document).ready(function(){
		  		var lat=$("#lat").val();
		  		var long=$("#long").val();
				$("#no_buses_running").hide();
				$("#loading").show();	
		  		 map = new google.maps.Map(document.getElementById('map-canvas'), {
				      zoom: 11,
				      
				      	panControl:true,
				         zoomControl:true,
				         mapTypeControl:true,
				         scaleControl:true,
				         streetViewControl:true,
				         overviewMapControl:true,
				         rotateControl:true,
				      center: new google.maps.LatLng(lat,long),
				      travelMode: google.maps.DirectionsTravelMode.DRIVING
				    });
		  		 
		  		setInterval(function() {
		  			
		  			$.ajax({
		  				
		  				url:"/sts/school_admin/map/getMapData",
		  				type:"POST",
		  				success:function(data){
		  					//alert(data);
		  					if(data=="none"){
		  						
		  						$("#no_buses_running").show();
		  						$("#loading").hide();
		  					}
		  					else{
		  						
		  						$("#no_buses_running").hide();
		  						$("#loading").hide();
		  					}
		  					var recv=data.split("+");
		  					
		  					removeFunction();	
		  					for(var i=0;i<recv.length;i++){
		  						
		  						if(recv[i]!=""){
		  							var data=recv[i].split(":");
			  						
						  			
						  			var latLng = new google.maps.LatLng(data[1], data[2]);
						  			var address=geocodePosition(latLng);
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
			  	/*var data4="<div style='width:300px;height:60px;'>No of Students:<b> 4</b><br>"+
						  "Current Location: <font color=sienna>Gloucester,Ottowa</font>"+
						  "<br>Bus Status:<span class='label label-success'>On Time</span></div>";
				var data3="<div style='width:300px;height:60px;'>No of Students:<b> 14</b><br>"+
						  "Current Location: <font color=sienna>Dunderosa Golf Course,Ottowa</font>"+
						  "<br>Bus Status:<span class='label label-warning'>Late</span></div>";		  
				var data2="<div style='width:300px;height:60px;'>No of Students:<b> 22</b><br>"+
						  "Current Location: <font color=sienna>Mcdee St john's Road,Ottowa</font>"+
						  "<br>Bus Status:<span class='label label-danger'>Very Late</span></div>";
				var data1="<div style='width:300px;height:60px;'>No of Students:<b> 16</b><br>"+
						  "Current Location: <font color=sienna>Gloucester,Ottowa</font>"+
						  "<br>Bus Status:<span class='label label-warning'>Late</span></div>";
			  		

						var locations = [
											[ data4, 45.4314, -75.6019, 4 ],
											[ data3, 45.4414, -75.7719, 3 ] ,
											[ data2, 45.4914, -75.8019, 2 ] ,
											[ data1, 45.5014, -75.8619, 1 ] ,
										];
			  			removeFunction();	
						
						
			  				load(locations);*/
				  		
		  	
		  	});
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
		  	