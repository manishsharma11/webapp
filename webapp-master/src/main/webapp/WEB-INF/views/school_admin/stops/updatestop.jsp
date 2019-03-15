<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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


<%-- <script type="text/javascript"
	src="<c:url value="/resources/project_js_file/homepage.js" />"></script> --%>
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
						<li>Update Stop</li>
					</ul>

					<ul class="crumb-buttons">


						<li><a href="#"> <i class="icon-calendar"></i>&nbsp;${date}
						</a></li>
					</ul>
				</div>
				<!-- /Breadcrumbs line -->
				
				<%-- <c:if test="${error_message=='latAndLongDup'}">
						<div class="alert alert-danger fade in">
						<i class="icon-remove close" data-dismiss="alert"></i>
						<strong>Error!</strong>  latitude and longitude are
							already exists....
						</div>
				</c:if> 
				
				<c:if test="${error_message=='stopDup'}">
						<div class="alert alert-danger fade in">
						<i class="icon-remove close" data-dismiss="alert"></i>
						<strong>Error!</strong> Stop name already exists.Please provide unique stop name...... 
						</div>
				</c:if>  --%>
				
				<!--=== Page Header ===-->
				<div class="page-header">

					<div class="page-title">
						<h3>Bus Stops</h3>
						<span>Update Stop</span>
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
									<i class="icon-reorder"></i>Stop Form
								</h4>
							</div>
							<div class="widget-content">
								<form class="form-horizontal row-border" id="validate-4" action="updatestopaction" method="post">

									<div class="form-group">
										<label class="col-md-3 control-label">Enable? <span class="required">*</span></label>
										<div class="col-md-9">
											<label class="radio"><input type="radio" name="enabled" value="true" class="required uniform" checked="checked"> Enable</label>
											<label class="radio"><input type="radio" name="enabled" value="false" class="uniform"> Disable</label>
											<label for="gender" class="has-error help-block" generated="true" style="display:none;"></label>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Stop Name<span
											class="required">*</span></label>
										<div class="col-md-9">	
											<input type="text" name="stop_name"
												class="form-control required" value="${stop.stop_name}">										
											<input type="hidden" name="stopId" class="form-control required" value="<c:out value="${stop.id}"></c:out>">
										</div>
									</div>
									
									<c:if test="${error_message=='stopDup'}">
											<div class="alert alert-danger fade in" align="center">						
											<strong>Error!</strong> Stop name already exists. Please provide unique stop name
											</div>
									</c:if> 
									
								   <div class="form-group">
										<label class="col-md-3 control-label">Stop Display Name<span
											class="required">*</span></label>
										<div class="col-md-9">
											<input type="text" name="display_name"
												class="form-control required"
												value="${stop.display_name}">
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">Latitude<span class="required">*</span></label>
										<div class="col-md-9">
											<input type="text" name="latitude" class="form-control required" value="${stop.latitude }" range="-90, +90">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Longitude<span class="required">*</span></label>
										<div class="col-md-9">
											<input type="text" name="longitude" class="form-control required" value="<c:out value="${stop.longitude }"></c:out>" range="-90, +90">
										</div>
									</div>

									 <c:if test="${error_message=='latAndLongDup'}">
										<div class="alert alert-danger fade in" align="center">
											<strong>Error!</strong>  Latitude and Longitude are
							already assigned to another stop
										</div>
									</c:if>

									<div class="form-group">
										<label class="col-md-3 control-label">Geofence<span
											class="required">*</span></label>
										<div class="col-md-9">
											<input type="text" name="geofence"
												class="form-control required" value="${stop.geofence}"
												pattern="^[0-9]*$" title="geofencer must be integer">
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">Short code<span
											class="required">*</span></label>
										<div class="col-md-9">
											<input type="text" name="shortcode"
												class="form-control required" value="${stop.shortcode}"
												pattern="^[a-zA-Z]*$" title="shortcode must be Alpha character">
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">Help text<span
											class="required">*</span></label>
										<div class="col-md-9">
											<input type="text" name="help_text"
												class="form-control required" value="${stop.help_text}"
												title="Help text">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Tags<span
											class="required">*</span></label>
										<div class="col-md-9">
											<input type="text" name="tags"
												class="form-control required" value="${stop.tags}"
												title="Tags">
										</div>
									</div>									
									<div class="form-group">
										<label class="col-md-3 control-label">Image Path<span
											class="required">*</span></label>
										<div class="col-md-9">
											<input type="text" name="image_path"
												class="form-control required" value="${stop.image_path}"
												title="Image path">
										</div>
									</div>
														
									<div class="alert alert-danger fade in" id="input_errors"
										style="display: none;">

										<strong>Error!</strong> Please fill all the fields which are
										marked *
									</div>
									<div class="form-actions">
										<a type="button" class="btn btn-default"
											href="<%=request.getContextPath()%>/school_admin/stop/stops">Cancel</a> <input
											type="submit" value="Update"
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