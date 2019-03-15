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
	src="<c:url value="/resources/jquery-1.11.0.min.js" />">
</script>
<!-- Include CSS files -->
<%@ include file="../jsp-includes/css_files.jsp"%>

<!-- Include JS files -->
<%@ include file="../jsp-includes/js_files.jsp"%>

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
						<li>Buses</li>
						<li>Stops</li>
						<li>Add New Stop</li>
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
						<h3>Bus Stops</h3>
						<span>Add Stop</span>
					</div>
					<br>
					<div class="col-sm-4 col-md-1"></div>
				</div>
				<div class="row" id="driver_form_add">
					<!--=== Validation Example 1 ===-->
					<div class="col-md-7">
						<div class="widget box">
							<div class="widget-header">
								<h4>
									<i class="icon-reorder"></i>Add New Bus Stop Form
								</h4>
							</div>
							<div class="widget-content">
								<form class="form-horizontal row-border" id="validate-4" action="addstopaction" method="post">

									<div class="form-group">
										<label class="col-md-3 control-label">Select Bus Stop Priority:<span
											class="required">*</span></label>
										<div class="col-md-9 clearfix">
											<select name="stop_number" class="col-md-8 select2 full-width-fix required">
												<option></option>
												<c:if test="${stops == 0 }">
													 <option>1</option>
												</c:if>
												<c:if test="${stops != 0 }">
													 <c:forEach begin="1" end="${stops+1 }" varStatus="num">
													   <option> ${num.index}</option>
													</c:forEach>
												</c:if>
												
												
											</select> <label for="chosen1" generated="true"
												class="has-error help-block" style="display: none;"></label>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Stop Name<span
											class="required">*</span></label>
										<div class="col-md-9">
											<input type="text" name="stop_name" 	class="form-control required" id="driver_name">
											<input type="hidden" name="bus_id" 	class="form-control required" value="<c:out value="${bus.id }"></c:out>">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Expected Time Towards School<span class="required">*</span></label>
										<div class="col-md-3">
											<input type="text" name="expected_time_hour_towards_school" class="form-control required digits"  placeholder="hour" id="h1">
											
										</div>
										<div class="col-md-3">
											<input type="text" name="expected_time_min_towards_school" class="form-control required digits"  placeholder="minute" id="m1">
											
										</div>
										<div class="col-md-3">
											<select name="expected_time_ampm_towards_school" class="col-md-8 select2 full-width-fix required" placeholder="am/pm" id="ap1">
												<option></option>
												<option>AM</option>
												<option>PM</option>
											</select>
											
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Expected Time Towards Home<span class="required">*</span></label>
										<div class="col-md-3">
											<input type="text" name="expected_time_hour_towards_home" class="form-control required digits"  placeholder="hour" id="h2">
											
										</div>
										<div class="col-md-3">
											<input type="text" name="expected_time_min_towards_home" class="form-control required digits"  placeholder="minute" id="m2">
											
										</div>
										<div class="col-md-3">
											<select name="expected_time_ampm_towards_home" class="col-md-8 select2 full-width-fix required" placeholder="am/pm" id="ap2">
												<option></option>
												<option>AM</option>
												<option>PM</option>
											</select>
											
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Latitude<span class="required">*</span></label>
										<div class="col-md-9">
											<input type="text" name="latitude" class="form-control required">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Longitude<span class="required">*</span></label>
										<div class="col-md-9">
											<input type="text" name="longitude" class="form-control required">
										</div>
									</div>
									<div class="alert alert-danger fade in" id="input_errors"
										style="display: none;">

										<strong>Error!</strong> Please fill all the fields which are
										marked *
									</div>
									<div class="form-actions">
										<a type="button" class="btn btn-default"
											href="/sts/school_admin/bus/stops?bus_id=<c:out value="${bus.id}"></c:out>">Cancel</a>
											 <input	type="submit" value="Add Stop" class="btn btn-primary pull-right" id="add_new_driver_action" >
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