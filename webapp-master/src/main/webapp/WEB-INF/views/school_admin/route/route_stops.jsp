<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" 
           uri="http://java.sun.com/jsp/jstl/functions" %>
<%//System.out.println("In jsp class"); %>
<!DOCTYPE html>
<html lang="en">
<!-- <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>  -->
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<title>Dashboard</title>
<!-- Files to be loaded -->

<script type="text/javascript"
	src="<c:url value="/resources/jquery-1.11.0.min.js" />"></script>
	

<script type="text/javascript">
	
	$(document).ready(function(){
		
		$("#nestable_list_1").append('<li class="dd-item" data-id="1"><div class="dd-handle"><font size="4" color="sienna">100</font>&nbsp;&nbsp;S-labs Stsdfdop</div></li>');
	
		$("#stops button").click(function(e){
			
			 $('#stops button').removeClass("btn-info");
		     $(this).addClass("btn-info");
	       	 var arr= $(this).val().split("*");
	       	 $("#stop_for_insert").val(arr);
	       	 //document.getElementById("stop_for_insert").value;
	       	 //alert(arr);
	       	 $("#stop_name").empty().append(arr[2]);
       		 $("#latitude").empty().append(arr[3]);
       		 $("#longitude").empty().append(arr[4]);
       		 $("#geo").empty().append(arr[5]);
       		 $("#map_button").prop('disabled', false);
       		// alert(arr);
	       	 if(arr[0]=="left"){
	       		 $("#code_1").hide();
	       		 $("#left_button").prop('disabled', true);
	       		 $("#up_button").prop('disabled', true);
	       		 $("#down_button").prop('disabled', true);
	       		 $("#right_button").prop('disabled', false);
	       	 }
	       	 if(arr[0]=="right"){
	       		 $("#left_button").prop('disabled', false);
	       		 $("#right_button").prop('disabled', true);
	       		 $("#up_button").prop('disabled', false);
	       		 $("#down_button").prop('disabled', false);
	       	 }
	    });
		
		 $("#right_button").click(function(){
			
			
			$("#code_1").hide();
		 }); 
		 $("#left_button").click(function(){
			 var stop_data=$("#stop_for_insert").val().split(",");
			 //alert(stop_data[1]);
			 $("#delete_stop_name").empty().append(stop_data[2]);
			 $("#delete_stop_id").val(stop_data[1]);	
				
		 }); 
		 //update_stop_pr
		 
		 $("#add_stop").click(function(){
			 	$("#loading_stopexists").hide();
	  			$("#code_1").hide();
	  			
				 var stop_data=$("#stop_for_insert").val().split(",");
				 //alert(stop_data);
				 var hour=$("#hour").val();
				 var min=$("#min").val();
				 var stop_number=$("#stop_number").val();
				
				 //alert("&geo="+stop_data[4]);
				 $("#loading").show();
				  $.ajax({
					 
					type:"POST",
					url:"routestop/add",
					data:	"stop_id="+stop_data[1]+"&route_id="+$.trim($("#route_id").val())
							+"&stop_time="+hour+":"+min+"&stop_number="+stop_number,
				  	success:function(result){
				  		result=$.trim(result);
				  		//alert(result);
				  		$("#loading").hide();
				  		if(result=="ok"){
				  			window.location.reload();
				  		}
				  		else if(result=="stop_exists"){
				  			$("#loading_stopexists").show();
				  			$("#code_1").hide();
				  			$("#code_2").hide();
				  		}
				  		else if(result=="code_1"){
				  			$("#loading_stopexists").hide();
				  			$("#code_1").show();
				  			$("#code_2").hide();
				  		}
				  		else if(result=="code_2"){
				  			$("#loading_stopexists").hide();
				  			$("#code_2").show();
				  			$("#code_1").hide();
				  		}
				  		else{
				  			alert("Some problem occured try to reload page.....");
				  		}
				  	}
				 }); 
			 
			 
		 });
		 $("#remove_stop").click(function(){
			 var stop_data=$("#stop_for_insert").val().split(",");
			 $("#loading").show();
			 //alert(stop_data);
			 $.ajax({
				 type:"POST",
					url:"routestop/remove",
					data:"id="+$("#delete_stop_id").val()+"&stop_number="+stop_data[8]+"&route_id="+stop_data[6],
				  	success:function(result){
				  		window.location.reload();
				  	}
			 });
		 });
		 $("#up_button").click(function(){
			 var stop_data=$("#stop_for_insert").val().split(",");
			 //alert(stop_data[9]);
			 $("#update_stop_name").empty().append(stop_data[2]);
			 $("#update_stop_id").val(stop_data[1]); 
			 $("#update_stop_pr").empty().append(stop_data[8]);
			 $("#update_stop_time").empty().append(stop_data[9]+":"+stop_data[10]);
			 var stops_size=$("#stops_size").val();
			 var data="";
			 for(var i=1;i<=stops_size;i++){
				 if(i==stop_data[8]){
					 data=data+"<option selected>"+i+"</option>";
				 }
				 else{
					 data=data+"<option>"+i+"</option>";
				 }
			 }
			 $("#update_stop_number").empty().append(data);
			 var data="";
			 for(var i=0;i<=23;i++){
				 
				 var j=i;
				 if(i<10){
					 j="0"+i;
				 }
				 if(i==stop_data[9].split(":")[0]){
					 data=data+"<option selected>"+j+"</option>";
				 }
				 else{
					 data=data+"<option>"+j+"</option>";
				 }
			 }
			// alert(data);
			 $("#update_hour").empty().append(data);
			 var h=$("#update_hour").val();
			// alert(h);
			 var data="";
			 for(var i=0;i<=59;i++){
				 
				 var j=i;
				 if(i<10){
					 j="0"+i;
				 }
				 if(i==stop_data[9].split(":")[1]){
					 data=data+"<option selected>"+j+"</option>";
				 }
				 else{
					 data=data+"<option>"+j+"</option>";
				 }
			 }
			 $("#update_min").empty().append(data);
			 $("#code_1_1").hide();
			 $("#code_2_2").hide();
		 });
		 
		 $("#update_stop").click(function(){
			 var stop_data=$("#stop_for_insert").val().split(",");
			 //alert(stop_data);
			 $("#update_stop_name").empty().append(stop_data[2]);
			 $("#update_stop_id").val(stop_data[1]); 
			 $("#update_stop_pr").empty().append(stop_data[8]);
			 $("#update_stop_time").empty().append(stop_data[9]+":"+stop_data[10]);
			 var old_num=stop_data[8];
			 var stop_id=$("#update_stop_id").val();
			 var new_num= $("#update_stop_number").val();
			 var old_time=stop_data[9]+":"+stop_data[10];
			 var new_time=$("#update_hour").val()+":"+$("#update_min").val();
			 
			 //alert(old_num+"-"+new_num+"-"+old_time+"-"+new_time+"-"+stop_id);
			 $("#loading1").show();
			 //alert(stop_data[6]);
			 $.ajax({
				 type:"POST",
					url:"routestop/update",
					data:"data="+old_num+"-"+new_num+"-"+old_time+"-"+new_time+"-"+stop_id+"-"+stop_data[6],
				  	success:function(result){
				  		//$("#loading1").hide();
				  		//alert(result);
				  		   if(result=="ok"){
				  			window.location.reload();
				  		}
				  		
				  		else if(result=="code_1_1"){
				  			$("#loading_stopexists").hide();
				  			$("#code_1_1").show();
				  			$("#code_2_1").hide();
				  		}
				  		else if(result=="code_2_2"){
				  			$("#loading_stopexists").hide();
				  			$("#code_2_2").show();
				  			$("#code_1_1").hide();
				  		}
				  		else{
				  			alert("Some problem occured try to reload page.....");
				  		}   
				  	}
				 
			 });
		 });
	});
</script>

<%-- <script type="text/javascript"
	src="<c:url value="/resources/project_js_file/homepage.js" />"></script> --%>
<!-- Include CSS files -->
<%@ include file="../jsp-includes/css_files.jsp"%>

<!-- Include JS files -->
<%@ include file="../jsp-includes/js_files.jsp"%>

 <style type="text/css">
		    	
		    /* 	tr:hover {
				    color: #261F1D !important;
				    background-color: #E5C37E !important;
				} */
		    </style>
						
</head>

<body>

	<!-- Include top navigation -->
	<%@include file="../jsp-includes/top_navigation.jsp"%>

	<div id="container">
		<div id="sidebar" class="sidebar-fixed">
			<div id="sidebar-content">




				<!-- Left Navigation -->
				<%@include file="../jsp-includes/left_navigation.jsp"%>





			</div>



			<div id="divider" class="resizeable"></div>

		</div>
		<!-- /Sidebar -->

		<div id="content">
			<div class="container">
				<!-- Breadcrumbs line -->
				<div class="crumbs">
					<ul id="breadcrumbs" class="breadcrumb">
						<li><i class="icon-home"></i> Dashboard</li>
						<li>Bus</li>
						<li>Bus Stops List</li>
					</ul>

					<ul class="crumb-buttons">


						<li><a href="#"> <i class="icon-calendar"></i>&nbsp;${date}
						</a></li>
					</ul>
				</div>
				<!-- /Breadcrumbs line -->

				<!--=== Page Header ===-->
				<div class="page-header">

					<div class="page-title">
						<h3>Bus Stops List</h3>
					</div>
					<br>
					<div class="col-sm-4 col-md-1"></div>


					

					<!-- /.col-md-3 -->

				</div>


				<!--=== Normal ===-->
				<%-- <div class="col-md-12">
					<a href="addstop?route_id=<c:out value="${route_id}"></c:out>" class="btn btn-primary"> <i
						class="icon-plus"></i> <span>Add Stop</span>
					</a>
					
				</div> --%>
						
				<input type="hidden" name ="route_id" id="route_id" value="${route.id} ">
				<div class="row" >
					<div class="col-md-4">
						<div class="widget box">
							<div class="widget-header">
									<h4><i class="icon-reorder"></i>Available Stops</h4>
								</div>
							<div class="widget-content align-right" style="height: 320px;overflow: scroll;">
									<div class="rowalign">
										<div class="col-md-15" id="stops">
												<c:forEach items="${ stops}" var="stop">
														<button class="btn  input-block-level  col-md-2" style="height:32px;text-align: " value="left*${stop.id }*${stop.stop_name }*${stop.latitude }*${stop.longitude }*${stop.geofence }*${route.id}" >
															${stop.stop_name }
														</button>	
														
												</c:forEach>
											
												
												<%-- <table>
													<c:forEach items="${ stops}" var="stop">
														<tr style="cursor: pointer;">
															<td class="btn btn-icon input-block-level"><button class="btn btn-icon input-block-level" >${stop.stop_name }</button></td>
															<td><br></br><br></td>
														</tr>
													</c:forEach>
													<tr style="cursor: pointer;">
														<td><button class="btn" >Primary</button></td>
														<td><br></br><br></td>
													</tr>
													<tr style="cursor: pointer;">
														<td><button class="btn">Primary</button></td>
														
													</tr>
												</table> --%>
										</div>
									</div>
							</div> 
						</div>
					</div> 
					<div class="col-md-3">
						<div class="widget box">
							<div class="widget-header">
											<h4><i class=" icon-envelope"></i>Stop Info</h4>
								</div>
							<div class="widget-content" style="height: 320px;">
								
									<div class="rowalign">
										
										<div class="col-md-11">
													
													<span>
														Stop Name: <font color="sienna" size="2" id="stop_name"></font>
														
													</span>	
													<br></br>	
													<span>
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														Latitude:  <font color="sienna" size="2" id="latitude"></font>
													</span>							
													<br></br>	
													<span>
														&nbsp;
														Longitude: <font color="sienna" size="2" id="longitude"></font>
													</span>	
													<br></br>	
													<span>
														&nbsp;
														Geo Fence: <font color="sienna" size="2" id="geo"></font>
													</span>	
													<br></br>
												
													<br></br><br>	&nbsp;&nbsp;
													
													
													<c:if test="${login_role  == 'ROLE_ADMIN' }">
													<button  class="btn btn-sm btn-primary bs-tooltip" data-toggle="modal" href="#myModal2" disabled id="left_button" data-original-title="Remove stop from route">
														<i class="icon-arrow-left"></i>
													</button>
													<button  class="btn btn-sm btn-primary bs-tooltip" data-toggle="modal" href="#myModal1" disabled id="right_button" data-original-title="Add stop to route">
														<i class="icon-arrow-right"></i>
													</button>
													<button id="up_button" class="btn btn-sm btn-primary bs-tooltip" data-toggle="modal" href="#myModal3"  disabled id="up_button" data-original-title="Update Stop time and change stop priority">
														<i class="icon-edit"></i>
													</button>
													<button  class="btn btn-sm btn-primary bs-tooltip" onclick="javascript: poponload()" disabled id="map_button" data-original-title="Show Stop in map">
														<i class=" icon-map-marker"></i>
													</button>
													</c:if>
													
													<script type="text/javascript">
														function poponload()
														{
															var data=document.getElementById("stop_for_insert").value;
														    testwindow = window.open("show_stop_map?data="+data, "show_stop_map", "location=1,status=1,scrollbars=1,width=800,height=600");
														   
														}
													</script>
										</div>
									</div>
							</div> 
						</div>
					</div> 
					<div class="col-md-4">
						<div class="widget box">
							<div class="widget-header">
									<h4><i class="icon-reorder"></i> Stops in route: <font color="sienna">${route.route_name }</font></h4>
								</div>
							<div class="widget-content" style="height: 320px;overflow: scroll;">
								
								<div class="rowalign" >
										<div class="col-md-15" id="stops">
											<c:forEach items="${ route_stops}" var="route_stop" >
														<button class="btn input-block-level col-md-1" 
														style="height:32px;text-align:"
														 value="right*${route_stop.id }*${route_stop.stop.stop_name }*${route_stop.stop.latitude }*${route_stop.stop.longitude }*
														 ${route_stop.stop.geofence }*${route.id}*${route_stop.stop.id}*${route_stop.stop_number}*${route_stop.stop_time}">
															${route_stop.stop.stop_name } &nbsp; <font style="text-align: right;">( ${route_stop.stop_time} )</font>
															<span class="label label-success">${route_stop.stop_number} </span>
														
														</button>	
														
													
												</c:forEach>
										</div>
									</div>
												
							</div> 
						</div> 
					</div> 
				</div> 
						<input type="hidden" value="" id="stop_for_insert">
						<input type="hidden" value="${route_stops.size() +1 }" id="no_stops">
				
			</div>
			<!-- /.container -->
			</div>
			<!-- /.container -->

				
				<!-- /Normal -->
			</div>
			<!-- /.container -->

										<!-- Modal dialog -->
											<div class="modal fade" id="myModal1">
												<div class="modal-dialog">
													<div class="modal-content">
														<div class="modal-header">
															<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
															<h4 class="modal-title">Add Stop to Route</h4>
														</div>
														<div class="modal-body">
															<div class="row">
																<div class="col-md-16 form-horizontal">	
																		<div class="form-group ">
																			<label class="col-md-3 control-label">Select stop priority:</label>
																			<div class="col-md-4">
																				<select class="col-md-10 select  required" id="stop_number">
																					<c:if test="${route_stops.size()==0 }">
																						<option>1</option>
																					</c:if>
																					<c:if test="${route_stops.size()!=0 }">
																						<c:forEach var="index" begin="1" end="${route_stops.size()+1 }" varStatus="num">
																						   
																							<option><fmt:formatNumber type="number" value="${num.index}" /></option>
																						</c:forEach>
																					</c:if>
																					
																				</select>
																			</div>
																		</div>
																		<br>
																		<div class="form-group ">
																			<label class="col-md-3 control-label">Select stop time:</label>
																			<div class="col-md-4">
																				<select class="col-md-10 select  required" id="hour">
																					<c:forEach begin="00" end="23" varStatus="num">
																						<option><fmt:formatNumber type="number" minIntegerDigits="2" maxIntegerDigits="2" value="${num.index}" /></option>
																					</c:forEach>
																				</select>
																			</div>
																			
																			<div class="col-md-4">
																				<select class="col-md-10 select full-width-fix required" id="min">
																					<c:forEach begin="00" end="59" varStatus="num">
																						<option><fmt:formatNumber type="number" minIntegerDigits="2" maxIntegerDigits="2" value="${num.index}" /></option>
																					</c:forEach>
																				</select>
																			</div>
																		</div>
																		
																		
																  </div>
															</div>
															<!-- <div id="loading" style="display: none;">
																	<h5 style="color: sienna;">please wait...... validating data</h5>
															</div> -->
															<div id="loading_stopexists" style="display: none;">
																	<h5 style="color: brown;">Error: Stop Name already Available, Please Try another</h5>
															</div>
															<div id="code_1" style="display: none;">
																	<h5 style="color: brown;">Error: Current Stop time should be Greater than before Stop, and less than Next Stop</h5>
															</div>
															<div id="code_2" style="display: none;">
																	<h5 style="color: brown;">Error: Current Stop time should be Greater than before Stop, and less than Next Stop</h5>
															</div>
														</div>
														<div class="modal-footer">
															<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
															<button type="button" class="btn btn-primary" id="add_stop">Add</button>
														</div>
													</div><!-- /.modal-content -->
												</div><!-- /.modal-dialog -->
											</div><!-- /.modal -->
						
											<!-- Modal dialog -->
											<div class="modal fade" id="myModal2">
												<div class="modal-dialog">
													<div class="modal-content">
														<div class="modal-header">
															<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
															<h4 class="modal-title">Remove stop from route</h4>
														</div>
														<div class="modal-body">
															Remove stop? &nbsp;<font color="sienna" size="3" id="delete_stop_name"></font>
															<input type="hidden" id="delete_stop_id" value="">
														</div>
														<div class="modal-footer">
															<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
															<button class="btn btn-sm btn-danger" id="remove_stop">Remove</button>
														</div>
													</div><!-- /.modal-content -->
												</div><!-- /.modal-dialog -->
											</div><!-- /.modal -->
											<!-- Modal dialog -->
											<div class="modal fade" id="myModal3">
												<div class="modal-dialog">
													<div class="modal-content">
														<div class="modal-header">
															<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
															<h4 class="modal-title">Update Stop Information</h4>
														</div>
														<div class="modal-body">
															<input type="hidden" id="update_stop_id" value="">
															<input type="hidden" id="stops_size" value="${route_stops.size()}">
															<h5>
																
																Stop Name:&nbsp;<font color="sienna" size="3" id="update_stop_name"></font>
															</h5>
															
															<br></br>
															<div class="row">
																<div class="col-md-16 form-horizontal">	
																		<div class="form-group ">
																			<label class="col-md-3 control-label">Select stop priority:</label>
																			<div class="col-md-4">
																				<select class="col-md-10 select  required" id="update_stop_number">
																					
																					
																				</select>
																			</div>
																		</div>
																		<br>
																		<div class="form-group ">
																			<label class="col-md-3 control-label">Select stop time:</label>
																			<div class="col-md-4">
																				<select class="col-md-10 select  required" id="update_hour">
																				    
																					
																				</select>
																			</div>
																			
																			<div class="col-md-4">
																				<select class="col-md-10 select full-width-fix required" id="update_min">
																					
																				</select>
																			</div>
																		</div>
																		
																		
																  </div>
															</div>
															<div id="loading1" style="display: none;">
																	<h5 style="color: sienna;">please wait...... validating data</h5>
															</div>
															
															<div id="code_1_1" style="display: none;">
																	<h5 style="color: brown;">Error: Current Stop time should be Greater than before Stop, and less than Next Stop</h5>
															</div>
															<div id="code_2_2" style="display: none;">
																	<h5 style="color: brown;">Error: Current Stop time should be Greater than before Stop, and less than Next Stop</h5>
															</div>
														</div>
														<div class="modal-footer">
															<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
															<button class="btn btn-sm btn-primary" id="update_stop">Update</button>
														</div>
													</div><!-- /.modal-content -->
												</div><!-- /.modal-dialog -->
											</div><!-- /.modal -->

</body>
</html>