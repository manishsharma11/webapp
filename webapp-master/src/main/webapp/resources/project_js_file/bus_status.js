$(document).ready(function() {
	
	$("#loading").show();
	load();
	setInterval(function() {
		
		$("#loading").show();
		
		load();
	}, 4000);
	
	
    
});
function load(){
	//alert($("#session").val());
	$('[data-toggle]').popover('hide');
	
	$.ajax({
		
		url:"bus/getBusPageData",
		type:"POST",
		data:"bus_id="+$("#bus_id").val()+"&trip_id="+$("#session").val(),
		success:function(res){
			
				res=$.trim(res);
				//alert(res);
				if(res=="none"){
					//alert(" I am none");
					$("#loading").hide();
				}
				else{
					var data=res.split("+");
					
					var obj = JSON.parse(data[5]);
					//var absent_students = JSON.parse(data[7]);
				
					
					
					$("#bus_lice_number").empty().append('Bus: &nbsp;&nbsp;<font color="sienna">'+data[3]+'</font>');
					$("#no_of_students").empty().append('Subscribers in bus: &nbsp;&nbsp;<font color="sienna">'+data[4]+'</font>');
					if(data[1]=="ontime"){
						$("#bus_status").empty().append('Status: &nbsp;&nbsp;<span class="label label-success">Ontime</span>');
					}
					if(data[1]=="late"){
						$("#bus_status").empty().append('Status: &nbsp;&nbsp;<span class="label label-warning">Late</span>');
					}
					if(data[1]=="verylate"){
						$("#bus_status").empty().append('Status: &nbsp;&nbsp;<span class="label label-danger">Very Late</span>');
					}
					$("#bus_current_stop").empty().append('Current Stop:&nbsp;&nbsp;<font color="sienna">'+data[2]+'</font>');
					/* var res="none";
					 if(json[i].arrived_time!="none")
						 res = json[i].arrived_time.split(" ");*/
					$("#bus_current_arrived").empty().append('Arrived Time:&nbsp;&nbsp;<font color="sienna">'+data[0]+'</font>');
					
					$("#tbody_students").empty();
					var append="";
					for(var i=0;i<obj.length;i++){
						

						//alert(obj[i]);
							var in_stop = "";
							var out_stop = "";
							var in_stop_time = "";
							var out_stop_time = "";
							var subscriber_boarded_wrong_stop_inbound = obj[i].subscriber_boarded_wrong_stop_inbound;
							var subscriber_boarded_wrong_stop_outbound = obj[i].subscriber_boarded_wrong_stop_outbound;
							//alert(subscriber_boarded_wrong_stop_inbound);
							if(subscriber_boarded_wrong_stop_inbound=="none" || subscriber_boarded_wrong_stop_inbound == null)
								in_stop ='<span class="label label-success">'+obj[i].in_stop+'</span>';
							else{
								/*
								in_stop="<span class='label label-warning bs-popover' data-trigger='hover' data-placement='top' data-content='Popover body goes here! Popover body goes here!' data-original-title='Popover at top'>"
								+obj[i].in_stop
								+"</span>";*/
								var sp=subscriber_boarded_wrong_stop_inbound.split("*");
								//alert(sp);
								
								
								in_stop='<span  class="label label-warning bs-popover" data-html="true" title="Subscriber Boarded Bus at Wrong Stop" '+
										'	data-container="body" data-toggle="popover" data-placement="top"'+
										'	data-content="Actual Stop: <font  size=3><b> '+sp[1]+'</b></font> ">'+obj[i].in_stop
										'</span>';
								
								//alert(subscriber_boarded_wrong_stop_inbound);
							}
							//alert(subscriber_boarded_wrong_stop_outbound);
							if(subscriber_boarded_wrong_stop_outbound=="none" || subscriber_boarded_wrong_stop_outbound ==null){
								//alert(subscriber_boarded_wrong_stop_outbound);
								if(obj[i].out_stop!="none")
									out_stop='<span class="label label-success">'+obj[i].out_stop+'</span>';
								else
									out_stop=obj[i].out_stop;
							}
								
							else{
							//alert(subscriber_boarded_wrong_stop_outbound);
								var sp=subscriber_boarded_wrong_stop_outbound.split("*");
								out_stop='<span  class="label label-danger bs-popover"  data-html="true" title="Subscriber Getoff Bus at Wrong Stop" '+
								'	data-container="body" data-toggle="popover" data-placement="top"'+
								'	data-content="Actual Stop:  <font  size=3><b> '+sp[1]+'</b></font> ">'+obj[i].out_stop
								'</span>';
								//alert(subscriber_boarded_wrong_stop_inbound);
							}
							
							in_stop_time = obj[i].in_time;
							out_stop_time = obj[i].out_time;
					
							var subscriber_boarded_wrong_bus=obj[i].subscriber_boarded_wrong_bus;
							var subscriber_boarded_wrong_trip=obj[i].subscriber_boarded_wrong_trip;
							
							if(subscriber_boarded_wrong_bus!="none"){
								
								append = append + "<tr>"  + "<td>"+'<span  class="label label-danger bs-popover"  data-html="true" title="Student Boarded Wrong Bus" '+
								'	data-container="body" data-toggle="popover" data-placement="top"'+
								'	data-content="Assigned Bus:  <font  size=3><b> '+subscriber_boarded_wrong_bus.split(":")[0]+'</b></font> &nbsp;&nbsp;Boarded Bus:  <font  size=3><b> '+subscriber_boarded_wrong_bus.split(":")[1]+'</b></font> ">'
								
								+ obj[i].subscriber_name+ "</td>";
							}
							
							else if(subscriber_boarded_wrong_bus=="none" && subscriber_boarded_wrong_trip!="none"){
								append = append + "<tr>"  + "<td>"+'<span  class="label label-danger bs-popover"  data-html="true" title="Student Taken different Trip" '+
								'	data-container="body" data-toggle="popover" data-placement="top"'+
								'	data-content="Assigned Trip:  <font  size=3><b> '+subscriber_boarded_wrong_trip.split(":")[0]+'</b></font> &nbsp;&nbsp;Boarded Trip:  <font  size=3><b> '+subscriber_boarded_wrong_trip.split(":")[1]+'</b></font> ">'
								
								+ obj[i].subscriber_name+ "</td>";
							}
							else{
								append = append + "<tr>"  + "<td>" + obj[i].subscriber_name+ "</td>";
							}
							append = append + "<td>" + in_stop + "</td>"
									+ "<td>" + in_stop_time + "</td>" +
									
									"<td>" + out_stop + "</td>" + "<td>"
									+ out_stop_time + "</td>" + "</tr>"
								
									;
								
					}
					/*for(var j=0;j<absent_students.length;j++){
						var stop_name;
						if(data[6]=="Drop Off"){
							
							stop_name=absent_students[j].stop_name_fromschool;
						}
						if(data[6]=="Pick Up"){
							stop_name=absent_students[j].stop_name_fromhome;
						}
						var absent='<span  class="label label-danger bs-popover"  data-html="true" title="Student Not Boarded Bus" '+
						'	data-container="body" data-toggle="popover" data-placement="top"'+
						'	data-content="At Stop:  <font  size=3><b> '+stop_name+' </b></font> "> Absent</span>';
						append = append + "<tr>"  + "<td>  "+absent_students[j].first_name +" "+absent_students[j].last_name+"</td>"
						+"<td>"+absent+" </td>"
						+"<td> </td>"
						+"<td> </td>"
						+"<td> </td>"
						+ "</tr>"
					
						;
					}*/
					$("#tbody_students").append(append);
					
					}
				
				 $("[data-toggle='popover']").popover({ trigger: "hover" });
				 $("#loading").hide();
				 
			}
	
	
	});
}
				
				