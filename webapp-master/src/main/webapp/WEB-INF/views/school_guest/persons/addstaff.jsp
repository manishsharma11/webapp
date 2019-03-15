<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
	
	 $("#bus_id").change(function(){
			$("#loading").show();
	        $.ajax({
	        	
	        	url:"getAllStopsByBusId",
	        	type:"POST",
	        	data:"route_id="+$(this).val(),
	        	success:function(result){
	        		//alert(result);
	        		var obj = JSON.parse( result );
	        		$("#stop_id").empty();
	        		var data="<option></option>";
	        		for(var i=0;i<obj.length;i++){
	        			//alert(obj[i].stop_name);
	        			data=data+"<option value="+obj[i].id+">"+obj[i].stop_name+"</option>";
	        		}
	        		$("#stop_id").append(data);
	        		$("#loading").hide();
	        	}
	        });
	    });
 });

</script>


<!-- Include CSS files -->
<%@ include file="../jsp-includes/css_files.jsp"%>

<!-- Include JS files -->
<%@ include file="../jsp-includes/js_files.jsp"%>

<script type="text/javascript">
	
		
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
						<li>Add New Staff</li>
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
						<span>Add Staff</span>
					</div>
					<br>
					<div class="col-sm-4 col-md-1"></div>



				</div>

				<div class="row" id="driver_form_add">
					<!--=== Validation Example 1 ===-->
					<div class="col-md-8">
						<div class="widget box">
							<div class="widget-header">
								<h4>
									<i class="icon-reorder"></i>Staff Form
								</h4>
							</div>
							<div class="widget-content">
								<form class="form-horizontal row-border" id="validate-4"
									action="addstaffaction" method="post">

									<div class="form-group">
										<label class="col-md-3 control-label">Select RFID<span
											class="required">*</span></label>
										<div class="col-md-9 clearfix">
											<select name="staff_rfid"
												class="col-md-12 select2 full-width-fix required"
												id="staff_rfid" style="border-color: grey;">
												<option></option>
												<c:forEach items="${rfids }" var="rfid">

													<option><c:out value="${rfid.rfid_number }"></c:out></option>

												</c:forEach>
											</select> <label for="chosen1" generated="true"
												class="has-error help-block" style="display: none;"></label>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Select Bus<span
											class="required">*</span></label>
										<div class="col-md-9 clearfix">
											<select name="bus_id"
												class="col-md-12 select2 full-width-fix required"
												id="bus_id" style="border-color: grey;">
												<option></option>
												<c:forEach items="${buses }" var="bus">

													<option value="<c:out value="${bus.route_id }"></c:out>"><c:out
															value="${bus.bus_licence_number }"></c:out></option>

												</c:forEach>
											</select> <label for="chosen1" generated="true"
												class="has-error help-block" style="display: none;"></label>
										</div>

										<span style="color: sienna; display: none;" id="loading">
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											Fetching stops list from database please wait... </span> <label
											class="col-md-3 control-label">Select Stop<span
											class="required">*</span></label>
										<div class="col-md-9 clearfix">
											<select name="stop_id"
												class="col-md-12 select2 full-width-fix required"
												id="stop_id" style="border-color: grey;">
												<option></option>

											</select> <label for="chosen2" generated="true"
												class="has-error help-block" style="display: none;"></label>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Staff ID<span
											class="required">*</span></label>
										<div class="col-md-9">
											<input type="text" name="staff_id"
												class="form-control required" id="driver_name">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Staff Name<span
											class="required">*</span></label>
										<div class="col-md-9">
											<input type="text" name="staff_name"
												class="form-control required" id="driver_name" pattern="^\+?[A-Za-z\ ]+\*?$" title="Name must be characters with spaces">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Staff Mobile [8-12]
											Number<span class="required">*</span>
										</label>
										<div class="col-md-9">
											<input type="text" name="staff_mobile"
												class="form-control required" id="driver_name"  rangelength="8, 12" pattern="^\+?[0-9\-]+\*?$" title="phone number must be numbers with symbols(+,-)">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Staff E-mail<span
											class="required">*</span></label>
										<div class="col-md-9">
											<input type="text" name="staff_email"
												class="form-control required email" id="driver_name">
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">Street<span
											class="required">*</span></label>
										<div class="col-md-9">
											<input type="text" name="street"
												class="form-control required" id="street">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">City<span
											class="required">*</span></label>
										<div class="col-md-9">
											<input type="text" name="city"
												class="form-control required" id="city">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">State<span
											class="required">*</span></label>
										<div class="col-md-9">
											<input type="text" name="state"
												class="form-control required" id="state">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Zip<span
											class="required">*</span></label>
										<div class="col-md-9">
											<input type="text" name="zip"
												class="form-control required" id="zip">
										</div>
									</div>
									<!-- <div class="form-group">
										<label class="col-md-3 control-label">Country<span
											class="required">*</span></label>
										<div class="col-md-9">
											<input type="text" name="country"
												class="form-control required" id="country">
										</div>
									</div> -->
									<div class="form-group">
										<label class="col-md-3 control-label">Select Country<span
											class="required">*</span></label>
										<div class="col-md-9 clearfix">
											<select name="country"
												class="col-md-12 select2 full-width-fix required"
												id="driver_rfid_number" style="border-color: grey;">
												<option></option>
												<c:forEach items="${countries}" var="countries">

													<option><c:out value="${countries}"></c:out></option>

												</c:forEach>
											</select> <label for="chosen1" generated="true"
												class="has-error help-block" style="display: none;"></label>
										</div>
									</div>


									<div class="alert alert-danger fade in" id="input_errors"
										style="display: none;">

										<strong>Error!</strong> Please fill all the fields which are
										marked *
									</div>
									<div class="form-actions">
										<a type="button" class="btn btn-default"
											href="/sts/school_admin/persons/staff">Cancel</a> <input
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