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

						<li>Delete Commuter</li>
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
									<i class="icon-reorder"></i>Form -- Delete
								</h4>
							</div>
							
							<div class="widget-content">
								<form class="form-horizontal row-border" id="validate-4" action="deleteaction" method="post">
									<input type="hidden" name="id" 	class="form-control required"  value="<c:out value="${commuter.commuter_id}"></c:out>">											
									<input type="hidden" name="apikey" 	class="form-control required"  value="<c:out value="${commuter.apikey}"></c:out>">											
									<input type="hidden" name="gcm_reg_id" 	class="form-control required"  value="<c:out value="${commuter.gcm_reg_id}"></c:out>">											
								
									<div class="form-group">
										
										<label class="col-md-3 control-label">Name:</label>
										<div class="col-md-9 clearfix">
											
											<label for="chosen1" style="color:sienna;" ><c:out value="${commuter.name}"/></label>
										</div>
									</div>
									<div class="form-group">
										
										<label class="col-md-3 control-label">Email</label>
										<div class="col-md-9 clearfix">
											<label for="chosen1" style="color:sienna;" ><c:out value="${commuter.email}"/></label>

										</div>
									</div>
									<div class="form-group">
										
										<label class="col-md-3 control-label">Mobile</label>
										<div class="col-md-9 clearfix">
												<label for="chosen1" style="color:sienna;" ><c:out value="${commuter.mobile}"/></label>

										</div>
									</div>
									
									<div class="form-group">
										
										<label class="col-md-3 control-label">Gender</label>
										
										<c:if test="${commuter.gender == 'M'}">
										
												<label for="chosen1" style="color:sienna;" >Male</label>

										</c:if>
										<c:if test="${commuter.gender == 'F'}">
										
											<label for="chosen1" style="color:sienna;" >Female</label>

										</c:if>
										
										
									</div>
									<div class="form-group">
										
										<label class="col-md-3 control-label">Status</label>
										<label for="chosen1" style="color:sienna;" ><c:out value="${commuter.status}"/></label>

									</div>
									<div class="form-group">
										
										<label class="col-md-3 control-label">Active</label>
										<label for="chosen1" style="color:sienna;" ><c:out value="${commuter.active}"/></label>

									</div>
									<div class="form-group">
										
										<label class="col-md-3 control-label">Created At</label>
										<label for="chosen1" style="color:sienna;" ><c:out value="${commuter.created_at}"/></label>

									</div>
									<div class="form-group">
										
										<label class="col-md-3 control-label">Verified At</label>
										<label for="chosen1" style="color:sienna;" ><c:out value="${commuter.verified_at}"/></label>

									</div>
									<div class="form-actions">
										<a type="button" class="btn btn-default" href="list">Cancel</a>
										<input type="submit" value="Delete" class="btn btn-danger pull-right" id="add_new_driver_action">
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