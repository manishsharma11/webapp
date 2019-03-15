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
									<i class="icon-reorder"></i>Stop Delete Form
								</h4>
							</div>
							<div class="widget-content">
								<form class="form-horizontal row-border" id="validate-4" action="deletestopaction" method="post">

									
									<div class="form-group">
										<label class="col-md-3 control-label">Stop Name</label>
										<div class="col-md-9">
											<label class="control-label" style="color: sienna"><c:out value="${stop.stop_name}"></c:out></label>
											
											<input type="hidden" name="stopId" 	class="form-control required" value="<c:out value="${stop.id }"></c:out>">
											
										</div>
									</div>
									<div class="form-group">
										
										<label class="col-md-3 control-label">Latitude</label>
										<div class="col-md-3">										
											<label class="control-label" style="color: sienna"><c:out value="${stop.latitude}"></c:out></label>											
										</div>									
									</div>
									<div class="form-group">
										
										<label class="col-md-3 control-label">Longitude</label>
										<div class="col-md-3">										
											<label class="control-label" style="color: sienna"><c:out value="${stop.longitude}"></c:out></label>											
										</div>										
									</div>
									<div class="form-group">
										
										<label class="col-md-3 control-label">Geofence</label>
										<div class="col-md-3">																			
											<label class="control-label" style="color: sienna"><c:out value="${stop.geofence}"></c:out></label>											
										</div>										
									</div>
									<div class="form-actions">
										<a type="button" class="btn btn-default"
											href="<%=request.getContextPath()%>/school_admin/stop/stops">Cancel</a>
											 <input type="submit" value="Delete" class="btn btn-primary pull-right" id="add_new_driver_action">
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