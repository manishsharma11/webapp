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
						<li>Trips</li>
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
									<i class="icon-reorder"></i>Trip Form
								</h4>
							</div>

							<div class="widget-content">
								<form class="form-horizontal row-border"
									 id="validate-4"
									action="addtripaction1" method="post">
									
									<div class="form-group">
										<label class="col-md-3 control-label">Select Trip Detail<span
											class="required">*</span></label>
										<div class="col-md-9 clearfix">
											<select name="trip_detail_id"
												class="col-md-9 select2 full-width-fix required"
												id="bus_css_id" style="border-color: grey;">
												<option></option>
												<c:forEach items="${tripDetails }" var="tripDetail">

													<option value="${tripDetail.id}">${tripDetail.trip_display_name}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="form-group">
									<label class="col-md-3 control-label">Enable? <span class="required">*</span></label>
										<div class="col-md-9">
											<label class="radio"><input type="radio" name="enabled" value="true" class="required uniform" > Enable</label>
											<label class="radio"><input type="radio" name="enabled" value="false" class="uniform"> Disable
											</label>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">From:</label>
										<div class="col-md-9">
											<input type="text" name="from" class="form-control datepicker required" >
										</div> 
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">To:</label>
										<div class="col-md-9">
											<input type="text" name="to" class="form-control datepicker required">
										</div>
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
					
				</div>

			</div>

		</div>

	</div>


</body>
</html>