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
						<li>Devices</li>

						<li>Delete</li>
					</ul>

					<ul class="crumb-buttons">


						<li><a href="#"> <i class="icon-calendar"></i>&nbsp;${date}
						</a></li>
					</ul>
				</div>
				<!-- /Breadcrumbs line -->

				<br />
				<c:if test="${ error == 'device_exists' }">

					<div class="alert alert-danger fade in col-md-7">
						<i class="icon-remove close" data-dismiss="alert"></i> <strong>Error!</strong>
						Device Id you have entered is already exists...
					</div>
				</c:if>


				<div class="row" id="driver_form_add">
					<!--=== Validation Example 1 ===-->
					<div class="col-md-6">
						<div class="widget box">
							<div class="widget-header">
								<h4>
									<i class="icon-reorder"></i>Update Device Form
								</h4>
							</div>
							<div class="widget-content">
								<form class="form-horizontal row-border" id="validate-4"
									action="deleteaction" method="post">


									<input type="hidden" name="old_ec_device_id"
										value="${device.ec_device_id }" class="form-control required">

									<div class="form-group">
										<label class="col-md-3 control-label">Device ID</label>
										<div class="col-md-9">
											<font color="sienna">${device.ec_device_id }</font>
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-3 control-label">Vehicle ID <span
											class="required">*</span></label>
										<div class="col-md-9">
											<font color="sienna">${device.vehicle_id }</font>
										</div>
									</div>


									<div class="alert alert-danger fade in" id="input_errors"
										style="display: none;">

										<strong>Error!</strong> Please fill all the fields which are
										Marked *
									</div>
									<div class="form-actions">
										<a type="button" class="btn btn-default"
											href="<%=request.getContextPath()%>/school_admin/bus/buses">Cancel</a>
										<input type="submit" value="Delete"
											class="btn btn-danger pull-right" id="add_new_driver_action">
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