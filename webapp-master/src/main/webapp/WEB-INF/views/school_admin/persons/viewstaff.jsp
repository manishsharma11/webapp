<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
		  
		  //bus_id_number_fromhome
		  $("#bus_licence_number_fromhome1").change(function(){
				$("#loading").show();
				var bus=$(this).val().split(":");
				//alert(bus);
				$("#bus_id_number_fromhome").val(bus[0]);
				$("#bus_licence_number_fromhome").val(bus[1]);
		        $.ajax({		        	
		        	url:"/sts/school_admin/persons/students/getAllRoutesByBusId",
		        	type:"POST",
		        	data:"bus_licence_number="+bus[1]+"&type=Pick Up",
		        	success:function(result){		        	
		        		 var obj = JSON.parse( result );
		        		//alert(obj);
		        		$("#route_name_fromhome1").empty();
		        		var data="<option></option>";
		        		for(var i=0;i<obj.length;i++){
		        			//alert(obj[i].stop_name);
		        			if(obj[i].route_name!="")
		        				data=data+"<option value=\""+obj[i].route_id+":"+obj[i].route_name+"\">"+obj[i].route_name+"</option>";
		        		}
		        		$("#route_name_fromhome1").append(data);
		        		$("#route_name_fromhome1").prop("disabled", false);
		        		$("#loading").hide();  
		        	}
		        });
		    });
		  $("#route_name_fromhome1").change(function(){
				$("#loading").show();
				var route=$(this).val().split(":");
				
				$("#route_id_fromhome").val(route[0]);
				$("#route_name_fromhome").val(route[1]);
		        $.ajax({
		        	
		        	url:"/sts/school_admin/persons/students/getAllTripsByBusId",
		        	type:"POST",
		        	data:"route_id="+route[0]+"&type=Pick Up",
		        	success:function(result){
		        		//alert(result);
		        		 var obj = JSON.parse( result );
		        		$("#trip_name_fromhome1").empty();
		        		var data="<option></option>";
		        		for(var i=0;i<obj.length;i++){
		        			//alert(obj[i].stop_name);
		        			data=data+"<option value=\""+obj[i].id+":"+obj[i].trip_name+"\">"+obj[i].trip_name+"</option>";
		        			//alert(data);
		        		}
		        		$("#trip_name_fromhome1").append(data);
		        		$("#trip_name_fromhome1").prop("disabled", false);
		        		$("#loading").hide(); 
		        	}
		        });
		    });
		  //trip_id_fromschool
		  $("#trip_name_fromhome1").change(function(){
				$("#loading").show();
				//alert($(this).val());
				var trip=$(this).val().split(":");
				$("#trip_id_fromhome").val(trip[0]);
				$("#trip_name_fromhome").val(trip[1]);
		        $.ajax({		        	
		        	url:"/sts/school_admin/persons/students/getAllStopsByTripId",
		        	type:"POST",
		        	data:"trip_id="+trip[0],
		        	success:function(result){
		        		//alert(result);
		        		  var obj = JSON.parse( result );
		        		$("#stop_id_fromhome1").empty();
		        		var data="<option></option>";
		        		for(var i=0;i<obj.length;i++){
		        			//alert(obj[i].stop_name);
		        			data=data+"<option value=\""+obj[i].id+":"+obj[i].stop_name+"\">"+obj[i].stop_name+"</option>";
		        		}
		        		$("#stop_id_fromhome1").append(data);
		        		$("#stop_id_fromhome1").prop("disabled", false);
		        		$("#loading").hide();
		        	}
		        });
		    });
		  $("#stop_id_fromhome1").change(function(){
			  var stop=$(this).val().split(":");
			  //stop_name_fromhome
			  $("#stop_name_fromhome").val(stop[1]);
			  $("#stop_id_fromhome").val(stop[0]);
		  });
		  $("#bus_licence_number_fromschool1").change(function(){
				$("#loading").show();
				//bus_id_number_fromschool
				var bus=$(this).val().split(":");
				//alert(bus);
				$("#bus_id_number_fromschool").val(bus[0]);
				$("#bus_licence_number_fromschool").val(bus[1]);
		        $.ajax({
		        	
		        	url:"/sts/school_admin/persons/students/getAllRoutesByBusId",
		        	type:"POST",
		        	data:"bus_licence_number="+bus[1]+"&type=Drop Off",
		        	success:function(result){
		        		 var obj = JSON.parse( result );
			        		// alert(obj);
			        		$("#route_name_fromschool1").empty();
			        		var data="<option></option>";
			        		for(var i=0;i<obj.length;i++){
			        			//alert(obj[i].stop_name);
			        			if(obj[i].route_name!="")
			        				data=data+"<option value=\""+obj[i].route_id+":"+obj[i].route_name+"\">"+obj[i].route_name+"</option>";
			        		}
		        		$("#route_name_fromschool1").append(data);
		        		$("#route_name_fromschool1").prop("disabled", false);
		        		$("#loading").hide();  
		        	}
		        });
		    });
		  $("#route_name_fromschool1").change(function(){
				$("#loading").show();
				var route=$(this).val().split(":");
				$("#route_id_fromschool").val(route[0]);
				$("#route_name_fromschool").val(route[1]);
				//alert(route);
		        $.ajax({
		        	
		        	url:"/sts/school_admin/persons/students/getAllTripsByBusId",
		        	type:"POST",
		        	data:"route_id="+route[0]+"&type=Drop Off",
		        	success:function(result){
		        		//alert(result);
		        		 var obj = JSON.parse( result );
		        		$("#trip_name_fromschool1").empty();
		        		var data="<option></option>";
		        		for(var i=0;i<obj.length;i++){
		        			//alert(obj[i].stop_name);
		        			data=data+"<option value=\""+obj[i].id+":"+obj[i].trip_name+"\">"+obj[i].trip_name+"</option>";
		        		}
		        		$("#trip_name_fromschool1").append(data);
		        		$("#trip_name_fromschool1").prop("disabled", false);
		        		$("#loading").hide(); 
		        	}
		        });
		    });
		  $("#trip_name_fromschool1").change(function(){
				$("#loading").show();
				//alert($(this).val());
				var trip=$(this).val().split(":");
				$("#trip_name_fromschool").val(trip[1]);
				$("#trip_id_fromschool").val(trip[0]);
		        $.ajax({
		        	
		        	url:"/sts/school_admin/persons/students/getAllStopsByTripId",
		        	type:"POST",
		        	data:"trip_id="+trip[0],
		        	success:function(result){
		        		//alert(result);
		        		  var obj = JSON.parse( result );
		        		$("#stop_name_fromschool1").empty();
		        		var data="<option></option>";
		        		for(var i=0;i<obj.length;i++){
		        			//alert(obj[i].stop_name);
		        			data=data+"<option value=\""+obj[i].id+":"+obj[i].stop_name+"\">"+obj[i].stop_name+"</option>";
		        		}
		        		$("#stop_name_fromschool1").prop("disabled", false);
		        		$("#stop_name_fromschool1").append(data);
		        		$("#loading").hide();
		        	}
		        });
		    });
		  $("#stop_name_fromschool1").change(function(){
			  var stop=$(this).val().split(":");
			  //stop_name_fromhome
			  $("#stop_id_fromschool").val(stop[0]);
			  $("#stop_name_fromschool").val(stop[1]);
		  });
	  });			
	</script>
<%-- <script type="text/javascript"
	src="<c:url value="/resources/project_js_file/homepage.js" />"></script> --%>
<!-- Include CSS files -->
<%@ include file="../jsp-includes/css_files.jsp"%>

<!-- Include JS files -->
<%@ include file="../jsp-includes/js_files.jsp"%>

<script type="text/javascript">
$(document).ready(function() {
    $("#selec_all").click(function() {  //on click 
    	$("input:checkbox").css("checked",  true);
        
    });
    
});
</script>
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
						<li>Users</li>
						<li>Staff</li>
						<li>Add Staff</li>
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
						<h3>Staff</h3>
						<span>Add Dtaff</span>
					</div>
					<br>
					<div class="col-sm-4 col-md-1"></div>



				</div>

				<div class="row" id="driver_form_add">
					<!--=== Validation Example 1 ===-->
					<div class="col-md-12">
						<div class="widget box">
							<div class="widget-header">
								<h4>
									<i class="icon-reorder"></i>Staff Form
								</h4>
							</div>
							<div class="widget-content">
								<form class="form-horizontal row-border" id="validate-4" action="addstaffaction" method="post">


									<!--=== Page Content ===-->
									<div class="row">
										<!--=== Validation Example 1 ===-->
										<div class="col-md-6">
											<div class="widget box">
												<div class="widget-header">
													<h4>
														<i class="icon-reorder"></i> Staff Details
													</h4>
												</div>
												<div class="widget-content">
													<div class="form-group">
														<label class="col-md-4 control-label">Name:</label>
														<label class="col-md-0 control-label">
															<font color="sienna">${staff.full_name}</font>
														</label>
													</div>
													
													<div class="form-group">
														<label class="col-md-4 control-label">
															Staff ID:
														</label>
														<label class="col-md-0 control-label">
															<font color="sienna">${staff.staff_id}</font>
														</label>
													</div>
													
													<div class="form-group">
														<label class="col-md-4 control-label">RFID
															Number: 
														</label>
														<label class="col-md-0 control-label">
															<font color="sienna">${staff.rfid_number }</font>
														</label>
														
													</div>
													<div class="form-group">
														<label class="col-md-4 control-label">
														Gender:
														</label>
														<label class="col-md-0 control-label">
															<font color="sienna">${staff.gender }</font>
														</label>
													
													</div>		
													<div class="form-group">
														<label class="col-md-4 control-label">
															Mobile Number: 
														</label>
														<label class="col-md-0 control-label">
															<font color="sienna">${staff.mobile_number }</font>
														</label>
													</div>
													<div class="form-group">
														<label class="col-md-4 control-label">
															Mail: 
														</label>
														<label class="col-md-0 control-label">
															<font color="sienna">${staff.email }</font>
														</label>
													</div>							
												</div>
											</div>
											<!-- /Student Details  -->
										</div>

										
										<div class="col-md-6">
											<div class="widget box">
												<div class="widget-header">
													<h4><i class="icon-reorder"></i> Address </h4>
												</div>
												<%-- --%>
												<div class="widget-content">
																		<div class="form-group">
																			<label class="col-md-4 control-label">
																				Street: 
																			</label>
																			<label class="col-md-0 control-label">
																				<font color="sienna">${address.street }</font>
																			</label>
																		</div>
																		<div class="form-group">
																			<label class="col-md-4 control-label">
																				City: 
																			</label>
																			<label class="col-md-0 control-label">
																				<font color="sienna">${address.city }</font>
																			</label>
																		</div>
																		<div class="form-group">
																			<label class="col-md-4 control-label">
																				State: 
																			</label>
																			<label class="col-md-0 control-label">
																				<font color="sienna">${address.state }</font>
																			</label>
																		</div>
																		<div class="form-group">
																			<label class="col-md-4 control-label">
																				Postal: 
																			</label>
																			<label class="col-md-0 control-label">
																				<font color="sienna">${address.postal }</font>
																			</label>
																		</div>
																		<div class="form-group">
																			<label class="col-md-4 control-label">
																				Country: 
																			</label>
																			<label class="col-md-0 control-label">
																				<font color="sienna">${address.country }</font>
																			</label>
																		</div>
																	</div>
											</div>
						<!-- /Validation Example 1 -->
					</div>
										<!-- /Parent Contact Details -->
									</div>
									<!-- first row completed -->
									<div class="row">
										<!--=== Validation Example 1 ===-->
										<div class="col-md-6">
											<div class="widget box">
												<div class="widget-header">
													<h4>
														<i class="icon-reorder"></i> From Home
													</h4>
												</div>
												<div class="widget-content">
													
													<div class="form-group">
														<label class="col-md-4 control-label">
															Bus:
														</label>
														<label class="col-md-0 control-label">
															<font color="sienna">${homeBus.bus_licence_number }</font>
														</label>
													</div>
													<div class="form-group">
														<label class="col-md-4 control-label">
															Trip:
														</label>
														<label class="col-md-0 control-label">
															<font color="sienna">${homeTrip.trip_name }</font>
														</label>
													</div>		
													<div class="form-group">
														<label class="col-md-4 control-label">
															Route:
														</label>
														<label class="col-md-0 control-label">
															<font color="sienna">${homeRoute.route_name }</font>
														</label>
													</div>				

													<div class="form-group">
														<label class="col-md-4 control-label">
															Stop:
														</label>
														<label class="col-md-0 control-label">
															<font color="sienna">${homeStop.stop_name }</font>
														</label>
													</div>
																						
												</div>
											</div>
											<!-- /From Home -->
										</div>

										<!--=== Validation Example 2 ===-->
										<div class="col-md-6">
											<div class="widget box">
												<div class="widget-header">
													<h4>
														<i class="icon-reorder"></i> From School
													</h4>
												</div>
												<%-- --%>
												<div class="widget-content">
													
													<div class="form-group">
														<label class="col-md-4 control-label">
															Bus:
														</label>
														<label class="col-md-0 control-label">
															<font color="sienna">${schoolBus.bus_licence_number }</font>
														</label>
														</div>
													</div>
													<div class="form-group">
														<label class="col-md-4 control-label">
															Trip:
														</label>
														<label class="col-md-0 control-label">
															<font color="sienna">${schoolTrip.trip_name }</font>
														</label>
													</div>	
													<div class="form-group">
														<label class="col-md-4 control-label">
															Route:
														</label>
														<label class="col-md-0 control-label">
															<font color="sienna">${schoolRoute.route_name }</font>
														</label>
													</div>		
															

													<div class="form-group">
														<label class="col-md-4 control-label">
															Stop:
														</label>
														<label class="col-md-0 control-label">
															<font color="sienna">${schoolStop.stop_name }</font>
														</label>
													</div>
																							
												</div>
											</div>
										</div>
										<!-- /Validation Example 2 -->
									
									
					<div class="row">
					
					<div class="col-md-6">
						<div class="widget box">
							<div class="widget-header">
								<h4><i class="icon-reorder"></i> Alerts </h4>
							</div>
							<%-- --%>
							<div class="widget-content">
								
								<div class="row">
										
									<div class="col-md-6">
								
										<table class="table table-striped table-bordered table-checkable table-highlight-head table-no-inner-border table-hover">
									
										<thead>
											<tr>
												<th class="checkbox-column">
													Mail alerts All
													
													<c:choose>
												        <c:when test="${email.all_alerts == 'on' }">
												            <input type="checkbox" class="uniform" name="email_all" checked="checked" disabled="disabled">
												        </c:when>
												       
												        <c:otherwise>
												            <input type="checkbox" class="uniform" name="email_all"  disabled="disabled">
												        </c:otherwise>
												    </c:choose>
													
												</th>
												
												
											</tr>
											
										</thead>
										<tbody>
										
											
											<tr>
												<td class="checkbox-column" >
													
													No Show&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<c:choose>
												        <c:when test="${email.no_show == 'on' }">
												            <input type="checkbox" class="uniform" name="email_no_show" checked="checked" disabled="disabled">
												        </c:when>
												       
												        <c:otherwise>
												            <input type="checkbox" class="uniform" name="email_no_show"  disabled="disabled">
												        </c:otherwise>
												    </c:choose> 
												</td>
												
											</tr>
											<tr>
												<td class="checkbox-column">
													Late&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	&nbsp;
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<c:choose>
												        <c:when test="${email.late == 'on' }">
												            <input type="checkbox" class="uniform" name="email_late" checked="checked" disabled="disabled">
												        </c:when>
												       
												        <c:otherwise>
												            <input type="checkbox" class="uniform" name="email_late"  disabled="disabled">
												        </c:otherwise>
												    </c:choose>
												 </td>
												
											</tr>
											<tr>
												<td class="checkbox-column">Irregularities &nbsp;&nbsp;&nbsp;
													<c:choose>
												        <c:when test="${email.irregularities == 'on' }">
												            <input type="checkbox" class="uniform" name="email_irregularities" checked="checked" disabled="disabled">
												        </c:when>
												       
												        <c:otherwise>
												            <input type="checkbox" class="uniform" name="email_irregularities"  disabled="disabled">
												        </c:otherwise>
												    </c:choose>
												 </td>
												
											</tr>
											<tr>
												<td class="checkbox-column">
													Regularities&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<c:choose>
												        <c:when test="${email.regularities == 'on' }">
												            <input type="checkbox" class="uniform" name="email_regularities" checked="checked" disabled="disabled">
												        </c:when>
												       
												        <c:otherwise>
												            <input type="checkbox" class="uniform" name="email_regularities"  disabled="disabled">
												        </c:otherwise>
												    </c:choose>
												 </td>
												
											</tr>
										</tbody>
									</table>					
									</div>
									<div class="col-md-6">
								
										<table class="table table-striped table-bordered table-checkable table-highlight-head table-no-inner-border table-hover">
									
										<thead>
											<tr>
												<th class="checkbox-column">
													Sms alerts All
														
													<c:choose>
												        <c:when test="${sms.all_alerts == 'on' }">
												            <input type="checkbox" class="uniform" name="sms_all" checked="checked" disabled="disabled">
												        </c:when>
												       
												        <c:otherwise>
												            <input type="checkbox" class="uniform" name="sms_all"  disabled="disabled">
												        </c:otherwise>
												    </c:choose>
												</th>
												
											</tr>
											
										</thead>
										<tbody>
										
											
											<tr>
												<td class="checkbox-column" >
													No Show&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<c:choose>
												        <c:when test="${sms.no_show == 'on' }">
												            <input type="checkbox" class="uniform" name="sms_no_show" checked="checked" disabled="disabled">
												        </c:when>
												       
												        <c:otherwise>
												            <input type="checkbox" class="uniform" name="sms_no_show"  disabled="disabled">
												        </c:otherwise>
												    </c:choose>
												</td>
												
											</tr>
											<tr>
												<td class="checkbox-column">
													Late&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	&nbsp;
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<c:choose>
												        <c:when test="${sms.late == 'on' }">
												            <input type="checkbox" class="uniform" name="sms_late" checked="checked" disabled="disabled">
												        </c:when>
												       
												        <c:otherwise>
												            <input type="checkbox" class="uniform" name="sms_late"  disabled="disabled">
												        </c:otherwise>
												    </c:choose>
												 </td>
												
											</tr>
											<tr>
												<td class="checkbox-column">Irregularities &nbsp;&nbsp;&nbsp;
													<c:choose>
												        <c:when test="${sms.irregularities == 'on' }">
												            <input type="checkbox" class="uniform" name="sms_irregularities" checked="checked" disabled="disabled">
												        </c:when>
												       
												        <c:otherwise>
												            <input type="checkbox" class="uniform" name="sms_irregularities"  disabled="disabled">
												        </c:otherwise>
												    </c:choose>
												 </td>
												
											</tr>
											<tr>
												<td class="checkbox-column">
													Regularities&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<c:choose>
												        <c:when test="${sms.regularities == 'on' }">
												            <input type="checkbox" class="uniform" name="sms_regularities" checked="checked" disabled="disabled">
												        </c:when>
												       
												        <c:otherwise>
												            <input type="checkbox" class="uniform" name="sms_regularities"  disabled="disabled">
												        </c:otherwise>
												    </c:choose>
												 </td>
												
											</tr>
										</tbody>
									</table>					
									</div>
									</div>
							</div>
						</div>
					</div>				
				</div>
				
									<div class="form-actions">
										<a type="button" class="btn btn-default"
											href="/sts/school_admin/persons/staff">Back</a> 
									</div>
				</form>
				</div>
				
						
							
							
						</div>
						<!-- /Validation Example 1 -->
					</div>
					
			</div>
			</div>
				</div>

			</div>
</body>
</html>