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
						<li>Buses</li>

						<li>Add New Bus</li>
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
						<h3>Bus</h3>
						<span>Add Bus</span>
					</div>
					


				</div>
				<c:if test="${message== 'bus_exists' }">
				
					<div class="alert alert-danger fade in">
						<i class="icon-remove close" data-dismiss="alert"></i>
						<strong>Error!</strong> Bus already Available with Bus License Number: <b>${bus_licence_number }</b>
					</div>
				
				</c:if>
				

				<div class="row" id="driver_form_add">
					<!--=== Validation Example 1 ===-->
					<div class="col-md-6">
						<div class="widget box">
							<div class="widget-header">
								<h4>
									<i class="icon-reorder"></i>Add New Bus Form
								</h4>
							</div>
							<div class="widget-content">
								<form class="form-horizontal row-border" id="validate-4" action="addbusaction" method="post">

									<div class="form-group">
										<label class="col-md-3 control-label">Select Driver<span
											class="required">*</span></label>
										<div class="col-md-9 clearfix">
											<select name="driver_name"
												class="col-md-9 select2 full-width-fix required"
												id="driver_id_name" style="border-color: grey;">
												<option></option>
												 
												<c:forEach items="${drivers}" var="driver">
													<c:if test="${driver.available==0}">
													<option value="<c:out value="${driver.id}"></c:out>"><c:out value="${driver.driver_name}"></c:out></option>
													</c:if>
												</c:forEach>
											</select> <label for="chosen1" generated="true"
												class="has-error help-block" style="display: none;"></label>
										</div>
									</div>
									<%-- <div class="form-group">
										<label class="col-md-3 control-label">Select Route<span
											class="required">*</span></label>
										<div class="col-md-9 clearfix">
											<select name="route_name_id"
												class="col-md-12 select2 full-width-fix required"
												id="driver_id_name" style="border-color: grey;">
												<option></option>
												<c:forEach items="${routes }" var="route">
														<c:if test="${route.bus_licence_number eq 'none'}">
													<option value="<c:out value="${route.route_name}"></c:out>:<c:out value="${route.id}"></c:out>"><c:out value="${route.route_name}"></c:out></option>
														</c:if>
												</c:forEach>
											</select> <label for="chosen1" generated="true"
												class="has-error help-block" style="display: none;"></label>
										</div>
									</div> --%>
									<div class="form-group">
										<label class="col-md-3 control-label">Bus Licence Number<span
											class="required">*</span></label>
										<div class="col-md-9">
											<input type="text" name="bus_licence_number" value="${bus.bus_licence_number }" 	class="form-control required"  pattern="^\+?[A-Za-z0-9]+\*?$" id="bus_licencenumber" title="Licence Number Must be Alpha Numeric with no spaces">
										</div>
									</div>
									 
									<div class="form-group">
										<label class="col-md-3 control-label">Bus Make and Model <span class="required">*</span></label>
										<div class="col-md-9">
											<input type="text" name="bus_make_model"  value="${bus.bus_make_model }" class="form-control required" pattern="^\+?[A-Za-z\ ]+\*?$" title="Make and Model must be characters with spaces">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Bus Capacity<span class="required">*</span></label>
										<div class="col-md-9">
											<input type="text" name="bus_capacity" class="form-control required digits" range="1,60" value="${bus.bus_capacity }">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Bus Allocated<span class="required">*</span></label>
										<div class="col-md-9">
											<input type="text" name="bus_allotted" class="form-control required digits" range="1,60" value="${bus.bus_allotted }">
										</div>
									</div>
									<div class="alert alert-danger fade in" id="input_errors"
										style="display: none;">

										<strong>Error!</strong> Please fill all the fields which are
										Marked *
									</div>
									<div class="form-actions">
										<a type="button" class="btn btn-default"
											href="<%=request.getContextPath()%>/school_admin/bus/buses">Cancel</a> <input
											type="submit" value="Add Bus"
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