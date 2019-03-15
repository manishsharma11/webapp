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
						<li>Trips</li>
						<li>Delete Trip</li>
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
						<h3>Bus Trips</h3>
						<span>Delete Trip</span>
					</div>
					<br>
					<div class="col-sm-4 col-md-1"></div>
				</div>
				<c:if test="${ERROR_MESSAGE != null }">
					<div class="alert alert-danger fade in">
						<i class="icon-remove close" data-dismiss="alert"></i> <strong>Error!</strong>
						${ERROR_MESSAGE }
					</div>
				</c:if>

				<div class="row" id="driver_form_add">
					<!--=== Validation Example 1 ===-->
					<div class="col-md-8">
						<div class="widget box">
							<div class="widget-header">
								<h4>
									<i class="icon-reorder"></i>Trip Delete Form
								</h4>
							</div>
							<div class="widget-content">
								<form class="form-horizontal row-border" id="validate-4"
									action="deleteTripAction" method="post">


									<div class="form-group">
										<label class="col-md-3 control-label">Trip Name :</label>
										<div class="col-md-9">
											<label class="control-label" style="color: sienna"><c:out
													value="${trip.trip_name}"></c:out></label> <input type="hidden"
												name="id" class="form-control required"
												value="<c:out value="${trip.id }"></c:out>">

										</div>
									</div>
									<div class="form-group">

										<label class="col-md-3 control-label">Trip Type :</label>
										<div class="col-md-3">
											<label class="control-label" style="color: sienna"><c:out
													value="${trip.trip_type}"></c:out></label>
										</div>
									</div>
									<div class="form-group">

										<label class="col-md-3 control-label">Route Name:</label>
										<div class="col-md-3">
											<label class="control-label" style="color: sienna"><c:out
													value="${trip.getRoutes().getRoute_name()}"></c:out></label>
										</div>
									</div>
									<div class="form-group">

										<label class="col-md-3 control-label">Bus Id:</label>
										<div class="col-md-3">
											<label class="control-label" style="color: sienna"><c:out
													value="${trip.getBus().getBus_licence_number()}"></c:out></label>
										</div>
									</div>
									<%-- <div class="form-group">

										<label class="col-md-3 control-label">School Time:</label>
										<div class="col-md-3">
											<label class="control-label" style="color: sienna"><fmt:formatNumber
													type="number" minIntegerDigits="2" maxIntegerDigits="2"
													value="${trip.school_time_hours}" /> <c:out value=":" />
												<fmt:formatNumber type="number" minIntegerDigits="2"
													maxIntegerDigits="2" value="${trip.school_time_minutes}" />
											</label>
										</div>
									</div> --%>

									<div class="form-group">

										<label class="col-md-3 control-label">Trip Start Time:</label>
										<div class="col-md-3">
											<label class="control-label" style="color: sienna"> <fmt:formatNumber
													type="number" minIntegerDigits="2" maxIntegerDigits="2"
													value="${trip.trip_start_time_hours}" /> <c:out value=":" />
												<fmt:formatNumber type="number" minIntegerDigits="2"
													maxIntegerDigits="2"
													value="${trip.trip_start_time_minutes}" />
											</label>
										</div>
									</div>


									<div class="form-group">

										<label class="col-md-3 control-label">Trip End Time:</label>
										<div class="col-md-3">
											<label class="control-label" style="color: sienna"> <fmt:formatNumber
													type="number" minIntegerDigits="2" maxIntegerDigits="2"
													value="${trip.trip_end_time_hours}" /> <c:out value=":" />
												<fmt:formatNumber type="number" minIntegerDigits="2"
													maxIntegerDigits="2" value="${trip.trip_end_time_minutes}" />
											</label>
										</div>
									</div>


									<div class="form-actions">
										<a type="button" class="btn btn-default"
											href="<%=request.getContextPath()%>/school_admin/trip/trips">Cancel</a>
										<input type="submit" value="Delete"
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