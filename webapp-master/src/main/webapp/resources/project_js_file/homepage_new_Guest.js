$(document).ready(function() {
	$("table tbody").empty();
	$("#loading").show();
	load();
	setInterval(function() {
		$("table tbody").empty();
		$("#loading").show();
		
		load();
	}, 8000);
	
});

function load(){
	$.ajax({
		
		url:"homepage/getHomePageData",
		type:"POST",
		success:function(result){
			//alert(result);
			result=$.trim(result);
			var out=result.split("+");
			var forNone=out[0].split(":");
			//alert(out);
			if(forNone[0]=="none"){
				$("#no_services").show();
				$("#loading").hide();
				$("#buses_running_outofservice").empty().append(forNone[1]);
				$("#buses_running_ontime").empty().append(0);
				$("#buses_running_late").empty().append(0);
				$("#buses_running_verylate").empty().append(0);
			}
			else{
				$("#no_services").hide();
				//alert(result);
				
				
				for(var i=0;i<out.length;i++){
					
					if(out[i]!=""){
						//alert(out[i]);
						var out_data=out[i].split(",");
							$("#buses_running_outofservice").empty().append(out_data[8]);
							$("#buses_running_ontime").empty().append(out_data[9]);
							$("#buses_running_late").empty().append(out_data[10]);
							$("#buses_running_verylate").empty().append(out_data[11]);
							var data=	'<tr>'+
							'<td><a  href="bus_id:'+out_data[0]+'">'+out_data[1]+' </a></td>'+
							'<td>'+out_data[2]+' </td>'+
							'<td>'+out_data[3]+'</td>'+
							'<td>'+out_data[4]+'</td>'+
							
							'<td><a data-toggle="modal" href="driver_id:'+out_data[5]+'#myModal1">'+out_data[6]+' </a></td>';
							
							if(out_data[7]=="ontime"){
								data=data+'<td><span class="label label-success">On Time</span></td>';
							}
							if(out_data[7]=="late"){
								data=data+'<td><span class="label label-warning">Late</span></td>';
							}
							if(out_data[7]=="verylate"){
								data=data+'<td><span class="label label-danger">Very Late</span></td>';
							}
							data=data+'<td><a href="map_id:'+out_data[0]+'"> <i class="icon-map-marker"></i>&nbsp;Map</a></td>'+
							'</tr>';
						
							$("table tbody").append(data);
							$("table tbody a ").click(function(event){
								event.preventDefault();
								var data=$(this).attr("href").split(":");
								if(data[0]=="bus_id"){
									window.open('bus?bus_id='+data[1], 'popup_name','height=' + 600 + 
											',width=' + 900+ ',resizable=no,scrollbars=yes,toolbar=no,menubar=no,location=no');
								}
								if(data[0]=="driver_id"){
									
								}
								if(data[0]=="map_id"){
									window.open('single_map?bus_id='+data[1], 'popup_name','height=' + 600 + 
											',width=' + 900+ ',resizable=no,scrollbars=yes,toolbar=no,menubar=no,location=no');
								}
								if(data[0]=="driver_id"){
									
									$("#board_time").text("");
									$("#exit_time").text("");
									var send=data[1].split("#");
									$.ajax({
										
										url:"/sts/school_guest/homepage/getdriverinformation",
										type:"POST",
										data:"driver_id="+send[0],
										success:function(result){
											if(result=="none"){
												$("#board_time").text("none");
												$("#exit_time").text("none");
											}
											else{
												var data=result.split("+");
												$("#board_time").text(data[0]);
												$("#exit_time").text(data[1]);
											}
										}
									});
								}
							});
					}
				}
				$("#loading").hide();
			}
			
		},
		error:function(){
			
		}
		
	});
	
}
/*var data=	'<tr>'+
'<td><a onclick=" openWindow()" href="#">'+o[i].bus_licence+' </a></td>'+
'<td>'+o[i].current_stop+' </td>'+
'<td>'+o[i].arrived_time+'</td>'+
'<td>'+o[i].no_of_students+'</td>'+
'<td>'+o[i].driver_name+'</td>'+
'<td><span class="label label-warning">Late</span></td>'+
'<td><a href="#"> <i class="icon-map-marker"></i>&nbsp;Map</a></td>'+
'</tr>';*/