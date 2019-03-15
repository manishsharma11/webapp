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
	$(function() {

		$("#route_css_id").click(function() {
			//alert("route selected");
			var route = $("#route_css_id").val();
			var arr = route.split(';;');
			var routeId = arr[0];
			$.ajax({

				url : "getAllStopssByRouteId",
				type : "POST",
				data : "route_id=" + routeId,
				success : function(result) {
					//alert(result);
					//alert($("#route_css_id").val());

					var obj = JSON.parse(result);
					//alert(obj);			
					var timings = obj.split(';');
					var firstStopTime = timings[0];
					var secondStopTime = timings[1];

					$("#stop_starttime").empty();
					$("#stop_starttime").append(firstStopTime);
					$("#stopStartHidden").val(firstStopTime);

					$("#stop_endtime").empty();
					$("#stop_endtime").append(secondStopTime);
					$("#stopEndHidden").val(secondStopTime);
				}
			});
		});
	});
</script>

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$('#time_error_id').hide();
						/* $('dupName_id').hide(); */
						$("#school_time_arrival").hide();
						$("#school_time_start").hide();
						$("#school_time_hours_total_id").hide();
						$("#school_time_minutes_total_id").hide();
						$("#routeEmptyCssId").hide();
						$("#break").hide();

						$("#trip_type_css_id")
								.change(
										function() {

											$("#loading").show();
											//alert($(this).val());

											$('#route_css_id option:selected')
													.each(function() {
														//alert("hi");
														$(this).remove();
													});

											if ($(this).val() == 'Pick Up') {
												$("#schoolTimeLable").hide();
												$("#school_time_arrival")
														.show();
												$("#school_time_start").hide();
												$("#school_time_hours_total_id")
														.show();
												$(
														"#school_time_minutes_total_id")
														.show();
												$("#break").show();
											} else {
												$("#schoolTimeLable").hide();
												$("#school_time_arrival")
														.hide();
												$("#school_time_start").show();
												$("#school_time_hours_total_id")
														.show();
												$(
														"#school_time_minutes_total_id")
														.show();
												$("#break").show();
											}
											$
													.ajax({
														url : "getAllRoutesByTripType",
														type : "POST",
														data : "trip_type="
																+ $(this).val(),
														success : function(
																result) {
															var obj = JSON
																	.parse(result);
															var data = "";

															for (var i = 0; i < obj.length; i++) {
																var d = obj[i].id
																		+ ";;"
																		+ obj[i].route_name;
																data = data
																		+ "<option value=\""+d+"\">"
																		+ obj[i].route_name
																		+ "</option>";
																//alert(data);
															}
															$("#route_css_id")
																	.empty();
															//alert($("#route_css_id").val());
															$("#route_css_id")
																	.append(
																			"<option>Select</option>")
																	.change();
															;
															$("#route_css_id")
																	.append(
																			data);
															$("#loading")
																	.hide();
														}
													});
										});
						$("#route_css_id").change(function() {

							//alert($(this).val());
						});
					});
</script>

<script type="text/javascript">
	function validate() {
		var start_hours = document.getElementById('trip_start_time_hours_id').value;
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
		if (routeValue == "Select") {
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
						<li>Trips Details</li>
						<li>Add</li>
					</ul>

					<ul class="crumb-buttons">

						<li><a href="#"> <i class="icon-calendar"></i>&nbsp;${date}
						</a></li>
					</ul>
				</div>
				<!-- /Breadcrumbs line -->


				<br/>

				<div class="row" id="driver_form_add">
					<!--=== Validation Example 1 ===-->
					<div class="col-md-7">
						<div class="widget box">
							<div class="widget-header">
								<h4>
									<i class="icon-reorder"></i>Trip Details Form
								</h4>
							</div>

							<div class="widget-content">
								<form class="form-horizontal row-border"
									onsubmit="return validate()" id="validate-4"
									action="addtripaction" method="get">

									<div class="form-group">
										<label class="col-md-3 control-label">Trip Name<span
											class="required">*</span></label>
										<div class="col-md-9">
											<input type="text" name="trip_name"
												class="form-control required" value="${trip.trip_name}">
										</div>



									</div>
									<div id="tripDup_id">
										<c:if test="${error_message=='tripDup'}">
											<div class="alert alert-danger fade in" align="center"
												id="dupName_id">
												<strong>Error!</strong> Trip name already Registered. Please
												provide unique trip name
											</div>
										</c:if>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Select Bus<span
											class="required">*</span></label>
										<div class="col-md-9 clearfix">
											<select name="bus_id"
												class="col-md-9 select2 full-width-fix required"
												id="bus_css_id" style="border-color: grey;">
												<option selected="selected"
													value="${trip.getBus().getBus_licence_number()}"><c:out
														value="${trip.getBus().getBus_licence_number()}"></c:out></option>
												<c:forEach items="${buses}" var="bus">
													<option value="${bus.bus_licence_number}"><c:out
															value="${bus.bus_licence_number}"></c:out></option>
												</c:forEach>
											</select>
										</div>
									</div>

									<div class="form-group">

										<label class="col-md-3 control-label">Select Trip Type<span
											class="required">*</span></label>
										<div class="col-md-9 clearfix">
											<c:if test="${errorOccured!='yes'}">
												<select name="trip_type"
													class="col-md-9 select2 full-width-fix required"
													id="trip_type_css_id" style="border-color: grey;">
													<%-- <option selected="selected"><c:out value="${trip.trip_type}"></c:out></option> --%>
													<option></option>
													<option><c:out value="Pick Up"></c:out></option>
													<option><c:out value="Drop Off"></c:out></option>
												</select>
											</c:if>

											<c:if test="${errorOccured=='yes'}">
												<c:if test="${trip.trip_type=='Pick Up'}">
													<select name="trip_type"
														class="col-md-9 select2 full-width-fix required"
														id="trip_type_css_id" style="border-color: grey;">
														<%-- <option selected="selected"><c:out value="${trip.trip_type}"></c:out></option> --%>
														<option></option>
														<option selected="selected"><c:out
																value="Pick Up"></c:out></option>
														<option><c:out value="Drop Off"></c:out></option>
													</select>
												</c:if>
												<c:if test="${trip.trip_type=='Drop Off'}">
													<select name="trip_type"
														class="col-md-9 select2 full-width-fix required"
														id="trip_type_css_id" style="border-color: grey;">
														<%-- <option selected="selected"><c:out value="${trip.trip_type}"></c:out></option> --%>
														<option></option>
														<option><c:out value="Pick Up"></c:out></option>
														<option selected="selected"><c:out
																value="Drop Off"></c:out></option>
													</select>
												</c:if>
											</c:if>

										</div>
									</div>




									<c:if test="${errorOccured!='yes'}">
										<div class="form-group">
											<label class="col-md-3 control-label">Select Route<span
												class="required">*</span></label>
											<div class="col-md-9 clearfix">
												<select name="route_name"
													class="col-md-9 select2 full-width-fix required"
													id="route_css_id" style="border-color: grey;">
													<option></option>
												</select>
											</div>
										</div>
									</c:if>
									<c:if test="${errorOccured=='yes'}">
										<div class="form-group">
											<label class="col-md-3 control-label">Select Route<span
												class="required">*</span></label>
											<div class="col-md-9 clearfix">
												<select name="route_name"
													class="col-md-9 select2 full-width-fix required"
													id="route_css_id" style="border-color: grey;">
													<option selected="selected"
														value="<c:out value="${trip.getRoutes().getId()}"></c:out>"><c:out
															value="${trip.getRoutes().getRoute_name()}"></c:out></option>
													<c:forEach items="${routes}" var="bus">
														<option value="${bus.id}"><c:out
																value="${bus.route_name}"></c:out></option>
													</c:forEach>
													<option></option>
												</select> <input type="hidden" name="route_id"
													value="${trip.getRoutes().getId()}">
											</div>
										</div>
									</c:if>
									<div class="alert alert-danger fade in" align="center"
										id="routeEmptyCssId">
										<strong>Error!</strong> Route name should not be empty.

									</div>


									<div class="form-group">
										<label class="col-md-3 control-label">Trip Start Time<span
											class="required">*</span>
										</label>

										<div class="col-md-3 clearfix">
											<select name="trip_start_time_hours"
												class="col-md-8 select2 full-width-fix required"
												id="trip_start_time_hours_id">
												<option selected="selected">${currentStopHoursTowardsHome}</option>
												<option selected="selected"><fmt:formatNumber
														type="number" minIntegerDigits="2" maxIntegerDigits="2"
														value="${trip.trip_start_time_hours}" /></option>

												<c:forEach begin="0" end="23" varStatus="num">
													<option><fmt:formatNumber type="number"
															minIntegerDigits="2" maxIntegerDigits="2"
															value="${num.index}" /></option>
												</c:forEach>
											</select>
										</div>

										<div class="col-md-3 clearfix">
											<select name=trip_start_time_minutes
												id="trip_start_time_minutes_id"
												class="col-md-8 select2 full-width-fix required">
												<!-- <option></option> -->
												<option selected="selected"><fmt:formatNumber
														type="number" minIntegerDigits="2" maxIntegerDigits="2"
														value="${trip.trip_start_time_minutes}" /></option>
												<c:forEach begin="0" end="59" varStatus="num">
													<option><fmt:formatNumber type="number"
															minIntegerDigits="2" maxIntegerDigits="2"
															value="${num.index}" /></option>
												</c:forEach>

											</select>
										</div>
									</div>
									<div>
										<c:if test="${error_message=='StartTripError'}">
											<div class="alert alert-danger fade in" align="center"
												id="prev_trip1">
												<strong>Error!</strong> Trip start timings should be less
												than the first stop start time
											</div>
										</c:if>
									</div>
									<div>
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



									<!-- <div id="school_time_arrival">
										<label class="col-md-3 control-label">School Arrival
											Time<span class="required">*</span>
										</label>
									</div>

									<div id="school_time_start">
										<label class="col-md-3 control-label">School Start
											Time<span class="required">*</span>
										</label>
									</div> -->






									<div>
										<c:if test="${error_message=='schoolStartTimeError'}">
											<div class="alert alert-danger fade in" align="center"
												id="prev_trip2">
												<strong>Error!</strong> School Start Time should be in
												between Trip Start time and First Stop time
											</div>
										</c:if>
									</div>
									<div>
										<c:if test="${error_message=='schoolArrivalTimeError'}">
											<div class="alert alert-danger fade in" align="center"
												id="prev_trip2">
												<strong>Error!</strong> School Arrival Time should be in
												between Last Stop time and Trip End time
											</div>
										</c:if>
									</div>






									<div class="form-group">
										<label class="col-md-3 control-label">Trip End Time<span
											class="required">*</span>
										</label>

										<div class="col-md-3 clearfix">
											<select name="trip_end_time_hours"
												class="col-md-8 select2 full-width-fix required"
												id=trip_end_time_hours_id>
												<option selected="selected"><fmt:formatNumber
														type="number" minIntegerDigits="2" maxIntegerDigits="2"
														value="${trip.trip_end_time_hours}" /></option>

												<c:forEach begin="0" end="23" varStatus="num">
													<option><fmt:formatNumber type="number"
															minIntegerDigits="2" maxIntegerDigits="2"
															value="${num.index}" /></option>
												</c:forEach>

											</select>
										</div>

										<div class="col-md-3 clearfix">
											<select name="trip_end_time_minutes"
												id="trip_end_time_minutes_id"
												class="col-md-8 select2 full-width-fix required">
												<option selected="selected"><fmt:formatNumber
														type="number" minIntegerDigits="2" maxIntegerDigits="2"
														value="${trip.trip_end_time_minutes}" /></option>
												<c:forEach begin="0" end="59" varStatus="num">
													<option><fmt:formatNumber type="number"
															minIntegerDigits="2" maxIntegerDigits="2"
															value="${num.index}" /></option>
												</c:forEach>

											</select>
										</div>

									</div>
									<div>
										<c:if test="${error_message=='tripEndTime'}">
											<div class="alert alert-danger fade in" align="center"
												id="prev_trip">
												<strong>Error!</strong> Trip end timings should not be in
												between the previous trip
											</div>
										</c:if>
									</div>
									<div>
										<c:if test="${error_message=='prevTrip'}">
											<div class="alert alert-danger fade in" align="center"
												id="prev_trip">
												<strong>Error!</strong> Trip timings should not be in
												between the previous trip timings
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
												<strong>Error!</strong> Trip end timings should be greater
												than the last stop time
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
											href="<%=request.getContextPath()%>/school_admin/trip/trips">Cancel</a>
										<input type="submit" value="Add"
											class="btn btn-primary pull-right" id="add_trip_button_id">
									</div>
								</form>
							</div>
						</div>
						<!-- /Validation Example 1 -->
					</div>
					<!-- First And Last Stop Details -->
					<div class="col-md-4" style="top: 100px;">
						<div class="widget box">
							<div class="widget-header">
								<h4>
									<i class="icon-reorder"></i>Stops Information
								</h4>
							</div>
							<c:if test="${errorOccured!='yes'}">
								<div class="widget-content">
									<form class="form-horizontal row-border" action="#">
										<div class="form-group">
											<label class="col-md-6 control-label"> First Stop
												Time&nbsp;&nbsp;<font color="sienna" id="stop_starttime"></font>
											</label>

										</div>
										<div class="form-group">
											<label class="col-md-6 control-label"> Last Stop
												Time&nbsp;&nbsp;<font color="sienna" id="stop_endtime"></font>
											</label>

										</div>
									</form>
								</div>
							</c:if>

							<c:if test="${errorOccured=='yes'}">
								<div class="widget-content">
									<form class="form-horizontal row-border" action="#">
										<div class="form-group">
											<label class="col-md-6 control-label"> First Stop
												Time&nbsp;&nbsp;<font color="sienna" id="stop_starttime"><c:out
														value="${fs}"></c:out></font>
											</label>

										</div>
										<div class="form-group">
											<label class="col-md-6 control-label"> Last Stop
												Time&nbsp;&nbsp;<font color="sienna" id="stop_endtime"><c:out
														value="${ls}"></c:out></font>
											</label>

										</div>
									</form>
								</div>
							</c:if>




						</div>
					</div>
					<!-- /First And Last Stop Details -->
				</div>

			</div>

		</div>

	</div>


</body>
</html>