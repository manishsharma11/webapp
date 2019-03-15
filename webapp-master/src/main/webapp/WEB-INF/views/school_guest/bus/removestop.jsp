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

>
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
						<li>Buses</li>
						<li>Stops</li>
						<li>Delete Stop</li>
					</ul>

					<ul class="crumb-buttons">
						<li><a href="#"> <i class="icon-calendar"></i>&nbsp;${date}
						</a></li>
					</ul>
				</div>
				
				<!-- /Breadcrumbs line -->
				<!-- Validating students /staff on the stop before deleting - added by Mushtaq -->
				<c:if test="${error=='removestper'}">
				<br>
						<div class="alert alert-danger fade in">
						<i class="icon-remove close" data-dismiss="alert"></i>
						<strong>Error! </strong> Cannot delete stop as student or staff are allocated to <b>${stop.stop_name}</b> stop.......
						</div>
				</c:if>
				<!-- Validating students /staff on the stop before deleting - added by Mushtaq -->
				<!--=== Page Header ===-->
				<div class="page-header">

					<div class="page-title">
						<h3>Bus Stops</h3>
						<span>Delete Stop</span>
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
									<i class="icon-reorder"></i>Delete Bus Stop Form
								</h4>
							</div>
							<div class="widget-content">
								<form class="form-horizontal row-border" id="validate-4"
									action="deletestopaction" method="post">

									<div class="form-group">
										<label class="col-md-3 control-label">Current Bus Stop
											Priority:</label>
										<div class="col-md-9">
											<label class="control-label"><c:out
													value="${stop.stop_number}"></c:out></label>
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-3 control-label">Stop Name<span
											class="required">*</span></label>
										<div class="col-md-9">
											<label class="control-label"><c:out
													value="${stop.stop_name}"></c:out></label> <input type="hidden"
												name="bus_id" class="form-control required"
												value="<c:out value="${bus_id }"></c:out>"> <input
												type="hidden" name="stop_id" class="form-control required"
												value="<c:out value="${stop.id }"></c:out>"> <input
												type="hidden" name="stop_number"
												class="form-control required"
												value="<c:out value="${stop.stop_number }"></c:out>">
										</div>
									</div>
									<div class="form-group">

										<label class="col-md-3 control-label">Expected Time
											Towards School<span class="required">*</span>
										</label>
										<div class="col-md-3">
											<label class="control-label"><c:out
													value="${stop.expected_time_towards_school}"></c:out></label>
										</div>
									</div>
									<div class="form-group">

										<label class="col-md-3 control-label">Expected Time
											Towards Home<span class="required">*</span>
										</label>
										<div class="col-md-3">
											<label class="control-label"><c:out
													value="${stop.expected_time_towards_home}"></c:out></label>
										</div>
									</div>
									<div class="form-group">

										<label class="col-md-3 control-label">Latitude<span
											class="required">*</span></label>
										<div class="col-md-3">
											<label class="control-label"><c:out
													value="${stop.lattitude}"></c:out></label>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Longitude<span
											class="required">*</span></label>
										<div class="col-md-3">
											<label class="control-label"><c:out
													value="${stop.longitude}"></c:out></label>
										</div>
									</div>
									<div class="form-actions">
										<a type="button" class="btn btn-default"
											href="/sts/school_admin/bus/stops?bus_id=<c:out value="${bus_id}"></c:out>">Cancel</a>
										<input type="submit" value="Delete Stop"
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