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
						<li>Users & Roles</li>						
						<li>Update User</li>
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
						<h3>Users</h3>
						<span>Update User</span>
					</div>
			
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
									action="updateRoleAction1" method="post">
									
									
									<div class="form-group">
										<label class="col-md-3 control-label">Full Name<span
											class="required">*</span></label>
										<div class="col-md-9">
											<input type="text" name="full_name"
												class="form-control required" maxlength="20"
												 
												value="${roleEntity.full_name}">												
										</div>										
									</div>
									
									
									<div class="form-group">
										<label class="col-md-3 control-label">User Name<span
											class="required">*</span></label>
										<div class="col-md-9">
											<input type="text" name="user_name"
												class="form-control required"  
												value="${roleEntity.user_name}">
										</div>
										<input type="hidden" value="${roleEntity.user_id }" name="userid">
									</div>
									<c:if test="${error=='userNameError'}">
											<div class="alert alert-danger fade in" align="center"
												id="prev_trip1">
												<strong>Error!</strong> User Name already exist please try another name.
											</div>
										</c:if>
									 <c:if test="${error_occured ne 'yes'}">
									<div class="form-group">
										<label class="col-md-3 control-label">Role<span class="required">*</span></label>
										<div class="col-md-9">
										
											<select name="role" class="form-control required">
												 <c:forEach var="roles" items="${allroles}">
												<c:choose>
												 		<c:when test="${roleEntity.getRole().getRole_name() eq roles.role_name}">
												 		
												 			<option  value="<c:out value="${roles.role_id}" ></c:out> " selected="selected"><c:out value="${roles.role_name}"></c:out></option>
												 			 
												 		</c:when>
												 	
												 	<c:otherwise>
												 		<option  value="<c:out value="${roles.role_id}"></c:out>" ><c:out value="${roles.role_name}"></c:out></option>
												 	
												 	</c:otherwise>
												 	</c:choose>
												 </c:forEach>
											</select>
											
										</div>
									</div>			
									</c:if>		
									<c:if test="${error_occured eq 'yes'}">
									<div class="form-group">
										<label class="col-md-3 control-label">Role<span class="required">*</span></label>
										<div class="col-md-9">
										
											<select name="role" class="form-control required">
												 <c:forEach var="roles" items="${allroles}">
												 	<c:choose>
												 		<c:when test="${roleEntity.getRole().getRole_name() eq roles.role_name}">
												 		
												 			<option  value="<c:out value="${roles.role_id}" ></c:out> " selected="selected"><c:out value="${roles.role_name}"></c:out></option>
												 			 
												 		</c:when>
												 	
												 	<c:otherwise>
												 		<option  value="<c:out value="${roles.role_id}"></c:out>" ><c:out value="${roles.role_name}"></c:out></option>
												 	
												 	</c:otherwise>
												 	</c:choose>
												 
												 
												
												 </c:forEach>
											</select>
											
										</div>
									</div>			
									</c:if>								
									
									<div class="form-group">
										<label class="col-md-3 control-label">Access Status<span class="required">*</span></label>
										<div class="col-md-9">
										
											<select name="access_status" class="form-control required">
											<c:choose>
											
												<c:when test="${roleEntity.access_status eq 'Disabled' }">
													<option value="Disabled" selected="selected">Disabled</option>
													<option value="Enabled">Enabled</option>
												</c:when>
												<c:otherwise>
													<option value="Enabled" selected="selected">Enabled</option>
													<option value="Disabled">Disabled</option>
												</c:otherwise>
											</c:choose>
											 
											
											 
											
											 
											
											
											</select></div></div>				
									<div class="alert alert-danger fade in" id="input_errors"
										style="display: none;">

										<strong>Error!</strong> Please fill all the fields which are
										Marked *
									</div>
									<div class="form-actions">
										<a type="button" class="btn btn-default"
											href="<%=request.getContextPath()%>/school_admin/roles/change">Cancel</a>
										<input type="submit" value="Update"
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