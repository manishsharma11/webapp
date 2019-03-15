<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


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
	function validate() {
		var start_hours = document.getElementById('session_start_time_hours_id').value;
		var start_minutes = document
				.getElementById('trip_start_time_minutes_id').value;
		var start_total = parseInt(start_hours) * 60 + parseInt(start_minutes);

		var end_hours = document.getElementById('trip_end_time_hours_id').value;
		var end_minutes = document.getElementById('trip_end_time_minutes_id').value;
		var end_total = parseInt(end_hours) * 60 + parseInt(end_minutes);
		//alert(start_total+""+end_total);
		if (start_total >= end_total) {
			$('#tripDup_id').hide();
			$('#time_error_id').show();
			return false;
		}
		var routeValue = document.getElementById('route_css_id').value;
		if(routeValue == "Select")
		{
			$('#routeEmptyCssId').show();
			return false;	
		}	
	};
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
						<li>Sessions</li>
						<li>Add Sessions</li>
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
						<h3>School Sessions</h3>
						<span>Add Session</span>
					</div>

					<div class="col-sm-4 col-md-1"></div>

				</div>


				<div class="row" id="driver_form_add">
					<!--=== Validation Example 1 ===-->
					<div class="col-md-7">
						<div class="widget box">
							<div class="widget-header">
								<h4>
									<i class="icon-reorder"></i>Session Form
								</h4>
							</div>

							<div class="widget-content">
								<form class="form-horizontal row-border"
									onsubmit="return validate()" id="validate-4"
									action="addsessionaction" method="get">

									<div class="form-group">
										<label class="col-md-3 control-label">Session Name<span
											class="required">*</span></label>
										<div class="col-md-9">
											<input type="text" name="session_name"
												class="form-control required" value="${session.session_name}">
										</div>

							

									</div>
									<div id="tripDup_id">
										<c:if test="${error_message=='tripDup'}">
											<div class="alert alert-danger fade in" align="center"
												id="dupName_id">
												<strong>Error!</strong> Session name already Registered. Please
												provide unique Session name	</div>
										</c:if>
									</div>
									 

									<div class="form-group">

										<label class="col-md-3 control-label">Select Session Type<span
											class="required">*</span></label>
										<div class="col-md-9 clearfix">
											 
												<select name="session_type"
													class="col-md-9 select2 full-width-fix required"
													id="trip_type_css_id" style="border-color: grey;">
													<%-- <option selected="selected"><c:out value="${trip.trip_type}"></c:out></option> --%>
													<option></option>
													<option><c:out value="Morning"></c:out></option>
													<option><c:out value="Afternoon"></c:out></option>
												</select>
											 
											  												 
										</div>
									</div>


									<!-- <div class="alert alert-danger fade in" align="center"
										id="routeEmptyCssId">
										<strong>Error!</strong> Route name should not be empty.

									</div>
 -->

									<div class="form-group">
										<label class="col-md-3 control-label">Session Start Time<span
											class="required">*</span>
										</label>

										<div class="col-md-3 clearfix">
											<select name="starting_time_hours"
												class="col-md-8 select2 full-width-fix required"
												id="trip_start_time_hours_id">
												<option selected="selected">${currentStopHoursTowardsHome}</option>
												<option selected="selected"><fmt:formatNumber
														type="number" minIntegerDigits="2" maxIntegerDigits="2"
														value="${session.starting_time_hours}" /></option>

												<c:forEach begin="0" end="23" varStatus="num">
													<option><fmt:formatNumber type="number"
															minIntegerDigits="2" maxIntegerDigits="2"
															value="${num.index}" /></option>
												</c:forEach>
											</select>
										</div>

										<div class="col-md-3 clearfix">
											<select name=starting_time_minutes
												id="trip_start_time_minutes_id"
												class="col-md-8 select2 full-width-fix required">
												<!-- <option></option> -->												
												<option selected="selected"><fmt:formatNumber
														type="number" minIntegerDigits="2" maxIntegerDigits="2"
														value="${session.starting_time_minutes}" /></option>
												<c:forEach begin="0" end="59" varStatus="num">
													<option><fmt:formatNumber type="number"
															minIntegerDigits="2" maxIntegerDigits="2"
															value="${num.index}" /></option>
												</c:forEach>

											</select>
										</div>
									</div>
									 <div>
										<c:if test="${error_message=='higherstart'}">
											<div class="alert alert-danger fade in" align="center"
												id="prev_trip1">
												<strong>Error!</strong> Session Start timings should be less
												than the End time
											</div>
										</c:if>
									</div> 
									<%-- <div>
										<c:if test="${error_message=='tripStartTime'}">
											<div class="alert alert-danger fade in" align="center"
												id="prev_trip">
												<strong>Error!</strong> Trip start timings should not be in
												between the previous trip
											</div>
										</c:if>
									</div>
									<div class="alert alert-danger fade in" align="center"
										id="time_error_id">
										<strong>Error!</strong> Trip start time should not be more
										than trip end time
									</div>
 --%>



									<div class="form-group">
										<label class="col-md-3 control-label">Session End Time<span
											class="required">*</span>
										</label>

										<div class="col-md-3 clearfix">
											<select name="end_time_hours"
												class="col-md-8 select2 full-width-fix required"
												id=trip_end_time_hours_id>
												<option selected="selected"><fmt:formatNumber
														type="number" minIntegerDigits="2" maxIntegerDigits="2"
														value="${session.end_time_hours}" /></option>

												<c:forEach begin="0" end="23" varStatus="num">
													<option><fmt:formatNumber type="number"
															minIntegerDigits="2" maxIntegerDigits="2"
															value="${num.index}" /></option>
												</c:forEach>

											</select>
										</div>

										<div class="col-md-3 clearfix">
											<select name="end_time_minutes"
												id="trip_end_time_minutes_id"
												class="col-md-8 select2 full-width-fix required">
												<option selected="selected"><fmt:formatNumber
														type="number" minIntegerDigits="2" maxIntegerDigits="2"
														value="${session.end_time_minutes}" /></option>
												<c:forEach begin="0" end="59" varStatus="num">
													<option><fmt:formatNumber type="number"
															minIntegerDigits="2" maxIntegerDigits="2"
															value="${num.index}" /></option>
												</c:forEach>

											</select>
										</div>

									</div>
								<%-- 	<div>
										 <c:if test="${error_message=='tripEndTime'}">
											<div class="alert alert-danger fade in" align="center"
												id="prev_trip">
												<strong>Error!</strong> Trip end timings should not be in
												between the previous trip
											</div>
										</c:if>
									</div> --%>
									<div>
										<c:if test="${error_message=='duplicate session'}">
											<div class="alert alert-danger fade in" align="center"
												id="prev_trip">
												<strong>Error!</strong> Session timings should not be in
												between the previous Session timings
											</div>
										</c:if>
									</div>
									<input type="hidden" id="stopStartHidden" name="stopStartTime">
									<input type="hidden" id="stopEndHidden" name="stopEndTime">

									<!-- <div id="break">
											<br>
											</div>
									 -->




									<div>
										<c:if test="${error_message=='EndTripError'}">
											<div class="alert alert-danger fade in" align="center"
												id="prev_trip2">
												<strong>Error!</strong> Session end timings should be greater
												than session start time
											</div>
										</c:if>
									</div>


									<div class="alert alert-danger fade in" id="input_errors"
										style="display: none;">

										<strong>Error!</strong> Please fill all the fields which are
										marked *
									</div>
									<div class="form-actions">
										<a type="button" class="btn btn-default"
											href="<%=request.getContextPath()%>/school_admin/session/sessionlist">Cancel</a> <input
											type="submit" value="Add Trip"
											class="btn btn-primary pull-right" id="add_trip_button_id">
									</div>
								</form>
							</div>
						</div>
						<!-- /Validation Example 1 -->
					</div>
					<!-- First And Last Stop Details -->
					
					<!-- /First And Last Stop Details -->
				</div>

			</div>

		</div>

	</div>


</body>
</html>