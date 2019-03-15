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
		        	
		        	url:"getAllRoutesByBusId",
		        	type:"POST",
		        	data:"bus_licence_number="+bus[1]+"&type=Pick Up",
		        	success:function(result){
		        	
		        		 var obj = JSON.parse( result );
		        		//alert(obj);
		        		$("#route_name_fromhome1").empty();
		        		var data="<option></option>";
		        		for(var i=0;i<obj.length;i++){
		        			//alert(obj[i].stop_name);
		        			//alert(obj[i].route_id+":"+obj[i].route_name);
		        			if(obj[i].routes.route_name!="")
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
				//alert(route);
				$("#route_id_fromhome").val(route[0]);
				$("#route_name_fromhome").val(route[1]);
		        $.ajax({
		        	
		        	url:"getAllTripsByBusId",
		        	type:"POST",
		        	data:"route_id="+route[0]+"&type=Pick Up",
		        	success:function(result){
		        		//alert(result);
		        		 var obj = JSON.parse( result );
		        		$("#trip_name_fromhome1").empty();
		        		var data="<option></option>";
		        		var trip_count = 0;
		        		for(var i=0;i<obj.length;i++){
		        			//alert(obj[i].seats_filled);
		        			
		        			if(obj[i].seats_filled == parseInt(obj[i].bus.bus_capacity))
		        				trip_count++;	
		        			else
		        				data=data+"<option value=\""+obj[i].id+":"+obj[i].trip_name+"\">"+obj[i].trip_name+"</option>";

		        		}
		        		//alert(trip_count);
		        		if(trip_count!=0){
		        			alert("Route selected contains no trips or all the seats are filled up");
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
		        	
		        	url:"getAllStopsByTripId",
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
		        	
		        	url:"getAllRoutesByBusId",
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
		        	
		        	url:"getAllTripsByBusId",
		        	type:"POST",
		        	data:"route_id="+route[0]+"&type=Drop Off",
		        	success:function(result){
		        		//alert(result);
		        		 var obj = JSON.parse( result );
		        		$("#trip_name_fromschool1").empty();
		        		var data="<option></option>";
		        		var trip_count =0;
		        		for(var i=0;i<obj.length;i++){
		        			//alert(obj[i].seats_filled);
		        			
		        			if(obj[i].seats_filled == parseInt(obj[i].bus.bus_capacity))
		        				trip_count++;	
		        			else
		        				data=data+"<option value=\""+obj[i].id+":"+obj[i].trip_name+"\">"+obj[i].trip_name+"</option>";

		        		}
		        		//alert(trip_count);
		        		if(trip_count!=0){
		        			alert("Route selected contains no trips or all the seats are filled up");
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
		        	
		        	url:"getAllStopsByTripId",
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
    
    $('#take_bus_fromhome').bind('change', function () {

    	   if ($(this).is(':checked')){
    		   
    		   $("#bus_licence_number_fromhome1").prop("disabled", false);
    		   
    	   }
    	     
    	   else{
    		   $("#bus_licence_number_fromhome1").prop("disabled", true);
    		   $("#stop_name_fromhome1").prop("disabled", true);
    		   $("#route_name_fromhome1").prop("disabled", true);
    		   $("#trip_name_fromhome1").prop("disabled", true);
    		   $("#stop_name_fromhome1").empty();
    		   $("#stop_name_fromhome1").empty();
    		   $("#route_name_fromhome1").empty();
    		   $("#trip_name_fromhome1").empty();
    		   $("#bus_id_number_fromhome").val("");
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
						<li>Students</li>
						<li>Add Student</li>
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
						<h3>Student</h3>
						<span>Add Student</span>
					</div>
					<br>
					<div class="col-sm-4 col-md-1"></div>
				</div>


				<!-- condition msg for gr no -->
				<c:if test="${error=='studentidExistError'}">
						<div class="alert alert-danger fade in">
						<i class="icon-remove close" data-dismiss="alert"></i>
						<strong>Error! </strong> <span> This Student ID already Registered, Please try another StudentId</span>
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
									<i class="icon-reorder"></i>Student Form
								</h4>
							</div>
							<div class="widget-content">
								<form class="form-horizontal row-border" id="validate-4" action="addstudentaction" method="post">


									<!--=== Page Content ===-->
									<div class="row">
										<!--=== Validation Example 1 ===-->
										<div class="col-md-6">
											<div class="widget box">
												<div class="widget-header">
													<h4>
														<i class="icon-reorder"></i> Student Details
													</h4>
												</div>
												<div class="widget-content">
													<div class="form-group">
														<label class="col-md-3 control-label">First Name <span
															class="required">*</span></label>
														<div class="col-md-8 clearfix">
															<input type="text" name="first_name" minlength="1"
																class="form-control required"
																pattern="^\+?[A-Za-z\ ]+\*?$"
																title="Name must be characters with spaces"
																value="${studentEntity.first_name}">
														</div>
													</div>
													<div class="form-group">
														<label class="col-md-3 control-label">Last Name <span
															class="required">*</span></label>
														<div class="col-md-8 clearfix">
															<input type="text" name="last_name" minlength="1"
																class="form-control required"
																pattern="^\+?[A-Za-z\ ]+\*?$"
																title="Name must be characters with spaces"
																value="${studentEntity.last_name}">
														</div>
													</div>
													<div class="form-group">
														<label class="col-md-3 control-label">Student GR Number <span
															class="required">*</span></label>
														<div class="col-md-8 clearfix">
															<input type="text" name="student_id"
																class="form-control required "
																value="${studentEntity.student_id}">
														</div>
													</div>
													
													<div class="form-group">
														<label class="col-md-3 control-label">Select RFID
															Number <span class="required">*</span>
														</label>
														<div class="col-md-8 clearfix">
															<select name="rfid_number"
																class="col-md-12 select2 full-width-fix required">
																<c:if test="${fn:length(rfids) eq 0}">
																	<option></option>
																</c:if>
																<c:if test="${fn:length(rfids) ne 0}">
																	<c:forEach items="${rfids }" var="rfid">

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

										
											<div class="col-md-8 clearfix">
												<label class="radio">
													<input type="radio" name="gender" class="required uniform" value="male" >
														 Male
												</label> 
												<label class="radio">
													<input 	type="radio" name="gender" class="uniform" value="female">
														Female
												</label>
												 <label for="gender" class="has-error help-block"
													generated="true" style="display: none;"></label>
											</div>
									</div>		
									 
									
									<div class="form-group">
														<label class="col-md-3 control-label">Student Grade <span
															class="required">*</span></label>
														<div class="col-md-8 clearfix">
															<input type="text" name="student_grade"
																class="form-control required "
																value="${studentEntity.student_grade}">
														</div>
													</div>							
												</div>
											</div>
											<!-- /Student Details  -->
										</div>

										<!--=== Validation Example 2 ===-->
										<div class="col-md-6">
											<div class="widget box">
												<div class="widget-header">
													<h4>
														<i class="icon-reorder"></i> Parent Contact Details
													</h4>
												</div>
												<div class="widget-content">
													<div class="form-group">
										<label class="col-md-3 control-label">First Name<span
											class="required">*</span></label>
										<div class="col-md-8 clearfix">
											<input type="text" name="parent_firstname" minlength="1"
												class="form-control required" pattern="^\+?[A-Za-z\ ]+\*?$"
												title="Name must be characters with spaces" value="${studentEntity.parent_firstname}">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Last Name<span
											class="required">*</span></label>
										<div class="col-md-8 clearfix">
											<input type="text" name="parent_lastname" minlength="1"
												class="form-control required" pattern="^\+?[A-Za-z\ ]+\*?$"
												title="Name must be characters with spaces" value="${studentEntity.parent_lastname}">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Person Mobile
											Number<span class="required">*</span>
										</label>
										<div class="col-md-8 clearfix">
											<input type="text" name="parent_mobile"
												class="form-control required" rangelength="8, 12"
												pattern="^\+?[0-9\-]+\*?$"
												title="phone number must be numbers with symbols(+,-) and range should between 8-12 " value="${studentEntity.parent_mobile }">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Parent Email<span
											class="required">*</span></label>
										<div class="col-md-8 clearfix">
											<input type="text" name="parent_email"
												class="form-control required email" value="${studentEntity.parent_email}">
										</div>
									</div>

												
												</div>
											</div>
										</div>
										<!-- /Parent Contact Details -->
									</div>
									<!-- first row completed -->
									<c:if test="${message== 'fromHome-error' }">

										<div class="alert alert-danger fade in">
											<i class="icon-remove close" data-dismiss="alert"></i> <strong>Error!</strong>
											There are no seats available in from home route: <b>${routeFromHome}</b>
										</div>

									</c:if>
									<c:if test="${message== 'fromSchool-error' }">

										<div class="alert alert-danger fade in">
											<i class="icon-remove close" data-dismiss="alert"></i> <strong>Error!</strong>
											There are no seats available in from school route: <b>${routeFromSchool}</b>
										</div>

									</c:if>
									<c:if test="${message== 'oneSeat-error' }">

										<div class="alert alert-danger fade in">
											<i class="icon-remove close" data-dismiss="alert"></i> <strong>Error!</strong>
											There is only one seat available in route: <b>${routeSame}</b>
										</div>

									</c:if>
									<c:if test="${message== 'bothEmpty-error' }">

										<div class="alert alert-danger fade in">
											<i class="" data-dismiss="alert"></i> <strong>Error!</strong>
											From-Home and From-School both should not be empty
										</div>

									</c:if>
									<div class="row">
										<!--=== Validation Example 1 ===-->
										<div class="col-md-6">
											<div class="widget box">
												<div class="widget-header">
													<h4>
														<i class="icon-reorder"></i> From Home
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														<label class="checkbox-inline">
															<input type="checkbox" class="uniform" id="take_bus_fromhome"> 
														</label>
													</h4>
												</div>
												<div class="widget-content">
													
													<div class="form-group">
														<label class="col-md-3 control-label">Select Bus<span
															class="required">*</span>
														</label>
														<div class="col-md-8 clearfix">
															<select name="bus_licence_number_fromhome1" disabled="disabled"
																class="col-md-12 select2 full-width-fix required"
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
													</div>
													<div class="form-group">
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
													<h4>
														<i class="icon-reorder"></i> From School
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														<label class="checkbox-inline">
															<input type="checkbox" class="uniform" id="take_bus_fromschool"> 
														</label>
													</h4>
												</div>
												<%-- --%>
												<div class="widget-content">
													
													<div class="form-group">
														<label class="col-md-3 control-label">Select Bus<span
															class="required">*</span>
														</label>
														<div class="col-md-8 clearfix">
															<select name="busLicence_fromschool1" disabled="disabled"
																class="col-md-12 select2 full-width-fix required"
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
													</div>
													<div class="form-group">
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
					<!--=== Validation Example 1 ===-->
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
																class="form-control required" value="${studentEntity.street}">
														</div>
													</div>
													<div class="form-group">
														<label class="col-md-3 control-label">City <span
															class="required">*</span></label>
														<div class="col-md-8 clearfix">
															<input type="text" name="city"
																class="form-control required" value="${studentEntity.city}">
														</div>
													</div>
													<div class="form-group">
														<label class="col-md-3 control-label">State <span
															class="required">*</span></label>
														<div class="col-md-8 clearfix">
															<input type="text" name="state"
																class="form-control required" value="${studentEntity.state}">
														</div>
													</div>
													<div class="form-group">
														<label class="col-md-3 control-label">Postal <span
															class="required">*</span></label>
														<div class="col-md-8 clearfix">
															<input type="text" name="postal"
																class="form-control required" pattern="^[a-zA-Z0-9]*$"
												title="postal must be alphanumeric" value="${studentEntity.postal}">
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
																		value="${student.country }"></c:out></option>
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
												<th >
													Email alerts 																										
												</th>
												
											</tr>
											<tr>
												<th class="checkbox-column">
													All&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;														
													<input type="checkbox" class="uniform" name="email_all" >
												</th>
												
											</tr>
											
										</thead>
										<tbody>
										
											
											
											<tr>
												<td class="checkbox-column" >
													No Show&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<input type="checkbox" class="uniform" name="email_noshow" id="checkbox1"> 
												</td>
												
											</tr>
											<tr>
												<td class="checkbox-column">
													Late&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<input type="checkbox" class="uniform" name="email_late" id="checkbox1">
												 </td>
												
											</tr>
											<tr>
												<td class="checkbox-column">Irregularities &nbsp;&nbsp;
													<input type="checkbox" class="uniform" name="email_irregularities" id="checkbox1">
												 </td>
												
											</tr>
											<tr>
												<td class="checkbox-column">
													Regularities&nbsp;
													&nbsp;&nbsp;&nbsp;
													<input type="checkbox" class="uniform" name="email_regularities" id="checkbox1">
												 </td>
												
											</tr>
										</tbody>
									</table>					
									</div>
									<div class="col-md-6">
								
										<table class="table table-striped table-bordered table-checkable table-highlight-head table-no-inner-border table-hover">
									
										<thead>
											<tr>
												<th>
													Sms alerts																											
												</th>
												
											</tr>
											<tr>
												<th class="checkbox-column">
													All	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;													
													<input type="checkbox" class="uniform" name="sms_all" >
												</th>
												
											</tr>
											
										</thead>
										<tbody>
										
											
											<tr>
												<td class="checkbox-column" >
													No Show&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<input type="checkbox" class="uniform" name="sms_noshow" id="checkbox1"> 
												</td>
												
											</tr>
											<tr>
												<td class="checkbox-column">
													Late&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<input type="checkbox" class="uniform" name="sms_late" id="checkbox1">
												 </td>
												
											</tr>
											<tr>
												<td class="checkbox-column">Irregularities &nbsp;&nbsp;&nbsp;
													<input type="checkbox" class="uniform" name="sms_irregularities" id="checkbox1">
												 </td>
												
											</tr>
											<tr>
												<td class="checkbox-column">
													Regularities&nbsp;
													&nbsp;&nbsp;&nbsp;&nbsp;
													<input type="checkbox" class="uniform" name="sms_regularities" id="checkbox1">
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
											href="/sts/school_admin/persons/students">Cancel</a> <input
											type="submit" value="Add"
											class="btn btn-primary pull-right" id="add_new_driver_action">
									</div>
								</form>
							</div>
						</div>
						<!-- /Validation Example 1 -->
					</div>


				</div>

			</div>
</body>
</html>