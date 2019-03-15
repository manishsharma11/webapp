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
						<li>Routes</li>
						<li>Update Route</li>
					</ul>

					<ul class="crumb-buttons">
						<li><a href="#"> <i class="icon-calendar"></i>&nbsp;${date}
						</a></li>
					</ul>
				</div>
				<!-- /Breadcrumbs line -->
				
				<%-- <c:if test="${error_message=='SessionOne_end_less'}">
						<div class="alert alert-danger fade in">
						<i class="icon-remove close" data-dismiss="alert"></i>
						<strong>Error! </strong> Session 1 end time should not be less than Session 1 start time...
						</div>
						<style>
							#home_error{
								
								border-color:red; 
							}
						</style>
				</c:if> 	
					
				<c:if test="${error_message=='SessionOne_between'}">
						<div class="alert alert-danger fade in">
						<i class="icon-remove close" data-dismiss="alert"></i>
						<strong>Error! </strong> Session 2 timings should not be in between Session 1 timings...
						</div>
						<style>
							#home_error{
								
								border-color:red; 
							}
						</style>
				</c:if> 
				
				<c:if test="${error_message=='SessionOne_between_start'}">
						<div class="alert alert-danger fade in">
						<i class="icon-remove close" data-dismiss="alert"></i>
						<strong>Error! </strong> Session 2 start time should not in between Session 1 timings...
						</div>
						<style>
							#home_error{
								
								border-color:red; 
							}
						</style>
				</c:if> 
				
				<c:if test="${error_message=='SessionOne_between_end'}">
						<div class="alert alert-danger fade in">
						<i class="icon-remove close" data-dismiss="alert"></i>
						<strong>Error! </strong> Session 2 end time should not in between Session 1 timings...
						</div>
						<style>
							#home_error{
								
								border-color:red; 
							}
						</style>
				</c:if>  --%>
					
				<c:if test="${error=='routeExistError'}">
						<div class="alert alert-danger fade in">
						<i class="icon-remove close" data-dismiss="alert"></i>
						<strong>Error! </strong>  Route already Available, Please provide unique Route Name</span>
						</div>
						<style>
							#home_error{
								
								border-color:red; 
							}
						</style>
				</c:if> 
				
				<%-- <c:if test="${error_message=='Session2_end_start_more'}">
						<div class="alert alert-danger fade in">
						<i class="icon-remove close" data-dismiss="alert"></i>
						<strong>Error! </strong> Session 2 end time should not be less than Session 2 start time...</span>
						</div>
						<style>
							#home_error{
								
								border-color:red; 
							}
						</style>
				</c:if>  --%>

				<!--=== Page Header ===-->
				<div class="page-header">

					<div class="page-title">
						<h3>Routes</h3>
						<span>Update Route</span>
					</div>
					<br>
					<div class="col-sm-4 col-md-1"></div>



				</div>


				<div class="row" id="driver_form_add">
					<!--=== Validation Example 1 ===-->
					<div class="col-md-7">
						<div class="widget box">
							<div class="widget-header">
								<h4>
									<i class="icon-reorder"></i>Route Form
								</h4>
							</div>
							<div class="widget-content">
								<form class="form-horizontal row-border" id="validate-4" action="updaterouteaction" method="post">

									<div class="form-group">
										<label class="col-md-3 control-label">Enable? <span class="required">*</span></label>
										<div class="col-md-9">
											<label class="radio"><input type="radio" name="enabled" value="true" class="required uniform" checked="checked"> Enable</label>
											<label class="radio"><input type="radio" name="enabled" value="false" class="uniform"> Disable</label>
											<label for="gender" class="has-error help-block" generated="true" style="display:none;"></label>
										</div>
									</div>							
									<div class="form-group">
										<label class="col-md-3 control-label">Route Name<span class="required">*</span></label>
										<div class="col-md-9">
											<input type="text" name="route_name" class="form-control required" minlength="1" value="<c:out value="${route.route_name }" ></c:out>">
											<%-- <input type="text" name="route_name" class="form-control required" minlength="1" value="${route.route_name}">
											<input type="hidden" name="id" class="form-control required" value="${route.id }">
											<input type="hidden" name="route_name_old" class="form-control required" value="${route.route_name }"> --%>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Route Display Name<span class="required">*</span></label>
										<div class="col-md-9">
											<input type="text" name="display_name" class="form-control required" minlength="1" value="<c:out value="${route.display_name }" ></c:out>">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Route Type <span class="required">&nbsp&nbsp</span></label>
										<div class="col-md-9">
											<input type="text" readonly="readonly" name="readOnly_route_type" class="form-control required" minlength="1" value="<c:out value="${route.route_type}" ></c:out>">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Route Type <span class="required">*</span></label>
										<div class="col-md-9">
											<select name="route_type" class="form-control required">
												<option value="Pick Up">Pick Up</option>
												<option value="Drop Off">Drop Off</option>
											</select>
										</div>
									</div>
									
									
									
									<div class="alert alert-danger fade in" id="input_errors"
										style="display: none;">

										<strong>Error!</strong> Please fill all the fields which are
										Marked *
									</div>
									<div class="form-actions">
										<a type="button" class="btn btn-default"
											href="<%=request.getContextPath()%>/school_admin/route/routes">Cancel</a> <input
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