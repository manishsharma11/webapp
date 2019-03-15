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
						<li>Users & Roles</li>
						<li>Remove User</li>
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
						<h3>Users</h3>
						<span>Remove User</span>
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
									<i class="icon-reorder"></i>User Form
								</h4>
							</div>
							<div class="widget-content">
								<form class="form-horizontal row-border" id="validate-4"
									action="deleteRoleAction" method="post">
									
									<div class="form-group">
										<label class="col-md-3 control-label">Full Name: 
										</label>
										<div class="col-md-9">
										<label class="control-label">
										<span class="required">
										<c:out value="${roleEntity.full_name }"></c:out></span>
										</label>
										</div>
									
									</div>
									
									<div class="form-group">
										<label class="col-md-3 control-label">User Name:
										</label>
										<div class="col-md-9">
										<label class="control-label">
										 <span
											class="required"><c:out
													value="${roleEntity.user_name }"></c:out></span> 
											</label>
											<input
											type="hidden" value="${roleEntity.user_id }" name="roleId_hidden">
										</div>
										 
									</div>
									 
									<div class="form-group">
										<label class="col-md-3 control-label">Role:</label>
										
										<div class="col-md-9">
										
										<label class="control-label">
										 <span
											class="required"><c:out value="${roleEntity.getRole().getRole_name() }"></c:out></span>
										</label>
										 </div>
									</div>

									<div class="alert alert-danger fade in" id="input_errors"
										style="display: none;">
										<strong>Error!</strong> Please fill all the fields which are
										marked *
									</div>
									<div class="form-actions">
										<a type="button" class="btn btn-default"
											href="<%=request.getContextPath()%>/school_admin/roles/change">Cancel</a> <input
											type="submit" value="Remove"
											class="btn btn-sm btn-danger pull-right"
											id="add_new_driver_action">
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