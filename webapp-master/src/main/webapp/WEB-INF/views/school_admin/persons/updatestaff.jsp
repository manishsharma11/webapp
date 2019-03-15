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
$(document).ready(function() {
    $("#selec_all").click(function() {  //on click 
    	$("input:checkbox").css("checked",  true);
        
    });
    $('#take_bus_fromhome').bind('change', function () {

 	   if ($(this).is(':checked')){
 		   
 		   $("#bus_licence_number_fromhome1").prop("disabled", false);
 		   
 	   }
 	     
 	   else{
 		   $("#bus_licence_number_fromhome1").prop("disabled", true);
 	   }
 		  

 });
 $('#take_bus_fromschool').bind('change', function () {

	   if ($(this).is(':checked')){
		   
		   $("#bus_licence_number_fromschool1").prop("disabled", false);
		   
	   }
	     
	   else{
		   $("#bus_licence_number_fromschool1").prop("disabled", true);
	   }
		  

});
});
</script>



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
		        				data=data+"<option value=\""+obj[i].routes.id+":"+obj[i].routes.route_name+"\">"+obj[i].routes.route_name+"</option>";
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
				$("#bus_licence_number_fromschool").val(bus[1])
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
			        				data=data+"<option value=\""+obj[i].routes.id+":"+obj[i].routes.route_name+"\">"+obj[i].routes.route_name+"</option>";
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
						<li>Subscribers</li>
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

				<!-- Staff id validation -->

				<c:if test="${error=='staffidExistError'}">
						<div class="alert alert-danger fade in">
						<i class="icon-remove close" data-dismiss="alert"></i>
						<strong>Error! </strong> <span> Staff ID already Registered, Please provide unique StaffId</span>
						</div>
						<style>
							#home_error{
								
								border-color:red; 
							}
						</style>
				</c:if> 



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
								<form class="form-horizontal row-border" id="validate-4" action="updatestaffaction" method="post">

									<input type="hidden" name="id" value="${staff.id }">
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
														<label class="col-md-3 control-label">Name <span
															class="required">*</span></label>
														<div class="col-md-8 clearfix">
															<input type="text" name="name" minlength="3"
																class="form-control required"
																pattern="^\+?[A-Za-z\ ]+\*?$"
																title="Name must be characters with spaces minimum 3 characters"
																value="${staff.full_name }">
														</div>
													</div>
													
													<div class="form-group">
														<label class="col-md-3 control-label">Staff ID <span
															class="required">*</span></label>
														<div class="col-md-8 clearfix">
															<input type="text" name="staff_id"
																class="form-control required "
																value="${staff.staff_id }">
														</div>
													</div>
													
													<div class="form-group">
														<label class="col-md-6 control-label">Current RFID
															Number: <font color="sienna">${staff.rfid_number }</font>
															<input type="hidden" value="${staff.rfid_number }" name="old_rfid_number">
														</label>
														<br><br/><br/>	
														<label class="col-md-3 control-label">Select RFID
															Number <span class="required">*</span>
														</label>
														<div class="col-md-8 clearfix">
															<select name="rfid_number"
																class="col-md-12 select2 full-width-fix">
																<c:if test="${fn:length(rfids) eq 0}">
																	<option></option>
																</c:if>
																<c:if test="${fn:length(rfids) ne 0}">
																	<option></option>
																	<c:forEach items="${rfids }" var="rfid">
																		<option></option>
																		<option value="${rfid.rfid_number }">${rfid.rfid_number }</option>
																	</c:forEach>
																</c:if>
																
															</select> <label for="chosen1" generated="true"
																class="has-error help-block" style="display: none;"></label>
														</div>
													</div>
													<div class="form-group">
										<label class="col-md-3 control-label">Gender<span
											class="required">*</span></label>

										
											<div class="col-md-4 clearfix">
											<c:if test="${staff.gender == 'male' }">
												
													<label class="radio">
													<input type="radio" name="gender" class="required uniform" checked="checked" value="male" >
														 Male
													</label> 
													<label class="radio">
														<input 	type="radio" name="gender" class="uniform" value="female">
															Female
													</label>
											</c:if>
											<c:if test="${staff.gender == 'female' }">
												
													<label class="radio">
													<input type="radio" name="gender" class="required uniform"  value="male" >
														 Male
													</label> 
													<label class="radio">
														<input 	type="radio" name="gender" class="uniform" checked="checked" value="female">
															Female
													</label>
											</c:if>	
												 <label for="gender" class="has-error help-block"
													generated="true" style="display: none;"></label>
											</div>
													
										
										
									</div>		
									<div class="form-group">
										<label class="col-md-3 control-label">Mobile
											Number<span class="required">*</span>
										</label>
										<div class="col-md-8 clearfix">
											<input type="text" name="mobile"
												class="form-control required" rangelength="8, 12"
												pattern="^\+?[0-9\-]+\*?$"
												title="phone number must be numbers with symbols(+,-) and range should between 8-12 " value="${staff.mobile_number }">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Email<span
											class="required">*</span></label>
										<div class="col-md-8 clearfix">
											<input type="text" name="email"
												class="form-control required email" value="${staff.email }">
										</div>
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
														<label class="col-md-3 control-label">Street <span
															class="required">*</span></label>
														<div class="col-md-8 clearfix">
															<input type="text" name="street"
																class="form-control required" value="${address.street }">
														</div>
													</div>
													<div class="form-group">
														<label class="col-md-3 control-label">City <span
															class="required">*</span></label>
														<div class="col-md-8 clearfix">
															<input type="text" name="city"
																class="form-control required" value="${address.city }">
														</div>
													</div>
													<div class="form-group">
														<label class="col-md-3 control-label">State <span
															class="required">*</span></label>
														<div class="col-md-8 clearfix">
															<input type="text" name="state"
																class="form-control required" value="${address.state }">
														</div>
													</div>
													<div class="form-group">
														<label class="col-md-3 control-label">Postal <span
															class="required">*</span></label>
														<div class="col-md-8 clearfix">
															<input type="text" name="postal" class="form-control required" 
																pattern="^[a-zA-Z0-9]*$" title="postal must be alphanumeric" value="${address.postal }">
														</div>
													</div>
													<div class="form-group">
														<label class="col-md-3 control-label">Select
															Country<span class="required">*</span>
														</label>
														<div class="col-md-8 clearfix">
															<select name="country"
																class="col-md-12 select2 full-width-fix required"
																id="driver_rfid_number" style="border-color: grey;">
																<option selected="selected"><c:out
																		value="${address.country }"></c:out></option>
																<c:forEach items="${countries}" var="countries">
																	<option><c:out value="${countries}"></c:out></option>
																</c:forEach>
															</select> <label for="chosen1" generated="true"
																class="has-error help-block" style="display: none;"></label>
														</div>
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
													<!-- <h4>
														<i class="icon-reorder"></i> From Home
													</h4> -->
													<c:if test="${homeBus.bus_licence_number != null}">
													<h4>
														<i class="icon-reorder"></i> From Home
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														<label class="checkbox-inline">
															<input type="checkbox" class="uniform" id="take_bus_fromhome" checked="checked" name="checkBoxHome"> 
														</label>
													</h4>
													</c:if>
													
													
													<c:if test="${homeBus.bus_licence_number == null}">
													<h4>
														<i class="icon-reorder"></i> From Home
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														<label class="checkbox-inline">
															<input type="checkbox" class="uniform" id="take_bus_fromhome" name="checkBoxHome"> 
														</label>
													</h4>
													</c:if>
												</div>
												<div class="widget-content">
													
													<div class="form-group">
														<label class="col-md-6 control-label">Current Bus
															: <font color="sienna">${homeBus.bus_licence_number }</font>
															<input type="hidden" value="${homeBus.bus_licence_number  }" name="old_bus_licence_number_fromhome">
															<input type="hidden" name="old_bus_id_number_fromhome"  value="${homeBus.bus_id }">											
															
														</label>
														<br><br/>
														<label class="col-md-3 control-label">Select Bus
														</label>
														<c:if test="${homeBus.bus_licence_number == null}">
														<div class="col-md-8 clearfix">
															<select name="bus_licence_number_fromhome1" disabled="disabled"
																class="col-md-12 select2 full-width-fix "
																id="bus_licence_number_fromhome1" style="border-color: grey;">		
																<option></option>														
																<c:forEach items="${buses}" var="bus">
																	<option	value="<c:out value="${bus.bus_id }"></c:out>:<c:out value="${bus.bus_licence_number }"></c:out>"><c:out
																			value="${bus.bus_licence_number }"></c:out></option>
																</c:forEach>
															</select>	
															<input type="hidden" name="bus_licence_number_fromhome" id="bus_licence_number_fromhome" value="">			
															<input type="hidden" name="bus_id_number_fromhome" id="bus_id_number_fromhome" value="">											
														</div>
														</c:if>
														<c:if test="${homeBus.bus_licence_number != null}">
														<div class="col-md-8 clearfix">
															<select name="bus_licence_number_fromhome1"
																class="col-md-12 select2 full-width-fix "
																id="bus_licence_number_fromhome1" style="border-color: grey;">		
																<option></option>														
																<c:forEach items="${buses}" var="bus">
																	<option	value="<c:out value="${bus.bus_id }"></c:out>:<c:out value="${bus.bus_licence_number }"></c:out>"><c:out
																			value="${bus.bus_licence_number }"></c:out></option>
																</c:forEach>
															</select>	
															<input type="hidden" name="bus_licence_number_fromhome" id="bus_licence_number_fromhome" value="">			
															<input type="hidden" name="bus_id_number_fromhome" id="bus_id_number_fromhome" value="">											
														</div>
														</c:if>
													</div>
													<div class="form-group">
														<label class="col-md-7 control-label">Current Route
															: <font color="sienna">${homeRoute.route_name }</font>
															<input type="hidden" value="${homeRoute.route_name  }" name="old_route_name_fromhome">
															<input type="hidden" value="${homeRoute.id }" name="old_route_id_fromhome">
														</label>
														<br><br/>
														<label class="col-md-3 control-label">Select Route<span
															class="required">*</span>
														</label>
														<div class="col-md-8 clearfix">
															<select name="route_name_fromhome1" disabled="disabled"
																class="col-md-12 select2 full-width-fix required"
																id="route_name_fromhome1" style="border-color: grey;">
																<option></option>

															</select>
															<input type="hidden" name="route_name_fromhome" value="" id="route_name_fromhome"> 
															<input type="hidden" name="route_id_fromhome" value="" id="route_id_fromhome">
														</div>
													</div>		
													<div class="form-group">
														<label class="col-md-6 control-label">Current Trip
															: <font color="sienna">${homeTrip.trip_name }</font>
															<input type="hidden" value="${homeTrip.trip_name }" name="old_trip_name_fromhome">
															<input type="hidden" value="${homeTrip.id }" name="old_trip_id_fromhome">
														</label>
														<br><br/>
														<label class="col-md-3 control-label">Select Trip<span
															class="required">*</span>
														</label>
														<div class="col-md-8 clearfix">
															<select name="trip_name_fromhome1" disabled="disabled"
																class="col-md-12 select2 full-width-fix required"
																id="trip_name_fromhome1" style="border-color: grey;">
																<option></option>

															</select>
														   <input type="hidden" name="trip_name_fromhome" id="trip_name_fromhome" value="">
															
															<input type="hidden" name="trip_id_fromhome" id="trip_id_fromhome" value="">
														</div>
													</div>				

													<div class="form-group">
														<label class="col-md-6 control-label">Current Stop
															: <font color="sienna">${homeStop.stop_name }</font>
															<input type="hidden" value="${homeStop.stop_name }" name="old_stop_name_fromhome">
															<input type="hidden" value="${homeStop.id }" name="old_stop_id_fromhome">
														</label>
														<br><br/>
														<label class="col-md-3 control-label">Stop<span
															class="required">*</span>
														</label>
														<div class="col-md-8 clearfix">
															<select name="stop_id_fromhome1" disabled="disabled"
																class="col-md-12 select2 full-width-fix required"
																id="stop_id_fromhome1" style="border-color: grey;">																
																<option></option>

															</select> 
															<input type="hidden" id="stop_name_fromhome" name="stop_name_fromhome" value=""> 
															<input type="hidden" id="stop_id_fromhome" name="stop_id_fromhome" value=""> 
														</div>
													</div>
																						
												</div>
											</div>
											<!-- /From Home -->
										</div>

										<!--=== Validation Example 2 ===-->
										<div class="col-md-6">
											<div class="widget box">
												<div class="widget-header">
													<!-- <h4>
														<i class="icon-reorder"></i> From School
													</h4> -->
													<c:if test="${schoolBus.bus_licence_number !=null}">
													<h4>
														<i class="icon-reorder"></i> From School
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														<label class="checkbox-inline">
															<input type="checkbox" class="uniform" id="take_bus_fromschool" checked="checked" name="checkBoxSchool"> 
														</label>
													</h4>
													</c:if>
													
													<c:if test="${schoolBus.bus_licence_number ==null}">
													<h4>
														<i class="icon-reorder"></i> From School
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														<label class="checkbox-inline">
															<input type="checkbox" class="uniform" id="take_bus_fromschool" name="checkBoxSchool"> 
														</label>
													</h4>
													</c:if>
												</div>
												<%-- --%>
												<div class="widget-content">
													
													<div class="form-group">
														<label class="col-md-6 control-label">Current Bus
															: <font color="sienna">${schoolBus.bus_licence_number }</font>
															<input type="hidden" value="${schoolBus.bus_licence_number }" name="old_bus_licence_number_fromschool">
															<input type="hidden" name="old_bus_id_number_fromschool"  value="${schoolBus.bus_id }">											
															
														</label>
														<br><br/>
														<label class="col-md-3 control-label">Select Bus
														</label>
														<c:if test="${schoolBus.bus_licence_number ==null}">
														<div class="col-md-8 clearfix">
															<select name="busLicence_fromschool1" disabled="disabled"
																class="col-md-12 select2 full-width-fix"
																id="bus_licence_number_fromschool1" style="border-color: grey;">
																<option></option>
																<c:forEach items="${buses}" var="bus">
																	<option	value="<c:out value="${bus.bus_id }"></c:out>:<c:out value="${bus.bus_licence_number }"></c:out>"><c:out
																			value="${bus.bus_licence_number }"></c:out></option>
																</c:forEach>
															</select>
															<input type="hidden" id="bus_id_number_fromschool" name="bus_id_number_fromschool" value="">
															<input type="hidden" id="bus_licence_number_fromschool" name="bus_licence_number_fromschool" value="">
														</div>
														</c:if>
														<c:if test="${schoolBus.bus_licence_number !=null}">
														<div class="col-md-8 clearfix">
															<select name="busLicence_fromschool1"
																class="col-md-12 select2 full-width-fix"
																id="bus_licence_number_fromschool1" style="border-color: grey;">
																<option></option>
																<c:forEach items="${buses}" var="bus">
																	<option	value="<c:out value="${bus.bus_id }"></c:out>:<c:out value="${bus.bus_licence_number }"></c:out>"><c:out
																			value="${bus.bus_licence_number }"></c:out></option>
																</c:forEach>
															</select>
															<input type="hidden" id="bus_id_number_fromschool" name="bus_id_number_fromschool" value="">
															<input type="hidden" id="bus_licence_number_fromschool" name="bus_licence_number_fromschool" value="">
														</div>
														</c:if>
													</div>
													<div class="form-group">
														<label class="col-md-7 control-label">Current Route
															: <font color="sienna">${schoolRoute.route_name }</font>
															<input type="hidden" value="${schoolRoute.route_name }" name="old_route_name_fromschool">
															<input type="hidden" value="${schoolRoute.id }" name="old_route_id_fromschool">
														</label>
														<br><br/>
														<label class="col-md-3 control-label">Select Route<span
															class="required">*</span>
														</label>
														<div class="col-md-8 clearfix">
															<select name="route_name_fromschool1" disabled="disabled"
																class="col-md-12 select2 full-width-fix required"
																id="route_name_fromschool1" style="border-color: grey;">
																<option></option>

															</select>
															<input type="hidden" name="route_name_fromschool" value="" id="route_name_fromschool">
															
															<input type="hidden" name="route_id_fromschool" value="" id="route_id_fromschool">
														</div>
													</div>		
													<div class="form-group">
														<label class="col-md-6 control-label">Current Trip
															: <font color="sienna">${schoolTrip.trip_name }</font>
															<input type="hidden" value="${schoolTrip.trip_name }" name="old_trip_name_fromschool">
															<input type="hidden" value="${schoolTrip.id}" name="old_trip_id_fromschool">
														</label>
														<br><br/>
														<label class="col-md-3 control-label">Select Trip<span
															class="required">*</span>
														</label>
														<div class="col-md-8 clearfix">
															<select name="trip_name_fromschool1"
																class="col-md-12 select2 full-width-fix required" disabled="disabled"
																id="trip_name_fromschool1" style="border-color: grey;">
																<option></option>
															</select> 
															<input type="hidden" name="trip_name_fromschool" id="trip_name_fromschool" value="">
															
															<input type="hidden" name="trip_id_fromschool" id="trip_id_fromschool" value="">
														</div>
													</div>			

													<div class="form-group">
														<label class="col-md-6 control-label">Current Stop
															: <font color="sienna">${schoolStop.stop_name}</font>
															<input type="hidden" value="${schoolStop.stop_name }" name="old_stop_name_fromschool">
															<input type="hidden" value="${schoolStop.id }" name="old_stop_id_fromschool">
														</label>
														<br><br/>
														<label class="col-md-3 control-label">Select Stop<span
															class="required">*</span>
														</label>
														<div class="col-md-8 clearfix">
															<select name="stop_name_fromschool1"
																class="col-md-12 select2 full-width-fix required" disabled="disabled"
																id="stop_name_fromschool1" style="border-color: grey;">
																	<option></option>
															</select> 
															<input type="hidden" id="stop_name_fromschool" name="stop_name_fromschool" value=""> 
															<input type="hidden" id="stop_id_fromschool" name="stop_id_fromschool" value=""> 
														</div>
													</div>
																							
												</div>
											</div>
										</div>
										<!-- /Validation Example 2 -->
									</div>
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
												            <input type="checkbox" class="uniform" name="email_all" checked="checked" >
												        </c:when>
												       
												        <c:otherwise>
												            <input type="checkbox" class="uniform" name="email_all"  >
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
												            <input type="checkbox" class="uniform" name="email_noshow" checked="checked" >
												        </c:when>
												       
												        <c:otherwise>
												            <input type="checkbox" class="uniform" name="email_noshow"  >
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
												            <input type="checkbox" class="uniform" name="email_late" checked="checked" >
												        </c:when>
												       
												        <c:otherwise>
												            <input type="checkbox" class="uniform" name="email_late"  >
												        </c:otherwise>
												    </c:choose>
												 </td>
												
											</tr>
											<tr>
												<td class="checkbox-column">Irregularities &nbsp;&nbsp;&nbsp;
													<c:choose>
												        <c:when test="${email.irregularities == 'on' }">
												            <input type="checkbox" class="uniform" name="email_irregularities" checked="checked" >
												        </c:when>
												       
												        <c:otherwise>
												            <input type="checkbox" class="uniform" name="email_irregularities" >
												        </c:otherwise>
												    </c:choose>
												 </td>
												
											</tr>
											<tr>
												<td class="checkbox-column">
													Regularities&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<c:choose>
												        <c:when test="${email.regularities == 'on' }">
												            <input type="checkbox" class="uniform" name="email_regularities" checked="checked" >
												        </c:when>
												       
												        <c:otherwise>
												            <input type="checkbox" class="uniform" name="email_regularities" >
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
												            <input type="checkbox" class="uniform" name="sms_all" checked="checked" >
												        </c:when>
												       
												        <c:otherwise>
												            <input type="checkbox" class="uniform" name="sms_all" >
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
												            <input type="checkbox" class="uniform" name="sms_noshow" checked="checked" >
												        </c:when>
												       
												        <c:otherwise>
												            <input type="checkbox" class="uniform" name="sms_noshow" >
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
												            <input type="checkbox" class="uniform" name="sms_late" checked="checked" >
												        </c:when>
												       
												        <c:otherwise>
												            <input type="checkbox" class="uniform" name="sms_late"  >
												        </c:otherwise>
												    </c:choose>
												 </td>
												
											</tr>
											<tr>
												<td class="checkbox-column">Irregularities &nbsp;&nbsp;&nbsp;
													<c:choose>
												        <c:when test="${ sms.irregularities == 'on' }">
												            <input type="checkbox" class="uniform" name="sms_irregularities" checked="checked" >
												        </c:when>
												       
												        <c:otherwise>
												            <input type="checkbox" class="uniform" name="sms_irregularities"  >
												        </c:otherwise>
												    </c:choose>
												 </td>
												
											</tr>
											<tr>
												<td class="checkbox-column">
													Regularities&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<c:choose>
												        <c:when test="${sms.regularities == 'on' }">
												            <input type="checkbox" class="uniform" name="sms_regularities" checked="checked" >
												        </c:when>
												       
												        <c:otherwise>
												            <input type="checkbox" class="uniform" name="sms_regularities"  >
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

									<div class="alert alert-danger fade in" id="input_errors"
										style="display: none;">
										<strong>Error!</strong> Please fill all the fields which are
										Marked *
									</div>
									<div class="form-actions">
										<a type="button" class="btn btn-default"
											href="/sts/school_admin/persons/staff">Cancel</a> <input
											type="submit" value="Update"
											class="btn btn-primary pull-right" id="add_new_driver_action">
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