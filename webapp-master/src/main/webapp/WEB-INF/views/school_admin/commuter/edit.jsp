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
						<li>Commuter</li>

						<li>Update Commuter</li>
					</ul>

					<ul class="crumb-buttons">
						<li><a href="#"> <i class="icon-calendar"></i>&nbsp;${date}
						</a></li>
					</ul>
				</div>
				<!-- /Breadcrumbs line -->

				<!--=== Page Header ===-->
				<div class="page-header">

				
					<br>
					<div class="col-sm-4 col-md-1"></div>
				</div>
				<c:if test="${ updated == true }">
					<p style="color:green;">Details updated...........</p>
				</c:if>
				<div class="row" id="driver_form_add">
					<!--=== Validation Example 1 ===-->
					<div class="col-md-7">
						<div class="widget box">
							<div class="widget-header">	
								<h4>
									<i class="icon-reorder"></i>Form -- Update
								</h4>
							</div>
							
							<div class="widget-content">
								<form class="form-horizontal row-border" id="validate-4" action="editaction" method="post">
									<input type="hidden" name="id" 	class="form-control required"  value="<c:out value="${commuter.commuter_id}"></c:out>">											
									<input type="hidden" name="apikey" 	class="form-control required"  value="<c:out value="${commuter.apikey}"></c:out>">											
									<input type="hidden" name="gcm_reg_id" 	class="form-control required"  value="<c:out value="${commuter.gcm_reg_id}"></c:out>">											
								
									<div class="form-group">
										
										<label class="col-md-3 control-label">Name</label>
										<div class="col-md-9 clearfix">
											<input type="text" name="name" 	class="form-control required"  value="<c:out value="${commuter.name}"></c:out>">											
											
											<label for="chosen1" generated="true"
												class="has-error help-block" style="display: none;"></label>
										</div>
									</div>
									<div class="form-group">
										
										<label class="col-md-3 control-label">Email</label>
										<div class="col-md-9 clearfix">
											<input type="text" name="email" 	class="form-control required"  value="<c:out value="${commuter.email}"></c:out>">											
											
											<label for="chosen1" generated="true"
												class="has-error help-block" style="display: none;"></label>
										</div>
									</div>
									<div class="form-group">
										
										<label class="col-md-3 control-label">Mobile</label>
										<div class="col-md-9 clearfix">
											<input type="text" name="mobile" 	class="form-control required"  value="<c:out value="${commuter.mobile}"></c:out>">											
											
											<label for="chosen1" generated="true"
												class="has-error help-block" style="display: none;"></label>
										</div>
									</div>
									
									<div class="form-group">
										
										<label class="col-md-3 control-label">Gender</label>
										<div class="col-md-9 clearfix">
										<c:if test="${commuter.gender == 'M'}">
										
											<select name="gender"
													class="col-md-9 select2 full-width-fix required"
													id="driver_id_name" style="border-color: grey;">
													<option value="M"  selected="selected">Male</option>
													<option value="F" >Female</option>
											</select>
										</c:if>
										<c:if test="${commuter.gender == 'F'}">
										
											<select name="gender"
													class="col-md-9 select2 full-width-fix required"
													id="driver_id_name" style="border-color: grey;">
													<option value="M">Male</option>
													<option value="F" selected="selected">Female</option>
											</select>
										</c:if>
										<c:if test="${commuter.gender == null}">
										
											<select name="gender"
													class="col-md-9 select2 full-width-fix required"
													id="driver_id_name" style="border-color: grey;">
													<option value="M">Male</option>
													<option  value="F">Female</option>
											</select>
										</c:if>
										</div>
									</div>
									<div class="form-group">
										
										<label class="col-md-3 control-label">Status</label>
										<div class="col-md-9 clearfix">
											<input type="text" name="status" 	class="form-control required"  value="<c:out value="${commuter.status}"></c:out>">											
											
											<label for="chosen1" generated="true"
												class="has-error help-block" style="display: none;"></label>
										</div>
									</div>
									<div class="form-group">
										
										<label class="col-md-3 control-label">Active</label>
										<div class="col-md-9 clearfix">
											<input type="text" name="active" 	class="form-control required"  value="<c:out value="${commuter.active}"></c:out>">											
											
											<label for="chosen1" generated="true"
												class="has-error help-block" style="display: none;"></label>
										</div>
									</div>
									<div class="form-group">
										
										<label class="col-md-3 control-label">Created At</label>
										<div class="col-md-9 clearfix">
											<input type="text" name="created_at" 	class="form-control"  value="<c:out value="${commuter.created_at}"></c:out>">											
											
											<label for="chosen1" generated="true"
												class="has-error help-block" style="display: none;"></label>
										</div>
									</div>
									<div class="form-group">
										
										<label class="col-md-3 control-label">Verified At</label>
										<div class="col-md-9 clearfix">
											<input type="text" name="verified_at" 	class="form-control"  value="<c:out value="${commuter.verified_at}"></c:out>">											
											
											<label for="chosen1" generated="true"
												class="has-error help-block" style="display: none;"></label>
										</div>
									</div>
									<div class="form-actions">
										<a type="button" class="btn btn-default" href="list">Cancel</a>
										<input type="submit" value="Update" class="btn btn-primary pull-right" id="add_new_driver_action">
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