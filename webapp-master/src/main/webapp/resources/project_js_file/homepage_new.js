$(document).ready(function() {
	$("table tbody").empty();
	$("#loading").show();
	load();
	setInterval(function() {
		$("table tbody").empty();
		$("#loading").show();
		
		load();
	}, 30000);
	
	
	$("a").click(function(){
		 
		var note=$(this).attr("value");
		if(note=="ontime" || note=="late" || note=="verylate" || note=="outofservice" ){
			
			$.ajax({
				
				type:"POST",
				url:"homepage/getNotifications",
				data:"status="+note,
				success:function(result){
					
					//
					result=JSON.parse(result);
					//alert(result);
					var append="";
					for(var i=0;i<result.length;i++){
						var label;
						if(result[i].vehicle_status==1){
							label="label-info";
						}
						if(result[i].vehicle_status==2){
							label="label-warning";
						}
						if(result[i].vehicle_status==3){
							label="label-danger";
						}
						
						//sami
						
						if(result[i].vehicle_status==4){
							label="label-info";
						}
						//ended
						
						append=append+'<li class="hoverable"><div class="col1"><div class="content"><div class="content-col1">'
								+'<div class="label '+label+'  "><i class="icon-bullhorn"></i></div></div><div class="content-col2">'
								+'<div class="desc">'+result[i].notification+'</div></div></div></li>';
								;
						
						
					}
					$("#notifications").empty().append(append);
				}
			});
		}
	});
	
	
});

function load(){
	$.ajax({
		url:"homepage/getHomePageData",
		type:"POST",
		success:function(result){
			//alert(result);
			result=$.trim(result);
			//alert(result);
			var forNone=result.split("---");
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
				//alert(forNone[0]);
				var sts=forNone[1].split(":");
				$("#buses_running_outofservice").empty().append(sts[3]);
				$("#buses_running_ontime").empty().append(sts[0]);
				$("#buses_running_late").empty().append(sts[1]);
				$("#buses_running_verylate").empty().append(sts[2]);
				var json=JSON.parse(forNone[0]);
				$("table tbody").empty();
				for(var i=0;i<json.length;i++){
					//alert(json[i].bus_licence_number);
					//  "my, tags are, in here".split(/[ ,]+/)
					/* var res="none";
					 if(json[i].arrived_time!="none")
						 res = json[i].arrived_time.split(" ");*/
					var data=	'<tr>'+
					'<td><a  href="bus_id:'+json[i].vehicle_id+':'+json[i].trip_id+'">'+json[i].vehicle_licence_number+' </a></td>'+
					'<td>'+json[i].current_stop+' </td>'+
					'<td>'+json[i].arrived_time+'</td>'+
					'<td>'+json[i].users_in_bus+'</td>'+
					//alert();
					'<td><a data-toggle="modal" href="driver_id:'+json[i].driver_id+":"+json[i].trip_id+":"+' #myModal1"> '+json[i].driver_name+' </a></td>';
					
					if(json[i].vehicle_status==1){
						data=data+'<td><span class="label label-success">On Time</span></td>';
					}
					if(json[i].vehicle_status==2){
						data=data+'<td><span class="label label-warning">Late</span></td>';
					}
					if(json[i].vehicle_status==3){
						data=data+'<td><span class="label label-danger">Very Late</span></td>';
					}
					if(json[i].vehicle_status==4){
						data=data+'<td><span class="label label-info">Out Of Service</span></td>';
					}
					data=data+'<td><a href="map_id:'+json[i].bus_id+":"+json[i].trip_id+'"> <i class="icon-map-marker"></i>&nbsp;Map</a></td>'+
					'</tr>';
					//alert(out_data[12]);
					$("table tbody").append(data);
					$("#loading").hide();
					$("table tbody a ").click(function(event){
						event.preventDefault();
						var data=$(this).attr("href").split(":");
						//alert($(this).attr("href"));
						if(data[0]=="bus_id"){
							//alert($(this).attr("href"));
								var a1=window.open('bus?bus_id='+data[1]+"&trip_id="+data[2], data[0],'height=' + 600 + 
										',width=' + 900+ ',resizable=no,scrollbars=yes,toolbar=no,menubar=no,location=no');
							
							
						}
						
						else if(data[0]=="map_id"){
							//alert(data[2]);
							var a2=window.open('single_map?route_id=none&trip_id='+data[2], data[0],'height=' + 600 + 
									',width=' + 900+ ',resizable=no,scrollbars=yes,toolbar=no,menubar=no,location=no');
						}
						else if(data[0]=="driver_id"){
							//alert(data);
							$("#board_time").text("");
							$("#exit_time").text("");
							var send=data[2].split("#");
							//alert(send);
							$.ajax({
								
								url:"homepage/getdriverinformation",
								type:"POST",
								data:"driver_id="+data[1]+"&trip_id="+data[2],
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