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
						<li>Buses Price Fares</li>


					</ul>

					<ul class="crumb-buttons">


						<li><a href="#"> <i class="icon-calendar"></i>&nbsp;${date}
						</a></li>
					</ul>
				</div>
				<!-- /Breadcrumbs line -->

				<!--=== Page Header ===-->
				<div class="page-header"></div>
				<c:if test="${message== 'bus_exists' }">

					<div class="alert alert-danger fade in">
						<i class="icon-remove close" data-dismiss="alert"></i> <strong>Error!</strong>
						Bus already Available with Bus License Number: <b>${bus_licence_number }</b>
					</div>

				</c:if>


				<div class="row" id="driver_form_add">
					<!--=== Validation Example 1 ===-->
					<div class="col-md-6">
						<div class="widget box">
							<div class="widget-header">
								<h4>
									<i class="icon-reorder"></i>Add New
								</h4>
							</div>
							<div class="widget-content">
								<form class="form-horizontal row-border" id="validate-4"
									action="addaction" method="post">

									<div class="form-group">
										<label class="col-md-3 control-label">Select Source
											Stop<span class="required">*</span>
										</label>
										<div class="col-md-9 clearfix">
											<select name="source_stop_id"
												class="col-md-9 select2 full-width-fix required"
												id="driver_id_name" style="border-color: grey;">
												<option></option>

												<c:forEach items="${stops}" var="stop">

													<option value="<c:out value="${stop.id}"></c:out>"><c:out
															value="${stop.stop_name}"></c:out></option>

												</c:forEach>
											</select> <label for="chosen1" generated="true"
												class="has-error help-block" style="display: none;"></label>
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-3 control-label">Select Dest Stop<span
											class="required">*</span>
										</label>
										<div class="col-md-9 clearfix">
											<select name="dest_stop_id"
												class="col-md-9 select2 full-width-fix required"
												id="dest_stop_id" style="border-color: grey;">
												<option></option>

												<c:forEach items="${stops}" var="stop">

													<option value="<c:out value="${stop.id}"></c:out>"><c:out
															value="${stop.stop_name}"></c:out></option>

												</c:forEach>
											</select> <label for="chosen1" generated="true"
												class="has-error help-block" style="display: none;"></label>
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">Bus Price <span
											class="required">*</span>
										</label>
										<div class="col-md-9">
											<input type="text" name="bus_fare" value=""
												class="form-control required" pattern="^\+?[0-9\-]+\*?$"

												id="bus_fare"
												title="Bus fare">
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-3 control-label">Auto Price <span
											class="required">*</span>
										</label>
										<div class="col-md-9">
											<input type="text" name="auto_fare"
												value="" class="form-control required"
												pattern="^\+?[0-9\-]+\*?$"

												title="Make and Model must be characters with spaces">
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">Our Fare (Actual fare)<span
											class="required">*</span></label>
										<div class="col-md-9">
											<input type="text" name="actual_fare"
												class="form-control required digits"  value="">
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">Our Fare (Charged fare)<span
											class="required">*</span></label>
										<div class="col-md-9">
											<input type="text" name="charged_fare"
												class="form-control required digits"  value="">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Discounted fare Enable? <span class="required">*</span></label>
										<div class="col-md-9">
											<label class="radio"><input type="radio" name="discounted_fare_enabled" value="true" class="required uniform"> Enable</label>
											<label class="radio"><input type="radio" name="discounted_fare_enabled" value="false" class="uniform" checked="checked"> Disable</label>
											<label for="gender" class="has-error help-block" generated="true" style="display:none;"></label>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Discounted fare<span
											class="required">*</span></label>
										<div class="col-md-9">
											<input type="text" name="discounted_fare"
												class="form-control required digits"  value="">
										</div>
									</div>																			
									<div class="form-group">
										<label class="col-md-3 control-label">Distance <span
											class="required">*</span>
										</label>
										<div class="col-md-9">
											<input type="text" name="distance"
												value="km" class="form-control required"
												pattern=""
												title="Distance must be characters with km">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Time duration <span
											class="required">*</span>
										</label>
										<div class="col-md-9">
											<input type="text" name="time_duration"
												value="mins" class="form-control required"
												pattern=""
												title="Duration must be characters with mins/hours">
										</div>
									</div>									
									<div class="alert alert-danger fade in" id="input_errors"
										style="display: none;">

										<strong>Error!</strong> Please fill all the fields which are
										Marked *
									</div>
									<div class="form-actions">
										<input
											type="submit" value="Add Fare Price"
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